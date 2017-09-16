
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
	private JButton lookPath, support, classier, clean;
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

		this.setLocation(500,300);
		setSize(900, 700);
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
		

		} else if (arg0.getSource() == classier) {
			System.out.println("path:" + pathText.getText());
			if (pathText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "路徑不能為空");
			} else {
			
			}
					
			System.gc();

			System.out.println("分類結束");
					
		} else if (arg0.getSource() == clean) {
			processINFOText.setText("");
		}

	}

}
