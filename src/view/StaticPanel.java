package view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Opration;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class StaticPanel extends JPanel {

	JButton up;
	JButton down;
	JButton left;
	JButton right;
	JButton start;
	JButton two_player;
	// 声音控制
	private JCheckBox voiceCtrl;

	JLabel location1;
	JLabel location2;
	JTextField locationText1;
	JTextField locationText2;

	private static final long serialVersionUID = 1L;

	StaticPanel(Opration opration) {

		up = opration.up;
		down = opration.down;
		left = opration.left;
		right = opration.right;
		start = opration.start;
		two_player = opration.two_player;
		voiceCtrl = opration.voiceCtrl;
		location1 = opration.location1;
		location2 = opration.location2;
		locationText1 = opration.locationText1;
		locationText2 = opration.locationText2;

		setBounds(0, 0, 360, 600);
		setLayout(null);
		setOpaque(false);
		setstart();
		settwo_player();
		setvoiceCtrl();
		setLocation();
		start.setBounds(233, 420, 100, 40);
		two_player.setBounds(233, 460, 100, 40);
		up.setBounds(266, 340, 33, 33);
		left.setBounds(233, 373, 33, 33);
		down.setBounds(266, 373, 33, 33);
		right.setBounds(299, 373, 33, 33);
		voiceCtrl.setBounds(233, 510, 100, 30);
		location1.setBounds(15, 500, 30, 20);
		location2.setBounds(15, 525, 30, 20);
		locationText1.setBounds(55, 500, 100, 20);
		locationText2.setBounds(55, 525, 100, 20);
		add(up);
		add(down);
		add(left);
		add(right);
		add(start);
		add(two_player);
		add(voiceCtrl);
		add(location1);
		add(location2);
		add(locationText1);
		add(locationText2);
	}

	/*
	 * 设置按钮
	 */
	private void setstart() {
		start.setContentAreaFilled(false);
		start.setFocusPainted(false);
		start.setFont(new Font("华文新魏", Font.PLAIN, 25));
		start.setForeground(Color.white);
		start.setFocusable(false);
	}

	private void settwo_player() {
		two_player.setContentAreaFilled(false);
		two_player.setFocusPainted(false);
		two_player.setFont(new Font("华文新魏", Font.PLAIN, 16));
		two_player.setForeground(Color.white);
		two_player.setFocusable(false);
	}

	private void setvoiceCtrl() {
		voiceCtrl.setOpaque(false);
		voiceCtrl.setFocusable(false);
	}

	private void setLocation() {
		location1.setFocusable(false);
		location2.setFocusable(false);
//		locationText1.setFocusable(false);
//		locationText2.setFocusable(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0, 0, 0, 30));
		// 主屏
		// g.fillRect(15, 30, 200, 360);
		g.fillRect(15, 100, 200, 360);
		// 排名区
		// g.fillRect(15, 405, 200, 130);
		// 右排版
		g.fillRect(233, 10, 105, 490);
		// 上边框
		g.fillRect(15, 15, 200, 70);

		g.setColor(new Color(2, 2, 2, 30));
		// 得分区
		g.fillRect(15, 15, 100, 70);
		// 提示区
		g.fillRect(233, 200, 100, 125);
		// 操作区
		g.fillRect(233, 330, 100, 85);
		// 边框
		g.setColor(Color.white);
		((Graphics2D) g).setStroke(new BasicStroke(3L));
		g.drawRect(13, 98, 204, 364);

		((Graphics2D) g).setStroke(new BasicStroke(1L));
		g.setFont(new Font("黑体", Font.PLAIN, 22));
		g.setColor(Color.DARK_GRAY);
		g.drawString("得分", 20, 43);
		g.drawString("下一个", 245, 235);
	}
}
