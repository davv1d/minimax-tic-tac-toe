class Node(
    val nestingLevel: Int,
    var data: GameData,
    var parent: Node? = null,
    var children: MutableList<Node> = mutableListOf()
) {

    fun addChild(node: Node) {
        children.add(node)
        node.parent = this
    }

    fun isLeafNode(): Boolean {
        return children.isEmpty()
    }

    fun getAllLeavesNodes(): MutableList<Node> {
        val leafNodes = mutableListOf<Node>()
        if (this.isLeafNode()) {
            leafNodes.add(this)
        } else {
            for (child in children) {
                leafNodes.addAll(child.getAllLeavesNodes())
            }
        }
        return leafNodes
    }

    fun getNotRatedNodes(): MutableList<Node> {
        val notRatedNodes = mutableListOf<Node>()
        if (this.getPoints() == Points.notCalculated()) {
            notRatedNodes.add(this)
            for (child in children) {
                notRatedNodes.addAll(child.getNotRatedNodes())
            }
        }
        return notRatedNodes
    }

    fun getBoard(): Array<Array<Player>> = this.data.board

    fun getPoints(): Points = this.data.points

    fun setPoints(points: Points) {
        this.data.points = points
    }

    override fun toString(): String {
        return data.toString()
    }
}