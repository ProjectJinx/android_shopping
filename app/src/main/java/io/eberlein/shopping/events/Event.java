package io.eberlein.shopping.events;

public class Event<T> {
    private T object;

    public Event(){}

    public Event(T object){
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
