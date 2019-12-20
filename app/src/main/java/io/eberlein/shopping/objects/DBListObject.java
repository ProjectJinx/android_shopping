package io.eberlein.shopping.objects;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.shopping.Static;
import io.eberlein.shopping.interfaces.DBListObjectInterface;
import io.paperdb.Book;
import io.paperdb.Paper;

public class DBListObject<T extends DBObject> extends DBObject implements DBListObjectInterface<T> {
    private List<String> stringReferences;

    public DBListObject(){
        super(Static.BOOK_DBLISTOBJECT);
        stringReferences = new ArrayList<>();
    }

    public DBListObject(String book){
        super(book);
        stringReferences = new ArrayList<>();
    }

    @Override
    public List<T> getObjects() {
        Book b = Paper.book(getBook());
        List<T> r = new ArrayList<>();
        for(String ref : stringReferences) r.add(b.read(ref));
        return r;
    }

    @Override
    public int add(T object){
        if(!stringReferences.contains(object.getUuid())) {
            stringReferences.add(object.getUuid());
            return size() - 1;
        }
        return -1;
    }

    @Override
    public void remove(T object){
        stringReferences.remove(object.getUuid());
        Paper.book(getBook()).delete(object.getUuid());
    }

    @Override
    public T get(int position){
        String ref = stringReferences.get(position);
        Log.d(getBook(), ref);
        T r = Paper.book(getBook()).read(ref);
        Log.d(getBook(), r.getUuid());
        return r;
    }

    @Override
    public int size(){
        return stringReferences.size();
    }
}
