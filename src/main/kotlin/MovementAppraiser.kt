class MovementAppraiser {
    fun rateAllNodes(tree: Node) {
        rateAllNodeLeaves(tree.getAllLeavesNodes())
        do {
            val notRatedNodes = tree.getNotRatedNodes()
            for (node in notRatedNodes) {
                if (node.isLeafNode()) {
                    calculatePoint(node.data)
                } else {
                    if (areChildrenRated(node)) {
                        node.data.point = calculateParentPoint(node.children)
                    }
                }
            }
        } while (notRatedNodes.size > 1)
    }

    private fun areChildrenRated(node: Node): Boolean {
        return node.children.asSequence()
            .filter { n -> n.data.point == -4 }
            .toList().isEmpty()
    }


    private fun rateAllNodeLeaves(allLeafNode: MutableList<Node>) {
        for (leaf in allLeafNode) {
            val point = calculatePoint(leaf.data)
            leaf.data.point = point
        }
    }

    private fun calculateParentPoint(children: MutableList<Node>): Int {
        return if (children[0].data.playerMakingTheMove == "X") {
            calculateBestChildrenPoint(children) { acc, i -> i < acc }
        } else {
            calculateBestChildrenPoint(children) { acc, i -> i > acc }
        }
    }

    private fun calculateBestChildrenPoint(children: MutableList<Node>, compare: (Int, Int) -> Boolean): Int {
        return children.asSequence()
            .map { node -> node.data.point }
            .reduce { acc, i ->
                if (compare.invoke(acc, i)) {
                    return i
                } else {
                    acc
                }
            }
    }

    private fun calculatePoint(gameData: GameData): Int {
        return when (gameData.playerMakingTheMove) {
            "X" -> {
                when {
                    BoardOperator.isWin(gameData.board) -> -1
                    else -> 0
                }
            }
            else -> {
                when {
                    BoardOperator.isWin(gameData.board) -> 1
                    else -> 0
                }
            }
        }
    }
}