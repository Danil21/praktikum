import java.util.Scanner;

public class StepTracker {
    Scanner scanner;

    MonthData monthData;

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
        //MonthData monthData = new MonthData(moth);
        if(moth > 0 && moth <= 12){System.out.println("Вы ввели :" + moth);}
        else{System.out.println("Вы ввели неправильное число месяца"); return; }

        System.out.println("Введите день месяца от 1 до 30");
        int day = scanner.nextInt();
        if(day > 0 && day <= 30){System.out.println("Вы ввели :" + day);}
        else{System.out.println("Вы ввели неправильное число дня"); return; }

        System.out.println("Введите количество пройденных шагов");
        int step = scanner.nextInt();
        if (step >= 0){System.out.println("Вы ввели :" + step); }
        else{System.out.println("Вы ввели неправильное число"); return; }

        monthData.days[day-1] = step;
    }

    void changeStepGoal(Scanner scanner){
        int getGoalUser = scanner.nextInt();
        if(getGoalUser > 0  && getGoalUser != 0) {getGoalUser = goalByStepsPerDay;}
        else{System.out.println("Введите число больше нуля");}
    }

    void printStatistic(Scanner scanner){
        Converter converter = new Converter();
        System.out.println("Введите число месяца");
        monthData = new MonthData();
        int Month = scanner.nextInt();
        int sumSteps = monthData.sumStepsFromMonth(); // получение суммы шагов за месяц
        monthToData[Month - 1].printDaysAndStepsFromMonth(); // вывод общей статистики по дням
        monthData.sumStepsFromMonth(); // вывод суммы шагов за месяц
       // monthData.maxSteps(Month);         // вывод максимального пройденного количества шагов за месяц
        System.out.println("Средне пройденное кол-во шагов за месяц : " + (sumSteps / 30));    // вывод среднего пройденного количества шагов за месяц
        converter.convertToKm(monthData.sumStepsFromMonth()); //пройденная дистанция (в км);
        monthToData[Month - 1].printDaysAndStepsFromMonth();  // вывод количества сожжённых килокалорий за месяц
        monthData.bestSeries(goalByStepsPerDay); // вывод лучшей серии
 
    }
       

          
    }



