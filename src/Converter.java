public class Converter {
    
    int convertToKm(int steps){
        int stepLengthCm = 75; //1 шаг в сантиметрах
        int cmToKm = 100000; //1 километр – 100 000 сантиметров
        return (steps*stepLengthCm) / cmToKm;
    }

    int convertStepsToKilocalories(int steps){
        int caloriesPerStep = 50; //калорий на шаг
        int caloriesPerKCalories = 1000; //1 кКалория - 1000 калорий
        return (steps*caloriesPerStep) / caloriesPerKCalories;
    }

}
