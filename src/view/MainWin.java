package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.Opration;
import model.GameData;
import model.compertitor;

import java.awt.*;

public class MainWin extends JFrame {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	private GamePanel gamePanel;
	private Container mainpane;
	private GameData gameData;
	private compertitor compData;
	StaticPanel staticPanel;
	ScoreNext scoreNext;
	comPanel companel;
	
	public MainWin(Opration opration, GameData gameData, compertitor compData){
		this.gameData = gameData;
		this.compData = compData;
		mainpane = getLayeredPane();
		setBounds(650, 100, 360, 600);
		setResizable(false);
		//自由布局
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		
		//设置背景
		setBackground();
		
		//绘制区域
		staticPanel = new StaticPanel(opration);
		mainpane.add(staticPanel);
		//添加游戏方块
		setGamePanel();
		//添加分数分数提示
		setScoreNext();
		setcomPanel();
		//设置图层顺序
		setZindex();
		//被选中的权力
		setFocusable(true);
	}
	
	//添加分数提示
	private void setScoreNext() {
		scoreNext = new ScoreNext(gameData);
		mainpane.add(scoreNext);
	}
	
	//联网界面
	private void setcomPanel() {
		companel = new comPanel(compData);
		mainpane.add(companel);
	}

	private void setZindex() {
		mainpane.setComponentZOrder(staticPanel, 1);
		mainpane.setComponentZOrder(gamePanel, 0);
		mainpane.setComponentZOrder(scoreNext, 0);
		mainpane.setComponentZOrder(companel, 0);
	}

	/*
	 * 设置背景
	 */
	void setBackground() {
		ImageIcon imgic = new ImageIcon("img/bg.png");
		JLabel jl = new JLabel(imgic);
		jl.setBounds(0,0,360,600);
		getContentPane().add(jl);
	}
	
	/*
	 * 添加游戏方块
	 */
	void setGamePanel() {
		gamePanel = new GamePanel(gameData);
		mainpane.add(gamePanel);
	}
	
	/*
	 * 获取游戏区
	 */
	public GamePanel getGamePanle() {
		return gamePanel;
	}
	/*
	 * 获取分数提示区
	 */
	public ScoreNext getScoreNext() {
		return scoreNext;
	}
	public comPanel getcompanel() {
		return companel;
	}
	/*
	 * 开关的获取
	 */
	public JButton getStart() {
		return staticPanel.start;
	}

	/*
	 * 弹窗
	 */
	public void alert(String string) {
		JOptionPane.showMessageDialog(this, string);	
	}
	
}
