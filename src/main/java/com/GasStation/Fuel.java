package com.GasStation;

import com.GasStation.compositions.FuelType;
import com.GasStation.compositions.IFuelType;
import com.GasStation.utils.IO;
import com.GasStation.utils.Translate;
import org.json.simple.JSONObject;

public class Fuel {
    private IFuelType type;
    private double price;

    public Fuel(IFuelType type, double price) {
        this.type = type;
        this.price = price;
    }

    public Fuel() {
    }

    public IFuelType getType() {
        return type;
    }

    public void setType(IFuelType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            return;
        }

        this.price = price;
    }

    public void registerFuel() {
        while (!(this.type instanceof FuelType)) {
            IO.print("Qual o tipo de combustível?");
            IO.print("1 - Gasolina");
            IO.print("2 - Álcool");
            int type = IO.readInt("Digite aqui a opção: ");

            if (GasStation.getFuel(type == 1 ? FuelType.GASOLINE : FuelType.ETHANOL) != null) {
                IO.print("Combustível já cadastrado.");
                return;
            }

            switch (type) {
                case 1:
                    this.setType(FuelType.GASOLINE);
                    break;
                case 2:
                    this.setType(FuelType.ETHANOL);
                    break;
                default:
                    IO.print("Opção inválida.");
            }
        }

        while (this.price <= 0) {
            IO.print("Qual o preço do combustível " + Translate.translate(this.type) + "?");

            double price = IO.readDouble("Digite aqui o valor: ");

            if (price <= 0) {
                IO.print("Valor inválido. O valor deve ser maior que 0.");
                continue;
            }

            this.setPrice(price);
        }

        // finaliza o cadastro
        IO.print("Combustível cadastrado com sucesso.");
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        obj.put("type", this.type.toString());
        obj.put("price", this.price);

        return obj.toJSONString();
    }
}
