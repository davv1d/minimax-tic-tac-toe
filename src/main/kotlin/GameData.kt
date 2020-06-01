
class GameData(
    val board: Array<Array<Player>>,
    var points: Points = Points.notCalculated()
) {

    override fun toString(): String {
        val s = StringBuilder()
        for (row in board) {
            s.append("\n").append("" + row[0] + " | " + row[1] + " | " + row[2])
        }
        s.append("\n ----------")
        return s.toString()
    }
}