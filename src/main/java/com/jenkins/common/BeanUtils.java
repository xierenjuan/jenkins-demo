package com.jenkins.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.pagehelper.Page;
import com.jenkins.exception.custom.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {


	private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 复制一个对象到另一个对象，忽略null值字段
	 * 
	 * 
	 * @param source
	 * @param target
	 * @param ignoreNull
	 */
	public static void copyProperties(Object source, Object target, Boolean ignoreNull) {
		if( target == null ) {
			return ;
		} 
		
		if( source == null ) {
			return ;
		}
		
		if (!ignoreNull) {
			org.springframework.beans.BeanUtils.copyProperties(source, target);
		} else {
			String[] ignoreFiled = getNullField(source);
			org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreFiled);
		}

	}
	
	public static void copyProperties(Object source, Object target) {
		copyProperties(  source ,target ,false );
	}
	
	/**
	 * 创建并复制一个对象
	 * @param source 源对象
	 * @param targetCls 目标 class
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> T copyNew(Object source, Class<T> targetCls) {
		if( source == null  ) {
			return null;
		}
		
		T rt;
		try {
			rt = targetCls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		org.springframework.beans.BeanUtils.copyProperties(source, rt);
		return rt;
	}
	
	/**
	 * 复制信息到outList
	 * 
	 * @param list
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> copyToOutList(List<?> list, Class<T> cls) {
		if( list == null ) {
			return null;
		}
		List<T> rtList = null;
		try {
			rtList = list.getClass().newInstance();
			
			if( list instanceof Page ) {
				BeanUtils.copyPageList( list  , rtList);
			}
			
			for( Object item :  list ) {
				T rtItem =   cls.newInstance();
				BeanUtils.copyProperties( item  , rtItem , false );
				rtList.add( rtItem );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rtList;
	}
	
	/**
	 *复制分页信息
	 *
	 * @param source
	 * @param target
	 */
	private static void copyPageList(Object source, Object target ) {
		String[] ignoreFiled = new String[] {"result"};
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreFiled);
	}

	/**
	 * 得到 值为null 的字段 （只找当前类，没找父类，因为我们的实体暂时没有继承关系）
	 * 
	 * @param source
	 * @return
	 */
	public static String[] getNullField(Object source) {
		List<String> fieldList = new ArrayList<>();
		Field[] fields = source.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.get(source) == null) {
					fieldList.add(field.getName());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}

		return fieldList.toArray(new String[] {});
	}
	
	/**
	 * 得到定义的所有字段（返回数组）
	 * 
	 * @param cls
	 * @return
	 */
	public static String[] getDeclareField(Class<?> cls) {
		return getDeclareFieldAsList(cls).toArray(new String[] {});
	}
	
	
	/**
	 * 得到定义的所有字段(返回list)
	 * 
	 * @param cls
	 * @return
	 */
	public static List<String> getDeclareFieldAsList(Class<?> cls) {
		List<String> fieldList = new ArrayList<>();
		Field[] fields = cls.getDeclaredFields();
		
		for (Field field : fields) {
			fieldList.add( field.getName() );
		}
		
		return fieldList;
	}
	
	
	
	

	/**
	 * 检查 in对象(如果有必填字段name就会抛出异常)
	 * 
	 * @param inObj
	 */
	@SuppressWarnings("unchecked")
	public static void checkInObjectRequired(Object inObj) {
		Class<?> cls = inObj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			
			Class<? extends Annotation> apiParamCls = null;
			try {
				apiParamCls = (Class<? extends Annotation>) Class.forName("io.swagger.annotations.ApiParam");
			} catch (ClassNotFoundException e2) {
				return ;
			}
			
			if (field.isAnnotationPresent( apiParamCls )) {
				Annotation apiParam = field.getAnnotation( apiParamCls );
				
				
				
				Boolean rt = false;
				try {
					Method method = apiParamCls.getMethod("required");
					rt = (Boolean) method.invoke( apiParam );
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				
				if ( rt ) {

					Object fieldValue = null;
					try {
						field.setAccessible(true);
						fieldValue = field.get(inObj);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (fieldValue == null) {
						throw new DataException("必填字段" + field.getName() + "不能为null");
					}

				}
			}

		}

	}

	/**
	 * 检查某个out对象
	 * 
	 * @param cls
	 */
	public static void checkOutObject(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			Class<?> fieldCls = field.getType();
			if (fieldCls.equals(Long.class) || fieldCls.equals(long.class)) {
				if (!field.getName().equals("serialVersionUID")) {
					if (!field.isAnnotationPresent(JsonSerialize.class)) {
						if( logger.isWarnEnabled() ) {
							logger.warn("类" + cls +"字段" +field.getName() +"是Long，写成向前端放回的时候可能丢失精度，你大概可以在字段上面加上：@JsonSerialize(using = LongJsonSerializer.class) 来改善这个问题 "  );
						}
					}
				}
			}

		}

	}
	

	/**
	 * 得到枚举数组的 names
	 * @param enums
	 * @return 枚举的names
	 */
	public static List<String> enumArraysNames( Enum<?>[] enums ) {
		List<String> names = new ArrayList<>();
		for( Enum<?> em : enums ) {
			names.add( em.name() );
		}
		return names;
	}



}
