/**
 * Type of farmer class that produces 5 corn equalling 25 nutrition every 4 turns
 * as long as there is no other farmers 2 above or below, or either side of it.
 */
public class CornFarmer extends Farmer {

    /**
     * Corn Farmer Constructor
     * Sets local parameters of x, y and grid
     * Registers the item into the grid
     * @param grid Grid grid which the object needs to belong to and be put into
     * @param yCoordinate int Y coordinate of where the item is to be on the grid
     * @param xCoordinate int x coordinate of where the item is to be on the grid
     */
    public CornFarmer(Grid grid, int yCoordinate, int xCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    /**
     * Returns the farmers name to display on the grid
     * @return String name(stock level)
     */
    @Override
    public String toString() {
        return "Corn("+getStock()+")";
    }

    /**
     * Checks if the timestep is a multiple of 4 and there is the needed space
     * before producing 25 nutrition and adding it to the stock level.
     * @param timeStep The current time-step
     */
    @Override
    public void process(TimeStep timeStep) {
        // If the turn is a multiple of 3
        if(((timeStep.getValue()%4) == 0) && checkSpace()){
            //Produce some food
            // Remember to pass nutrition value not num of radishes
            addToStock(25);

            //Update total production
            grid.recordProduction(25);
        }

    }

    /**
     * Takes the objects location and checks 1 space up and down and 2 spaces left and right
     * to determine if there is another farmer.
     * @return True if there are no farmers, false if a farmer is found
     */
    private boolean checkSpace(){
        // if there is space in 4 directions then return true
        boolean xROkay = false;
        boolean xLOkay = false;
        boolean yUOkay = false;
        boolean yDOkay = false;

        //check left
        if(xCoordinate == 0){
            xLOkay = true;
        } else if((xCoordinate == 1) && !(isFarmer(xCoordinate-1, yCoordinate))){
            xLOkay = true;
        } else if ((xCoordinate > 1) && !(isFarmer(xCoordinate-1, yCoordinate)) && !(isFarmer(xCoordinate-2, yCoordinate))){
            xLOkay = true;
        }

        //check right
        if(xCoordinate == grid.getWidth()-1){
            xROkay = true;
        } else if((xCoordinate == grid.getWidth()-2) && !(isFarmer(xCoordinate+1, yCoordinate))){
            xROkay = true;
        } else if ((xCoordinate < grid.getWidth()-2) && !(isFarmer(xCoordinate+1, yCoordinate)) && !(isFarmer(xCoordinate+2, yCoordinate))){
            xROkay = true;
        }

        //check Up
        if(yCoordinate == 0){
            yUOkay = true;
        } else if (!(isFarmer(xCoordinate, yCoordinate-1))){
            yUOkay = true;
        }

        //check Down
        if(yCoordinate == grid.getHeight()-1){
            yDOkay = true;
        } else if (!(isFarmer(xCoordinate, yCoordinate+1))){
            yDOkay = true;
        }

        if(xLOkay && xROkay && yUOkay && yDOkay){
            return true;
        } else {
            return false;
        }

    }

}
