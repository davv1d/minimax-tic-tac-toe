class MovementsAppraiser {
    fun rateAllNodes(tree: Node) {
        rateAllNodeLeaves(tree.getAllLeavesNodes())
        do {
            val notRatedNodes = tree.getNotRatedNodes()
            for (node in notRatedNodes) {
                when {
                    node.isLeafNode() -> calculateHumanOrComputerPoints(node)
                    else -> when {
                        areChildrenRated(node) -> node.setPoints(getParentPoints(node.children))
                    }
                }
            }
        } while (notRatedNodes.size > 1)
    }

    private fun areChildrenRated(node: Node): Boolean {
        return node.children.asSequence()
            .filter { n -> n.getPoints() == Points.notCalculated() }
            .toList().isEmpty()
    }


    private fun rateAllNodeLeaves(allLeafNode: MutableList<Node>) {
        for (leaf in allLeafNode) {
            val points = calculateHumanOrComputerPoints(leaf)
            leaf.setPoints(points)
        }
    }

    private fun getParentPoints(children: MutableList<Node>): Points {
        return if (children[0].nestingLevel % 2 == 0) {
            getBestChildrenPoints(children) { acc, i -> i.value < acc.value }
        } else {
            getBestChildrenPoints(children) { acc, i -> i.value > acc.value }
        }
    }

    private fun getBestChildrenPoints(children: MutableList<Node>, compare: (Points, Points) -> Boolean): Points {
        return children.asSequence()
            .map { node -> node.getPoints() }
            .reduce { acc, i ->
                when {
                    compare.invoke(acc, i) -> i
                    else -> acc
                }
            }
    }

    private fun calculateHumanOrComputerPoints(node: Node): Points {
        return when (node.nestingLevel % 2 == 0) {
            true -> {
                calculatePoints(node, Points.humanWin())
            }
            else -> {
                calculatePoints(node, Points.computerWin())
            }
        }
    }

    private fun calculatePoints(node: Node, points: Points): Points {
        return when {
            GameStateChecker.isWin(node.getBoard()) -> points
            else -> Points.draw()
        }
    }
}