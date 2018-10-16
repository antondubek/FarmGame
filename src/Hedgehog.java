import java.util.concurrent.ThreadLocalRandom;

public class Hedgehog extends AbstractItem {

    private boolean isAccepting = true;

    public Hedgehog(Grid grid, int yCoordinate, int xCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }


    @Override
    public String toString() {
        return "Hedgehog("+getStock()+")";
    }

    public boolean isAccepting() {
        return isAccepting;
    }

    @Override
    public void process(TimeStep timeStep) {

        if (timeStep.getValue() == 11){
            //when it is 11, move!
            Integer[] freeCorner = checkCorners();
            if(freeCorner[0] != null){
                moveHouse(freeCorner);
                isAccepting = false;
            }
        } else if (timeStep.getValue() > 11) {
            //hibernate and eat 1 per turn till empty
            if(getStock() >= 2){
                grid.recordConsumption(2);
                reduceStock(2);
            }
        }
    }

    private Integer[] checkCorners(){
        int stock = getStock();
        Integer[] coords = new Integer[2];
        //check for free corners
        if(grid.getItem(0,0) == null){
            //if top left free, move there
            coords[0] = 0;
            coords[1] = 0;
        } else if(grid.getItem(grid.getWidth()-1, 0) == null){
            //move top right
            coords[0] = grid.getWidth()-1;
            coords[1] = 0;
        } else if(grid.getItem(0, grid.getHeight()-1) == null){
            //move bottom left
            coords[0] = 0;
            coords[1] = grid.getHeight()-1;
        } else if(grid.getItem(grid.getWidth()-1, grid.getHeight()-1) == null){
            //move bottom right
            coords[0] = grid.getWidth()-1;
            coords[1] = grid.getHeight()-1;
        }
        return coords;
    }

    private void moveHouse(Integer[] newCoords){
        int newx = newCoords[0];
        int newy = newCoords[1];
        int currentStock = getStock();

        grid.registerItem(newx, newy, this);
        grid.setStockAt(newx, newy, currentStock);
        reduceStock(currentStock);
        grid.registerItem(this.xCoordinate, this.yCoordinate, null);
        this.xCoordinate = newx;
        this.yCoordinate = newy;

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
