class Minimax {
    private val movementsAppraiser = MovementsAppraiser()
    private val movementsCalculator = MovementsCalculator()

    fun getBestMovement(board: Array<Array<Player>>, player: Player, nestingLevel: Int): List<GameData> {
        val movementsTree = movementsCalculator.calculateMoves(board, player, nestingLevel)
        movementsAppraiser.rateAllNodes(movementsTree)
        val bestMovements = chooseBestMovements(movementsTree)
        return bestMovements.asSequence().map { node -> node.data }.toList()
    }

    private fun chooseBestMovements(movementTree: Node): List<Node> {
        return movementTree.children.asSequence()
            .filter { node -> node.getPoints() == movementTree.getPoints() }
            .toList()
    }
}