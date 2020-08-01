package ink.putin.study.leetcode.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


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
@Configuration
@EnableCaching
@PropertySource("classpath:redis.properties")
public class MyCacheConfig extends CacheConfiguration {
    @Override
    public String redisCachePrefix() {
        return "gaea-customer";
    }
}
