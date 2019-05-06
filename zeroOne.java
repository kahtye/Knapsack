import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class zeroOne {
	public static void main(String[] args)throws Exception{
		int n; //The number of items
		int[] val; //An array with all of the values 
		int[] weight; // A corresponding array containing the weights
		int m; //The knapsack capacity
		
		
		Scanner reader = new Scanner(System.in);
		System.out.println("1) Ask user for weights and profits");
		System.out.println("2) Read input.txt");
		System.out.println("3) Run time complexity test");
		
		int input = reader.nextInt();
		
		//Ask user to manually input weights and profits
		if (input == 1){
			System.out.print("Number of objects:");
			n = reader.nextInt();
			val = new int[n];
			weight = new int[n];
			
			System.out.print(n + " weights(separate by space, enter when done): ");
			for (int i = 0; i < n; i++){
				weight[i] = reader.nextInt();
			}
			
			System.out.print(n + " profits(separate by space, enter when done): ");
			for (int i = 0; i < n; i++){
				val[i] = reader.nextInt();
			}
			
			System.out.print("Knapsack capacity: ");
			m = reader.nextInt();
	
			int knapSack = knap(m,weight,val,n);
			System.out.println(knapSack);
		}
		
		//Read a text file "input.txt" which contains weights and values
		//Ask user for knapsack capacity
		else if (input == 2){
			//Create arraylist to read all values on "input.txt"
			ArrayList<Integer> knapsack = new ArrayList<Integer>(); 
			File file = new File("input.txt");
			Scanner in = new Scanner(file);
			
			//Put all items in arraylist
			while(in.hasNextInt()){
				knapsack.add(in.nextInt());
			}
			
			n = knapsack.size()/2;
			val = new int[n];
			weight = new int[n];
			in = new Scanner(file);
			int count = 0;
			
			//Put the values from the arraylist into the proper values and weights array
			while(in.hasNextInt()){
				val[count] = in.nextInt();
				weight[count] = in.nextInt();
				count++;
			}
			
			
			System.out.print("Knapsack capacity: ");
			m = reader.nextInt();
			int k = knap(m,weight,val,n); //Run the 0/1 algorithm
			System.out.println("Total profit: " + k);
		}
		
		//Run time complexity test for objects 10-120
		else{
			System.out.println("Objects  time");
			for (int j = 10; j <= 120; j+=10){
				n = j;
				m = j+5;
				val = new int[n];
				weight = new int[n];
				for (int i = 0; i < n; i++){
					val[i] = (int)(Math.random()*100);
					weight[i] = (int)(Math.random()*100);
				}
				long start = System.nanoTime();
				int knapSack = (knap(m,weight,val,n));
				long elapsed = System.nanoTime() - start;
				System.out.println(j + "\t" + elapsed);
			}
		}
		
	}	
	
	/**
	 * @param a
	 * @param b
	 * @return the greater value between a and b
	 */
	static int max(int a, int b){
		if (a > b){
			return a;
		}
		return b;
	}
	
	/**
	 * @param m
	 * @param weight
	 * @param val
	 * @param n
	 * @return max profit
	 */
	static int knap(int m, int weight[], int val[], int n){
		
		//Base case
		if (n == 0 || m == 0){
			return 0;
		}
		
		//If the weight is greater than the knapsack capacity
		if (weight[n-1] > m){
			return knap(m,weight,val,n-1);
		}
		
		
		else{
			//First value is the value[n-1] + knapsack of the previous value
			// with capactity of capacity - the previous weight
			int first = (val[n-1] + knap(m-weight[n-1],weight,val,n-1));
			
			//Second value is the knapsack of the proceeding value
			int second = knap(m,weight,val,n-1);
			
			//get the max of the first and the second
			int maximum = max(first,second);
			
			//return the max
			return maximum;
		}
	}
	
}
