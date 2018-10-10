public class RadishFarmer extends AbstractItem {

    public RadishFarmer(Grid grid, int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;
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
