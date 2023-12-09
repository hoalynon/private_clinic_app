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

public class TaiKhoan_CapNhat implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;

	private ButtonRound buttonCapNhat;
	
	private TextFieldRound textfieldTenDangNhap;
	private TextFieldRound textfieldMatKhau;
	
	private JTable table;
	
	TaiKhoan_CapNhat(QLHome MyHome, JTable table){
		
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

		JLabel labelHeading = new JLabel("CAP NHAT TAI KHOAN");
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
	        
		textfieldTenDangNhap.setText((String)table.getValueAt(SelectedRow,mapping[0]));
		textfieldMatKhau.setText((String)table.getValueAt(SelectedRow,mapping[1]));
		
	    textfieldTenDangNhap.setBackground(new Color(210, 210, 210));
	    textfieldTenDangNhap.setEditable(false);
	}
	
	public void CapNhat() {
			
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_taikhoan_sua1taikhoan (?,?,?) }");
			
			statement.setString(1,textfieldTenDangNhap.getText());
			statement.setString(2,textfieldMatKhau.getText());
			statement.registerOutParameter(3,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(3);
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
		textfieldTenDangNhap.setText("");
		textfieldMatKhau.setText("");
	}
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
                case "Ten Dang Nhap":
                    mapping[0] = i;
                    break;
                case "Mat Khau":
                    mapping[1] = i;
                    break;
            }
        }
	return mapping;
	}

	public JPanel getpanelContent() {
		return panelContent;
	}
}
