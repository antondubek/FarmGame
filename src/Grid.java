public class Grid extends AbstractGrid{


    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {

    }

    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {
        return null;
    }

    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        return 0;
    }

    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {

    }

    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {

    }

    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {

    }

    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {

    }

    @Override
    public void processItems(TimeStep timeStep) {

    }

    @Override
    public void recordProduction(int nutrition) {

    }

    @Override
    public int getTotalProduction() {
        return 0;
    }

    @Override
    public void recordConsumption(int nutrition) {

    }

    @Override
    public int getTotalConsumption() {
        return 0;
    }
}
