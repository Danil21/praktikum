import java.util.Calendar;

public class MonthData {

    Calendar calendar = Calendar.getInstance();
    int[] days = new int[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)];;

    void printDaysAndStepsFromMonth(){
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + " День : " + days[i]);
        }
    }

    int sumStepsFromMonth(){
        int sum = 0;
        for (int i = 0; i < days.length; i++) {
            sum = sum + days[i];
        }

        return sum;
    }

    int maxSteps(){
        int maxStepsCompleted = 0;
        for (int i = 0; i < days.length; i++) {
            if (days[i] > maxStepsCompleted) {
                maxStepsCompleted = days[i];
            }
        }

        System.out.println("Максимальное количество шагов за месяц : " + maxStepsCompleted);
        return maxStepsCompleted;
    }

        int bestSeries(int goalByStepsPerDay){
    
        int max = 0;
        int current = 0;
        for (int i = 0; i < days.length; i++) {
            if (days[i] >= goalByStepsPerDay) {
                current++;
                if (current > max) {
                   max = current;
                }
            } else { current = 0; }
        }
        return max;
          
      }

}

