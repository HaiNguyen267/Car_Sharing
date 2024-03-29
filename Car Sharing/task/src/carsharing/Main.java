package carsharing;


import carsharing.service.MainMenu;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String dbName = parseDBName(args);
            DatabaseManager.initDatabase(dbName);
            MainMenu.getInstance().runMainMenu();
            DatabaseManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseDBName(String[] args) {
        List<String> list = Arrays.asList(args);

        if (list.contains("-databaseFileName")) {
            int index = list.indexOf("-databaseFileName");
            return list.get(index + 1);
        } else {
            return "myDatabase";
        }
    }
}
