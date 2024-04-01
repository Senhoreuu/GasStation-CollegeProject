package com.GasStation;

import com.GasStation.compositions.FuelType;
import com.GasStation.utils.IO;
import org.json.simple.JSONObject;

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
        this.fuel = new Fuel();
        this.capacity = 0;
        this.quantity = 0;
        this.id = (int) (Math.random() * 10000);
    }

    public Tank(int id, Fuel fuel, double capacity, double quantity) {
        this.id = id;
        this.fuel = fuel;
        this.capacity = capacity;
        this.quantity = 0;
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
        if (quantity < 0) {
            return;
        }

        this.quantity = quantity;
    }

    public void addQuantity(double quantity) {
        this.quantity += quantity;
    }

    private void setCapacity(double capacity) {
        if (capacity < 0) {
            return;
        }

        this.capacity = capacity;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public void registerTank() {
        while (!(this.fuel.getType() instanceof FuelType)) {
            IO.print("Qual o tipo de combustível?");
            IO.print("1 - Gasolina");
            IO.print("2 - Álcool");
            int type = IO.readInt("Digite aqui a opção: ");

            Fuel fuel;

            switch (type) {
                case 1:
                    fuel = GasStation.getFuel(FuelType.GASOLINE);

                    if (fuel == null) {
                        IO.print("Combustível não cadastrado.");
                        return;
                    }

                    this.setFuel(fuel);
                    break;
                case 2:
                    fuel = GasStation.getFuel(FuelType.ETHANOL);

                    if (fuel == null) {
                        IO.print("Combustível não cadastrado.");
                        return;
                    }

                    this.setFuel(fuel);
                    break;
                default:
                    IO.print("Opção inválida.");
            }
        }

        while (this.capacity <= 0) {
            IO.print("Qual a capacidade do tanque?");
            this.setCapacity(IO.readDouble("Digite aqui o valor: "));
        }

        IO.print("Tanque cadastrado com sucesso.");
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        obj.put("id", this.id);
        obj.put("fuel", this.fuel.getType().toString());
        obj.put("capacity", this.capacity);
        obj.put("quantity", this.quantity);

        return obj.toJSONString();
    }
}
