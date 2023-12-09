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

public class BenhNhan_CapNhat implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;

	private ButtonRound buttonCapNhat;
	
	private JRadioButton rbuttonNam;
	private JRadioButton rbuttonNu;
	private ButtonGroup bgroupGioiTinh;
	
	private JRadioButton rbuttonCo;
	private JRadioButton rbuttonKhong;
	private ButtonGroup bgroupDatLich;
	
	private TextFieldRound textfieldMaBN;
	private TextFieldRound textfieldHoTen;
	private TextFieldRound textfieldQueQuan;
	private TextFieldRound textfieldNoiOHienTai;
	
	private JDateChooser NgaySinh;
	
	private JTable table;
	
	BenhNhan_CapNhat(QLHome MyHome, JTable table){
		
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
		
		JLabel labelHeading = new JLabel("CAP NHAT BENH NHAN");
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

		rbuttonNam = new JRadioButton("Nam");
		rbuttonNam.setBackground(textbackcolor);
		rbuttonNam.setBounds(150, 70, 50, 20);
		rbuttonNam.setActionCommand("Nam");
		panel1.add(rbuttonNam);
		
		rbuttonNu = new JRadioButton("Nu");
		rbuttonNu.setBackground(textbackcolor);
		rbuttonNu.setBounds(200, 70, 50, 20);
		rbuttonNu.setActionCommand("Nu");
		panel1.add(rbuttonNu);
		
		bgroupGioiTinh = new ButtonGroup();
		bgroupGioiTinh.add(rbuttonNam);
		bgroupGioiTinh.add(rbuttonNu);
		
		JLabel labelNgaySinh = new JLabel("Ngay Sinh : ");
		labelNgaySinh.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgaySinh.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgaySinh.setBounds(300, 70, 115, 20);
		panel1.add(labelNgaySinh);
		
		NgaySinh = new JDateChooser();
		NgaySinh.setBounds(420, 70, 115, 20);
		NgaySinh.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgaySinh.setDateFormatString("dd/MM/yyyy");
		panel1.add(NgaySinh);
		
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
		panel1.add(textfieldNoiOHienTai);
		
		JLabel labelDatLich = new JLabel("Kha Nang Dat Lich : ");
		labelDatLich.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDatLich.setFont(new Font("Bevan", Font.BOLD, 12));
		labelDatLich.setBounds(30, 190, 115, 20);
		panel1.add(labelDatLich);

		rbuttonCo = new JRadioButton("Co");
		rbuttonCo.setBackground(textbackcolor);
		rbuttonCo.setBounds(150, 190, 50, 20);
		rbuttonCo.setActionCommand("Co");
		panel1.add(rbuttonCo);
		
		rbuttonKhong = new JRadioButton("Khong");
		rbuttonKhong.setBackground(textbackcolor);
		rbuttonKhong.setBounds(200, 190, 70, 20);
		rbuttonKhong.setActionCommand("Khong");
		panel1.add(rbuttonKhong);
		
		bgroupDatLich = new ButtonGroup();
		bgroupDatLich.add(rbuttonCo);
		bgroupDatLich.add(rbuttonKhong);
		
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
	        
		textfieldMaBN.setText((String)table.getValueAt(SelectedRow,mapping[0]));
		textfieldHoTen.setText((String)table.getValueAt(SelectedRow,mapping[1]));
		textfieldQueQuan.setText((String)table.getValueAt(SelectedRow,mapping[4]));
		textfieldNoiOHienTai.setText((String)table.getValueAt(SelectedRow,mapping[5]));
	
		if ((String)table.getValueAt(SelectedRow,mapping[2]) != null) {
			switch ((String)table.getValueAt(SelectedRow,mapping[2])) {
			case "Nam":
				rbuttonNam.setSelected(true);
				break;
			case "Nu":
				rbuttonNu.setSelected(true);
				break;
			}
		}
		else {
			bgroupGioiTinh.clearSelection();
		}
		
		if ((String)table.getValueAt(SelectedRow,mapping[6]) != null) {
			switch ((String)table.getValueAt(SelectedRow,mapping[6])) {
			case "Co":
				rbuttonCo.setSelected(true);
				break;
			case "Khong":
				rbuttonKhong.setSelected(true);
				break;
			}
		}
		else {
			bgroupDatLich.clearSelection();
		}
		
		if (table.getValueAt(SelectedRow, mapping[3]) != null) {
			try {
				NgaySinh.setDate(new SimpleDateFormat("dd/MM/yyyy").parse((String)table.getValueAt(SelectedRow, mapping[3])));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	        textfieldMaBN.setBackground(new Color(210, 210, 210));
	        textfieldMaBN.setEditable(false);
	}
	
	public void CapNhat() {
		
			String stringNgaySinh = "";
			if (NgaySinh.getDate() != null) {
				Date date = NgaySinh.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				stringNgaySinh = sdf.format(date);
			}
			
			String stringGioiTinh = ""; 
			if (bgroupGioiTinh.getSelection() != null) {
				stringGioiTinh = bgroupGioiTinh.getSelection().getActionCommand();
			}
			
			int intDatLich = 1; 
			if (bgroupDatLich.getSelection() != null) {
				if (bgroupDatLich.getSelection().getActionCommand() == "Khong")
					intDatLich = 2;
			}
				

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_benhnhan_sua1benhnhan (?,?,?,?,?,?,?,?) }");
			
			statement.setString(1,textfieldMaBN.getText());
			statement.setString(2,textfieldHoTen.getText());
			statement.setString(3,stringGioiTinh);
			statement.setString(4,stringNgaySinh);
			statement.setString(5,textfieldQueQuan.getText());
			statement.setString(6,textfieldNoiOHienTai.getText());
			statement.setInt(7,intDatLich);
			statement.registerOutParameter(8,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(8);
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
		textfieldMaBN.setText("");
		textfieldHoTen.setText("");
		textfieldQueQuan.setText("");
		textfieldNoiOHienTai.setText("");
		bgroupGioiTinh.clearSelection();
		NgaySinh.setDate(null);
	}
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
                case "Ma BN":
                    mapping[0] = i;
                    break;
                case "Ho Ten":
                    mapping[1] = i;
                    break;
                case "Gioi Tinh":
                    mapping[2] = i;
                    break;
                case "Ngay Sinh":
                    mapping[3] = i;
                    break;
                case "Que Quan":
                    mapping[4] = i;
                    break;
                case "Noi O Hien Tai":
                    mapping[5] = i;
                    break;
                case "Dat Lich":
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
