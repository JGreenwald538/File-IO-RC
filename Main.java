import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Read parameters from params.txt
            File inputFile = new File("params.txt");
            Scanner scanner = new Scanner(inputFile);
            double B = Double.parseDouble(scanner.nextLine()); // Voltage in volts
            double R = Double.parseDouble(scanner.nextLine()); // Resistance in ohms
            double C = Double.parseDouble(scanner.nextLine()); // Capacitance in microfarads, converted to farads
            int tStart = Integer.parseInt(scanner.nextLine()); // Start time in microseconds
            int tEnd = Integer.parseInt(scanner.nextLine()); // End time in microseconds
            scanner.close();

            // Calculate and write v(t) to rc.txt
            FileWriter writer = new FileWriter("rc.txt");
            int steps = 100;
            for (int i = 0; i <= steps; i++) {
                double t = tStart + (tEnd - tStart) * i / steps;
                double v = B * (1 - Math.exp(-t / (R * C))); // Correctly convert time to seconds for the formula
                writer.write(String.format("%.0f  %.5f\n", t, v));
            }
            writer.close();

            // Calculate rise time from 0.06B to 0.95B
            scanner = new Scanner(new File("rc.txt"));
            double t0_06B = -1, t0_95B = -1;
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("  ");
                double t = Double.parseDouble(parts[0]);
                double v = Double.parseDouble(parts[1]);

                if (v >= 0.06 * B && t0_06B == -1) {
                    t0_06B = t; // Time when v(t) first exceeds 0.06B
                }

                if (v >= 0.95 * B && t0_95B == -1) {
                    t0_95B = t; // Time when v(t) first exceeds 0.95B
                    break; // Stop searching once both thresholds are reached
                }
            }
            scanner.close();

            if (t0_06B != -1 && t0_95B != -1) {
                System.out.println("Rise time from 0.06B to 0.95B: " + (t0_95B - t0_06B) + " µs");
            } else {
                System.out.println("Could not calculate rise time.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
