package MKP;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

public class fi {
	static String[] splits;
	static String[] splitss;
	static String[] sp;
	static int[][][] a; // ���ڴ�Ŷ��������
	static String[] h; // ��ŵ�һ������
	static int c; // ������Լ��
	static int n = 0; // ������ά��
	static double givevalue = 0; // �����ļ۸�
	static String[][] flags; // ·����¼��
	static int[][] flagss; // ��ֵ��¼��

	public static void main(String[] args) {
		File file = new File("C:\\Users\\14651\\Desktop\\6����Ʒ10��Լ��.txt");// �ҵ�txt�ı����Ŀ¼�������Լ���·���޸ļ���
		int[][][] gg = s(file); // ���շ��ص�����
		int wh = gg[0].length; // ��¼��ά������
		flags = new String[wh][wh * wh * wh * wh * wh]; // ��¼·���ı�
		flagss = new int[wh][wh * wh * wh * wh * wh]; // ��¼��ֵ�ı�

		// �����ȡ�õ�����
		System.out.println("��ȡ���������ݣ�λ��������0����");
		for (int iss = 0; iss < wh; iss++) {
			for (int is = 0; is < gg[0].length; is++) {
				System.out.print(gg[0][iss][is] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < wh - 2; i++) {
			System.out.println("<========��" + (i + 1) + "��Լ���Ŀ�ѡ·�����ֵ======>");
			c = gg[0][wh - 1][i]; // ��ǰ����������
			// ʵ����
			se a = new se(c, givevalue, n, gg[0][i + 1], gg[0][0], i, flags, flagss);
			a.b(0);
		}
		int as = Integer.parseInt(flags[0][0]);
		int aas = 1;
		String ap = "";
		int app = 0;
		// ���·����۸�
		for (int i = 0; i < as; i++) {
			String u = flags[0][as]; // �ӵ�һ�е����һ����ʼ
			for (int j = 1; j < wh - 2; j++) {
				for (int k = Integer.parseInt(flags[j][0]); k >= 1; k--) { // ����ĵ�һ��Ԫ��Ϊ���Ž�ĸ���
					// �����Ӽ��Ƿ����
					if (u.equals(flags[j][k])) {
						ap = u; // ��¼·��
						app = flagss[j][k - 1]; // ��¼��ֵ
						aas++; // �����ж�
					} else
						continue;
				}
			}
			if (as > 1)
				as--;
		}
		if (aas >= wh - 2) {
			System.out.println("���ŵ�·��ѡ��Ϊ��" + ap + "  ");
			System.out.println("���ŵļ�ֵΪ��" + app);
			System.out.print("ѡ��   ");
			for (int j = 0; j < ap.length(); j++) {
				char tem = ap.charAt(j);
				if (tem == '1') // �ո�
					System.out.print((j + 1) + "��  ");
			}
			System.out.print("��Ʒ");

		} else
			System.out.println("û�����Ž���");
	}

	// ��ȡ����
	public static int[][][] s(File file) {
		int i = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// ����һ��BufferedReader������ȡ�ļ�
			String s = null;
			s = br.readLine();
			h = s.split(" ");
			n = Integer.parseInt(h[0]); // ��ȡ��ά��
			givevalue = Integer.parseInt(h[2]); // ��ȡ�۸�
			splitss = new String[Integer.parseInt(h[1]) + 2];// ��ȥ��һ��ʣ�������
			sp = new String[Integer.parseInt(h[1]) + 2]; // һ������ж��ٸ�����
			a = new int[1][Integer.parseInt(h[1]) + 2][Integer.parseInt(h[0]) * Integer.parseInt(h[0]) * 2]; // ת���ɶ�ά����

			// ѭ��������
			while ((s = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				splits = s.split("\n");
				splitss[i] = splits[0];
				int count = 0;// ͳ�ƿո����
				// �Կո�Ϊ�ָ���
				for (int j = 0; j < splitss[i].length(); j++) {
					char tem = s.charAt(j);
					if (tem == ' ') // �ո�
						count++;
				}
				sp = splitss[i].split(" ");
				for (int k = 0; k < count + 1; k++) {// ȡ��Ԫ��
					a[0][i][k] = Integer.parseInt(sp[k]);// ���ַ�����ת�����Σ�����ʽ��������
				}
				i++;
			}
			br.close(); // �رն�ȡ����ֹ�ļ���ʧ
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
