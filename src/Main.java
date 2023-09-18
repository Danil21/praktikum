import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Practicum
    // мы подготовили эти прайс-листы
    static List<Item> PRICE_LIST_1913 = Arrays.asList(new Item("Корова", 60.0),
            new Item("Курица", 0.60),
            new Item("Рубашка", 1.0),
            new Item("Шапка гусарская", 12.0),
            new Item("Гармонь", 7.50),
            new Item("Рояль", 200.0));

    static List<Item> PRICE_LIST_1984 = Arrays.asList(new Item("Банка сгущёнки", 0.55),
            new Item("Автомобиль «Запорожец»", 5600.0),
            new Item("Мороженое", 0.20),
            new Item("Шапка-ушанка цигейковая", 14.0),
            new Item("Шампанское «Советское»", 3.6),
            new Item("Карта мира", 2.54),
            new Item("Мотоцикл «Восход»", 450.0),
            new Item("Портативный кассетный магнитофон «Весна-202-1»", 195.0),
            new Item("Пальто осеннее", 100.0));

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму в современных рублях:");
        int amount = scanner.nextInt();

        System.out.println("Введите год (1913 или 1984)");
        int year = scanner.nextInt();

        // это коэффициент для перевода рубля по курсу в заданном году
        double koef = getYearKoef(year);

        // курс рубля задан не для каждого года
        if(koef == 0){
            System.out.println("Для этого года у нас нет данных.");
            return;
        }

        // переведите заданную сумму в рубли по старому курсу
        double amountByYear = amount * (1/koef);

        // выведите получившийся курс рубля
        printAmountInYear(year, amountByYear);

        // выведите товары, которые вам доступны
        printQuantityByYear(year, amountByYear);
    }

    private static double getYearKoef(int year) {
        // реализуйте функцию с помощью switch (year)
        switch(year){
            case 1913:
                return  884;
            case 1984:
                return  337;
            default:
                return 0;
        }
    }

    public static void printAmountInYear(int year, double amount) {
        System.out.println("В " + year + " году эта сумма приблизительно равнялась бы "
                + String.format("%,.2f", amount) + " р. "
                + "На эти деньги вы могли бы купить следующие товары:");
    }

    public static void printQuantityByYear(int year, double amount) {
        List<Item> priceList = new ArrayList<>();
        // реализуйте заполнение прайслиста с помощью switch(year)
        switch(year){
            case 1913:
                priceList.addAll(PRICE_LIST_1913);
                break;
            case 1984:
                priceList.addAll(PRICE_LIST_1984);
                break;
        }
        // здесь мы выводим доступные товары из списка
        for (Item item : priceList) {

            double quantity=0;
            if(amount > item.price){quantity = amount / item.price;}
            amount -= item.price;

            if(quantity > 0 & amount > item.price | amount > 0) {
                System.out.println(item.name + " — " + (int) quantity + " шт.");
            }
            
          //  printItemYouCanBuy(item, amount);

        }      
    }

    private static void printItemYouCanBuy(Item item, double amount) {
        // подсчитайте количество товара в штуках, которое вы можете купить за amount рублей
       // double ost = amount - item.price;
//        double quantity= 0.0;
//
//    //  x - x.price =n
//        for(int i= 0; i < amoun% item.price; i++){
//            quantity = amount / item.price;
//            amoun -= item.price;
//        }
        double am = amount;
        double quantity=0;
        if(amount > item.price){quantity = amount / item.price;}
        am = amount - item.price;
        if(quantity > 0 & am > item.price | am > 0) {
            System.out.println(item.name + " — " + (int) quantity + " шт.");
        }
    }

    // с помощью этого класса описывается товар и его цена
    static class Item {
        String name;

        double price;

        Item(String name, double price){
            this.name = name;
            this.price = price;
        }
    }

}
