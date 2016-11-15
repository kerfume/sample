package jp.clearnet.kerfume.proc.threadrelation.thread

import java.io.BufferedReader
import java.io.IOException
import java.sql.SQLException

import scalikejdbc._
import jp.clearnet.kerfume.dao.DaoTrait

case class Thrd_mng(id: Int)

/**
 * ワークテーブルに格納されているスレッドから記事取得用スレッドを<br/>
 * 選別する。<br/>
 * @author Kerfume
 */
object ThreadSortProc extends DaoTrait{
  def main(args: Array[String]): Unit = {
    setUpDB
  }

  def setUpDB: Unit ={
    Class.forName(DRIVER)
    ConnectionPool.singleton(URL,USER,PASSWORD)

    val members: List[Map[String, Any]] = DB readOnly { implicit session =>
      SQL("SELECT " +
        " * " +
        " FROM " +
        " WRK_THRD_MNG " +
        " WHERE " +
        " RES_NUM > 100" +
        " AND NOT EXISTS( " +
        "  SELECT" +
        "   * " +
        "  FROM" +
        "  THRD_MNG " +
        "  WHERE " +
        "   WRK_THRD_MNG.THRD_ID = THRD_MNG.THRD_ID )" +
        " ORDER BY INSERT_NUM ASC " +
        " LIMIT 1 ").map(rs => rs.toMap).list.apply()
    }
    members.foreach(s => println(s.apply("THRD_TITLE")))
    println(members.length)
  }


}