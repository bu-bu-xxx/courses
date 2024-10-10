/**
 * The InputTest class implments an application that 
 * read one integer from standard input (command line), 
 * and then print "Your input integer is: xxx", xxx is 
 * the read integer. 
 */
// To use class from the library, you need to use the import keyword.
// Here we import java.util.Scanner for reading input.
import java.util.*; 
class InputTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Create a Scanner object named in.
        int a = in.nextInt(); // let in read a integer from command line and assign the value to variable a.
        System.out.println("Your input integer is: " + a);  // Display "Your input integer is: " and variable a.
    }
}
