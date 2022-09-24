package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

import model.GameData;
import model.compertitor;


//服务端
public class Client {
	public Client(Socket socket,GameData gameData,compertitor compData, String IP, int P) {
		try {
			gameData.init();
			gameData.state = 2;
			socket = new Socket(IP, P);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
			System.out.println("connected."); 
			compData.isOline = true;
			gameData.state = 1;
			while (true) {  
				//客户端先写后读
				int[][] bMap = new int[10][20];
				for (int i = 0; i < bMap.length; i++) {
					for (int j = 0; j < bMap[i].length; j++) {
						bMap[i][j] = gameData.existBlocks[i][j];
					}
				}  
				oos.writeObject(bMap); 
				oos.writeInt(gameData.score);
				oos.writeBoolean(gameData.isdead());
				oos.flush();
				
				int[][] blockMap = (int[][])ois.readObject(); 
				int score = (int) ois.readInt();
				boolean loseGame = (boolean)ois.readBoolean();
				compData.setOtherWholeBlockMap(blockMap, score);
				if (loseGame) {
					gameData.state = 2;
					JOptionPane.showMessageDialog(null,"You Win!", "游戏结束",  JOptionPane.PLAIN_MESSAGE);
					gameData.init();
					compData.Init();
					socket.close();
					return;
				}
				
				if (gameData.isdead()) {
					gameData.init();
					compData.Init();
					socket.close();
					return;
				}
				Thread.sleep(1000);
			}
		} catch (IOException | InterruptedException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
}
