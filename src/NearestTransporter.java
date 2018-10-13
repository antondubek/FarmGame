import java.util.ArrayList;
import java.util.List;

public class NearestTransporter extends AbstractItem {
    private int capacity;
    private List<AbstractItem> farmers;
    private List<AbstractItem> consumers;
    private List<Integer> farmerScores;
    private List<Integer> consumerScores;
    private int farmerIndex;
    private int consumerIndex;
    private AbstractItem nearestFarmer;
    private AbstractItem nearestConsumer;

    public NearestTransporter(Grid grid, int yCoordinate, int xCoordinate, int capacity){
        this.grid = grid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.capacity = capacity;

        grid.registerItem(xCoordinate,yCoordinate, this);
    }

    @Override
    public String toString() {
        return "NT";
    }

    @Override
    public void process(TimeStep timeStep) {
        //collect farmers
        farmers = getFarmers();

        // collect consumers
        consumers = getConsumers();

        //score farmers
        farmerScores = scoreFarmers();

        //score consumers
        consumerScores = scoreConsumers();

        //Get nearest farmer
        nearestFarmer = getNearestFarmer();

        //get lowest consumer
        nearestConsumer = getNearestConsumer();

        if(nearestFarmer != null && nearestConsumer != null){
            // get the stock level at the farmer locationSystem.out.println("DEBUG: getItem return = " + grid[xCoordinate][yCoordinate]);
            int farmerStock = nearestFarmer.getStock();

            if(farmerStock >= capacity){
                //Reduce farmer capacity by capacity
                nearestFarmer.reduceStock(capacity);
                //Increase consumer capacity
                nearestConsumer.addToStock(capacity);
            } else {
                //reduce farmer stock by amount
                nearestFarmer.reduceStock(farmerStock);
                // increase consumer by amount
                nearestConsumer.addToStock(farmerStock);
            }
        }

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

    private List<AbstractItem> getFarmers(){

        //Create an empty list of farmers
        farmers = new ArrayList<AbstractItem>();

        //Find all the farmers in the grid and add them to the list
        for (int y = 0; y <= (grid.getHeight()-1); y++) {
            for (int x = 0; x <= grid.getWidth()-1; x++) {
                //if item is an item and a farmer, add it to the list
                AbstractItem farmerItem = grid.getItem(x,y);
                if(((farmerItem instanceof RadishFarmer) || (farmerItem instanceof  CornFarmer))){
                    farmers.add(farmerItem);
                }
            }
        }
        return farmers;
    }

    private List<AbstractItem> getConsumers(){

        //Create an empty list of consumers
        consumers = new ArrayList<AbstractItem>();

        for (int y = 0; y <= grid.getHeight()-1; y++) {
            for (int x = 0; x <= grid.getWidth()-1; x++) {
                //if item is an item and a consumer add it to the list
                AbstractItem consumerItem = grid.getItem(x,y);
                if(((consumerItem instanceof Rabbit) || (consumerItem instanceof  Beaver))) {
                    consumers.add(consumerItem);
                }
            }
        }
        return consumers;
    }

    private List<Integer> scoreFarmers(){

        // Create an empty arraylist of farmer scores
        farmerScores = new ArrayList<Integer>();

        // Get the x and y score (how far they are away from the NT) and add the absolute values together
        for(AbstractItem farmer : farmers){
            int xCord = farmer.xCoordinate;
            int yCord = farmer.yCoordinate;

            int finalScore = (Math.abs(this.xCoordinate - xCord)) + (Math.abs(this.yCoordinate - yCord));

            //Add the score to the list
            farmerScores.add(finalScore);
        }

        return farmerScores;
    }

    private List<Integer> scoreConsumers(){

        // Create an empty arraylist of farmer scores
        consumerScores = new ArrayList<Integer>();

        // Get the x and y score (how far they are away from the NT) and add the absolute values together
        for(AbstractItem consumer : consumers){
            int xCord = consumer.xCoordinate;
            int yCord = consumer.yCoordinate;

            int finalScore = (Math.abs(this.xCoordinate - xCord)) + (Math.abs(this.yCoordinate - yCord));

            //Add the score to the list
            consumerScores.add(finalScore);
        }

        return consumerScores;
    }

    private AbstractItem getNearestFarmer(){

        //get lowest farmer by iterating through and overriding a value
        Integer lowest = Integer.MAX_VALUE;
        for(int i = 0; i < farmerScores.size(); i++){
            if(farmerScores.get(i) < lowest){
                lowest = farmerScores.get(i);
                farmerIndex = i;
            }
        }

        // Go through and see if there is anything else with the same score
        int matches = 0;
        for(Integer scores: farmerScores){
            if(scores == lowest){
                matches++;
            }
        }

        //if matches == 1 ie only one value of that and no ambiguity
        if(matches == 1){
            nearestFarmer = farmers.get(farmerIndex);
            return nearestFarmer;
        } else {
            return null;
        }
    }

    private AbstractItem getNearestConsumer(){

        //get lowest consumer by iterating through and overriding a value
        Integer lowest = Integer.MAX_VALUE;
        for(int i = 0; i < consumerScores.size(); i++){
            if(consumerScores.get(i) < lowest){
                lowest = consumerScores.get(i);
                consumerIndex = i;
            }
        }

        // Go through and see if there is anything else with the same score
        int matches = 0;
        for(Integer scores: consumerScores){
            if(scores == lowest){
                matches++;
            }
        }

        //if matches == 1 ie only one value of that and no ambiguity
        if(matches == 1){
            nearestConsumer = consumers.get(consumerIndex);
            return nearestConsumer;
        } else {
            return null;
        }
    }
}
