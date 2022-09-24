package controller;

import view.MainWin;
import model.GameData;
import model.compertitor;

public class Main {
	public static void main(String[] args) {
		// 实例化操作
		Opration opration = new Opration();
		// 加载数据
		GameData gameData = new GameData();
		// 加载对手数据
		compertitor compData = new compertitor();
		// 数据与窗口相关联
		MainWin mainwin = new MainWin(opration, gameData, compData);
		// 窗口与操作区相关联
		opration.setWin(mainwin);
		// 数据区与窗口相关联
		opration.setData(gameData);
		opration.setCompData(compData);
		// 启用线程
		new AutoDown(gameData, mainwin).start();

		mainwin.setVisible(true);

	}
}

class AutoDown extends Thread {
	private GameData gameData;
	private MainWin mainwin;

	AutoDown(GameData gameData, MainWin mainwin) {
		this.gameData = gameData;
		this.mainwin = mainwin;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (gameData.state == 1) {
					if (gameData.move(false, 1)) {
						mainwin.getGamePanle().repaint();
					}
					mainwin.getGamePanle().repaint();
					mainwin.getScoreNext().repaint();
					mainwin.getcompanel().repaint();
					sleep(400);
				} else if (gameData.state == 3) {
					mainwin.alert("游戏结束，得分" + gameData.score);
					mainwin.getStart().setText(gameData.state_text[gameData.state]);
					gameData.state = 4;
					// 游戏数据清空
					gameData.init();
				} else {
					sleep(150);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
