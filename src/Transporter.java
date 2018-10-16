/**
 * Class which creates an inheritence path for transporters
 * Mainly used for simplicity of checking whether an item is a transporter
 * and abstracting methods
 */
class Transporter extends AbstractItem {


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
}
