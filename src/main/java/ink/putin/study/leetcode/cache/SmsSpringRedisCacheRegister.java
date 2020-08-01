package ink.putin.study.leetcode.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author wenpengyuan
 * @version 1.0
 * @since 1.0
 */
class SmsSpringRedisCacheRegister {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSpringRedisCacheRegister.class);

    private static Map<String, Long> expiresMap = new ConcurrentHashMap<>();
    private static Map<String, SmsCacheConfigVO> cacheConfigVOMap = new ConcurrentHashMap<>();


    /**
     * 注册 SmsSpringRedisCache
     */
    static void register(Method method,SmsSpringRedisCache cache){
        if(Objects.isNull(method) || Objects.isNull(cache)){
            return;
        }
        //获取默认的 cacheName
        String cacheName = getCacheName(method);

        SmsCacheConfigVO vo = getDefault();

        // 默认使用类名加方法名作为缓存的名称
        if(cache.cacheNames().length == 0 || StringUtils.isBlank(cache.cacheNames()[0])){
            vo.setCacheNames(new String[]{cacheName});
        }else{
            vo.setCacheNames(cache.cacheNames());
            cacheName = cache.cacheNames()[0];
        }

        // 已经包含缓存
        if(cacheConfigVOMap.containsKey(cacheName.intern())){
            return;
        }

        if(StringUtils.isNotBlank(cache.keyGenerator()) && StringUtils.isNotBlank(cache.key())){
            vo.setKeyGenerator(cache.keyGenerator());
        }
        if(StringUtils.isNotBlank(cache.cacheManager())){
            vo.setCacheManager(cache.cacheManager());
        }
        if(StringUtils.isNotBlank(cache.unless())){
            vo.setUnless(cache.unless());
        }

        vo.setKey(cache.key());
        vo.setCacheResolver(cache.cacheResolver());
        vo.setCondition(cache.condition());
        vo.setSync(cache.sync());
        vo.setExpire(cache.expire());
        vo.setKeyGeneratorFieldsExclude(cache.keyGeneratorFieldsExclude());

        cacheConfigVOMap.put(cacheName,vo);
        expiresMap.put(cacheName,cache.expire());

        LOGGER.info("注册缓存：{},过期时间：{}",cacheName,vo.getExpire());
    }

    private static SmsCacheConfigVO getDefault(){
        SmsCacheConfigVO vo = new SmsCacheConfigVO();
        vo.setUnless(CacheConfiguration.RESULT_NULL_NOT_CACHE);
        vo.setCacheManager(CacheConfiguration.REDIS_CACHE_MANAGER);
        vo.setKeyGenerator(CacheConfiguration.SMS_KEY_GENERATOR);
        return vo;
    }

    /**
     * 注册 Cacheable
     */
    static void register(Method method, Cacheable cache, SpringCaCheRedisExpire expire){
        if(Objects.isNull(method) || Objects.isNull(cache)){
            return;
        }
        //获取默认的 cacheName
        String cacheName = getCacheName(method);

        SmsCacheConfigVO vo = getDefault();

        // 默认使用类名加方法名作为缓存的名称
        if(cache.cacheNames().length == 0 || StringUtils.isBlank(cache.cacheNames()[0])){
            vo.setCacheNames(new String[]{cacheName});
        }else{
            vo.setCacheNames(cache.cacheNames());
            cacheName = cache.cacheNames()[0];
        }

        // 已经包含缓存
        if(cacheConfigVOMap.containsKey(cacheName.intern())){
            return;
        }

        if(StringUtils.isNotBlank(cache.keyGenerator()) && StringUtils.isNotBlank(cache.key())){
            vo.setKeyGenerator(cache.keyGenerator());
        }
        if(StringUtils.isNotBlank(cache.cacheManager())){
            vo.setCacheManager(cache.cacheManager());
        }
        if(StringUtils.isNotBlank(cache.unless())){
            vo.setUnless(cache.unless());
        }

        vo.setKey(cache.key());
        vo.setCacheResolver(cache.cacheResolver());
        vo.setCondition(cache.condition());
        vo.setSync(cache.sync());
        if(Objects.nonNull(expire)){
            vo.setExpire(expire.value());
            expiresMap.put(cacheName,expire.value());
        }
        cacheConfigVOMap.put(cacheName,vo);
        LOGGER.info("注册缓存：{},过期时间：{}",cacheName,vo.getExpire());
    }

    /**
     * 注册 CachePut
     */
    static void register(Method method, CachePut cache, SpringCaCheRedisExpire expire){
        if(Objects.isNull(method) || Objects.isNull(cache)){
            return;
        }
        //获取默认的 cacheName
        String cacheName = getCacheName(method);

        SmsCacheConfigVO vo = getDefault();

        // 默认使用类名加方法名作为缓存的名称
        if(cache.cacheNames().length == 0 || StringUtils.isBlank(cache.cacheNames()[0])){
            vo.setCacheNames(new String[]{cacheName});
        }else{
            vo.setCacheNames(cache.cacheNames());
            cacheName = cache.cacheNames()[0];
        }

        // 已经包含缓存
        if(cacheConfigVOMap.containsKey(cacheName.intern())){
            return;
        }

        if(StringUtils.isNotBlank(cache.keyGenerator()) && StringUtils.isNotBlank(cache.key())){
            vo.setKeyGenerator(cache.keyGenerator());
        }
        if(StringUtils.isNotBlank(cache.cacheManager())){
            vo.setCacheManager(cache.cacheManager());
        }
        if(StringUtils.isNotBlank(cache.unless())){
            vo.setUnless(cache.unless());
        }

        vo.setKey(cache.key());
        vo.setCacheResolver(cache.cacheResolver());
        vo.setCondition(cache.condition());
        if(Objects.nonNull(expire)){
            vo.setExpire(expire.value());
            expiresMap.put(cacheName,expire.value());
        }
        cacheConfigVOMap.put(cacheName,vo);
        LOGGER.info("注册缓存：{},过期时间：{}",cacheName,vo.getExpire());
    }

    /**
     * 获取默认的缓存名称
     */
    private static String getCacheName(Method method) {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        return className + "." +methodName;
    }


    /**
     * 获取缓存配置
     */
    static SmsCacheConfigVO getCacheConfigVo(String cacheName){
        return cacheConfigVOMap.get(cacheName);
    }

    /**
     * 获取过期时间map
     */
    static Map<String, Long> getExpiresMap() {
        return expiresMap;
    }
}
