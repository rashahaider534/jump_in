import java.util.*;

public class Game {
    private Node intitNode;
    private Scanner scanner;
    private Node goalNode;
    public Game(Board initialBoard) {
        this.intitNode = new Node(initialBoard.deepCopy(), null, null, 0);
        this.scanner = new Scanner(System.in);
    }
    public boolean dfs()
    {
        Stack<Node> dfsstack=new Stack<>();
        Set<Node> dfsvisited=new HashSet<>();
        boolean founddfssoulation=false;
        dfsstack.push(intitNode);
        dfsvisited.add(intitNode);
        while(!dfsstack.isEmpty())
        {

           Node node= dfsstack.peek();
           dfsstack.pop();
            if(node.isFinal())
            {
                goalNode = node;
                founddfssoulation=true;
                break;
            }
            for (Node nextstate:node.generateNextStates())
            {

                if(!dfsvisited.contains(nextstate) && ! dfsstack.contains(nextstate))
                {
                    dfsvisited.add(nextstate);
                    dfsstack.push(nextstate);
                }
            }

        }
        printpath(goalNode);
        goalNode.printState();
        System.out.println("visit size:"+dfsvisited.size());
        return founddfssoulation;
    }
    public boolean bfs()
    {
        Queue<Node> bfsqueue=new LinkedList<>();
        Set<Node> bfsvisited=new HashSet<>();
        boolean founddfssoulation=false;
        bfsqueue.add(intitNode);
        bfsvisited.add(intitNode);
        while(!bfsqueue.isEmpty())
        {

            Node node= bfsqueue.poll();
            if(node.isFinal())
            {
                goalNode = node;
                founddfssoulation=true;
                break;
            }
            for (Node nextstate:node.generateNextStates())
            {
                if(!bfsvisited.contains(nextstate) && !bfsqueue.contains(nextstate))
                {
                    bfsvisited.add(nextstate);
                    bfsqueue.add(nextstate);
                }
            }

        }
        printpath(goalNode);
        goalNode.printState();
        System.out.println("visit size:"+bfsvisited.size());
        return founddfssoulation;
    }
    public boolean ucs()
    {
        PriorityQueue<Node> ucsqueue=new PriorityQueue(Comparator.comparingInt(Node::getCost));
        Map<Node,Integer> ucsvisiter=new HashMap<>();
        ucsqueue.add(intitNode);
        ucsvisiter.put(intitNode,0);
        boolean founducssoulation=false;
        while (!ucsqueue.isEmpty())
        {
            Node node=ucsqueue.poll();
            if(node.getCost()>ucsvisiter.get(node))
            {
                continue;
            }
            if(node.isFinal())
            {
                goalNode=node;
                founducssoulation=true;
                break;
            }
            for(Node next:node.generateNextStates()){
                int nextcost =next.getCost();
                if(!ucsvisiter.containsKey(next) && !ucsqueue.contains(next))
                {
                    ucsvisiter.put(next,nextcost);
                    ucsqueue.add(next);
                }
                else if(ucsqueue.contains(next) && nextcost<ucsvisiter.get(next))
                {
                    ucsvisiter.put(next,nextcost);
                    ucsqueue.add(next);
                }

            }

        }
        if(founducssoulation)
        {
            printpath(goalNode);
            goalNode.printState();
        }
        System.out.println("visite nodes: " + ucsvisiter.size());
        return founducssoulation;
    }

    public boolean Astar()
    {
        PriorityQueue<Node> ucsqueue=new PriorityQueue(Comparator.comparingInt(Node::getf));
        Map<Node,Integer> ucsvisiter=new HashMap<>();
        ucsqueue.add(intitNode);
        ucsvisiter.put(intitNode,0);
        boolean founducssoulation=false;
        while (!ucsqueue.isEmpty())
        {
            Node node=ucsqueue.poll();
            if(node.getCost()>ucsvisiter.get(node))
            {
                continue;
            }
            if(node.isFinal())
            {
                goalNode=node;
                founducssoulation=true;
                break;
            }
            for(Node next:node.generateNextStates()){
                int nextcost =next.getCost();
                if(!ucsvisiter.containsKey(next) && !ucsqueue.contains(next))
                {
                    ucsvisiter.put(next,nextcost);
                    ucsqueue.add(next);
                }
                else if(ucsqueue.contains(next) && nextcost<ucsvisiter.get(next))
                {
                    ucsvisiter.put(next,nextcost);
                    ucsqueue.add(next);
                }

            }

        }
        if(founducssoulation)
        {
            printpath(goalNode);
            goalNode.printState();
        }
        System.out.println("visite nodes: " + ucsvisiter.size());
        return founducssoulation;
    }
    public void printpath(Node goal)
    {
        List<Action>path=new ArrayList<>();
        Node node=goal;
        while (node.getParent()!=intitNode)
        {
            path.add(node.getAction());
            node=node.getParent();
        }
        path.add(node.getAction());
        Collections.reverse(path);
        System.out.println("solution path:");
        for(Action action:path)
        {
            System.out.println(action.toString());
        }

    }












    public void playuser() {
        while (true) {
            System.out.println("\n===== Jump In (Node-Based Game) =====");
            intitNode.printState();
            if (intitNode.isFinal()) {
                System.out.println("🎉 كل الأرانب أصبحت في الحفر — فزت باللعبة!");
                break;
            }
            System.out.println("\nاختر قطعة للتحريك:");
            System.out.println("1 - أرنب");
            System.out.println("2 - ثعلب");
            int choice = scanner.nextInt();
            if (choice == 1) playRabbit();
            if (choice == 2) playFox();
           // else System.out.println("❌ خيار غير صحيح.");
        }
    }
    private void playRabbit() {
        System.out.println("\nأدخل موقع الأرنب:");
        Position pos = readPosition();
        Cell cell = intitNode.getState().getCell(pos);

        if (cell == null || cell.getPiece() == null ||
                !(cell.getPiece() instanceof Rabbit || cell.getPiece() instanceof RabbitInHole)) {
            System.out.println("❌ لا يوجد أرنب هنا.");
            return;
        }
        List<Position> moves = intitNode.getState().getRabbitMoves(pos);
        if (moves.isEmpty()) {
            System.out.println("❌ الأرنب لا يمكنه الحركة.");
            return;
        }
        System.out.println("✔ الحركات الممكنة:");
        for (int i = 0; i < moves.size(); i++) {
            System.out.println(i + " → " +
                    "(" + moves.get(i).getRow() + "," + moves.get(i).getCol() + ")");
        }
        System.out.print("اختر حركة: ");
        int m = scanner.nextInt();
        if (m < 0 || m >= moves.size()) {
            System.out.println("❌ رقم حركة غير صحيح.");
            return;
        }
        Position to = moves.get(m);
        Action act = new Action(Action.PieceType.RABBIT, pos, to);
        Board newBoard = intitNode.applyMove(act);
        intitNode = new Node(newBoard, intitNode, act, intitNode.getCost() + 1);

        System.out.println("✔ تمت حركة الأرنب.");
    }
    private void playFox() {

        System.out.println("\nأدخل موقع الثعلب:");
        Position pos = readPosition();
        Cell cell = intitNode.getState().getCell(pos);

        if (cell == null || cell.getPiece() == null ||
                !(cell.getPiece() instanceof Fox )) {
            System.out.println("❌ لا يوجد ثعلب هنا.");
            return;
        }
        Fox fox=(Fox) intitNode.getState().getCell(pos).getPiece();
        List<Position> moves = intitNode.getState().getFoxMoves(fox);
        if (moves.isEmpty()) {
            System.out.println("❌ الثعلب لا يمكنه الحركة.");
            return;
        }
        System.out.println("✔ الحركات الممكنة:");
        for (int i = 0; i < moves.size(); i++) {
            System.out.println(i + " → " +
                    "(" + moves.get(i).getRow() + "," + moves.get(i).getCol() + ")");
        }
        System.out.print("اختر حركة: ");
        int m = scanner.nextInt();
        if (m < 0 || m >= moves.size()) {
            System.out.println("❌ رقم حركة غير صحيح.");
            return;
        }
        Position to = moves.get(m);
        Action act = new Action(Action.PieceType.FOX, pos, to);
        Board newBoard = intitNode.applyMove(act);
        intitNode = new Node(newBoard, intitNode, act, intitNode.getCost() + 1);

        System.out.println("✔ تمت حركة الثعلب.");
    }
    private Position readPosition() {
        System.out.print("Row (0-4): ");
        int r = scanner.nextInt();
        System.out.print("Col (0-4): ");
        int c = scanner.nextInt();
        return new Position(r, c);
    }


}
