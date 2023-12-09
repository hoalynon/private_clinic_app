package QuanLy;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import Chung.ButtonRound;
import Chung.TextFieldRound;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Benh_CapNhat implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;

	private ButtonRound buttonCapNhat;
	
	
	private TextFieldRound textfieldMaBenh;
	private TextFieldRound textfieldTenBenh;
	private TextFieldRound textfieldTenKhoa;
	private TextFieldRound textfieldGia;
	
	private JTable table;
	
	Benh_CapNhat(QLHome MyHome, JTable table){
		
		this.MyHome = MyHome;
		this.table = table;
		
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
		panel1.setPreferredSize(new Dimension (160,260));
		panel1.setLayout(null);
		panelContent.add(panel1,BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (80,80));
		panel2.setLayout(new FlowLayout(FlowLayout.TRAILING,50,20));
		panelContent.add(panel2, BorderLayout.SOUTH);

		JLabel labelHeading = new JLabel("CAP NHAT BENH");
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
		
		buttonCapNhat = new ButtonRound();
		buttonCapNhat.setText("  Cap Nhat  ");
		buttonCapNhat.setForeground(buttonfrontcolor);
		buttonCapNhat.setBackground(buttonbackcolor);
		buttonCapNhat.addActionListener(this);
		buttonCapNhat.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonCapNhat.setBounds(80, 30, 85, 21);
		buttonCapNhat.setBorderPainted(false);
		panel2.add(buttonCapNhat);
		
		if (table != null && table.getSelectedRowCount() == 1) {
			setInformation(table.getSelectedRow());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonCapNhat) {
			CapNhat();
		}
		
	}
	
	public void setInformation(int SelectedRow) {
        
	        int [] mapping = getMapping();
		
		textfieldMaBenh.setText((String)table.getValueAt(SelectedRow,mapping[0]));
		textfieldTenBenh.setText((String)table.getValueAt(SelectedRow,mapping[1]));
		textfieldTenKhoa.setText((String)table.getValueAt(SelectedRow,mapping[2]));
		textfieldGia.setText((String)table.getValueAt(SelectedRow,mapping[3]));

	    textfieldMaBenh.setBackground(new Color(210, 210, 210));
	    textfieldMaBenh.setEditable(false);
	}
	
	public void CapNhat() {
		
		if (textfieldGia.getText().equals("")) {
			textfieldGia.setText("0");
		}

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_benh_sua1benh (?,?,?,?,?) }");
			
			statement.setString(1,textfieldMaBenh.getText());
			statement.setString(2,textfieldTenBenh.getText());
			statement.setString(3,textfieldTenKhoa.getText());
			statement.setInt(4,Integer.parseInt(textfieldGia.getText()));
			statement.registerOutParameter(5,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(5);
			if (changedrows > 0) {
				JOptionPane.showMessageDialog(null, "Cap Nhat Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
			}
			
			statement.close();
			

			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		//Refresh();
	}
	
	public void Refresh() {
		textfieldMaBenh.setText("");
		textfieldTenBenh.setText("");
		textfieldTenKhoa.setText("");
		textfieldGia.setText("");
	}
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
                case "Ma Benh":
                    mapping[0] = i;
                    break;
                case "Ten Benh":
                    mapping[1] = i;
                    break;
                case "Ten Khoa":
                    mapping[2] = i;
                    break;
                case "Gia Dieu Tri":
                    mapping[3] = i;
                    break;
            }
        }
	return mapping;
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
