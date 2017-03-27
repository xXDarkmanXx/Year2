import java.util.*;

//Lab 5
public class Main {
	
	//Question 1
	public static int randSelect(int [] A, int p, int r, int i){
		
		//If there is only one element
		if(p==r)		return A[p];
		
		int q = randPartition(A,p,r);
		
		//Position of Pivot
		int k = q - p + 1;
		if(i==k)		return A[q];
		else if (i<k)	return randSelect(A, p, q-1, i);
		else			return randSelect(A, q+1, r, i-k);
	}
	
	private static int randPartition(int [] A, int p, int r){
		//Random Pivot
		int size = (r-p+1);
		Random a = new Random();
		int i = p;
		int temp; 
		int www = a.nextInt(size);
		int pvtIdx 	= p + (www % size);
		int	pivot 	= A[pvtIdx];

		//Swap Random Pivot with First Position
		temp = A[p];
		A[p]=A[pvtIdx];
		A[pvtIdx]=temp;
		
		//Partition starts here
		for(int j=i+1;j<=r;j++){
			if(A[j] <= pivot){
				temp = A[j];
				A[j] = A[++i];
				A[i] = temp;
			}				
		}
		
		temp = A[p];
		A[p] = A[i];
		A[i] = temp;
		return i;
	}
	
	//Question 2
	private static class Coordinate{
		int x, y;
		Coordinate(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static int optimumLocation(Coordinate [] coordinate){
		int [] optimum = new int [coordinate.length];
		for(int i=0;i<coordinate.length;i++)	optimum[i]=coordinate[i].y;
		return randSelect(optimum, 0, optimum.length-1,(int)Math.ceil(optimum.length/2.0));
	}
	
	
	public static void main(String args[]){
		
		//Question 1
		int [] arr 	= {11,4,74,55,3,17,8,46,43,33,32};
		System.out.println("Maximum value is : " + randSelect(arr, 0, arr.length-1, arr.length));
		System.out.println("Minimum value is : " + randSelect(arr, 0, arr.length-1, 1));
		System.out.println("Fifth value is : " + randSelect(arr, 0, arr.length-1, 5));
		System.out.println("Eighth value is : " + randSelect(arr, 0, arr.length-1, 8));
		if(arr.length%2!=0)	System.out.println("Median value (floor/ceiling) is : " + randSelect(arr, 0, arr.length-1, (int)Math.ceil(arr.length/2.0)));
		else{
			System.out.println("Median value (floor) is : " + randSelect(arr, 0, arr.length-1, arr.length/2));
			System.out.println("Median value (ceiling) is : " + randSelect(arr, 0, arr.length-1, (arr.length+2)/2));
		}
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		//Question 2
		Coordinate [] c = new Coordinate[9];
        c[0] = new Coordinate(-5,71);
        c[1] = new Coordinate(1,64);
        c[2] = new Coordinate(7,98);
        c[3] = new Coordinate(11,45);
        c[4] = new Coordinate(18,56);
        c[5] = new Coordinate(-8,23);
        c[6] = new Coordinate(5,14);
        c[7] = new Coordinate(13,33);
        c[8] = new Coordinate(21,3);
		System.out.println("The optimum location is at y-coordinate of : " + optimumLocation(c));
	}
}
