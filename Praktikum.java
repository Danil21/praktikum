import java.util.Scanner;

public class Practicum {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StepTracker library = new StepTracker(scanner);

            while (true) {
                printMenu();

                int command = scanner.nextInt();
                if (command == 0) {
                    System.out.println("Выход.");
                    break;
                } else if (command == 1) {
                    library.addBook();
                } else if (command == 2) {
                    library.findByAuthor();
                } else if (command == 3) {
                    library.printAll();
                } else {
                    System.out.println("Извините, такой команды пока нет.");
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Вывод шагов за пн-1, ");
        System.out.println("2 - Изменить цель по кол-во шагов");
        System.out.println("3 - Увидеть список книг.");
        System.out.println("0 - Выход.");
    }

}
