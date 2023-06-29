public class Converter {
    
    int convertToKm(int steps){
        steps = (steps*75) / 100000 ;
        return steps;
    }

    int convertStepsToKilocalories(int steps){
        steps  = (steps*50) / 1000;
        return steps;
    }

}
