package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {

	static int L, R, C;
	static StringBuilder sb;
	static char[][][] map;
	static int[] dy = {-1, 1, 0, 0, 0, 0};
	static int[] dx = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1 ,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			L = Integer.parseInt(st.nextToken()); // 층 수
			R = Integer.parseInt(st.nextToken()); // 한 층의 행
			C = Integer.parseInt(st.nextToken()); // 한 층의 열
			
			if(L == 0 && R == 0 && C == 0) { // 종료 조건
				System.out.println(sb.toString());
				return;
			}
			
			map = new char[L][R][C];
			int sx = 0;
			int sy = 0;
			int sz = 0;
			
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < R; j++) {
					String s = br.readLine();
					for(int k = 0; k < C; k++) {
						map[i][j][k] = s.charAt(k);
						if(map[i][j][k] == 'S') { // 시작 지점이면
							sz = i;
							sy = j;
							sx = k;
						}
					}
				}
				br.readLine();
			}
			bfs(sz, sy, sx);

		}
	}
	
	static void bfs(int z, int y, int x) {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] v = new boolean[L][R][C];
		q.offer(new Node(z, y, x, 0)); // 시작지점 큐에 넣기
		v[z][y][x] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int curZ = cur.z;
			int curY = cur.y;
			int curX = cur.x;
			
			if(map[curZ][curY][curX] == 'E') { // 출구 도착하면
				sb.append("Escaped in " + cur.time + " minute(s).\n");
				return;
			}
			
			for(int d = 0; d < 6; d++) {
				int nz = curZ + dz[d];
				int ny = curY + dy[d];
				int nx = curX + dx[d];
				
				if(nx < 0 || nx >= C || ny < 0 || ny >= R || nz < 0 || nz >= L) continue;
				
				if(v[nz][ny][nx]) continue;
				
				if(map[nz][ny][nx] == '.' || map[nz][ny][nx] == 'E') {
					v[nz][ny][nx] = true;
					q.offer(new Node(nz, ny, nx, cur.time +1));
				}
			}
		}
		sb.append("Trapped!\n");
	}

	static class Node{
		int z, y, x;
		int time;

		public Node(int z, int y, int x, int time) {
			this.z = z;
			this.y = y;
			this.x = x;
			this.time = time;
		}
		
	}
}
