package io.eberlein.shopping.objects;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.shopping.Static;
import io.eberlein.shopping.interfaces.DBListObjectInterface;

public class DBListObject<T> extends DBObject implements DBListObjectInterface<T> {
    private List<T> objects;

    public DBListObject(){
        super(Static.BOOK_DBLISTOBJECT);
        objects = new ArrayList<>();
    }

    public DBListObject(String book){
        super(book);
        objects = new ArrayList<>();
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public int add(T object){
        if(!objects.contains(object)) {
            objects.add(object);
            save();
            return size() - 1;
        }
        return -1;
    }

    public void remove(T object){
        objects.remove(object);
        save();
    }

    public T get(int position){
        return objects.get(position);
    }

    public int size(){
        return objects.size();
    }
}
