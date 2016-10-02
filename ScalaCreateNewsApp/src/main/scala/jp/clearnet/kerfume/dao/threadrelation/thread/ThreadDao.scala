package jp.clearnet.kerfume.dao.threadrelation.thread

//リリース時には不要！消す！
import scala.slick.driver.MySQLDriver.simple._

import jp.clearnet.kerfume.dao.DaoTrait
import jp.clearnet.kerfume.dao.ThrdMng

import java.util.Date
import java.sql.SQLException

object ThreadDao extends DaoTrait{
  def insert(threadDto:ThreadDto):Boolean = {
    var result = false
    
    try {
      Database.forURL(URL, driver = DRIVER,
      user = USER,
      password = PASSWORD) withSession {
  
        implicit session =>
  
          implicit lazy val ThrdMng = TableQuery[ThrdMng];
          
          ThrdMng.insert(
              threadDto.threadId.toInt
              ,threadDto.title
              ,threadDto.resNum
              ,DATEFORMAT.format(new Date),DATEFORMAT.format(new Date),0)
  
        }
      result = true
      
      return result
      
      }catch{
        case e:SQLException => return result
        case e:Exception => return result
    } 
  }
  
  def insertAll(threadDtoList:List[ThreadDto]):Boolean = {
    var result = false
    
    try {
      Database.forURL(URL, driver = DRIVER,
      user = USER,
      password = PASSWORD) withSession {
  
        implicit session =>
  
          implicit lazy val ThrdMng = TableQuery[ThrdMng];
          
          threadDtoList.foreach { threadDto => 
            ThrdMng.insert(
                threadDto.threadId.toInt
                ,threadDto.title
                ,threadDto.resNum
                ,DATEFORMAT.format(new Date),DATEFORMAT.format(new Date),0)
            }
        }
      result = true
      
      return result
      
      }catch{
        case e:SQLException => return result
        case e:Exception => return result
    } 
  }
  
}