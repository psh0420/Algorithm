package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_7699 {

	static int R, C, res;
	static char[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][] v; 
	static boolean[] alphabet;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			R = sc.nextInt();
			C = sc.nextInt();
			
			map = new char[R][C];
			v = new boolean[R][C];
			alphabet = new boolean[26]; // 알파벳 26개 방문 체크
			for (int i = 0; i < R; i++) {
				String s = sc.next();
				for (int j = 0; j < C; j++) {
					map[i][j] = s.charAt(j);
				}
			}// 입력부
			
			res = 0;
			
			// 시작지점 방문체크
			v[0][0] = true;
			alphabet[map[0][0] - 'A'] = true; // 현재 알파벳에서 'A'(65)를 빼면 0~25까지가 된다
			
			dfs(0, 0, 1); //(0,0)에서 시작
			
			System.out.println("#" + t + " " + res);
			
		}
	}	
	
	static void dfs(int y, int x, int cnt) {
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || nx >= C || ny < 0 || ny >= R) continue; // 배열 경계 체크
			
			if(v[ny][nx]) continue; // 현재 지점을 방문했으면 패스
			
			if(!alphabet[map[ny][nx] - 'A']) { // 처음 방문하는 명물이면
				v[ny][nx] = true;
				alphabet[map[ny][nx] - 'A'] = true;
				
				dfs(ny, nx, cnt+1);
				
				v[ny][nx] = false;
				alphabet[map[ny][nx] - 'A'] = false;
				
			}
			
		}
		
		res = Math.max(cnt, res);
	}
	
}
