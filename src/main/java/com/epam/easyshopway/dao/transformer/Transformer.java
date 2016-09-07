package com.epam.easyshopway.dao.transformer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Transformer<T> {

	private Class<?> cls;
	private T t;

	public Transformer(Class<?> cls) {
		super();
		this.cls = cls;
	}

	@SuppressWarnings("unchecked")
	public List<T> fromRStoCollection(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		List<T> list = new ArrayList<>();
		while (rs.next()) {
			t = (T) cls.newInstance();
			for (Field f : cls.getDeclaredFields()) {
				if (f.isAnnotationPresent(Column.class)) {
					try {
						if (f.getType() == Boolean.class) {
							f.setAccessible(true);
							f.set(t, rs.getObject(f.getAnnotation(Column.class).value()));
						} else if (f.getType() == Time.class || f.getType() == Date.class) {
							cls.getMethod("set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1), String.class)
									.invoke(t, rs.getString(f.getAnnotation(Column.class).value()));
						} else {
							cls.getMethod("set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1), f.getType())
									.invoke(t, f.getType().getConstructor(String.class)
											.newInstance(rs.getString(f.getAnnotation(Column.class).value())));
						}
					} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
//						e.printStackTrace();
					}
				}
			}
			list.add(t);
		}
		return list;
	}
}