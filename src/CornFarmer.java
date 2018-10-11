public class CornFarmer extends AbstractItem {

    public CornFarmer(Grid grid, int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    @Override
    public String toString() {
        return "Corn("+getStock()+")";
    }

    @Override
    public void process(TimeStep timeStep) {
        // If the turn is a multiple of 3
        if((timeStep.getValue()%4) == 0){

            // IF there is nothing around it, check notes!!
            // Add into if statement or another if statement

            //Produce some food
            // Remember to pass nutrition value not num of radishes
            addToStock(25);

            //Update total production
            grid.recordProduction(25);
        }

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
        //Reduece stock at the items location
        this.grid.reduceStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }
}
