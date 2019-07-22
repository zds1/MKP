package MKP;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

public class fi {
	static String[] splits;
	static String[] splitss;
	static String[] sp;
	static int[][][] a; // 用于存放读入的数据
	static String[] h; // 存放第一行数据
	static int c; // 给定的约束
	static int n = 0; // 给定的维度
	static double givevalue = 0; // 给定的价格
	static String[][] flags; // 路径记录表
	static int[][] flagss; // 价值记录表

	public static void main(String[] args) {
		File file = new File("C:\\Users\\14651\\Desktop\\6个商品10个约束.txt");// 我的txt文本存放目录，根据自己的路径修改即可
		int[][][] gg = s(file); // 接收返回的数据
		int wh = gg[0].length; // 记录二维的行数
		flags = new String[wh][wh * wh * wh * wh * wh]; // 记录路径的表
		flagss = new int[wh][wh * wh * wh * wh * wh]; // 记录价值的表

		// 输出读取好的数据
		System.out.println("读取进来的数据，位数不足用0补：");
		for (int iss = 0; iss < wh; iss++) {
			for (int is = 0; is < gg[0].length; is++) {
				System.out.print(gg[0][iss][is] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < wh - 2; i++) {
			System.out.println("<========第" + (i + 1) + "个约束的可选路径与价值======>");
			c = gg[0][wh - 1][i]; // 当前的限制条件
			// 实例化
			se a = new se(c, givevalue, n, gg[0][i + 1], gg[0][0], i, flags, flagss);
			a.b(0);
		}
		int as = Integer.parseInt(flags[0][0]);
		int aas = 1;
		String ap = "";
		int app = 0;
		// 输出路径与价格
		for (int i = 0; i < as; i++) {
			String u = flags[0][as]; // 从第一行的最后一个开始
			for (int j = 1; j < wh - 2; j++) {
				for (int k = Integer.parseInt(flags[j][0]); k >= 1; k--) { // 数组的第一个元素为最优解的个数
					// 遍历子集是否相等
					if (u.equals(flags[j][k])) {
						ap = u; // 记录路径
						app = flagss[j][k - 1]; // 记录价值
						aas++; // 存在判断
					} else
						continue;
				}
			}
			if (as > 1)
				as--;
		}
		if (aas >= wh - 2) {
			System.out.println("最优的路径选择为：" + ap + "  ");
			System.out.println("最优的价值为：" + app);
			System.out.print("选择：   ");
			for (int j = 0; j < ap.length(); j++) {
				char tem = ap.charAt(j);
				if (tem == '1') // 空格
					System.out.print((j + 1) + "号  ");
			}
			System.out.print("商品");

		} else
			System.out.println("没有最优解噢");
	}

	// 读取数据
	public static int[][][] s(File file) {
		int i = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			s = br.readLine();
			h = s.split(" ");
			n = Integer.parseInt(h[0]); // 读取出维度
			givevalue = Integer.parseInt(h[2]); // 读取价格
			splitss = new String[Integer.parseInt(h[1]) + 2];// 除去第一行剩余多少行
			sp = new String[Integer.parseInt(h[1]) + 2]; // 一行最多有多少个数据
			a = new int[1][Integer.parseInt(h[1]) + 2][Integer.parseInt(h[0]) * Integer.parseInt(h[0]) * 2]; // 转换成二维数组

			// 循环读数据
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				splits = s.split("\n");
				splitss[i] = splits[0];
				int count = 0;// 统计空格个数
				// 以空格为分隔符
				for (int j = 0; j < splitss[i].length(); j++) {
					char tem = s.charAt(j);
					if (tem == ' ') // 空格
						count++;
				}
				sp = splitss[i].split(" ");
				for (int k = 0; k < count + 1; k++) {// 取出元素
					a[0][i][k] = Integer.parseInt(sp[k]);// 将字符数字转成整形，按格式存入数组
				}
				i++;
			}
			br.close(); // 关闭读取，防止文件丢失
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
