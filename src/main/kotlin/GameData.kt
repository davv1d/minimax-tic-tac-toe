
class GameData(val board: Array<Array<String>>, val playerMakingTheMove: String, var point: Int = -4) {

    override fun toString(): String {
        val s = StringBuilder()
        for (row in board) {
            s.append(row.contentDeepToString()).append("\n")
        }
        return s.toString()
    }
}