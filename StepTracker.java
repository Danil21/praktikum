import java.util.ArrayList;
import java.util.Scanner;
public class StepTracker {

    MonthData monthData = new MonthData();

    MonthData monthToData[] = new MonthData[12];
    int goalByStepsPerDay;

    ArrayList<Convertor> addStep;

    public StepTracker(Scanner scanner) {
        addStep = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    void addNewNumberStepsPerDay(Scanner scanner) {
        System.out.println("Введите номер месяца");
        String moth = scanner.nextLine();

        System.out.println("Введите номер дня");
        String day = scanner.nextLine();

        System.out.println("Введите количество шагов");
        int step = scanner.nextInt();

        addStep.add(new Convertor(moth, day, step));
    }

    public static double findMaxExpense(double[] expenses){
        double maxExpense = 0;
        for (int i = 0; i < expenses.length; i++) {
            if (expenses[i] > maxExpense) {
                maxExpense = expenses[i];
            }
        }
        // Печать должна остаться здесь — для получения максимальной траты нужно вызвать метод
        System.out.println("Самая большая сумма расходов на этой неделе составила " + maxExpense + " руб.");
        return maxExpense;
    }
}


