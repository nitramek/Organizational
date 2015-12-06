package cz.nitramek.organizational.domain.service;


import java.io.Serializable;

public interface Service<T> extends Serializable {

    T create();

    T add(T type);

    T update(T type);

    T get(long id);
}
