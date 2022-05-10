package may;

import java.util.Scanner;

public class BOJ_2630 {
	static int N, wCnt, bCnt;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		wCnt = 0;
		bCnt = 0;
		dfs(0, 0, N);
		
		System.out.println(wCnt);
		System.out.println(bCnt);
	}
	
	static void dfs(int r, int c, int size) {
		
		int white = 0;
		int blue = 0;
		
		for(int i = r; i < r+size; i++){
			for(int j = c; j < c+size; j++) {
				if(map[i][j] == 0) {
					white++;
				}else {
					blue++;
				}
			}
		}
		
		if(white == size*size) {
			wCnt++;
			return;
		}else if(blue == size*size){
			bCnt++;
			return;
		}
		
		int newSize = size/2;
		
		dfs(r, c, newSize);
		dfs(r, c+newSize, newSize);
		dfs(r+newSize, c, newSize);
		dfs(r+newSize, c+newSize, newSize);
		
	}

}
