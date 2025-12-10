public class Fox extends Piece{
    public enum Orientation { HORIZONTAL, VERTICAL }
    private Orientation orientation;
    private Position headPosition;
    private Position tailPosition;

    public Fox( Orientation orientation,Position headPosition,Position tailPosition) {
        this.orientation = orientation;
        this.headPosition = headPosition;
        this.tailPosition = tailPosition;
    }

    public Orientation getOrientation() { return orientation; }
    public Position getHeadPosition() { return headPosition; }
    public Position getTailPosition() {
        return tailPosition;
    }

    @Override
    public String getSymbol() {
        return "F";
    }


}
