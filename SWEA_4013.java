package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4013 {
	static int K, res;
	static int[][] map;
	static int[] scores = {1, 2, 4, 8};
	static int[] ratation;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= TC; t++) {
			K = Integer.parseInt(in.readLine()); // 자석 회전 횟수
			map = new int[4][8];			
			res = 0;
			
			StringTokenizer st = null;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < 8; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());					
				}
			} 
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1; // 자석 번호 ( 0부터 시작하도록 -1)
				int dir = Integer.parseInt(st.nextToken()); // 자석 회전 방향
				
				check(num, dir); // 회전방향 정하기
				
				for (int j = 0; j < 4; j++) {
					ratate(j); //회전시키기
				}
			}
			
			for (int i = 0; i < 4; i++) {
				if(map[i][0] == 1) res += scores[i];
			}
			
			System.out.println("#" + t + " " + res);
		}
		
	}
	static void ratate(int idx) {
		int temp;
		if(ratation[idx] == 1) { // 시계방향으로 
			temp = map[idx][7];
			for (int j = 7; j > 0; j--) {
				map[idx][j] = map[idx][j-1];
			}
			map[idx][0] = temp;
		}else if(ratation[idx] == -1) { // 반시계방향
			temp = map[idx][0];
			for (int j = 0; j < 7; j++) {
				map[idx][j] = map[idx][j+1];
			}
			map[idx][7] = temp;
		}else {
			return;
		}
	}
	
	static void check(int n, int d) { 
		ratation = new int[4]; // 초기화 조심
		ratation[n] = d;
		
		// 현재 자석에서 오른쪽으로 
		for (int i = n+1; i < 4; i++) {
			if(map[i-1][2] != map[i][6]) {
				if(ratation[i-1] == 1) {
					ratation[i] = -1; // 반시계방향
				}else {
					ratation[i] = 1; // 시계방향
				}
			}else {
				ratation[i] = 0; // 움직이지 않음
				break;
			}
		}
		
		// 현재 자석에서 왼쪽으로 
		for (int i = n-1; i >= 0; i--) {
			if(map[i][2] != map[i+1][6]) {
				if(ratation[i+1] == 1) {
					ratation[i] = -1;
				}else {
					ratation[i] = 1;
				}
			}else {
				ratation[i] = 0;
				break;
			}
		}
	}

}
