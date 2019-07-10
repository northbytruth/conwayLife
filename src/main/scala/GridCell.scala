class GridCell {
  var presentAlive = false
  var futureAlive = false

  def flipState()= {
    presentAlive = futureAlive
    futureAlive = false
  }

  override def toString: String = {

    if(presentAlive){
      "#"
    }else{
      " "
    }

  }
}
