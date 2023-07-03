public class MonthData {

    int[] days = new int[30];

    void printDaysAndStepsFromMonth(){
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + " день :" + days[i]);
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

