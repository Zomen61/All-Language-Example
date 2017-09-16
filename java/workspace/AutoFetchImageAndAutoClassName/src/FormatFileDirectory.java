import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FormatFileDirectory extends JFrame implements ActionListener{
	final private String[] filterString = {".mp4",".wmv","mkv","avi"};
	private JLabel title, example;
	private JTextField pathText;
	private JButton lookPath,Format;
	
	FormatFileDirectory(){
		super("將影片都放入資料夾中");
		
	    Container container = getContentPane();
	    container.setLayout(new FlowLayout());
		
		title = new JLabel("選擇要整理的目錄:");
		add(title);
		pathText = new JTextField(25);
		pathText.setEditable(false);
		add(pathText);
		example = new JLabel("例:D:/新資料夾");
		add(example);
		lookPath = new JButton("瀏覽");
		add(lookPath);
		Format = new JButton("整理");
		add(Format);
		
		pathText.setText("G:\\A\\");
		
		lookPath.addActionListener(this);
		Format.addActionListener(this);
		
		this.setLocation(500,500);
		setSize(300, 500);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == lookPath) {
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

		} else if (e.getSource() == Format) {
			FileProcess fileprocess = new FileProcess();
			File fileDir = new File(pathText.getText());
			String[] list = fileprocess.getFiles(fileDir, false,filterString, true);
			for(String every:list) {
				File temp = new File(every);
				String tempPath = temp.getParent() +"\\"+temp.getName();
				String  newDirPath = tempPath;
				for(String everyFilter:filterString) {
					newDirPath = newDirPath.replaceAll(everyFilter, "");
				}
				
				System.out.println(newDirPath);
				fileprocess.createDirectory(newDirPath);
				
				fileprocess.moveFileToDirectory(every,newDirPath);
				
			}
			
			
		}
		
	}
}
