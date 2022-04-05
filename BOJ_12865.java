package boj;

import java.util.Scanner;

public class BOJ_12865 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N  = sc.nextInt(); // 물품 수
		int K = sc.nextInt(); // 배낭 무게
		int[] w = new int[N+1]; // 물건 무게
		int[] v = new int[N+1]; // 물건 가치
		int[][] dp = new int[N+1][K+1]; //메모이제이션
		
		for (int i = 1; i <= N; i++) {
			w[i] = sc.nextInt();
			v[i] = sc.nextInt();
		}
			
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if(j-w[i] < 0) { // i번째 물건을 넣지 않았을 때
					dp[i][j] = dp[i-1][j];
				}else { // i번째 물건을 넣었을 때와 넣지 않았을 때의 가치가 큰 값을 선택
					dp[i][j] = Math.max(dp[i-1][j-w[i]] + v[i], dp[i-1][j]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
	
	

}
