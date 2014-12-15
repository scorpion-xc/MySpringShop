package org.itstep.java.web.shop.model;

public class BasketItem {
    int count = 1;
    float price;
    Good good;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }
    
    public int incCount() {
        return ++count;
    }
    
        public int decCount() {
        return --count;
    }
}
