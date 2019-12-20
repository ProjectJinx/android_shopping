package io.eberlein.shopping.objects;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.shopping.Static;
import io.eberlein.shopping.interfaces.DBListObjectInterface;
import io.paperdb.Book;
import io.paperdb.Paper;

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

    @Override
    public void loadFromDB() {
        Book sb = Paper.book(getBook());
        for(String k : sb.getAllKeys()) objects.add(sb.read(k));
    }

    @Override
    public List<T> getObjects() {
        return objects;
    }

    @Override
    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    @Override
    public int add(T object){
        if(!objects.contains(object)) {
            objects.add(object);
            save();
            return size() - 1;
        }
        return -1;
    }

    @Override
    public void remove(T object){
        objects.remove(object);
        save();
    }

    @Override
    public T get(int position){
        return objects.get(position);
    }

    @Override
    public int size(){
        return objects.size();
    }
}
