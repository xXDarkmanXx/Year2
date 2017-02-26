import java.util.Arrays;

/*
 * Implementation of Merge Sort - O(n log n)
 * @Author khooi8913
 */

public class MergeSort {
	public static void main(String args[]){
		int arr[]={91,5,2,76,9,1,5,7,97};
		mergeSort(arr,0,arr.length-1);
	}
	
	public static void mergeSort(int a[], int low, int high){
		System.out.print("Currently dealing with : ");
		for(int i=low;i<=high;i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
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
	
public static void mergeSort(int a[], int low, int mid, int high){
		
		System.out.println("Debug 0: Low: " + low + " Mid: " + mid + " High: " + high);
		System.out.println("Debug 1: "+Arrays.toString(a));
		
		//Initialize a temporary array of size the sum of the two arrays to be merged
		//Left[low...mid]	Right[mid+1...high]
		int size = (high-low)+1;
		int newArr[] = new int[size];
		
		//Initialize variables LHS RHS for both points of the two arrays to be merged
		int LHS=low;
		int RHS=mid+1;
		
		//Initialize variable i as the index for the values to be inserted into the temporary array
		int i=0;
		
		System.out.println("Debug 2: LHS: " +LHS + " Mid: " + mid +" RHS: "+ RHS + " i: " + i);
		
		//By comparing the values from the two arrays, (merge) copy them into the temporary array (sorted)
		while(i<=high){
			
			if(a[LHS]<a[RHS]){
				newArr[i]=a[LHS];
				System.out.println("Condition 1 Triggered: "+Arrays.toString(newArr));
				LHS++;
			}else if(a[LHS]>a[RHS]){
				newArr[i]=a[RHS];
				System.out.println("Condition 2 Triggered: "+Arrays.toString(newArr));
				RHS++;
			}else{
				newArr[i]=a[LHS];
				System.out.println("Condition 3 Triggered: "+Arrays.toString(newArr));
				LHS++;
			}
			i++;
			System.out.println("Debug 3: LHS: " +LHS + " Mid: " + mid +" RHS: "+ RHS + " i: " + i);
			//If any of the two arrays are being fully copied into the temporary array
			//Exit from the while loop as no more comparison need to be done further
			if(LHS>mid || RHS>high)
				break;
		}
		
		//(Merge) Copy the remaining values into the temporary array (sorted)
		for(;i<=high;i++){
			System.out.println("Debug 4: "+Arrays.toString(newArr));
			if(LHS>=mid && RHS<=high){
				newArr[i]=a[RHS];
				RHS++;
			}
			else if(RHS>=high && LHS<=mid){
				newArr[i]=a[LHS];
				LHS++;
			}
			System.out.println("Debug 5: "+Arrays.toString(newArr));
		}
		
		//Replace the original array's values with the sorted array's values
		int newArrL=0;
		System.out.println("Replacing values: Low: " + low + " High: " + high);
		for(int j=low;j<=high;j++,newArrL++){
			a[j]=newArr[newArrL];
		}
		System.out.println("Sorted for indexes from "+ low  +  " to " + high + " where: " + Arrays.toString(a));
		
	}
}


