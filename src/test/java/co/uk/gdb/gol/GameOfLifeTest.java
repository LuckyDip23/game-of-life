package co.uk.gdb.gol;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GameOfLifeTest {
    static int ALIVE = 1;
    static int DEAD = 0;

    @Test
    /*
    Any live cell with fewer than two live neighbors dies, as if caused by under population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by overpopulation.
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     */
    public void testRules(){
        GameOfLife application = new GameOfLife();

        int[] neightbourList1 = {0,0,0,0,0,0,0,0};
        assertEquals(application.checkCell(ALIVE, neightbourList1), DEAD);

        int[] neightbourList2 = {1,0,0,0,0,0,0,0};
        assertEquals(application.checkCell(ALIVE, neightbourList2), DEAD);

        int[] neightbourList3 = {1,1,0,0,0,0,0,0};
        assertEquals(application.checkCell(ALIVE, neightbourList3), ALIVE);

        int[] neightbourList4 = {1,1,1,0,0,0,0,0};
        assertEquals(application.checkCell(ALIVE, neightbourList4), ALIVE);

        int[] neightbourList5 = {1,1,1,1,0,0,0,0};
        assertEquals(application.checkCell(ALIVE, neightbourList5), DEAD);

        int[] neightbourList6 = {1,1,1,1,1,0,0,0};
        assertEquals(application.checkCell(ALIVE, neightbourList6), DEAD);

        int[] neightbourList7 = {1,1,1,1,1,1,0,0};
        assertEquals(application.checkCell(ALIVE, neightbourList7), DEAD);

        int[] neightbourList8 = {1,1,1,1,1,1,1,0};
        assertEquals(application.checkCell(ALIVE, neightbourList8), DEAD);

        int[] neightbourList9 = {1,1,1,1,1,1,1,1};
        assertEquals(application.checkCell(ALIVE, neightbourList9), DEAD);

        int[] neightbourList10 = {1,1,1,0,0,0,0,0};
        assertEquals(application.checkCell(DEAD, neightbourList10), ALIVE);
    }
}
