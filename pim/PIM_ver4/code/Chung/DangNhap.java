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
	
	private static DatabaseConnector database;
	
	private JFrame frame;
	private JPanel panelTitle;
	private JPanel panelContent;
	private PanelRound panelContentInner;
	
	private ButtonRound buttonDangNhap;
	
	private TextFieldRound textfieldTenDangNhap;
	private TextFieldRound textfieldMatKhau;
	
	private PasswordFieldRound passwordfieldMatKhau;
	
	private ButtonRound buttonView;
	private ButtonRound buttonHide;
	
	private JLabel labeltitleSymbol;
	private JLabel labeltitleName;
	private JLabel labeltitleSlogan;
	private JLabel labelContact;
	
	
	public DangNhap(){
		
		database = new DatabaseConnector();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,450);
		frame.setLocationRelativeTo(null);
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
		
		//labeltitleSymbol = new JLabel(new ImageIcon("PIM_ver4\\picture\\drug.png"));
		labeltitleSymbol = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("drug.png")));
		labeltitleSymbol.setBounds(10, 0, 50, 50);
		panelTitle.add(labeltitleSymbol);
		
		labeltitleName = new JLabel("PIM");
		labeltitleName.setForeground(Color.decode("#28526a"));
		labeltitleName.setBounds(70, 5, 100, 20);
		labeltitleName.setFont(new Font("Bevan", Font.BOLD, 20));
		panelTitle.add(labeltitleName);
		
		labeltitleSlogan = new JLabel("We care your health");
		labeltitleSlogan.setForeground(Color.decode("#28526a"));
		labeltitleSlogan.setBounds(70, 25, 300, 20);
		labeltitleSlogan.setFont(new Font("Bevan", Font.PLAIN, 14));
		panelTitle.add(labeltitleSlogan);
		
		JLabel labelTenDangNhap = new JLabel("Ten Dang Nhap");
		labelTenDangNhap.setFont(new Font("Bevan", Font.PLAIN, 12));
		labelTenDangNhap.setBounds(130, 30, 200, 20);
		labelTenDangNhap.setHorizontalAlignment(SwingConstants.LEFT);
		panelContentInner.add(labelTenDangNhap);
		
		textfieldTenDangNhap = new TextFieldRound();
		textfieldTenDangNhap.setBounds(130, 55, 220, 20);
		textfieldTenDangNhap.setColumns(10);
		textfieldTenDangNhap.setRoundAllCorner(10);
		panelContentInner.add(textfieldTenDangNhap);
		
		JLabel labelMatKhau = new JLabel("Mat Khau");
		labelMatKhau.setFont(new Font("Bevan", Font.PLAIN, 12));
		labelMatKhau.setBounds(130, 80, 115, 20);
		labelMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		panelContentInner.add(labelMatKhau);
		
		textfieldMatKhau = new TextFieldRound();
		textfieldMatKhau.setBounds(130, 105, 220, 20);
		textfieldMatKhau.setFont(new Font("Bevan", Font.PLAIN, 12));
		textfieldMatKhau.setColumns(10);
		textfieldMatKhau.addActionListener(this);
		textfieldMatKhau.setRoundAllCorner(10);
		panelContentInner.add(textfieldMatKhau);
		
		passwordfieldMatKhau = new PasswordFieldRound();
		passwordfieldMatKhau.setBounds(130, 105, 220, 20);
		passwordfieldMatKhau.setFont(new Font("Bevan", Font.PLAIN, 12));
		passwordfieldMatKhau.setColumns(10);
		passwordfieldMatKhau.addActionListener(this);
		passwordfieldMatKhau.setRoundAllCorner(10);
		panelContentInner.add(passwordfieldMatKhau);
		
		buttonView = new ButtonRound();
		//buttonView.setIcon(new ImageIcon("PIM_ver4\\picture\\view.png"));
		buttonView.setIcon(new ImageIcon(getClass().getClassLoader().getResource("view.png")));
		buttonView.setBackground(Color.decode("#d6e7ef"));
		buttonView.addActionListener(this);
		buttonView.setBorderPainted(false);
		buttonView.setBounds(360, 102, 25, 25);
		buttonView.setRoundAllCorner(5);
		panelContentInner.add(buttonView);
		buttonView.setVisible(false);
		
		buttonHide = new ButtonRound();
		//buttonHide.setIcon(new ImageIcon("PIM_ver4\\picture\\hide.png"));
		buttonHide.setIcon(new ImageIcon(getClass().getClassLoader().getResource("hide.png")));
		buttonHide.setBackground(Color.decode("#d6e7ef"));
		buttonHide.addActionListener(this);
		buttonHide.setBorderPainted(false);
		buttonHide.setBounds(360, 102, 25, 25);
		buttonHide.setRoundAllCorner(5);
		panelContentInner.add(buttonHide);
		
		buttonDangNhap = new ButtonRound();
		buttonDangNhap.setText("Login");
		buttonDangNhap.setForeground(Color.decode("#ffffff"));
		buttonDangNhap.setBackground(Color.decode("#1b3954"));
		buttonDangNhap.addActionListener(this);
		buttonDangNhap.setFont(new Font("Bevan", Font.BOLD, 16));
		buttonDangNhap.setBorderPainted(false);
		buttonDangNhap.setBounds(190, 170, 115, 30);
		buttonDangNhap.setRoundAllCorner(20);
		panelContentInner.add(buttonDangNhap);
		
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
		
		if (e.getSource() == buttonDangNhap) {
			DangNhap();
		}
		if (e.getSource() == buttonView) {
			textfieldMatKhau.setText(passwordfieldMatKhau.getText());
			passwordfieldMatKhau.setVisible(false);
			textfieldMatKhau.setVisible(true);
			buttonHide.setVisible(true);
			buttonView.setVisible(false);
		}
		if (e.getSource() == buttonHide) {
			passwordfieldMatKhau.setText(textfieldMatKhau.getText());
			textfieldMatKhau.setVisible(false);
			passwordfieldMatKhau.setVisible(true);
			buttonView.setVisible(true);
			buttonHide.setVisible(false);
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
		
		String query = "SELECT * FROM TAIKHOAN WHERE TENDANGNHAP = '" + textfieldTenDangNhap.getText() +"'";
		if (textfieldMatKhau.isVisible()) {
			if(textfieldMatKhau.getText().equals("")) {
				query += " AND MATKHAU IS NULL";
			}
			else {
				query += " AND MATKHAU = '" + textfieldMatKhau.getText() + "'";
			}
		}
		else if (passwordfieldMatKhau.isVisible()){
			if(passwordfieldMatKhau.getText().equals("")) {
				query += " AND MATKHAU IS NULL";
			}
			else {
				query += " AND MATKHAU = '" + passwordfieldMatKhau.getText() + "'";
			}
		}
		System.out.println(query);

		try {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/PIMpdb", "rootdata", "mypassword");
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			
			if (resultset.next())
			{
				if (textfieldTenDangNhap.getText().substring(0,2).equals("BN")) {
					new BNHome(this);
					frame.dispose();
				}
				else if (textfieldTenDangNhap.getText().substring(0,2).equals("BS")) {
					new BSHome(this);
					frame.dispose();
				}
				else if (textfieldTenDangNhap.getText().substring(0,2).equals("QL")) {
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
		return textfieldTenDangNhap.getText();
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
	

