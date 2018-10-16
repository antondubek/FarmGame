class Farmer extends AbstractItem {

    @Override
    public void process(TimeStep timeStep) {

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
