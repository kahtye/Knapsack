import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
public class Fractional{
	public static void main(String[] args)throws Exception{
		int n; //number of items in knapsack
		int[] val; //array of all of the values 
		int[] weight; //array of all of the weights
		int m; //maximum capacity
		
		
		Scanner reader = new Scanner(System.in);
		System.out.println("1) Ask user for weights and profits");
		System.out.println("2) Read input.txt");
		System.out.println("3) Run time complexity test");
		
		int input = reader.nextInt();
		
		//Ask the user for all the inputs for the knapsack
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
	
			item[] knap = new item[n];
			for (int i = 0; i < n; i++){
				knap[i] = new item(weight[i],val[i]);
			}
			
			runKnap(knap,n,m,true);
		}
		
		//Read the weights and values from "input.txt"
		//Ask user for max capacity, m
		else if (input == 2){
			ArrayList<Integer> knapsack = new ArrayList<Integer>(); 
			File file = new File("input.txt");
			Scanner in = new Scanner(file);
			while(in.hasNextInt()){
				knapsack.add(in.nextInt());
			}
			n = knapsack.size()/2;
			val = new int[n];
			weight = new int[n];
			in = new Scanner(file);
			int count = 0;
			while(in.hasNextInt()){
				val[count] = in.nextInt();
				weight[count] = in.nextInt();
				count++;
			}
			
			
			item[] knap = new item[n];
			for (int i = 0; i < knap.length; i++){
				knap[i] = new item(weight[i], val[i]);
			}
			
			System.out.print("Knapsack capacity: ");
			m = reader.nextInt();
			runKnap(knap,n,m,true);
		}
		
		//Print the times out for random knapsacks of 10-100000
		else{
			System.out.println("Objects Time");
			for (int j = 10; j <= 100000; j*=2){
				n = j;
				m = j+5;
				val = new int[n];
				weight = new int[n];
				for (int i = 0; i < n; i++){
					val[i] = (int)(Math.random()*100);
					weight[i] = (int)(Math.random()*100);
				}
				item[] knap = new item[n];
				for (int i = 0; i < n; i++){
					knap[i] = new item(weight[i],val[i]);
				}
		
				long start = System.nanoTime();
				runKnap(knap,n,m,false);
				long elapsed = System.nanoTime()-start;
				System.out.println(j + "\t" + elapsed);
			}
		}
		
	}
	
	static void runKnap(item[] knap,int n, int w, boolean print){
		bubbleSort(knap); //run a bubble sort in which the highest densities are first
		
		//only print if not running time complexity test
		if (print){
			System.out.println();
			printArr(knap);
		}
		
		int totalWeight = 0;
		double benefit = 0;
		int wt;
		
		for (int i = 0; i < n; i++){
			
			//If the current knapsack weight plus the total weight is less than 
			//the max weight, add this weight and benefit
			if(knap[i].getWeight() + totalWeight <= w){
				totalWeight += knap[i].getWeight();
				benefit += knap[i].getVal();
			}
			else{
				//If adding this weight is over the max capacity,
				//add the current value divided by the current weight to 
				//the total value
				wt = (w-totalWeight);
				double val = wt*((double)(knap[i].getVal())) / (double)(knap[i].getWeight());
				totalWeight += wt;
				benefit += val;
			}
			
		}
		
		//Only print if not running time complexity test
		if (print){
			System.out.println("Total benefit: " + benefit);
			System.out.println("Total weight: " + totalWeight);
		}
		
		
	}
	
	/**
	 * @param arr
	 * Run a bubble sort on the array
	 */	
	static void bubbleSort(item[] arr){
		int n = arr.length;
	    item temp;
	      for(int i = 0; i < n; i++) {
	         for(int j=1; j < (n-i); j++) {
	            if(arr[j-1].getDensity() < arr[j].getDensity()) { 
	               temp = arr[j-1]; 
	               arr[j-1] = arr[j];
	               arr[j] = temp;
	            } 
	         } 
	      }
	}
	
	/**
	 * @param arr
	 * Print the 2x2 array
	 */
	static void printArr(item[] arr){
		int n = arr.length;
		System.out.println("Item   Val   Weight   Density");
		for (int i = 0; i < n; i++){
			System.out.print(i+1 + "\t" + arr[i].getWeight());
			System.out.print("\t" + arr[i].getVal() + "\t");
			System.out.print(arr[i].getDensity());
			System.out.println();
		}
	}
}