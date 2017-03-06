import java.util.*;

public class Main {
	
	public static void main(String args[]){
		//int x [] = randomArray(1000);
		//System.out.println(Arrays.toString(x));
		//bucketSort(x);
		//radixSort(x);
		//countingSort(randomArray(100000));
		//selectionSort(randomArray(100000));
		//insertionSort(randomArray(100000));
		bubbleSort(randomArray(100000));
		shellSort(randomArray(100000));
		//System.out.println(Arrays.toString(x));
	}
	
	//Random Array Generator
	public static int [] randomArray(int size){
		int x [] = new int[size];
		Random rand = new Random();
		for(int i=0;i<x.length;i++)		x[i]=rand.nextInt(5000);
		return x;
	}
	
	//Question 1 - Bubble Sort
	public static void bubbleSort(int array[]){
		double currentTime = System.nanoTime();
		int temp=0;
		for(int i=0;i<array.length-1;i++){
			for(int j=0;j<array.length-1-i;j++){
				if(array[j]>array[j+1]){
					temp = array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
		System.out.println("[Bubble Sort]		Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	}
	
	//Question 2 - Counting Sort
	public static void countingSort(int array[]){
		double currentTime = System.nanoTime();
		
		//Find the maximum
		int max=0;
		for(int i=0;i<array.length;i++)	max = Math.max(max,array[i]);
		
		//Count array
		int [] count = new int[max+1];
		for(int i=0;i<array.length;i++)	count[array[i]]++;
		
		//Assign back to the array
		int c=0;
		for(int i=0;i<count.length;i++){
			if(count[i]!=0){
				for(int j=0;j<count[i];j++)	array[c++]=i;
			}
		}
		System.out.println("[Counting Sort]		Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	}
	
	//Question 3 - Bucket Sort
	public static void bucketSort(int array[]){
		double currentTime = System.nanoTime();
		
		//Find maximum
		int max = 0;
		for(int i=0;i<array.length;i++)	max = Math.max(max, array[i]);
		
		//Find number of digits for max
		int digitCount=0;
		for(;max>0;max/=10)	digitCount++;
		
		double [] newArr = new double[array.length];
		//Divide by a certain constant
		for(int i=0;i<array.length;i++)	newArr[i]= array[i]/Math.pow(10, digitCount);
		
		//Initialize Bucket
		ArrayList<ArrayList> a = new ArrayList();
		for(int i=0;i<10;i++)	a.add(new ArrayList<Double>());
		
		for(int i=0;i<newArr.length;i++){
			a.get((int)newArr[i]*10).add(newArr[i]);
		}
		
		for(int i=0;i<10;i++)	Collections.sort(a.get(i));
		int c=0;
		for(int i=0;i<a.size();i++){
			for(int j=0;j<a.get(i).size();j++){
				array[c++]=(int) ((double)a.get(i).get(j) * Math.pow(10, digitCount));
			}
		}
		System.out.println("[Bucket Sort] Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	}
	
	public static void shellSort(int array[]){
		
		double currentTime = System.nanoTime();
		
		//Define gap size
		int gap = array.length/2;
		
		//For every loop, decrease gap size by a factor of 2 until 1
		for(;gap>0;gap/=2){	
			//System.out.println("Gap size: " + gap);
			//Do insertion sort based on the gap
			for(int i=0;i<array.length;i++){
				int j=i+gap;
				//System.out.println("i: " + Arrays.toString(array));
				while(j>0 && j<array.length && (j-gap)>=0){
					if(array[j-gap]>array[j]){
						int temp = array[j];
						array[j]=array[j-gap];
						array[j-gap]=temp;
						//System.out.println("SWAP " + (j-gap) + " " + j);
					}
					
					j-=gap;
				}
			}
		}
		System.out.println("[Insertion Sort]	Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	}

	//Insertion Sort
	public static void insertionSort(int array[]){
		double currentTime = System.nanoTime();
		for(int i=1;i<array.length;i++){
			int j=i;
			while(j>0){
				if(array[j]<array[j-1]){
					int temp = array[j];
					array[j]=array[j-1];
					array[j-1]=temp;
				}
				j--;
			}
		}
		System.out.println("[Insertion Sort]	Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	}

	//Selection Sort
	public static void selectionSort(int array[]){
		double currentTime = System.nanoTime();
		for(int i=0;i<array.length;i++){
			int min = array[i];
			int index = i;
			for(int j=i;j<array.length;j++){
				if(array[j]<min){
					min = array[j];
					index = j;
				}
			}
			int temp = array[i];
			array[i]=array[index];
			array[index] = temp;
		}
		System.out.println("[Selection Sort]	Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	}
	
	//Radix Sort
	public static void radixSort(int [] array){
		double currentTime = System.nanoTime();
		int max=0;
		//Find maximum value > Get total number of digits
		for(int i=0;i<array.length;i++)	max = Math.max(max, array[i]);
		
		//Do counting sort for each digits
		for(int e=1;max>0;max/=10,e*=10){
			R_countingSort(array,e);
		}
		System.out.println("[Radix Sort]	Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	}
		//Counting Sort for Radix Sort
		private static void R_countingSort(int [] array, int e){
			int [] count = new int[10];
			for(int i=0;i<array.length;i++)	count[(array[i]/e)%10]++;
			for(int i=1;i<count.length;i++)	count[i]+=count[i-1];
			
			int [] newArr = new int[array.length];
			
			for(int i=array.length-1;i>=0;i--)	newArr[(count[((array[i])/e)%10]--)-1] = array[i];
			
			for(int i=0;i<array.length;i++)	array[i]=newArr[i];
		}
}
