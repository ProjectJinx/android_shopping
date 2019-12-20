package io.eberlein.shopping.interfaces;

import java.util.List;


public interface DBListObjectInterface<T> {
    List<T> getObjects();
    void setObjects(List<T> objects);
    int add(T object);
    void remove(T object);
    T get(int position);
    int size();
    void loadFromDB();
}
