import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StepTracker stepTracker = new StepTracker(scanner);

            while (true) {
                printMenu();

                int command = scanner.nextInt();

                if (command == 0) {
                    System.out.println("Выход.");
                    break;
                } else if (command == 1) {
                    
                } else if (command == 2) {
                    stepTracker.addNewNumberStepsPerDay(scanner);
                } else if (command == 3) {
                    stepTracker.printStatistic();
                } else {
                    System.out.println("Извините, такой команды пока нет.");
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Вывод количесво шагов за опреденный день, 1-ПН, 2-ВТ, 3-СР, 4-ЧТ, 5-ПТ, 6-СБ, 7-ВС");
        System.out.println("2 - Изменить цель по кол-во шагов");
        System.out.println("3 - Посмотреть статистику за определенный месяц.");
        System.out.println("0 - Выход.");
    }

}
