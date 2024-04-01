import com.GasStation.Fuel;
import com.GasStation.GasPump;
import com.GasStation.GasStation;
import com.GasStation.Tank;
import com.GasStation.utils.IO;
import com.GasStation.utils.Saver;
import com.GasStation.utils.Translate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void showMenu() {
        IO.print("O que deseja fazer?");
        IO.print("1 - Cadastrar um novo combustível");
        IO.print("2 - Cadastrar um novo tanque");
        IO.print("3 - Cadastrar uma nova bomba");
        IO.print("4 - Editar preço de um combustível");
        IO.print("5 - Abastecer Tanque");
        IO.print("6 - Calcular faturamento total");
        IO.print("7 - Mostrar combustíveis cadastrados");
        IO.print("8 - Mostrar tanques cadastrados");
        IO.print("9 - Mostrar bombas cadastradas");
        IO.print("0 - Sair");
    }

    public static void main(String[] args) {
        int option = -1;

        String gasStationName = (String) Saver.get("gasStationName");

        if (gasStationName.isEmpty()) {
            gasStationName = IO.readString("Digite o nome do posto: ");
            Saver.save("gasStationName", gasStationName);
        }

        GasStation.setName(gasStationName);
        GasStation.loadStation();

        IO.print("Bem-vindo ao posto " + GasStation.getName());

        while (option != 0) {
            showMenu();
            option = IO.readInt("Digite a opção desejada: ");

            switch (option) {
                case 1:
                    Fuel fuel = new Fuel();
                    fuel.registerFuel();

                    if (fuel.getType() == null) {
                        break;
                    }

                    GasStation.addFuel(fuel);
                    break;
                case 2:
                    Tank tank = new Tank();
                    tank.registerTank();

                    if (tank.getFuel() == null) {
                        break;
                    }

                    GasStation.addTank(tank);
                    break;
                case 3:
                    GasPump gasPump = new GasPump();
                    gasPump.registerGasPump();

                    if (gasPump.getFuel() == null) {
                        break;
                    }

                    GasStation.addGasPump(gasPump);
                    break;
                case 4:
                    GasStation.editFuelPrice();

                    Saver.save("fuels", GasStation.getFuels().toString());
                    break;
                case 5:
                    GasStation.fillUp();
                    break;
                case 6:
                    IO.print("Faturamento total: R$ " + GasStation.calculateTotalRevenue());
                    break;
                case 7:
                    if (GasStation.getFuels().isEmpty()) {
                        IO.print("Nenhuma combustível cadastrada.");
                        break;
                    }

                    IO.print("Combustíveis cadastrados: ");
                    Stream<Fuel> fuels = GasStation.getFuels().stream();
                    Stream<String> fuelsInString = fuels.map(f -> "Tipo: " + Translate.translate(f.getType()) + ". Preço: " + f.getPrice());
                    IO.print(fuelsInString.collect(Collectors.joining("\n")));
                    break;
                case 8:
                    if (GasStation.getTanks().isEmpty()) {
                        IO.print("Nenhuma tanques cadastrada.");
                        break;
                    }

                    IO.print("Tanques cadastrados: ");
                    Stream<Tank> tanks = GasStation.getTanks().stream();
                    Stream<String> tanksInString = tanks.map(t -> "Tipo: " + Translate.translate(t.getFuel().getType()) + ". Capacidade: " + t.getCapacity() + ". Quantidade: " + t.getQuantity());
                    IO.print(tanksInString.collect(Collectors.joining("\n")));
                    break;
                case 9:
                    if (GasStation.getGasPumps().isEmpty()) {
                        IO.print("Nenhuma bomba cadastrada.");
                        break;
                    }

                    IO.print("Bombas cadastradas: ");
                    Stream<GasPump> gasPumps = GasStation.getGasPumps().stream();
                    Stream<String> gasPumpsInString = gasPumps.map(p -> "ID: " + p.getId() + ". Tipo: " + Translate.translate(p.getFuel().getType()));
                    IO.print(gasPumpsInString.collect(Collectors.joining("\n")));
                    break;
                default:
                    IO.print("O posto " + gasStationName + " foi fechado.");
                    option = 0;
                    break;
            }
        }
    }
}