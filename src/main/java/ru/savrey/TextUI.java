package ru.savrey;

import java.util.List;
import java.util.Scanner;

public class TextUI {
    Shop shop;
    Cart cart;

    private int userChoice = 0;

    public TextUI(Shop shop) {
        this.shop = shop;
        cart = new Cart(shop);
        menu();
    }

    public void startScreen() {
        System.out.println("Выберите одно из действий:");
        System.out.println("1. Показать список доступных продуктов");
        System.out.println("2. Перейти в корзину");
        System.out.println("0. Выход");
    }

    public void storeProductsMenu() {
        System.out.println("Выберите одно из действий:");
        System.out.println("1. Добавить в корзину");
        System.out.println("2. Удалить из корзины");
        System.out.println("0: Выход");
    }

    public void menu() {
        do {
            startScreen();
            getUserInput();

            switch (userChoice) {
                case 1 -> {
                    displayStoreProducts();
                    storeProductsMenu();
                    getUserInput();
                    innerChoice();
                }
                case 2 -> showCart();
                case 0 -> System.exit(0);
                default -> {
                }
            }
        } while (userChoice != 0);
    }

    private void innerChoice() {
        switch (userChoice) {
            case 1 -> {
                addProductToCart();
                showCart();
            }
            case 2 -> {
                removeProductFromCart();
                showCart();
            }
            default -> {
            }
        }
    }

    private int getUserInput() throws NumberFormatException {
        Scanner in = new Scanner(System.in);
        userChoice = Integer.parseInt(in.nextLine());
        return userChoice;
    }

    private void displayStoreProducts() {
        List<Product> products = shop.productsShop();
        String format = "%1$-3s| %2$-20s| %3$-9s| %4$-3s\n";
        System.out.format(format, "ID", "Название", "Цена, р.", "Кол-во в магазине, шт.");
        for (Product prod : products) {
            System.out.format(format, prod.getId(), prod.getName(), prod.getPrice(), prod.getQuantity());
        }
        System.out.println();
    }

    private void addProductToCart() {
        System.out.println("Введите ID продукта, который хотите добавить в корзину:");
        int id = getUserInput();
        cart.addProductToCartByID(id);
    }

    private void showCart() {
        cart.printCartItems();
    }

    private void removeProductFromCart() {
        System.out.println("Введите ID продукта, который хотите удалить:");
        int id = getUserInput();
        cart.removeProductByID(id);
    }
}
