public class FarmGame {
    public static void main(String[] args){
        Grid grid = new Grid(9, 9);
        new RadishFarmer(grid, 4, 4);
        new Rabbit(grid, 0, 4);
        new VerticalTransporter(grid, 2, 4, 10);
        new Beaver(grid, 4, 0);

        // Item.printInfoMessages = true;

        Game game = new Game(grid);
        game.run(6);

    }
}
