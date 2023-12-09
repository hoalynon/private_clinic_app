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

public class TaiKhoan_Them implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonThem;
	
	private TextFieldRound textfieldTenDangNhap;
	private TextFieldRound textfieldMatKhau;


	TaiKhoan_Them(QLHome MyHome){
		
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
		
		JLabel labelHeading = new JLabel("THEM MOI TAI KHOAN");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
		JLabel labelTenDangNhap = new JLabel("Ten Dang Nhap : ");
		labelTenDangNhap.setFont(new Font("Bevan", Font.BOLD, 12));
		labelTenDangNhap.setBounds(30, 30, 115, 20);
		labelTenDangNhap.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelTenDangNhap);
		
		textfieldTenDangNhap = new TextFieldRound();
		textfieldTenDangNhap.setBounds(150, 30, 115, 20);
		textfieldTenDangNhap.setColumns(10);
		panel1.add(textfieldTenDangNhap);
		
		JLabel labelMatKhau = new JLabel("Mat Khau : ");
		labelMatKhau.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMatKhau.setBounds(30, 70, 115, 20);
		labelMatKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMatKhau);
		
		textfieldMatKhau = new TextFieldRound();
		textfieldMatKhau.setBounds(150, 70, 115, 20);
		textfieldMatKhau.setColumns(10);
		panel1.add(textfieldMatKhau);
		
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

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_taikhoan_them1taikhoan (?,?,?) }");
			statement.setString(1,textfieldTenDangNhap.getText());
			statement.setString(2,textfieldMatKhau.getText());
			statement.registerOutParameter(3,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(3);
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
		textfieldTenDangNhap.setText("");
		textfieldMatKhau.setText("");
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
