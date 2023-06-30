import java.util.Calendar;

public class MonthData {

    int[] dataMoth;
    int[] days;

	public void getStepForDay(){
        Calendar calendar = Calendar.getInstance();
        dataMoth = new int[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)];
    }

    void printDaysAndStepsFromMonth(){
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + days[i] + "День :" + dataMoth[days[i]]);
        }
    }

    int sumStepsFromMonth(){
        int sum = 0;
        for (int i = 0; i < days.length; i++) {
            sum = sum + days[i];
        }

        return sum;
    }

    int maxSteps(int[] days){
        int maxStepsCompleted = 0;
        for (int i = 0; i < days.length; i++) {
            if (days[i] > maxStepsCompleted) {
                maxStepsCompleted = days[i];
            }
        }

        System.out.println("Максимальное количество шагов за месяц" + maxStepsCompleted);
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

