package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.GameData;
import model.MusicPlayer;
import view.ImgButton;
import view.MainWin;
import socket.Client;
import socket.Server;
import model.compertitor;

public class Opration {

	public opButton up;
	public opButton down;
	public opButton left;
	public opButton right;
	public JButton start;
	public JButton two_player;
	public JCheckBox voiceCtrl;
	public String selectedValue;
	public JLabel location1 = new JLabel("IP:");
	public JLabel location2 = new JLabel("端口:");
	public JTextField locationText1 = new JTextField();
	public JTextField locationText2 = new JTextField();
	String IP;
	int P;
	
	ServerSocket serverSocket;
	Socket socket;
	private Thread online;
	
	private MainWin mainwin;
	private GameData gameData;
	private compertitor CompData;

	abstract class opButton extends ImgButton {
		private static final long serialVersionUID = 1L;

		public opButton(ImageIcon imageIcon) {
			super(imageIcon);
			// TODO Auto-generated constructor stub
		}

		@Override // 重写
		public void onClick() {
			if (gameData.state == 1) {
				doClick();
			}
		}

		abstract public void doClick();
	}

	Opration() {
		up = new opButton(new ImageIcon("img/up.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void doClick() {
				gameData.rote();
				mainwin.getGamePanle().repaint();
				mainwin.getScoreNext().repaint();
				mainwin.getcompanel().repaint();
				start.setText(gameData.state_text[gameData.state]);
			}
		};
		down = new opButton(new ImageIcon("img/down.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void doClick() {
				if (gameData.move(false, 1)) {
					mainwin.getGamePanle().repaint();
				}
				mainwin.getScoreNext().repaint();
				mainwin.getcompanel().repaint();
				start.setText(gameData.state_text[gameData.state]);
			}
		};

		left = new opButton(new ImageIcon("img/left.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void doClick() {
				gameData.move(true, -1);
				mainwin.getGamePanle().repaint();
				mainwin.getScoreNext().repaint();
				mainwin.getcompanel().repaint();
				start.setText(gameData.state_text[gameData.state]);
			}
		};
		right = new opButton(new ImageIcon("img/right.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void doClick() {
				gameData.move(true, 1);
				mainwin.getGamePanle().repaint();
				mainwin.getScoreNext().repaint();
				mainwin.getcompanel().repaint();
				start.setText(gameData.state_text[gameData.state]);
			}

		};
		
		start = new JButton("开始");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameData.state == 1) {
					if (MusicPlayer.isRunning()) {
						MusicPlayer.bgmStop();
					}
					gameData.state = 2;
				} else {
					// MusicPlayer.scorePlay();
					if (!MusicPlayer.isRunning()) {
						MusicPlayer.bgmPlay();
					}
//					locationText1.setFocusable(false); 
//			    	locationText2.setFocusable(false);
					gameData.state = 1;
				}
				start.setText(gameData.state_text[gameData.state]);
			}
		});

		two_player = new JButton("双人游戏");
		two_player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IP = locationText1.getText();
		    	String s2 = locationText2.getText();
		    	P = Integer.parseInt(s2);
		    	locationText1.setFocusable(false); 
		    	locationText2.setFocusable(false); 
				Object[] possibleValues = { "服务端", "客户端" };
				selectedValue = (String) JOptionPane.showInputDialog(null, "Choose", "联网",
						JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
				if (selectedValue == null)
					return;
				online = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (selectedValue.equals("服务端"))
							new Server(serverSocket, gameData, CompData, P);
						else new Client(socket, gameData, CompData, IP, P); 
					}
				});
				online.start(); 
				
			}
		});

		voiceCtrl = new JCheckBox("声音");
		voiceCtrl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (voiceCtrl.isSelected()) {
					MusicPlayer.setturnOn(true);
					if (!MusicPlayer.isRunning()) {
						MusicPlayer.bgmPlay();
					}
				} else {
					MusicPlayer.setturnOn(false);
					if (MusicPlayer.isRunning()) {
						MusicPlayer.bgmStop();
					}
				}
			}
		});

	}

	/*
	 * 关联视图 mainwin
	 */
	public void setWin(MainWin mainwin) {
		this.mainwin = mainwin;
		this.mainwin.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					down.onClick();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					left.onClick();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					right.onClick();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					up.onClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

		});
	}

	public void setData(GameData gameData) {
		this.gameData = gameData;
	}
	
	public void setCompData(compertitor CompData) {
		this.CompData = CompData;
	}
}
