import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Minimax minimax = new Minimax();
        String[][] board = {
                {"O", "X", "O"},
                {" ", "X", " "},
                {"X", " ", " "}};
        System.out.println(BoardOperator.INSTANCE.isDraw(board));
        System.out.println(BoardOperator.INSTANCE.isWin(board));
        int numberOfPossibleMoves = BoardOperator.INSTANCE.getNumberOfPossibleMoves(board);
        Node tree = new MovementCalculator().calculateMoves(board, "X", numberOfPossibleMoves);
        List<Node> allLeafNodes = tree.getAllLeavesNodes();
        List<Node> sortedLeafs = allLeafNodes.stream()
                .sorted((o1, o2) -> Integer.compare(o2.getNestingLevel(), o1.getNestingLevel()))
                .collect(Collectors.toList());
        GameData bestMovement = minimax.getBestMovement(board, "X", numberOfPossibleMoves);
        System.out.println(bestMovement);
    }
}
