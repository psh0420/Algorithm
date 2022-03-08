package study;

import java.util.Arrays;
import java.util.Scanner;
// 제한시간 초과
public class Swea3752 {
	static int N;
	static int[] scores;
	static boolean[] v, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t++) {
			N = sc.nextInt();
			scores = new int[N];
			v = new boolean[N];

			int max = 0;
			for (int i = 0; i < N; i++) {
				scores[i] = sc.nextInt();
				max += scores[i];
			}
			ans = new boolean[max+1]; // 더해서 나올 수 있는 가장 큰 수 만큼 배열 생성
			
			subset(0);
			int res = 0;
			for (int i = 0; i < max+1; i++) {
				if(ans[i]) {
					res++;
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}
	static void subset(int cnt) {
		
		if(cnt == N) {
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				if(v[i]) {
					sum += scores[i];					
				}else {
					sum += 0;
				}
			}
			
			if(!ans[sum]) {
				ans[sum] = true;
			}

			return;
		}
		
		v[cnt] = true;
		subset(cnt+1); 
		
		v[cnt] = false;
		subset(cnt+1);
		
	}
}
