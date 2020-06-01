import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Minimax minimax = new Minimax();
        Player[][] board = {
                {Player.X, Player.X, Player.NO},
                {Player.NO, Player.O, Player.O},
                {Player.NO, Player.O, Player.NO}};
        System.out.println(BoardOperator.INSTANCE.isDraw(board));
        System.out.println(BoardOperator.INSTANCE.isWin(board));
        int numberOfPossibleMoves = BoardOperator.INSTANCE.getNumberOfPossibleMoves(board);
        Node tree = new MovementCalculator().calculateMoves(board, Player.O, numberOfPossibleMoves);
        GameData bestMovement = minimax.getBestMovement(board, Player.O, numberOfPossibleMoves);
        System.out.println(bestMovement);
    }
}
