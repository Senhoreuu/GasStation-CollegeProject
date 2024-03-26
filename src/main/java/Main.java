import com.GasStation.utils.*;
import com.GasStation.*;
import com.GasStation.compositions.*;

public class Main {
    public static void showMenu() {
        IO.print("O que deseja fazer?");
        IO.print("1 - Cadastrar um novo combustível");
        IO.print("2 - Cadastrar um novo tanque");
        IO.print("3 - Cadastrar uma nova bomba");
        IO.print("4 - Editar preço de um combustível");
        IO.print("5 - Abastecer");
        IO.print("6 - Calcular faturamento total");
        IO.print("0 - Sair");
    }

    public static void main(String[] args) {
        int option = -1;
        String gasStationName = IO.readString("Digite o nome do seu posto de combustivel: ");

        IO.print("Bem-vindo ao posto " + gasStationName);
        showMenu();

        GasStation gasStation = new GasStation();

        while (option != 0) {
            option = IO.readInt("Digite a opção desejada: ");

            switch (option) {
                case 1:
                    Fuel fuel = new Fuel();
                    fuel.registerFuel();

                    gasStation.addFuel(fuel);
                    break;
                case 2:
                    Tank tank = new Tank();
                    tank.registerTank();

                    gasStation.addTank(tank);
                    break;
                case 3:
                    GasPump gasPump = new GasPump();
                    gasPump.registerGasPump();

                    gasStation.addGasPump(gasPump);
                    break;
                case 4:
                    //todo
                    break;
                case 5:
                    //todo
                    break;
                case 6:
                    IO.print("Faturamento total: R$ " + gasStation.calculateTotalRevenue());
                    break;
                default:
                    IO.print("O posto " + gasStationName + " foi fechado.");
                    return;
            }

            showMenu();
        }
    }
}
