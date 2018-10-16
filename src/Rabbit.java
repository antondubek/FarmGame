public class Rabbit extends Consumer {

    public Rabbit(Grid grid, int yCoordinate, int xCoordinate){
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
}
