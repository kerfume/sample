package jp.clearnet.kerfume.dao

import java.util.Date;

import scala.slick.driver.MySQLDriver.simple._

class ThrdMng(tag: Tag) extends Table[(Long, String, Int, String, String, Int)](tag,"Wrk_Thrd_Mng") {
  def id = column[Long]("THRD_ID", O.PrimaryKey)
  def title = column[String]("THRD_TITLE", O.PrimaryKey)
  def resNum = column[Int]("RES_NUM")
  def updDate = column[String]("UPD_DATE")
  def regDate = column[String]("REG_DATE")
  def delFlg = column[Int]("DEL_FLG")
  def * = (id, title, resNum ,updDate ,regDate ,delFlg)
}
