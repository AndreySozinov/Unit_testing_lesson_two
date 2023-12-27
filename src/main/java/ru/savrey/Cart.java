package ru.savrey;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    public List<Product> cartItems = new ArrayList<>();

    Shop shop;
    private Double totalPrice;

    public Cart(Shop shop) {
        this.shop = shop;
    }

    public void addProductToCartByID(int id) {
        Product product = getProductByProductID(id);

        addToCart(product);

        recalculate();
    }

    /**
     * Метод пересчитывает сумму покупки
     */
    public void recalculate() {
        totalPrice = 0.00d;
        cartItems.forEach(it -> totalPrice += it.getPrice() * it.getQuantity());
    }

    /**
     * Метод ищет продукт по его ID в магазине
     * @param id ID продукта
     * @return найденный продукт
     */
    private Product getProductByProductID(int id) {
        Product product = new Product();

        List<Product> products = shop.productsShop();
        for (Product prod : products) {
            if (prod.getId() == id) {
                product = prod;
                break;
            }
        }
        if (id > shop.productsShop().size() || id < 0) {
            try {
                throw new NoSuchFieldException("Не найден продукт с id: " + id);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Не найден продукт с id: " + id);
            }
        }
        return product;
    }

    /**
     * Метод прибавляет 1 товар в корзину и вычитает 1 в магазине
     * @param product продукт, добавляемый в корзину
     */
    private void addToCart(Product product) {
        Product productInCart = new Product(product.getId(),
                product.getName(), product.getPrice(), 0);
        Product productInShop = shop.productsShop().get(product.getId() - 1);

        if (productInShop.getQuantity() == 0) {
            System.out.println("Этого товара нет в наличии");
            return;
        }

        if (hasContainProduct(productInCart)) {
            Objects.requireNonNull(getContainProduct(productInCart))
                    .setQuantity(Objects.requireNonNull(getContainProduct(productInCart))
                    .getQuantity() + 1);
        } else {
            productInCart.setQuantity(1);
            cartItems.add(productInCart);
        }
        recalculate();
        shop.productsShop().get(product.getId() - 1).setQuantity(productInShop.getQuantity() - 1);
    }

    private boolean hasContainProduct(Product product) {
        for (Product cartItem : cartItems) {
            if (Objects.equals(cartItem.getId(), product.getId()) &&
                    shop.productsShop().get(product.getId()-1).getQuantity() >0) return true;
        }
        return false;
    }

    private boolean hasContainProductID(int id) {
        for (Product cartItem : cartItems) {
            if (Objects.equals(cartItem.getId(), id)) return true;
        }
        return false;
    }

    /**
     * Метод ищет продукт в корзине, если он уже был в нее добавлен ранее
     * @param product Искомый продукт
     * @return Найденный продукт
     */
    private Product getContainProduct(Product product) {
        for (Product cartItem : cartItems) {
            if (Objects.equals(cartItem.getId(), product.getId())) return cartItem;
        }
        return null;
    }

    /**
     * Метод возвращает один экземпляр товара из корзины в магазин
     * @param id ID товара
     */
    public void removeProductByID(int id) {
        if (!hasContainProductID(id)) {
            if (!hasContainProductID(id)) {
                try {
                    throw new NoSuchFieldException("В корзине не найден продукт с id: " +id);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException("В корзине не найден продукт с id: " +id);
                }
            }
        }

        /**
         * Снижает кол-во товара в корзине на 1
         */
        Product prod = getProductByProductID(id);
        if (hasContainProduct(prod) && Objects.requireNonNull(getContainProduct(prod)).getQuantity() > 1) {
            Objects.requireNonNull(getContainProduct(prod)).setQuantity(Objects
                    .requireNonNull(getContainProduct(prod)).getQuantity() - 1);
        } else if (hasContainProduct(prod) && Objects.requireNonNull(getContainProduct(prod)).getQuantity() == 1) {
            cartItems.remove(getContainProduct(prod));
        }

        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        recalculate();

        Product productInShop = shop.productsShop().get(id - 1);
        shop.productsShop().get(id - 1).setQuantity(productInShop.getQuantity() + 1);
    }

    void printCartItems() {
        String format = "%1$-3s| %2$-20s| %3$-9s| %4$-3s\n";
        System.out.println("Сейчас у вас в корзине:");
        System.out.format(format, "ID", "Название", "Цена, р.", "Кол-во в корзине, шт.");
        for (Product prod : cartItems) {
            System.out.format(format, prod.getId(), prod.getName(), prod.getPrice(), prod.getQuantity());
        }
        System.out.println("Общая стоимость корзины: " + getTotalPrice() + " р.");
        System.out.println();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
