open class Point private constructor(val value: Int) {

    companion object {
        fun computerWin(): Point = Point(1)

        fun humanWin(): Point = Point(-1)

        fun draw(): Point = Point(0)

        fun notCalculated(): Point = Point(Int.MIN_VALUE)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }
}