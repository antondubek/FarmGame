public class FarmGame {
    public static void main(String[] args){
        Grid grid = new Grid(5, 3);
        new CornFarmer(grid, 0, 0);
        new RadishFarmer(grid, 4, 0);
        new Rabbit(grid, 0, 2);
        new NearestTransporter(grid, 2, 1, 10);

        Game game = new Game(grid);
        game.run(5);
    }
}
