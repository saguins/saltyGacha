package gacha;

import pool.Pool;
import pool.PoolResult;
import pool.domain.PoolModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Gacha {

    private static List<PoolModel> characters = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String key = "";
    private static Integer counter = 0;

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
            List<PoolModel> characterList = gacha.pickTen();
            characters.addAll(characterList);
            System.out.println("-----------------------");
            System.out.println("Result:");
            for (PoolModel prize : characterList) {
                System.out.println(prize.getParsedTier() + " " + prize.getName());
            }
            System.out.println("-----------------------");
            counterResult();
            System.out.println("Try again? (enter anything to continue / n to quit)");
            key = scanner.nextLine();
            increaseCounter();
        }
    }

    private static void resultMenu(){
        PoolResult result = new PoolResult(characters);
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
                    result.getResultListOfCharacters();
                    emptyKey();
                    break;
                case "f":
                    System.out.println("Congratulations!");
                    return;
                default:
                    System.out.println("Press key: ");
                    System.out.println("1 - Percentage of rarity characters founded.");
                    System.out.println("2 - Quantity of characters by rarity.");
                    System.out.println("3 - Quantity, Name and Rarity of all founded characters.");
                    System.out.println("f - Finishes");
                    key = scanner.nextLine();
                    break;
            }
        }
    }

    private static void increaseCounter(){
        if (!"n".equals(key)){
            counter++;
        }
    }

    private static void counterResult(){
        if(counter == 1){
            System.out.println("You have retried " + counter + " time.");
            System.out.println("-----------------------");
        }
        else if (counter > 1){
            System.out.println("You have retried " + counter + " times.");
            System.out.println("-----------------------");
        }
    }

    private static void emptyKey(){
        key = "";
    }

}
