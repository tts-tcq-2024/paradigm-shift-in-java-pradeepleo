package vitals;

public class Main {
    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        return temperatureIsOk(temperature) && socIsOk(soc) && chargeRateIsOk(chargeRate);
    }
    static boolean temperatureIsOk(float temperature) {
       if(temperature < 0 || temperature > 45) {
            System.out.println("Temperature is out of range!");
            return false;
        }
        return true;
    }
    static boolean socIsOk(float soc) {
        if(soc < 20 || soc > 80){
            System.out.println("State of Charge is out of range!");
            return false;
        }
        return true;
    }
    static boolean chargeRateIsOk(float chargeRate) {
        if(chargeRate > 0.8){
            System.out.println("State of Charge is out of range!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert(batteryIsOk(25, 70, 0.7f) == true);
        assert(batteryIsOk(50, 85, 0.0f) == false);
        assert(batteryIsOk(50, 85, 0.9f) == false);
        assert(batteryIsOk(23, 85, 0.9f) == false);
        assert(batteryIsOk(43, 56, 0.9f) == false);
        System.out.println("Some more tests needed");
    }
}
