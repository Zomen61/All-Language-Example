package testguimulitiplethread;

public class Count {
	static int c = 10000;

	public static void testCount(NewJFrame nf) {
		for (int i = 0; i < c; i++) {

			// 先考虑暂停情况：
			while (nf.getFlag() == 1) {// sleep():暂停，循环暂停。
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					//
				}
			}
			// 再考虑跳出情况：
			if (nf.getFlag() == -1) {
				break;
			}
			// 最后才输出：
			System.out.println(i);
			nf.getJLabel1().setText(String.valueOf(i));///

			// 未未优化前的代码：
			/*
			 * if(nf.getFlag()==0){ System.out.println(i);
			 * nf.getJLabel1().setText(String.valueOf(i));/// } else{
			 * if(nf.getFlag()==-1){ break; }
			 * while(nf.getFlag()==1){//sleep():暂停，循环暂停。 try {
			 * Thread.sleep(1000); } catch (InterruptedException ex) { // } }
			 * //别漏了输出： System.out.println(i);
			 * nf.getJLabel1().setText(String.valueOf(i));/// }
			 */

		}
	}

	public static void testCount() {

	}
}
