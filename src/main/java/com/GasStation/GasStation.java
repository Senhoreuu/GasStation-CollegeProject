package com.GasStation;

import com.GasStation.compositions.FuelType;
import com.GasStation.compositions.IFuelType;
import com.GasStation.utils.IO;
import com.GasStation.utils.Saver;
import com.GasStation.utils.Translate;

import java.util.ArrayList;

public class GasStation {
    private static final ArrayList<Fuel> fuels = new ArrayList<Fuel>();
    private static final ArrayList<GasPump> gasPumps = new ArrayList<GasPump>();
    private static final ArrayList<Tank> tanks = new ArrayList<Tank>();

    private static String name;

    public static void addFuel(Fuel Fuel) {
        fuels.add(Fuel);

        Saver.save("fuels", fuels.toString());
    }

    public static void addGasPump(GasPump pump) {
        gasPumps.add(pump);

        Saver.save("gasPumps", gasPumps.toString());
    }

    public static void addTank(Tank tank) {
        tanks.add(tank);

        Saver.save("tanks", tanks.toString());
    }

    public static ArrayList<Fuel> getFuels() {
        return fuels;
    }

    public static ArrayList<GasPump> getGasPumps() {
        return gasPumps;
    }

    public static ArrayList<Tank> getTanks() {
        return tanks;
    }

    public static Fuel getFuel(IFuelType type) {
        for (Fuel Fuel : fuels) {
            if (Fuel.getType() == type) {
                return Fuel;
            }
        }
        return null;
    }

    public static Tank getTank(IFuelType type) {
        for (Tank tank : tanks) {
            if (tank.getFuel().getType() == type) {
                return tank;
            }
        }
        return null;
    }

    public static GasPump getGasPump(int id) {
        for (GasPump pump : gasPumps) {
            if (pump.getId() == id) {
                return pump;
            }
        }
        return null;
    }

    public static String calculateTotalRevenue() {
        return "";
    }

    public static void editFuelPrice() {
        IO.print("Qual combustível deseja editar o preço? ");
        IO.print("1 - Gasolina");
        IO.print("2 - Álcool");

        int fuel = IO.readInt("Digite aqui a opção: ");

        if (fuel != 1 && fuel != 2) {
            IO.print("Opção inválida.");
            return;
        }

        IFuelType type = fuel == 1 ? FuelType.GASOLINE : FuelType.ETHANOL;
        Fuel Fuel = getFuel(type);

        if (Fuel == null) {
            IO.print("Você ainda não cadastrou o combustível " + Translate.translate(type) + ". Cadastre-o antes de alterar o preço!!");
            return;
        }

        double price = IO.readDouble("Digite o novo preço: ");

        if (price < 0) {
            IO.print("O preço não pode ser menor que zero (negativo)");
            return;
        }

        Fuel.setPrice(price);

        IO.print("Preço do combustível " + Translate.translate(Fuel.getType()) + " alterado para R$ " + price);
    }

    public static void fillUp() {
        IO.print("Qual o tipo de combustível?");
        IO.print("1 - Gasolina");
        IO.print("2 - Álcool");

        int fuel = IO.readInt("Digite aqui a opção: ");

        if (fuel != 1 && fuel != 2) {
            IO.print("Opção inválida.");
            return;
        }

        IFuelType type = fuel == 1 ? FuelType.GASOLINE : FuelType.ETHANOL;
        Fuel Fuel = getFuel(type);

        if (Fuel == null) {
            IO.print("Você ainda não cadastrou o combustível " + Translate.translate(type) + ". Cadastre-o antes de abastecer.");
            return;
        }

        for (GasPump gasPump : getGasPumps()) {
            if (gasPump.getFuel().getType() == type) {
                IO.print("Bomba de combustível: " + gasPump.getId());
            }
        }

        int gasPumpId = IO.readInt("Qual o número da bomba de combustível? ");

        GasPump gasPump = getGasPump(gasPumpId);
        Tank tank = getTank(type);

        if (tank == null) {
            IO.print("Você ainda não cadastrou o tanque para o combustível " + Translate.translate(type));
            return;
        }

        while (true) {
            double amount = IO.readDouble("Quantos litros deseja abastecer? ");
            if (amount <= 0) {
                IO.print("Quantidade inválida. A quantidade deve ser maior que zero");
                continue;
            }

            if (tank.getCapacity() < amount) {
                IO.print("O tanque não comporta essa quantidade de combustível.");
                continue;
            }

            tank.addQuantity(amount);

            break;
        }

        Saver.save("tanks", tanks.toString());

        IO.print("Tanque abastecido com sucesso");
        IO.print("Quantidade atual: " + tank.getQuantity() + " litros");
    }

    public static void setName(String gasStationName) {
        name = gasStationName;
    }

    public static String getName() {
        return name;
    }

    public static void loadStation() {
        loadFuels();
        loadTanks();
        loadGasPumps();
    }

    private static void loadFuels() {
        try {
            ArrayList<Fuel> fuelsList = Saver.getFuels();

            if (fuelsList.isEmpty()) {
                return;
            }

            fuels.addAll(fuelsList);
        } catch (Exception e) {
            System.out.println("[X] Falha ao recuperar os combustíveis.");
        }
    }

    private static void loadTanks() {
        try {
            ArrayList<Tank> tanksList = Saver.getTanks();

            if (tanksList.isEmpty()) {
                return;
            }

            tanks.addAll(tanksList);
        } catch (Exception e) {
            System.out.println("[X] Falha ao recuperar os tanques.");
        }
    }

    private static void loadGasPumps() {
        try {
            ArrayList<GasPump> gasPumpsList = Saver.getGasPumps();

            if (gasPumpsList.isEmpty()) {
                return;
            }

            gasPumps.addAll(gasPumpsList);
        } catch (Exception e) {
            System.out.println("[X] Falha ao recuperar as bombas de combustível.");
        }
    }
}
