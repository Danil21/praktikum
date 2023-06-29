import java.util.Scanner;

public class StepTracker {
    Scanner scanner;
    MonthData monthToData[] = new MonthData[12];

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
        if(moth <= 12 | moth == 0 || moth < 0 ){System.out.println("Вы ввели неправильное число месяца"); break;}

        System.out.println("Введите день месяца от 1 до 30");
        int day = scanner.nextInt();
        if(day <= monthData.dataMoth.length | day == 0 || day < 0 ){System.out.println("Вы ввели неправильное число дня"); break;}

        System.out.println("Введите количество пройденных шагов");
        int step = scanner.nextInt();
        if (step == 0 || step < 0 ){System.out.println("Вы ввели неправильное число"); break; }

        MonthData monthData = new MonthData(moth);
        monthData.days[day-1] += step; 
    }

    void changeStepGoal(Scanner scanner){
        int getGoalUser = scanner.nextInt();
        if(getGoalUser > 0 || getGoalUser != 0) {getGoalUser = goalByStepsPerDay;}
        else{System.out.println("Введите число больше нуля");}
    }

    void printStatistic(){
        Converter converter = new Converter();
      // 
    }


}


