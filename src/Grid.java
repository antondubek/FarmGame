public class Grid extends AbstractGrid{

    private int width;
    private int height;
    private int totalProduction;
    private int totalConsumption;

    Grid(int height, int width){
        this.width = width;
        this.height = height;
        grid = new AbstractItem[height][width];
        stock = new int[height][width];
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
        // Put the item into the location on the grid
        grid[yCoordinate][xCoordinate] = item;
    }

    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {
        // Check if there is an item at location, if T return, else null
        if (grid[yCoordinate][xCoordinate] instanceof AbstractItem){
            return grid[yCoordinate][xCoordinate];
        } else {
            return null;
        }
    }

    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        //. Returns amount of stock at a location
        return stock[yCoordinate][xCoordinate];
    }

    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {
        //Set the stock at location to 0
        setStockAt(xCoordinate, yCoordinate, 0);
    }

    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        // Increments the stock at a location
        setStockAt(xCoordinate,yCoordinate, (getStockAt(xCoordinate, yCoordinate)+ nutrition));
    }

    @Override

    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        int currentStock = getStockAt(xCoordinate, yCoordinate);
        setStockAt(xCoordinate, yCoordinate, currentStock -= nutrition);
    }

    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        stock[yCoordinate][xCoordinate] = nutrition;
    }

    @Override
    public void processItems(TimeStep timeStep) {

        //For farmers
        for (int y = 0; y <= (getHeight()-1); y++) {
            for (int x = 0; x <= getWidth()-1; x++) {
                //if item is an item and a radish farmer
                AbstractItem farmerItem = getItem(x,y);
                if(((farmerItem instanceof RadishFarmer) || (farmerItem instanceof  CornFarmer))){
                    farmerItem.process(timeStep);
                }
            }
        }

        //For transporters
        for (int y = 0; y <= getHeight()-1; y++) {
            for (int x = 0; x <= getWidth()-1; x++) {
                //if item is an item is a transporter
                AbstractItem transporterItem = getItem(x,y);
                if(((transporterItem instanceof VerticalTransporter) || (transporterItem instanceof  HorizontalTransporter)
                 || (transporterItem instanceof NearestTransporter))){
                    transporterItem.process(timeStep);
                }
            }
        }


        //For consumers
        for (int y = 0; y <= getHeight()-1; y++) {
            for (int x = 0; x <= getWidth()-1; x++) {
                //if item is an item and a consumer
                AbstractItem consumerItem = getItem(x,y);
                if(((consumerItem instanceof Rabbit) || (consumerItem instanceof  Beaver))) {
                    consumerItem.process(timeStep);
                }
            }
        }
    }

    @Override
    public void recordProduction(int nutrition) {
        totalProduction += nutrition;
    }

    @Override
    public int getTotalProduction() {
        return totalProduction;
    }

    @Override
    public void recordConsumption(int nutrition) {
        totalConsumption += nutrition;
    }

    @Override
    public int getTotalConsumption() {
        return totalConsumption;
    }


}
