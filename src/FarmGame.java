public class FarmGame {
    public static void main(String[] args){
        Grid grid = new Grid(1, 3);
        new RadishFarmer(grid, 0, 0);
        new Beaver(grid, 0, 2);
        new HorizontalTransporter(grid, 0, 1, 10);

        Game game = new Game(grid);
        game.run(5);
    }
}
