package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AttributeValueTypeDTO implements Identifiable {
    private long id;
    private String name;

    private String methodName;
    private Class<?> convertingClass;


    private Method creatingMethod;

    public AttributeValueTypeDTO() {
    }

    public AttributeValueTypeDTO(String methodName, Class<?> convertingClass) throws NoSuchMethodException {
        this.constructMethod(methodName, convertingClass);
        this.methodName = methodName;
        this.convertingClass = convertingClass;
    }

    private void constructMethod(String methodName, Class<?> convertingClass) throws NoSuchMethodException {
        this.creatingMethod = convertingClass.getMethod(methodName, String.class);
    }

    public Object convert(String value) {
        try {
            return this.creatingMethod.invoke(value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) throws NoSuchMethodException {
        this.methodName = methodName;
        this.constructMethod(this.methodName, this.convertingClass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getConvertingClass() {
        return convertingClass;
    }

    public void setConvertingClass(Class<?> convertingClass) throws NoSuchMethodException {
        this.convertingClass = convertingClass;
        this.constructMethod(this.methodName, this.convertingClass);
    }


}
