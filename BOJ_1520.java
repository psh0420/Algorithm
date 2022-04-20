package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520 {
	
	static int M, N;	
	static int[][] map, v;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		v = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			Arrays.fill(v[i], -1);			
		}
		dfs(0, 0); 
		
		
		
		System.out.println(v[0][0]);
	}
	
	static int dfs(int y, int x) {
		
		if(y == M-1 && x == N-1) {// 목적지 도착
			return 1;
		}
		
		if(v[y][x] == -1) { // 현재 지점에 처음 방문하면
			v[y][x] = 0;
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue; // 배열범위 체크
			
				if (map[y][x] > map[ny][nx]) { // 내리막길이면	
					v[y][x] += dfs(ny, nx);
				}		
			}
		}			
		
		return v[y][x];
	}
}
