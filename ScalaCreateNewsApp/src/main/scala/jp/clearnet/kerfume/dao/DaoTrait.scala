package jp.clearnet.kerfume.dao

import java.text.SimpleDateFormat

/**
 * Dao用インタフェース（の代わりのTrait）
 * @author Kerfume
 */
abstract trait DaoTrait {
  //定数宣言
  val SCHEME = "jdbc:mysql"
  val HOST = "localhost"
  val POST = "3306"
  val DBNAME = "NEWS_DB"
  val URL = s"${SCHEME}://${HOST}:${POST}/${DBNAME}"
  val DRIVER = "com.mysql.jdbc.Driver"
  val USER = "PROCUSER"
  val PASSWORD = "PROCPASS"
   
  val DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd")
  val DELETEFLG_UNDELETED = 0
  
}