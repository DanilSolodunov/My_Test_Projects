package org.example;

public enum Reception {

    CAPPUCCINO (100, 10, 15),
    LATTE (80, 20, 30);

    private int water;
    private int coffee;
    private int milk;

    Reception (int water, int coffee, int milk) {
        this.water = water;
        this.coffee = coffee;
        this.milk = milk;
        System.out.println();
    }

    public int getWater() {
        return water;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getMilk() {
        return milk;
    }
}




