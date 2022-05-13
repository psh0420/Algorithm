package may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {

	static int R, C, max;
	static char[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][] v;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = in.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		max = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'L') {
					int cnt = bfs(i, j);					
					max = Math.max(max, cnt);
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	static int bfs(int y, int x) {
		v = new boolean[R][C]; 
		Queue<Node> q = new LinkedList<>();
		int res = 0;
		q.offer(new Node(y, x, 0));
		v[y][x] = true;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
					
				if(nx < 0 || nx >= C || ny < 0 || ny >= R) continue;
				
				if(v[ny][nx]) continue;
				if (map[ny][nx] == 'L') {
					q.offer(new Node(ny, nx, cur.size+1));
					v[ny][nx] = true;
					res = Math.max(res, cur.size+1);
				}
							
			}				
		}
		return res;
	}
	
	static class Node{
		int y, x, size;
		
		public Node(int y, int x, int size) {
			this.y = y;
			this.x = x;
			this.size = size;
		}	
	}
}
