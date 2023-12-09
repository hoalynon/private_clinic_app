package QuanLy;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import Chung.ButtonRound;
import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThietBiYTe_Them implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonThem;
	
	private JRadioButton rbutton1Lan;
	private JRadioButton rbuttonTaiSuDung;
	private ButtonGroup bgroupLoaiSD;
	
	private TextFieldRound textfieldMaThietBi;
	private TextFieldRound textfieldTenThietBi;
	private TextFieldRound textfieldCongDung;
	private TextFieldRound textfieldSoLuongTong;
	private TextFieldRound textfieldGia;
	
	private int maxRow;

	ThietBiYTe_Them(QLHome MyHome){
		
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
		
		JLabel labelHeading = new JLabel("THEM MOI THIET BI Y TE");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
		JLabel labelMaThietBi = new JLabel("Ma Thiet Bi : ");
		labelMaThietBi.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaThietBi.setBounds(30, 30, 115, 20);
		labelMaThietBi.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaThietBi);
		
		textfieldMaThietBi = new TextFieldRound();
		textfieldMaThietBi.setBounds(150, 30, 115, 20);
		textfieldMaThietBi.setColumns(10);
		panel1.add(textfieldMaThietBi);
		maxRow = getMaxRow();
		textfieldMaThietBi.setText("TB" + MyHome.fillZero(maxRow, 3) + Integer.toString(maxRow));
        textfieldMaThietBi.setBackground(new Color (210, 210, 210));
        textfieldMaThietBi.setEditable(false);
		
		JLabel labelTenThietBi = new JLabel("Ten Thiet Bi : ");
		labelTenThietBi.setFont(new Font("Bevan", Font.BOLD, 12));
		labelTenThietBi.setBounds(30, 70, 115, 20);
		labelTenThietBi.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelTenThietBi);
		
		textfieldTenThietBi = new TextFieldRound();
		textfieldTenThietBi.setBounds(150, 70, 115, 20);
		textfieldTenThietBi.setColumns(10);
		panel1.add(textfieldTenThietBi);
		
		JLabel labelCongDung = new JLabel("Cong Dung : ");
		labelCongDung.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCongDung.setFont(new Font("Bevan", Font.BOLD, 12));
		labelCongDung.setBounds(30, 110, 115, 20);
		panel1.add(labelCongDung);
		
		textfieldCongDung = new TextFieldRound();
		textfieldCongDung.setColumns(10);
		textfieldCongDung.setBounds(150, 110, 250, 20);
		panel1.add(textfieldCongDung);
		
		JLabel labelSoLuongTong = new JLabel("So Luong : ");
		labelSoLuongTong.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSoLuongTong.setFont(new Font("Bevan", Font.BOLD, 12));
		labelSoLuongTong.setBounds(30, 150, 115, 20);
		panel1.add(labelSoLuongTong);
		
		textfieldSoLuongTong = new TextFieldRound();
		textfieldSoLuongTong.setColumns(10);
		textfieldSoLuongTong.setBounds(150, 150, 115, 20);
		panel1.add(textfieldSoLuongTong);
		
		JLabel labelGia = new JLabel("Gia 1 Lan Dung : ");
		labelGia.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGia.setFont(new Font("Bevan", Font.BOLD, 12));
		labelGia.setBounds(30, 190, 115, 20);
		panel1.add(labelGia);
		
		textfieldGia = new TextFieldRound();
		textfieldGia.setColumns(10);
		textfieldGia.setBounds(150, 190, 115, 20);
		panel1.add(textfieldGia);
		
		JLabel labelLoaiSD = new JLabel("Loai SD : ");
		labelLoaiSD.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLoaiSD.setFont(new Font("Bevan", Font.BOLD, 12));
		labelLoaiSD.setBounds(300, 30, 115, 20);
		panel1.add(labelLoaiSD);

		rbutton1Lan = new JRadioButton("1 Lan");
		rbutton1Lan.setBackground(textbackcolor);
		rbutton1Lan.setBounds(420, 30, 70, 20);
		rbutton1Lan.setActionCommand("1 lan");
		rbutton1Lan.setSelected(true);
		panel1.add(rbutton1Lan);
		
		rbuttonTaiSuDung = new JRadioButton("Tai Su Dung");
		rbuttonTaiSuDung.setBackground(textbackcolor);
		rbuttonTaiSuDung.setBounds(420, 70, 100, 20);
		rbuttonTaiSuDung.setActionCommand("Tai su dung");
		panel1.add(rbuttonTaiSuDung);
		
		bgroupLoaiSD = new ButtonGroup();
		bgroupLoaiSD.add(rbutton1Lan);
		bgroupLoaiSD.add(rbuttonTaiSuDung);
		
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
		
		if (textfieldSoLuongTong.getText().equals("")) {
			textfieldSoLuongTong.setText("0");
		}
		
		if (textfieldGia.getText().equals("")) {
			textfieldGia.setText("0");
		}
		
		String stringLoaiSD = ""; 
		if (bgroupLoaiSD.getSelection() != null) {
			stringLoaiSD = bgroupLoaiSD.getSelection().getActionCommand();
		}			

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thietbiyte_them1thietbiyte (?,?,?,?,?,?,?) }");
			statement.setString(1,textfieldMaThietBi.getText());
			statement.setString(2,textfieldTenThietBi.getText());
			statement.setString(3,stringLoaiSD);
			statement.setString(4,textfieldCongDung.getText());
			statement.setInt(5,Integer.parseInt(textfieldSoLuongTong.getText()));
			statement.setInt(6,Integer.parseInt(textfieldGia.getText()));
			statement.registerOutParameter(7,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(7);
			if (changedrows > 0) {
				JOptionPane.showMessageDialog(null, "Them Moi Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
				textfieldMaThietBi.setText("TB" + MyHome.fillZero(maxRow, 3) + Integer.toString(++maxRow));
			}
			
			statement.close();
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		//Refresh();
	}
	
	public int getMaxRow(){
        int maxRow = -1, compareNum;

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thietbiyte_laythietbiyte (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			while(resultset.next()) {
	                        compareNum = Integer.parseInt(resultset.getString("MATHIETBI").substring(2));
	                        if(compareNum > maxRow){
	                            maxRow = compareNum;
	                        }
			} 
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
        return (maxRow + 1);
    }
	
	public void Refresh() {
		textfieldMaThietBi.setText("");
		textfieldTenThietBi.setText("");
		textfieldCongDung.setText("");
		textfieldSoLuongTong.setText("");
		textfieldGia.setText("");
		bgroupLoaiSD.clearSelection();
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
