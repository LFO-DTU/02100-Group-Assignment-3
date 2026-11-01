import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameOfLifeMain {
    public static void main(String[] args) {
        Scanner std_in_scanner = new Scanner(System.in);
        
        Scanner state_file = null;
        while (state_file == null) {
            System.out.print("Enter start state path: ");
            String path = std_in_scanner.nextLine();
            File file = new File(path);
            try {
                state_file = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File " + path + " does not exist.");
            }
        }
        
        System.out.print("Enter time between steps (ms): ");
        while (!std_in_scanner.hasNextInt()) {
            System.out.print("Please enter an integer: ");
            if (std_in_scanner.hasNext()) { std_in_scanner.next(); }
        }
        int delay = std_in_scanner.nextInt();

        System.out.print("Enter iteration count (0 for max): ");
        while (!std_in_scanner.hasNextInt()) {
            System.out.print("Please enter an integer: ");
            if (std_in_scanner.hasNext()) { std_in_scanner.next(); }
        }
        int iterations = std_in_scanner.nextInt();
        iterations = iterations == 0 ? Integer.MAX_VALUE : iterations;
        
        std_in_scanner.close();

        GameOfLife game = new GameOfLife(state_file);
        
        int gridSize = game.getGridSize();
        StdDraw.setCanvasSize(1024,1024);
        StdDraw.setXscale(-0.5, (double)gridSize - 0.5);
        StdDraw.setYscale(-((double)gridSize - 0.5),0.5);
        StdDraw.setPenRadius((double) 2 / gridSize);
        
        for (int i = 0; i <= iterations; i++) {
            StdDraw.clear();
            for (int x = 0; x < gridSize; x++) {
                for (int y = 0; y < gridSize; y++) {
                    if (game.getCell(x,y) == 1) {
                        StdDraw.point(x, -y);
                    }
                }
            }
            StdDraw.show(delay);
            game.nextState();
        }
    }
}
