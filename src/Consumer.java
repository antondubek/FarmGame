class Consumer extends AbstractItem {

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
        // Rabbit has no stock so stock reduced to 0 when finished consuming
        this.grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
    }

}
