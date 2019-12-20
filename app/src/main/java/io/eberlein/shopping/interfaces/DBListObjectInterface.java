package io.eberlein.shopping.interfaces;

import java.util.List;


public interface DBListObjectInterface<T> {
    List<T> getObjects();
    int add(T object);
    void remove(T object);
    T get(int position);
    int size();
}
