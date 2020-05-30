object BoardOperator {
    fun isDraw(board: Array<Array<String>>): Boolean {
        for (x in 0 until 3) {
            for (y in 0 until 3) {
                if (board[x][y] == " ") {
                    return false
                }
            }
        }
        return true
    }

    fun isWin(board: Array<Array<String>>): Boolean {
        val isHorizontalWin: Boolean = checkHorizontal(board)
        val isVerticalWin: Boolean = checkVertical(board)
        val isDiagonalWin: Boolean = checkDiagonal(board)
        return isHorizontalWin || isVerticalWin || isDiagonalWin
    }

    private fun checkDiagonal(board: Array<Array<String>>): Boolean {
        return (board[0][0] != " " && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] != " " && board[0][2] == board[1][1] && board[1][1] == board[2][0])
    }

    private fun checkVertical(board: Array<Array<String>>): Boolean {
        for (y in 0 until 3) {
            if (board[0][y] != " " && board[0][y] == board[1][y] && board[1][y] == board[2][y]) {
                return true
            }
        }
        return false
    }

    private fun checkHorizontal(board: Array<Array<String>>): Boolean {
        for (x in 0 until 3) {
            if (board[x][0] != " " && board[x][0] == board[x][1] && board[x][1] == board[x][2]) {
                return true
            }
        }
        return false
    }


    fun cloneBoard(board: Array<Array<String>>): Array<Array<String>> {
        val clonedBoard: Array<Array<String>> = arrayOf(arrayOf(" ", " ", " "), arrayOf(" ", " ", " "), arrayOf(" ", " ", " "))
        for(x in 0 until 3) {
            for(y in 0 until 3){
                clonedBoard[x][y] = String(board[x][y].toByteArray())
            }
        }
        return clonedBoard
    }

    fun getNumberOfPossibleMoves(board: Array<Array<String>>): Int {
        var counter = 0
        for(x in 0 until 3) {
            for(y in 0 until 3){
                if (board[x][y] == " ") counter++
            }
        }
        return counter
    }
}