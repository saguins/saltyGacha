package gacha;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import gui.GUIWindow;
import pool.Pool;
import pool.domain.PoolModel;


public class Gacha {

    public static List<PoolModel> characters = new ArrayList<>();
    private static Integer counter = 0;

    public static void main(String[] args) {
    	GUIWindow window = new GUIWindow();
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
    }

    public static List<PoolModel> huntMenu(){
        Pool gacha = new Pool();
        List<PoolModel> characterList = gacha.pickTen();
        characters.addAll(characterList);
        counterResult();
        increaseCounter();
        
        return characterList;
    }

    private static void increaseCounter(){
    	counter++;
    }

    public static String counterResult(){
        return ("You have retried " + counter + " time(s).");
    }


}
