public class Cell {
    public enum CellType {
        EMPTY, RABBIT, FOX, MUSHROOM, HOLE, RABBIT_IN_HOLE
    }

    private CellType type;
    private Piece piece;

    public Cell(CellType type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }

    public CellType getType() { return type; }
    public Piece getPiece() { return piece; }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return type == CellType.EMPTY && piece == null;
    }

    public String getSymbol() {
        return (piece == null) ? "." : piece.getSymbol();
    }
}
