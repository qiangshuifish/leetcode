package ink.putin.study.leetcode.cache;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 呛水滴鱼
 * @version 1.0
 * @apiNote
 * @date 2020-02-11 21:17
 * @since 1.0
 */
@Component
public class CacheServiceTest {

    private AtomicInteger testStringCount = new AtomicInteger(0);
    private AtomicInteger testObjectCount = new AtomicInteger(0);
    private AtomicInteger testCacheableCount = new AtomicInteger(0);

    @SmsSpringRedisCache(expire = 5)
    public String testString(String  key){
        return "testString ==== " + key + " ==== " + + testStringCount.incrementAndGet();
    }

    @SmsSpringRedisCache(expire = 5)
    public JSONObject testObjectCount(String  key){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key",key);
        jsonObject.put("count",testObjectCount.incrementAndGet());
        jsonObject.put("name",testObjectCount);
        return jsonObject;
    }

    @SpringCaCheRedisExpire(5)
    @Cacheable
    public String testCacheable(String  key){
        return "testCacheable ==== " + key + " ==== " + + testCacheableCount.incrementAndGet();
    }

}
