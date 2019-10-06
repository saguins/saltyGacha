package Gacha;

import Pool.Pool01;
import java.util.Arrays;
import java.util.Scanner;

public class Gacha01 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pool01 gacha = new Pool01();
        int gacha_counter = 0;
        System.out.println("Gacha Simulator");
        System.out.println("-----------------------");
        System.out.println(gacha.toString());
        System.out.println("-----------------------");
        System.out.println("Try again? (enter anything to continue / n to quit)");
        String key = input.nextLine();
        while (!key.equals("n")) {
            gacha = new Pool01();
            gacha_counter++;
            System.out.println("-----------------------");
            System.out.println(gacha.toString());
            System.out.println("-----------------------");
            if(gacha_counter == 1){
                System.out.println("You have retried " + gacha_counter + " time.");
            }
            else{
                System.out.println("You have retried " + gacha_counter + " times.");
            }
            System.out.println("Try again? (enter anything to continue / n to quit)");
            key = input.nextLine();
        }
        System.out.println("-----------------------");
        System.out.println("Goodbye!");
    }

}
