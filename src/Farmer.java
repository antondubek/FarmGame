/** Class which creates an inheritance path for farmers
 * Abstracts out common stock methods for simplicity
 */
class Farmer extends AbstractItem {

    /** Empty process class needed to validate as an AbstractItem
     * this is looked after by individual farmer classes
     * @param timeStep The current time-step
     */
    @Override
    public void process(TimeStep timeStep) {

    }

    /** Gets the stock at the location of the object using the grid.getStockAt method
     *
     * @return nutrition Int stock at that point
     */
    @Override
    protected int getStock() {
        // Retrieve the amount of stock at this location
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    /**Adds to the stock at the location of the object using the grid.addToStockAt method
     *
     * @return nutrition Int stock at that point
     */
    @Override
    protected void addToStock(int nutrition) {
        //Add to stock at this farmers location
        this.grid.addToStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }

    /**Reduces the stock at the location of the object using the grid.reduceToStockAt method
     *
     * @return nutrition Int stock at that point
     */
    @Override
    protected void reduceStock(int nutrition) {
        //Reduece stock at the items location
        this.grid.reduceStockAt(this.xCoordinate, this.yCoordinate, nutrition);
    }

    /**
     * Given a location, checks whether there is a farmer there
     * @param x Coordinate
     * @param y Coordinate
     * @return True if a farmer is found, else false.
     */
    public boolean isFarmer(int x, int y){
        AbstractItem item = grid.getItem(x,y);

        if(item instanceof Farmer){
            return true;
        } else {
            return false;
        }
    }
}
