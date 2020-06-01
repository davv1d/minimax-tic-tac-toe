import Player.*

fun main() {
    val computer = Computer(0.7)
    val board = arrayOf(
        arrayOf(X, NO, NO),
        arrayOf(X, O, NO),
        arrayOf(O, X, O)
    )
    val selectMovement = computer.selectMovement(board = board, human = X)
    println(selectMovement)
}