package MKP;

public class se {
	int cw = 0;// ��¼����
	int c = 0;// �����������
	int cv = 0; // ��¼��ֵ
	double mc = 0;// ������ֵ����
	int n = 0;// ������5����Ʒ
	int[] path;// ����¼���е��ƶ�·����Ϊ1��ʱ���ʾѡ������ݣ�Ϊ0��ʾ��ѡ��
	int maxvalue = 0;// ���������Ȩ��ֵ
	int bestv = 0;
	String bestway = "";
	int[] w; // ��������
	int[] v; // ��ֵ����
	int a = 0; // ��¼·������
	int flag; // ��¼ά�ȴ���
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
				flags[flag][0] = Integer.toString(a); // ��¼·������
				flags[flag][a] = bestway; // ��¼·��
				flagss[flag][a - 1] = maxvalue; // ��¼��ֵ
				System.out.println(bestway + " " + maxvalue + " " + bestv + " ");
				// }
				bestway = "";
			}
			return;
		}
		if (cv + v[i] <= mc && cw + w[i] <= c) { // ��Լ���ͱ�����ֵ���޽�
			path[i] = 1;
			cw += w[i];
			cv += v[i];
			b(i + 1);
			cw -= w[i];
			cv -= v[i];
		}
		if (Bound(i + 1, c, cw, cv, n, w, v) > bestv) {
			// �Һ���
			path[i] = 0;
			b(i + 1);
		}
	}
//�Һ���
	private static int Bound(int p, int c, int cw, int cv, int n, int[] w, int v[]) {
		int cleft = c - cw;
		int b = cv;
		while (p <= n && w[p] <= cleft) {
			cleft -= w[p];
			b += v[p];
			p++;
		}
		// ���
		if (p < n) {
			int tmp = v[p] / w[p];
			b += tmp * cleft;
		}
		return b;
	}

}
