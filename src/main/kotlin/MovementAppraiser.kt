import Player.*

class MovementAppraiser {
    fun rateAllNodes(tree: Node) {
        rateAllNodeLeaves(tree.getAllLeavesNodes())
        do {
            val notRatedNodes = tree.getNotRatedNodes()
            for (node in notRatedNodes) {
                if (node.isLeafNode()) {
                    newCalculatePoint(node)
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
            .filter { n -> n.data.point == Point.notCalculated() }
            .toList().isEmpty()
    }


    private fun rateAllNodeLeaves(allLeafNode: MutableList<Node>) {
        for (leaf in allLeafNode) {
            val point = newCalculatePoint(leaf)
            leaf.data.point = point
        }
    }

    private fun calculateParentPoint(children: MutableList<Node>): Point {
        return if (children[0].nestingLevel % 2 == 0) {
            calculateBestChildrenPoint(children) { acc, i -> i.value < acc.value }
        } else {
            calculateBestChildrenPoint(children) { acc, i -> i.value > acc.value }
        }
    }

    private fun calculateBestChildrenPoint(children: MutableList<Node>, compare: (Point, Point) -> Boolean): Point {
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

    private fun newCalculatePoint(node: Node): Point {
        return when (node.nestingLevel % 2 == 0) {
            true -> {
                when {
                    BoardOperator.isWin(node.data.board) -> Point.humanWin()
                    else -> Point.draw()
                }
            }
            else -> {
                when {
                    BoardOperator.isWin(node.data.board) -> Point.computerWin()
                    else -> Point.draw()
                }
            }
        }
    }
}