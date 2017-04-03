import java.util.Arrays;

public class PriorityQueue {
	
	private int [] A = new int[20];
	private int heapSize;
	
	//Constructor
	public PriorityQueue(){
		heapSize=0;
	}
	
	//Initialize the Priority Queue with an Array
	public PriorityQueue(int [] array){
		heapSize=array.length;
		for(int i=0;i<array.length;i++)	A[i]=array[i];
		for(int i=heapSize/2;i>=0;i--)	maxHeapify(A, i, heapSize);
	}
	
	public int heapMaximum(){
		return A[0];
	}
	
	public int extractHeapMax(){
		if(heapSize<1)	System.err.println("heap underflow");
		else{
			int max = heapMaximum();
			A[0] = A[--heapSize];
			for(int i=heapSize/2;i>=0;i--)	maxHeapify(A, i, heapSize-1);
			return max;
		}
		return -1;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<heapSize;i++)	sb.append(A[i]+" ");
		return sb.toString().trim();
	}
	
	public int getHeapSize(){
		return heapSize;
	}
	
	public void heapIncreaseKey(int i, int key){
		if(key<A[i])	System.err.println("new key is smaller than current key");
		A[i]=key;
		while(i>=0 && A[parent(i)]<A[i]){
			swap(A, parent(i), i);
			i = parent(i);
		}
	}
	
	public int parent(int i){
		return i/2;
	}
	
	public void maxHeapInsert(int newValue){
		heapSize = heapSize + 1;
		A[heapSize-1]=-1;
		heapIncreaseKey(heapSize-1,newValue);
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
