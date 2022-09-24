package model;

import java.awt.Point;
import java.util.Random;
import java.awt.Color;

public class GameData {
	public blocks block;
	public blocks[] BLOCKS = new blocks[] {
			/*
			 * xs[] , ys[]; 俄罗斯方块的7种形态
			 */
			new blocks(new int[] { -1, 0, 1, 1 }, new int[] { 0, 0, 0, 1 }),
			new blocks(new int[] { -1, 0, 1, 2 }, new int[] { 0, 0, 0, 0 }),
			new blocks(new int[] { -1, -1, 0, 1 }, new int[] { 0, 1, 0, 0 }),
			new blocks(new int[] { -1, 0, 0, 1 }, new int[] { 0, 0, 1, 1 }),
			new blocks(new int[] { -1, 0, 0, 1 }, new int[] { 1, 0, 1, 0 }),
			new blocks(new int[] { 0, 0, 1, 1 }, new int[] { 0, 1, 0, 1 }),
			new blocks(new int[] { -1, 0, 0, 1 }, new int[] { 0, 0, 1, 0 }), };
	int op = 100;
	public Color[] color = new Color[] { new Color(255, 0, 0, op), new Color(0, 255, 0, op), new Color(0, 0, 255, op),
			new Color(255, 255, 0, op), new Color(255, 0, 255, op), new Color(0, 255, 255, op),
			new Color(150, 150, 150, op), };
	
	// 按钮信息
	public String[] state_text = new String[] { "开始", "暂停", "继续", "重来" };
	
	//偏移量
	public int x;
	public int y;
	
	// 存放格子
	public int[][] existBlocks;
	// 存放删除格子的数组
	int[] deletnum;
	
	//随机因子
	Random random;
	
	//现在
	public int current;
	
	//下一个
	public int next;
	
	// 得分
	public int score;
	
	//游戏状态
	public int state;

	public GameData() {
		init();
	}

	public void init() {
		score = 0 ;
		random = new Random();
		next = random.nextInt(7);
		existBlocks = new int[10][20];
		initBlocks();
	}

	/*
	 * 移动方法
	 * isH是否水平移动，step移动的步数
	 */
	public boolean move(boolean isH, int step) {
		boolean isdelet = false;
		if (isH) {
			for (Point point : block.points) {
				if (point.x + x + step < 0 || point.x + x + step > 9
						|| existBlocks[point.x + x + step][point.y + y + 2] != 0)
					return false;
			}
			MusicPlayer.actionPlay();
			x += step;
		} else {
			for (Point point : block.points) {
				if (point.y + y + step > 17 || existBlocks[point.x + x][point.y + y + 2 + step] != 0) {
					sevaBlocks();
					isdelet = deletTest();
					if (isdelet) {
						deletLine();
					}
					if (isdead()) {
						//死亡方法
						state = 3;
					}
					//如果方块到底，新建一个Blocks
					initBlocks();
					return true;
				}
			}
			//方块下落
			y += step;
		}
		//返回是否可以消行
		return isdelet;
	}

	private void initBlocks() {
		x = 4;
		y = -2;
		block = new blocks(BLOCKS[next]);
		current = next;
		next = random.nextInt(7);
		deletnum = new int[20];
	}

	/*
	 * 旋转方法
	 */
	public void rote() {
		for (Point point : block.points) {
			int _x = -point.y + x;
			int _y = point.x + y;
			if (_x < 0 || _x > 9) {
				return;
			}
			if (_y > 17 || _y < -2) {
				return;
			}
			if (existBlocks[_x][_y + 2] != 0) {
				return;
			}
			//正方形不旋转
			if (current == 5) {
				return;
			}
		}
		//旋转公式
		for (Point point : block.points) {
			int temp = point.x;
			point.x = -point.y;
			point.y = temp;
		}
		MusicPlayer.actionPlay();
	}

	/*
	 * 保存
	 */
	void sevaBlocks() {
		MusicPlayer.reachPlay();
		for (Point point : block.points) {
			existBlocks[point.x + x][point.y + y + 2] = current + 1;
		}
	}

	/*
	 * 检测消行
	 */
	boolean deletTest() {
		boolean isDelet = false;
		boolean isEmpty;
		for (int i = 19; i >= 2; i--) {
			isEmpty = false;
			for (int j = 0; j < 10; j++) {
				if (existBlocks[j][i] == 0) {
					isEmpty = true;
					break;
				}
			}
			if (!isEmpty) {
				isDelet = true;
				deletnum[i - 1] = deletnum[i] + 1;
			} else {
				deletnum[i - 1] = deletnum[i];
			}
		}
		return isDelet;
	}

	/*
	 * 消行
	 */
	void deletLine() {
		for (int i = 19; i >= 2; i--) {
			for (int j = 0; j < 10; j++) {
				existBlocks[j][i + deletnum[i]] = existBlocks[j][i];
			}
		}
		MusicPlayer.scorePlay();
		score += deletnum[2] * 10;
	}

	/*
	 * 死亡判断
	 */
	public boolean isdead() {
		for (int j = 0; j < 10; j++) {
			if (existBlocks[j][2] != 0)
				return true;
		}
		return false;
	}

	public String getScore() {
		return "" + score;
	}
}
