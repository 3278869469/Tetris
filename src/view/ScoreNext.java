package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.GameData;

public class ScoreNext extends JPanel{

	private GameData gameData;
	int[] offset = new int[] {0, -10, 0, 0, 0, -10, 0 };
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ScoreNext(GameData gameData){
		this.gameData = gameData;
		setOpaque(false);
		setBounds(10, 30, 323, 600);
	}
	
	public void paintComponent(Graphics g) {
		g.setFont(new Font("黑体", Font.PLAIN, 23));
		g.drawString(gameData.getScore(), 15, 45);
		
		for(Point point:gameData.BLOCKS[gameData.next].points) {
			g.setColor(gameData.color[gameData.next]);
			g.fillRect((point.x)*20 + 35+225 + offset[gameData.next], (point.y)*20 + 150+75, 20, 20);
			g.drawImage(new ImageIcon("./img/mask1.png").getImage(), (point.x)*20 + 35+225 + offset[gameData.next], (point.y)*20 + 150+75, 20, 20, null);
		}
	}
	
}