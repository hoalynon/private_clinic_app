package QuanLy;



import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DateFormatter;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import Chung.ButtonRound;
import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class DieuPhoiThietBi_Them implements ActionListener, KeyListener, MouseListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonThem;
	
	private TextFieldRound textfieldMaCa;
	private TextFieldRound textfieldMaThietBi;
	private TextFieldRound textfieldSoLuong;
	
	private JDateChooser NgayDieuPhoi;
	private JDateChooser NgayKetThuc;
	
	private JSpinner spinnerNgayDieuPhoi;
	private JSpinner spinnerNgayKetThuc;
	
	private JFrame frameChonTB;

	private ButtonRound buttonChonTB;

	private JTable tableChonTB;


	DieuPhoiThietBi_Them(QLHome MyHome){
		
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
		
		JLabel labelHeading = new JLabel("THEM MOI DIEU PHOI THIET BI");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
		JLabel labelMaCa = new JLabel("Ma Ca : ");
		labelMaCa.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaCa.setBounds(30, 30, 115, 20);
		labelMaCa.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaCa);
		
		textfieldMaCa = new TextFieldRound();
		textfieldMaCa.setBounds(150, 30, 115, 20);
		textfieldMaCa.setColumns(10);
		panel1.add(textfieldMaCa);
		
		JLabel labelMaThietBi = new JLabel("Ma Thiet Bi : ");
		labelMaThietBi.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaThietBi.setBounds(30, 70, 115, 20);
		labelMaThietBi.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaThietBi);
		
		textfieldMaThietBi = new TextFieldRound();
		textfieldMaThietBi.setBounds(150, 70, 115, 20);
		textfieldMaThietBi.setColumns(10);
		panel1.add(textfieldMaThietBi);
		
		JLabel labelSoLuong = new JLabel("So Luong : ");
		labelSoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSoLuong.setFont(new Font("Bevan", Font.BOLD, 12));
		labelSoLuong.setBounds(30, 110, 115, 20);
		panel1.add(labelSoLuong);
		
		textfieldSoLuong = new TextFieldRound();
		textfieldSoLuong.setColumns(10);
		textfieldSoLuong.setBounds(150, 110, 115, 20);
		panel1.add(textfieldSoLuong);
		
		JLabel labelNgayDieuPhoi = new JLabel("Ngay Dieu Phoi: ");
		labelNgayDieuPhoi.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayDieuPhoi.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayDieuPhoi.setBounds(300, 30, 115, 20);
		panel1.add(labelNgayDieuPhoi);
		
		NgayDieuPhoi = new JDateChooser();
		NgayDieuPhoi.setBounds(420, 30, 115, 20);
		NgayDieuPhoi.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgayDieuPhoi.setDateFormatString("dd/MM/yyyy");
		NgayDieuPhoi.setDate(Calendar.getInstance().getTime());
		panel1.add(NgayDieuPhoi);
		
		SpinnerDateModel modelNgayDieuPhoi = new SpinnerDateModel();
        modelNgayDieuPhoi.setValue(Calendar.getInstance().getTime());
        spinnerNgayDieuPhoi = new JSpinner(modelNgayDieuPhoi);
        JSpinner.DateEditor editorNgayDieuPhoi = new JSpinner.DateEditor(spinnerNgayDieuPhoi, "HH:mm:ss");
        spinnerNgayDieuPhoi.setEditor(editorNgayDieuPhoi);
        spinnerNgayDieuPhoi.setBounds(540, 30, 80, 20);
        panel1.add(spinnerNgayDieuPhoi);
        DateFormatter formatterNgayDieuPhoi = (DateFormatter)editorNgayDieuPhoi.getTextField().getFormatter();
        formatterNgayDieuPhoi.setAllowsInvalid(false);
        formatterNgayDieuPhoi.setOverwriteMode(true);
		
		JLabel labelNgayKetThuc = new JLabel("Ngay Ket Thuc: ");
		labelNgayKetThuc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayKetThuc.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayKetThuc.setBounds(300, 70, 115, 20);
		panel1.add(labelNgayKetThuc);
		
		NgayKetThuc = new JDateChooser();
		NgayKetThuc.setBounds(420, 70, 115, 20);
		NgayKetThuc.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgayKetThuc.setDateFormatString("dd/MM/yyyy");
		NgayKetThuc.setDate(Calendar.getInstance().getTime());
		panel1.add(NgayKetThuc);
		
		SpinnerDateModel modelNgayKetThuc = new SpinnerDateModel();
        modelNgayKetThuc.setValue(Calendar.getInstance().getTime());
        spinnerNgayKetThuc = new JSpinner(modelNgayKetThuc);
        JSpinner.DateEditor editorNgayKetThuc = new JSpinner.DateEditor(spinnerNgayKetThuc, "HH:mm:ss");
        spinnerNgayKetThuc.setEditor(editorNgayKetThuc);
        spinnerNgayKetThuc.setBounds(540, 70, 80, 20);
        panel1.add(spinnerNgayKetThuc);
        DateFormatter formatterNgayKetThuc = (DateFormatter)editorNgayKetThuc.getTextField().getFormatter();
        formatterNgayKetThuc.setAllowsInvalid(false);
        formatterNgayKetThuc.setOverwriteMode(true);
		
		buttonThem = new ButtonRound();
		buttonThem.setText("    Them    ");
		buttonThem.setForeground(buttonfrontcolor);
		buttonThem.setBackground(buttonbackcolor);
		buttonThem.addActionListener(this);
		buttonThem.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonThem.setBounds(80, 30, 85, 21);
		buttonThem.setBorderPainted(false);
		panel2.add(buttonThem);
		
		//buttonChonTB = new JButton(new ImageIcon("PIM_ver4\\picture\\syringe.png"));
		buttonChonTB = new ButtonRound();
		buttonChonTB.setIcon(new ImageIcon(getClass().getClassLoader().getResource("syringe.png")));
		buttonChonTB.setForeground(new Color(40, 82, 106));
		buttonChonTB.setBackground(new Color(200, 210, 230));
		buttonChonTB.addActionListener(this);
		buttonChonTB.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonTB.setBounds(270, 70, 30, 20);
		buttonChonTB.setBorderPainted(false);
		panel1.add(buttonChonTB);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == buttonThem || e.getSource() == MyHome.getButtonBack()) {
			if (frameChonTB != null) {
				frameChonTB.dispose();
			}
			Them();
		}
		if (e.getSource() == buttonChonTB) {
        	if (frameChonTB != null) {
				frameChonTB.dispose();
			} 	
			chonTB();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() == tableChonTB) {
			textfieldMaThietBi.setText((String) tableChonTB.getValueAt(tableChonTB.getSelectedRow(),0));
		}

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == tableChonTB) {
			textfieldMaThietBi.setText((String) tableChonTB.getValueAt(tableChonTB.getSelectedRow(),0));
		}
	}
	
	public void chonTB() {
		frameChonTB = new JFrame("Danh sach cac thiet bi");
		frameChonTB.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameChonTB.setSize(300,200);
		frameChonTB.setLocationRelativeTo(buttonChonTB);
		frameChonTB.setLocation(frameChonTB.getX() + 175, frameChonTB.getY() + 50);
		frameChonTB.setBackground(new Color(214, 231, 239));
		frameChonTB.setLayout(new BorderLayout());
		frameChonTB.addMouseListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension (400, 200));
		frameChonTB.add(scrollPane,BorderLayout.CENTER);
		
		tableChonTB = new JTable() {
			boolean[] columnEditables = new boolean[] {
                                        false, false, false
				};
			@Override
		    public boolean isCellEditable(int rowIndex, int columnIndex)
		    {
		        return columnEditables[columnIndex];
		    }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	            Component comp = super.prepareRenderer(renderer, row, column);
	            if (isCellSelected(row,column)) {
	            	comp.setBackground(new Color(211, 211, 211));
	            	comp.setForeground(new Color(0, 0, 0));
	            }
	            else if (row % 2 == 0) {
	            	comp.setBackground(new Color(255, 255, 255));
	            	comp.setForeground(new Color(0, 0, 0));
	            }
	            else {
	            	comp.setBackground(new Color(255, 255, 255));
	            	comp.setForeground(new Color(0, 0, 0));
	            }
	            return comp;
			}
			};
			tableChonTB.setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonTB.getTableHeader().setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonTB.setBackground(new Color(255, 255, 255));
			tableChonTB.setGridColor(new Color(235, 235, 235));
			tableChonTB.setRowHeight(20);
			tableChonTB.addMouseListener(this);
			tableChonTB.addKeyListener(this);
			tableChonTB.getTableHeader().setReorderingAllowed(false);
		
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thietbiyte_laythietbiyte (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) tableChonTB.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ma Thiet Bi","Ten Thiet Bi","SL Con Lai"});
			data.setRowCount(0);
			
			tableChonTB.getColumnModel().getColumn(0).setPreferredWidth(75);
            tableChonTB.getColumnModel().getColumn(1).setPreferredWidth(250);
            tableChonTB.getColumnModel().getColumn(2).setPreferredWidth(75);
			
			while(resultset.next()) {
				Vector row = new Vector();
				for (int i = 0; i < columnCount; i++) {
					if (i == 0 || i == 1 || i == 5) {
						row.add(resultset.getString(i+1));
	                }
				}
				data.addRow(row);
			} 
            
			statement.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(tableChonTB);
		
		frameChonTB.setVisible(true);
	}
	
	public void Them() {
		
		if (textfieldSoLuong.getText().equals("")) {
			textfieldSoLuong.setText("0");
		}
		
		String stringNgayDieuPhoi = "", stringSpinnerNgayDieuPhoi = "", stringDieuPhoi = "NULL";;
		if (NgayDieuPhoi.getDate() != null) {
			Date date = NgayDieuPhoi.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			stringNgayDieuPhoi = sdf.format(date);
			
			date = (Date)spinnerNgayDieuPhoi.getValue();
            sdf = new SimpleDateFormat("HH:mm:ss");
            stringSpinnerNgayDieuPhoi = sdf.format(date);
            stringDieuPhoi = stringNgayDieuPhoi + " " + stringSpinnerNgayDieuPhoi;
		}
		
		String stringNgayKetThuc = "", stringSpinnerNgayKetThuc = "", stringKetThuc = "NULL";
		if (NgayKetThuc.getDate() != null) {
			Date date = NgayKetThuc.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			stringNgayKetThuc = sdf.format(date);

			date = (Date)spinnerNgayKetThuc.getValue();
            sdf = new SimpleDateFormat("HH:mm:ss");
            stringSpinnerNgayKetThuc = sdf.format(date);
            stringKetThuc = stringNgayKetThuc + " " + stringSpinnerNgayKetThuc;
		}				

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_dieuphoithietbi_them1dieuphoithietbi (?,?,?,?,?,?) }");
			statement.setString(1,textfieldMaCa.getText());
			statement.setString(2,textfieldMaThietBi.getText());
			statement.setInt(3,Integer.parseInt(textfieldSoLuong.getText()));
			statement.setString(4,stringDieuPhoi);
			statement.setString(5,stringKetThuc);
			statement.registerOutParameter(6,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(6);
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
		textfieldMaCa.setText("");
		textfieldMaThietBi.setText("");
		textfieldSoLuong.setText("");
		NgayDieuPhoi.setDate(Calendar.getInstance().getTime());
		NgayKetThuc.setDate(Calendar.getInstance().getTime());
		
		SpinnerDateModel modelNgayDieuPhoi = new SpinnerDateModel();
        modelNgayDieuPhoi.setValue(Calendar.getInstance().getTime());
        spinnerNgayDieuPhoi.setModel(modelNgayDieuPhoi);
        JSpinner.DateEditor editorNgayDieuPhoi = new JSpinner.DateEditor(spinnerNgayDieuPhoi, "HH:mm:ss");
        spinnerNgayDieuPhoi.setEditor(editorNgayDieuPhoi);
        DateFormatter formatterNgayDieuPhoi = (DateFormatter)editorNgayDieuPhoi.getTextField().getFormatter();
        formatterNgayDieuPhoi.setAllowsInvalid(false);
        formatterNgayDieuPhoi.setOverwriteMode(true);
        
        SpinnerDateModel modelNgayKetThuc = new SpinnerDateModel();
        modelNgayKetThuc.setValue(Calendar.getInstance().getTime());
        spinnerNgayKetThuc.setModel(modelNgayKetThuc);
        JSpinner.DateEditor editorNgayKetThuc = new JSpinner.DateEditor(spinnerNgayKetThuc, "HH:mm:ss");
        spinnerNgayKetThuc.setEditor(editorNgayKetThuc);
        DateFormatter formatterNgayKetThuc = (DateFormatter)editorNgayKetThuc.getTextField().getFormatter();
        formatterNgayKetThuc.setAllowsInvalid(false);
        formatterNgayKetThuc.setOverwriteMode(true);
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
