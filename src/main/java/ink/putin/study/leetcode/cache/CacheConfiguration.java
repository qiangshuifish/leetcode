package ink.putin.study.leetcode.cache;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * <p>Spring Redis 缓存配置</p>
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
public class CacheConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfiguration.class);


    public static final String REDIS_CACHE_EVN = "redisCacheEvn";
    public static final String SMS_KEY_GENERATOR = "keyGenerator";
    public static final String REDIS_CACHE_MANAGER = "redisCacheManager";
    public static final String RESULT_NULL_NOT_CACHE = "#result == null";

    /**
     * 构造缓存key的时候忽略的属性名称列表
     */
    private List<String> cacheKeyExceptProperty = Lists.newArrayList("timestamp","uuid");


    @Bean("cacheOperationSource")
    @Primary
    public AnnotationCacheOperationSource annotationCacheOperationSource() {
        return new AnnotationCacheOperationSource(new SmsCacheAnnotationParser());
    }

    /**
     * 默认缓存的前缀
     *
     * @return
     */
    protected String redisCachePrefix() {
        return "redis_cache";
    }

    /**
     * 构造缓存key的时候忽略的属性名称列表
     * @return
     */
    protected List<String> cacheKeyExceptProperties() {
        return cacheKeyExceptProperty;
    }

    @Bean(REDIS_CACHE_EVN)
    public String redisCacheEvn(Environment environment) {
        String evn = environment.getProperty("app.environment");
        if ("dev".equals(evn) || "D".equals(evn) || "d".equals(evn)) {
            return "dev";
        }
        if ("test".equals(evn) || "T".equals(evn) || "t".equals(evn)) {
            return "test";
        }
        return "prod";
    }

    private Boolean isNoCacheEvn(Environment environment) {
        String evn = environment.getProperty("app.environment");
        if ("dev".equals(evn) || "D".equals(evn) || "d".equals(evn)) {
            return true;
        }
        if ("test".equals(evn) || "T".equals(evn) || "t".equals(evn)) {
            return true;
        }
        return false;
    }


    @Bean(SMS_KEY_GENERATOR)
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {

            String cacheName = method.getDeclaringClass().getName() + "." + method.getName();

            StringBuilder builder = new StringBuilder();
            for (Object param : params) {
                // 基本类型
                if (isPrimitive(param)) {
                    builder.append(Objects.hashCode(param));
                // 空
                } else if (Objects.isNull(param)) {
                    builder.append(Objects.hashCode(null));
                // 包装类型
                } else {
                    SmsCacheConfigVO cacheConfigVo = SmsSpringRedisCacheRegister.getCacheConfigVo(cacheName);
                    if(Objects.nonNull(cacheConfigVo)){
                        String[] keyGeneratorFieldsExclude = cacheConfigVo.getKeyGeneratorFieldsExclude();
                        if(keyGeneratorFieldsExclude != null && keyGeneratorFieldsExclude.length > 0){
                            builder.append(toJsonString(param,Lists.newArrayList(keyGeneratorFieldsExclude)).intern().hashCode());
                        }
                    }else{
                        builder.append(toJsonString(param,null).intern().hashCode());
                    }
                }
            }
            return cacheName + "_" + builder.toString();
        };
    }


    /**
     * 对象转json，并排除指定的属性值
     * @param obj
     * @return
     */
    private String toJsonString(Object obj,List<String> cacheKeyExceptProperty) {

        // 如果有自定义的使用自定义的
        final List<String> cacheKeyExceptPropertyFinal = CollectionUtils.isEmpty(cacheKeyExceptProperty)?
                cacheKeyExceptProperties():cacheKeyExceptProperty;

        // 属性过滤器
        PropertyFilter propertyFilter = (object, name, value) -> {
            if(cacheKeyExceptPropertyFinal.contains(name)){
                return false;
            }
            return true;
        };

        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.getPropertyFilters().add(propertyFilter);
        serializer.write(obj);
        return out.toString();
    }


    /**
     * 判断一个对象是否是基本类型或基本类型的封装类型
     */
    private static boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    @Bean
    public SpringCaCheRedisExpireRegistry springCaCheRedisExpireRegistry() {
        return new SpringCaCheRedisExpireRegistry();
    }

    /**
     * SpringRedis缓存配置
     *
     * @param redisTemplate
     * @param environment
     * @return
     */
    @Bean(REDIS_CACHE_MANAGER)
    @Primary
    public RedisCacheManager concurrentMapCacheManager(RedisTemplate redisTemplate, Environment environment) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        String appName = environment.getProperty("redis.app");
        redisCacheManager.setCachePrefix(new DefaultRedisCachePrefix(appName + "_" + redisCachePrefix()));
        // 研发，测试环境不缓存
        if(isNoCacheEvn(environment)){
            Map<String, Long> expiresMap = SmsSpringRedisCacheRegister.getExpiresMap();
            expiresMap.forEach((k,v) -> expiresMap.put(k,1L));
            redisCacheManager.setExpires(expiresMap);
            LOGGER.info("============= 研发测试环境 缓存1秒 =============");
        }else{
            redisCacheManager.setExpires(SmsSpringRedisCacheRegister.getExpiresMap());
        }
        // 默认10分钟过期
        redisCacheManager.setDefaultExpiration(60 * 10);
        return redisCacheManager;
    }


    /**
     * redis操作模版
     *
     * @param factory
     * @return
     */
    @Bean("redisTemplate")
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * redis工厂
     *
     * @param environment
     * @param redisSentinelConfiguration
     * @return
     */
    @Bean("redisConnectionFactory")
    @Primary
    public RedisConnectionFactory redisConnectionFactory(Environment environment, RedisSentinelConfiguration redisSentinelConfiguration) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(environment.getProperty("redis.sentinel.maxTotal", Integer.class));
        poolConfig.setMaxIdle(environment.getProperty("redis.sentinel.maxIdle", Integer.class));
        poolConfig.setMinIdle(environment.getProperty("redis.sentinel.minIdle", Integer.class));
        poolConfig.setMaxWaitMillis(environment.getProperty("redis.sentinel.maxWaitMillis", Integer.class));
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration, poolConfig);
        redisConnectionFactory.afterPropertiesSet();
        return redisConnectionFactory;
    }

    /**
     * redis哨兵配置
     *
     * @param environment
     * @return
     */
    @Bean("redisSentinelConfiguration")
    @Primary
    public RedisSentinelConfiguration redisSentinelConfiguration(Environment environment) {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
        sentinelConfig.master(environment.getProperty("redis.sentinel.masterName"));
        String[] hosts = environment.getProperty("redis.sentinel.server").split("\\|");
        for (String host : hosts) {
            String[] split = host.split(":");
            sentinelConfig.sentinel(split[0], Integer.valueOf(split[1]));
        }
        return sentinelConfig;
    }


}
