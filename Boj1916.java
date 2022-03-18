package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1916 {
	
	static class Node implements Comparable<Node>{
		int idx;   // ����ȣ
		int distance;   // �̵� �� �������� �Ÿ�
		
		public Node(int idx, int distance) {
			super();
			this.idx = idx;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return distance - o.distance; // �������� ����
		}	
	}
	
	static ArrayList<ArrayList<Node>> adj; // ��������Ʈ
	static int[] dist; // ���������� �������� ���� �ִ� �Ÿ� ����
	static boolean[] v; // �湮üũ
	static int ans; // �ּҺ��
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList<>();
		dist = new int[N+1];
		v = new boolean[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE); // �ִ� �Ÿ��� inf�� �ʱ�ȭ
		
		for (int i = 0; i < N+1; i++) { // ��������Ʈ ����
			adj.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) { // ��������Ʈ �Է¹���
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adj.get(start).add(new Node(end, weight)); // start���� end�� ���� ����ġ
		}
		
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken()); 
		int to = Integer.parseInt(st.nextToken()); 
		
		dijkstra(from, to);
		
		System.out.println(dist[to]);	
	}
	
	static void dijkstra(int from, int to) {
		//�켱���� ť ���
		PriorityQueue<Node> pq = new PriorityQueue<>(); // �ִܰŸ��� ���ŵ� ��带 ����

		pq.offer(new Node(from, 0)); // �켱���� ť�� ���۳�� ����
		dist[from] = 0; // �������� ��带 0���� �ʱ�ȭ
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.idx;
			
			if(!v[cur]) { // �湮���� ���� ��常 üũ
				v[cur] = true;
				
				for (Node node : adj.get(cur)) {
					
					// ���� ���������� �Ÿ��� �̹� ��ϵ� ���������� �Ÿ����� ª���� ����
					if(dist[node.idx] > dist[cur] + node.distance) { 					
						dist[node.idx] = dist[cur] + node.distance;
						pq.add(new Node(node.idx, dist[node.idx])); // ���ŵ� ���� �켱���� ť�� ����
					}
				}
			}
			
		}
			
	}
}
