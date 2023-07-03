import java.util.Scanner;

public class StepTracker {
    Scanner scanner;
    Converter converter = new Converter();

    MonthData[] monthToData = new MonthData[12];
    int goalByStepsPerDay = 10000;

    StepTracker(Scanner scan) {
        scanner = scan;

        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumberStepsPerDay(Scanner scanner) {
        System.out.println("Введите номер месяца");
        int moth = scanner.nextInt();
        if(moth < 0 || moth > 12){System.out.println("Вы ввели неправильное число месяца"); return;}

        System.out.println("Введите день месяца от 1 до 30");
        int day = scanner.nextInt();
        if(day < 0 || day > 30){System.out.println("Вы ввели неправильное число дня"); return;}

        System.out.println("Введите количество пройденных шагов");
        int step = scanner.nextInt();
        if (step < 0 || step != 0){System.out.println("Вывдите число больше нуля"); return;}

        MonthData monthData = monthToData[moth-1];
        monthData.days[day-1] = step;
    }

    void changeStepGoal(Scanner scanner){
        int getGoalUser = scanner.nextInt();
        if(getGoalUser > 0  || getGoalUser != 0) {goalByStepsPerDay = getGoalUser;}
        else{System.out.println("Введите число больше нуля");}
    }

    void printStatistic(Scanner scanner){
        System.out.println("Введите число месяца за который нужно вывести статистику");
        
        int month = scanner.nextInt();
        MonthData monthData = monthToData[month - 1];
        int sumSteps = monthData.sumStepsFromMonth(); // получение суммы шагов за месяц
        System.out.println("\n");   
        
        monthData.printDaysAndStepsFromMonth();   // вывод общей статистики по дням

        System.out.println("Сумма шагов за месяц : " +  monthData.sumStepsFromMonth());        // вывод суммы шагов за месяц
        System.out.println("Максимальное количество шагов за месяц : " + monthData.maxSteps());               // вывод максимального пройденного количества шагов за месяц
        System.out.println("Средне пройденное кол-во шагов за месяц : " + (sumSteps / monthData.days.length));    // вывод среднего пройденного количества шагов за месяц
        System.out.println("Пройденная дистанция в километрах : " + converter.convertToKm(sumSteps)); //пройденная дистанция (в км);
        System.out.println("Количество сожжённых килокалорий за месяц : " + converter.convertStepsToKilocalories(monthData.sumStepsFromMonth()));  ;  // вывод количества сожжённых килокалорий за месяц
        System.out.println("Лучшая серия  : " + monthData.bestSeries(goalByStepsPerDay)); // вывод лучшей серии
     }

  }



