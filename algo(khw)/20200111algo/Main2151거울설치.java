import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main2151거울설치 {
	static char[][] carr;
	static int[] dx = {-1,1,0,0};//위, 아래, 좌, 우
	static int[] dy = {0,0,-1,1};
	static int sx, sy, N, ex, ey;
	static int dist[][][];
	static Queue<SPoint> q = new LinkedList<>();
	static public class SPoint{
		int x;
		int y;
		int dir;
		public SPoint(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;//방향
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		carr = new char[N][N];
		dist = new int[N][N][4];
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<N;j++){
				Arrays.fill(dist[i][j], 987654321);
				carr[i][j] = s.charAt(j);
				if(carr[i][j]=='#') {
					sx = i;
					sy = j;
				}
			}
		}
		carr[sx][sy]='s';
		for(int k=0;k<4;k++) {
			q.add(new SPoint(sx, sy, k));
			dist[sx][sy][k]=0;
		}
		bfs();
		int min = Integer.MAX_VALUE;
		for(int k=0;k<4;k++) {
			if(min>dist[ex][ey][k]) {
				min = dist[ex][ey][k];
			}
		}
		System.out.println(min);
	}

	private static void bfs() {
		SPoint p = null;
		while(!q.isEmpty()) {
			p = q.poll();
			//			if(carr[p.x][p.y]=='#') {
			//				int min = Integer.MAX_VALUE;
			////				System.out.println(dist[p.x][p.y][p.dir]);
			//				for(int k=0;k<4;k++) {
			//					if(min>dist[p.x][p.y][k]) {
			//						min = dist[p.x][p.y][k];
			//					}
			//				}
			//				System.out.println(min);
			//				return;
			//			}

			int tx = p.x + dx[p.dir];
			int ty = p.y + dy[p.dir];

			if(tx<0 || ty<0 || tx>=N || ty>=N) {
				continue;
			}

			if(carr[tx][ty]=='*') {
				continue;
			}

			else if(carr[tx][ty]=='!') {
				if(dist[tx][ty][p.dir]>dist[p.x][p.y][p.dir]) {
					dist[tx][ty][p.dir]=dist[p.x][p.y][p.dir];
					q.add(new SPoint(tx, ty, p.dir));
				}

				if(p.dir==0) {//위로 이동할때 좌, 우 좌표 add
					if(dist[tx][ty][2]>dist[p.x][p.y][p.dir]+1) {
						q.add(new SPoint(tx, ty, 2));
						dist[tx][ty][2]=dist[p.x][p.y][p.dir]+1;						
					}
					if(dist[tx][ty][3]>dist[p.x][p.y][p.dir]+1) {
						q.add(new SPoint(tx, ty, 3));
						dist[tx][ty][3]=dist[p.x][p.y][p.dir]+1;						
					}
				}
				if(p.dir==1) {
					if(dist[tx][ty][2]>dist[p.x][p.y][p.dir]+1) {
						q.add(new SPoint(tx, ty, 2));
						dist[tx][ty][2]=dist[p.x][p.y][p.dir]+1;
					}
					if(dist[tx][ty][3]>dist[p.x][p.y][p.dir]+1) {
						q.add(new SPoint(tx, ty, 3));
						dist[tx][ty][3]=dist[p.x][p.y][p.dir]+1;
					}
				}
				if(p.dir==2) {
					if(dist[tx][ty][0]>dist[p.x][p.y][p.dir]+1) {
						q.add(new SPoint(tx, ty, 0));
						dist[tx][ty][0]=dist[p.x][p.y][p.dir]+1;
					}
					if(dist[tx][ty][1]>dist[p.x][p.y][p.dir]+1) {
						q.add(new SPoint(tx, ty, 1));
						dist[tx][ty][1]=dist[p.x][p.y][p.dir]+1;
					}
				}
				if(p.dir==3) {
					if(dist[tx][ty][0]>dist[p.x][p.y][p.dir]+1) {
						q.add(new SPoint(tx, ty, 0));
						dist[tx][ty][0]=dist[p.x][p.y][p.dir]+1;
					}
					if(dist[tx][ty][1]>dist[p.x][p.y][p.dir]+1) {
						q.add(new SPoint(tx, ty, 1));
						dist[tx][ty][1]=dist[p.x][p.y][p.dir]+1;
					}
				}
			}
			else if(carr[tx][ty]=='.') {
				if(dist[tx][ty][p.dir]>dist[p.x][p.y][p.dir]) {
					dist[tx][ty][p.dir]=dist[p.x][p.y][p.dir];
					q.add(new SPoint(tx, ty, p.dir));
				}
			}
			else if(carr[tx][ty]=='#') {
				ex = tx;
				ey = ty;
				if(dist[tx][ty][p.dir]>dist[p.x][p.y][p.dir]) {
					dist[tx][ty][p.dir]=dist[p.x][p.y][p.dir];
				}
//				int min = Integer.MAX_VALUE;
//				for(int k=0;k<4;k++) {
//					if(min>dist[tx][ty][k]) {
//						min = dist[tx][ty][k];
//					}
//				}
//				System.out.println(dist[tx][ty][p.dir]);
//				return;
			}
		}
	}
}