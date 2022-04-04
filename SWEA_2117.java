package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_2117 {
	
	static class Node{
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}		
	}
	
	static int n, m, ans;
	static int[][] map;
	static Queue<Node> q;
	static boolean[][] v;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t++) {
			ans = 0;
			n = sc.nextInt();
			m = sc.nextInt();
			
			q = new LinkedList<>();
			map = new int[n][n];
			v = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// process
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					init(); //  초기화
					bfs(i, j);
				}
			}
			
			// output
			System.out.println("#" + t + " " + ans);
		}

	}
	static void bfs(int r, int c) {
		q.offer(new Node(r, c));
		
		v[r][c] = true; // 방문체크
		
		int k = 1;
		int house = map[r][c] == 1 ? 1 : 0;
		
		// 이익계산
		if(house * m >= k * k + (k - 1) * (k - 1)) { // n이 1일때
			ans = k > ans ? k : ans;
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			k++;
			
			for (int i = 0; i < size; i++) {
				Node cur = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if(nr < 0 || nr >= n || nc < 0 || nc >= n || v[nr][nc])
						continue;
					
					if(map[nr][nc] == 1) house++;
					
					q.offer(new Node(nr, nc));
					v[nr][nc] = true;
				}
			}
			
			if(house * m >= k * k + (k - 1) * (k - 1)) {
				ans = house > ans ? house : ans;
			}
		}
	}
	
	static void init() {
		q.clear();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				v[i][j] = false;
			}
		}
	}

}