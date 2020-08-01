package ink.putin.study.leetcode.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p>1.在所有bean注册注册到Spring后，扫面所有的类，提取SpringCaCheRedisExpire的值</p>
 * <p>2.在创建完 RedisCacheManager 后修改它的 Expires</p>
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
@Slf4j
public class SpringCaCheRedisExpireRegistry implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        for (String name : registry.getBeanDefinitionNames()) {
            BeanDefinition definition = registry.getBeanDefinition(name);
            if(Objects.isNull(definition) || StringUtils.isBlank(definition.getBeanClassName())){
                continue;
            }
            Class<?> clazz;
            try {
                clazz = Class.forName(definition.getBeanClassName());
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError("没有找到："+ definition.getBeanClassName());
            }
            for (Method method : clazz.getMethods()) {

                SmsSpringRedisCache smsSpringRedisCache = method.getAnnotation(SmsSpringRedisCache.class);
                SpringCaCheRedisExpire springRedisCaCheExpire = method.getAnnotation(SpringCaCheRedisExpire.class);
                CachePut cachePut = method.getAnnotation(CachePut.class);
                Cacheable cacheable = method.getAnnotation(Cacheable.class);

                // 缓存注册
                SmsSpringRedisCacheRegister.register(method,smsSpringRedisCache);
                SmsSpringRedisCacheRegister.register(method,cachePut,springRedisCaCheExpire);
                SmsSpringRedisCacheRegister.register(method,cacheable,springRedisCaCheExpire);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
