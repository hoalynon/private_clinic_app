package QuanLy;



import java.awt.*;
import javax.swing.*;

import Chung.ButtonRound;
import Chung.TextFieldRound;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Thuoc_CapNhat implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;

	private ButtonRound buttonCapNhat;
	
	private TextFieldRound textfieldMaThuoc;
	private TextFieldRound textfieldTenThuoc;
	private TextFieldRound textfieldCongDung;
	private TextFieldRound textfieldSoLuongConLai;
	private TextFieldRound textfieldGia;
	
	private JTable table;
	
	Thuoc_CapNhat(QLHome MyHome, JTable table){
		
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

		JLabel labelHeading = new JLabel("CAP NHAT THUOC");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
        JLabel labelMaThuoc = new JLabel("Ma Thuoc : ");
		labelMaThuoc.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaThuoc.setBounds(30, 30, 115, 20);
		labelMaThuoc.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaThuoc);
		
		textfieldMaThuoc = new TextFieldRound();
		textfieldMaThuoc.setBounds(150, 30, 115, 20);
		textfieldMaThuoc.setColumns(10);
		panel1.add(textfieldMaThuoc);
		
		JLabel labelTenThuoc = new JLabel("Ten Thuoc : ");
		labelTenThuoc.setFont(new Font("Bevan", Font.BOLD, 12));
		labelTenThuoc.setBounds(30, 70, 115, 20);
		labelTenThuoc.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelTenThuoc);
		
		textfieldTenThuoc = new TextFieldRound();
		textfieldTenThuoc.setBounds(150, 70, 115, 20);
		textfieldTenThuoc.setColumns(10);
		panel1.add(textfieldTenThuoc);
		
		JLabel labelCongDung = new JLabel("Cong Dung : ");
		labelCongDung.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCongDung.setFont(new Font("Bevan", Font.BOLD, 12));
		labelCongDung.setBounds(30, 110, 115, 20);
		panel1.add(labelCongDung);
		
		textfieldCongDung = new TextFieldRound();
		textfieldCongDung.setColumns(10);
		textfieldCongDung.setBounds(150, 110, 250, 20);
		panel1.add(textfieldCongDung);
		
		JLabel labelSoLuongConLai = new JLabel("So Luong : ");
		labelSoLuongConLai.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSoLuongConLai.setFont(new Font("Bevan", Font.BOLD, 12));
		labelSoLuongConLai.setBounds(30, 150, 115, 20);
		panel1.add(labelSoLuongConLai);
		
		textfieldSoLuongConLai = new TextFieldRound();
		textfieldSoLuongConLai.setColumns(10);
		textfieldSoLuongConLai.setBounds(150, 150, 115, 20);
		panel1.add(textfieldSoLuongConLai);
		
		JLabel labelGia = new JLabel("Gia 1 Vien : ");
		labelGia.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGia.setFont(new Font("Bevan", Font.BOLD, 12));
		labelGia.setBounds(30, 190, 115, 20);
		panel1.add(labelGia);
		
		textfieldGia = new TextFieldRound();
		textfieldGia.setColumns(10);
		textfieldGia.setBounds(150, 190, 115, 20);
		panel1.add(textfieldGia);
		
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
	        
		textfieldMaThuoc.setText((String)table.getValueAt(SelectedRow,mapping[0]));
		textfieldTenThuoc.setText((String)table.getValueAt(SelectedRow,mapping[1]));
		textfieldCongDung.setText((String)table.getValueAt(SelectedRow,mapping[2]));
		textfieldSoLuongConLai.setText((String)table.getValueAt(SelectedRow,mapping[3]));
		textfieldGia.setText((String)table.getValueAt(SelectedRow,mapping[4]));
	
	        textfieldMaThuoc.setBackground(new Color(210, 210, 210));
	        textfieldMaThuoc.setEditable(false);
	}
	
	
	public void CapNhat() {
		
			if (textfieldSoLuongConLai.getText().equals("")) {
				textfieldSoLuongConLai.setText("0");
			}
			if (textfieldGia.getText().equals("")) {
				textfieldGia.setText("0");
			}

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thuoc_sua1thuoc (?,?,?,?,?,?) }");
			
			statement.setString(1,textfieldMaThuoc.getText());
			statement.setString(2,textfieldTenThuoc.getText());
			statement.setString(3,textfieldCongDung.getText());
			statement.setString(4,textfieldSoLuongConLai.getText());
			statement.setString(5,textfieldGia.getText());
			statement.registerOutParameter(6,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(6);
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
		textfieldMaThuoc.setText("");
		textfieldTenThuoc.setText("");
		textfieldCongDung.setText("");
		textfieldSoLuongConLai.setText("");
		textfieldGia.setText("");
	}
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
                case "Ma Thuoc":
                    mapping[0] = i;
                    break;
                case "Ten Thuoc":
                    mapping[1] = i;
                    break;
                case "Cong Dung":
                    mapping[2] = i;
                    break;
                case "So Luong Con Lai":
                    mapping[3] = i;
                    break;
                case "Gia 1 Vien":
                    mapping[4] = i;
                    break;
            }
        }
	return mapping;
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
