open class Points private constructor(val value: Int) {

    companion object {
        fun computerWin(): Points = Points(1)

        fun humanWin(): Points = Points(-1)

        fun draw(): Points = Points(0)

        fun notCalculated(): Points = Points(Int.MIN_VALUE)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Points

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }
}