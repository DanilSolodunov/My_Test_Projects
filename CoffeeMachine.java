package org.example;

public class CoffeeMachine {

    public final int waterMax = 1500;
    public final int coffeeMax = 1000;
    public final int milkMax = 1000;
    public final int cupsMax = 10;

    private int actualCoffee;
    private int actualMilk;
    private int actualWater;
    private int cups;
    private int countOfCappuccino = 0;
    private int countOfLatte = 0;

    static UserMenu userMenu = new UserMenu();

    public CoffeeMachine() {
        this.actualCoffee = coffeeMax;
        this.actualMilk = milkMax;
        this.actualWater = waterMax;
    }

    public int getActualWater() {
        return actualWater;
    }

    public int getActualCoffee() {
        return actualCoffee;
    }

    public int getActualMilk() {
        return actualMilk;
    }

    public int getCups() {
        return cups;
    }

    public int getCountOfCappuccino() {
        return countOfCappuccino;
    }

    public int getCountOfLatte() {
        return countOfLatte;
    }

    public void addActualWater(int actualWater) {
        this.actualWater += actualWater;
    }

    public void addActualCoffee(int actualCoffee) {
        this.actualCoffee += actualCoffee;
    }

    public void addActualMilk(int actualMilk) {
        this.actualMilk += actualMilk;
    }

    public void start() {
        userMenu.startMenu();
    }

    public void makeCoffee(Reception reception, int countOfCups) {
        for (int i = 0; i < countOfCups; i++) {
            if (ingredientsScan(reception, countOfCups)) {
                System.out.println("не хватает ингридиентов для приготовления!");
            }
            ingredientsScan(reception, countOfCups);
            if (cups >= cupsMax) {
                userMenu.cupsClears();
            }
            actualMilk -= reception.getMilk();
            actualCoffee -= reception.getCoffee();
            actualWater -= reception.getWater();
            cups ++;
            if (reception == Reception.valueOf(Reception.CAPPUCCINO.name())) {
                countOfCappuccino ++;
            }
            else {
                countOfLatte ++;
            }
        }
    }

    public boolean ingredientsScan(Reception reception, int countOfCups) {
        if (actualWater > reception.getWater() * countOfCups &&
                actualCoffee > reception.getCoffee() * countOfCups &&
                actualMilk > reception.getMilk() * countOfCups) {
            return true;
        }
        return false;
    }

    public void cupsClear() {
        cups = 0;
    }
}
