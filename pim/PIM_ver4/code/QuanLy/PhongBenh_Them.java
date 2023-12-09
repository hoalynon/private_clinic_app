package QuanLy;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import Chung.ButtonRound;
import Chung.TextFieldRound;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhongBenh_Them implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonThem;
	
	private JRadioButton rbuttonThuong;
	private JRadioButton rbuttonVIP;
	private JRadioButton rbuttonCachLy;
	private ButtonGroup bgroupLoai;
	
	private TextFieldRound textfieldMaPhong;
	private TextFieldRound textfieldToa;
	private TextFieldRound textfieldLau;
	private TextFieldRound textfieldSucChua;
	private TextFieldRound textfieldGia1Ngay;
	

	PhongBenh_Them(QLHome MyHome){
		
		this.MyHome = MyHome;
		
		textfrontcolor = MyHome.getTextFrontColor();
		textbackcolor = MyHome.getTextBackColor();
		buttonfrontcolor = MyHome.getButtonFrontColor();
		buttonbackcolor = MyHome.getButtonBackColor();
		
		panelContent = new JPanel();
		panelContent.setBackground(textbackcolor);
		panelContent.setLayout(new BorderLayout());
		
		JPanel panel0 = new JPanel();
		panel0.setBackground(textbackcolor);
		panel0.setPreferredSize(new Dimension (160,60));
		panel0.setLayout(new BorderLayout());
		panelContent.add(panel0,BorderLayout.NORTH);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(textbackcolor);
		panel1.setPreferredSize(new Dimension (100,260));
		panel1.setLayout(null);
		panelContent.add(panel1,BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (80,80));
		panel2.setLayout(new FlowLayout(FlowLayout.TRAILING,50,20));
		panelContent.add(panel2, BorderLayout.SOUTH);
		
		JLabel labelHeading = new JLabel("THEM MOI PHONG BENH");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
		JLabel labelMaPhong = new JLabel("Ma Phong : ");
		labelMaPhong.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaPhong.setBounds(30, 30, 115, 20);
		labelMaPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaPhong);
		
		textfieldMaPhong = new TextFieldRound();
		textfieldMaPhong.setBounds(150, 30, 115, 20);
		textfieldMaPhong.setColumns(10);
		panel1.add(textfieldMaPhong);
		
		JLabel labelToa = new JLabel("Toa : ");
		labelToa.setFont(new Font("Bevan", Font.BOLD, 12));
		labelToa.setBounds(30, 70, 115, 20);
		labelToa.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelToa);
		
		textfieldToa = new TextFieldRound();
		textfieldToa.setBounds(150, 70, 115, 20);
		textfieldToa.setColumns(10);
		panel1.add(textfieldToa);
		
		JLabel labelLau = new JLabel("Lau : ");
		labelLau.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLau.setFont(new Font("Bevan", Font.BOLD, 12));
		labelLau.setBounds(30, 110, 115, 20);
		panel1.add(labelLau);
		
		textfieldLau = new TextFieldRound();
		textfieldLau.setColumns(10);
		textfieldLau.setBounds(150, 110, 115, 20);
		panel1.add(textfieldLau);
		
		JLabel labelSucChua = new JLabel("Suc Chua : ");
		labelSucChua.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSucChua.setFont(new Font("Bevan", Font.BOLD, 12));
		labelSucChua.setBounds(30, 150, 115, 20);
		panel1.add(labelSucChua);
		
		textfieldSucChua = new TextFieldRound();
		textfieldSucChua.setColumns(10);
		textfieldSucChua.setBounds(150, 150, 115, 20);
		panel1.add(textfieldSucChua);
		
		JLabel labelGia1Ngay = new JLabel("Gia Thue 1 Ngay : ");
		labelGia1Ngay.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGia1Ngay.setFont(new Font("Bevan", Font.BOLD, 12));
		labelGia1Ngay.setBounds(30, 190, 115, 20);
		panel1.add(labelGia1Ngay);
		
		textfieldGia1Ngay = new TextFieldRound();
		textfieldGia1Ngay.setColumns(10);
		textfieldGia1Ngay.setBounds(150, 190, 115, 20);
		panel1.add(textfieldGia1Ngay);
		
		JLabel labelLoai = new JLabel("Loai : ");
		labelLoai.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLoai.setFont(new Font("Bevan", Font.BOLD, 12));
		labelLoai.setBounds(300, 30, 115, 20);
		panel1.add(labelLoai);

		rbuttonThuong = new JRadioButton("Thuong");
		rbuttonThuong.setBackground(textbackcolor);
		rbuttonThuong.setBounds(420, 30, 70, 20);
		rbuttonThuong.setActionCommand("Thuong");
		panel1.add(rbuttonThuong);
		
		rbuttonVIP = new JRadioButton("VIP");
		rbuttonVIP.setBackground(textbackcolor);
		rbuttonVIP.setBounds(420, 70, 70, 20);
		rbuttonVIP.setActionCommand("VIP");
		panel1.add(rbuttonVIP);
		
		rbuttonCachLy = new JRadioButton("Cach ly");
		rbuttonCachLy.setBackground(textbackcolor);
		rbuttonCachLy.setBounds(420, 110, 70, 20);
		rbuttonCachLy.setActionCommand("Cach ly");
		panel1.add(rbuttonCachLy);
		
		bgroupLoai = new ButtonGroup();
		bgroupLoai.add(rbuttonThuong);
		bgroupLoai.add(rbuttonVIP);
		bgroupLoai.add(rbuttonCachLy);
		
		buttonThem = new ButtonRound();
		buttonThem.setText("    Them    ");
		buttonThem.setForeground(buttonfrontcolor);
		buttonThem.setBackground(buttonbackcolor);
		buttonThem.addActionListener(this);
		buttonThem.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonThem.setBounds(80, 30, 85, 21);
		buttonThem.setBorderPainted(false);
		panel2.add(buttonThem);
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == buttonThem) {
			Them();
		}
	}
	
	public void Them() {
		
		if (textfieldToa.getText().equals("")) {
			textfieldToa.setText("0");
		}
		if (textfieldLau.getText().equals("")) {
			textfieldLau.setText("0");
		}
		if (textfieldSucChua.getText().equals("")) {
			textfieldSucChua.setText("0");
		}
		if (textfieldGia1Ngay.getText().equals("")) {
			textfieldGia1Ngay.setText("0");
		}
		String stringLoai = ""; 
		if (bgroupLoai.getSelection() != null) {
			stringLoai = bgroupLoai.getSelection().getActionCommand();
		}

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_phongbenh_them1phongbenh (?,?,?,?,?,?,?) }");
			statement.setString(1,textfieldMaPhong.getText());
			statement.setString(2,stringLoai);
			statement.setInt(3,Integer.parseInt(textfieldToa.getText()));
			statement.setInt(4,Integer.parseInt(textfieldLau.getText()));
			statement.setInt(5,Integer.parseInt(textfieldSucChua.getText()));
			statement.setInt(6,Integer.parseInt(textfieldGia1Ngay.getText()));
			statement.registerOutParameter(7,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(7);
			if (changedrows > 0) {
				JOptionPane.showMessageDialog(null, "Them Moi Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
			}
			
			statement.close();
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		//Refresh();
	}
	
	public void Refresh() {
		textfieldMaPhong.setText("");
		textfieldToa.setText("");
		textfieldLau.setText("");
		textfieldSucChua.setText("");
		textfieldGia1Ngay.setText("");
		bgroupLoai.clearSelection();
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
