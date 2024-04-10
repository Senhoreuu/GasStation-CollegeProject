package com.GasStation;

import com.GasStation.compositions.FuelType;
import com.GasStation.utils.IO;
import org.json.simple.JSONObject;

public class GasPump {
    private final int id;
    private Tank tank;

    public GasPump(Tank tank) {
        this.tank = tank;

        this.id = (int) (Math.random() * 10000);
    }

    public GasPump(int id,Tank tank) {
        this.id = id;
        this.tank = tank;
    }

    public GasPump() {
        this.id = (int) (Math.random() * 10000);
    }

    public int getId() {
        return id;
    }

    public Fuel getFuel() {
        if (tank == null)
            return null;

        return tank.getFuel();
    }

    public boolean hasFuel() {
        if (this.tank == null)
            return false;

        return this.tank.getFuel() != null;
    }

    public Tank getTank() {
        return this.tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        obj.put("id", this.id);
        if (getFuel() != null)
            obj.put("fuel", getFuel().getType().toString());
        else
            obj.put("fuel", "Unknown");

        return obj.toJSONString();
    }

    public void registerGasPump() {
        while (getFuel() == null) {
            IO.print("Qual o tipo de combustível?");
            IO.print("1 - Gasolina");
            IO.print("2 - Álcool");

            Tank tank = null;

            switch (IO.readInt("Digite aqui a opção: ")) {
                case 1:
                    tank = GasStation.getTank(FuelType.GASOLINE);

                    if (tank == null) {
                        IO.print("Combustível não cadastrado.");
                        return;
                    }
                    break;
                case 2:
                    tank = GasStation.getTank(FuelType.ETHANOL);

                    if (tank == null) {
                        IO.print("Combustível não cadastrado.");
                        return;
                    }
                    break;
                default:
                    IO.print("Opção inválida.");
            }

            if (tank != null) {
                this.setTank(tank);
                IO.print("Bomba de combustível cadastrada com sucesso.");
            }
        }
    }
}
