package com.app.rest.jaxrs.paramconverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class MyDateParamConverterProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.getName().equals(MyDate.class.getName())){
			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					Date d = new Date();
					MyDate date = new MyDate(d.getDate(), d.getMonth(), d.getYear());
					if (value.equals("today")) {
					}
					if (value.equals("tomorrow")) {
						date.setDate(d.getDate()+1);
					}
					if (value.equals("nextweek")) {
						date.setDate(d.getDate()+7);
					}
					if (value.equals("nextmonth")) {
						date.setDate(d.getMonth()+1);
					}
					if (value.equals("nextyear")) {
						date.setDate(d.getYear()+1);
					}
					return rawType.cast(date);
				}

				@Override
				public String toString(T value) {
					return value.toString();
				}
				
			};
		}
		return null;
	}

}
