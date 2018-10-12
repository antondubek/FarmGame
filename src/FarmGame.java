public class FarmGame {
    public static void main(String[] args){
        Grid grid = new Grid(5, 5);
        new CornFarmer(grid, 0, 0);
        new RadishFarmer(grid, 4, 4);
        new RadishFarmer(grid, 3, 4);
        new Rabbit(grid, 0, 4);
        new Beaver(grid, 4, 0);
        new HorizontalTransporter(grid, 0, 2, 10);
        new HorizontalTransporter(grid, 4, 2, 10);
        new VerticalTransporter(grid, 2, 0, 10);
        new VerticalTransporter(grid, 2, 4, 10);

        Game game = new Game(grid);
        game.run(50);
    }
}
