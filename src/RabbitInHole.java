public class RabbitInHole extends Piece{
    private Rabbit rabbit;

    public RabbitInHole(Rabbit rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public String getSymbol() {
        return "RO";
    }
}
