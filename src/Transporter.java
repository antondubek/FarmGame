/**
 * Class which creates an inheritence path for transporters
 * Mainly used for simplicity of checking whether an item is a transporter
 * and abstracting methods
 */
class Transporter extends AbstractItem {

    public int capacity;
    public AbstractItem farmer;
    public AbstractItem consumer;


    /** Empty process class needed to validate as an AbstractItem
     * this is looked after by individual transporter classes
     * @param timeStep The current time-step
     */
    @Override
    public void process(TimeStep timeStep) {

    }

    /**
     * The transporters do not use these methods so 0 is returned
     * @return
     */
    @Override
    protected int getStock() {
        return 0;
    }

    /**
     * The transporters do not use these methods
     * @param nutrition The amount of nutrition to add
     */
    @Override
    protected void addToStock(int nutrition) {

    }

    /**
     *
     * @param nutrition The amount of nutrition to subtract
     */
    @Override
    protected void reduceStock(int nutrition) {

    }

    /**
     * Takes the stock from a farmer and moves it to a consumer
     * @param farmer AbstractItem farmer to move from
     * @param consumer AbstractItem consumer to move to
     */
    public void moveStock(AbstractItem farmer, AbstractItem consumer){
        // get the stock level at the farmer locationSystem.out.println("DEBUG: getItem return = " + grid[xCoordinate][yCoordinate]);
        int farmerStock = farmer.getStock();

        if(farmerStock >= capacity){
            //Reduce farmer capacity by capacity
            farmer.reduceStock(capacity);
            //Increase consumer capacity
            consumer.addToStock(capacity);
        } else { ;
            //reduce farmer stock by amount
            farmer.reduceStock(farmerStock);
            // increase consumer by amount
            consumer.addToStock(farmerStock);
        }
    }

    public boolean processFoundItem(AbstractItem item){
        if(item instanceof Farmer){
            farmer = item;
            return true;
        } else if(item instanceof Consumer) {
            if((item instanceof Hedgehog)){
                if(((Hedgehog) item).isAccepting()){
                    consumer = item;
                    return true;
                } else {
                    consumer = null;
                    return true;
                    }
            } else {
                consumer = item;
                return true;
            }
        } else {
            if(consumer instanceof Hedgehog){
                consumer = null;
                return false;
            } else{
                return false;
            }
        }
    }


}
