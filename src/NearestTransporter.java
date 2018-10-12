import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NearestTransporter extends AbstractItem {
    private int capacity;
    private List<AbstractItem> farmers;
    private List<AbstractItem> consumers;
    private List<Integer> farmerScores;
    private List<Integer> consumerScores;
    private int index;

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

        for(Integer ints: farmerScores){
            System.out.println(ints);
        }
        //get lowest farmer
        Integer lowest = Integer.MAX_VALUE;
        for(int i = 0; i < farmerScores.size(); i++){
            if(farmerScores.get(i) < lowest){
                lowest = farmerScores.get(i);
                index = i;
            }
        }

        // See if list contains another value the same
        int matches = 0;
        for(Integer scores: farmerScores){
            if(scores == lowest){
                matches++;
            }
        }

        //if matches == 1 ie only one value of that
        if(matches == 1){
            //
        }

        //get lowest consumer

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
                //if item is an item and a radish farmer
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
                //if item is an item and a consumer
                AbstractItem consumerItem = grid.getItem(x,y);
                if(((consumerItem instanceof Rabbit) || (consumerItem instanceof  Beaver))) {
                    consumers.add(consumerItem);
                }
            }
        }
        return consumers;
    }

    private List<Integer> scoreFarmers(){

        farmerScores = new ArrayList<Integer>();

        for(AbstractItem farmer : farmers){
            int xCord = farmer.xCoordinate;
            int yCord = farmer.yCoordinate;

            int finalScore = (Math.abs(this.xCoordinate - xCord)) + (Math.abs(this.yCoordinate - yCord));

            farmerScores.add(finalScore);
        }

        return farmerScores;
    }

    private List<Integer> scoreConsumers(){

        consumerScores = new ArrayList<Integer>();

        for(AbstractItem consumer : consumers){
            int xCord = consumer.xCoordinate;
            int yCord = consumer.yCoordinate;

            int finalScore = (Math.abs(this.xCoordinate - xCord)) + (Math.abs(this.yCoordinate - yCord));

            consumerScores.add(finalScore);
        }

        return consumerScores;
    }
}
