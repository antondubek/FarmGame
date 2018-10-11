public class Grid extends AbstractGrid{

    private int width;
    private int height;
    private int totalProduction;
    private int totalConsumption;

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
        System.out.println("DEBUG: registerItem");
        System.out.println("DEBUG: Item at x,y = " +grid[xCoordinate][yCoordinate]);
    }

    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {
        // Check if there is an item at location, if T return, else null
        if (grid[xCoordinate][yCoordinate] instanceof AbstractItem){
            System.out.println("DEBUG: getItem return = " + grid[xCoordinate][yCoordinate]);
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
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                //if item is an item and a radish farmer
                if(getItem(x,y) != null && getItem(x,y) instanceof RadishFarmer) {
                    getItem(x, y).process(timeStep);
                }
            }
        }
    }

    @Override
    public void recordProduction(int nutrition) {
        totalProduction += nutrition;
    }

    @Override
    public int getTotalProduction() {
        return totalProduction;
    }

    @Override
    public void recordConsumption(int nutrition) {
        totalConsumption += nutrition;
    }

    @Override
    public int getTotalConsumption() {
        return totalConsumption;
    }
}
