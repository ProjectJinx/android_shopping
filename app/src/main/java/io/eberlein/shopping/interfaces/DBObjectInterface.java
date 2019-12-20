package io.eberlein.shopping.interfaces;

public interface DBObjectInterface<T> {
    void save();
    void delete();
    void setUuid(String uuid);
    String getUuid();
    String getBook();
}
