package com.test.service.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

	/**
	 * 
	 * @param object
	 * @param methodName
	 * @param returnType
	 * @param arguments
	 * @return
	 * @throws Exception
	 */
	public static <O> O callPrivateMethod(Object object, String methodName, Class<O> returnType, Object... arguments)
			throws Exception {
		return invoke(object, methodName, arguments);

	}

	/**
	 * 
	 * @param classType
	 * @param methodName
	 * @param returnType
	 * @param arguments
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <O, I> O callPrivateMethod(Class<I> classType, String methodName, Class<O> returnType,
			Object... arguments) throws Exception {
		return invoke((I) Class.forName(classType.getName()).newInstance(), methodName, arguments);

	}

	@SuppressWarnings("unchecked")
	private static <O, I> O invoke(I object, String methodName, Object... arguments)
			throws ClassNotFoundException, IllegalAccessException, InvocationTargetException {
		Method[] declaredMethods = object.getClass().getDeclaredMethods();
		O response = null;
		for (Method method : declaredMethods) {
			if (method.getName().equalsIgnoreCase(methodName)) {
				method.setAccessible(true);
				return (O) method.invoke(object, arguments);
			}
		}
		return response;
	}
}
