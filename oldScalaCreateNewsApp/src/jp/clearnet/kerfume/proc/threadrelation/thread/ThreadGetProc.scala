package jp.clearnet.kerfume.proc.threadrelation.thread

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import scala.slick.driver.MySQLDriver.simple._

import jp.clearnet.kerfume.dao.threadrelation.thread.ThreadDto;
import jp.clearnet.kerfume.dao.DaoTrait;
import jp.clearnet.kerfume.dao.ThrdMng;

/**
 * 2chSCの対象の板よりスレッドの一覧を取得し、<br/>
 * 整形したものをスレッド管理テーブルへ格納する。<br/>
 * @author Kerfume
 */
object ThreadGetProc extends DaoTrait{
  //スレッド一覧リスト
  private var threadDtoList:List[ThreadDto] = null 
  //取得先URL:DEFAULT = 2chSC -> ニュース速報板
  private var targetUrl:String = "http://hayabusa3.2ch.sc/news/subback.html"
  //スレッド行判定パターン
  private var threadPattern:Pattern = Pattern.compile("<a.*\\(\\d+\\)</a>");
  //スレッドタイトル判定パターン
  private var threadTitlePattern:Pattern = Pattern.compile("\\d+: .*\\(\\d+\\)</a>$")
  //レスポンス数判定パターン
  private var responsNumPattern:Pattern = Pattern.compile("\\(\\d+\\)$")
  //スレッドID（URL）判定パターン
  private var threadIdPattern:Pattern = Pattern.compile("^<a href=\"\\d+/l50\">")

  //エラー内容定数：後でpropatyファイルに退避
  val ERR001 = "Err001:スレッド取得エラー"
  val ERR002 = "Err002:DBエラー"
 
  /**
  * スレッド取得→DB登録のトランザクション処理。
  * @return 成否判定
  */
  def runTransaction():Boolean = {
    var result:Boolean = false
    
    //スレッドの一覧を取得する
    result = getThreadList()
    
    result
  }
  
 /**
  * 取得先URLよりスレッドの一覧を取得し、DBへ登録する
  * @return result 結果
  */
  private def getThreadList():Boolean =  {
    //結果
    var result = false
    //取得先URL
    var url:URL = null
    //HTTPコネクション
    var http:HttpURLConnection = null
    //取得用インプットストリームリーダー
    var isr:InputStreamReader = null
    //取得用バッファリーダー
    var br:BufferedReader = null
    //スレッド名一時格納用String
    var buffLine:String=null
    
    try{
      //DB系
      Database.forURL(URL, driver = DRIVER,
              user = USER,
              password = PASSWORD) withSession {
  
                implicit session =>
                  implicit lazy val thrdMng = TableQuery[ThrdMng];
        session.withTransaction{
          //session.conn.setAutoCommit(false)
          url = new URL(targetUrl)
          //URLよりコネクションを取得しHttpConnectionクラスにキャスト
          http = url.openConnection().asInstanceOf[HttpURLConnection]
          //HttpConnection各種設定
          http.setRequestMethod("POST");
    			http.setRequestProperty("Accept-Language", "jp");
    			http.setReadTimeout(30000);
    			//HTTP接続
    			http.connect();
    			println("HTTP接続-完了")
    			//コンテンツ（HTML）取得
    			isr = new InputStreamReader(http.getInputStream(), "Shift-JIS");
    			br = new BufferedReader(isr);
    			println("コンテンツ（HTML）取得-完了")
    			while ({buffLine = br.readLine()
    			        buffLine != null}) {
    			  
    			  if(matcher(buffLine)){
    			    //格納用ThreadDto
    			    var threadDto:ThreadDto = null
    			    //スレッドDtoの取得
    					if({threadDto = getThreadInf(buffLine)
    					    threadDto != null && threadDto.title != "null"}){
    					  println("スレッド取得-完了")
    					  println(threadDto.threadId + ":" + threadDto.title + ":" + threadDto.resNum)
    					  try{
    					    println("DB登録-開始")
    					  //DBへ格納
    					    thrdMng.insert(
    					        threadDto.threadId.toLong
    					        , threadDto.title
    					        , threadDto.resNum
    					        , DATEFORMAT.format(new Date())
    					        , DATEFORMAT.format(new Date())
    					        , DELETEFLG_UNDELETED
    					        )
    					        println("DB登録-完了")
    					  }catch{
                //DBエラー
                case e:Exception => {
                    session.rollback()
                    result = false
                    println(ERR002)
                    println(e.printStackTrace())
                    throw e
                  }
    					  }
  					  }     
  					}
  				}
  			}
  			
  			
      }
			//取得したスレッドの一覧を返す
			return result
			
    }catch{ 
      //取得エラー
      case e:Exception => {
        println(ERR001)
        println(e.printStackTrace())
      }
      return result
    }
  }
  
  /**
  * スレッドの一覧をDBへ登録する。
  * @param スレッド一覧
  */
  private def insertThreadDto(threadDtoList:List[ThreadDto]):Boolean = {
    //TODO DAOクラスを呼び出す
    var result = false
    threadDtoList.foreach { 
      x => 
      //printf("%s：%s：%s\n",x.title,x.resNum,x.threadId) }
    }
    result
  }

  /**
  * HTML行を検査しスレッド行である場合はTrueを返す。
  * @return 判定結果
  */
  private def matcher(word:String) = {
		var result:Boolean = false;
		var matcher:Matcher = threadPattern.matcher(word);
		if(matcher.matches()){
			result = true;
		}
		
		result;
	}
  
  /**
  * スレッド行を検査し、スレッドDtoを返す。
  * @return 判定結果
  */
  private def getThreadInf(word:String):ThreadDto = {
		var title:String = null
		var resNum:String = null
		var threadId:String = null
  	var matcher:Matcher = null
  	//スレッドタイトル・レスポンス数の取得
  	matcher = threadTitlePattern.matcher(word)
		if(matcher.find()){
		  title = matcher.group()
		  title = title.replaceAll("</a>$", "").replaceAll("^\\d+: ", "")
		  matcher = responsNumPattern.matcher(title)
		  if(matcher.find()){
		    //レスポンス数の取得
		    resNum = matcher.group()
		    resNum = resNum.replaceAll("\\(", "").replaceAll("\\)", "")
		    //スレッドタイトルの取得
		    title = title.replaceAll("\\(\\d+\\)$", "")
		  }
		  //スレッドID取得
		  matcher = threadIdPattern.matcher(word)
		  if(matcher.find()){
		    threadId = matcher.group()
		    threadId = threadId.replaceAll("^<a href=\"", "").replaceAll("/l50\">$", "")
		  }
		  if(
		      title != null 
		      && resNum != null
		      && threadId != null
		      ){
		    return new ThreadDto(title,resNum.toInt,threadId)
		  }
		}
		
		//マッチしなかった場合nullを返す
		null;
	}
  
  /**
  * 取得先URL(文字列)を取得する。
  * @return 取得先URL
  */
  def getTargetUrl() = {
    this.targetUrl
  }
  /**
  * 取得先URL(文字列)を設定する。
  * @param 取得先URL
  */
  def setTargetUrl(targetUrl:String) = {
    this.targetUrl = targetUrl
  }
  
}

