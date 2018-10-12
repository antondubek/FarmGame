public class Beaver extends AbstractItem {

    public Beaver(Grid grid, int yCoordinate, int xCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    @Override
    public String toString() {
        return "Beaver(" + getStock() + ")";
    }

    @Override
    public void process(TimeStep timeStep) {

        // get the initial stock level
        int stockLevel = getStock();

        //If there is 55 units or more
        if((stockLevel) > 55){
            grid.recordConsumption(5);
            grid.setStockAt(xCoordinate,yCoordinate,50);
        }
        else if((stockLevel) >= 5){
            //eat 5 nutrition
            grid.recordConsumption(5);
            reduceStock(5);
        } else {
            // eat whatever there is
            grid.recordConsumption(stockLevel);
            reduceStock(stockLevel);
        }
    }

    @Override
    protected int getStock() {
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    @Override
    protected void addToStock(int nutrition) {
        this.grid.addToStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }

    @Override
    protected void reduceStock(int nutrition) {
        this.grid.reduceStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }
}
