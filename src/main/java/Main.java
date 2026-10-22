import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int round = 1;
    public static double cash;
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Boolean> chamber = new ArrayList();
    public static int[][] ROUND_CONFIG = {
            {2, 4, 2}, // hearts, bullets and live rounds.
            {4, 6, 3},
            {5, 8, 4}
    };
    public static int playerHearts = ROUND_CONFIG[round - 1][0];
    public static int computerHearts = ROUND_CONFIG[round - 1][0];
    public static boolean playerTurn = true;
    public static Random ran = new Random();

    public static void main(String[] args){

        mainMenu();

        while(round <= 3) {
            System.out.println();
            System.out.println("════ ROUND " + round + " ════");
            playerHearts = ROUND_CONFIG[round - 1][0];
            computerHearts = ROUND_CONFIG[round - 1][0];
            playerTurn = true;
            chamber.clear();

            while (playerHearts != 0 && computerHearts != 0) {
                System.out.println();
                System.out.println("You have " + playerHearts + " hearts.");
                System.out.println("Computer has " + computerHearts + " hearts.");
                System.out.println();

                if (playerTurn) {
                    System.out.println("════ PLAYERS TURN ════");
                    shoot(playersShootingChoice());
                }
                if (!playerTurn && computerHearts != 0) {
                    System.out.println();
                    System.out.println("════ COMPUTERS TURN ════");
                    System.out.println("Making a decision...");
                    wait(3000);
                    shoot(computersShootingChoice());
                }
            }

            if (playerHearts == 0) {
                System.out.println("════ YOU DIED ════");
                System.out.println();
                System.out.println("You died on round " + round + ".");
                System.out.println("You won " + (round - 1) + " rounds.");
                return;
            }

            if (round == 3) {
                double cashWon = ran.nextDouble(200, 1000);
                System.out.println("════ YOU WON THE GAME ════");
                System.out.println();
                System.out.println("Woo oho! Congratulations.");
                System.out.println("You won another round and also $" + cashWon + " in cash.");
                cash += cashWon;
                round++;
            } else {
                System.out.println();
                System.out.println("════ YOU WON ROUND " + round + " ════");
                System.out.println("You just won a round!");
                round++;
            }

        }

    }
    public static void generateChamber(ArrayList<Boolean> chamber) {
        chamber.clear();
        int pairs = ROUND_CONFIG[round - 1][2];
        for (int i = 0; i < pairs; i++) {
            chamber.add(true);
            chamber.add(false);
        }
        Collections.shuffle(chamber);
    }
    public static boolean playersShootingChoice() {
        while (true) {
            System.out.println("Who do you want to shoot?");
            System.out.println();
            System.out.println("1. Yourself");
            System.out.println("2. Enemy");
            System.out.println();
            System.out.print("Option: ");

            if (!scanner.hasNextInt()) {
                scanner.next(); // descarta lo que no sea un número
                System.out.println("Please enter 1 or 2.\n");
                continue;
            }

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> { return true; }
                case 2 -> { return false; }
                default -> System.out.println("Please enter 1 or 2.\n");
            }
        }
    }
    public static boolean computersShootingChoice(){
        return ran.nextBoolean();
    }
    public static void shoot(boolean shootSelf) {
        if(chamber.isEmpty()){
            generateChamber(chamber);
            System.out.println("Reloading the shootgun...");
            wait(2000);
        }

        boolean live = chamber.removeFirst();

        if (live) {
            if (playerTurn) {
                if (shootSelf) {
                    System.out.println("You just shot yourself.");
                    System.out.println("You lost one heart.");
                    playerHearts--;
                } else {
                    System.out.println("You just shot the computer.");
                    System.out.println("Computer lost one heart.");
                    computerHearts--;
                }
                playerTurn = false;
            } else {
                if (shootSelf) {
                    System.out.println("Computer just shot himself.");
                    System.out.println("Computer lost one heart.");
                    computerHearts--;
                } else {
                    System.out.println("Computer shot you.");
                    System.out.println("You lost one heart.");
                    playerHearts--;
                }
                playerTurn = true;
            }
        } else {
            if (playerTurn) {
                if (shootSelf) {
                    System.out.println("It was blank. You can shoot again.");
                } else {
                    System.out.println("It was blank. You lost your opportunity.");
                    playerTurn = false;
                }
            } else {
                if (shootSelf) {
                    System.out.println("Computer shot himself. It was blank. Computer shoots again.");
                } else {
                    System.out.println("Computer shot you. It was blank. It's your turn.");
                    playerTurn = true;
                }
            }
        }
    }
    public static void wait(int ms){
        try{
           Thread.sleep(ms);
        } catch (InterruptedException e){
            System.out.println("There was an error on wait's method" + e.getMessage());
        }
    }
    public static void showHowToPlay() {
        System.out.println();
        System.out.println("════ HOW TO PLAY ════");
        System.out.println();
        System.out.println("- You face the Computer in 3 rounds. Each round, both of you start with the same hearts.");
        System.out.println("- The shotgun is loaded with LIVE and BLANK shells in a random order.");
        System.out.println("- On your turn you choose who to shoot: yourself or the Computer.");
        System.out.println("  * LIVE shell: whoever gets shot loses one heart, and the turn passes.");
        System.out.println("  * BLANK shell: nobody gets hurt.");
        System.out.println("    - If you shot yourself, you shoot again.");
        System.out.println("    - If you shot your enemy, the turn passes.");
        System.out.println("- When the shotgun is empty it gets reloaded.");
        System.out.println("- The first one to reach 0 hearts loses the round.");
        System.out.println("- Win all 3 rounds to win the game and earn cash!");
        System.out.println();
        System.out.println("Press ENTER to go back...");
        scanner.nextLine();
        scanner.nextLine();
    }
    public static void mainMenu() {
        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║       BUCKSHOT ROULETTE      ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.println("You've got: $" + cash);
            System.out.println();
            System.out.println("1. Play");
            System.out.println("2. How to play");
            System.out.println("3. About");
            System.out.println("4. Exit");
            System.out.println();

            int option;
            while (true) {
                System.out.print("Option: ");
                if (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Please enter a number between 1 and 4.\n");
                    continue;
                }
                option = scanner.nextInt();
                if (option >= 1 && option <= 4) break;
                System.out.println("Please enter a number between 1 and 4.\n");
            }

            switch (option) {
                case 1 -> { return; }
                case 2 -> showHowToPlay();
                case 3 -> showAbout();
                case 4 -> {
                    System.out.println("See you next time!");
                    System.exit(0);
                }
            }
        }
    }
    public static void showAbout() {
        System.out.println();
        System.out.println("════ ABOUT ════");
        System.out.println();
        System.out.println("Buckshot Roulette is a turn-based game of luck, memory and nerve.");
        System.out.println("You and the Computer take turns pulling the trigger of a shotgun");
        System.out.println("loaded with a mix of live and blank shells. You know how many of");
        System.out.println("each there are, but not the order. Count the shells, calculate the");
        System.out.println("odds and decide: is it worth the risk?");
        System.out.println();
        System.out.println("Press ENTER to go back...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
