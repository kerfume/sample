package jp.clearnet.kerfume.dao.threadrelation.thread

/**
 * スレッドDtoクラス。
 * @author Kerfume
 * 
 * @param スレッドタイトル
 * @param レスポンス数
 * @param スレッドURL
 */
class ThreadDto(
    private var _title:String = ""
    ,private var _resNum:Int = 0
    ,private var _threadId:String = ""){
  
  def title:String = {
    _title
  }
  def title_=(title:String) = {
    _title = title
  }
  def resNum:Int = {
    _resNum
  }
  def resNum_=(resNum:Int) = {
    _resNum = resNum
  }
  def threadId:String = {
    _threadId
  }
  def threadId_=(threadId:String) = {
    _threadId = threadId
  }
}

