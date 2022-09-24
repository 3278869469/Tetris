package model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MusicPlayer {
	static volatile boolean running = false;
    static volatile boolean turnOn =false;
    private static String bgmUrl="sound\\bgm.wav";
    private static String moveUrl="sound\\MOVE.WAV";
    private static String reachUrl="sound\\REACH.WAV";
    private static String scoreUrl="sound\\SCORE.WAV";
   
    private static AudioClip bgmAudioClip;
    private static AudioClip actionAudioClip;
    private static AudioClip reachAudioClip;
    private static AudioClip scoreAudioClip;
    
    @SuppressWarnings("deprecation")
	public static void bgmPlay(){
    	if(!turnOn)
    		return;
    	if(bgmAudioClip==null){
    		try {
                URL cb;
                File file = new File(bgmUrl);
                cb = file.toURL();
                bgmAudioClip = Applet.newAudioClip(cb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{	
    		bgmAudioClip.loop();
    		running = true;
    		//System.out.println("bgm");
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    	
    }
    @SuppressWarnings("deprecation")
	public static void bgmStop(){
    	try{
            bgmAudioClip.stop();
            running =false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("deprecation")
	public static void actionPlay(){
    	if(!turnOn)
    		return;
    	if(reachAudioClip==null){
    		try {
                URL cb;
                File file = new File(moveUrl);
                cb = file.toURL();
                actionAudioClip = Applet.newAudioClip(cb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		actionAudioClip.play();
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    }
    
    @SuppressWarnings("deprecation")
	public static void reachPlay(){
    	if(!turnOn)
    		return;
    	if(reachAudioClip==null){
    		try {
                URL cb;
                File file = new File(reachUrl);
                cb = file.toURL();
                reachAudioClip = Applet.newAudioClip(cb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		reachAudioClip.play();
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    }
    
    @SuppressWarnings("deprecation")
	public static void scorePlay(){
    	if(!turnOn)
    		return;
    	if(scoreAudioClip==null){
    		try {
                URL cb;
                File file = new File(scoreUrl);
                cb = file.toURL();
                scoreAudioClip = Applet.newAudioClip(cb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		scoreAudioClip.play();
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    }
    
    /**
     * 返回是否正在运行
     */
    public static boolean isRunning(){
    	
        return running;
    }
    /**
     * 返回是否打开了音乐开关
     * 没打开表示静音状态
     */
    public static boolean isturnOn(){
    	return turnOn;
    }
    /**
     * 设置是否静音
     * @param turn
     */
    public static void setturnOn(Boolean turn){
    	turnOn=turn;
    }
    
}
