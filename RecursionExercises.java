import java.util.*;

public class RecursionExercises {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("1 - factorial\n2 - euclid\n3 - power\n4 - fibonacci"); //menu
		int choice = in.nextInt();
		
		//invoke methods for each choice
		if (choice == 1) {
			System.out.println("enter a number for its factorial: ");
			int n = in.nextInt();
			System.out.println(factorial(n));
		} else if (choice == 2) {
			System.out.println("enter the larger number: ");
			int n1 = in.nextInt();
			System.out.println("enter the smaller number ");
			int n2 = in.nextInt();
			System.out.println(euclid(n1, n2));
		} else if (choice == 3){
			System.out.println("enter a base number: ");
			double b = in.nextDouble();
			System.out.println("enter the power: ");
			double p = in.nextDouble();
			System.out.println(power(b, p));
		} else if (choice == 4){
			System.out.println("enter which digit of fibonacci's sequence you want: ");
			int n = in.nextInt();
			System.out.println(fibonacci(n));
		} else {
			System.out.println("invalid");
		}
        in.close();
		//end main method
	}
	
	public static int factorial(int n) {
		if (n > 1) {
			return n * factorial(n-1); //recursive case; this continually multiplies the number by itself subtracted by 1 
		}
		return 1; //base case; if just one entered, return that
	}
	
	public static int euclid(int n1, int n2) { //euclidean algorithm
		if (n1 == n2) {
			return n1; //rule 1
		} else if (n1 > n2) {
			return euclid(n2, n1-n2); // rule 2
		} else {
			return euclid(n2, n1); // rule 3
		}
		
	}
	
	public static double power(double b, double p) { //not very efficient, can be more if separate odd and even numbers. not as efficient for large #s.
        if (p == 0) {
            return 1; //base case. if the power is 0, product mst be 1
        } else {
            return b * power(b, p - 1.0); //recursive case, decrement p.
        }
	}

    public static int fibonacci(int n) { //this function returns the n digit of fibonacci's sequence
        if (n <= 2) { //if n is less than or equal to 2
            return 1; //base case. the first 2 digits are 1
        } else {
            return fibonacci(n-2) + fibonacci(n-1); //recursive case; add previous 2 numbers
        }
    }
}

