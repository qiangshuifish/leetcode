package ink.putin.study.leetcode.cache;

import java.lang.annotation.*;

/**
 * <p>为Spring的添加过期时间</p>
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
 * @see org.springframework.cache.annotation.Cacheable,org.springframework.cache.annotation.CachePut
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SpringCaCheRedisExpire {

    /**
     * 过期时间
     * @return
     */
    long value() default 600;
}
