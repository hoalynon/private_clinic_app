package QuanLy;



import java.awt.*;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DateFormatter;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import Chung.ButtonRound;
import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class CaBenh_Them implements ActionListener, KeyListener, MouseListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonThem;

	private JRadioButton rbuttonTaiGia;
	private JRadioButton rbuttonNhapVien;
	private JRadioButton rbuttonCachLy;
	private ButtonGroup bgroupHinhThuc;
	
	private TextFieldRound textfieldMaCa;
	private TextFieldRound textfieldMaBN;
	private TextFieldRound textfieldMaBS;
	private TextFieldRound textfieldMaBenh;
	private TextFieldRound textfieldMaPhong;
	
	private JDateChooser NgayBatDau;
	private JDateChooser NgayKetThuc;
	
	private JSpinner spinnerNgayBatDau;
	private JSpinner spinnerNgayKetThuc;
	
	private JComboBox comboBoxMucDo;
	private JComboBox comboBoxTinhTrang;
	
	private String listMucDo[]  = {"Khong cap cuu","Hoi suc","Nang","Cham soc dac biet","Cap cuu"}; 
	private String listTinhTrang[]  = {"Trieu chung","Chuan doan","Dieu tri","Giam sat","Cham soc"}; 

	private int maxRow;
	
	private JFrame frameChonBN;
	private JFrame frameChonBS;
	private JFrame frameChonBE;
	private JFrame frameChonPH;
	
	private ButtonRound buttonChonBN;
	private ButtonRound buttonChonBS;
	private ButtonRound buttonChonBE;
	private ButtonRound buttonChonPH;
	
	private JTable tableChonBN;
	private JTable tableChonBS;
	private JTable tableChonBE;
	private JTable tableChonPH;

	CaBenh_Them(QLHome MyHome){
		
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
		
		JLabel labelHeading = new JLabel("THEM MOI CA BENH");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
        JLabel labelMaCa = new JLabel("Ma Ca : ");
		labelMaCa.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaCa.setBounds(30, 0, 115, 20);
		labelMaCa.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaCa);
		
		textfieldMaCa = new TextFieldRound();
		textfieldMaCa.setBounds(150, 0, 115, 20);
		textfieldMaCa.setColumns(10);
		panel1.add(textfieldMaCa);
		maxRow = getMaxRow();
		textfieldMaCa.setText("CA" + MyHome.fillZero(maxRow, 3) + Integer.toString(maxRow));
        textfieldMaCa.setBackground(textbackcolor);
        textfieldMaCa.setEditable(false);
        
		JLabel labelMaBN = new JLabel("Ma BN : ");
		labelMaBN.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaBN.setBounds(30, 40, 115, 20);
		labelMaBN.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaBN);
		
		textfieldMaBN = new TextFieldRound();
		textfieldMaBN.setBounds(150, 40, 115, 20);
		textfieldMaBN.setColumns(10);
		panel1.add(textfieldMaBN);
		
		JLabel labelMaBS = new JLabel("Ma BS : ");
		labelMaBS.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaBS.setBounds(30, 80, 115, 20);
		labelMaBS.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaBS);
		
		textfieldMaBS = new TextFieldRound();
		textfieldMaBS.setBounds(150, 80, 115, 20);
		textfieldMaBS.setColumns(10);
		panel1.add(textfieldMaBS);
		
		JLabel labelMaBenh = new JLabel("Ma Benh : ");
		labelMaBenh.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMaBenh.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaBenh.setBounds(30, 120, 115, 20);
		panel1.add(labelMaBenh);
		
		textfieldMaBenh = new TextFieldRound();
		textfieldMaBenh.setColumns(10);
		textfieldMaBenh.setBounds(150, 120, 115, 20);
		panel1.add(textfieldMaBenh);
		
		JLabel labelMaPhong = new JLabel("Ma Phong : ");
		labelMaPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMaPhong.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaPhong.setBounds(30, 160, 115, 20);
		panel1.add(labelMaPhong);
		
		textfieldMaPhong = new TextFieldRound();
		textfieldMaPhong.setColumns(10);
		textfieldMaPhong.setBounds(150, 160, 115, 20);
		panel1.add(textfieldMaPhong);
		
		JLabel labelHinhThuc = new JLabel("Hinh Thuc : ");
		labelHinhThuc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelHinhThuc.setFont(new Font("Bevan", Font.BOLD, 12));
		labelHinhThuc.setBounds(30, 200, 115, 20);
		panel1.add(labelHinhThuc);

		rbuttonTaiGia = new JRadioButton("Tai gia");
		rbuttonTaiGia.setBackground(textbackcolor);
		rbuttonTaiGia.setBounds(150, 200, 70, 20);
		rbuttonTaiGia.setActionCommand("Tai gia");
		panel1.add(rbuttonTaiGia);
		
		rbuttonNhapVien = new JRadioButton("Nhap vien");
		rbuttonNhapVien.setBackground(textbackcolor);
		rbuttonNhapVien.setBounds(220, 200, 90, 20);
		rbuttonNhapVien.setActionCommand("Nhap vien");
		panel1.add(rbuttonNhapVien);
		
		rbuttonCachLy = new JRadioButton("Cach ly");
		rbuttonCachLy.setBackground(textbackcolor);
		rbuttonCachLy.setBounds(310, 200, 70, 20);
		rbuttonCachLy.setActionCommand("Cach ly");
		panel1.add(rbuttonCachLy);
		
		bgroupHinhThuc = new ButtonGroup();
		bgroupHinhThuc.add(rbuttonTaiGia);
		bgroupHinhThuc.add(rbuttonNhapVien);
		bgroupHinhThuc.add(rbuttonCachLy);

		JLabel labelMucDo = new JLabel("Muc Do : ");
		labelMucDo.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMucDo.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMucDo.setBounds(300, 40, 115, 20);
		panel1.add(labelMucDo);
		
		comboBoxMucDo = new JComboBox(listMucDo);
		comboBoxMucDo.setBounds(420, 40, 115, 20);
		panel1.add(comboBoxMucDo);
		
		JLabel labelTinhTrang = new JLabel("Tinh Trang : ");
		labelTinhTrang.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTinhTrang.setFont(new Font("Bevan", Font.BOLD, 12));
		labelTinhTrang.setBounds(300, 80, 115, 20);
		panel1.add(labelTinhTrang);
		
		comboBoxTinhTrang = new JComboBox(listTinhTrang);
		comboBoxTinhTrang.setBounds(420, 80, 115, 20);
		panel1.add(comboBoxTinhTrang);
		
		JLabel labelNgayBatDau = new JLabel("Bat Dau: ");
		labelNgayBatDau.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayBatDau.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayBatDau.setBounds(300, 120, 115, 20);
		panel1.add(labelNgayBatDau);
		
		NgayBatDau = new JDateChooser();
		NgayBatDau.setBounds(420, 120, 115, 20);
		NgayBatDau.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgayBatDau.setDateFormatString("dd/MM/yyyy");
		NgayBatDau.setDate(Calendar.getInstance().getTime());
		panel1.add(NgayBatDau);
		
		SpinnerDateModel modelNgayBatDau = new SpinnerDateModel();
        modelNgayBatDau.setValue(Calendar.getInstance().getTime());
        spinnerNgayBatDau = new JSpinner(modelNgayBatDau);
        JSpinner.DateEditor editorNgayBatDau = new JSpinner.DateEditor(spinnerNgayBatDau, "HH:mm:ss");
        spinnerNgayBatDau.setEditor(editorNgayBatDau);
        spinnerNgayBatDau.setBounds(540, 120, 80, 20);
        panel1.add(spinnerNgayBatDau);
        DateFormatter formatterNgayBatDau = (DateFormatter)editorNgayBatDau.getTextField().getFormatter();
        formatterNgayBatDau.setAllowsInvalid(false);
        formatterNgayBatDau.setOverwriteMode(true);
        
        NgayBatDau.setBackground(new Color(210, 210, 210));
        ((JTextFieldDateEditor) NgayBatDau.getDateEditor()).setEditable(false);
        NgayBatDau.getCalendarButton().setEnabled(false);
        spinnerNgayBatDau.getComponent(0).setEnabled(false);
        spinnerNgayBatDau.getComponent(1).setEnabled(false);
        ((DefaultEditor) spinnerNgayBatDau.getEditor()).getTextField().setEditable(false);
		
		JLabel labelNgayKetThuc = new JLabel("Ket Thuc: ");
		labelNgayKetThuc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayKetThuc.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayKetThuc.setBounds(300, 160, 115, 20);
		panel1.add(labelNgayKetThuc);
		
		NgayKetThuc = new JDateChooser();
		NgayKetThuc.setBounds(420, 160, 115, 20);
		NgayKetThuc.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgayKetThuc.setDateFormatString("dd/MM/yyyy");
		NgayKetThuc.setDate(Calendar.getInstance().getTime());
		panel1.add(NgayKetThuc);
		
		SpinnerDateModel modelNgayKetThuc = new SpinnerDateModel();
        modelNgayKetThuc.setValue(Calendar.getInstance().getTime());
        spinnerNgayKetThuc = new JSpinner(modelNgayKetThuc);
        JSpinner.DateEditor editorNgayKetThuc = new JSpinner.DateEditor(spinnerNgayKetThuc, "HH:mm:ss");
        spinnerNgayKetThuc.setEditor(editorNgayKetThuc);
        spinnerNgayKetThuc.setBounds(540, 160, 80, 20);
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
		
		//buttonChonBN = new JButton(new ImageIcon("PIM_ver4\\picture\\patient.png"));
		buttonChonBN = new ButtonRound();
		buttonChonBN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("patient.png")));
		buttonChonBN.setForeground(new Color(40, 82, 106));
		buttonChonBN.setBackground(new Color(200, 210, 230));
		buttonChonBN.addActionListener(this);
		buttonChonBN.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonBN.setBounds(270, 40, 30, 20);
		buttonChonBN.setBorderPainted(false);
		panel1.add(buttonChonBN);
		
		//buttonChonBS = new JButton(new ImageIcon("PIM_ver4\\picture\\doctor.png"));
		buttonChonBS = new ButtonRound();
		buttonChonBS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("doctor.png")));
		buttonChonBS.setForeground(new Color(40, 82, 106));
		buttonChonBS.setBackground(new Color(200, 210, 230));
		buttonChonBS.addActionListener(this);
		buttonChonBS.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonBS.setBounds(270, 80, 30, 20);
		buttonChonBS.setBorderPainted(false);
		panel1.add(buttonChonBS);
		
		//buttonChonBE = new JButton(new ImageIcon("PIM_ver4\\picture\\virus.png"));
		buttonChonBE = new ButtonRound();
		buttonChonBE.setIcon(new ImageIcon(getClass().getClassLoader().getResource("virus.png")));
		buttonChonBE.setForeground(new Color(40, 82, 106));
		buttonChonBE.setBackground(new Color(200, 210, 230));
		buttonChonBE.addActionListener(this);
		buttonChonBE.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonBE.setBounds(270, 120, 30, 20);
		buttonChonBE.setBorderPainted(false);
		panel1.add(buttonChonBE);
		
		//buttonChonPH = new JButton(new ImageIcon("PIM_ver4\\picture\\operating-room.png"));
		buttonChonPH = new ButtonRound();
		buttonChonPH.setIcon(new ImageIcon(getClass().getClassLoader().getResource("operating-room.png")));
		buttonChonPH.setForeground(new Color(40, 82, 106));
		buttonChonPH.setBackground(new Color(200, 210, 230));
		buttonChonPH.addActionListener(this);
		buttonChonPH.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonPH.setBounds(270, 160, 30, 20);
		buttonChonPH.setBorderPainted(false);
		panel1.add(buttonChonPH);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonThem || e.getSource() == MyHome.getButtonBack()) {
			if (frameChonBN != null) {
				frameChonBN.dispose();
			}
			if (frameChonBS != null) {
				frameChonBS.dispose();
			}
			if (frameChonBE != null) {
				frameChonBE.dispose();
			}
			if (frameChonPH != null) {
				frameChonPH.dispose();
			}
			Them();
		}
		if (e.getSource() == buttonChonBN) {
        	if (frameChonBN != null) {
				frameChonBN.dispose();
			} 	
			chonBN();
		}
        if (e.getSource() == buttonChonBS) {
        	if (frameChonBS != null) {
				frameChonBS.dispose();
			}
        	if (frameChonBE != null) {
				frameChonBE.dispose();
			}
			chonBS();
		}
        if (e.getSource() == buttonChonBE) {
        	if (frameChonBE != null) {
				frameChonBE.dispose();
			}
        	if (frameChonBS != null) {
				frameChonBS.dispose();
			}
			chonBE();
		}
        if (e.getSource() == buttonChonPH) {
        	if (frameChonPH != null) {
				frameChonPH.dispose();
			} 	
			chonPH();
		}
	}
	
	 @Override
		public void mouseClicked(MouseEvent e) {
			
		 if (e.getSource() == tableChonBN) {
				textfieldMaBN.setText((String) tableChonBN.getValueAt(tableChonBN.getSelectedRow(),0));
			}
			if (e.getSource() == tableChonBS) {
				textfieldMaBS.setText((String) tableChonBS.getValueAt(tableChonBS.getSelectedRow(),0));
			}
			if (e.getSource() == tableChonBE) {
				textfieldMaBenh.setText((String) tableChonBE.getValueAt(tableChonBE.getSelectedRow(),0));
			}
			if (e.getSource() == tableChonPH) {
				textfieldMaPhong.setText((String) tableChonPH.getValueAt(tableChonPH.getSelectedRow(),0));
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
			if (e.getSource() == tableChonBN) {
				textfieldMaBN.setText((String) tableChonBN.getValueAt(tableChonBN.getSelectedRow(),0));
			}
			if (e.getSource() == tableChonBS) {
				textfieldMaBS.setText((String) tableChonBS.getValueAt(tableChonBS.getSelectedRow(),0));
			}
			if (e.getSource() == tableChonBE) {
				textfieldMaBenh.setText((String) tableChonBE.getValueAt(tableChonBE.getSelectedRow(),0));
			}
			if (e.getSource() == tableChonPH) {
				textfieldMaPhong.setText((String) tableChonPH.getValueAt(tableChonPH.getSelectedRow(),0));
			}
		}
	
	public void chonBN() {
		frameChonBN = new JFrame("Danh sach cac benh nhan");
		frameChonBN.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameChonBN.setSize(300,200);
		frameChonBN.setLocationRelativeTo(buttonChonBN);
		frameChonBN.setLocation(frameChonBN.getX() + 175, frameChonBN.getY() - 100);
		frameChonBN.setBackground(new Color(214, 231, 239));
		frameChonBN.setLayout(new BorderLayout());
		frameChonBN.addMouseListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension (400, 200));
		frameChonBN.add(scrollPane,BorderLayout.CENTER);
		
		tableChonBN = new JTable() {
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
			tableChonBN.setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonBN.getTableHeader().setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonBN.setBackground(new Color(255, 255, 255));
			tableChonBN.setGridColor(new Color(235, 235, 235));
			tableChonBN.setRowHeight(20);
			tableChonBN.addMouseListener(this);
			tableChonBN.addKeyListener(this);
			tableChonBN.getTableHeader().setReorderingAllowed(false);
		
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_benhnhan_laybenhnhan (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) tableChonBN.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ma BN","Ho Ten","Ngay Sinh"});
			data.setRowCount(0);
			
			tableChonBN.getColumnModel().getColumn(0).setPreferredWidth(75);
            tableChonBN.getColumnModel().getColumn(1).setPreferredWidth(175);
            tableChonBN.getColumnModel().getColumn(2).setPreferredWidth(150);
			
			while(resultset.next()) {
				Vector row = new Vector();
				for (int i = 0; i < columnCount; i++) {
					if (i == 0 || i == 1) {
						row.add(resultset.getString(i+1));
	                }
	                else if (i == 3 && resultset.getDate(i+1) != null){
	                	row.add(new SimpleDateFormat("dd/MM/yyyy").format(resultset.getDate(i+1)));
	                }
				}
				data.addRow(row);
			} 
            
			statement.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(tableChonBN);
		
		frameChonBN.setVisible(true);
	}
	
	public void chonBS() {
		frameChonBS = new JFrame("Danh sach cac bac si");
		frameChonBS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameChonBS.setSize(300,200);
		frameChonBS.setLocationRelativeTo(buttonChonBS);
		frameChonBS.setLocation(frameChonBS.getX() + 175, frameChonBS.getY() - 50);
		frameChonBS.setBackground(new Color(214, 231, 239));
		frameChonBS.setLayout(new BorderLayout());
		frameChonBS.addMouseListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension (400, 200));
		frameChonBS.add(scrollPane,BorderLayout.CENTER);
		
		tableChonBS = new JTable() {
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
			tableChonBS.setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonBS.getTableHeader().setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonBS.setBackground(new Color(255, 255, 255));
			tableChonBS.setGridColor(new Color(235, 235, 235));
			tableChonBS.setRowHeight(20);
			tableChonBS.addMouseListener(this);
			tableChonBS.addKeyListener(this);
			tableChonBS.getTableHeader().setReorderingAllowed(false);
		
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_cabenh_laybacsi_theobenh (?,?) }");
			statement.setString(1,textfieldMaBenh.getText());
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) tableChonBS.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ma BS","Ho Ten","Ten Khoa"});
			data.setRowCount(0);
			
			tableChonBS.getColumnModel().getColumn(0).setPreferredWidth(75);
            tableChonBS.getColumnModel().getColumn(1).setPreferredWidth(175);
            tableChonBS.getColumnModel().getColumn(2).setPreferredWidth(150);
			
			while(resultset.next()) {
				Vector row = new Vector();
				for (int i = 0; i < columnCount; i++) {
					if (i == 0 || i == 1 || i == 6) {
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
		
		scrollPane.setViewportView(tableChonBS);
		
		frameChonBS.setVisible(true);
	}
	
	public void chonBE() {
		frameChonBE = new JFrame("Danh sach cac benh");
		frameChonBE.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameChonBE.setSize(300,200);
		frameChonBE.setLocationRelativeTo(buttonChonBE);
		frameChonBE.setLocation(frameChonBE.getX() + 175, frameChonBE.getY());
		frameChonBE.setBackground(new Color(214, 231, 239));
		frameChonBE.setLayout(new BorderLayout());
		frameChonBE.addMouseListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension (400, 200));
		frameChonBE.add(scrollPane,BorderLayout.CENTER);
		
		tableChonBE = new JTable() {
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
			tableChonBE.setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonBE.getTableHeader().setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonBE.setBackground(new Color(255, 255, 255));
			tableChonBE.setGridColor(new Color(235, 235, 235));
			tableChonBE.setRowHeight(20);
			tableChonBE.addMouseListener(this);
			tableChonBE.addKeyListener(this);
			tableChonBE.getTableHeader().setReorderingAllowed(false);
		
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_benh_laybenh (?)  }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) tableChonBE.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ma Benh","Ten Benh","Ten Khoa"});
			data.setRowCount(0);
			
			tableChonBE.getColumnModel().getColumn(0).setPreferredWidth(75);
            tableChonBE.getColumnModel().getColumn(1).setPreferredWidth(175);
            tableChonBE.getColumnModel().getColumn(2).setPreferredWidth(150);
			
			while(resultset.next()) {
				Vector row = new Vector();
				for (int i = 0; i < columnCount; i++) {
						row.add(resultset.getString(i+1));
				}
				data.addRow(row);
			} 
            
			statement.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(tableChonBE);
		
		frameChonBE.setVisible(true);
	}
	
	public void chonPH() {
		frameChonPH = new JFrame("Danh sach cac phong trong");
		frameChonPH.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameChonPH.setSize(300,200);
		frameChonPH.setLocationRelativeTo(buttonChonPH);
		frameChonPH.setLocation(frameChonPH.getX() + 175, frameChonPH.getY() + 50);
		frameChonPH.setBackground(new Color(214, 231, 239));
		frameChonPH.setLayout(new BorderLayout());
		frameChonPH.addMouseListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension (400, 200));
		frameChonPH.add(scrollPane,BorderLayout.CENTER);
		
		tableChonPH = new JTable() {
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
			tableChonPH.setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonPH.getTableHeader().setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonPH.setBackground(new Color(255, 255, 255));
			tableChonPH.setGridColor(new Color(235, 235, 235));
			tableChonPH.setRowHeight(20);
			tableChonPH.addMouseListener(this);
			tableChonPH.addKeyListener(this);
			tableChonPH.getTableHeader().setReorderingAllowed(false);
		
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_phongbenh_layphongbenh (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) tableChonPH.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ma Phong","Loai","Con Trong"});
			data.setRowCount(0);
			
			tableChonPH.getColumnModel().getColumn(0).setPreferredWidth(75);
            tableChonPH.getColumnModel().getColumn(1).setPreferredWidth(175);
            tableChonPH.getColumnModel().getColumn(2).setPreferredWidth(150);
			
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
		
		scrollPane.setViewportView(tableChonPH);
		
		frameChonPH.setVisible(true);
	}
	
	public void Them() {
		
		String stringNgayBatDau = "", stringSpinnerNgayBatDau = "", stringBatDau = "NULL";
		if (NgayBatDau.getDate() != null) {
			Date date = NgayBatDau.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			stringNgayBatDau = sdf.format(date);
			
			date = (Date)spinnerNgayBatDau.getValue();
            sdf = new SimpleDateFormat("HH:mm:ss");
            stringSpinnerNgayBatDau = sdf.format(date);
            stringBatDau = stringNgayBatDau + " " + stringSpinnerNgayBatDau;
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
		
		String stringHinhThuc = ""; 
		if (bgroupHinhThuc.getSelection() != null) {
			stringHinhThuc = bgroupHinhThuc.getSelection().getActionCommand();
		}				

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_cabenh_them1cabenh (?,?,?,?,?,?,?,?,?,?,?) }");
			statement.setString(1,textfieldMaCa.getText());
			statement.setString(2,textfieldMaBN.getText());
			statement.setString(3,textfieldMaBS.getText());
			statement.setString(4,textfieldMaBenh.getText());
			statement.setString(5,(String) comboBoxMucDo.getItemAt(comboBoxMucDo.getSelectedIndex()));
			statement.setString(6,stringHinhThuc);
			statement.setString(7,stringBatDau);
			statement.setString(8,stringKetThuc);
			statement.setString(9,(String) comboBoxTinhTrang.getItemAt(comboBoxTinhTrang.getSelectedIndex()));
			statement.setString(10,textfieldMaPhong.getText());
			statement.registerOutParameter(11,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(11);
			if (changedrows > 0) {
				JOptionPane.showMessageDialog(null, "Them Moi Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
				textfieldMaCa.setText("CA" + MyHome.fillZero(maxRow, 3) + Integer.toString(++maxRow));
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
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_cabenh_laycabenh (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			while(resultset.next()) {
	                        compareNum = Integer.parseInt(resultset.getString("MACA").substring(2));
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
		textfieldMaCa.setText("");
		textfieldMaBN.setText("");
		textfieldMaBS.setText("");
		textfieldMaBenh.setText("");
		textfieldMaPhong.setText("");
		bgroupHinhThuc.clearSelection();
		NgayBatDau.setDate(Calendar.getInstance().getTime());
		NgayKetThuc.setDate(Calendar.getInstance().getTime());
		comboBoxMucDo.setSelectedIndex(0);
		comboBoxTinhTrang.setSelectedIndex(0);
		
		SpinnerDateModel modelNgayBatDau = new SpinnerDateModel();
        modelNgayBatDau.setValue(Calendar.getInstance().getTime());
        spinnerNgayBatDau.setModel(modelNgayBatDau);
        JSpinner.DateEditor editorNgayBatDau = new JSpinner.DateEditor(spinnerNgayBatDau, "HH:mm:ss");
        spinnerNgayBatDau.setEditor(editorNgayBatDau);
        DateFormatter formatterNgayBatDau = (DateFormatter)editorNgayBatDau.getTextField().getFormatter();
        formatterNgayBatDau.setAllowsInvalid(false);
        formatterNgayBatDau.setOverwriteMode(true);
        
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
