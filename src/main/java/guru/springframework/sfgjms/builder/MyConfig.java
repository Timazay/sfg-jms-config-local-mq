package guru.springframework.sfgjms.builder;

import com.typesafe.config.*;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MyConfig implements Config {
    @Override
    public ConfigObject root() {
        return null;
    }

    @Override
    public ConfigOrigin origin() {
        return null;
    }

    @Override
    public Config withFallback(ConfigMergeable configMergeable) {
        return null;
    }

    @Override
    public Config resolve() {
        return null;
    }

    @Override
    public Config resolve(ConfigResolveOptions configResolveOptions) {
        return null;
    }

    @Override
    public boolean isResolved() {
        return false;
    }

    @Override
    public Config resolveWith(Config config) {
        return null;
    }

    @Override
    public Config resolveWith(Config config, ConfigResolveOptions configResolveOptions) {
        return null;
    }

    @Override
    public void checkValid(Config config, String... strings) {

    }

    @Override
    public boolean hasPath(String s) {
        return false;
    }

    @Override
    public boolean hasPathOrNull(String s) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set<Map.Entry<String, ConfigValue>> entrySet() {
        return null;
    }

    @Override
    public boolean getIsNull(String s) {
        return false;
    }

    @Override
    public boolean getBoolean(String s) {
        return false;
    }

    @Override
    public Number getNumber(String s) {
        return null;
    }

    @Override
    public int getInt(String s) {
        return 0;
    }

    @Override
    public long getLong(String s) {
        return 0;
    }

    @Override
    public double getDouble(String s) {
        return 0;
    }

    @Override
    public String getString(String s) {
        return null;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> aClass, String s) {
        return null;
    }

    @Override
    public ConfigObject getObject(String s) {
        return null;
    }

    @Override
    public Config getConfig(String s) {
        return null;
    }

    @Override
    public Object getAnyRef(String s) {
        return null;
    }

    @Override
    public ConfigValue getValue(String s) {
        return null;
    }

    @Override
    public Long getBytes(String s) {
        return null;
    }

    @Override
    public ConfigMemorySize getMemorySize(String s) {
        return null;
    }

    @Override
    public Long getMilliseconds(String s) {
        return null;
    }

    @Override
    public Long getNanoseconds(String s) {
        return null;
    }

    @Override
    public long getDuration(String s, TimeUnit timeUnit) {
        return 0;
    }

    @Override
    public Duration getDuration(String s) {
        return null;
    }

    @Override
    public Period getPeriod(String s) {
        return null;
    }

    @Override
    public TemporalAmount getTemporal(String s) {
        return null;
    }

    @Override
    public ConfigList getList(String s) {
        return null;
    }

    @Override
    public List<Boolean> getBooleanList(String s) {
        return null;
    }

    @Override
    public List<Number> getNumberList(String s) {
        return null;
    }

    @Override
    public List<Integer> getIntList(String s) {
        return null;
    }

    @Override
    public List<Long> getLongList(String s) {
        return null;
    }

    @Override
    public List<Double> getDoubleList(String s) {
        return null;
    }

    @Override
    public List<String> getStringList(String s) {
        return null;
    }

    @Override
    public <T extends Enum<T>> List<T> getEnumList(Class<T> aClass, String s) {
        return null;
    }

    @Override
    public List<? extends ConfigObject> getObjectList(String s) {
        return null;
    }

    @Override
    public List<? extends Config> getConfigList(String s) {
        return null;
    }

    @Override
    public List<? extends Object> getAnyRefList(String s) {
        return null;
    }

    @Override
    public List<Long> getBytesList(String s) {
        return null;
    }

    @Override
    public List<ConfigMemorySize> getMemorySizeList(String s) {
        return null;
    }

    @Override
    public List<Long> getMillisecondsList(String s) {
        return null;
    }

    @Override
    public List<Long> getNanosecondsList(String s) {
        return null;
    }

    @Override
    public List<Long> getDurationList(String s, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public List<Duration> getDurationList(String s) {
        return null;
    }

    @Override
    public Config withOnlyPath(String s) {
        return null;
    }

    @Override
    public Config withoutPath(String s) {
        return null;
    }

    @Override
    public Config atPath(String s) {
        return null;
    }

    @Override
    public Config atKey(String s) {
        return null;
    }

    @Override
    public Config withValue(String s, ConfigValue configValue) {
        return null;
    }
}
