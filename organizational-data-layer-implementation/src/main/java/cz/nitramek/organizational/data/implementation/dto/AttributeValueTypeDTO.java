package cz.nitramek.organizational.data.implementation.dto;


import com.google.gson.annotations.Expose;
import cz.nitramek.organizational.domain.interafaces.Identifiable;

import java.lang.reflect.Method;

public class AttributeValueTypeDTO implements Identifiable {

    @Expose
    private long id;

    @Expose
    private String name;

    @Expose
    private String methodName;


    private transient Class<?> convertingClass;

    @Expose
    private String convertingClassName;


    private transient Method creatingMethod;

    public AttributeValueTypeDTO() {
    }

    public AttributeValueTypeDTO(String methodName, Class<?> convertingClass) throws NoSuchMethodException {
        this.constructMethod(methodName, convertingClass);
        this.methodName = methodName;
        setConvertingClass(convertingClass);

    }

    private void constructMethod(String methodName, Class<?> convertingClass) throws NoSuchMethodException {
        this.creatingMethod = convertingClass.getMethod(methodName, String.class);
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
        this.convertingClassName = convertingClass.getName();
    }

    public String getConvertingClassName() {
        return convertingClassName;
    }

    public void setConvertingClassName(String convertingClassName) {
        this.convertingClassName = convertingClassName;
        try {
            setConvertingClass(Class.forName(convertingClassName));
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
