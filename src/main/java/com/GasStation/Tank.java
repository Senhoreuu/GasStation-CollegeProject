package com.GasStation;

import com.GasStation.compositions.FuelType;
import com.GasStation.utils.IO;

// Classe do Tanque de combustivel
public class Tank {
    private final int id;
    private Fuel fuel;
    private double capacity;
    private double quantity;

    public Tank(Fuel fuel, double capacity) {
        this.fuel = fuel;
        this.capacity = capacity;
        this.quantity = capacity;

        // (int) faz um cast para inteiro
        this.id = (int) (Math.random() * 10000);
    }

    public Tank() {
        this.id = (int) (Math.random() * 10000);
    }

    public int getId() {
        return id;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    private void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    /// "@Override" sobrescreve o metodo que ja existe
    @Override
    public String toString() {
        return fuel.getType() + " - " + quantity + "L";
    }

    public void registerTank() {
        while (!(this.fuel.getType() instanceof FuelType)) {
            IO.print("Qual o tipo de combustível?");
            IO.print("1 - Gasolina");
            IO.print("2 - Álcool");
            int type = IO.readInt("Digite aqui a opção: ");

            switch (type) {
                case 1:
                    this.setFuel(new Fuel(FuelType.GASOLINE, 0));
                    break;
                case 2:
                    this.setFuel(new Fuel(FuelType.ETHANOL, 0));
                    break;
                default:
                    IO.print("Opção inválida.");
            }
        }

        while(this.fuel.getPrice() <= 0) {
            IO.print("Qual o preço do combustível?");
            this.fuel.setPrice(IO.readDouble("Digite aqui o valor: "));
        }

        while (this.capacity <= 0) {
            IO.print("Qual a capacidade do tanque?");
            this.setCapacity(IO.readDouble("Digite aqui o valor: "));
        }
    }
}
