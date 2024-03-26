package com.GasStation;

public class GasPump {
    private final int id;
    private Fuel fuel;
    private double price;

    public GasPump(Fuel fuel, double price) {
        this.fuel = fuel;
        this.price = price;

        this.id = (int) (Math.random() * 10000);
    }

    public GasPump() {
        this.id = (int) (Math.random() * 10000);
    }

    public int getId() {
        return id;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return fuel.getType() + " - R$ " + price;
    }

    public void registerGasPump() {
    }
}
