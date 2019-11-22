package com.zerg.tiamat.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerg.tiamat.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : xuyang
 * @date : 2019-09-04 17:38
 */

@Slf4j
public class JsonUtils {
    private JsonUtils() {
    }

    private static final FieldMapFilter DEFAULT_FILTER = (key, value) -> value;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object object) {
        String json = null;
        try {
            json = OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("toJson error={}", e);
        }
        return json;
    }


    public static <T> T toBean(String str, Class<T> clazz) {
        if (str == null) {
            return null;
        }
        if (clazz == String.class) {
            return clazz.cast(str);
        }

        T bean = null;
        try {
            bean = OBJECT_MAPPER.readValue(str, clazz);
        } catch (IOException e) {
            log.error("deserialize by Class failed, str is" + str);
            throw new BizException("deserialize failed");
        }

        if (Objects.isNull(bean)) {
            log.error("bean is null, str is " + str);
            throw new BizException("bean is null");
        }
        return bean;

    }


    public static <T> T toBean(String str, TypeReference<T> type) {
        T bean = null;
        try {
            bean = OBJECT_MAPPER.readValue(str, type);
        } catch (IOException e) {
            log.error("deserialize by TypeReference failed, str is " + str);
            throw new BizException("deserialize failed");
        }

        if (Objects.isNull(bean)) {
            log.error("bean is null, str is " + str);
            throw new BizException("bean is null");
        }
        return bean;
    }

    public static Map<String, Object> object2Map(final Object obj) {
        return object2Map(obj, true, DEFAULT_FILTER);
    }

    public static Map<String, Object> object2MapWithoutNull(final Object obj) {
        return object2Map(obj, false, DEFAULT_FILTER);
    }

    /**
     * @param obj       需要转换的对象
     * @param allowNull 是否允许value为空值
     * @param filter    字段映射时可添加过滤
     * @return
     */
    public static Map<String, Object> object2Map(final Object obj, boolean allowNull, FieldMapFilter filter) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class clazz = obj.getClass();
            while (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (allowNull || Objects.nonNull(field.get(obj))) {
                        Object value = filter == null ? field.get(obj) : filter.filter(field.getName(), field.get(obj));
                        map.put(field.getName(), value);
                    }
                }

                clazz = clazz.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            log.error("Object2Map occurs error! the object is {}", obj);
            throw new IllegalArgumentException("Object2Map occurs error!");
        }
        return map;
    }

    @Deprecated
    public static Map<String, Object> object2Map(final Object obj, boolean isNull, Filter filter) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class clazz = obj.getClass();
            while (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (isNull || Objects.nonNull(field.get(obj))) {
                        Object value = filter == null ? field.get(obj) : filter.filter(field.get(obj));
                        map.put(field.getName(), value);
                    }
                }

                clazz = clazz.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            log.error("Object2Map occurs error! the object is {}", obj);
            throw new IllegalArgumentException("Object2Map occurs error!");
        }
        return map;
    }


    public static <T> T map2Object(Map<String, Object> map, Class<T> clz) {
        return map2Object(map, clz, false, DEFAULT_FILTER);
    }


    public static <T> T map2ObjectWithLine2Hump(Map<String, Object> map, Class<T> clz) {
        return map2Object(map, clz, true, DEFAULT_FILTER);
    }

    /**
     * @param map       需要映射的map
     * @param clz       类对象
     * @param line2Hump 是否需要将下划线转为驼峰
     * @param filter    字段映射时可添加过滤
     * @param <T>
     * @return
     */
    public static <T> T map2Object(Map<String, Object> map, Class<T> clz, boolean line2Hump, FieldMapFilter filter) {
        T obj = null;
        try {
            obj = clz.newInstance();
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);

                String key = line2Hump ? Strings.humpToLine(field.getName()) : field.getName();
                Object value = filter == null ? map.get(key) : filter.filter(key, map.get(key));
                field.set(obj, value);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Map2Object occurs error! the map is " + toJson(map));
            throw new IllegalArgumentException("Map2Object occurs error!");
        }
        return obj;
    }

}
