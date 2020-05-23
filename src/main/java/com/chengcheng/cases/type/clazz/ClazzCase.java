package com.chengcheng.cases.type.clazz;

import com.chengcheng.entity.casesmodel.clazzModel.ClazzBean;
import io.swagger.annotations.Api;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

@Api("Java反射")
public class ClazzCase {

	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		ClazzBean clazzBean = new ClazzBean();
		clazzBean.setProp1("Hello");
		clazzBean.setProp2(100);
		// ----------------------------------------
		Class clazz = ClazzBean.class;  // 获取Class对象方式1
		Class clazz2 = Class.forName("com.chengcheng.entity.casesmodel.clazzModel.ClazzBean");  // 获取Class对象方式2
		// ----------------------------------------
		String clazzName = clazz.getName();  // 获取类名, 含包名
		String clazzSimpleName = clazz.getSimpleName();  // 获取类名, 不含包名
		System.out.println("1. 获取类名, 含包名: " + clazzName);
		System.out.println("2. 获取类名, 不含包名: " + clazzSimpleName);
		// ----------------------------------------
		int mod = clazz.getModifiers();//获取类修饰符
		System.out.println("3. 判断类修饰符是否为Public: " + Modifier.isPublic(mod));  // 判断类修饰符
		System.out.println("4. 判断类修饰符是否为Protected: " + Modifier.isProtected(mod));  // 判断类修饰符
		// ----------------------------------------
		Package p = clazz.getPackage();  //获取包
		System.out.println("5. 获取包: " + p);
		// ----------------------------------------
		Class superClass = clazz.getSuperclass();  // 获取父类
		System.out.println("6. 获取该类的父类: " + superClass);
		// ----------------------------------------
		Class[] interfaces = clazz.getInterfaces();  // 获取实现接口
		System.out.println("7. 获取实现的接口: " + interfaces.length);
		// ----------------------------------------
		Constructor[] cons = clazz.getConstructors();  // 构造方法
		System.out.println("8. 获取构造方法: " + cons.length);
		// ----------------------------------------
		Method[] methods = clazz.getMethods();  // 获取所有方法
		System.out.println("9. 获取方法的个数: " + methods.length);
		for (Method method : methods) {
			System.out.println("10. 获取所有方法: " + method);
		}
		// ----------------------------------------
		Method[] methods2 = clazz.getMethods();  // 获取是所有方法名称
		for (Method method : methods2) {
			System.out.println("11. 取是所有方法名称: " + method.getName());
		}
		// -----------------------------------------
		Method method = clazz.getMethod("getProp1", null);  // 获取指定方法
		System.out.println("12. 获取指定方法: " + method);
		Object methodValue = method.invoke(clazzBean, null);  // 调用方法
		System.out.println("13. 调用方法输出的值: " + methodValue);
		// -----------------------------------------
		Method method3 = clazz.getMethod("setProp3", Double.class);  // 获取指定方法
		Object methodVlaue3 = method3.invoke(clazzBean, 2.0);  // 调用setter方法，该方法没有返回值，所以methodVlaue3为null；此处注意参数2.0 ，不能用null
		System.out.println("14. 调用方法输出的值: " + methodVlaue3);
		// -----------------------------------------
		Field[] fields = clazz.getDeclaredFields();  // 获取变量
		System.out.println("15. 获取变量个数: " + fields.length);
		for (Field field : fields) {
			field.setAccessible(true);
			field.set(clazzBean, null);  // 设置字段的值
			System.out.println("15. field.getAnnotations:" + field.getAnnotations().length + " || 15. field.getName:" + field.getName() + " || 15. field.get:" + field.get(clazzBean));  // 获取实例属性名和值
		}
		// -----------------------------------------
		Annotation[] annos = clazz.getAnnotations();  // 获取类注解
		System.out.println("16. 获取类注解: " + annos.length);


	}

}
