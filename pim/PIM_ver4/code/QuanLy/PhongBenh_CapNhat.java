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

public class PhongBenh_CapNhat implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;

	private ButtonRound buttonCapNhat;
	
	private JRadioButton rbuttonThuong;
	private JRadioButton rbuttonVIP;
	private JRadioButton rbuttonCachLy;
	private ButtonGroup bgroupLoai;
	
	private TextFieldRound textfieldMaPhong;
	private TextFieldRound textfieldToa;
	private TextFieldRound textfieldLau;
	private TextFieldRound textfieldSucChua;
	private TextFieldRound textfieldGia1Ngay;
	
	private JTable table;
	
	PhongBenh_CapNhat(QLHome MyHome, JTable table){
		
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

		JLabel labelHeading = new JLabel("CAP NHAT PHONG BENH");
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
		
		JLabel labelLoai = new JLabel("Loai : ");
		labelLoai.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLoai.setFont(new Font("Bevan", Font.BOLD, 12));
		labelLoai.setBounds(300, 30, 115, 20);
		panel1.add(labelLoai);
		
		JLabel labelGia1Ngay = new JLabel("Gia Thue 1 Ngay : ");
		labelGia1Ngay.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGia1Ngay.setFont(new Font("Bevan", Font.BOLD, 12));
		labelGia1Ngay.setBounds(30, 190, 115, 20);
		panel1.add(labelGia1Ngay);
		
		textfieldGia1Ngay = new TextFieldRound();
		textfieldGia1Ngay.setColumns(10);
		textfieldGia1Ngay.setBounds(150, 190, 115, 20);
		panel1.add(textfieldGia1Ngay);

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
		
		rbuttonCachLy = new JRadioButton("Cach Ly");
		rbuttonCachLy.setBackground(textbackcolor);
		rbuttonCachLy.setBounds(420, 110, 70, 20);
		rbuttonCachLy.setActionCommand("Cach ly");
		panel1.add(rbuttonCachLy);
		
		bgroupLoai = new ButtonGroup();
		bgroupLoai.add(rbuttonThuong);
		bgroupLoai.add(rbuttonVIP);
		bgroupLoai.add(rbuttonCachLy);
		
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
	        
		textfieldMaPhong.setText((String)table.getValueAt(SelectedRow,mapping[0]));
		textfieldToa.setText((String)table.getValueAt(SelectedRow,mapping[2]));
		textfieldLau.setText((String)table.getValueAt(SelectedRow,mapping[3]));
		textfieldSucChua.setText((String)table.getValueAt(SelectedRow,mapping[4]));
		textfieldGia1Ngay.setText((String)table.getValueAt(SelectedRow,mapping[6]));
	
		if ((String)table.getValueAt(SelectedRow,mapping[1]) != null) {
			switch ((String)table.getValueAt(SelectedRow,mapping[1])) {
			case "Thuong":
				rbuttonThuong.setSelected(true);
				break;
			case "VIP":
				rbuttonVIP.setSelected(true);
				break;
			case "Cach ly":
				rbuttonCachLy.setSelected(true);
			}
		}
		else {
			bgroupLoai.clearSelection();
		}
		
	        textfieldMaPhong.setBackground(new Color(210, 210, 210));
	        textfieldMaPhong.setEditable(false);
	}
	
	
	public void CapNhat() {
		
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
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_phongbenh_sua1phongbenh (?,?,?,?,?,?,?) }");
			
			statement.setString(1,textfieldMaPhong.getText());
			statement.setInt(2,Integer.parseInt(textfieldToa.getText()));
			statement.setInt(3,Integer.parseInt(textfieldLau.getText()));
			statement.setInt(4,Integer.parseInt(textfieldSucChua.getText()));
			statement.setString(5,stringLoai);
			statement.setInt(6,Integer.parseInt(textfieldGia1Ngay.getText()));
			statement.registerOutParameter(7,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(7);
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
		textfieldMaPhong.setText("");
		textfieldToa.setText("");
		textfieldLau.setText("");
		textfieldSucChua.setText("");
		textfieldGia1Ngay.setText("");
		bgroupLoai.clearSelection();
	}
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
                case "Ma Phong":
                    mapping[0] = i;
                    break;
                case "Loai":
                    mapping[1] = i;
                    break;
                case "Toa":
                    mapping[2] = i;
                    break;
                case "Lau":
                    mapping[3] = i;
                    break;
                case "Suc Chua":
                    mapping[4] = i;
                    break;
                case "Con Trong":
                    mapping[5] = i;
                    break;
                case "Gia/Ngay":
                    mapping[6] = i;
                    break;
            }
        }
	return mapping;
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
