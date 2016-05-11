package jp.kerfume.app.logic;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jp.kerfume.app.interf.*;
import jp.kerfume.app.bean.*;

import java.io.IOException;

/**
 * xmlファイルからbeanを生成するクラス
 * @author kei
 *
 */
public class MakeDtoImpl implements MakeDto{
	
	InDataBean idb;
	Logger logger;
	
	public MakeDtoImpl(){
		logger = Logger.getLogger (MakeDtoImpl.class.getName ());
		DOMConfigurator.configure("log4j_common.xml");
	}
	
	/**
	 * @param file 取り込み対象の絶対path+ファイル名
	 * @return xmlファイルから生成したbeanを返す
	 * 
	 */
	public InDataBean setData(String file) throws SAXException,ParserConfigurationException,
												  IOException,NumberFormatException {
		idb = new InDataBean();
		
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		
		Element elementRoot = document.getDocumentElement(); //Root要素：<data>
		
		Element nameEle = (Element) elementRoot.getElementsByTagName("name").item(0);
		Element ageEle = (Element) elementRoot.getElementsByTagName("age").item(0);
		Element sexEle = (Element) elementRoot.getElementsByTagName("sex").item(0);
		idb.setName(nameEle.getFirstChild().getNodeValue());
		idb.setAge(Integer.parseInt(ageEle.getFirstChild().getNodeValue()));
		
		switch(sexEle.getFirstChild().getNodeValue()){
		case "男":idb.setSex(0); break;
		case "女":idb.setSex(1); break;
		default :idb.setSex(-1); break;
		}
		
		
		}catch(SAXException e){
			logger.error("XMLの解析に失敗しました。ファイルを確認してください。");
			throw e;
		}catch(ParserConfigurationException e){
			logger.error("XMLの解析に失敗しました。ファイルを確認してください。");
			throw e;
		}catch(IOException e){
			logger.error("XMLの読み込みに失敗しました。ファイルを確認してください。");
			throw e;
		}catch(NumberFormatException e){
			logger.error("xmin_0001_003 要素「age」の値が不正です。");
			throw e;
		}
		
		chekDate();
		
		return idb; 
	}

	/**
	 * xmlファイルから生成したbeanデータの整合性をチェックする
	 * 
	 */
	public void chekDate() throws RuntimeException {
		
		if(idb.getName().length() > 20){
			logger.error("xmin_0001_002 要素「name」の値が大きすぎます。");
			throw new RuntimeException();
		}
		
		if(idb.getAge() > 999 || idb.getAge() < 0){
			logger.error("xmin_0001_003 要素「age」の値が不正です。");
			throw new RuntimeException();
		}
		
		if(idb.getSex() >= 3 || idb.getSex() <= -1){
			logger.error("xmin_0001_004 要素「sex」の値不正です。");
			throw new RuntimeException();
		}
		
	}
	
	
}
