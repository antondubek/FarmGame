public class VerticalTransporter extends AbstractItem {

    public VerticalTransporter(Grid grid, int xCoordinate, int yCoordinate){
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public void process(TimeStep timeStep) {

    }

    @Override
    protected int getStock() {
        return 0;
    }

    @Override
    protected void addToStock(int nutrition) {

    }

    @Override
    protected void reduceStock(int nutrition) {

    }
}
