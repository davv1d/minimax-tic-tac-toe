class Node(
    val nestingLevel: Int,
    private var data: GameData,
    private var parent: Node? = null,
    private var children: MutableList<Node> = mutableListOf()
) {

    fun addChild(node: Node) {
        children.add(node)
        node.setParent(this)
    }

    fun isLeafNode(): Boolean {
        return children.isEmpty()
    }

    fun setChildren(children: MutableList<Node>) {
        for (child in children) {
            addChild(child)
        }
    }

    fun setParent(parent: Node) {
        this.parent = parent
    }

    fun getData(): GameData {
        return data
    }

    fun getParent(): Node? {
        return parent;
    }

    fun getChildren(): MutableList<Node> {
        return children;
    }

    fun isRoot(): Boolean {
        return parent == null;
    }

    fun getAllLeafNodes(): MutableList<Node> {
        val leafNodes = mutableListOf<Node>()
        if (this.children.isEmpty()) {
            leafNodes.add(this)
        } else {
            for (child in children) {
                leafNodes.addAll(child.getAllLeafNodes())
            }
        }
        return leafNodes
    }

    fun getNotCalculated(): MutableList<Node> {
        val notCalculatedNode = mutableListOf<Node>()
        if (this.getData().point == -4) {
            notCalculatedNode.add(this)
            for (child in children) {
                notCalculatedNode.addAll(child.getNotCalculated())
            }
        }
        return notCalculatedNode
    }

    override fun toString(): String {
        return data.toString()
    }


}