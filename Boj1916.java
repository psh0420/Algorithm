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
		int idx;   // 노드번호
		int distance;   // 이동 할 노드까지의 거리
		
		public Node(int idx, int distance) {
			super();
			this.idx = idx;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return distance - o.distance; // 오름차순 정렬
		}	
	}
	
	static ArrayList<ArrayList<Node>> adj; // 인접리스트
	static int[] dist; // 시작점에서 정점까지 가는 최단 거리 저장
	static boolean[] v; // 방문체크
	static int ans; // 최소비용
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList<>();
		dist = new int[N+1];
		v = new boolean[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE); // 최단 거리를 inf로 초기화
		
		for (int i = 0; i < N+1; i++) { // 인접리스트 생성
			adj.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) { // 인접리스트 입력받음
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adj.get(start).add(new Node(end, weight)); // start에서 end로 가는 가중치
		}
		
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken()); 
		int to = Integer.parseInt(st.nextToken()); 
		
		dijkstra(from, to);
		
		System.out.println(dist[to]);	
	}
	
	static void dijkstra(int from, int to) {
		//우선순위 큐 사용
		PriorityQueue<Node> pq = new PriorityQueue<>(); // 최단거리가 갱신된 노드를 저장

		pq.offer(new Node(from, 0)); // 우선순위 큐에 시작노드 넣음
		dist[from] = 0; // 시작지점 노드를 0으로 초기화
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.idx;
			
			if(!v[cur]) { // 방문하지 않은 노드만 체크
				v[cur] = true;
				
				for (Node node : adj.get(cur)) {
					
					// 인접 정점까지의 거리가 이미 기록된 정점까지의 거리보다 짧으면 갱신
					if(dist[node.idx] > dist[cur] + node.distance) { 					
						dist[node.idx] = dist[cur] + node.distance;
						pq.add(new Node(node.idx, dist[node.idx])); // 갱신된 값을 우선순위 큐에 삽입
					}
				}
			}
			
		}
			
	}
}
