package QuanLy;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Chung.ButtonRound;
import Chung.TextFieldRound;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ThietBiYTe_CapNhat implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;

	private ButtonRound buttonCapNhat;
	
	private JRadioButton rbutton1Lan;
	private JRadioButton rbuttonTaiSuDung;
	private ButtonGroup bgroupLoaiSD;
	
	private TextFieldRound textfieldMaThietBi;
	private TextFieldRound textfieldTenThietBi;
	private TextFieldRound textfieldCongDung;
	private TextFieldRound textfieldSoLuongTong;
	private TextFieldRound textfieldGia;
	
	private JTable table;
	
	ThietBiYTe_CapNhat(QLHome MyHome, JTable table){
		
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

		JLabel labelHeading = new JLabel("CAP NHAT THIET BI Y TE");
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
		
		/*JLabel labelLoaiSD = new JLabel("Loai SD : ");
		labelLoaiSD.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLoaiSD.setFont(new Font("Bevan", Font.BOLD, 12));
		labelLoaiSD.setBounds(300, 30, 115, 20);
		panel1.add(labelLoaiSD);

		rbutton1Lan = new JRadioButton("1 Lan");
		rbutton1Lan.setBackground(Color.decode("#d6e7ef"));
		rbutton1Lan.setBounds(420, 30, 70, 20);
		rbutton1Lan.setActionCommand("1 lan");
		rbutton1Lan.setSelected(true);
		panel1.add(rbutton1Lan);
		
		rbuttonTaiSuDung = new JRadioButton("Tai Su Dung");
		rbuttonTaiSuDung.setBackground(Color.decode("#d6e7ef"));
		rbuttonTaiSuDung.setBounds(420, 70, 100, 20);
		rbuttonTaiSuDung.setActionCommand("Tai su dung");
		panel1.add(rbuttonTaiSuDung);
		
		bgroupLoaiSD = new ButtonGroup();
		bgroupLoaiSD.add(rbutton1Lan);
		bgroupLoaiSD.add(rbuttonTaiSuDung);*/
		
		buttonCapNhat = new ButtonRound();
		buttonCapNhat.setText("    Cap Nhat    ");
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
	        
		textfieldMaThietBi.setText((String)table.getValueAt(SelectedRow,mapping[0]));
		textfieldTenThietBi.setText((String)table.getValueAt(SelectedRow,mapping[1]));
		textfieldCongDung.setText((String)table.getValueAt(SelectedRow,mapping[3]));
		textfieldSoLuongTong.setText((String)table.getValueAt(SelectedRow,mapping[4]));
		textfieldGia.setText((String)table.getValueAt(SelectedRow,mapping[5]));
	
		/*if ((String)table.getValueAt(SelectedRow,mapping[2]) != null) {
			switch ((String)table.getValueAt(SelectedRow,mapping[2])) {
			case "1 lan":
				rbutton1Lan.setSelected(true);
				break;
			case "Tai su dung":
				rbuttonTaiSuDung.setSelected(true);
				break;
			}
		}
		else {
			bgroupLoaiSD.clearSelection();
		*/
	        textfieldMaThietBi.setBackground(new Color(210, 210, 210));
	        textfieldMaThietBi.setEditable(false);
	}
	
	
	public void CapNhat() {
		
			if (textfieldSoLuongTong.getText().equals("")) {
				textfieldSoLuongTong.setText("0");
			}
			if (textfieldGia.getText().equals("")) {
				textfieldGia.setText("0");
			}
			
			String stringLoaiSD = "";
			/*if (bgroupLoaiSD.getSelection() != null) {
				stringLoaiSD = bgroupLoaiSD.getSelection().getActionCommand();
			}*/

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thietbiyte_sua1thietbiyte (?,?,?,?,?,?,?) }");
			
			statement.setString(1,textfieldMaThietBi.getText());
			statement.setString(2,textfieldTenThietBi.getText());
			statement.setString(3,stringLoaiSD);
			statement.setString(4,textfieldCongDung.getText());
			statement.setString(5,textfieldSoLuongTong.getText());
			statement.setString(6,textfieldGia.getText());
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
		textfieldMaThietBi.setText("");
		textfieldTenThietBi.setText("");
		textfieldCongDung.setText("");
		textfieldSoLuongTong.setText("");
		textfieldGia.setText("");
		bgroupLoaiSD.clearSelection();
	}
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
                case "Ma Thiet Bi":
                    mapping[0] = i;
                    break;
                case "Ten Thiet Bi":
                    mapping[1] = i;
                    break;
                case "Loai SD":
                    mapping[2] = i;
                    break;
                case "Cong Dung":
                    mapping[3] = i;
                    break;
                case "So Luong Tong":
                    mapping[4] = i;
                    break;
                case "Gia 1 Lan":
                    mapping[5] = i;
                    break;
            }
        }
	return mapping;
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
