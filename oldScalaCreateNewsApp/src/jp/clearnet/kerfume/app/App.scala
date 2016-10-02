package jp.clearnet.kerfume.app

import jp.clearnet.kerfume.proc.threadrelation.thread.ThreadGetProc

object App extends App {
  println("Hello!")
  ThreadGetProc.runTransaction()
  
}