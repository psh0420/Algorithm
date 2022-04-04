package study;

import java.util.Scanner;

public class Boj11729 {
	static int n, cnt;
	static StringBuilder sb; // 이동 횟수를 먼저 출력하기 위해 StringBuilder 사용
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		n = sc.nextInt();
		cnt = 0; // 몇번 이동하는지 체크
		
		hanoi(n, 1, 3, 2);
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	static void hanoi(int n, int from, int to, int temp) {
		cnt++;
		
		// 종료 조건
		if(n == 1) {
			sb.append(from +" " + to + "\n");
			return;
		}
		
		// 원판 n-1개를 from에서 temp로 이동
		hanoi(n-1,from, temp, to);
		// 제일 큰 원판을 to로 이동
		sb.append(from +" " + to + "\n");
		// temp에 있던 n-1를 to로 이동
		hanoi(n-1, temp, to, from);

	}
}
