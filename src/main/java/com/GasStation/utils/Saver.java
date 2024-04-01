package com.GasStation.utils;

import com.GasStation.Fuel;
import com.GasStation.GasPump;
import com.GasStation.GasStation;
import com.GasStation.Tank;
import com.GasStation.compositions.FuelType;
import com.GasStation.compositions.IFuelType;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

// Class que vai servir como um banco de dados
public class Saver {
    private static final String DEFAULT_CONFIG_FILE = "resources/config.json";
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String DIRECTORY = USER_DIR + "/posto/src/main/java/";
    private static final String CONFIG_FILE = DIRECTORY + DEFAULT_CONFIG_FILE;

    public static void save(String key, String value) throws RuntimeException {
        JSONObject all = getAll();

        all.put(key, value);

        try (FileWriter file = new FileWriter(CONFIG_FILE)) {
            file.write(all.toJSONString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object get(String key) throws RuntimeException {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(CONFIG_FILE)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            return jsonObject.get(key);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Fuel> getFuels() {
        ArrayList<Fuel> fuelsList = new ArrayList<>();

        String fuels = (String) get("fuels");

        if (fuels == null) {
            return fuelsList;
        }

        JSONParser parser = new JSONParser();
        JSONArray parsedFuels;

        try {
            parsedFuels = (JSONArray) parser.parse(fuels);
        } catch (ParseException e) {
            return fuelsList;
        }

        for (Object fuelObj : parsedFuels) {
            JSONObject fuelJson = (JSONObject) fuelObj;

            IFuelType type = fuelJson.get("type").equals(FuelType.ETHANOL) ? FuelType.ETHANOL : FuelType.GASOLINE;

            Fuel fuel = new Fuel(type, (double) fuelJson.get("price"));
            fuelsList.add(fuel);
        }

        return fuelsList;
    }

    public static ArrayList<Tank> getTanks() {
        ArrayList<Tank> tanksList = new ArrayList<>();

        String tanks = (String) get("tanks");

        if (tanks == null) {
            return tanksList;
        }

        JSONParser parser = new JSONParser();
        JSONArray parsedTanks;

        try {
            parsedTanks = (JSONArray) parser.parse(tanks);
        } catch (ParseException e) {
            return tanksList;
        }

        for (Object tankObj : parsedTanks) {
            JSONObject tankJson = (JSONObject) tankObj;

            IFuelType type = tankJson.get("fuel").equals(FuelType.ETHANOL) ? FuelType.ETHANOL : FuelType.GASOLINE;

            Tank tank = new Tank(GasStation.getFuel(type), (double) tankJson.get("capacity"));
            tanksList.add(tank);
        }

        return tanksList;
    }

    public static ArrayList<GasPump> getGasPumps() {
        ArrayList<GasPump> gasPumpsList = new ArrayList<>();

        String gasPumps = (String) get("gasPumps");

        if (gasPumps == null) {
            return gasPumpsList;
        }

        JSONParser parser = new JSONParser();
        JSONArray parsedGasPumps;

        try {
            parsedGasPumps = (JSONArray) parser.parse(gasPumps);
        } catch (ParseException e) {
            return gasPumpsList;
        }

        for (Object gasPumpObj : parsedGasPumps) {
            JSONObject gasPumpJson = (JSONObject) gasPumpObj;

            IFuelType type = gasPumpJson.get("fuel").equals(FuelType.GASOLINE) ? FuelType.GASOLINE : FuelType.ETHANOL;
            long id = (long) gasPumpJson.get("id");

            GasPump gasPump = new GasPump((int) id, GasStation.getFuel(type));
            gasPumpsList.add(gasPump);
        }

        return gasPumpsList;
    }

    public static JSONObject getAll() throws RuntimeException {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(CONFIG_FILE)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
