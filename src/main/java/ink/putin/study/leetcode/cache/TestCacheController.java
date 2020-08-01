package ink.putin.study.leetcode.cache;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 呛水滴鱼
 * @version 1.0
 * @apiNote
 * @date 2020-02-11 21:25
 * @since 1.0
 */
@RequestMapping("/test")
@RestController
public class TestCacheController {

    @Resource
    private CacheServiceTest cacheServiceTest;

    @GetMapping("/testString")
    public String testString(String key) {
        return cacheServiceTest.testString(key);
    }

    @GetMapping("/testObjectCount")
    public JSONObject testObjectCount(String key) {
        return cacheServiceTest.testObjectCount(key);
    }

    @GetMapping("/testCacheable")
    public String testCacheable(String key) {
        return cacheServiceTest.testCacheable(key);
    }
}
