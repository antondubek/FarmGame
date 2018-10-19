import org.junit.Test;

import static org.junit.Assert.*;

public class FarmGameTest {


    /**
     * Checks if the correct closest farmer and consumer are selected
     */
    @Test
    public void selectingCorrectClosest() {
        Grid grid = new Grid(5, 5);
        new CornFarmer(grid, 0, 0);
        RadishFarmer radish = new RadishFarmer(grid, 4, 1);
        NearestTransporter nt = new NearestTransporter(grid,2, 2, 10);
        new Rabbit(grid, 2, 4);
        Beaver beaver = new Beaver(grid, 4, 3);


        Game game = new Game(grid);
        game.run(50);

        assertEquals("Beaver(0)", beaver.toString());
        assertEquals("Radish(0)", radish.toString());
    }

    /**
     * Checks that if there are 2 farmers equidistant, no stock is moved
     */
    @Test
    public void equidistantFarmerTest() {
        Grid grid = new Grid(5, 5);
        CornFarmer corn = new CornFarmer(grid, 0, 1);
        RadishFarmer radish = new RadishFarmer(grid, 4, 1);
        NearestTransporter nt = new NearestTransporter(grid, 2, 2, 10);
        Rabbit rabbit = new Rabbit(grid, 2, 4);
        Beaver beaver = new Beaver(grid, 4, 3);

        Game game = new Game(grid);
        game.run(50);

        assertEquals("Beaver(0)", beaver.toString());
        assertEquals("Rabbit(0)", rabbit.toString());
    }


    /**
     * Checks that if 2 consumers are equidistant, no stock is moved
     */
    @Test
    public void equidistantConsumerTest() {
        Grid grid = new Grid(5, 5);
        new CornFarmer(grid, 0, 0);
        new RadishFarmer(grid, 4, 1);
        new NearestTransporter(grid,2, 2, 10);
        Rabbit rabbit = new Rabbit(grid, 0, 3);
        Beaver beaver = new Beaver(grid, 4, 3);


        Game game = new Game(grid);
        game.run(50);

        assertEquals("Rabbit(0)", rabbit.toString());
        assertEquals("Beaver(0)", beaver.toString());
    }

    /**
     * Checks that up until turn 12 the hedgehog will not consume any food
     */
    @Test
    public void noEatingBefore12(){
        Grid grid = new Grid(1, 3);
        RadishFarmer radish = new RadishFarmer(grid, 0,0);
        HorizontalTransporter HT = new HorizontalTransporter(grid, 0, 1, 10);
        Hedgehog hedgy = new Hedgehog(grid, 0, 2);

        Game game = new Game(grid);
        game.run(11);

        // Up to turn 11, hedgehog should not consume anything
        assertEquals(0, grid.getTotalConsumption());
    }

    /**
     * Tests that the hedgehog can find a free corner successfully
     */
    @Test
    public void checkCorners(){
        Grid grid = new Grid(4, 4);
        CornFarmer corn = new CornFarmer(grid, 2, 0);
        HorizontalTransporter HT = new HorizontalTransporter(grid, 2, 1, 10);
        Hedgehog hedgy = new Hedgehog(grid, 2, 3);

        Game game = new Game(grid);
        game.run(1);

        //
        Integer[] check = hedgy.checkCorners();

        assertEquals(check[0].intValue(), 0);
        assertEquals(check[1].intValue(), 0);
    }

    /**
     * Checks that the hedgehog will not move if all corners are taken
     */
    @Test
    public void allCornersTaken(){
        Grid grid = new Grid(4, 4);
        new Rabbit(grid, 0, 0);
        new Rabbit(grid, 0, 3);
        new Rabbit(grid, 3, 0);
        new Rabbit(grid, 3,3);
        CornFarmer corn = new CornFarmer(grid, 2, 0);
        HorizontalTransporter HT = new HorizontalTransporter(grid, 2, 1, 10);
        Hedgehog hedgy = new Hedgehog(grid, 2, 3);

        Game game = new Game(grid);
        game.run(20);

        // CHeck that the hedgehog is in the same location at end
        AbstractItem item = grid.getItem(3, 2);
        boolean expected = false;
        if (item instanceof Hedgehog){
            expected = true;
        }

        assertEquals(expected, true);
    }

    /**
     * Checks that if the hedgehog cannot find a corner it will not consume and just collect stock
     */
    @Test
    public void unsuccessfulMove(){
        Grid grid = new Grid(1, 3);
        RadishFarmer radish = new RadishFarmer(grid, 0,0);
        HorizontalTransporter HT = new HorizontalTransporter(grid, 0, 1, 10);
        Hedgehog hedgy = new Hedgehog(grid, 0, 2);

        Game game = new Game(grid);
        game.run(21);

        // Unsuccessful move so hedgehog continues to collect but does not consume
        assertEquals(0, grid.getTotalConsumption());
        // Continues to collect stock, 20 turns, radish produces 10 every 3 so 70 by turn 21
        assertEquals(70, grid.getStockAt(2,0));
    }


    /**
     * Checks that if the hedgehog successfully moves, then it consumes at the correct rate
     */
    @Test
    public void successfulMove(){
        Grid grid = new Grid(4, 4);
        CornFarmer corn = new CornFarmer(grid, 2, 0);
        HorizontalTransporter HT = new HorizontalTransporter(grid, 2, 1, 10);
        Hedgehog hedgy = new Hedgehog(grid, 2, 3);

        Game game = new Game(grid);
        game.run(20);

        //Corn produces 25 per 4 turns so 50 by turn 10
        //Hedgehog consumes 2 per turn for turn 12-20 = 18
        // TotalConsumption = 18
        assertEquals(18, grid.getTotalConsumption());
    }

    /**
     * Checks that a transporter will successfully latch onto an object that the hedgehog was blocking after
     * the hedgehog successfully moves.
     */
    @Test
    public void successfulLatch(){
        Grid grid = new Grid(4, 4);
        CornFarmer corn = new CornFarmer(grid, 2, 0);
        HorizontalTransporter HT = new HorizontalTransporter(grid, 2, 1, 30);
        Hedgehog hedgy = new Hedgehog(grid, 2, 2);
        Rabbit rabbit = new Rabbit(grid, 2, 3);

        Game game = new Game(grid);
        game.run(20);

        // Note Transporter capacity increased to 30 for ease of calculation
        // Hedgehog
        //Corn produces 25 per 4 turns so 50 by turn 10
        //Hedgehog consumes 2 per turn for turn 12-20 = 18
        // TotalConsumption = 18

        //Rabbit
        // Corn produces 25 on turn 12, 16, 20 = 75
        // Rabbit can eat 8 per turn saving none
        // So eats 8 on turns 12,16 and 20 = 24

        // 24+18 = 42
        assertEquals(42, grid.getTotalConsumption());
    }




}