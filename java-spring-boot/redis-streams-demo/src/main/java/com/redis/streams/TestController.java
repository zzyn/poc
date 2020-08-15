package com.redis.streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/redis")
public class TestController {

    private final RedisTemplate<String, Object> redisTemplate;
    private final String streamName = "test_stream";
    private final String consumerGroup = "test_group";

    @Autowired
    public TestController(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    @GetMapping(path = "/produce")
    public Mono<RecordId> produce(){

        Integer index = new Random().nextInt();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("name", String.format("test_%s", index));
        map.put("age", index.toString());
        RecordId recordId = redisTemplate.opsForStream().add(streamName, map);

        return Mono.justOrEmpty(recordId);
    }

    @GetMapping(path = "/create_group")
    public Mono<String> create_group(){

        String group = redisTemplate.opsForStream().createGroup(streamName , consumerGroup);

        return Mono.justOrEmpty(group);
    }


    /**
     * unbounded range
     * @return
     */
    @GetMapping(path = "/range")
    public Mono<List<MapRecord<String,Object,Object>>> range(){
        Range<String>  range = Range.unbounded();
        List<MapRecord<String,Object,Object>> list = redisTemplate.opsForStream().range(streamName, range);
        return Mono.justOrEmpty(list);
    }

    /**
     * unbounded range with limit
     * @return
     */
    @GetMapping(path = "/range_limit")
    public Mono<List<MapRecord<String,Object,Object>>> range_limit_unbounded(){
        Range<String>  range = Range.unbounded();
        RedisZSetCommands.Limit limit = RedisZSetCommands.Limit.limit();
        limit = limit.count(3);
        List<MapRecord<String,Object,Object>> list = redisTemplate.opsForStream().range(streamName, range, limit);
        return Mono.justOrEmpty(list);
    }

    /**
     * left bounded range with limit
     * @return
     */
    @GetMapping(path = "/range_limit_left")
    public Mono<List<MapRecord<String,Object,Object>>> range_limit_left(){
        Range<String>  range = Range.rightUnbounded(Range.Bound.inclusive("1585293962443-0"));
        RedisZSetCommands.Limit limit = RedisZSetCommands.Limit.limit();
        limit = limit.count(3);
        List<MapRecord<String,Object,Object>> list = redisTemplate.opsForStream().range(streamName, range, limit);
        return Mono.justOrEmpty(list);
    }

    /**
     * right bounded range with limit
     * @return
     */
    @GetMapping(path = "/range_limit_right")
    public Mono<List<MapRecord<String,Object,Object>>> range_limit_right(){
        Range<String>  range = Range.leftUnbounded(Range.Bound.inclusive("1585293962443-0"));
        RedisZSetCommands.Limit limit = RedisZSetCommands.Limit.limit();
        limit = limit.count(1);
        List<MapRecord<String,Object,Object>> list = redisTemplate.opsForStream().range(streamName, range, limit);
        return Mono.justOrEmpty(list);
    }

    /**
     * unbounded reverse range
     * @return
     */
    @GetMapping(path = "/reverse_range")
    public Mono<List<MapRecord<String,Object,Object>>> reverse_range(){
        Range<String> range = Range.unbounded();
        List<MapRecord<String,Object,Object>> list = redisTemplate.opsForStream().reverseRange(streamName, range);
        return Mono.justOrEmpty(list);
    }



    /**
     * consume
     * @return
     */
    @GetMapping(path = "/consume")
    public Mono<List<MapRecord<String,Object,Object>>> publish(){
        StreamReadOptions readOptions = StreamReadOptions.empty();
        List<MapRecord<String,Object,Object>> list = redisTemplate.opsForStream().read(readOptions.noack().count(3));
        return Mono.justOrEmpty(list);
    }
}
