public class CornFarmer extends Farmer {

    public CornFarmer(Grid grid, int yCoordinate, int xCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;

        grid.registerItem(xCoordinate, yCoordinate, this);
    }

    @Override
    public String toString() {
        return "Corn("+getStock()+")";
    }

    @Override
    public void process(TimeStep timeStep) {
        // If the turn is a multiple of 3
        if(((timeStep.getValue()%4) == 0) && checkSpace()){

            // IF there is nothing around it, check notes!!
            // Add into if statement or another if statement

            //Produce some food
            // Remember to pass nutrition value not num of radishes
            addToStock(25);

            //Update total production
            grid.recordProduction(25);
        }

    }

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

    private boolean isFarmer(int x, int y){
        AbstractItem item = grid.getItem(x,y);

        if(item instanceof RadishFarmer || item instanceof CornFarmer){
            return true;
        } else {
            return false;
        }
    }
}
