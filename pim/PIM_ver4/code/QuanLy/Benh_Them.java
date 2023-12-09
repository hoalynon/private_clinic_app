package QuanLy;



import java.awt.*;
import javax.swing.*;

import Chung.ButtonRound;
import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class Benh_Them implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonThem;
	
	private TextFieldRound textfieldMaBenh;
	private TextFieldRound textfieldTenBenh;
	private TextFieldRound textfieldTenKhoa;
	private TextFieldRound textfieldGia;

	private int maxRow;
	
	Benh_Them(QLHome MyHome){
		
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
		
		JLabel labelHeading = new JLabel("THEM MOI BENH");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
		JLabel labelMaBenh = new JLabel("Ma Benh : ");
		labelMaBenh.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaBenh.setBounds(30, 30, 115, 20);
		labelMaBenh.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaBenh);
		
		textfieldMaBenh = new TextFieldRound();
		textfieldMaBenh.setBounds(150, 30, 115, 20);
		textfieldMaBenh.setColumns(10);
		panel1.add(textfieldMaBenh);
		maxRow = getMaxRow();
		textfieldMaBenh.setText("BE" + MyHome.fillZero(maxRow, 3) + Integer.toString(maxRow));
        textfieldMaBenh.setBackground(textbackcolor);
        textfieldMaBenh.setEditable(false);
		
		JLabel labelTenBenh = new JLabel("Ten Benh : ");
		labelTenBenh.setFont(new Font("Bevan", Font.BOLD, 12));
		labelTenBenh.setBounds(30, 70, 115, 20);
		labelTenBenh.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelTenBenh);
		
		textfieldTenBenh = new TextFieldRound();
		textfieldTenBenh.setBounds(150, 70, 115, 20);
		textfieldTenBenh.setColumns(10);
		panel1.add(textfieldTenBenh);

		JLabel labelTenKhoa = new JLabel("Ten Khoa : ");
		labelTenKhoa.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTenKhoa.setFont(new Font("Bevan", Font.BOLD, 12));
		labelTenKhoa.setBounds(30, 110, 115, 20);
		panel1.add(labelTenKhoa);
		
		textfieldTenKhoa = new TextFieldRound();
		textfieldTenKhoa.setColumns(10);
		textfieldTenKhoa.setBounds(150, 110, 115, 20);
		panel1.add(textfieldTenKhoa);
		
		JLabel labelGia = new JLabel("Gia Dieu Tri : ");
		labelGia.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGia.setFont(new Font("Bevan", Font.BOLD, 12));
		labelGia.setBounds(30, 150, 115, 20);
		panel1.add(labelGia);
		
		textfieldGia = new TextFieldRound();
		textfieldGia.setColumns(10);
		textfieldGia.setBounds(150, 150, 115, 20);
		panel1.add(textfieldGia);
		
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
		
		if (textfieldGia.getText().equals("")) {
			textfieldGia.setText("0");
		}

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_benh_them1benh (?,?,?,?,?) }");
			statement.setString(1,textfieldMaBenh.getText());
			statement.setString(2,textfieldTenBenh.getText());
			statement.setString(3,textfieldTenKhoa.getText());
			statement.setInt(4,Integer.parseInt(textfieldGia.getText()));
			statement.registerOutParameter(5,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(5);
			if (changedrows > 0) {
				JOptionPane.showMessageDialog(null, "Them Moi Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
				textfieldMaBenh.setText("BE" + MyHome.fillZero(maxRow, 3) + Integer.toString(++maxRow));
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
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_benh_laybenh (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			while(resultset.next()) {
	                        compareNum = Integer.parseInt(resultset.getString("MABENH").substring(2));
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
		textfieldMaBenh.setText("");
		textfieldTenBenh.setText("");
		textfieldTenKhoa.setText("");
		textfieldGia.setText("");
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
