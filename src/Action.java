public class Action {
    public enum PieceType { RABBIT, FOX }
    public PieceType piece;
    public Position from;
    public Position to;

    public Action(PieceType piece, Position from, Position to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return piece + ": (" + from.getRow() + "," + from.getCol() + ") → (" + to.getRow() + "," + to.getCol() + ")";
    }

}
