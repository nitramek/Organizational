package cz.nitramek.organizational.domain.classes;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AttributeValueType implements Identifiable {
    private long id;
    private String name;

    private String methodName;
    private Class<?> convertingClass;


    private Method creatingMethod;

    public AttributeValueType() {
    }

    public AttributeValueType(String methodName, Class<?> convertingClass) throws NoSuchMethodException {
        this.constructMethod(methodName, convertingClass);
        this.methodName = methodName;
        this.convertingClass = convertingClass;
    }

    private void constructMethod(String methodName, Class<?> convertingClass) throws NoSuchMethodException {
        this.creatingMethod = convertingClass.getMethod(methodName, String.class);
    }

    public Object convert(String value) {

        try {
            this.constructMethod(this.methodName, this.convertingClass);
            return this.creatingMethod.invoke(null, value);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttributeValueType)) return false;

        AttributeValueType that = (AttributeValueType) o;

        if (getId() != that.getId()) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getMethodName().equals(that.getMethodName())) return false;
        return getConvertingClass().equals(that.getConvertingClass());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + getMethodName().hashCode();
        result = 31 * result + getConvertingClass().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AttributeValueType{");
        sb.append("convertingClass=").append(convertingClass);
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", methodName='").append(methodName).append('\'');
        sb.append(", creatingMethod=").append(creatingMethod);
        sb.append('}');
        return sb.toString();
    }
}
