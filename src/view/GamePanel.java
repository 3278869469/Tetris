package view;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.GameData;

import java.awt.*;

public class GamePanel extends JPanel{

	private GameData gameData;
	private static final long serialVersionUID = 1L;
	
	GamePanel(GameData gameData){
		this.gameData = gameData;
		setOpaque(false);
		setBounds(15,100,200,360);
	}
	
	public void paintComponent(Graphics g) {
		//话出正在下落的格子
		for(Point point:gameData.block.points) {
			g.setColor(gameData.color[gameData.current]);
			g.fillRect((point.x+gameData.x)*20, (point.y+gameData.y)*20, 20, 20);
			g.drawImage(new ImageIcon("./img/mask1.png").getImage(), (point.x+gameData.x)*20, (point.y+gameData.y)*20, 20, 20, null);
		}
		//画出已经落地（已存在）的格子
		for(int i=19; i>=2; i--) {
			for(int j=0; j<10; j++) {
				 if(gameData.existBlocks[j][i] != 0) {
					 g.setColor(gameData.color[gameData.existBlocks[j][i]-1]);
					 g.fillRect(j*20, (i-2)*20, 20, 20);
					 g.drawImage(new ImageIcon("./img/mask1.png").getImage(), j*20, (i-2)*20, 20, 20, null);
				 }
			}
		}
	}
	
}
