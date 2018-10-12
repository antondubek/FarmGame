public class VerticalTransporter extends AbstractItem {
    private int capacity;

    public VerticalTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity){
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    @Override
    public String toString() {
        return "VT";
    }

    @Override
    public void process(TimeStep timeStep) {
        // get the column of transporter (xcord)
        // start at y coord of the transporter and go down to find something
        AbstractItem farmer = null;
        AbstractItem consumer = null;
        for(int y = yCoordinate; y < grid.getHeight(); y++){
            AbstractItem item = grid.getItem(xCoordinate, y);
            if((item instanceof RadishFarmer) || (item instanceof CornFarmer)){
                farmer = item;
                break;
            } else if((item instanceof Rabbit) || (item instanceof Beaver)) {
                consumer = item;
                break;
            }
        }

        // go up till you find something else
        for(int y = yCoordinate; y >= 0; y--){
            AbstractItem item = grid.getItem(xCoordinate, y);
            if((item instanceof RadishFarmer) || (item instanceof CornFarmer)){
                farmer = item;
                break;
            } else if((item instanceof Rabbit) || (item instanceof Beaver)) {
                consumer = item;
                break;
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

    @Override
    protected int getStock() {
        return 0;
    }

    @Override
    protected void addToStock(int nutrition) {

    }

    @Override
    protected void reduceStock(int nutrition) {

    }
}
