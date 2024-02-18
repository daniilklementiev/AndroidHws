package com.example.hws;

public class Item {
    private String name;
    private int price;
    private boolean checked;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        this.checked = false;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

