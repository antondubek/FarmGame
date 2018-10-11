public class Rabbit extends AbstractItem {

    public Rabbit(Grid grid, int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    @Override
    public String toString() {
        return "Rabbit("+getStock()+")";
    }

    @Override
    public void process(TimeStep timeStep) {

        //Get the initial stock level
        int stockLevel = getStock();

        System.out.println("StockLevel = " + stockLevel);

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

    @Override
    protected int getStock() {
        // Retrieve the amount of stock at this location
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    @Override
    protected void addToStock(int nutrition) {
        //Add to stock at this farmers location
        this.grid.addToStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }

    @Override
    protected void reduceStock(int nutrition) {
        // Rabbit has no stock so stock reduced to 0 when finished consuming
        this.grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
    }
}
