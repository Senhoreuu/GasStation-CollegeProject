package com.GasStation;

import com.GasStation.compositions.IFuelType;

import java.util.ArrayList;

public class GasStation {
    private final ArrayList<Fuel> fuels;
    private final ArrayList<GasPump> gasPumps;
    private final ArrayList<Tank> tanks;

    public GasStation() {
        fuels = new ArrayList<Fuel>();
        gasPumps = new ArrayList<GasPump>();
        tanks = new ArrayList<Tank>();
    }

    public void addFuel(Fuel Fuel) {
        fuels.add(Fuel);
    }

    public void addGasPump(GasPump pump) {
        gasPumps.add(pump);
    }

    public void addTank(Tank tank) {
        tanks.add(tank);
    }

    public ArrayList<Fuel> getFuels() {
        return fuels;
    }

    public ArrayList<GasPump> getGasPumps() {
        return gasPumps;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public Fuel getFuel(IFuelType type) {
        for (Fuel Fuel : fuels) {
            if (Fuel.getType() == type) {
                return Fuel;
            }
        }
        return null;
    }

    public Tank getTank(IFuelType type) {
        for (Tank tank : tanks) {
            if (tank.getFuel().getType() == type) {
                return tank;
            }
        }
        return null;
    }

    public GasPump getGasPump(int id) {
        for (GasPump pump : gasPumps) {
            if (pump.getId() == id) {
                return pump;
            }
        }
        return null;
    }

    public String calculateTotalRevenue() {
        double totalRevenue = 0;
        for (GasPump pump : gasPumps) {
            totalRevenue += pump.getPrice();
        }
        return String.format("%.2f", totalRevenue);
    }
}
