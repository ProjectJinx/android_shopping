package io.eberlein.shopping.interfaces;

public interface ViewHolderInterface<T> {
    void onBtnDeletedClicked(T object);
    void onBtnEditClicked(T object);
    void onClicked(T object);
    void set(T object);
}
