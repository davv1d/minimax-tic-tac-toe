import kotlin.random.Random

class Minimax {
    private val movementAppraiser: MovementAppraiser = MovementAppraiser()
    private val movementCalculator: MovementCalculator = MovementCalculator()

    fun getBestMovement(board: Array<Array<Player>>, player: Player, nestingLevel: Int): GameData {
        val movementTree = movementCalculator.calculateMoves(board, player, nestingLevel)
        movementAppraiser.rateAllNodes(movementTree)
        val bestMovement = chooseBestMovement(movementTree)
        return when (bestMovement.size) {
            1 -> bestMovement[0].data
            else -> bestMovement[Random.nextInt(0, bestMovement.size)].data
        }
    }

    private fun chooseBestMovement(movementTree: Node): List<Node> {
        return movementTree.children.asSequence()
            .filter { node -> node.data.point == movementTree.data.point }
            .toList()
    }
}