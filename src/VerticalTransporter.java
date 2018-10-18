/**
 * Vertical Transporter Class contains methods for a transporter to move stock between a farmer and
 * consumer either side vertically of it with nothing blocking.
 */

public class VerticalTransporter extends Transporter {

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
     * Transporter initially runs the check left and right methods
     * A check is then carried out to make sure 1 is a farmer and 1 is a consumer
     * If so, movestock is called
     * Otherwise nothing happens
     * Check is also conducted at timeStep 12 for hedgehog moving step 11
     * @param timeStep The current time-step
     */
    @Override
    public void process(TimeStep timeStep) {
        if(timeStep.getValue() == 1 || timeStep.getValue() == 12) {
            checkUp();
            checkDown();
        }

        // Check we have found 2 things
        if((farmer != null) && (consumer != null)){
            moveStock(farmer, consumer);
        }
    }

    /**
     * Takes the transporters position and checks up of the transporter for an AbstractItem
     * Checks if the item is a farmer or consumer and saves to the class variable
     * An extra check has been put to check if the hedgehog is currently accepting.
     */
    private void checkUp(){
        // get the column of transporter (xcord)
        // start at y coord of the transporter and go down to find something
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
    }

    /**
     * Takes the transporters position and checks down of the transporter for an AbstractItem
     * Checks if the item is a farmer or consumer and saves to the class variable
     * An extra check has been put to check if the hedgehog is currently accepting.
     */
    private void checkDown(){
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
    }

}
