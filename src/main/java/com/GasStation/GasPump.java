package com.GasStation;

import com.GasStation.compositions.FuelType;
import com.GasStation.utils.IO;
import org.json.simple.JSONObject;

public class GasPump {
    private final int id;
    private Fuel fuel;

    public GasPump(Fuel fuel) {
        this.fuel = fuel;

        this.id = (int) (Math.random() * 10000);
    }

    public GasPump(int id, Fuel fuel) {
        this.id = id;
        this.fuel = fuel;
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

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        obj.put("id", this.id);
        if (this.fuel != null)
            obj.put("fuel", this.fuel.getType().toString());
        else
            obj.put("fuel", "Unknown");

        return obj.toJSONString();
    }

    public void registerGasPump() {
        while (this.fuel == null || !(this.fuel.getType() instanceof FuelType)) {
            IO.print("Qual o tipo de combustível?");
            IO.print("1 - Gasolina");
            IO.print("2 - Álcool");

            Fuel fuel;

            switch (IO.readInt("Digite aqui a opção: ")) {
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

        IO.print("Bomba de combustível cadastrada com sucesso.");
    }
}
