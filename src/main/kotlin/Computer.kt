import kotlin.random.Random

class Computer(private val chanceOfTheBestMovementIfEmptyBoard: Double) {
    private val minimax: Minimax = Minimax()
    private val fieldsAssessor: FieldsAssessor = FieldsAssessor()

    fun selectMovement(board: Array<Array<Player>>, human: Player): GameData {
        return when {
            board.isEmpty() -> {
                val pointsOfFields = fieldsAssessor.getPointsOfFields()
                val coordinates = selectMovementAtTheEmptyBoard(pointsOfFields)
                GameData(board = makeMovement(board, coordinates, human))
            }
            else -> {
                val bestMovement = minimax.getBestMovement(board, human, board.getNumberOfPossibleMoves())
                when (bestMovement.size) {
                    1 -> bestMovement[0]
                    else -> bestMovement[Random.nextInt(0, bestMovement.size)]
                }
            }
        }
    }

    private fun selectMovementAtTheEmptyBoard(pointsOfFields: List<Coordinates>): Coordinates {
        val random = Math.random()
        return when {
            random < chanceOfTheBestMovementIfEmptyBoard -> Coordinates(1, 1)
            else -> pointsOfFields[Random.nextInt(0, 5)]
        }
    }

    private fun makeMovement(board: Array<Array<Player>>,coordinates: Coordinates, human: Player): Array<Array<Player>> {
        val clonedBoard = board.cloneBoard()
        clonedBoard[coordinates.x][coordinates.y] = human.changePlayer()
        return clonedBoard
    }
}