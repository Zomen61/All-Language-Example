package testguimulitiplethread;

public class TestThread extends Thread {

	NewJFrame nf;

	@Override
	public void run() {
		// 在Count中暂停线程即可，无需在run()中暂停线程。
		/*
		 * while(true){ if(nf.getFlag()==1){ try { Thread.sleep(1000); } catch
		 * (InterruptedException ex) { // } }
		 */
		// else{
		Count.testCount(nf);
		// 执行计算完毕后跳出，线程结束。
		nf.setFlag(-1);
		// break;
		// }
		// }

	}

}
