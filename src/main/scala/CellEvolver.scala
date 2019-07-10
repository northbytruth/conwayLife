/**
  *
  * CellEvolver
  *
  * Takes a 3x3 group and returns centre cell's next state
  *
  * Any live cell with fewer than two live neighbours dies, as if by underpopulation.
  * Any live cell with two or three live neighbours lives on to the next generation.
  * Any live cell with more than three live neighbours dies, as if by overpopulation.
  * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
  */
object CellEvolver {

  /**
    * Returns next state of 9 by 9 grid cell centre
    *
    * @param CellNeighbourhood
    * @return
    */
  def evolve(CellNeighbourhood: Array[GridCell]): Boolean = {
    var nextState = false
    var aliveNeighbours = 0;

    val centreCell: GridCell = CellNeighbourhood(4)
    for(i <- 0 until 9){
      if(i!=4 && CellNeighbourhood(i).presentAlive){
        aliveNeighbours += 1
      }
    }

    if (aliveNeighbours < 2) {
      false
    } else if (aliveNeighbours == 2 && centreCell.presentAlive) {
      true
    } else if (aliveNeighbours == 3) {
      true
    } else {
      false
    }
  }

}
