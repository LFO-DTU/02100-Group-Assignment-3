import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfLife {
    private int[][] state;

    public GameOfLife(int n) {
        this.state = new int[n][n];
    }

    public GameOfLife(int[][] inititalState) {
        this.state = inititalState;
    }

    public GameOfLife(Scanner file) {
        List<Integer> cells = new ArrayList<>();
        while(file.hasNextInt()) {
            cells.add(file.nextInt());
        }
        int size = (int) Math.sqrt(cells.size());
        int[][] parsed_state = new int[size][size];
        for (int i = 0; i < cells.size(); i++) {
            parsed_state[i / size][i % size] = cells.get(i);
        }
        this.state = parsed_state;
    }

    public void nextState() {
        int[][] new_state = new int[state.length][state[0].length];
        for (int y = 0; y < state.length; y++) {
            for (int x = 0; x < state[y].length; x++) {
                int living_neighbours = countLivingNeighbours(x,y);
                if (state[y][x] == 1 && living_neighbours < 2) {
                    new_state[y][x] = 0;
                } else if (state[y][x] == 1 && living_neighbours > 3) {
                    new_state[y][x] = 0;
                } else if (state[y][x] == 0 && living_neighbours == 3) {
                    new_state[y][x] = 1;
                } else {
                    new_state[y][x] = state[y][x];
                }
            }
        }
        state = new_state;
    }

    // Considers all out of bounds cells dead
    public int getCell(int x, int y) {
        if (y >= 0 && y < state.length && x >= 0 && x < state[y].length) {
            return state[y][x];
        }
        return 0;
    }

    public void setCell(int x, int y, int val) {
        if (y >= 0 && y < state.length && x >= 0 && x < state[y].length) {
            state[y][x] = val > 0 ? 1 : 0;
        }
    }

    public int getGridSize() {
        return state.length;
    }

    private int countLivingNeighbours(int x, int y) {
        return getCell(x-1,y-1) + getCell(x, y-1) + getCell(x+1, y-1)
            +  getCell(x-1,y)   +                 + getCell(x+1, y)
            +  getCell(x-1,y+1) + getCell(x, y+1) + getCell(x+1, y+1);
    }
}
