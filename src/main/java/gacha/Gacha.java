package gacha;

import pool.Pool;
import pool.PoolResult;
import pool.domain.PoolModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Gacha {

    static List<PoolModel> myLoot = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String key = "";

    public static void main(String[] args) {
        System.out.println("main.java.Pool.Gacha Simulator");
        huntMenu();
        resultMenu();

        System.out.println("-----------------------");
        System.out.println();
        System.out.println("Goodbye!");
    }

    private static void huntMenu(){
        while (!key.equals("n")) {
            Pool gacha = new Pool();
            List<PoolModel> hunt = gacha.pickTen();
            myLoot.addAll(hunt);
            System.out.println("-----------------------");
            System.out.println("Result:");
            for (PoolModel prize : hunt) {
                System.out.println(prize.getParsedTier() + " " + prize.getName());
            }
            System.out.println("-----------------------");
            System.out.println("Try again? (enter anything to continue / n to quit)");
            key = scanner.nextLine();
        }
    }

    private static void resultMenu(){
        PoolResult result = new PoolResult(myLoot);
        result.getTotal();
        while (!"f".equals(key)) {
            System.out.println("-----------------------");
            switch (key){
                case "1":
                    result.getResultInPercentage();
                    emptyKey();
                    break;
                case "2":
                    result.getResultInQuantity();
                    emptyKey();
                    break;
                case "3":
                    result.getResultListOfItems();
                    emptyKey();
                    break;
                case "f":
                    System.out.println("Congratulations!");
                    return;
                default:
                    System.out.println("Press key: ");
                    System.out.println("1 - Percentage of rarity item founded.");
                    System.out.println("2 - Quantity of items by rarity.");
                    System.out.println("3 - Quantity, Name and Rarity of all founded items.");
                    System.out.println("f - Finishes");
                    key = scanner.nextLine();
                    break;
            }
        }
    }

    private static void emptyKey(){
        key = "";
    }

}
