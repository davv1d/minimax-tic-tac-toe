class MovementCalculator {
    fun calculateMoves(board: Array<Array<String>>, player: String, nestingLevel: Int): Node {
        val node = Node(0, GameData(BoardOperator.cloneBoard(board), player))
        var player2 = player
        for (levelNumber in 1..nestingLevel) {
            val allLeavesNodes = node.getAllLeavesNodes()
            player2 = changePlayer(player2)
            calculateOneMovement(allLeavesNodes, player2, levelNumber)
        }
        return node
    }

    private fun calculateOneMovement(nodes: MutableList<Node>, player: String, nestingLevel: Int) {
        for (node in nodes) {
            val board = node.data.board
            for (x in 0 until 3) {
                for (y in 0 until 3) {
                    if (board[x][y] == " " && !BoardOperator.isDraw(board) && !BoardOperator.isWin(board)) {
                        val clonedBoard: Array<Array<String>> = BoardOperator.cloneBoard(board)
                        clonedBoard[x][y] = player
                        val data = GameData(clonedBoard, playerMakingTheMove = player)
                        val child = Node(data = data, nestingLevel = nestingLevel)
                        node.addChild(child)
                    }
                }
            }
        }
    }

    private fun changePlayer(player: String): String {
        return when (player) {
            "X" -> "O"
            else -> "X"
        }
    }

}