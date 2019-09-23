package com.voucher.manage.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lz
 * @description 关于map的一些简化操作
 * @date 2019/5/10
 */
public final class MapUtils {

    public static final <K, V> Object getObject(K key, Map<K, V> map) {
        if (ObjectUtils.isEmpty(map)) {
            return null;
        }
        return map.get(key);
    }

    public static final <K, V> V get(K key, Map<K, V> map) {
        Object object = getObject(key, map);
        V v = object == null ? null : (V) object;
        return v;
    }

    public static final <K, V> String getString(K key, Map<K, V> map) {
        Object object = getObject(key, map);
        V v = object == null ? null : (V) object;
        return v == null ? null : v.toString();
    }

    public static final <K, V> Integer getInteger(K key, Map<K, V> map) {
        return getString(key, map) == null ? null : Integer.valueOf(getString(key, map));
    }

    public static final <K, V> Long getLong(K key, Map<K, V> map) {
        return getString(key, map) == null ? null : Long.valueOf(getString(key, map));
    }

    public static final <K, V> Float getFloat(K key, Map<K, V> map) {
        return getString(key, map) == null ? null : Float.valueOf(getString(key, map));
    }

    public static final <K, V> Boolean getBoolean(K key, Map<K, V> map) {
        return getString(key, map) == null ? null : Boolean.valueOf(getString(key, map));
    }

    public static final <K, V> List<String> getStrList(K key, Map<K, V> map) {
        return getObject(key, map) == null ? null : (List<String>) getObject(key, map);
    }


    public static Double getDouble(String key, Map<String, Object> map) {
        return getString(key, map) == null ? null :Double.valueOf(getString(key, map));
    }

    public static Map<String, Object> getStrMap(String key, Map<String, Object> map) {
        Object obj = get(key, map);
        if (obj != null && obj instanceof Map) {
            return (Map<String, Object>) obj;
        }
        return new HashMap<>(16);
    }
}
