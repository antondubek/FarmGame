/**
 * Vertical Transporter Class contains methods for a transporter to move stock between a farmer and
 * consumer either side vertically of it with nothing blocking.
 */

public class VerticalTransporter extends Transporter {
    private int capacity;

    /**
     *Vertical Transporter Constructor
     * Sets local parameters of x, y, grid and capacity
     * Registers the item into the grid
     * @param grid Grid grid which the object needs to belong to and be put into
     * @param yCoordinate int Y coordinate of where the item is to be on the grid
     * @param xCoordinate int x coordinate of where the item is to be on the grid
     * @param capacity int the amount of stock (in nutrition) that the transporter can move per timestep
     */
    public VerticalTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity){
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * Returns the transporters name to display on the grid.
     * @return String name
     */
    @Override
    public String toString() {
        return "VT";
    }

    /**
     * Transporter initially finds the nearest object below it, then above it.
     * A check is then made for the instance of hedgehog whether it is currently accepting stock
     * With 2 items found, a check is then carried out to make sure 1 is a farmer and 1 is a consumer
     * If so, stock is moved from the farmer to the consumer up to the capacity of the transporter
     * Otherwise nothing happens
     * @param timeStep The current time-step
     */
    @Override
    public void process(TimeStep timeStep) {
        // get the column of transporter (xcord)
        // start at y coord of the transporter and go down to find something
        AbstractItem farmer = null;
        AbstractItem consumer = null;
        for(int y = yCoordinate; y < grid.getHeight(); y++){
            AbstractItem item = grid.getItem(xCoordinate, y);
            if(item instanceof Farmer){
                farmer = item;
                break;
            } else if(item instanceof Consumer) {
                if((item instanceof Hedgehog)){
                    if(((Hedgehog) item).isAccepting()){
                        consumer = item;
                        break;
                    } else {
                        break;
                    }
                } else {
                    consumer = item;
                    break;
                }
            }
        }

        // go up till you find something else
        for(int y = yCoordinate; y >= 0; y--){
            AbstractItem item = grid.getItem(xCoordinate, y);
            if(item instanceof Farmer){
                farmer = item;
                break;
            } else if(item instanceof Consumer) {
                if((item instanceof Hedgehog)){
                    if(((Hedgehog) item).isAccepting()){
                        consumer = item;
                        break;
                    } else {
                        break;
                    }
                } else {
                    consumer = item;
                    break;
                }
            }
        }

        //System.out.println(farmer);
        //System.out.println(consumer);

        // Check we have found 2 things
        if((farmer != null) && (consumer != null)){
            // get the stock level at the farmer locationSystem.out.println("DEBUG: getItem return = " + grid[xCoordinate][yCoordinate]);
            int farmerStock = farmer.getStock();

            if(farmerStock >= capacity){
                //Reduce farmer capacity by capacity
                farmer.reduceStock(capacity);
                //Increase consumer capacity
                consumer.addToStock(capacity);
            } else { ;
                //reduce farmer stock by amount
                farmer.reduceStock(farmerStock);
                // increase consumer by amount
                consumer.addToStock(farmerStock);
            }
        }
    }

}
