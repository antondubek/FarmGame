/**
 * Grid class contains methods to manipulate and retrieve items and values within the grid and stock
 */
public class Grid extends AbstractGrid{

    private int width;
    private int height;
    private int totalProduction;
    private int totalConsumption;

    /**
     * Grid constructor
     * sets the class variables width and height and creates an AbstractItem grid and Int stock grid
     * @param height int height of the grid
     * @param width int width of the grid
     */
    Grid(int height, int width){
        this.width = width;
        this.height = height;
        grid = new AbstractItem[height][width];
        stock = new int[height][width];
    }


    /**
     * Returns the width of the grid
     * @return int grid width
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns height of the grid
     * @return int grid height
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Registers an item on the grid at the location passed. If null is passed then the object at that location
     * is deleted. This is used for the hedgehog class.
     * @param xCoordinate
     * @param yCoordinate
     * @param item
     */
    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
        if(item == null){
            grid[yCoordinate][xCoordinate] = null;
        } else {
            // Put the item into the location on the grid
            grid[yCoordinate][xCoordinate] = item;
        }
    }

    /**
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @return
     */
    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {
        // Check if there is an item at location, if T return, else null
        if (grid[yCoordinate][xCoordinate] instanceof AbstractItem){
            return grid[yCoordinate][xCoordinate];
        } else {
            return null;
        }
    }

    /**
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @return
     */
    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        //. Returns amount of stock at a location
        return stock[yCoordinate][xCoordinate];
    }

    /**
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     */
    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {
        //Set the stock at location to 0
        setStockAt(xCoordinate, yCoordinate, 0);
    }

    /**
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        // Increments the stock at a location
        setStockAt(xCoordinate,yCoordinate, (getStockAt(xCoordinate, yCoordinate)+ nutrition));
    }

    /**
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        int currentStock = getStockAt(xCoordinate, yCoordinate);
        setStockAt(xCoordinate, yCoordinate, currentStock -= nutrition);
    }

    /**
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        stock[yCoordinate][xCoordinate] = nutrition;
    }

    /**
     *
     * @param timeStep The time step we are at. This value may be used to determine production frequency of farmers.
     */
    @Override
    public void processItems(TimeStep timeStep) {

        //For farmers
        for (int y = 0; y <= (getHeight()-1); y++) {
            for (int x = 0; x <= getWidth()-1; x++) {
                //if item is an item and a radish farmer
                AbstractItem farmerItem = getItem(x,y);
                if(farmerItem instanceof Farmer){
                    farmerItem.process(timeStep);
                }
            }
        }

        //For transporters
        for (int y = 0; y <= getHeight()-1; y++) {
            for (int x = 0; x <= getWidth()-1; x++) {
                //if item is an item is a transporter
                AbstractItem transporterItem = getItem(x,y);
                if(transporterItem instanceof Transporter){
                    transporterItem.process(timeStep);
                }
            }
        }


        //For consumers
        for (int y = 0; y <= getHeight()-1; y++) {
            for (int x = 0; x <= getWidth()-1; x++) {
                //if item is an item and a consumer
                AbstractItem consumerItem = getItem(x,y);
                if(((consumerItem instanceof Rabbit) || (consumerItem instanceof  Beaver) || (consumerItem instanceof Hedgehog))) {
                    consumerItem.process(timeStep);
                }
            }
        }
    }

    /**
     *
     * @param nutrition the amount
     */
    @Override
    public void recordProduction(int nutrition) {
        totalProduction += nutrition;
    }

    /**
     *
     * @return
     */
    @Override
    public int getTotalProduction() {
        return totalProduction;
    }

    /**
     *
     * @param nutrition the amount
     */
    @Override
    public void recordConsumption(int nutrition) {
        totalConsumption += nutrition;
    }

    /**
     *
     * @return
     */
    @Override
    public int getTotalConsumption() {
        return totalConsumption;
    }


}
