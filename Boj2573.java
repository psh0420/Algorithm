package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2573 {
	static int N, M, year;
	static int[][] map, ans;
	static boolean[][] v;
	static int[] dy= {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		year = 0;
		map = new int[N][M];
		ans = new int[N][M];
		v = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		
		// 빙산 갯수 찾기
		int n = 0;
		while((n = icebergNum()) < 2) {			
			if(n == 0) { // 빙산이 하나도 없으면
				year = 0;
				break;
			}
			v = new boolean[N][M]; // 방문체크 초기화 필수!!
			
			// 주변 바다 갯수 찾기
			for (int i = 0; i < N; i++) {			
				for (int j = 0; j < M; j++) {
					if(map[i][j] != 0) {	
						find(i, j, 0);
					}
				}
			}
			
			// 원래 맵에서 주변 바다 갯수를 빼준다
			for (int i = 0; i < N; i++) {			
				for (int j = 0; j < M; j++) {
					map[i][j] -= ans[i][j]; 
					if(map[i][j] < 0) { // 음수면 0으로
						map[i][j] = 0;
					}
				}
			}		
			
			year++; 
					
		}
		
			
		System.out.println(year);

	}
	static int icebergNum() { // 빙산 갯수
		int res = 0;
		for (int i = 0; i < N; i++) {			
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0 && !v[i][j]) {	
					dfs(i, j);
					res++;
				}
			}
		}
		return res;
		
	}
	static void find(int y, int x, int cnt) { // 주변 바다 갯수 찾기
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			for (int i = 0; i < 1; i++) { // 상하좌우 1번씩만 탐색
				if(map[ny][nx] == 0) {
					cnt++;
				}
			}		
		}
		
		ans[y][x] = cnt; // 주변 바다 갯수 저장

		
	}
	
	static void dfs(int y, int x) { // 빙산 갯수 세기
		v[y][x] = true;
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(nx >= 0 && nx < M && ny >= 0 && ny < N && 
					map[ny][nx] != 0 && !v[ny][nx]) {
				dfs(ny, nx);
			}			
		}		
	}	
}
