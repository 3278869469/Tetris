package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

import model.GameData;
import model.compertitor;

//客户端
public class Server {
	public Server(ServerSocket serverSocket, GameData gameData, compertitor compData, int P) {
		try {
			gameData.init();
			gameData.state = 2;
			serverSocket = new ServerSocket(P); 
			System.out.println("waiting client....");
			Socket socket = serverSocket.accept(); 	//连接socket
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());	
			compData.isOline = true;
			gameData.state = 1;
			System.out.println("client connected.\n"); 
			while (true) {	
				//服务端先读后写
				int[][] blockMap = (int[][])ois.readObject();  
				int score = (int) ois.readInt();
				boolean loseGame = (boolean) ois.readBoolean();
				compData.setOtherWholeBlockMap(blockMap, score);	
				 
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

				if (loseGame) {
					System.out.println(score);
					gameData.state = 2;
					JOptionPane.showMessageDialog(null,"You Win!", "游戏结束",  JOptionPane.PLAIN_MESSAGE);
					gameData.init();
					compData.Init();
					serverSocket.close();
					return;
				}
				if (gameData.isdead()) {
					gameData.init();
					compData.Init();
					serverSocket.close();
					return;
				}
				
				Thread.sleep(1000);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
}
