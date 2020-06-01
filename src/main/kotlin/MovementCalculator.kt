class MovementCalculator {
    fun calculateMoves(board: Array<Array<Player>>, player: Player, nestingLevel: Int): Node {
        val node = Node(0, GameData(BoardOperator.cloneBoard(board)))
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
            val board = node.data.board
            for (x in 0 until 3) {
                for (y in 0 until 3) {
                    if (board[x][y] == Player.NO && !BoardOperator.isDraw(board) && !BoardOperator.isWin(board)) {
                        val clonedBoard: Array<Array<Player>> = BoardOperator.cloneBoard(board)
                        clonedBoard[x][y] = player
                        val data = GameData(clonedBoard)
                        val child = Node(data = data, nestingLevel = nestingLevel)
                        node.addChild(child)
                    }
                }
            }
        }
    }
}