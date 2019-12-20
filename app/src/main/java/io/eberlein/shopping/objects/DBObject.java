package io.eberlein.shopping.objects;

import android.util.Log;

import java.util.Date;
import java.util.UUID;

import io.eberlein.shopping.interfaces.DBObjectInterface;
import io.eberlein.shopping.Static;
import io.paperdb.Paper;

public class DBObject implements DBObjectInterface {
    private final String book;

    private String uuid;
    private Date created;

    public DBObject(String book){
        this.book = book;
        this.uuid = UUID.randomUUID().toString();
        this.created = new Date();
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getBook() {
        return book;
    }

    public Date getCreated() {
        return created;
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

    @Override
    public void delete() {
        Paper.book(book).delete(uuid);
    }
}
