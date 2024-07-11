package org.example;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class UserMenu {

    private final CoffeeMachine coffeeMachine = new CoffeeMachine();
    private final Scanner scanner = new Scanner(System.in);
    private int userChoice;
    private Map<String, HashMap<Reception, Integer>> coffeeProfile = new HashMap<>();
    private List<String> logger = new ArrayList<>();

    private int checkToInt() {
        while (true) {
            try {
                userChoice = scanner.nextInt();
                if (userChoice >= 0) {
                    return userChoice;
                } else {
                    System.out.println("введены не верные данные! \nповторите попытку!");
                    logger.add("введены не верные данные!");
                }
            } catch (Exception exception) {
                System.out.println("введены не верные данные! \nповторите попытку!");
                logger.add("введены не верные данные!");
                scanner.next();
            }
        }
    }

    public void startMenu() {
        System.out.println("1. включить кофемашинку");
        do {
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    mainMenu();
                    logger.add("кофемашина включина");
                    break;
                }
            }
            System.out.println("повторите попытку");
            logger.add("кофемашина не включилась");
        } while (true);
    }

    public void mainMenu() {
        do {
            System.out.println("выберите действия:" +
                    "\n1. приготовить кофе" +
                    "\n2. выбрать/создать профиль" +
                    "\n3. проверить/добавить ингредиенты" +
                    "\n4. вывести рецепт кофе" +
                    "\n5. техническое обслуживание" +
                    "\n6. вывести количество приготовленных напитков" +
                    "\n7. вывести логи");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    if (coffeeMachine.getCups() > coffeeMachine.cupsMax) {
                        cupsClears();
                        logger.add("попытка приготовить кофе, кофемашина грязная!");
                    }
                    choiceCoffeeMenu();
                    break;
                }
                case 2: {
                    userProfile();
                    break;
                }
                case 3: {
                    checkIngredients();
                    break;
                }
                case 4: {
                    recipesCoffee();
                    break;
                }
                case 5: {
                    menuClear();
                    break;
                }
                case 6: {
                    System.out.println("каппучино приготовлено: " + coffeeMachine.getCountOfCappuccino()
                            + ", латте приготовлено: " + coffeeMachine.getCountOfLatte());
                    break;
                }
                case 7: {
                    for (int i = 0; i < logger.size(); i++) {
                        System.out.println("log " + i + " " + logger.get(i));
                    }
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                    break;
                }
            }
        } while (true);
    }

    private void menuClear() {
        while (true) {
            System.out.println("1. произвести очистку" +
                    "\n2. степень загрязнения" +
                    "\n0. назад");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    cupsClears();
                    break;
                }
                case 2: {
                    cleanMenu();
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                    break;
                }
            }
            break;
        }
    }

    private void cleanMenu() {
        System.out.println("кофемашина загрязняна на: " + coffeeMachine.getCups()
                + "/" + coffeeMachine.cupsMax +
                "\n 1. очистить кофемашину" +
                "\n0. назад");
        while (true) {
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    cupsClears();
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                    break;
                }
            }
            mainMenu();
        }
    }

    public void cupsClears() {
        if (coffeeMachine.getCups() >= coffeeMachine.cupsMax) {
            System.out.println("кофемашина грязная! \n1. произвести очистку \n0. назад");
            logger.add("ошибка! кофемашина грязная, необходима очистка");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    coffeeMachine.cupsClear();
                    System.out.println("очистка успешно завершена!");
                    logger.add("очистка успешно завершина");
                    mainMenu();
                    break;
                }
                case 0: {
                    mainMenu();
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                    break;
                }
            }
        } else if (coffeeMachine.getCups() == 0) {
            System.out.println("машина чистая и не нуждается в чистке!");
            logger.add("ошибка! кофемашина чистая");
            mainMenu();
        }
    }

    private void recipesCoffee() {
        do {
            System.out.println("1. рецепт каппучино" +
                    "\n2. рецепт латте" +
                    "\n0. назад");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    recipesCappuccino();
                    break;
                }
                case 2: {
                    recipesLatte();
                    break;
                }
                case 0: {
                    mainMenu();
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                    break;
                }
            }
        } while (true);
    }

    private void recipesLatte() {
        System.out.println("латте:" +
                "\nмолочный, слегка теплый напиток. используется " + Reception.LATTE.getWater()
                + " мл воды, " + Reception.LATTE.getCoffee() + " г кофе и "
                + Reception.LATTE.getMilk() + " мл молока" +
                "\nдля приготовления кофе варится при температуре 110 градусов" +
                "\nдалее добавляется 80 мл воды, размешивая кофе" +
                "\nзатем добавляется холодное молоко, но не смешивается" +
                "\nтаким образом создается плавный переход от горячего и терпкого кофе к " +
                "\nслегка теплому кофейно-молочному вкусу");
    }

    private void recipesCappuccino() {
        System.out.println("капучино: \n" +
                "молочный напиток. используется " + Reception.CAPPUCCINO.getWater() + " мл воды, "
                + Reception.CAPPUCCINO.getCoffee() + " г кофейных зерен и "
                + Reception.CAPPUCCINO.getMilk() + " мл молока \n" +
                "первым делом варится при температуре 110 градусов" +
                "\nдалее добавляется 100 мл горячей воды, постепенно размешивая кофе \n" +
                "последним этапом добавление горячего взбитого молока для образования кофейной пены");
    }

    private void userProfile() {
        do {
            System.out.println("1. выбрать профиль \n2. добавить новый \n0. назад");
            logger.add("открыто меню профиля");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    choosingProfile();
                    break;
                }
                case 2: {
                    createUserProfile();
                    break;
                }
                case 0: {
                    mainMenu();
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                    break;
                }
            }
        } while (true);
    }

    private void choosingProfile() {
        System.out.println("введите имя пользователя");
        String userName = scanner.next();
        logger.add("открыта карточка выбора профиля приготовления кофе");
        if (coffeeProfile.containsKey(userName)) {
            coffeeMachine.makeCoffee(Reception.CAPPUCCINO,
                    coffeeProfile.get(userName).get(Reception.CAPPUCCINO));
            System.out.println("ваш " + coffeeProfile.get(userName).get(Reception.CAPPUCCINO)
                    + " " + Reception.CAPPUCCINO.name() + " готов!");
            logger.add("выбран профиль приготовления каппучино");
            coffeeMachine.makeCoffee(Reception.LATTE,
                    coffeeProfile.get(userName).get(Reception.LATTE));
            System.out.println("ваш " + coffeeProfile.get(userName).get(Reception.LATTE)
                    + " " + Reception.LATTE.name() + " готов!");
            logger.add("выбран профиль приготовления латте");
        } else {
            System.out.println("Аккаунт не найден, пожалуйста создайте аккаунт!");
            logger.add("ошибка поиска профиля приготовления кофе");
        }
    }

    private void createUserProfile() {
        HashMap<Reception, Integer> userCoffee = new HashMap<>();
        System.out.println("введите количество кофе \nсколько капучино?");
        logger.add("открыта карточка создания профиля приготовления кофе");
        userCoffee.put(Reception.CAPPUCCINO, checkToInt());
        logger.add("количество каппучино добавлено в профиль");
        System.out.println("сколько латте?");
        int choiceCheck = checkToInt();
        if (userChoice + choiceCheck > 10) {
            System.out.println("ошибка! нельзя приготовить больше 10 кофе за раз!");
        } else {
            userCoffee.put(Reception.LATTE, choiceCheck);
            logger.add("количество латте добавлено в профиль");
            System.out.println("введите имя:");
            String name = scanner.next();
            coffeeProfile.put(name, userCoffee);
            System.out.println("профиль успешно создан!");
            logger.add("профиль успешно создан");
        }
    }

    public void choiceCoffeeMenu() {
        do {
            System.out.println("выберите напиток: " +
                    "\n1. каппучино" +
                    "\n2. латте" +
                    "\n0. назад");
            logger.add("открыто меню выбора кофе");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    choiceCountOfCoffeeMenu(Reception.CAPPUCCINO);
                    logger.add("капучино приготовился");
                    break;
                }
                case 2: {
                    choiceCountOfCoffeeMenu(Reception.LATTE);
                    logger.add("латте приготовился");
                    break;
                }
                case 0: {
                    mainMenu();
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                    break;
                }
            }
        } while (true);
    }

    public void choiceCountOfCoffeeMenu(Reception reception) {
        do {
            System.out.println("укажите количество кофе" +
                    "\n1. приготовить 1 кофе" +
                    "\n2. приготовить 3 кофе" +
                    "\n3. введите желаемое количество" +
                    "\n0. назад");
            logger.add("открыто меню выбора количества напитков");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    coffeeCups1Menu(reception);
                    break;
                }
                case 2: {
                    coffeeCups3Menu(reception);
                    break;
                }
                case 3: {
                    coffeeCupsAddMenu(reception);
                    break;
                }
                case 0: {
                    choiceCoffeeMenu();
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                }
            }
        } while (true);
    }

    private void coffeeCupsAddMenu(Reception reception) {
        System.out.println("укажите количество");
        userChoice = checkToInt();
        if (!coffeeMachine.ingredientsScan(reception, userChoice)) {
            System.out.println("не хватает ингридиентов для приготовления!");
            logger.add("ошибка готовки кофе! не хватает ингредиентов для: " + reception.name());
        }
        coffeeMachine.makeCoffee(reception, userChoice);
        System.out.println("ваши " + userChoice + " " + reception.name() + " готовы!");
        logger.add("приготовился " + userChoice + " " + reception.name());
    }

    private void coffeeCups3Menu(Reception reception) {
        if (!coffeeMachine.ingredientsScan(reception, 3)) {
            System.out.println("не хватает ингридиентов для приготовления!");
            logger.add("ошибка готовки кофе! не хватает ингредиентов для: " + reception.name());
        }
        coffeeMachine.makeCoffee(reception, 3);
        System.out.println("ваши 3 " + reception.name() + " готовы!");
        mainMenu();
        logger.add("приготовился 3 " + reception.name());
    }

    private void coffeeCups1Menu(Reception reception) {
        if (!coffeeMachine.ingredientsScan(reception, 1)) {
            System.out.println("не хватает ингридиентов для приготовления!");
            logger.add("ошибка готовки кофе! не хватает ингредиентов для: " + reception.name());
        }
        coffeeMachine.makeCoffee(reception, 1);
        System.out.println("ваш 1 " + reception.name() + " готов!");
        mainMenu();
        logger.add("приготовился 1 " + reception.name());
    }

    private void checkIngredients() {
        do {
            System.out.println("1. проверить ингредиенты \n2. добавить ингредиенты \n0. назад");
            logger.add("открыто меню проверки ингредиентов");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    System.out.println("вода: " + coffeeMachine.getActualWater()
                            + " кофе: " + coffeeMachine.getActualCoffee()
                            + " молоко: " + coffeeMachine.getActualMilk());
                    logger.add("выполнен запрос проверки ингредиентов");
                }
                case 2: {
                    addIngredients();
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                }
            }
        } while (true);
    }

    private void addIngredients() {
        while (true) {
            System.out.println("1. добавить воды" +
                    "\n2. добавить кофе" +
                    "\n3. добавить молока" +
                    "\n0. назад");
            logger.add("открыто меню добавления ингредиентов");
            userChoice = checkToInt();
            switch (userChoice) {
                case 1: {
                    addWater();
                    break;
                }
                case 2: {
                    addCoffee();
                    break;
                }
                case 3: {
                    addMilk();
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("не верный выбор!");
                }
            }
            break;
        }
    }

    private void addMilk() {
        System.out.println("укажите количество");
        userChoice = checkToInt();
        if (coffeeMachine.getActualWater() + userChoice > coffeeMachine.waterMax) {
            System.out.println("перелив молока!");
            logger.add("ошибка! перелив молока");
            mainMenu();
        } else {
            coffeeMachine.addActualMilk(userChoice);
            System.out.println("молоко добавлено");
            logger.add("молоко добавлено");
        }
    }

    private void addCoffee() {
        System.out.println("укажите количество");
        userChoice = checkToInt();
        if (coffeeMachine.getActualWater() + userChoice > coffeeMachine.waterMax) {
            System.out.println("АХТУНГ!!");
            logger.add("ошибка! большое количество кофейных зерен");
            mainMenu();
        } else {
            coffeeMachine.addActualCoffee(userChoice);
            System.out.println("кофе добавлено");
            logger.add("кофе добавлено");
        }
    }

    private void addWater() {
        System.out.println("укажите количество");
        userChoice = checkToInt();
        if (coffeeMachine.getActualWater() + userChoice > coffeeMachine.waterMax) {
            System.out.println("перелив воды!!");
            logger.add("ошибка! перелив воды");
            mainMenu();
        } else {
            coffeeMachine.addActualWater(userChoice);
            System.out.println("вода добавлена");
            logger.add("вода добавлена");
        }
    }
}