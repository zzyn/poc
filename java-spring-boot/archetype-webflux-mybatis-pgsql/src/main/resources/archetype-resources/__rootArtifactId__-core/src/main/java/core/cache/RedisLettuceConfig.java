package ${groupId}.core.cache;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.models.role.RedisNodeDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Configuration
public class RedisLettuceConfig {

    private final AtomicInteger index = new AtomicInteger(-1);
    private Logger logger = LoggerFactory.getLogger(RedisLettuceConfig.class);
    @Autowired
    private RedisMasterSlaveConfigProperties masterSlaveConfigProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        RedisHostInfo master = masterSlaveConfigProperties.getMasterHostInfo();
        List<RedisHostInfo> slaves = masterSlaveConfigProperties.getSlaveInfos();

        RedisStaticMasterReplicaConfiguration awsElasticRedisConfig = new RedisStaticMasterReplicaConfiguration(master.getHost(), master.getPort());

        awsElasticRedisConfig.setDatabase(masterSlaveConfigProperties.getDatabase());

        if (Objects.nonNull(slaves) && !slaves.isEmpty()) {

            slaves
                    .stream()
                    .forEachOrdered(x -> {
                        awsElasticRedisConfig.addNode(x.getHost(), x.getPort());
                    });
        }

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration
                .builder()
                .readFrom(new ReadFrom() {
                    @Override
                    public List<RedisNodeDescription> select(Nodes nodes) {
                        List<RedisNodeDescription> allNodes = nodes.getNodes();
                        int ind = Math.abs(index.incrementAndGet() % allNodes.size());
                        RedisNodeDescription selected = allNodes.get(ind);
                        logger.info("Selected random node {} with uri {}", ind, selected.getUri());
                        List<RedisNodeDescription> remaining = IntStream.range(0, allNodes.size())
                                .filter(i -> i != ind)
                                .mapToObj(allNodes::get).collect(toList());
                        return Stream.concat(
                                Stream.of(selected),
                                remaining.stream()
                        ).collect(toList());
                    }
                })
                .build();

        return new LettuceConnectionFactory(awsElasticRedisConfig, clientConfig);
    }
}
