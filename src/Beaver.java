/**
 * Type of animal class that consumes 5 units of nutrition every turn and can store up to 50
 * nutrition it does not consume.
 */
public class Beaver extends Consumer {

    /**
     * Beaver Consumer Constructor
     * Sets local parameters of x, y and grid
     * Registers the item into the grid
     * @param grid Grid grid which the object needs to belong to and be put into
     * @param yCoordinate int Y coordinate of where the item is to be on the grid
     * @param xCoordinate int x coordinate of where the item is to be on the grid
     */
    public Beaver(Grid grid, int yCoordinate, int xCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * Returns the consumers name to display on the grid
     * @return String name(stock level)
     */
    @Override
    public String toString() {
        return "Beaver(" + getStock() + ")";
    }

    /**
     * Checks how much stock is at the location before consuming up to 5 units
     * storing any extra nutrition as long as the store is not up to its max of 50
     * @param timeStep Current game timestep
     */
    @Override
    public void process(TimeStep timeStep) {

        // get the initial stock level
        int stockLevel = getStock();

        //If there is 55 units or more
        if((stockLevel) > 55){
            grid.recordConsumption(5);
            grid.setStockAt(xCoordinate,yCoordinate,50);
        }
        else if((stockLevel) >= 5){
            //eat 5 nutrition
            grid.recordConsumption(5);
            reduceStock(5);
        } else {
            // eat whatever there is
            grid.recordConsumption(stockLevel);
            reduceStock(stockLevel);
        }
    }
}
