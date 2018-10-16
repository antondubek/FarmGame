class Transporter extends AbstractItem {


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
