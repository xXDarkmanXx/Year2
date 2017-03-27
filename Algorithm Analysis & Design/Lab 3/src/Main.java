import java.util.*;
import java.io.*;

public class Main {
	
	//Powering a Number - Cache
	static int cache[] = new int[1000];
	
	//Fibonacci Numbers - Cache
	static int fibCache[] = new int[1000];
	
	public static void main(String args[]){
		
		//Binary Search
		int array [] = {24,25,26,27,28,29,30};
		System.out.println("24 is found at Index : " + binarySearch(array,24));
		System.out.println("31 is found at Index : " + binarySearch(array,31));
		
		//Powering a Number
		System.out.println("2 to the power of 10 is : " + powerNumber(2,10));
		
		//Fibonacci Number
		fibCache[0]=0;
		fibCache[1]=1;
		System.out.println("7th Fibonacci Number is : " + fibonacciNumbers(7));
		System.out.println("17th Fibonacci Number is : " + fibonacciNumbers(17));
		
		//Matric Multiplication
		int [][] A = {{1,1},{2,3}};
		int [][] B = {{1,0},{0,1}};
		for(int i[]:matrixMultiplication(A, B))	System.out.println(Arrays.toString(i));
		
		//Merge Sort
		int array1 [] = {23,5,2,1,65,345,32,232,55,2};
		MergeSort(array1);
		System.out.println(Arrays.toString(array1));

		//Quick Sort
		int array2 [] = {23,5,52,44,2,32,67,23,44,6,2,123,42,33};
		QuickSort(array2);
		System.out.println(Arrays.toString(array2));
		
		//Part2 
		//Finding the Matric Number
		int [] matricNum = {150009, 150008, 150010, 150006, 150011, 150013, 150012};
		System.out.println(findMissingMatric(matricNum, 0, matricNum.length-1, 150006, 150013));
		
	}
	
	//Binary Search (Return index)
	public static int binarySearch(int array[], int value){
		int low = 0, high = array.length-1;
		int mid = (low + high)/2;
		return binarySearch(array, low, mid, high, value);
	}
	
		private static int binarySearch(int array[], int low, int mid, int high, int value){
			if(low>high)			return -1;
			if(array[mid]==value)	return mid;
			if(value<array[mid])		return binarySearch(array, low, (low+mid-1)/2, mid-1, value);
			else if(value>array[mid])	return binarySearch(array, mid+1, (mid+1+high)/2, high, value);
			else	return -1;
		}

	//Powering a Number (with DP)
	public static int powerNumber(int number, int power){
		cache[0]=1;	cache[1]=number;
		if(power==0)	return cache[power];
		if(power==1)	return cache[power];
		if(power%2==0) 	cache[power] = powerNumber(number, power/2)*powerNumber(number, power/2);
		else 			cache[power] = powerNumber(number, (power-1))*powerNumber(number, 1);
		return cache[power];
	}

	//Fibonacci Numbers
	public static int fibonacciNumbers(int n){
		if(n==0 || n==1)	return fibCache[n];
		else	return fibCache[n]= fibonacciNumbers(n-1)+ fibonacciNumbers(n-2);
	}
	
	//Matrix Multiplication
	public static int[][] matrixMultiplication(int [][] A, int [][]B){
		int w = A.length;		//RowA
		int x = A[0].length;	//ColumnA
		int y = B.length;		//RowB
		int z = B[0].length;	//ColumnB
		
		int [][] C = new int[w][z];	//results Array
		
		for(int i=0;i<C.length;i++){
			for(int j=0;j<C[0].length;j++){
				for(int k=0;k<x;k++){
					C[i][j] += A[i][k]*B[k][j];
				}
			}
		}
		
		return C;
	}
	
	//Merge Sort
	public static void MergeSort(int array[]){
		if(array.length>1){
			int midVal = array.length/2;
			int [] left = new int [midVal];
			int [] right = new int [array.length-midVal];
			
			for(int i=0;i<left.length;i++)	left[i]=array[i];
			for(int i=midVal;i<array.length;i++)	right[i-midVal]=array[i];
			
			MergeSort(left);
			MergeSort(right);
			
			//Merge Left and Right
			int LHS=0, RHS=0;
			int i=0;
			
			while(LHS<left.length && RHS<right.length)	array[i++] = left[LHS]<right[RHS] ? left[LHS++] : right[RHS++];
			while(LHS<left.length)	array[i++] = left[LHS++];
			while(RHS<right.length)	array[i++] = right[RHS++];
			
		}
	}

	//Quick Sort
	public static void QuickSort(int array[]){
		QuickSort(array, 0, array.length-1);
	}
	
		private static void QuickSort(int array[], int p, int r){
			if(p<r){
				int q = qsPartition(array,p,r);
				QuickSort(array,p,q-1);
				QuickSort(array,q+1,r);
			}
		}
		
		private static int qsPartition(int array[], int p, int r){
			//Randomized QuickSort
			Random rand = new Random();
			int pvtIndex = (rand.nextInt(10000)%(r-p+1)) + p;
			swap(array, p , pvtIndex);
			
			int i=p;
			int pivot=array[p];
			
			for(int j=i+1;j<=r;j++){
				if(array[j]<=pivot){
					swap(array, ++i, j);
				}
			}
			
			swap(array, p, i);
			return i;
		}
		
		private static void swap(int [] array, int original, int dest){
			int temp = array[original];
			array[original] = array[dest];
			array[dest] = temp;
		}
	
	//Part 2
	//Finding the Matric Number
	public static int findMissingMatric(int array[], int p, int r, int start, int end){
		int median = (start+end)/2;
		
		boolean found=false;
		int medianIdx=0;
		for(int i=p;i<r;i++){
			if(array[i]==median){
				found=true;
				medianIdx=0;
				break;
			}
		}
		swap(array, p, medianIdx);
		if(!found)	return median;
		else{
			int q = partition(array,p,r);
			int size = q-p;
			if(size < (median-start))	return findMissingMatric(array,p,q-1,start,median);
			else	return findMissingMatric(array,q+1,r,median+1,end);
		}
	}
	
		private static int partition(int array[], int p, int r){
			int pivot = array[p];
			int i = p;
			for(int j=i+1;j<=r;j++){
				if(array[j]<=pivot)
					swap(array,++i,j);
			}
			swap(array,i,p);
			return i;
		}
}
