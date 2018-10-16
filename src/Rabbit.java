/**
 * Type of animal class that consumes 8 units of nutrition every turn and cannot store anything
 * it does not consume.
 */
public class Rabbit extends Consumer {

    /**
     * Rabbit Consumer Constructor
     * Sets local parameters of x, y and grid
     * Registers the item into the grid
     * @param grid Grid grid which the object needs to belong to and be put into
     * @param yCoordinate int Y coordinate of where the item is to be on the grid
     * @param xCoordinate int x coordinate of where the item is to be on the grid
     */
    public Rabbit(Grid grid, int yCoordinate, int xCoordinate){
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
        return "Rabbit("+getStock()+")";
    }

    /**
     * Checks how much stock is at the location before consuming up to 8 units
     * discarding any remaining nutrition
     * @param timeStep Current game timestep
     */
    @Override
    public void process(TimeStep timeStep) {

        //Get the initial stock level
        int stockLevel = getStock();

        // If there is 8 units or more
        if((stockLevel) >= 8){
            // Eat 8 nutrition
            grid.recordConsumption(8);
        } else {
            // Else eat whatever is there
            grid.recordConsumption(stockLevel);
        }
        reduceStock(stockLevel);
    }
}
