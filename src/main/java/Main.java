import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Minimax minimax = new Minimax();
        String[][] board = {
                {"X", " ", " "},
                {" ", "X", " "},
                {"O", "X", "O"}};
        System.out.println(BoardOperator.INSTANCE.isDraw(board));
        System.out.println(BoardOperator.INSTANCE.isWin(board));
//        System.out.println(new GameData(board));
//        List<Node> nodes = minimax.calculateMove(board);
//        List<Node> leafNode = nodes.stream()
//                .flatMap(node -> node.getAllLeafNodes().stream())
//                .collect(Collectors.toList());
//        List<Node> nodeList = minimax.calculateSecondMove(leafNode, "O");
//        List<GameData> collect = nodeList.stream()
//                .flatMap(node -> node.getAllLeafNodes().stream())
//                .map(node -> node.getData())
//                .collect(Collectors.toList());

        int numberOfPossibleMoves = BoardOperator.INSTANCE.getNumberOfPossibleMoves(board);
        Node tree = minimax.calculateMoves(board, "X", numberOfPossibleMoves);
        List<Node> allLeafNodes = tree.getAllLeafNodes();
        List<Node> sortedLeafs = allLeafNodes.stream()
                .sorted((o1, o2) -> Integer.compare(o2.getNestingLevel(), o1.getNestingLevel()))
                .collect(Collectors.toList());
        minimax.rateTheBoard(sortedLeafs);
        System.out.println(numberOfPossibleMoves);
    }
}
