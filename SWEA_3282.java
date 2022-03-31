package boj;

import java.util.Scanner;

public class SWEA_3282 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();  // 물건 개수
			int K = sc.nextInt();  // 가방 부피 => 최대 k개 만큼 넣을 수 있음
			
			int[] v = new int[N+1]; // 부피
			int[] c = new int[N+1];  // 가치
			
			for (int i = 1; i <= N; i++) {
				v[i] = sc.nextInt();
				c[i] = sc.nextInt();
			}
			
			// 메모이제이션 : dp[i][j] => 부피가 j이고 i번째 물건까지 고려했을 때 최대 가치의 합
			int[][] dp = new int[N+1][K+1]; 
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= K; j++) {
					if(v[i] > j) { // i번째 물건이 정해진 부피보다 크면
						dp[i][j] = dp[i-1][j];  // 이전에 구한 최대값을 가져온다
					}else { // 정해진 부피보다 작거나 같으면
						// 물건을 넣는 것과 안 넣는 것 중에 가치가 최대인 것을 선택 
						dp[i][j] = Math.max(dp[i-1][j-v[i]] + c[i], dp[i-1][j]);
					}
					
				}
			}
			
			System.out.println("#" + t + " " + dp[N][K]);
		}
	}

}
