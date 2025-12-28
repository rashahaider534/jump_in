import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private Board state;
    private Node parent=null;
    private Action action=null;
    private int depth=0;
    private int cost=0;

    public Node(Board state, Node parent, Action action, int cost) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.cost = cost;
        this.depth = (parent == null ? 0 : parent.depth + 1);
    }
    public Board getState() { return state; }
    public Node getParent() { return parent; }
    public Action getAction() { return action; }
    public int getDepth() { return depth; }
    public int getCost() { return cost; }

    public List<Action> getPossibleActions() {
        List<Action> actions = new ArrayList<>();

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                Position pos = new Position(r, c);
                Cell cell = state.getCell(pos);
                if (cell == null || cell.getPiece() == null) continue;

                if (cell.getPiece() instanceof Rabbit || cell.getPiece() instanceof RabbitInHole) {
                    List<Position> moves = state.getRabbitMoves(pos);
                    for (Position newPos : moves) {
                        actions.add(new Action(Action.PieceType.RABBIT, pos, newPos));
                    }
                }
                if (cell.getPiece() instanceof Fox fox) {

                    List<Position> foxMoves = state.getFoxMoves(fox);
                    if(pos.equals(fox.getTailPosition())) continue;
                    for (Position newPos : foxMoves) {
                        actions.add(new Action(Action.PieceType.FOX, fox.getHeadPosition(), newPos));

                    }

                }
            }}

                return actions;
            }

    public Board applyMove(Action action) {
        Board newState = state.deepCopy();
        if (action.piece == Action.PieceType.RABBIT) {
            Position from = action.from;
            Position to = action.to;
           newState.moveRabbit(from,to);
            return newState;
        }
        if (action.piece == Action.PieceType.FOX) {
            Position from = action.from;
            Position to = action.to;
            newState.moveFox(from,to);
            return newState;
        }
        return newState;
    }

    public List<Node> generateNextStates() {
        List<Node> children = new ArrayList<>();
        List<Action> actions = getPossibleActions();

        for (Action act : actions) {
            Board nextBoard = applyMove(act);
            Node child = new Node(nextBoard, this, act, this.cost + 1);
            children.add(child);
        }

        return children;
    }
    public void printState() {
        System.out.println("--------------------------------");
        System.out.println("Depth: " + depth + " | Cost: " + cost);
        state.printBoard();

    }
    public boolean isFinal() {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                Cell cell = state.getCell(new Position(r, c));

                if (cell.getPiece() instanceof Rabbit) return false;
            }
        }
        return true;
    }
    public int Heuristic()
    {
//        int count=0;
//        for(int r=0;r<5;r++)
//        {
//            for(int c=0;c<5;c++)
//            {
//                Cell cell =state.getCell(new Position(r,c));
//                if(cell.getPiece() instanceof Rabbit)
//                {
//                    count++;
//                }
//            }
//        }
//        return count;

        int h=0;
        for(int r=0;r<5;r++)
       {
           for(int c=0;c<5;c++) {
                    Cell cell=state.getCell(new Position(r,c));
                    if(cell.getPiece() instanceof  Rabbit)
                    {
                        if(r ==4 || r==0 ||c==4 || c==0)
                        {
                            h+=1;
                        }
                        else{
                            h+=2;
                        }
                    }
           }
           }
        return h;

    }
    public int getf()
    {
        return cost +Heuristic();
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node)) return false;
        Node other = (Node) o;

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                Position p = new Position(r, c);

                String s1 = state.getCell(p).getSymbol();
                String s2 = other.state.getCell(p).getSymbol();

                if (!Objects.equals(s1, s2)) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                String symbol = state.getCell(new Position(r, c)).getSymbol();
                hash = 31 * hash + (symbol == null ? 0 : symbol.hashCode());
            }
        }

        return hash;
    }
}
