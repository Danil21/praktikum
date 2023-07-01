import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StepTracker stepTracker = new StepTracker(scanner);

            while (true) {
                System.out.println("\n");
                printMenu();

                int command = scanner.nextInt();
                   
                if (command == 0) {
                    System.out.println("Выход.");
                    break;
                } else if (command == 1) {
                  stepTracker.addNewNumberStepsPerDay(scanner);
                } else if (command == 2) {
                    stepTracker.changeStepGoal(scanner);
                } else if (command == 3) {
                    stepTracker.printStatistic(scanner);
                } else {
                    System.out.println("Извините, такой команды пока нет. \n");
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количесво шагов за опреденный день");
        System.out.println("2 - Изменить цель по кол-во шагов");
        System.out.println("3 - Посмотреть статистику за определенный месяц.");
        System.out.println("0 - Выход.");
    }

}
