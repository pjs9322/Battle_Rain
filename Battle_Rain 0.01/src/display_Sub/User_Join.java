package display_Sub;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.control;

public class User_Join extends Sub_Frame {
	private static final long serialVersionUID = 1L;

	private JPanel panel01 = new JPanel();
	private JPanel panel02 = new JPanel();
	private JPanel panel03 = new JPanel();
	
	private JPanel panel_NORTH = new JPanel();
	private JPanel panel_SOUTH = new JPanel();

	private JLabel userID = new JLabel("������ID");
	private JLabel userPW = new JLabel("�н�����");
	private JLabel userName = new JLabel("���г���");
	
	private JTextField textID = new JTextField(15);
	private JPasswordField textPW = new JPasswordField(15);
	private JTextField textName = new JTextField(15);

	private JButton ok_Button = new JButton("����");
	private JButton cancel_Button = new JButton("���");
	
	public User_Join(control control) {
		super(control, 280, 200);
		this.setTitle("ȸ������");

		this.panel01.add(userID);
		this.panel01.add(textID);
		
		this.panel02.add(userPW);
		this.panel02.add(textPW);

		this.panel03.add(userName);
		this.panel03.add(textName);
		
		this.panel_SOUTH.add(ok_Button);
		this.panel_SOUTH.add(cancel_Button);

		this.panel_NORTH.add(panel01, BorderLayout.NORTH);
		this.panel_NORTH.add(panel02, BorderLayout.CENTER);
		this.panel_NORTH.add(panel03, BorderLayout.SOUTH);
		this.add(panel_NORTH);
		this.add(panel_SOUTH, BorderLayout.SOUTH);
		
		this.ok_Button.addActionListener(actionListener);
		this.ok_Button.setActionCommand("Join");
		
		this.cancel_Button.addActionListener(actionListener);
		this.cancel_Button.setActionCommand("Cancel");
	}


	@SuppressWarnings("deprecation")
	public void Join() {
		String alart = this.control.user_Join(textID.getText(), textPW.getText());
		if (alart != null) {
			JOptionPane.showMessageDialog(this, alart, "���", 0);
		} else {
			JOptionPane.showMessageDialog(this, "ȸ�� ������ �Ϸ�Ǿ����ϴ�.", "���Լ���", 0);
			this.dispose();
		}
	}
	
	public void Cancel() {
		this.dispose();
	}
}