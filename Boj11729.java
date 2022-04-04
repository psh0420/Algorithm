package study;

import java.util.Scanner;

public class Boj11729 {
	static int n, cnt;
	static StringBuilder sb; // �̵� Ƚ���� ���� ����ϱ� ���� StringBuilder ���
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		n = sc.nextInt();
		cnt = 0; // ��� �̵��ϴ��� üũ
		
		hanoi(n, 1, 3, 2);
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	static void hanoi(int n, int from, int to, int temp) {
		cnt++;
		
		// ���� ����
		if(n == 1) {
			sb.append(from +" " + to + "\n");
			return;
		}
		
		// ���� n-1���� from���� temp�� �̵�
		hanoi(n-1,from, temp, to);
		// ���� ū ������ to�� �̵�
		sb.append(from +" " + to + "\n");
		// temp�� �ִ� n-1�� to�� �̵�
		hanoi(n-1, temp, to, from);

	}
}
