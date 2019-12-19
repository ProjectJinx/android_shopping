package io.eberlein.shopping.object;

import io.eberlein.shopping.Static;
import io.paperdb.Paper;

public class Shop {
    private String name;
    private String description;


    public Shop(){
        this.name = "";
        this.description = "";
    }

    public Shop(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void save(){
        Paper.book(Static.BOOK_SHOP).write(name, this); // todo more unique key
    }
}
