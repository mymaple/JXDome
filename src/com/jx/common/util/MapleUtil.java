package com.jx.common.util;

import java.beans.BeanInfo;  
import java.beans.IntrospectionException;  
import java.beans.Introspector;  
import java.beans.PropertyDescriptor;  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.util.HashMap;  
import java.util.Map;

import net.sf.json.JSONObject;

import java.util.List;

public class MapleUtil {
	
	/**
	 * 检测List是否为empty
	 * @param List list
	 * @return 不为空则返回true，否则返回false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean emptyList(List list) {
		return list == null || list.size() == 0;
	}
	
	/**
	 * 检测List是否不为empty
	 * @param List list
	 * @return 不为空则返回true，否则返回false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean notEmptyList(List list) {
		return list != null && list.size() > 0;
	}
	
	/** 
     * 将一个 Map 对象转化为一个 JavaBean 
     *  
     * @param type 
     *            要转化的类型 
     * @param map 
     *            包含属性值的 map 
     * @return 转化出来的 JavaBean 对象 
     * @throws IntrospectionException 
     *             如果分析类属性失败 
     * @throws IllegalAccessException 
     *             如果实例化 JavaBean 失败 
     * @throws InstantiationException 
     *             如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 
     *             如果调用属性的 setter 方法失败 
     */  
    public static Object convertMap(Class<?> type, Map<String, ?> map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }  
        }  
        return obj;  
    }  
  
    /** 
     * 将一个 JavaBean 对象转化为一个 Map 
     *  
     * @param bean 
     *            要转化的JavaBean 对象 
     * @return 转化出来的 Map 对象 
     * @throws IntrospectionException 
     *             如果分析类属性失败 
     * @throws IllegalAccessException 
     *             如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 
     *             如果调用属性的 setter 方法失败 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertBean(Object bean) throws IntrospectionException,  
            IllegalAccessException, InvocationTargetException {
        Class<?> type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    } 
    
    /** 
     * 将一个 Map 对象转化为一个 JavaBean 
     *  
     * @param type 
     *            要转化的类型 
     * @param map 
     *            包含属性值的 map 
     * @return 转化出来的 JavaBean 对象 
     * @throws IntrospectionException 
     *             如果分析类属性失败 
     * @throws IllegalAccessException 
     *             如果实例化 JavaBean 失败 
     * @throws InstantiationException 
     *             如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 
     *             如果调用属性的 setter 方法失败 
     */  
    public static Object convertMapUpper(Class<?> type, Map<String, ?> map)
    		throws IntrospectionException, IllegalAccessException,
    		InstantiationException, InvocationTargetException {
    	BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  
    	Object obj = type.newInstance(); // 创建 JavaBean 对象  
    	
    	// 给 JavaBean 对象的属性赋值  
    	PropertyDescriptor[] propertyDescriptors = beanInfo
    			.getPropertyDescriptors();
    	for (int i = 0; i < propertyDescriptors.length; i++) {
    		PropertyDescriptor descriptor = propertyDescriptors[i];
    		String propertyName = descriptor.getName();
    		if (map.containsKey(MapleStringUtil.firstToUpper(propertyName))) {
    			// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
    			Object value = map.get(MapleStringUtil.firstToUpper(propertyName));
    			
    			Object[] args = new Object[1];
    			args[0] = value;
    			
    			descriptor.getWriteMethod().invoke(obj, args);
    		}
    	}
    	return obj;
    }  
    
    /** 
     * 将一个 JavaBean 对象转化为一个 Map 
     *  
     * @param bean 
     *            要转化的JavaBean 对象 
     * @return 转化出来的 Map 对象 
     * @throws IntrospectionException 
     *             如果分析类属性失败 
     * @throws IllegalAccessException 
     *             如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 
     *             如果调用属性的 setter 方法失败 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertBeanUpper(Object bean) throws IntrospectionException,  
    IllegalAccessException, InvocationTargetException {
    	Class<?> type = bean.getClass();
    	Map returnMap = new HashMap();
    	BeanInfo beanInfo = Introspector.getBeanInfo(type);
    	
    	PropertyDescriptor[] propertyDescriptors = beanInfo
    			.getPropertyDescriptors();
    	for (int i = 0; i < propertyDescriptors.length; i++) {
    		PropertyDescriptor descriptor = propertyDescriptors[i];
    		String propertyName = descriptor.getName();
    		if (!propertyName.equals("class")) {
    			Method readMethod = descriptor.getReadMethod(); 
    			Object result = readMethod.invoke(bean, new Object[0]);
    			if (result != null) {
    				returnMap.put(MapleStringUtil.firstToUpper(propertyName), result);
    			} else {
    				returnMap.put(MapleStringUtil.firstToUpper(propertyName), "");
    			}  
    		}  
    	}  
    	return returnMap;
    } 
	
    /** 
     * 将一个 JSONObject 对象转化为一个 JavaBean 
     *  
     * @param type 
     *            要转化的类型 
     * @param json 
     *            包含属性值的 map 
     * @return 转化出来的 JavaBean 对象 
     * @throws IntrospectionException 
     *             如果分析类属性失败 
     * @throws IllegalAccessException 
     *             如果实例化 JavaBean 失败 
     * @throws InstantiationException 
     *             如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 
     *             如果调用属性的 setter 方法失败 
     */  
    public static Object convertJson(Class<?> type, JSONObject json)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (json.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = json.getString(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }  
        }  
        return obj;  
    } 
}
