import Array._
import scala.util.Random

/**
  * Main entry point to start conway game of life
  *
  * The game is a zero-player game, meaning that its evolution is determined by its initial state,
  * requiring no further input.  One interacts with the Game of Life by creating an initial configuration
  * and observing how it evolves, or, for advanced players, by creating patterns with particular properties.
  *
  *
  */
object LifeMain extends App {

  var gridSize = 20
  if (args.length == 1) {
    gridSize = args(0).toInt
  }

  var lifeGrid: Array[Array[GridCell]] = ofDim[GridCell](gridSize, gridSize)
  for(i <- 0 until gridSize; j <- 0 until gridSize){
    lifeGrid(i)(j) = new GridCell()
  }

  lifeGrid.foreach(x => x.foreach(y => if (Random.nextInt(10)==1) {
    y.presentAlive = true
  }))

  var previousGrid = ""
  var noChange = false

  while (!noChange) {

    Thread.sleep(50)
    val printGridBuilder = new StringBuilder()

    for (i <- 0 until gridSize-1) {
      for (j <- 0 until gridSize-1) {

        printGridBuilder.append(lifeGrid(i)(j))

        var neighbourCells: Array[GridCell] = new Array[GridCell](9)
        for(c <- 0 until 9){
          neighbourCells(c) = new GridCell()
        }

        if (i > 0) {
          neighbourCells(0) = lifeGrid(i - 1)(j)
          if (j > 0) {
            neighbourCells(1) = lifeGrid(i - 1)(j - 1)
          }
          if (j < gridSize) {
            neighbourCells(2) = lifeGrid(i - 1)(j + 1)
          }
        }

        if (j > 0) {
          neighbourCells(3) = lifeGrid(i)(j - 1)
        }
        neighbourCells(4) = lifeGrid(i)(j)
        if (j < gridSize) {
          neighbourCells(5) = lifeGrid(i)(j + 1)
        }

        if (i < gridSize) {
          if (j > 0) {
            neighbourCells(6) = lifeGrid(i + 1)(j - 1)
          }
          if (j < gridSize) {
            neighbourCells(7) = lifeGrid(i + 1)(j + 1)
          }
          neighbourCells(8) = lifeGrid(i + 1)(j)
        }

        lifeGrid(i)(j).futureAlive = CellEvolver.evolve(neighbourCells)
      }
      printGridBuilder.append("\n")
    }

    println(printGridBuilder.toString())
    lifeGrid.foreach(x => x.foreach(y => y.flipState()))
    if(printGridBuilder.toString().equals(previousGrid)){
      noChange=true
    }
    previousGrid = printGridBuilder.toString()
  }

}
