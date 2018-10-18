
/**
 * Type of animal class that collects stock for 10 turns before trying to find a corner
 * to hibernate in where it will then consume 2 units of nutrition per turn. If it cannot find
 * a corner to move to it will stubbornly sit and collect food, even if already in a corner :).
 */
public class Hedgehog extends Consumer {

    private boolean isAccepting = true;
    private boolean moveSuccessful = false;

    /**
     * Hedgehog Consumer Constructor
     * Sets local parameters of x, y and grid
     * Registers the item into the grid
     * @param grid Grid grid which the object needs to belong to and be put into
     * @param yCoordinate int Y coordinate of where the item is to be on the grid
     * @param xCoordinate int x coordinate of where the item is to be on the grid
     */
    public Hedgehog(Grid grid, int yCoordinate, int xCoordinate){
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
        return "Hedgehog("+getStock()+")";
    }

    /**
     * Getter for boolean isAccepting
     * @return Boolean isAccepting
     */
    public boolean isAccepting() {
        return isAccepting;
    }

    /**
     * Collects stock for 10 turns before checking whether a corner is free to move to turn 11
     * If a corner is free, calls the moveHouse method to relocate to this new space. If it isint
     * it will sit and do nothing but collect stock. If it moves successfully, it will consume
     * 2 units of nutrition per turn, until the game ends or it runs out of food.
     * @param timeStep current game timestep
     */
    @Override
    public void process(TimeStep timeStep) {
        if (timeStep.getValue() == 11){
            //when it is 11, move!
            Integer[] freeCorner = checkCorners();
            if(freeCorner[0] != null){
                moveHouse(freeCorner);
                isAccepting = false;
                moveSuccessful = true;
            }
        } else if (timeStep.getValue() > 11 && moveSuccessful) {
            //hibernate and eat 1 per turn till empty
            if(getStock() >= 2){
                grid.recordConsumption(2);
                reduceStock(2);
            }
        }
    }

    /**
     * Method to check whether any of the 4 corners of the grid are free, returning the coordinates
     * if there is on or an empty Integer[]
     * @return Integer[] If found Integer[xCord, yCord] or empty if none found
     */
    private Integer[] checkCorners(){
        int stock = getStock();
        Integer[] coords = new Integer[2];
        //check for free corners
        if(grid.getItem(0,0) == null){
            //if top left free, move there
            coords[0] = 0;
            coords[1] = 0;
        } else if(grid.getItem(grid.getWidth()-1, 0) == null){
            //move top right
            coords[0] = grid.getWidth()-1;
            coords[1] = 0;
        } else if(grid.getItem(0, grid.getHeight()-1) == null){
            //move bottom left
            coords[0] = 0;
            coords[1] = grid.getHeight()-1;
        } else if(grid.getItem(grid.getWidth()-1, grid.getHeight()-1) == null){
            //move bottom right
            coords[0] = grid.getWidth()-1;
            coords[1] = grid.getHeight()-1;
        }
        return coords;
    }

    /**
     * Method to move both the object and the stock from one location to a new location
     * @param newCoords Integer[] containing [xCords, yCords] of the new location
     */
    private void moveHouse(Integer[] newCoords){
        int newx = newCoords[0];
        int newy = newCoords[1];
        int currentStock = getStock();

        grid.registerItem(newx, newy, this);
        grid.setStockAt(newx, newy, currentStock);
        reduceStock(currentStock);
        grid.registerItem(this.xCoordinate, this.yCoordinate, null);
        this.xCoordinate = newx;
        this.yCoordinate = newy;

    }
}
