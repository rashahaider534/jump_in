import java.util.List;

public class Level {
   public static Board level1()
   {
    Board board = new Board();
    board.setCell(new Position(2, 3), new Cell(Cell.CellType.RABBIT, new Rabbit()));
    board.setCell(new Position(0, 0), new Cell(Cell.CellType.HOLE, new Hole()));
    board.setCell(new Position(4, 0), new Cell(Cell.CellType.HOLE, new Hole()));
    board.setCell(new Position(4, 4), new Cell(Cell.CellType.HOLE, new Hole()));
    board.setCell(new Position(2, 2), new Cell(Cell.CellType.HOLE, new Hole()));
    board.setCell(new Position(0, 1), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
    board.setCell(new Position(0, 2), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
    board.setCell(new Position(1, 3), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));

       return board;
   }
    public static Board level2()
    {
        Board board = new Board();
        board.setCell(new Position(0, 2), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(0, 4), new Cell(Cell.CellType.RABBIT_IN_HOLE, new RabbitInHole(new Rabbit())));
        board.setCell(new Position(0, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(2, 2), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(0, 3), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(1, 4), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(2, 3), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));

        return board;
    }
    public static Board level3()
    {
        Board board = new Board();
        board.setCell(new Position(1, 2), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(0, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(0, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(0, 3), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
       board.setCell(new Position(1, 1), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(2, 2), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        Fox fox=new Fox(Fox.Orientation.HORIZONTAL,new Position(1,3),new Position(1,4));
        board.setCell(fox.getHeadPosition(),new Cell(Cell.CellType.FOX,fox));
        board.setCell(fox.getTailPosition(),new Cell(Cell.CellType.FOX,fox));
        return board;
    }
    public static Board level4()
    {
        Board board = new Board();
        board.setCell(new Position(0, 3), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(0, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(0, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(2, 1), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(2, 2), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(2, 4), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        Fox fox=new Fox(Fox.Orientation.HORIZONTAL,new Position(1,1),new Position(1,2));
        board.setCell(fox.getHeadPosition(),new Cell(Cell.CellType.FOX,fox));
        board.setCell(fox.getTailPosition(),new Cell(Cell.CellType.FOX,fox));
        return board;
    }
    public static Board level5()
    {
        Board board = new Board();
        board.setCell(new Position(0, 4), new Cell(Cell.CellType.RABBIT_IN_HOLE, new RabbitInHole(new Rabbit())));
        board.setCell(new Position(2, 2), new Cell(Cell.CellType.RABBIT_IN_HOLE, new RabbitInHole(new Rabbit())));
        board.setCell(new Position(4, 4), new Cell(Cell.CellType.RABBIT_IN_HOLE, new RabbitInHole(new Rabbit())));
        board.setCell(new Position(1, 0), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(0, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(2, 3), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(3, 0), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(3, 4), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        return board;
    }
    public static Board level6()
    {
        Board board = new Board();
        board.setCell(new Position(0, 4), new Cell(Cell.CellType.RABBIT_IN_HOLE, new RabbitInHole(new Rabbit())));
        board.setCell(new Position(0, 2), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(2, 1), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(4, 2), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(0, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(2, 2), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(2, 0), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(2, 3), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(1, 4), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        Fox fox=new Fox(Fox.Orientation.VERTICAL,new Position(0,1),new Position(1,1));
        board.setCell(fox.getHeadPosition(),new Cell(Cell.CellType.FOX,fox));
        board.setCell(fox.getTailPosition(),new Cell(Cell.CellType.FOX,fox));
        return board;
    }
    public static Board level8()
    {
        Board board = new Board();
        board.setCell(new Position(2, 2), new Cell(Cell.CellType.RABBIT_IN_HOLE, new RabbitInHole(new Rabbit())));
        board.setCell(new Position(1, 3), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(0, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(0, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(0, 1), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(4, 1), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        Fox fox=new Fox(Fox.Orientation.HORIZONTAL,new Position(1,0),new Position(1,1));
        board.setCell(fox.getHeadPosition(),new Cell(Cell.CellType.FOX,fox));
        board.setCell(fox.getTailPosition(),new Cell(Cell.CellType.FOX,fox));
        return board;
    }
    public static Board level9()
    {
        Board board = new Board();

        board.setCell(new Position(4, 1), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(4, 2), new Cell(Cell.CellType.RABBIT, new Rabbit()));
        board.setCell(new Position(0, 0), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(0, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(2, 2), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 4), new Cell(Cell.CellType.HOLE, new Hole()));
        board.setCell(new Position(4, 0), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(3, 2), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        board.setCell(new Position(1, 2), new Cell(Cell.CellType.MUSHROOM, new Mushroom()));
        Fox fox=new Fox(Fox.Orientation.VERTICAL,new Position(0,1),new Position(1,1));
        board.setCell(fox.getHeadPosition(),new Cell(Cell.CellType.FOX,fox));
        board.setCell(fox.getTailPosition(),new Cell(Cell.CellType.FOX,fox));
        return board;
    }
   public static Board level( int levelnum)
   {
        switch (levelnum){
            case 1:
              return   level1();
            case 2:
                return   level2();
            case 3:
                return level3();
            case 4:
                return level4();
            case 5:
                return level5();
            case 6:
                return level6();
            case 8:
                return level8();
            case 9:
                return level9();
            default:
                System.out.println("❌ مستوى غير موجود");
                return null;
        }
   }



}
