package vitals;

public class Main {
  static boolean temperatureIsOk(float temperature) {
        return (temperature >= 0 && temperature <= 45);
    }
    static boolean socIsOk(float soc) {
        return (soc >=20 && soc <=80);
    }
    static boolean chargeRateIsOk(float chargeRate) {
        return (chargeRate <= 0.8);
    }
    static void displayValidation(String property, boolean ValidationStatus){
        System.out.println(property + (ValidationStatus ? " is Ok" : " is Not Ok"));
    }
    
    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        boolean isTempOk = temperatureIsOk(temperature);
        boolean issocIsOk = socIsOk(soc);
        boolean ischargeRateOk = chargeRateIsOk(chargeRate);
        displayValidation("temperature", isTempOk);
        displayValidation("State of charge", issocIsOk);
        displayValidation("ChargeRate", ischargeRateOk);
        return isTempOk && issocIsOk && ischargeRateOk;
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
