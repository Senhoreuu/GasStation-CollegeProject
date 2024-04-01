package com.GasStation.utils;

import com.GasStation.compositions.FuelType;
import com.GasStation.compositions.IFuelType;

public class Translate {
    static public String translate(IFuelType fuel) {
        if (fuel.equals(FuelType.GASOLINE)) {
            return "Gasolina";
        } else if (fuel.equals(FuelType.ETHANOL)) {
            return "Álcool";
        }

        return "Unknown";
    }
}
