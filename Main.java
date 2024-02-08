import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File myObj = new File("input.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter("input.txt");
                Scanner myReader = new Scanner(System.in);
                while (true) {
                    System.out.println("Please enter the coordinates in the following format: x1,y1\nx2,y2 or type 'exit' to quit.");
                    System.out.print("Enter the first set of coordinates: ");
                    String coordinates1 = myReader.nextLine();
                    if (coordinates1.equals("exit")) {
                        myWriter.close();
                        myReader.close();
                        break;
                    }
                    myWriter.write(coordinates1 + "\n");
                    System.out.print("Enter the second set of coordinates: ");
                    String coordinates2 = myReader.nextLine();
                    if (coordinates2.equals("exit")) {
                        myWriter.close();
                        myReader.close();
                        break;
                    }
                    myWriter.write(coordinates2 + "\n");
                }
                myWriter.close();
                myReader.close();

            } else {
                Scanner myReader = new Scanner(myObj);
                FileWriter myWriter = new FileWriter("output.txt");
                while (myReader.hasNextLine()) {
                    String[] coordinates1 = myReader.nextLine().split(",");
                    int x1 = Integer.parseInt(coordinates1[0]);
                    int y1 = Integer.parseInt(coordinates1[1]);
                    String[] coordinates2 = myReader.nextLine().split(",");
                    int x2 = Integer.parseInt(coordinates2[0]);
                    int y2 = Integer.parseInt(coordinates2[1]);
                    double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
                    System.out.println("The distance between the two points is:" + distance);
                    myWriter.write(distance + "\n");
                }
                myReader.close();
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
