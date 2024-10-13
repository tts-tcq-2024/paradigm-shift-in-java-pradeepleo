package vitals;

public class Main {
  
    // Limits for each parameter
    static final float TEMPERATURE_LOWER_LIMIT = 0;
    static final float TEMPERATURE_UPPER_LIMIT = 45;
    static final float SOC_LOWER_LIMIT = 20;
    static final float SOC_UPPER_LIMIT = 80;
    static final float CHARGE_RATE_UPPER_LIMIT = 0.8f;

    // Tolerance level for warnings (5%)
    static final float TOLERANCE_PERCENT = 0.05f;

    // Warning flags for tuning (can be turned on/off based on customer feedback)
    static boolean temperatureWarningEnabled = true;
    static boolean socWarningEnabled = true;
    static boolean chargeRateWarningEnabled = true;

    // Check if temperature is within limits
static boolean temperatureIsOk(float temperature) {
    boolean isWithinLimit = isWithinTemperatureLimit(temperature);
    if (temperatureWarningEnabled && isWithinLimit) {
        checkWarningForTemperature(temperature);
    }
    return isWithinLimit;
}

// Helper function to check if temperature is within limit
static boolean isWithinTemperatureLimit(float temperature) {
    return temperature >= TEMPERATURE_LOWER_LIMIT && temperature <= TEMPERATURE_UPPER_LIMIT;
}


    // Check if SoC is within limits
static boolean socIsOk(float soc) {
    boolean isWithinLimit = isWithinSoCLimit(soc);
    if (socWarningEnabled && isWithinLimit) {
        checkWarningForSoC(soc);
    }
    return isWithinLimit;
}

// Helper function to check if SoC is within limit
static boolean isWithinSoCLimit(float soc) {
    return soc >= SOC_LOWER_LIMIT && soc <= SOC_UPPER_LIMIT;
}


    // Check if charge rate is within limits
    static boolean chargeRateIsOk(float chargeRate) {
        if (chargeRate <= CHARGE_RATE_UPPER_LIMIT) {
            if (chargeRateWarningEnabled) {
                checkWarningForChargeRate(chargeRate);
            }
            return true;
        }
        return false;
    }

    // Warning check for temperature
    static void checkWarningForTemperature(float temperature) {
        float upperTolerance = TEMPERATURE_UPPER_LIMIT - (TEMPERATURE_UPPER_LIMIT * TOLERANCE_PERCENT);
        if (temperature > upperTolerance) {
            System.out.println("Warning: Approaching temperature peak");
        }
    }

    // Warning check for SoC
    static void checkWarningForSoC(float soc) {
        float upperTolerance = SOC_UPPER_LIMIT - (SOC_UPPER_LIMIT * TOLERANCE_PERCENT);
        float lowerTolerance = SOC_LOWER_LIMIT + (SOC_LOWER_LIMIT * TOLERANCE_PERCENT);
        
        if (soc > upperTolerance) {
            System.out.println("Warning: Approaching charge-peak");
        } else if (soc < lowerTolerance) {
            System.out.println("Warning: Approaching discharge");
        }
    }

    // Warning check for charge rate
    static void checkWarningForChargeRate(float chargeRate) {
        float upperTolerance = CHARGE_RATE_UPPER_LIMIT - (CHARGE_RATE_UPPER_LIMIT * TOLERANCE_PERCENT);
        if (chargeRate > upperTolerance) {
            System.out.println("Warning: Approaching charge-rate peak");
        }
    }

    // Display validation result
    static void displayValidation(String property, boolean validationStatus) {
        System.out.println(property + (validationStatus ? " is OK" : " is NOT OK"));
    }

    // Overall battery status check
    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        boolean isTempOk = temperatureIsOk(temperature);
        boolean isSocOk = socIsOk(soc);
        boolean isChargeRateOk = chargeRateIsOk(chargeRate);
        displayValidation("Temperature", isTempOk);
        displayValidation("State of Charge", isSocOk);
        displayValidation("Charge Rate", isChargeRateOk);
        return isTempOk && isSocOk && isChargeRateOk;
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
