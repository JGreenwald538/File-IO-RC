import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileReader("params.txt"));
            double B = Double.parseDouble(scanner.nextLine()); // 12
            double R = Double.parseDouble(scanner.nextLine()); // 500
            double C = Double.parseDouble(scanner.nextLine()); // Convert µF to F
            int tStart = Integer.parseInt(scanner.nextLine()); // 0
            int tEnd = Integer.parseInt(scanner.nextLine()); // 1000
            scanner.close();

            FileWriter writer = new FileWriter("rc.txt");
            double dt = (tEnd - tStart) / 100.0; // Assuming 100 steps
            for (int i = 0; i <= 100; i++) {
                double t = i * dt;
                double v = B * (1 - Math.exp((-t)/(R*C))); // Use the corrected equation
                writer.write(String.format("%.0f  %.5f\n", t, v));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
