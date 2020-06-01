enum class Player {
    X {
        override fun changePlayer(): Player = O
    },
    O {
        override fun changePlayer(): Player = X
    },
    NO {
        override fun changePlayer(): Player = NO
    };

    abstract fun changePlayer(): Player

    override fun toString(): String {
        return when(this == NO) {
            true -> " "
            else -> this.name
        }
    }
}
