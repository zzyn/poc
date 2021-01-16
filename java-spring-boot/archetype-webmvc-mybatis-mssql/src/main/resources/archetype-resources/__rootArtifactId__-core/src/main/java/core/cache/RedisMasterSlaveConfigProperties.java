package ${groupId}.core.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisMasterSlaveConfigProperties {

    /**
     * master node
     */
    private String master;

    /**
     * slave nodes
     */
    private List<String> slaves;

    /**
     * database index
     */
    private Integer database;

    /**
     * connection timeout
     */
    private Integer timeout;

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public RedisHostInfo getMasterHostInfo(){
        String[] data = master.split(":");
        return new RedisHostInfo()
                .setHost(data[0])
                .setPort(Integer.valueOf(data[1]));
    }

    public List<String> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<String> slaves) {
        this.slaves = slaves;
    }

    public List<RedisHostInfo> getSlaveInfos(){
        List<RedisHostInfo> list = new ArrayList<>();

        if(Objects.nonNull(slaves) && slaves.size() > 0){
            slaves.stream().forEachOrdered(x->{
                String[] data = x.split(":");
                list.add(new RedisHostInfo()
                        .setHost(data[0])
                        .setPort(Integer.valueOf(data[1])));
            });
        }
        return list;
    }
}
