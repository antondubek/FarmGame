import java.util.ArrayList;
import java.util.List;

/**
 * Nearest Transporter Class contains methods for a transporter to move stock between the closest farmer and
 * consumer to it. If there are two farmers or two consumers equidistant then it will move nothing.
 **/
public class NearestTransporter extends Transporter {
    private int farmerIndex;
    private AbstractItem nearestFarmer;
    private AbstractItem nearestConsumer;

    /**
     *Horizontal Transporter Constructor
     * Sets local parameters of x, y, grid and capacity
     * Registers the item into the grid
     * @param grid Grid grid which the object needs to belong to and be put into
     * @param yCoordinate int Y coordinate of where the item is to be on the grid
     * @param xCoordinate int x coordinate of where the item is to be on the grid
     * @param capacity int the amount of stock (in nutrition) that the transporter can move per timestep
     */
    public NearestTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity){
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;

        grid.registerItem(xCoordinate,yCoordinate, this);
    }

    /**
     * Returns the transporters name to display on the grid.
     * @return String name
     */
    @Override
    public String toString() {
        return "NT";
    }

    /**
     * Transporter gets the closest consumer and farmer using methods below and depending on the
     * stock levels will transport up to the stock amount between a farmer and consumer. Will run
     * once and cache the objects so only needs to scan once saving resources.
     * Check is also conducted at timeStep 12 for hedgehog moving step 11
     * @param timeStep The current time-step
     */
    @Override
    public void process(TimeStep timeStep) {

        if(timeStep.getValue() == 1 || timeStep.getValue() == 12) {
            //collect farmers
            List<AbstractItem> farmers = getFarmers();

            // collect consumers
            List<AbstractItem> consumers = getConsumers();

            //score farmers
            List<Integer> farmerScores = scoreObjects(farmers);

            //score consumers
            List<Integer> consumerScores = scoreObjects(consumers);

            //Get nearest farmer
            nearestFarmer = getNearest(farmers, farmerScores);

            //get lowest consumer
            nearestConsumer = getNearest(consumers, consumerScores);
        }

        if(nearestFarmer != null && nearestConsumer != null){
            moveStock(nearestFarmer, nearestConsumer);
        }
    }

    /**
     * Scans the whole grid looking looking for farmers
     * @returns List<ArrayList> of farmers within the grid
     */
    private List<AbstractItem> getFarmers(){

        //Create an empty list of farmers
        List<AbstractItem> allfarmers = new ArrayList<>();

        //Find all the farmers in the grid and add them to the list
        for (int y = 0; y <= (grid.getHeight()-1); y++) {
            for (int x = 0; x <= grid.getWidth()-1; x++) {
                //if item is an item and a farmer, add it to the list
                AbstractItem farmerItem = grid.getItem(x,y);
                if(farmerItem instanceof Farmer){
                    allfarmers.add(farmerItem);
                }
            }
        }
        return allfarmers;
    }

    /**
     * Scans the whole grid looking looking for Consumers
     * @returns List<ArrayList> of consumers within the grid
     */
    private List<AbstractItem> getConsumers(){

        //Create an empty list of consumers
        List<AbstractItem> allConsumers = new ArrayList<>();

        for (int y = 0; y <= grid.getHeight()-1; y++) {
            for (int x = 0; x <= grid.getWidth()-1; x++) {
                //if item is an item and a consumer add it to the list
                AbstractItem consumerItem = grid.getItem(x,y);
                if(consumerItem instanceof Consumer) {
                    allConsumers.add(consumerItem);
                }
            }
        }
        return allConsumers;
    }

    /**
     * Taking a list of objects within the grid, method will score the objects based on there x and y
     * coordinates in relation to the transporter. A higher score is further away and a lower score
     * is closer.
     * @param objectList A list of abstract items of consumer or farmer from within the grid
     * @return A list of integers with the corresponding scores in the same location as passed param list
     */
    private List<Integer> scoreObjects(List<AbstractItem> objectList){

        // Create an empty arraylist of farmer scores
        List<Integer> scores = new ArrayList<>();

        // Get the x and y score (how far they are away from the NT) and add the absolute values together
        for(AbstractItem farmer : objectList){
            int xCord = farmer.xCoordinate;
            int yCord = farmer.yCoordinate;

            int finalScore = (Math.abs(this.xCoordinate - xCord)) + (Math.abs(this.yCoordinate - yCord));

            //Add the score to the list
            scores.add(finalScore);
        }

        return scores;
    }

    /**
     * For a list of objects and there corresponding score, will find the object with the lowest score
     * and ensure that there is no other objects with the same score. Will then return the object if
     * it has the lowest or return null if another matching score is found.
     * @param objectList List of objects
     * @param objectScores List of the corresponding scores
     * @return AbstractItem with the lowest score or null if 2 of the same score are found
     */
    private AbstractItem getNearest(List<AbstractItem> objectList, List<Integer> objectScores){

        //get lowest farmer by iterating through and overriding a value
        Integer lowest = Integer.MAX_VALUE;
        for(int i = 0; i < objectScores.size(); i++){
            if(objectScores.get(i) < lowest){
                lowest = objectScores.get(i);
                farmerIndex = i;
            }
        }

        // Go through and see if there is anything else with the same score
        int matches = 0;
        for(Integer scores: objectScores){
            if(scores.equals(lowest)){
                matches++;
            }
        }

        //if matches == 1 ie only one value of that and no ambiguity
        if(matches == 1){
            return objectList.get(farmerIndex);
        } else {
            return null;
        }
    }

}
