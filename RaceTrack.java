import java.util.Scanner;

public class RaceTrack {
    
    //Bane størrelse
    private static final int SCALE = 2;
    //Målstreg og sejr
    private static boolean started = false;

    // Position
    private static int x = 50*SCALE;
    private static int y = 80*SCALE;

    // Hastighed
    private static int vx = 0;
    private static int vy = 0;
    private static int moves = 0;
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        if(!started)
        {started=true;}
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100*SCALE);
        StdDraw.setYscale(0, 100*SCALE);
        
        drawTrack();
        drawCar(x, y);

        while (true) {

            System.out.print("Indtast retning (1-9): ");

            int move = scanner.nextInt();

            int oldX = x;
            int oldY = y;

            updateVelocity(move);

            x += vx;
            y += vy;

            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(oldX, oldY, x, y);

            drawCar(x, y);
            moves++;

            if (!isOnTrack(x, y)) {
                System.out.println("Crash! Du er kørt udenfor banen.");
                break;
            }
            //Check for sejr
            if (started && crossedFinishLine(oldX, oldY, x, y)) {

                System.out.println("Du vandt på " + moves + " træk!");

                break;
            }
            //Print hastighed
            System.out.println("Hastighed: (" + vx + "," + vy + ")");
        }

        scanner.close();
    }
    private static boolean crossedFinishLine(int oldX, int oldY, int newX, int newY) {
        return oldY > 90*SCALE && newY <= 90*SCALE && newX >= 40*SCALE && newX <= 60*SCALE;
    }
    private static void updateVelocity(int move) {

        switch (move) {

            case 1:
                vx--;
                vy--;
                break;

            case 2:
                vy--;
                break;

            case 3:
                vx++;
                vy--;
                break;

            case 4:
                vx--;
                break;

            case 5:
                break;

            case 6:
                vx++;
                break;

            case 7:
                vx--;
                vy++;
                break;

            case 8:
                vy++;
                break;

            case 9:
                vx++;
                vy++;
                break;

            default:
                System.out.println("Ugyldigt input.");
        }
    }

    private static void drawTrack() {

        // Banen (grå ring)
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

        for (int i = 10*SCALE; i <= 90*SCALE; i++) {
            for (int j = 10*SCALE; j <= 90*SCALE; j++) {

                boolean insideOuter =
                        i >= 10*SCALE && i <= 90*SCALE &&
                        j >= 10*SCALE && j <= 90*SCALE;

                boolean insideInner =
                        i >= 30*SCALE && i <= 70*SCALE &&
                        j >= 30*SCALE && j <= 70*SCALE;

                if (insideOuter && !insideInner) {
                    StdDraw.filledSquare(i, j, 0.5);
                }
            }
        }

        // Startlinje
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(50*SCALE, 90.5*SCALE, 50*SCALE, 70.5*SCALE);
    }

    private static void drawCar(int x, int y) {

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledSquare(x, y, 0.8);
    }

    private static boolean isOnTrack(int x, int y) {

        boolean insideOuter =
                x >= 10*SCALE && x <= 90*SCALE &&
                y >= 10*SCALE && y <= 90*SCALE;

        boolean insideInner =
                x >= 30*SCALE && x <= 70*SCALE &&
                y >= 30*SCALE && y <= 70*SCALE;

        return insideOuter && !insideInner;
    }
}
