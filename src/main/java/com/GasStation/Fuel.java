package com.GasStation;

import com.GasStation.compositions.FuelType;
import com.GasStation.compositions.IFuelType;
import com.GasStation.utils.IO;

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
        this.price = price;
    }

    @Override
    public String toString() {
        return type + " - R$ " + price;
    }

    public void registerFuel() {
        // o while vai ficar rodando ate que o type seja de um combustivel valido (Gasolina e Alcool).
        // instanceof checa se o objeto eh uma instancia do outro objeto
        while (!(this.type instanceof FuelType)) {
            IO.print("Qual o tipo de combustível?");
            IO.print("1 - Gasolina");
            IO.print("2 - Álcool");
            int type = IO.readInt("Digite aqui a opção: ");

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

        // o while vai ficar rodando ate que o price seja maior que 0, bem simples
        while (this.price <= 0) {
            IO.print("Qual o preço do combustível " + this.type + "?");

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
}
