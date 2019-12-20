package io.eberlein.shopping.objects;

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
            save();
            return size() - 1;
        }
        return -1;
    }

    @Override
    public void remove(T object){
        stringReferences.remove(object.getUuid());
        save();
    }

    @Override
    public T get(int position){
        return Paper.book(getBook()).read(stringReferences.get(position));
    }

    @Override
    public int size(){
        return stringReferences.size();
    }
}
