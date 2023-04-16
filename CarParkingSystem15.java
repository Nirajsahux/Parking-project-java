import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CarParkingSystem15 {

    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 10;

    private static String[][] parkingLot = new String[NUM_ROWS][NUM_COLS];
    private static String[] registeredPlates = new String[NUM_ROWS * NUM_COLS];
    private static String[] parkedPlates = new String[NUM_ROWS * NUM_COLS];
    private static int numRegisteredPlates = 0;
    private static int numParkedPlates = 0;

    public static void main(String[] args) throws IOException {
        initializeParkingLot();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("\nEnter 1 to add a registered vehicle");
            System.out.println("Enter 2 to check if a vehicle is registered");
            System.out.println("Enter 3 to add a parked vehicle");
            System.out.println("Enter 4 to remove a parked vehicle");
            System.out.println("Enter 5 to generate reports");
            System.out.println("Enter q to quit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addRegisteredPlate(scanner);
                    break;
                case "2":
                    checkIfRegistered(scanner);
                    break;
                case "3":
                    addParkedPlate(scanner);
                    break;
                case "4":
                    removeParkedPlate(scanner);
                    break;
                case "5":
                    generateReports();
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        saveParkingLot();
        saveRegisteredPlates();
        saveParkedPlates();
    }

    private static void initializeParkingLot() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                parkingLot[row][col] = "-";
            }
        }
    }

    private static void addRegisteredPlate(Scanner scanner) throws IOException {
        System.out.println("\nEnter the vehicle plate number:");
        String plate = scanner.nextLine();
        if (isRegistered(plate)) {
            System.out.println("Vehicle already registered");
        } else {
            registeredPlates[numRegisteredPlates] = plate;
            numRegisteredPlates++;
            System.out.println("Vehicle registered successfully");
        }
    }

    private static boolean isRegistered(String plate) {
        for (int i = 0; i < numRegisteredPlates; i++) {
            if (registeredPlates[i].equals(plate)) {
                return true;
            }
        }
        return false;
    }

    private static void checkIfRegistered(Scanner scanner) {
        System.out.println("\nEnter the vehicle plate number:");
        String plate = scanner.nextLine();
        if (isRegistered(plate)) {
            System.out.println("Vehicle is registered");
        } else {
            System.out.println("Vehicle is not registered");
        }
    }

    private static void addParkedPlate(Scanner scanner) throws IOException {
        System.out.println("\nEnter the vehicle plate number:");
        String plate = scanner.nextLine();
        if (!isRegistered(plate)) {
            System.out.println("Vehicle is not registered");
        } else if (isParked(plate)) {
            System.out.println("Vehicle is already parked");
        } else {
            boolean parked = false;
            for (int row = 0; row < NUM_ROWS; row++) {
                for (int col = 0; col < NUM_COLS; col++) {
if (parkingLot[row][col].equals("-")) {
parkingLot[row][col] = plate;
parkedPlates[numParkedPlates] = plate;
numParkedPlates++;
System.out.println("Vehicle parked at row " + (row + 1) + ", col " + (col + 1));
parked = true;
break;
}
}
if (parked) {
break;
}
}
if (!parked) {
System.out.println("Parking lot is full");
}
}
}
private static boolean isParked(String plate) {
    for (int i = 0; i < numParkedPlates; i++) {
        if (parkedPlates[i].equals(plate)) {
            return true;
        }
    }
    return false;
}

private static void removeParkedPlate(Scanner scanner) throws IOException {
    System.out.println("\nEnter the vehicle plate number:");
    String plate = scanner.nextLine();
    if (!isRegistered(plate)) {
        System.out.println("Vehicle is not registered");
    } else if (!isParked(plate)) {
        System.out.println("Vehicle is not parked");
    } else {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (parkingLot[row][col].equals(plate)) {
                    parkingLot[row][col] = "-";
                    for (int i = 0; i < numParkedPlates; i++) {
                        if (parkedPlates[i].equals(plate)) {
                            parkedPlates[i] = parkedPlates[numParkedPlates - 1];
                            parkedPlates[numParkedPlates - 1] = null;
                            numParkedPlates--;
                            System.out.println("Vehicle removed from row " + (row + 1) + ", col " + (col + 1));
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
}

private static void generateReports() throws IOException {
    int numEmptySpaces = 0;
    for (int row = 0; row < NUM_ROWS; row++) {
        for (int col = 0; col < NUM_COLS; col++) {
            if (parkingLot[row][col].equals("-")) {
                numEmptySpaces++;
            }
        }
    }

    System.out.println("\n---REPORTS---");
    System.out.println("Number of empty spaces: " + numEmptySpaces);
    System.out.println("Number of registered vehicles: " + numRegisteredPlates);
    System.out.println("Registered vehicles: ");
    for (int i = 0; i < numRegisteredPlates; i++) {
        System.out.println(registeredPlates[i]);
    }
    System.out.println("Number of parked vehicles: " + numParkedPlates);
    System.out.println("Parked vehicles: ");
    for (int i = 0; i < numParkedPlates; i++) {
        System.out.println(parkedPlates[i]);
    }
}

private static void saveParkingLot() throws IOException {
    FileWriter writer = new FileWriter("parking_lot.txt");
    for (int row = 0; row < NUM_ROWS; row++) {
        for (int col = 0; col < NUM_COLS; col++) {
            writer.write(parkingLot[row][col] + " ");
        }
        writer.write("\n");
    }
    writer.close();
}

private static void saveRegisteredPlates() {
    try (FileWriter writer = new FileWriter("registered_plates.txt")) {
        for (int i = 0; i < numRegisteredPlates; i++) {
            writer.write(registeredPlates[i] + "\n");
        }
        System.out.println("Registered plates saved to registered_plates.txt");
    } catch (IOException e) {
        System.out.println("Failed to save registered plates: " + e.getMessage());
    }
}

    
    private static void saveParkedPlates() {
        // implementation code here
    }
}

// Here's an example of how to use this program:

// Enter 1 to add a registered vehicle
// Enter 2 to check if a vehicle is registered
// Enter 3 to add a parked vehicle
// Enter 4 to remove a parked vehicle
// Enter 5 to generate reports
// Enter q to quit

// 1
// Enter the vehicle plate number:
// ABC123
// Vehicle registered successfully

// Enter 1 to add a registered vehicle
// Enter 2 to check if a vehicle is registered
// Enter 3 to add a parked vehicle
// Enter 4 to remove a parked vehicle
// Enter 5 to generate reports
// Enter q to quit

// 1
// Enter the vehicle plate number:
// XYZ789
// Vehicle registered successfully

// Enter 1 to add a registered vehicle
// Enter 2 to check if a vehicle is registered
// Enter 3 to add a parked vehicle
// Enter 4 to remove a parked vehicle
// Enter 5 to generate reports
// Enter q to quit

// 3
// Enter the vehicle plate number:
// ABC123
// Vehicle parked at row 1, col 1

// Enter 1 to add a registered vehicle
// Enter 2 to check if a vehicle is registered
// Enter 3 to add a parked vehicle
// Enter 4 to remove a parked vehicle
// Enter 5 to generate reports
// Enter q to quit

// 3
// Enter the vehicle plate number:
// XYZ789
// Vehicle parked at row 1, col 2

// Enter 1 to add a registered vehicle
// Enter 2 to check if a vehicle is registered
// Enter 3 to add a parked vehicle
// Enter 4 to remove a parked vehicle
// Enter 5 to generate reports
// Enter q to quit

// 4
// Enter the vehicle plate number:
// ABC123
// Vehicle removed from row 1, col 1

// Enter 1 to add a registered vehicle
// Enter 2 to check if a vehicle is registered
// Enter 3 to add a parked vehicle
// Enter 4 to remove a parked vehicle
// Enter 5 to generate reports
// Enter q to quit

// 5
// Report generated successfully

// Enter 1 to add a registered vehicle
// Enter 2 to check if a vehicle is registered
// Enter 3 to add a parked vehicle
// Enter 4 to remove a parked vehicle
// Enter 5 to generate reports
// Enter q to quit

// q
// Parking lot saved successfully
// Registered plates saved successfully
// Parked plates saved successfully