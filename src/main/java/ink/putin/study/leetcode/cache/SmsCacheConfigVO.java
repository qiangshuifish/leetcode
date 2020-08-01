package ink.putin.study.leetcode.cache;

import java.util.Arrays;
import java.util.Objects;

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
public class SmsCacheConfigVO {

    private String[] cacheNames;

    private String key;

    private String keyGenerator;

    private String cacheManager;

    private String cacheResolver;

    private String condition;

    private String unless;

    private Boolean sync;

    private Long expire;

    private String[] keyGeneratorFieldsExclude;

    public String[] getCacheNames() {
        return cacheNames;
    }

    public void setCacheNames(String[] cacheNames) {
        this.cacheNames = cacheNames;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyGenerator() {
        return keyGenerator;
    }

    public void setKeyGenerator(String keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public String getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(String cacheManager) {
        this.cacheManager = cacheManager;
    }

    public String getCacheResolver() {
        return cacheResolver;
    }

    public void setCacheResolver(String cacheResolver) {
        this.cacheResolver = cacheResolver;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getUnless() {
        return unless;
    }

    public void setUnless(String unless) {
        this.unless = unless;
    }

    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String[] getKeyGeneratorFieldsExclude() {
        return keyGeneratorFieldsExclude;
    }

    public void setKeyGeneratorFieldsExclude(String[] keyGeneratorFieldsExclude) {
        this.keyGeneratorFieldsExclude = keyGeneratorFieldsExclude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsCacheConfigVO that = (SmsCacheConfigVO) o;
        return Arrays.equals(cacheNames, that.cacheNames) &&
                Objects.equals(key, that.key) &&
                Objects.equals(keyGenerator, that.keyGenerator) &&
                Objects.equals(cacheManager, that.cacheManager) &&
                Objects.equals(cacheResolver, that.cacheResolver) &&
                Objects.equals(condition, that.condition) &&
                Objects.equals(unless, that.unless) &&
                Objects.equals(sync, that.sync) &&
                Objects.equals(expire, that.expire) &&
                Arrays.equals(keyGeneratorFieldsExclude, that.keyGeneratorFieldsExclude);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(key, keyGenerator, cacheManager, cacheResolver, condition, unless, sync, expire);
        result = 31 * result + Arrays.hashCode(cacheNames);
        result = 31 * result + Arrays.hashCode(keyGeneratorFieldsExclude);
        return result;
    }
}
