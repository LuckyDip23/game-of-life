package co.uk.gdb.gol;

import java.util.Arrays;

/**
 * Simple Game Of Life Implementation:
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by overpopulation.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * TODO allow customizable grid sizes
 * TODO allow option to randomise the grid at startup
 * TODO allow option to explicitly set a starting grid
 * TODO animate it properly
 */
public class GameOfLife implements Runnable{
    private static final int GENERATIONS = 100;
    private static final int DEAD = 0;
    private static final int ALIVE = 1;

    private final int[][] grid = {
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0}
    };

    public static void main(String[] args){
        System.out.println("Welcome to the Game Of Life");

        Thread thread = new Thread(new GameOfLife());
        thread.start();
    }

    protected String renderCell(int y, int x) {
        return grid[y][x] > 0 ? "*" : ".";
    }

    protected int checkCell(int cell, int[] neighbours){
        //Count up the number of alive neighbours
        long liveNeighbours = Arrays.stream(neighbours)
                .filter(neighbour ->  neighbour != DEAD)
                .count();

        if(cell == ALIVE){
            if(liveNeighbours < 2 || liveNeighbours > 3){
                return DEAD;
            }

            if(liveNeighbours == 2 || liveNeighbours == 3 ){
                return ALIVE;
            }
        }

        if(cell == DEAD && liveNeighbours == 3){
            return ALIVE;
        }

        return cell;
    }

    @Override
    public void run() {
        int gridWith = grid[0].length -1;
        int gridHeight = grid.length - 1;

        for(int gen = 0; gen < GENERATIONS; gen++) {
            StringBuilder gridBuilder = new StringBuilder();
            for (int y = 0; y < gridHeight; y++) {
                for (int x = 0; x < gridWith; x++) {
                    int minY = y - 1;
                    int maxY = y + 1;

                    int minX = x - 1;
                    int maxX = x + 1;

                    boolean maxYoK = minX > 0 && maxY <= gridHeight;
                    int[] neighbours = {
                            minY > 0  && minX > 0 ? grid[minY][minX] : -1,
                            minY > 0 ? grid[minY][x] : -1,
                            minY > 0  && maxX <= gridWith ? grid[minY][maxX] : -1,

                            minX > 0 ? grid[y][minX] : -1,
                            maxX > 0 ? grid[y][maxX] : -1,

                            maxYoK ? grid[maxY][minX] : -1,
                            maxYoK ? grid[maxY][x] : -1,
                            maxYoK ? grid[maxY][maxX] : -1
                    };

                    grid[y][x] = checkCell(grid[y][x], neighbours);

                    gridBuilder.append("[").append(renderCell(y, x)).append("]");
                }
                gridBuilder.append("\n");
            }
            System.out.println(gridBuilder.toString());
        }
    }
}
