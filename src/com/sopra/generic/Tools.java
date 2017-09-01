package com.sopra.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tools {
	public static void copy(Object source, Object destination) throws IllegalArgumentException, IllegalAccessException{
		List<Field> myFields = Tools.getAllFields(source.getClass());
		
		for (Field field : myFields) {
			if ((field.getModifiers() & Modifier.FINAL) != Modifier.FINAL) {
				field.setAccessible(true);
				Object myValue = field.get(source);
				if (myValue != null) {
					field.set(destination, myValue);
				}
			}
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public static List<Field> getAllFields(Class cls) {
		List<Field> myFields = new ArrayList<Field>(Arrays.asList(cls.getDeclaredFields()));
		Class mySuperclass = cls.getSuperclass();
		
		if (mySuperclass != null) {
			myFields.addAll(Tools.getAllFields(mySuperclass));
		}
		
		return myFields;
	}
}
