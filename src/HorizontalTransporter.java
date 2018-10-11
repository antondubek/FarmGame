public class HorizontalTransporter extends AbstractItem {
    private int capacity;

    public HorizontalTransporter(Grid grid, int xCoordinate, int yCoordinate, int capacity){
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;

        grid.registerItem(xCoordinate,yCoordinate, this);
    }

    @Override
    public String toString() {
        return "HT";
    }

    @Override
    public void process(TimeStep timeStep) {
        // get the column of transporter (xcord)
        // start at y=1/0 and go down until you hit a farmer
        AbstractItem farmer = null;
        for(int y = 0; y < grid.getHeight(); y++){
            AbstractItem item = grid.getItem(xCoordinate, y);
            if((item instanceof RadishFarmer) || (item instanceof CornFarmer)){
                farmer = item;
            }
        }

        // do the same again to find a consumer
        AbstractItem consumer = null;
        for(int y = 0; y < grid.getHeight(); y++){
            AbstractItem item2 = grid.getItem(xCoordinate,y);
            if((item2 instanceof Rabbit) || (item2 instanceof Beaver)){
                consumer = item2;
            }
        }

        System.out.println(farmer);
        System.out.println(consumer);

        // Check we have found 2 things
        if((farmer != null) && (consumer != null)){
            // get the stock level at the farmer locationSystem.out.println("DEBUG: getItem return = " + grid[xCoordinate][yCoordinate]);
            int farmerStock = farmer.getStock();
            System.out.println(farmerStock);

            if(farmerStock >= capacity){
                System.out.println("true1");
                //Reduce farmer capacity by capacity
                farmer.reduceStock(capacity);
                //Increase consumer capacity
                consumer.addToStock(capacity);
            } else {
                System.out.println("true2");
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
