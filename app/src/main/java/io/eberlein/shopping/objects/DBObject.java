package io.eberlein.shopping.objects;

import android.util.Log;

import java.util.UUID;

import io.eberlein.shopping.interfaces.DBObjectInterface;
import io.eberlein.shopping.Static;
import io.paperdb.Paper;

public class DBObject implements DBObjectInterface {
    private final String book;

    private String uuid;

    public DBObject(String book){
        this.book = book;
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getBook() {
        return book;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static DBObject get(String uuid){
        return Paper.book(Static.BOOK_DBOBJECT).read(uuid);
    }

    @Override
    public void save(){
        Log.d(book, uuid);
        Paper.book(book).write(uuid, this);
    }
}
