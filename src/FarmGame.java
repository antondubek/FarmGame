public class FarmGame {
    public static void main(String[] args){
        Grid grid = new Grid(9, 9);
        new CornFarmer(grid, 0, 0);
        new HorizontalTransporter(grid,0, 3, 10);
        new Hedgehog(grid, 0, 4);

        // Item.printInfoMessages = true;

        Game game = new Game(grid);
        game.run(50);
    }
}
