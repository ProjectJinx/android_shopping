package io.eberlein.shopping.interfaces;

public interface ViewHolderInterface<T> {
    void onBtnDeletedClicked(T object);
    void onBtnEditClicked(T object);
    void onClicked(T object);
    void onSwipeLeft(T object);
    void onSwipeRight(T object);
    void onSwipeUp(T object);
    void onSwipeDown(T object);
    void set(T object);
}
