package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.compertitor;

public class comPanel extends JPanel {
	public compertitor compData;
	public boolean isOline;

	comPanel(compertitor compData) {
		isOline = false;
		this.compData = compData;
		setOpaque(false);
		setBounds(0, 0, 360, 600);
	}

	public void paintComponent(Graphics g) {
		if (compData.isOline) {
			g.setFont(new Font("黑体", Font.PLAIN, 23));
			g.drawString("对手", 160, 40);
			g.drawString(compData.getScore(), 170, 75);
			g.setColor(Color.red);
			g.drawString("VS", 103, 40);
			g.setColor(Color.white);
			((Graphics2D)g).setStroke(new BasicStroke(2L));
			g.drawRect(233, 10, 102, 182);
			for(int i=19; i>=2; i--) {
				for(int j=0; j<10; j++) {
					 if(compData.block[j][i] != 0) {
						 g.setColor(new Color(0,0,0,70));
						 g.fillRect(j*10+233, (i-2)*10+10, 10, 10);
						 g.drawImage(new ImageIcon("./img/mask1.png").getImage(), j*10+233, (i-2)*10+10, 10, 10, null);
					 }
				}
			}
		}
	}

}
