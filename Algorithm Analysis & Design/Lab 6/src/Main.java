import java.util.*;
import java.io.*;

//Lab 6
public class Main {
	
	public static void main(String args[]){
		//Question 1
		int [] array = {11,4,74,55,3,17,8,46,43,33};
		for(int i=(array.length/2)-1;i>=0;i--)	maxHeapify(array, i);
		System.out.println(Arrays.toString(array));
		
		//Question 2
		int [] array1 ={11,4,74,55,3,17,8,46,43,33};
		heapSort(array1);
		System.out.println(Arrays.toString(array1));
		
		//Question 3
		int [] array2 ={11,4,74,55,3,17,8,46,43,33};
		PriorityQueue A = new PriorityQueue(array2);
		System.out.println("Maximum value of the heap is: " + A.heapMaximum());
		System.out.println(A.toString());
		A.maxHeapInsert(2000);
		System.out.println(A.toString());
		A.extractHeapMax();
		System.out.println(A.toString());
	}
	
	//Question 1
	public static void maxHeapify(int [] array, int root){
		int largest = root;
		int	leftChild = 2*root +1;
		int rightChild = 2*root +2;
		if(leftChild<array.length && array[leftChild] > array[largest])	largest=leftChild;
		if(rightChild<array.length && array[rightChild]>array[largest])	largest=rightChild;
		//If there is a swap occur, must check that tree again to make sure that the tree is completely max heapified
		if(largest!=root){
			swap(array, root, largest);
			maxHeapify(array, largest);
		}	
	}
	
	//Question 2
	public static void heapSort(int array[]){
		//Build Max Heap
		for(int i=(array.length/2)-1;i>=0;i--)	maxHeapify(array, i);
		
		//Sort
		for(int i=array.length-1;i>=0;i--){
			swap(array, 0, i);
			maxHeapify(array, 0, i);
		}
		
	}
	
	public static void maxHeapify(int [] array, int root, int end){
		int largest = root;
		int	leftChild = 2*root +1;
		int rightChild = 2*root +2;
		if(leftChild<end && array[leftChild] > array[largest])	largest=leftChild;
		if(rightChild<end && array[rightChild]>array[largest])	largest=rightChild;
		//If there is a swap occur, must check that tree again to make sure that the tree is completely max heapified
		if(largest!=root){
			swap(array, root, largest);
			maxHeapify(array, largest, end);
		}
	}
	
	private static void swap(int [] array, int original, int dest){
		int temp = array[original];
		array[original]=array[dest];
		array[dest]=temp;
	}

}
