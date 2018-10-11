public class VerticalTransporter extends AbstractItem {
    private int capacity;

    public VerticalTransporter(Grid grid, int xCoordinate, int yCoordinate, int capacity){
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;
    }

    @Override
    public void process(TimeStep timeStep) {
        // get the column of transporter (xcord)
        // start at y=1/0 and go down until you hit a farmer
        for(int y = 0; y < grid.getHeight(); y++){
            AbstractItem item = grid.getItem(xCoordinate, y);
            if((item instanceof RadishFarmer) || (item instanceof CornFarmer)){
                AbstractItem farmer = item;
            }
        }

        for(int y2 = 0; y2 < grid.getHeight(); y2++){
            AbstractItem item2 = grid.getItem(xCoordinate,y2);
            if((item2 instanceof Rabbit) || (item2 instanceof Beaver)){
                AbstractItem consumer = item2;
            }
        }

        // do the same again to find a consumer
        // get the stock level at the farmer location
        // check the stock level compared to capacity
        // take up to 'capacity' or stocklevel from farmer
        // add up to 'capacity' or stocklevel to consumer

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
