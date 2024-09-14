import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Point {
    int x;
    int y;
    String color;

    Point(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}

public class PointVisualizer extends JFrame {
    private List<Point> points;

    public PointVisualizer(List<Point> points) {
        this.points = points;
        setTitle("Point Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void paint(Graphics g) {
        super.paint(g);

        for (Point point : points) {
            int scaledX = point.x + getWidth() / 2; // Adjusting to fit the window
            int scaledY = getHeight() / 2 - point.y; // Adjusting to fit the window

            switch (point.color) {
                case "R":
                    g.setColor(Color.RED);
                    break;
                case "G":
                    g.setColor(Color.GREEN);
                    break;
                case "B":
                    g.setColor(Color.BLUE);
                    break;
                case "P":
                    g.setColor(new Color(128, 0, 128)); // Purple
                    break;
                default:
                    g.setColor(Color.BLACK);
            }

            g.fillOval(scaledX - 5, scaledY - 5, 10, 10);
        }
    }

    public static void main(String[] args) {
        List<Point> points = generatePoints();

        SwingUtilities.invokeLater(() -> {
            PointVisualizer visualizer = new PointVisualizer(points);
            visualizer.setVisible(true);
        });
    }

    public static List<Point> generatePoints() {
        List<Point> points = new ArrayList<>();

        // Define the starting points for each class
        int[][] startingPoints = {
                {-4500, -4400}, {-4100, -3000}, {-1800, -2400}, {-2500, -3400}, {-2000, -1400}, // RED
                {0, 0},                                                                                 // GREEN
                {4000, 1000}, {2500, 1000}, {1500, 2500}, {3000, 3500}, {4500, 2500},                 // BLUE
                {1500, 4500}, {3000, 4500}, {2000, 5000}, {3500, 5000}, {4000, 4500},                 // PURPLE
        };

        String[] colors = {"R", "G", "B", "P"};

        for (int i = 0; i < startingPoints.length; i++) {
            int[] point = startingPoints[i];
            String color = colors[i / 5];
            points.add(new Point(point[0], point[1], color));
        }

        return points;
    }
}