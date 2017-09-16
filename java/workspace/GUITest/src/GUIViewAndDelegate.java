
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.JCheckBox;

public class GUIViewAndDelegate extends JFrame implements ActionListener{
	private JLabel title, ansTitle;
	private JTextField operand1,operand2,operator,Ans;
	private JButton calculate,clean;

	public GUIViewAndDelegate() {
		super("計算機");

		setResizable(false);

		Container container = getContentPane();
		container.setLayout(new FlowLayout());


		title = new JLabel("算式:");
		container.add(title);
		operand1 = new JTextField(5);
		container.add(operand1);
		operator = new JTextField(1);
		container.add(operator);
		operand2 = new JTextField(5);
		container.add(operand2);
		calculate = new JButton("計算");
		container.add(calculate);
		
//		clean = new JButton("清空");
//		container.add(clean);

		ansTitle = new JLabel("答案:");
		container.add(ansTitle);
		
		Ans = new JTextField(10);
		Ans.setEditable(false);
		container.add(Ans);

		calculate.addActionListener(this);
		
		this.setLocation(500,300);
		setSize(275, 150);
		setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == calculate) {
			String operatorString = operator.getText();
			System.out.println(operatorString);
			
			int A = Integer.parseInt(operand1.getText());
			int B = Integer.parseInt(operand2.getText());
			int ans = 0;
			Boolean Error = false;
			
			switch (operatorString){
			case "+":
				ans = A + B; 
				break;
			case "-":
				ans = A - B; 
				break;
			case "*":
				ans = A * B; 
				break;
			case "/":
				ans = A / B; 
				break;
			default:
				Error = true;
				break;
			}
			if (Error) {
				Ans.setText("輸入值錯誤");
			}else{
				Ans.setText(String.valueOf(ans));
			}
			
			System.out.println(ans);
		} 

	}

}
