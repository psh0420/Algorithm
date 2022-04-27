package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2629 {
	static int n;
	static int[] chu, guseul;
	static boolean[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 추 갯수
		chu = new int[n]; // 추 무게
		dp = new boolean[n+1][55001]; // 구슬 최대 무게 40000 + 추 최대 무게 (500 * 30) = 55000
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine()); // 구슬 갯수
		guseul = new int[m]; // 구슬 무게
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			guseul[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			if(dp[n][guseul[i]]) {
				sb.append("Y ");
			}else {
				sb.append("N ");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int cnt, int weight) {
		if(dp[cnt][weight]) return;
		
		dp[cnt][weight] = true;
		
		if(cnt == n) return;
		
		dfs(cnt + 1, weight + chu[cnt]); // 추를 더하는 경우
		dfs(cnt + 1, weight);  // 추를 사용 안하는 경우
		dfs(cnt + 1, Math.abs(weight - chu[cnt])); // 구슬 있는 곳에 추 더하는 경우
	}

}
