
public class RadishFarmer extends AbstractItem {

    public RadishFarmer(Grid grid, int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;

        System.out.println("DEBUG: Radish Farmer Constructor");
        grid.registerItem(xCoordinate, yCoordinate, this);

    }

    @Override
    public String toString() {
        return "Radish("+getStock()+")";
        //return "RadishFarmer";
    }

    @Override
    public void process(TimeStep timeStep) {
        System.out.println("DEBUG: Timestep = " + timeStep);

        // If the turn is a multiple of 3
        if((timeStep.getValue()%3) == 0){

            // IF there is nothing around it, check notes!!
            // Add into if statement or another if statement

            //Produce some food
            System.out.println("DEBUG: Creating something");
            // Remember to pass nutrition value not num of radishes
            addToStock(10);
        }
    }

    @Override
    protected int getStock() {
        // Retrieve the amount of stock at this location
        int stock = this.grid.getStockAt(this.xCoordinate, this.yCoordinate);

        //System.out.println("DEBUG: Amount of stock at location = " + stock);
        return stock;
    }

    @Override
    protected void addToStock(int nutrition) {
        //Add to stock at this farmers location
        System.out.println("DEBUG: Stock to add = " + nutrition);
        this.grid.addToStockAt(this.xCoordinate, this.yCoordinate, nutrition);

        //Update total production
        grid.recordProduction(nutrition);
    }

    @Override
    protected void reduceStock(int nutrition) {
        //Reduece stock at the items location
        System.out.println("DEBUG: Reduce stock = "+nutrition);
        this.grid.reduceStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }
}
