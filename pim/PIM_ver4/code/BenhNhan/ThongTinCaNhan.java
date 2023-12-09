package BenhNhan;



import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class ThongTinCaNhan {
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private BNHome MyHome;
	private JPanel panelContent;
	
	private TextFieldRound textfieldMaBN;
	private TextFieldRound textfieldHoTen;
	private TextFieldRound textfieldGioiTinh;
	private TextFieldRound textfieldNgaySinh;
	private TextFieldRound textfieldQueQuan;
	private TextFieldRound textfieldNoiOHienTai;
	
	ThongTinCaNhan(BNHome MyHome){
		
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
		panel1.setPreferredSize(new Dimension (100,340));
		panel1.setLayout(null);
		panelContent.add(panel1,BorderLayout.CENTER);
		
		JLabel labelHeading = new JLabel("THONG TIN BAN THAN");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
        JLabel labelMaBN = new JLabel("Ma BN : ");
		labelMaBN.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaBN.setBounds(30, 30, 115, 20);
		labelMaBN.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaBN);
		
		textfieldMaBN = new TextFieldRound();
		textfieldMaBN.setBounds(150, 30, 115, 20);
		textfieldMaBN.setColumns(10);
		panel1.add(textfieldMaBN);
		
		JLabel labelHoTen = new JLabel("Ho Ten : ");
		labelHoTen.setFont(new Font("Bevan", Font.BOLD, 12));
		labelHoTen.setBounds(300, 30, 115, 20);
		labelHoTen.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelHoTen);
		
		textfieldHoTen = new TextFieldRound();
		textfieldHoTen.setBounds(420, 30, 115, 20);
		textfieldHoTen.setColumns(10);
		panel1.add(textfieldHoTen);
		
		JLabel labelGioiTinh = new JLabel("Gioi Tinh : ");
		labelGioiTinh.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGioiTinh.setFont(new Font("Bevan", Font.BOLD, 12));
		labelGioiTinh.setBounds(30, 70, 115, 20);
		panel1.add(labelGioiTinh);

		textfieldGioiTinh = new TextFieldRound();
		textfieldGioiTinh.setBounds(150, 70, 115, 20);
		textfieldGioiTinh.setColumns(10);
		panel1.add(textfieldGioiTinh);
		
		JLabel labelNgaySinh = new JLabel("Ngay Sinh : ");
		labelNgaySinh.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgaySinh.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgaySinh.setBounds(300, 70, 115, 20);
		panel1.add(labelNgaySinh);
		
		textfieldNgaySinh = new TextFieldRound();
		textfieldNgaySinh.setBounds(420, 70, 115, 20);
		textfieldNgaySinh.setColumns(10);
		panel1.add(textfieldNgaySinh);
		
		JLabel labelQueQuan = new JLabel("Que Quan : ");
		labelQueQuan.setHorizontalAlignment(SwingConstants.RIGHT);
		labelQueQuan.setFont(new Font("Bevan", Font.BOLD, 12));
		labelQueQuan.setBounds(30, 110, 115, 20);
		panel1.add(labelQueQuan);
		
		textfieldQueQuan = new TextFieldRound();
		textfieldQueQuan.setColumns(10);
		textfieldQueQuan.setBounds(150, 110, 400, 20);
		panel1.add(textfieldQueQuan);
		
		JLabel labelNoiOHienTai = new JLabel("Noi O : ");
		labelNoiOHienTai.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNoiOHienTai.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNoiOHienTai.setBounds(30, 150, 115, 20);
		panel1.add(labelNoiOHienTai);
		
		textfieldNoiOHienTai = new TextFieldRound();
		textfieldNoiOHienTai.setColumns(10);
		textfieldNoiOHienTai.setBounds(150, 150, 400, 20);
		textfieldNoiOHienTai.setCaretPosition(0);
		panel1.add(textfieldNoiOHienTai);
		
		setInformation();
	}
	
public void setInformation() { 
		
	
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_benhnhan_laybenhnhan_theobenhnhan (?,?) }");
			statement.setString(1,MyHome.getID());
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			
			resultset.next();
			
			if (resultset.getString("MABN") != null) {
				textfieldMaBN.setText(resultset.getString("MABN"));
			} else textfieldMaBN.setText("");
			if (resultset.getString("HOTEN") != null) {
				textfieldHoTen.setText(resultset.getString("HOTEN"));
			} else textfieldHoTen.setText("");
			if (resultset.getString("GIOITINH") != null) {
				textfieldGioiTinh.setText(resultset.getString("GIOITINH"));
			} else textfieldGioiTinh.setText("");
			if (resultset.getDate("NGAYSINH") != null) {
				textfieldNgaySinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(resultset.getDate("NGAYSINH")));
			} else textfieldNgaySinh.setText("");
			if (resultset.getString("QUEQUAN") != null) {
				textfieldQueQuan.setText(resultset.getString("QUEQUAN"));
			} else textfieldQueQuan.setText("");
			if (resultset.getString("NOIOHIENTAI") != null) {
				textfieldNoiOHienTai.setText(resultset.getString("NOIOHIENTAI"));
			} else textfieldNoiOHienTai.setText("");
			
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
			textfieldMaBN.setEditable(false);
			textfieldHoTen.setEditable(false);
			textfieldGioiTinh.setEditable(false);
			textfieldNgaySinh.setEditable(false);
			textfieldQueQuan.setEditable(false);
			textfieldNoiOHienTai.setEditable(false);
		
	}
	
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
