import java.util.Calendar;
public class MonthData {

    int[] dataMoth;

    public void getStepForDay(){

        Calendar calendar = Calendar.getInstance();
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dataMoth = new int[days];

    }
}
