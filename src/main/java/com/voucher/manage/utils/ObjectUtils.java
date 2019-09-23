package com.voucher.manage.utils;

import cn.hutool.core.util.ObjectUtil;

import java.lang.reflect.Array;
import java.util.*;

public class ObjectUtils extends org.springframework.util.ObjectUtils {


    /**
     * @Author lz
     * @Description: 只要有一个为enmpty
     * @param: [objs]
     * @return: {boolean}
     * @Date: 2019/5/13 11:25
     **/
    public static boolean isEmpty(Object... objs) {
        if (objs == null) {
            return true;
        }
        boolean isEmpty = false;
        if (objs.getClass().isArray()) {
            isEmpty = Array.getLength(objs) == 0;
        }
        for (Object o : objs) {
            if (o == null) {
                isEmpty = true;
            } else if (o instanceof String) {
                String s = o.toString();
                isEmpty = "null".equals(s) || "".equals(s);
            } else if (o.getClass().isArray()) {
                isEmpty = Array.getLength(o) == 0;
            } else if (o instanceof CharSequence) {
                isEmpty = ((CharSequence) o).length() == 0;
            } else if (o instanceof Collection) {
                isEmpty = ((Collection) o).isEmpty();
            } else if (o instanceof Map) {
                isEmpty = ((Map) o).isEmpty();
            }
            if (isEmpty) {
                return isEmpty;
            }
        }
        return isEmpty;
    }

    public static boolean isNotEmpty(Object... objs) {
        return !isEmpty(objs);
    }

    public static <T> byte[] serialize(T obj) {
        return ObjectUtil.serialize(obj);
    }

    public static <T> T unserialize(byte[] bytes) {
        return ObjectUtil.unserialize(bytes);
    }

    public static void main(String[] args) {
        System.out.println(isEmpty(null));
    }
}
