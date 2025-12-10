import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int SIZE = 5;
    private Map<Position, Cell> grid;

    public Board() {
        grid = new HashMap<>();
        initBoard();
    }

    private void initBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Position pos = new Position(row, col);
                grid.put(pos, new Cell(Cell.CellType.EMPTY, null));
            }
        }
    }

    public Cell getCell(Position pos) {
        return grid.get(pos);
    }
    public void setCell(Position pos, Cell cell) {
        grid.put(pos, cell);
    }

    public void printBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Position pos = new Position(row, col);
                System.out.print(grid.get(pos).getSymbol() + "\t");
            }
            System.out.println();
        }
    }

    public Board deepCopy() {
        Board copy = new Board();
        copy.grid.clear();
        for (Map.Entry<Position, Cell> entry : this.grid.entrySet()) {
            Position pos = entry.getKey();
            Cell originalCell = entry.getValue();

            Piece originalPiece = originalCell.getPiece();
            Piece pieceCopy = null;

            if (originalPiece != null) {
                if (originalPiece instanceof Rabbit) {
                    pieceCopy = new Rabbit();
                } else if (originalPiece instanceof RabbitInHole) {
                    pieceCopy = new RabbitInHole(new Rabbit());
                } else if (originalPiece instanceof Fox) {
                    Fox originalFox = (Fox) originalPiece;
                    pieceCopy = new Fox(
                            originalFox.getOrientation(),
                            new Position(originalFox.getHeadPosition().getRow(),
                                    originalFox.getHeadPosition().getCol()),
                            new Position(originalFox.getTailPosition().getRow(),
                            originalFox.getTailPosition().getCol())
                    );
                } else if (originalPiece instanceof Mushroom) {
                    pieceCopy = new Mushroom();
                } else if (originalPiece instanceof Hole) {
                    pieceCopy = new Hole();
                }
            }
            Cell cellCopy = new Cell(originalCell.getType(), pieceCopy);
            copy.grid.put(new Position(pos.getRow(), pos.getCol()), cellCopy);
        }

        return copy;
    }

    public boolean moveRabbit(Position from,Position to) {
        Cell currentCell = getCell(from);
        if (currentCell == null ||
                (currentCell.getPiece() == null) ||
                !(currentCell.getPiece() instanceof Rabbit || currentCell.getPiece() instanceof RabbitInHole)) {
            return false;
        }
        boolean rabbitInHole = currentCell.getPiece() instanceof RabbitInHole;
        Cell checkCell = getCell(to);
                if (!rabbitInHole) {
                    if (checkCell.getType() == Cell.CellType.EMPTY) {
                        setCell(to, new Cell(Cell.CellType.RABBIT, new Rabbit()));
                        setCell(from, new Cell(Cell.CellType.EMPTY, null));
                    }
                    else if (checkCell.getType() == Cell.CellType.HOLE) {
                        setCell(to, new Cell(Cell.CellType.RABBIT_IN_HOLE, new RabbitInHole(new Rabbit())));
                        setCell(from, new Cell(Cell.CellType.EMPTY, null));
                    }
                }

                else {
                    if (checkCell.getType() == Cell.CellType.EMPTY) {
                        setCell(to, new Cell(Cell.CellType.RABBIT, new Rabbit()));
                        setCell(from, new Cell(Cell.CellType.HOLE, new Hole()));
                    }
                    else if (checkCell.getType() == Cell.CellType.HOLE) {
                        setCell(to, new Cell(Cell.CellType.RABBIT_IN_HOLE, new RabbitInHole(new Rabbit())));
                        setCell(from, new Cell(Cell.CellType.HOLE, new Hole()));
                    }
                }
                return true;
    }
    public boolean moveFox(Position from, Position to) {
        Cell fromCell = getCell(from);
        if (fromCell == null || !(fromCell.getPiece() instanceof Fox)) return false;

        Fox fox = (Fox) fromCell.getPiece();

        Position head = fox.getHeadPosition();
        Position tail = fox.getTailPosition();

        if (!from.equals(head)) return false;

        Position newHead = to;
        Position newTail;

        if (fox.getOrientation() == Fox.Orientation.HORIZONTAL) {

            if (to.getCol() < head.getCol()) {
                newTail = new Position(newHead.getRow(), newHead.getCol()+1);
            } else {
                newTail = new Position(newHead.getRow(), newHead.getCol()-1);
            }
        }

        else {

            if (to.getRow() < head.getRow()) {
                newTail = new Position(newHead.getRow()+1, newHead.getCol());
            } else {
                newTail = new Position(newHead.getRow()-1, newHead.getCol());
            }
        }
        if (!isInsideBoard(newHead) || !isInsideBoard(newTail)) return false;
        if (!getCell(newHead).isEmpty() && !newHead.equals(head)) return false;


        setCell(head, new Cell(Cell.CellType.EMPTY, null));
        setCell(tail, new Cell(Cell.CellType.EMPTY, null));

        Fox newFox = new Fox(fox.getOrientation(), newHead, newTail);

        setCell(newHead, new Cell(Cell.CellType.FOX, newFox));
        setCell(newTail, new Cell(Cell.CellType.FOX, newFox));

        return true;
    }


    private boolean isInsideBoard(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < 5
                && pos.getCol() >= 0 && pos.getCol() < 5;
    }

    public List<Position> getRabbitMoves(Position from)
    {
        List<Position> possibleMoves=new ArrayList<>();
        Cell statrcell=getCell(from);
        if(statrcell.getPiece()==null || (!(statrcell.getPiece() instanceof Rabbit) && !(statrcell.getPiece() instanceof RabbitInHole)))
        {
            return possibleMoves;
        }
        int [][]dirction={
                {-1,0},
                {1,0},
                {0,1},
                {0,-1}
        };
       for(int []d:dirction)
        {
            int r = from.getRow();
            int c = from.getCol();
            boolean jumpedOverSomething=false;
            while (true)
            {
                r+=d[0];
                c+=d[1];
                Position pos = new Position(r,c);
                if (!isInsideBoard(pos)) break;
                Cell cell=getCell(pos);

                if(cell ==null)break;

                Cell.CellType type=cell.getType();

                if(type == Cell.CellType.RABBIT_IN_HOLE||
                type==Cell.CellType.RABBIT||
                type==Cell.CellType.MUSHROOM||
                type== Cell.CellType.FOX)
                {
                    jumpedOverSomething=true;
                    continue;
                }
                if(jumpedOverSomething &&(type==Cell.CellType.HOLE ||type== Cell.CellType.EMPTY))
                {
                    possibleMoves.add(pos);
                }
                break;
            }

        }
       return possibleMoves;

    }

    public List<Position> getFoxMoves(Fox fox) {
        List<Position> moves = new ArrayList<>();
        Position head = fox.getHeadPosition();
        Position tail = fox.getTailPosition();

       if (fox.getOrientation() == Fox.Orientation.VERTICAL) {
            Position uptohead = new Position(head.getRow() - 1, head.getCol());
            if (isInsideBoard(uptohead) ) {
                Cell upcell=getCell(uptohead);
                if(upcell.isEmpty()){
                    moves.add(uptohead);}
                if(upcell.getPiece() instanceof Fox)
                {
                    Position upt=new Position(uptohead.getRow()-1, uptohead.getCol() );
                    if (isInsideBoard(upt) && getCell(upt).isEmpty())
                    {
                        moves.add(upt);
                    }
                }
            }
            Position downtohead = new Position(tail.getRow() + 1, tail.getCol());
            if (isInsideBoard(downtohead) ) {
                Cell downcell=getCell(downtohead);
                if(downcell.isEmpty()){
                    moves.add(downtohead);}
                if(downcell.getPiece() instanceof Fox)
                {
                    Position downt=new Position(downtohead.getRow()+1, downtohead.getCol() );
                    if (isInsideBoard(downt) && getCell(downt).isEmpty())
                    {
                        moves.add(downt);
                    }
                }
            }
        }
        else {
            Position rightOfHead = new Position(head.getRow(), head.getCol() + 1);
            if (isInsideBoard(rightOfHead) ) {
                Cell rightCell = getCell(rightOfHead);
                if(rightCell.isEmpty()){
                moves.add(rightOfHead);}
                if( rightCell.getPiece() instanceof Fox )
                {
                    Position rightt=new Position(rightOfHead.getRow(), rightOfHead.getCol() + 1);
                    if (isInsideBoard(rightt) && getCell(rightt).isEmpty())
                    {
                        moves.add(rightt);
                    }
                }
            }
           Position leftOfHead  = new Position(head.getRow(), head.getCol() - 1);
           if (isInsideBoard(leftOfHead ) ) {
               Cell leftCell = getCell(leftOfHead);
               if(leftCell.isEmpty()){
                   moves.add(leftOfHead);}
               if(leftCell.getPiece() instanceof Fox )
               {
                   Position leftt=new Position(tail.getRow(), tail.getCol() - 1);
                   if (isInsideBoard(leftt) && getCell(leftt).isEmpty())
                   {
                       moves.add(leftt);
                   }
               }
           }
        }


        return moves;
    }



}
