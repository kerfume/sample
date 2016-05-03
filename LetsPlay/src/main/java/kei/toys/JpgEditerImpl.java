package kei.toys;

import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.imageio.ImageIO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kei.Interface.JpgEditer;

/**
* jpgファイルを読み込んで編集するクラス
* @author kerfume
*
*/
public class JpgEditerImpl implements JpgEditer{
	
	private BufferedImage buimg = null;
	private File file;
	Logger logger = Logger.getLogger (App.class.getName ());
	
	public JpgEditerImpl(){
		DOMConfigurator.configure("deploy_res/log4j_common.xml");
	}
	/**
	 * 編集_文字追記とか？
	 * @return 0:正常
	 */
	public int editJpg() {	
		
		try{
			buimg = ImageIO.read(file);
			
			System.out.println(buimg.getWidth()+":"+buimg.getHeight());
			
			Graphics2D grph = buimg.createGraphics();
			
			grph.setColor(Color.RED);
			grph.drawString("Hello!!",0,10);
			
			for(int i = 0 ; i < 100 ; i += 2){
				grph.drawLine(0, 20 + i, 329, 25 + i);
				grph.drawLine(0, 131 + i, 329, 126 + i);
			}
			
			grph = null;
			ImageIO.write(buimg, "jpeg", file);
			
			buimg = null;
			
		}catch(Exception e){
			logger.error(e.getMessage());
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * 画像初期化処理
	 * 
	 */
	public void format(){
		try{
			//ApplicationContext context = new ClassPathXmlApplicationContext("MainContext.xml");
			//Properties p = (Properties) context.getBean("myProperty");
			System.out.println(file.getAbsolutePath());
			System.out.println();
			buimg = ImageIO.read(file);
			Graphics2D grph = buimg.createGraphics();
			
			grph.setBackground(Color.WHITE);
			grph.clearRect(0, 0, buimg.getWidth(), buimg.getHeight());
			
			grph = null;
			ImageIO.write(buimg, "ping", file);
			
			buimg = null;
			
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 終了処理
	 * 
	 */
	public void close(){
		buimg = null;
		file = null;
	}
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}
