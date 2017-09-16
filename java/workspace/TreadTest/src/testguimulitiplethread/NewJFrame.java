package testguimulitiplethread;

public class NewJFrame extends javax.swing.JFrame {

	private int flag = -1;// -1:未启动，0：启动，1：暂停
	TestThread t;

	/** Creates new form NewJFrame */
	public NewJFrame() {
		initComponents();
		this.setVisible(true);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setText("开始");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jLabel1.setText("空");

		jButton2.setText("暂停");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("结束");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(96, 96, 96)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 156,
												Short.MAX_VALUE)
										.addGap(18, 18, 18))
								.addGroup(layout.createSequentialGroup().addComponent(jButton1)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32,
												Short.MAX_VALUE)
										.addComponent(jButton2).addGap(28, 28, 28)))
						.addComponent(jButton3).addContainerGap(73, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(34, 34, 34)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1).addComponent(jButton3).addComponent(jButton2))
						.addGap(83, 83, 83)));

		pack();
	}// </editor-fold>

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// 由于程序比较简单，所以提供了暂停功能。而且，在该程序中计算比较简单，所以在计算中暂停依旧可以保持线程安全。
		// 但对于复杂计算是不是也能提供暂停功能呢？疑惑中……复杂计算的流程多，循环跳转也多，要在暂停的同时保证线程安全难道要把整个程序暂停？
		if (flag == -1) {
			flag = 0;
			jButton1.setEnabled(false);
			jButton1.setText("继续");
			t = new TestThread();
			t.nf = this;
			t.start();
		}
		if (flag == 1) {
			flag = 0;
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		if (flag == 0) {
			flag = 1;
			jButton1.setEnabled(true);
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		flag = -1;
		jLabel1.setText("空");
		jButton1.setEnabled(true);
		jButton1.setText("开始");
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewJFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	// End of variables declaration

	public javax.swing.JButton getJButton1() {
		return jButton1;
	}

	public javax.swing.JButton getJButton2() {
		return jButton2;
	}

	public javax.swing.JLabel getJLabel1() {
		return jLabel1;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
