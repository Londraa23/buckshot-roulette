package Managers;

import Utils.TimeUtils;

import java.io.*;

public class EconomyManager {
    public static double initialCash = 0;
    public static double cash;
    static File file = new File("cash.txt");

    public static void initialize() {

        // Create file if it doesn't exist
        if (!file.exists()) {
            saveCash(initialCash);
            System.out.println("Creating files...");
            TimeUtils.wait(1000);
        }

        // Read file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // Load current money
            System.out.println("Loading game...");
            String linea = reader.readLine();
            cash = (linea != null) ? Double.parseDouble(linea.trim()) : initialCash;

        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file " + file.getName() + ": " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in " + file.getName() + ": " + e.getMessage());
            cash = initialCash;
        } catch (IOException e) {
            System.out.println("There was an exception while reading " + file.getName() + ": " + e.getMessage());
            return;
        }

        TimeUtils.wait(1000);
        System.out.println("The game has been successfully been set up. Enjoy!");
        TimeUtils.wait(1000);
    }

    private static void saveCash(double value) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(Double.toString(value));
        } catch (IOException e) {
            System.out.println("There was an exception while writing " + file.getName() + ": " + e.getMessage());
        }
    }

    public static double getCash(){
        return cash;
    }

    public static double setCash(double newCash){
        cash = newCash;
        saveCash(cash);
        return cash;
    }

    public static double addCash(double amount){
        return setCash(cash + amount);
    }

    public static double removeCash(double amount){
        return setCash(cash - amount);
    }
}
