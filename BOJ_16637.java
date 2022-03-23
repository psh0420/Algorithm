package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class BOJ_16637 {
	static int N, ans;
	static char[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new char[N];
		
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			arr[i] = s.charAt(i);
		}
		
		ans = Integer.MIN_VALUE; // 최소값으로 초기화
		
		// 2번째 숫자부터 괄호를 왼쪽에 칠 것인지(현재 인덱스 숫자를 포함x), 오른쪽에 칠 것인지 정한다
		dfs(2, arr[0] - '0');
		
		// 출력
		System.out.println(ans);
		
	}
	
	static void dfs(int i, int sum) { // i: 배열의 인덱스, sum: 계산값 저장
		// 종료 조건
		if(i >= N) {
			ans = Math.max(ans, sum); // N개를 계산 다했으면 가장 큰 값를 결과값으로 저장 
			return;
		}
		
		// 괄호 안 묶는다
		// 현재까지의 합과 현재 인덱스를 계산한 결과를 다음 숫자에 넘긴다
		dfs(i+2, cal(sum, arr[i]-'0', arr[i-1]));
		
		// 괄호로 묶는다
		if(i+2 < N) {
			// 괄호 먼저 계산
			int right = cal(arr[i]-'0', arr[i+2]-'0', arr[i+1]);
			// 현재까지의 결과와 합치기
			int left = cal(sum, right, arr[i-1]);
			dfs(i+4, left);
		}
	}
	
	// 연산자에 따른 계산 결과 리턴
	static int cal(int i, int j, char op) { // i: 연산자 앞 숫자, j: 연산자 뒷 숫자, op: 연산자
		if(op == '+') {
			return i + j;
		}else if(op == '-') {
			return i - j;
		}else
			return i * j;
	}
}
