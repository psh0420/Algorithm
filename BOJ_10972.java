package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_10972 {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		nextPermutation();
	}
	
	static void nextPermutation() { // 현재 상태에서 다음 순열 찾기
		
		// 1. 맨 뒤에서 부터 arr[i-1] < arr[i] 을 만족하는 것을 찾는다
		int i = N-1;
		while(i > 0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i == 0) { // 다음 순열이 없다
			System.out.println("-1");
			return;
		}
		// 2. arr[i-1] < arr[j]인 j를 찾는다
		int j = N-1;
		while(arr[i-1]>= arr[j]) {
			j--;
		}
		
		// 3. i-1과 j를 swap한다
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		// 4. i부터 j까지의 순서를 reverse 해준다
		int k = N-1;
		while(i < k) {
			temp = arr[i];
			arr[i] = arr[k];
			arr[k] = temp;
			++i;
			--k;
		}
		
		// 다음 순열 출력
		for(int n : arr) {
			System.out.print(n + " ");
		}
	}

}
