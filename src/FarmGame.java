public class FarmGame {
    public static void main(String[] args){
        Grid grid = new Grid(9, 9);
        new RadishFarmer(grid, 0, 0);

        // Item.printInfoMessages = true;

        Game game = new Game(grid);
    }
}
