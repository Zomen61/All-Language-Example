
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

import javax.swing.JCheckBox;

public class GUIViewAndDelegate extends JFrame implements ActionListener{
	private JLabel title, example;
	private JTextField pathText;
	private JButton lookPath, support, classier, clean,openFormatProgram;
	private CheckPanel checkboxPanel;
	private JTextArea processINFOText;
	private static JProgressBar progressbar;

	String fileName, filePath;
	static int allDicCount;
	static Integer finishedDicCount = 0;
	String[] choiceCompany;

	public GUIViewAndDelegate() {
		super("整理你的D槽!!!!!");

		setResizable(false);

		Container containerbottom = getContentPane();
		containerbottom.setLayout(new BorderLayout());

		JPanel container = new JPanel();
		container.setLayout(new FlowLayout());
		container.setPreferredSize(new Dimension(800, 75));
		container.setMaximumSize(new Dimension(800, 75));

		title = new JLabel("選擇要整理的目錄:");
		container.add(title);
		pathText = new JTextField(25);
		pathText.setEditable(false);
		container.add(pathText);
		example = new JLabel("例:D:/新資料夾");
		container.add(example);
		lookPath = new JButton("瀏覽");
		container.add(lookPath);
		support = new JButton("支援番號");
		container.add(support);
		classier = new JButton("整理");
		container.add(classier);
		clean = new JButton("清空處理訊息");
		container.add(clean);
		openFormatProgram = new JButton("開啟檔案格式化程式");
		container.add(openFormatProgram);
		checkboxPanel = new CheckPanel();
		container.add(checkboxPanel);

		containerbottom.add(container, BorderLayout.NORTH);
		// 設定可滑動JTextArea
		processINFOText = new JTextArea("請選擇目錄並按下整理...\n");
		Font font = new Font("Serif", 1, 16);
		processINFOText.setFont(font);
		processINFOText.setEditable(false);
		// 設定JTextArea更新會拉到最底下
		DefaultCaret caret = (DefaultCaret) processINFOText.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		// 設定scroll只能垂直滑動
		JScrollPane g = new JScrollPane(processINFOText);
		g.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		containerbottom.add(g, BorderLayout.CENTER);

		progressbar = new JProgressBar();
		progressbar.setOrientation(JProgressBar.HORIZONTAL);
		progressbar.setMinimum(0);
		progressbar.setMaximum(100);
		progressbar.setValue(0);
		progressbar.setStringPainted(true);
		progressbar.setPreferredSize(new Dimension(900, 25));
		progressbar.setBorderPainted(true);
		progressbar.setBackground(Color.lightGray);

		containerbottom.add(progressbar, BorderLayout.SOUTH);

		lookPath.addActionListener(this);
		classier.addActionListener(this);
		support.addActionListener(this);
		clean.addActionListener(this);
		openFormatProgram.addActionListener(this);
		
		pathText.setText("G:\\A\\");

		this.setLocation(500,300);
		setSize(1200, 700);
		setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == lookPath) {
			JFileChooser chooser;
			if (pathText.getText() != "") {
				chooser = new JFileChooser(pathText.getText());
			} else {
				chooser = new JFileChooser();
			}
			chooser.setMultiSelectionEnabled(false);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (chooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
				pathText.setText(chooser.getSelectedFile().getAbsolutePath());
				System.out.println(pathText.getText());
			}

		} else if (arg0.getSource() == support) {
			supportWindows GUI2 = new supportWindows();
			GUI2.setVisible(true);
			GUI2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		} else if (arg0.getSource() == classier) {
			System.out.println("path:" + pathText.getText());
			if (pathText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "路徑不能為空");
			} else {
				choiceCompany = checkboxPanel.search.toArray(new String[checkboxPanel.search.size()]);
				if (choiceCompany.length == 0) {
					JOptionPane.showMessageDialog(null, "請先選擇片商");
				} else {
					classier.setEnabled(false);

					// Initialize progress property.
					FileProcess processDelegate = new FileProcess();

					processINFOText.append("開始格式化...\n");
					processINFOText.repaint();
					processDelegate.fileNameFormat(pathText.getText(), choiceCompany);
					processINFOText.append("格式化結束...\n");
					processINFOText.append("讀取目綠中...\n");
					processINFOText.repaint();
					String[] dicList = processDelegate.getFileList(pathText.getText(), choiceCompany);

					String[][] splitedDicList;
					allDicCount = dicList.length;
					processINFOText.append("共找到" + allDicCount + "個目錄需要分類\n");
					processINFOText.repaint();
					splitedDicList = processDelegate.fileNamesplit(dicList, choiceCompany);
					
					Thread thread1 = new Thread() {
						public void run() {
							WebSourceCodeFetch netDelegate = new WebSourceCodeFetch();

							processINFOText.append("開始分類...\n");
							processINFOText.repaint();
							finishedDicCount = 0;

							// Make random progress.

							for (int i = 0; i < splitedDicList.length; i++) {
								processINFOText.append("分類" + splitedDicList[i][0] + splitedDicList[i][1] + "中...\n");
								processINFOText.repaint();
								String status = netDelegate.autoClassier(splitedDicList[i][0], splitedDicList[i][1],
										pathText.getText());
								if (status.equals("成功")) {
									processINFOText
											.append("分類" + splitedDicList[i][0] + splitedDicList[i][1] + "完成\n\n");
									System.out.println("Finish:" + finishedDicCount);
									processINFOText.repaint();
								} else {
									processINFOText.append("分類" + splitedDicList[i][0] + splitedDicList[i][1] + "失敗\n");
									processINFOText.append("原因:" + status + "\n\n");
									processINFOText.repaint();
									System.out.println("Finish:" + finishedDicCount);
									processINFOText.repaint();
								}

								processINFOText.setCaretPosition(processINFOText.getDocument().getLength());
								processINFOText.repaint();
								finishedDicCount++;
								float percentfloat = (float)finishedDicCount/(float)allDicCount *100;
								final int percent = (int) percentfloat ;
								try {
									SwingUtilities.invokeLater(new Runnable() {
										public void run() {
											System.out.println(percent);
											GUIViewAndDelegate.updateBar(percent);
										}
									});
									java.lang.Thread.sleep(100);
								} catch (InterruptedException e) {
									;
								}

							}
							processINFOText.append("分類結束\n");
							classier.setEnabled(true);
						}
					};

					thread1.start();
					
					System.gc();

					System.out.println("分類結束");

				}
			}
		} else if (arg0.getSource() == clean) {
			processINFOText.setText("");
		}else if(arg0.getSource() == openFormatProgram) {
			FormatFileDirectory GUI3 = new FormatFileDirectory();
			GUI3.setVisible(true);
			GUI3.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}

	}

	  public static void updateBar(int newValue) {
		  progressbar.setValue(newValue);
		  progressbar.revalidate();
	  }



}
