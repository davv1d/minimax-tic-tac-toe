import Player.NO

class MovementsCalculator {
    private val gameStateChecker = GameStateChecker()
    fun calculateMoves(board: Array<Array<Player>>, player: Player, nestingLevel: Int): Node {
        val node = Node(0, GameData(board.cloneBoard()))
        var player2 = player
        for (levelNumber in 1..nestingLevel) {
            val allLeavesNodes = node.getAllLeavesNodes()
            player2 = player2.changePlayer()
            calculateOneMovement(allLeavesNodes, player2, levelNumber)
        }
        return node
    }

    private fun calculateOneMovement(nodes: MutableList<Node>, player: Player, nestingLevel: Int) {
        for (node in nodes) {
            val board = node.getBoard()
            for (x in 0 until 3) {
                for (y in 0 until 3) {
                    if (isMovePossible(board, x, y)) {
                        val clonedBoard: Array<Array<Player>> = board.cloneBoard()
                        clonedBoard[x][y] = player
                        val data = GameData(clonedBoard)
                        val child = Node(data = data, nestingLevel = nestingLevel)
                        node.addChild(child)
                    }
                }
            }
        }
    }

    private fun isMovePossible(board: Array<Array<Player>>, x: Int, y: Int): Boolean {
        return board[x][y] == NO && !gameStateChecker.isDraw(board) && !gameStateChecker.isWin(board)
    }
}