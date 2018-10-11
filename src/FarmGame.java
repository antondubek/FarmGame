public class FarmGame {
    public static void main(String[] args){
        Grid grid = new Grid(9, 9);
        new RadishFarmer(grid, 1, 1);

        // Item.printInfoMessages = true;

        Game game = new Game(grid);
        game.run(1);

    }
}
