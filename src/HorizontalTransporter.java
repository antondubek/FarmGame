/**
 *
 **/
public class HorizontalTransporter extends Transporter {
    private int capacity;

    /**
     *
     * @param grid
     * @param yCoordinate
     * @param xCoordinate
     * @param capacity
     */
    public HorizontalTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity){
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;

        grid.registerItem(xCoordinate,yCoordinate, this);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "HT";
    }

    /**
     *
     * @param timeStep The current time-step
     */
    @Override
    public void process(TimeStep timeStep) {
        // get the row of transporter (xcord)
        // start at x coord of the transporter and go right to find something
        AbstractItem farmer = null;
        AbstractItem consumer = null;
        for(int x = xCoordinate; x < grid.getWidth(); x++){
            AbstractItem item = grid.getItem(x, yCoordinate);
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

        // go left till you find something else
        for(int x = xCoordinate; x >= 0; x--){
            AbstractItem item = grid.getItem(x, yCoordinate);
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
            } else {
                //reduce farmer stock by amount
                farmer.reduceStock(farmerStock);
                // increase consumer by amount
                consumer.addToStock(farmerStock);
            }
        }

    }

    /**
     *
     * @return
     */
    @Override
    protected int getStock() {
        return 0;
    }

    /**
     *
     * @param nutrition The amount of nutrition to add
     */
    @Override
    protected void addToStock(int nutrition) {

    }

    /**
     *
     * @param nutrition The amount of nutrition to subtract
     */
    @Override
    protected void reduceStock(int nutrition) {

    }
}
