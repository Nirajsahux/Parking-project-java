/* TOPIC FOR JAVA PROJECT:
#Implement a car-parking system that records which cars are currently parked in the car park. 
Only registered cars must be allowed to enter the car park, 
so the system must be able to keep a list of authorized vehicles (and names of owners) 
as well as those actually parked at a particular time. 
The system must be able to provide a series of reports, 
for example indicating whether or not the car park is full, 
displaying lists of registered vehicles and those parked. */
/************************************************************/

/*As,for the given topic we can impliment a Double Dimensional Array to represent the area of the parking.
And the area having the respected number of slots to hold total numbers of car.*/

import java.util.Scanner;
public class CarParkingAreaFile02
{
    public static void main(String arg[])
    {
        Scanner ob=new Scanner(System.in);
        System.out.println("********* Car Parking System *********");
        System.out.println("Enter the number of Row the Car Parking Area contain: ");
        int row=ob.nextInt();
        System.out.println("Enter the number of Col. the Car Parking Area contain: ");
        int col=ob.nextInt();
        int[][] Area = new int[row][col];
        System.out.println("As,for the given data the Car Parking Area contains repected slots: ");
        for (int i = 0; i < Area.length; i++) {
            for (int j = 0; j < Area[i].length; j++) {
                System.out.print((char)(65+i));
                System.out.print(j+"\t");
            }
            System.out.println();
        }
        System.out.println("Now, you can slot the car as for Slot given nos.");
        int newValue=ob.nextInt();
if (row >= 0 && row < Area.length && col >= 0 && col < Area[row].length) {
    // Replace the element
    Area[row][col] = newValue;
    System.out.println("Element replaced.");
} else {
    System.out.println("Invalid row or column number.");
}
    }
}
