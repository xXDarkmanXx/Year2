import java.util.*;

public class Main {
	
	public static void main(String args[]){
		int x [] = randomArray(15);
		//double currentTime = System.nanoTime();
		//mergeSort(x,0,x.length-1);
		//Arrays.sort(x);
		//System.out.println("[Merge Sort]	Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
		
		//bucketSort(randomArray(100000));
		//bucketSort2(randomArray(100000));
		//radixSort(randomArray(100000));
		//countingSort(randomArray(100000));
		//selectionSort(randomArray(100000));
		//insertionSort(randomArray(100000));
		System.out.println("Non-sorted array: " + Arrays.toString(x));
		bubbleSort(x);
		//shellSort(randomArray(100000));
		System.out.println("Sorted array (Bubble Sort): " + Arrays.toString(x));
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
		//double currentTime = System.nanoTime();
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
		//System.out.println("[Bubble Sort]		Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
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
		
		for(int i=0;i<newArr.length;i++)	a.get((int)newArr[i]*10).add(newArr[i]);
		
		for(int i=0;i<10;i++)	Collections.sort(a.get(i));
		int c=0;
		for(int i=0;i<a.size();i++){
			for(int j=0;j<a.get(i).size();j++){
				array[c++]=(int) ((double)a.get(i).get(j) * Math.pow(10, digitCount));
			}
		}
		System.out.println("[Bucket Sort] Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	}
	
	//Question 4 - Shell Sort 
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
	
	public static void bucketSort2 (int [] a){
		double currentTime = System.nanoTime();
		//Declaring buckets
	    ArrayList<PriorityQueue<Integer>> lists = new ArrayList<PriorityQueue<Integer>>();

	    //Initializing buckets
	    for(int i=0; i <10;i++)	lists.add(new PriorityQueue<Integer>());

	    //Find maximum
	    int max =0 ;
	    for (int i=0;i<a.length;i++)	max = Math.max(max,a[i]);

	    //Dividing constant
	    int divider = (int)(Math.ceil((max+1)/10.0));				//No need Math.ceil, +0.5 will do
	    for(int i=0;i<a.length;i++)	lists.get(a[i]/divider).add(a[i]);
	        
	    //Returning sort values back to the array 
	    int bucketSize, count=0;
	    for(int i=0;i<lists.size();i++){
	    	bucketSize = lists.get(i).size();
	    	for(int j=0;j<bucketSize;j++){
	        	a[count++]=lists.get(i).poll();
	       	}
	    }
	    System.out.println("[Bucket Sort2]	Time elapsed: "+ (System.nanoTime()-currentTime)/Math.pow(10, 9)+"	second(s).");
	} 

	//Merge Sort
	public static void mergeSort(int a[], int low, int high){
		if(low<high){
			int mid = (low+high)/2;
			//Split into left
			mergeSort(a,low,mid);
			//Split into right
			mergeSort(a,mid+1,high);
			//Merge the arrays together
			mergeSort(a,low,mid,high);
		}
	}
	private static void mergeSort(int a[], int low, int mid, int high){
		
		//Initialize a temporary array of size the sum of the two arrays to be merged
		//Left[low...mid]	Right[mid+1...high]
		int size = (high-low)+1;
		int newArr[] = new int[size];
		
		//Initialize variables LHS RHS for both points of the two arrays to be merged
		int LHS=low;
		int RHS=mid+1;
		
		//Initialize variable i as the index for the values to be inserted into the temporary array
		int i=0;
		
		//By comparing the values from the two arrays, (merge) copy them into the temporary array (sorted)
		while(i<=high){
			
			if(a[LHS]<a[RHS]){
				newArr[i]=a[LHS];
				LHS++;
			}else if(a[LHS]>a[RHS]){
				newArr[i]=a[RHS];
				RHS++;
			}else{
				newArr[i]=a[LHS];
				LHS++;
			}
			i++;
			//If any of the two arrays are being fully copied into the temporary array
			//Exit from the while loop as no more comparison need to be done further
			if(LHS>mid || RHS>high)
				break;
		}
		
		//(Merge) Copy the remaining values into the temporary array (sorted)
		for(;i<=high;i++){
			if(LHS>=mid && RHS<=high){
				newArr[i]=a[RHS];
				RHS++;
			}
			else if(RHS>=high && LHS<=mid){
				newArr[i]=a[LHS];
				LHS++;
			}
		}
		
		//Replace the original array's values with the sorted array's values
		int newArrL=0;
		for(int j=low;j<=high;j++,newArrL++){
			a[j]=newArr[newArrL];
		}
	}
}
