package MKP;

public class se {
	int cw = 0;// 记录重量
	int c = 0;// 背包最大容量
	int cv = 0; // 记录价值
	double mc = 0;// 背包价值限制
	int n = 0;// 代表有5个物品
	int[] path;// 来记录树中的移动路径，为1的时候表示选择该数据，为0表示不选择
	int maxvalue = 0;// 背包的最大权重值
	int bestv = 0;
	String bestway = "";
	int[] w; // 重量数组
	int[] v; // 价值数组
	int a = 0; // 记录路径个数
	int flag; // 记录维度次数
	String flags[][];
	int flagss[][];

	public se(int c, double mc, int n, int[] w, int[] v, int flag, String[][] flags, int[][] flagss) {
		this.c = c;
		this.mc = mc;
		this.n = n;
		this.w = w;
		this.v = v;
		this.flag = flag;
		this.flags = flags;
		this.flagss = flagss;
		this.path = new int[n + 1];
	}

	public void b(int i) {
		if (i >= n) {
			if (cv >= maxvalue) {
				maxvalue = cv;
				bestv = cw;
				for (int k = 0; k < n; k++) {
					bestway += Integer.toString(path[k]);
				}
				// if(maxvalue==mc) {
				a++;
				flags[flag][0] = Integer.toString(a); // 记录路径个数
				flags[flag][a] = bestway; // 记录路径
				flagss[flag][a - 1] = maxvalue; // 记录价值
				System.out.println(bestway + " " + maxvalue + " " + bestv + " ");
				// }
				bestway = "";
			}
			return;
		}
		if (cv + v[i] <= mc && cw + w[i] <= c) { // 用约束和背包价值来限界
			path[i] = 1;
			cw += w[i];
			cv += v[i];
			b(i + 1);
			cw -= w[i];
			cv -= v[i];
		}
		if (Bound(i + 1, c, cw, cv, n, w, v) > bestv) {
			// 右孩子
			path[i] = 0;
			b(i + 1);
		}
	}
//右孩子
	private static int Bound(int p, int c, int cw, int cv, int n, int[] w, int v[]) {
		int cleft = c - cw;
		int b = cv;
		while (p <= n && w[p] <= cleft) {
			cleft -= w[p];
			b += v[p];
			p++;
		}
		// 拆分
		if (p < n) {
			int tmp = v[p] / w[p];
			b += tmp * cleft;
		}
		return b;
	}

}
