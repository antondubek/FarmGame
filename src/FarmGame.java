public class FarmGame {
    public static void main(String[] args){
        Grid grid = new Grid(9, 9);
        new CornFarmer(grid, 0, 0);
        new VerticalTransporter(grid,3, 0, 10);
        new Hedgehog(grid, 4, 0);

        // Item.printInfoMessages = true;

        Game game = new Game(grid);
        game.run(50);
    }
}
