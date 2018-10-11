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
        if (grid[xCoordinate][yCoordinate] instanceof AbstractItem){
            return grid[xCoordinate][yCoordinate];
        } else {
            return null;
        }
    }

    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        //. Returns amount of stock at a location
        return stock[xCoordinate][yCoordinate];
    }

    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {
        //Set the stock at location to 0
        setStockAt(xCoordinate, yCoordinate, 0);
    }

    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        // Increments the stock at a location
        int currentStock = getStockAt(xCoordinate, yCoordinate);
        setStockAt(xCoordinate,yCoordinate, currentStock += nutrition);
    }

    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        int currentStock = getStockAt(xCoordinate, yCoordinate);
        setStockAt(xCoordinate, yCoordinate, currentStock -= nutrition);
    }

    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        stock[xCoordinate][yCoordinate] = nutrition;
    }

    @Override
    public void processItems(TimeStep timeStep) {

        //For farmers
        for(int y=0; y<grid.length; y++){
            for(int x=0; x<grid[y].length; x++) {
                // if the item is an item and is a farmer
                if(getItem(x,y) != null && getItem(x,y) instanceof RadishFarmer){
                    //process it
                }
            }
        }
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
