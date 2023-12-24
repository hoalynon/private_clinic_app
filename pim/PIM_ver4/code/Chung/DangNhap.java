package Chung;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import BacSi.BSHome;
import BenhNhan.BNHome;
import Connection.DatabaseConnector;
import QuanLy.QLHome;

public class DangNhap implements ActionListener, ComponentListener{
	private JFrame frame;
    // PIM, we care your health
	private JPanel panelTitle;
    // Content panels
	private JPanel panelContent;
	private PanelRound panelContentInner;
    // 2 inputs
	private TextFieldRound txtFieldAccName;
	private TextFieldRound txtFieldPass; // show password input field
	private PasswordFieldRound passFieldPass; // hide password input field
    // Btns
	private ButtonRound btnSignIn;
	private ButtonRound btnView;
	private ButtonRound btnHide;
	// labels
	private JLabel labelTitleSymbol; 
	private JLabel labelTitleName;
	private JLabel labelTitleSlogan;
	private JLabel labelContact; // 1800 1091
	
	private static DatabaseConnector database;
	
	public DangNhap(){
		database = new DatabaseConnector();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,450);
		frame.setLocationRelativeTo(null); // center the window on the screen
		frame.getContentPane().setBackground(Color.decode("#d6e7ef"));
		frame.getContentPane().setLayout(new BorderLayout());
		frame.addComponentListener(this);
		int widthframe = frame.getWidth();
		int heightframe = frame.getHeight();
		
		panelTitle = new JPanel();
		panelTitle.setBackground(Color.decode("#ffffff"));
		panelTitle.setPreferredSize(new Dimension (50,50));
		panelTitle.setLayout(null);

		panelContent = new JPanel();
		panelContent.setBackground(Color.decode("#1b3954"));
		panelContent.setPreferredSize(new Dimension (widthframe, heightframe - 50));
		panelContent.setLayout(null);
		
		panelContentInner = new PanelRound();
		panelContentInner.setBounds((widthframe - 500)/2, (heightframe - 50 - 300 - 70)/2, 500, 300);
		panelContentInner.setRoundAllCorner(20);
		panelContentInner.setLayout(null);
		
//		labelTitleSymbol = new JLabel(new ImageIcon("drug.png"));
		labelTitleSymbol = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("drug.png")));
//		labelTitleSymbol = new JLabel();
 		labelTitleSymbol.setBounds(10, 0, 50, 50);
		panelTitle.add(labelTitleSymbol);
	
		labelTitleName = new JLabel("PIM");
		labelTitleName.setForeground(Color.decode("#28526a"));
		labelTitleName.setBounds(70, 5, 100, 20);
		labelTitleName.setFont(new Font("Bevan", Font.BOLD, 20));
		panelTitle.add(labelTitleName);
		
		labelTitleSlogan = new JLabel("We care your health");
		labelTitleSlogan.setForeground(Color.decode("#28526a"));
		labelTitleSlogan.setBounds(70, 25, 300, 20);
		labelTitleSlogan.setFont(new Font("Bevan", Font.PLAIN, 14));
		panelTitle.add(labelTitleSlogan);
		
		JLabel labelAccName = new JLabel("Ten Dang Nhap");
		labelAccName.setFont(new Font("Bevan", Font.PLAIN, 12));
		labelAccName.setBounds(130, 30, 200, 20);
		labelAccName.setHorizontalAlignment(SwingConstants.LEFT);
		panelContentInner.add(labelAccName);
		
		txtFieldAccName = new TextFieldRound();
		txtFieldAccName.setBounds(130, 55, 220, 20);
		txtFieldAccName.setColumns(10);
		txtFieldAccName.setRoundAllCorner(10);
		panelContentInner.add(txtFieldAccName);
		
		JLabel labelPass = new JLabel("Mat Khau");
		labelPass.setFont(new Font("Bevan", Font.PLAIN, 12));
		labelPass.setBounds(130, 80, 115, 20);
		labelPass.setHorizontalAlignment(SwingConstants.LEFT);
		panelContentInner.add(labelPass);
		
		txtFieldPass = new TextFieldRound();
		txtFieldPass.setBounds(130, 105, 220, 20);
		txtFieldPass.setFont(new Font("Bevan", Font.PLAIN, 12));
		txtFieldPass.setColumns(10);
		txtFieldPass.addActionListener(this);
		txtFieldPass.setRoundAllCorner(10);
		panelContentInner.add(txtFieldPass);
		
		passFieldPass = new PasswordFieldRound();
		passFieldPass.setBounds(130, 105, 220, 20);
		passFieldPass.setFont(new Font("Bevan", Font.PLAIN, 12));
		passFieldPass.setColumns(10);
		passFieldPass.addActionListener(this);
		passFieldPass.setRoundAllCorner(10);
		panelContentInner.add(passFieldPass);
		
		btnView = new ButtonRound();
		//btnView.setIcon(new ImageIcon("view.png"));
		btnView.setIcon(new ImageIcon(getClass().getClassLoader().getResource("view.png")));
//		btnView.setIcon();
		btnView.setBackground(Color.decode("#d6e7ef"));
		btnView.addActionListener(this);
		btnView.setBorderPainted(false);
		btnView.setBounds(360, 102, 25, 25);
		btnView.setRoundAllCorner(5);
		panelContentInner.add(btnView);
		btnView.setVisible(false);
		
		btnHide = new ButtonRound();
//		btnHide.setIcon(new ImageIcon("hide.png"));
		btnHide.setIcon(new ImageIcon(getClass().getClassLoader().getResource("hide.png")));
//		btnHide.setIcon();
		btnHide.setBackground(Color.decode("#d6e7ef"));
		btnHide.addActionListener(this);
		btnHide.setBorderPainted(false);
		btnHide.setBounds(360, 102, 25, 25);
		btnHide.setRoundAllCorner(5);
		panelContentInner.add(btnHide);
		
		btnSignIn = new ButtonRound();
		btnSignIn.setText("Login");
		btnSignIn.setForeground(Color.decode("#ffffff"));
		btnSignIn.setBackground(Color.decode("#1b3954"));
		btnSignIn.addActionListener(this);
		btnSignIn.setFont(new Font("Bevan", Font.BOLD, 16));
		btnSignIn.setBorderPainted(false);
		btnSignIn.setBounds(190, 170, 115, 30);
		btnSignIn.setRoundAllCorner(20);
		panelContentInner.add(btnSignIn);
		
		labelContact = new JLabel("Forgot your account? Contact us at 18001091");
		labelContact.setFont(new Font("Bevan", Font.PLAIN, 14));
		labelContact.setBounds(100, 230, 350, 20);
		panelContentInner.add(labelContact);
		
		frame.getContentPane().add(panelTitle,BorderLayout.NORTH);
		frame.getContentPane().add(panelContent,BorderLayout.CENTER);
		panelContent.add(panelContentInner);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnSignIn) {
			DangNhap();
		}
		if (e.getSource() == btnView) {
			txtFieldPass.setText(passFieldPass.getText());
			passFieldPass.setVisible(false);
			txtFieldPass.setVisible(true);
			btnHide.setVisible(true);
			btnView.setVisible(false);
		}
		if (e.getSource() == btnHide) {
			passFieldPass.setText(txtFieldPass.getText());
			txtFieldPass.setVisible(false);
			passFieldPass.setVisible(true);
			btnView.setVisible(true);
			btnHide.setVisible(false);
		}
	}
	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getSource() == frame) {
			panelContentInner.setLocation((frame.getWidth() - 500)/2, (frame.getHeight() - 50 - 300 - 70)/2);
		}
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void DangNhap() {
		
		String query = "SELECT * FROM TAIKHOAN WHERE TENDANGNHAP = '" + txtFieldAccName.getText() +"'";
		if (txtFieldPass.isVisible()) {
			if(txtFieldPass.getText().equals("")) {
				query += " AND MATKHAU IS NULL";
			}
			else {
				query += " AND MATKHAU = '" + txtFieldPass.getText() + "'";
			}
		}
		else if (passFieldPass.isVisible()){
			if(passFieldPass.getText().equals("")) {
				query += " AND MATKHAU IS NULL";
			}
			else {
				query += " AND MATKHAU = '" + passFieldPass.getText() + "'";
			}
		}
		System.out.println(query);

		try {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/PIMpdb", "rootdata", "mypassword");
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			
			if (resultset.next())
			{
				if (txtFieldAccName.getText().substring(0,2).equals("BN")) {
					new BNHome(this);
					frame.dispose();
				}
				else if (txtFieldAccName.getText().substring(0,2).equals("BS")) {
					new BSHome(this);
					frame.dispose();
				}
				else if (txtFieldAccName.getText().substring(0,2).equals("QL")) {
					new QLHome(this);
					frame.dispose();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Loi: " + "Thong tin khong hop le !\n\tVui long dang nhap lai.", "Loi", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public String getID() {
		return txtFieldAccName.getText();
	}
	/*public String getDatabaseURL() {
		return DatabaseURL;
	}
	public String getDatabaseUsername() {
		return DatabaseUsername;
	}
	public String getDatabasePassword() {
		return DatabasePassword;
	}*/
	public DatabaseConnector getDatabase() {
		return database;
	}
	public JFrame getFrame() {
		return frame;
	}
	
	public JPanel getPanelContent() {
		return panelContent;
	}

}
	
