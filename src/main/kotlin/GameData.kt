
class GameData(val board: Array<Array<Player>>, var point: Point = Point.notCalculated()) {

    override fun toString(): String {
        val s = StringBuilder()
        for (row in board) {
            s.append(row.contentDeepToString()).append("\n")
        }
        return s.toString()
    }
}