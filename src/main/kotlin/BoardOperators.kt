import Player.NO

fun Array<Array<Player>>.cloneBoard(): Array<Array<Player>> {
    val clonedBoard: Array<Array<Player>> = arrayOf(arrayOf(NO, NO, NO), arrayOf(NO, NO, NO), arrayOf(NO, NO, NO))
    for(x in 0 until 3) {
        for(y in 0 until 3){
            clonedBoard[x][y] = this[x][y]
        }
    }
    return clonedBoard
}


fun Array<Array<Player>>.getNumberOfPossibleMoves(): Int {
    var counter = 0
    for(x in 0 until 3) {
        for(y in 0 until 3){
            if (this[x][y] == NO) counter++
        }
    }
    return counter
}