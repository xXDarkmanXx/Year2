import java.util.*;
import java.io.*;

public class Main {

	static int []cache = new int [300000];
	
	public static void main(String args[]) throws IOException{
		//PART A
		//Initialization for Fibonacci's (DP)
		cache[0]=0;		cache[1]=1;
		int [] array0 = {1,3,4};
		
		//TODO: Invoke Part A methods here
		
		//PART B
		Map<String, String> mapBully = new HashMap<String, String>();
		
		mapBully.put("a","candy");	mapBully.put("b","dirt");	mapBully.put("c","meh");
		
		//Question 1
		mapBully.put("b", mapBully.get("a"));	mapBully.put("a", "");
		
		//Question 2
		Map<String, Integer> word0 = new HashMap<String, Integer>();
		String word = "hello";
		for(int i=0;i<word.length();i++)	word0.put(word.charAt(i)+"", 0);
	}
	
	//PART A
	//Question 1 -> O(1), O(n) - Loop
	public static int sum3(int []a){
		return Arrays.stream(a).sum();
	}
	
	//Question 2 -> O(1)
	public static boolean firstLast6(int a[]){
		if(a[0]==6 || a[a.length-1]==6)	return true;
		else	return false;
	}
	
	//Question 4 -> O(n)
	public static String linearSearch(int []a, int search){
		for(int i=0;i<a.length;i++)
			if(a[i]==search)	return "Number " + search + " found at index " + i;
		return "Number not found";
	}
	
	//Question 5 (a) -> DP Approach O(2^n), O(1) once cached
	public static int iterativeFibonacci(int n){
		for(int i=2;i<=n;i++)	cache[i]=cache[i-1]+cache[i-2];
		return cache[n];
	}
		
	//Question 5(b) -> DP Approach O(2^n), O(1) once cached
	public static int recursiveFibonacci(int n){
			if(n==0 || n==1 || cache[n]!=0)	return cache[n];
			else	return cache[n]= recursiveFibonacci(n-1) + recursiveFibonacci(n-2);
	}
	
	//Question 6 -> O(n)
	public static int[] rotateLeft3(int a[]){
		int temp=a[0];
		for(int i=0;i<a.length-1;i++)	a[i]=a[i+1];
		a[a.length-1]=temp;
		return a;
	}
	
	//Question 7
	public static int[] biggerTwo(int a[], int b[]){
		if(sum3(a)>sum3(b))	return a;
		else if (sum3(a)<sum3(b)) return b;
		else	return a;
	}
}