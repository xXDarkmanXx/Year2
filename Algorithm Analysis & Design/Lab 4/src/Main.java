import java.util.*;

//Lab 4
public class Main {
	
	public static void main (String args[]){
		
		//Question 1
		System.out.println("Random number by Linear Congruential Method is : " + linearCongruential(3123213));
		System.out.println("Random number by Linear Congruential Method is : " + linearCongruential(27868538));
		
		//Question 2
		Random rand = new Random();
		System.out.println("Next Integer value : " + rand.nextInt(10000));
		System.out.println("Next Long value : " + rand.nextLong());
		System.out.println("Next Float value : " + rand.nextFloat());
		System.out.println("Next Double value : " + rand.nextDouble());
		System.out.println("Next Gaussian value : " + rand.nextGaussian());
		System.out.println("Next Boolean value : " + rand.nextBoolean());
		
		
		//Question 3
		int a[] = {2,53,25,1,44,22,1};
		System.out.println("Original Array " + Arrays.toString(a));
		shuffle(a);
		System.out.println("Shuffled Array " + Arrays.toString(a));
		
	}
	
	//Question 1
	public static long  linearCongruential(long r){
		long a = 104683;
		long b = 104729;
		long m = Integer.MAX_VALUE-1;
		return (a*r + b) % m;
	}
	
	//Question 3 -> Fisher Yates Shuffling Algorithm
	public static void shuffle(int [] array){
		Random a = new Random();
		int randPos;
		for(int i=0; i<array.length;i++){
			randPos = a.nextInt(array.length-i) + i;
			swap(array, i, randPos);
		}
	}
	
	public static void swap(int [] array, int i, int randPos){
		int temp = array[i];
		array[i] = array[randPos];
		array[randPos] = temp;
	}
}
