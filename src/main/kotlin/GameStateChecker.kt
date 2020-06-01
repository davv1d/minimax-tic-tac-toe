import Player.NO

object GameStateChecker {
    fun isDraw(board: Array<Array<Player>>): Boolean {
        for (x in 0 until 3) {
            for (y in 0 until 3) {
                if (board[x][y] == NO) {
                    return false
                }
            }
        }
        return true
    }

    fun isWin(board: Array<Array<Player>>): Boolean {
        val isHorizontalWin: Boolean = checkHorizontal(board)
        val isVerticalWin: Boolean = checkVertical(board)
        val isDiagonalWin: Boolean = checkDiagonal(board)
        return isHorizontalWin || isVerticalWin || isDiagonalWin
    }

    private fun checkDiagonal(board: Array<Array<Player>>): Boolean {
        return (board[0][0] != NO && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] != NO && board[0][2] == board[1][1] && board[1][1] == board[2][0])
    }

    private fun checkVertical(board: Array<Array<Player>>): Boolean {
        for (y in 0 until 3) {
            if (board[0][y] != NO && board[0][y] == board[1][y] && board[1][y] == board[2][y]) {
                return true
            }
        }
        return false
    }

    private fun checkHorizontal(board: Array<Array<Player>>): Boolean {
        for (x in 0 until 3) {
            if (board[x][0] != NO && board[x][0] == board[x][1] && board[x][1] == board[x][2]) {
                return true
            }
        }
        return false
    }
}