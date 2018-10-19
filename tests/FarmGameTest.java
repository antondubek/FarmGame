import org.junit.Test;

import static org.junit.Assert.*;

public class FarmGameTest {


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

}