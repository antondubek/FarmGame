public class Grid extends AbstractGrid{

    private int width;
    private int height;
    private AbstractItem[][] grid;
    private int[][] stock;

    Grid(int width, int height){
        this.width = width;
        this.height = height;
        grid = new AbstractItem[width][height];
        stock = new int[width][height];
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
        // Put the item into the location on the grid
        grid[xCoordinate][yCoordinate] = item;
    }

    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {
        // Check if there is an item at location, if T return, else null

        return null;
    }

    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        return 0;
    }

    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {

    }

    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {

    }

    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {

    }

    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {

    }

    @Override
    public void processItems(TimeStep timeStep) {

    }

    @Override
    public void recordProduction(int nutrition) {

    }

    @Override
    public int getTotalProduction() {
        return 0;
    }

    @Override
    public void recordConsumption(int nutrition) {

    }

    @Override
    public int getTotalConsumption() {
        return 0;
    }
}
