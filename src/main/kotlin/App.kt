import GameStateChecker.isDraw
import GameStateChecker.isWin
import Player.*

fun main() {
    val minimax = Minimax()
    val board = arrayOf(
        arrayOf(X, X, NO),
        arrayOf(NO, O, O),
        arrayOf(NO, O, NO)
    )
    println(isDraw(board))
    println(isWin(board))
    val numberOfPossibleMoves = board.getNumberOfPossibleMoves()
    val bestMovement = minimax.getBestMovement(board, O, numberOfPossibleMoves)
    println(bestMovement)
}