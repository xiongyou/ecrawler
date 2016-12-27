package edu.cqu.fly.crawler.util;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

public class QueryUtils {

	public static <T> TypedQueryBuilder<T> getTQBuilder(T searchObj, Map<String, String[]> parameterMap) {
		@SuppressWarnings("unchecked")
		TypedQueryBuilder<T> builder = new TypedQueryBuilder<T>((Class<T>) searchObj.getClass(), "e");
		doGetTQBuilder(builder, searchObj, parameterMap, "e");

		return builder;
	}

	private static <T> void doGetTQBuilder(TypedQueryBuilder<T> builder, Object searchObj, Map<String, String[]> parameterMap, String alias) {
		PropertyDescriptor properties[] = PropertyUtils.getPropertyDescriptors(searchObj);
		String aliasName, name, type;
		for (int i = 0; i < properties.length; i++) {

			name = properties[i].getName();
			type = properties[i].getPropertyType().toString();
			aliasName = alias + "." + name;

			try {
				if (judgedIsUselessField(name) || !PropertyUtils.isReadable(searchObj, name)) {
					continue;
				}
				// 添加 判断是否有区间值
				String beginValue = null;
				String endValue = null;
				if (parameterMap != null && parameterMap.containsKey(name + BEGIN)) {
					beginValue = parameterMap.get(name + BEGIN)[0].trim();
				}
				if (parameterMap != null && parameterMap.containsKey(name + END)) {
					endValue = parameterMap.get(name + END)[0].trim();
				}

				Object value = PropertyUtils.getSimpleProperty(searchObj, name);
				// 根据类型分类处理
				if ("class java.lang.String".equals(type)) {

					final String strValue = (String) value;
					if (StringUtils.isNotEmpty(strValue)) {
						// [1].In 多个条件查询{逗号隔开参数}
						if (strValue.indexOf(",") >= 0) {
							// 页面输入查询条件，情况（取消字段的默认条件）
							if (strValue.indexOf(" ") >= 0) {
								final String val = strValue.substring(strValue.indexOf(" "));
								builder.addRestriction(aliasName, "=", val);
							} else {
								final String[] vs = strValue.split(",");
								builder.addRestriction(aliasName, "in", vs);
							}
						}
						// [2].模糊查询{带有* 星号的参数}
						else if (strValue.indexOf("*") >= 0) {
							builder.addRestriction(aliasName, "like", strValue.replace("*", "%"));
						}
						// [3].不匹配查询{等于！叹号}
						// (1).不为空字符串
						else if (strValue.equals("!")) {
							builder.addRestriction(aliasName, "is NOT NULL", null);
						}
						// (2).不为NULL
						else if (strValue.toUpperCase().equals("!NULL")) {
							builder.addRestriction(aliasName, "is NOT NULL", null);
						}
						// (3).正常不匹配
						else if (strValue.indexOf("!") >= 0) {
							builder.addRestriction(aliasName, "!=", strValue.replace("!", ""));
						}
						// [4].全匹配查询{没有特殊符号的参数}
						else {
							builder.addRestriction(aliasName, "=", strValue);
						}
					}

				} else if (type.contains("class java.lang") || type.contains("class java.math")) {

					if (value != null) {
						builder.addRestriction(aliasName, "=", value);
					} else if (StringUtils.isNotEmpty(beginValue)) {
						builder.addRestriction(aliasName, ">=", beginValue);
					} else if (StringUtils.isNotEmpty(endValue)) {
						builder.addRestriction(aliasName, "<=", beginValue);
					}

				} else if ("class java.util.Date".equals(type)) {
					if (StringUtils.isNotEmpty(beginValue)) {
						if (beginValue.length() == 19) {
							builder.addRestriction(aliasName, ">=", DATE_TIME.parse(beginValue));
						} else if (beginValue.length() == 10) {
							builder.addRestriction(aliasName, ">=", DATE.parse(beginValue));
						}
					}
					if (StringUtils.isNotEmpty(endValue)) {
						if (endValue.length() == 19) {
							builder.addRestriction(aliasName, "<=", DATE_TIME.parse(endValue));
						} else if (endValue.length() == 10) {
							// 对于"yyyy-MM-dd"格式日期，因时间默认为0，故此添加" 23:23:59"并使用time解析，以方便查询日期时间数据
							builder.addRestriction(aliasName, "<=", DATE_TIME.parse(endValue + " 23:23:59"));
						}
					}
					if (value != null) {
						builder.addRestriction(aliasName, "=", value);
					}
				} else if (isJavaClass(properties[i].getPropertyType())) {
					if (value != null && itIsNotAllEmpty(value)) {
						// 如果是实体类,创建别名,继续创建查询条件
						doGetTQBuilder(builder, value, parameterMap, aliasName);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static final String END = "_end";
	private static final String BEGIN = "_begin";

	private static boolean judgedIsUselessField(String name) {
		return "class".equals(name) || "ids".equals(name) || "page".equals(name) || "rows".equals(name) || "sort".equals(name)
				|| "order".equals(name);
	}

	private static boolean isJavaClass(Class<?> clazz) {
		boolean isBaseClass = false;
		if (clazz.isArray()) {
			isBaseClass = false;
		} else if (clazz.isPrimitive() || clazz.getPackage() == null || clazz.getPackage().getName().equals("java.lang")
				|| clazz.getPackage().getName().equals("java.math") || clazz.getPackage().getName().equals("java.util")) {
			isBaseClass = true;
		}
		return isBaseClass;
	}

	private static final SimpleDateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");

	private static boolean itIsNotAllEmpty(Object param) {
		boolean isNotEmpty = false;
		try {
			PropertyDescriptor properties[] = PropertyUtils.getPropertyDescriptors(param);
			String name;
			for (int i = 0; i < properties.length; i++) {
				name = properties[i].getName();
				if ("class".equals(name) || !PropertyUtils.isReadable(param, name)) {
					continue;
				}
				if (Map.class.isAssignableFrom(properties[i].getPropertyType())) {
					Map<?, ?> map = (Map<?, ?>) PropertyUtils.getSimpleProperty(param, name);
					if (map != null && map.size() > 0) {
						isNotEmpty = true;
						break;
					}
				} else if (Collection.class.isAssignableFrom(properties[i].getPropertyType())) {
					Collection<?> c = (Collection<?>) PropertyUtils.getSimpleProperty(param, name);
					if (c != null && c.size() > 0) {
						isNotEmpty = true;
						break;
					}
				} else if (PropertyUtils.getSimpleProperty(param, name) != null) {
					isNotEmpty = true;
					break;
				}
			}
		} catch (Exception e) {

		}
		return isNotEmpty;
	}
}
