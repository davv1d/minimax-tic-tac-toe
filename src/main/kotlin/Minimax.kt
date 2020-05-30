class Minimax {

    private fun calculateOneMovement(nodes: MutableList<Node>, player: String, nestingLevel: Int): MutableList<Node> {
        for (node in nodes) {
            val board = node.getData().board
            for (x in 0 until 3) {
                for (y in 0 until 3) {
                    if (board[x][y] == " " && !BoardOperator.isDraw(board) && !BoardOperator.isWin(board)) {
                        val clonedBoard: Array<Array<String>> = BoardOperator.cloneBoard(board)
                        clonedBoard[x][y] = player
                        val child = Node(
                            data = GameData(clonedBoard, playerMakingTheMove = player),
                            nestingLevel = nestingLevel
                        )
                        node.addChild(child)
                    }
                }
            }
        }
        return nodes
    }

    fun calculateMoves(board: Array<Array<String>>, player: String, numberOfMoves: Int): Node {
        val node = Node(0, GameData(BoardOperator.cloneBoard(board), player))
        var player2 = player
        for (i in 1..numberOfMoves) {
            val allLeafNodes = node.getAllLeafNodes()
            player2 = changePlayer(player2)
            calculateOneMovement(allLeafNodes, player2, i)
        }
        return node
    }

    private fun changePlayer(player: String): String {
        return if (player == "X") {
            "O"
        } else {
            "X"
        }
    }

    fun rateTheBoard(allLeafNode: MutableList<Node>) {
        rateTheBoard2(allLeafNode)
        for (leaf in allLeafNode) {
            val parent = leaf.getParent()
            if (parent != null) {
                val children = parent.getChildren()
                val parentPoint = calculateParentPoint(children)
                parent.getData().point = parentPoint
            }
        }
    }


    fun rateTheBoard2(allLeafNode: MutableList<Node>) {
        for (leaf in allLeafNode) {
            val point = calculatePoint(leaf.getData())
            leaf.getData().point = point
        }
    }

    private fun calculateParentPoint(children: MutableList<Node>): Int {
        return if (children[0].getData().playerMakingTheMove == "X") {
            calculateBestChildrenPoint(children) { acc, i -> i < acc }
        } else {
            calculateBestChildrenPoint(children) { acc, i -> i > acc }
        }
    }

    private fun calculateBestChildrenPoint(children: MutableList<Node>, compare: (Int, Int) -> Boolean): Int {
        return children.asSequence()
            .map { node -> node.getData().point }
            .reduce { acc, i ->
                if (compare.invoke(acc, i)) {
                    return i
                } else {
                    acc
                }
            }
    }

    private fun calculatePoint(gameData: GameData): Int {
        return if (gameData.playerMakingTheMove == "X") {
            if (BoardOperator.isWin(gameData.board)) {
                -1
            } else {
                0
            }
        } else {
            if (BoardOperator.isWin(gameData.board)) {
                1
            } else {
                0
            }
        }
    }
}