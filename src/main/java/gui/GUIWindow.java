package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gacha.Gacha;
import pool.PoolResult;
import pool.domain.PoolModel;

@SuppressWarnings("serial")
public class GUIWindow extends JPanel{
	
	/**Window Dimensions*/
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	/**Buttons*/
	private JButton rollButton;
	private JButton percentButton;
	private JButton rarityButton;
	private JButton quantityButton;
	
	/**Labels*/
	private JLabel title;
	
	/**TextArea*/
	private JTextArea infoArea;
	
	public GUIWindow(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		
		/**Setting layout*/
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); 
		
		/**Setting the button handler*/
		ButtonHandler buttonHandler = new ButtonHandler();
		
		/**TextArea and ScrollPanel*/
		Box box = Box.createHorizontalBox();
		infoArea = new JTextArea("", 100, 35);
		infoArea.setFont(new Font("Arial", Font.PLAIN, 12));
		infoArea.setEditable(false);
		for (PoolModel prize : Gacha.huntMenu()) {
			infoArea.append(String.format("%s%n", prize.getParsedTier() + " " + prize.getName()));
        }
		infoArea.append(Gacha.counterResult());
		JScrollPane scroll = new JScrollPane(infoArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		box.add(scroll);
		
		/**Title*/
		title = new JLabel("<html>Gacha Simulator<br/>-----------------------</html>");
		title.setFont(new Font("Arial", Font.PLAIN, 24));
		
		/**Buttons*/
		rollButton = new JButton("Roll");
		percentButton = new JButton("Percentage of rarity characters founded");
		rarityButton = new JButton("Quantity of characters by rarity.");
		quantityButton = new JButton("Quantity, Name and Rarity of all founded characters.");
		rollButton.addActionListener(buttonHandler);
		percentButton.addActionListener(buttonHandler);
		rarityButton.addActionListener(buttonHandler);
		quantityButton.addActionListener(buttonHandler);
		
		/**Title*/
		c.fill = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		add(title, c);

		/**Text Area*/
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 200; 
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		add(box, c);
		
		/**Buttons*/
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; 
		c.gridwidth = 0;
		c.gridx = 0;
		c.gridy = 2;
		add(rollButton, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(percentButton, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(rarityButton, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(quantityButton, c);
	}
	
	private class ButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			//Clearing the text area
			infoArea.setText("");
			if(event.getSource() == rollButton){
				for (PoolModel prize : Gacha.huntMenu()) {
					infoArea.append(String.format("%s%n", prize.getParsedTier() + " " + prize.getName()));
	            }
				infoArea.append(Gacha.counterResult());
			}
			else if(event.getSource() == percentButton){
		        PoolResult result = new PoolResult(Gacha.characters);
		        infoArea.append(result.getResultInPercentage());
			}
			else if(event.getSource() == rarityButton){
		        PoolResult result = new PoolResult(Gacha.characters);
		        infoArea.append(result.getResultInQuantity());
			}
			else if(event.getSource() == quantityButton){
		        PoolResult result = new PoolResult(Gacha.characters);
		        result.getResultListOfCharacters().forEach((n) -> 
		        infoArea.append(String.format("%s%n", n)));
			}
		}
	}
}
