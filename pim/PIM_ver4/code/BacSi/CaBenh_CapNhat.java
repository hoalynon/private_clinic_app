package BacSi;



import java.awt.*;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DateFormatter;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import Chung.ButtonRound;
import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class CaBenh_CapNhat implements ActionListener, KeyListener, MouseListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private BSHome MyHome;
	private JPanel panelContent;

	private ButtonRound buttonCapNhat;
	
	private JRadioButton rbuttonTaiGia;
	private JRadioButton rbuttonNhapVien;
	private JRadioButton rbuttonCachLy;
	private ButtonGroup bgroupHinhThuc;
	
	private TextFieldRound textfieldMaCa;
	private TextFieldRound textfieldMaBN;
	private TextFieldRound textfieldMaBenh;
	private TextFieldRound textfieldMaPhong;
	
	private JDateChooser NgayBatDau;
	private JDateChooser NgayKetThuc;
	
	private JSpinner spinnerNgayBatDau;
	private JSpinner spinnerNgayKetThuc;
	
	private JTable table;
	private JComboBox comboBoxMucDo;
	private JComboBox comboBoxTinhTrang;

	private String listMucDo[]  = {"Khong cap cuu","Hoi suc","Nang","Cham soc dac biet","Cap cuu"}; 
	private String listTinhTrang[]  = {"Trieu chung","Chuan doan","Dieu tri","Giam sat","Cham soc","Da ket thuc"}; 
	private int TinhTrangTruoc = 5;
	private int TinhTrangTruoc2;
	
	private JFrame frameChonBN;
	private JFrame frameChonBE;
	private JFrame frameChonPH;
	
	private ButtonRound buttonChonBN;
	private ButtonRound buttonChonBE;
	private ButtonRound buttonChonPH;
	
	private JTable tableChonBN;
	private JTable tableChonBE;
	private JTable tableChonPH;
	
	CaBenh_CapNhat(BSHome MyHome, JTable table){
		
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

		JLabel labelHeading = new JLabel("CAP NHAT CA BENH");
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
		
        JLabel labelMaBN = new JLabel("Ma BN : ");
		labelMaBN.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaBN.setBounds(30, 40, 115, 20);
		labelMaBN.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaBN);
		
		textfieldMaBN = new TextFieldRound();
		textfieldMaBN.setBounds(150, 40, 115, 20);
		textfieldMaBN.setColumns(10);
		panel1.add(textfieldMaBN);
		
		JLabel labelMaBenh = new JLabel("Ma Benh : ");
		labelMaBenh.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMaBenh.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaBenh.setBounds(30, 80, 115, 20);
		panel1.add(labelMaBenh);
		
		textfieldMaBenh = new TextFieldRound();
		textfieldMaBenh.setColumns(10);
		textfieldMaBenh.setBounds(150, 80, 115, 20);
		panel1.add(textfieldMaBenh);
		
		JLabel labelMaPhong = new JLabel("Ma Phong : ");
		labelMaPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMaPhong.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaPhong.setBounds(30, 120, 115, 20);
		panel1.add(labelMaPhong);
		
		textfieldMaPhong = new TextFieldRound();
		textfieldMaPhong.setColumns(10);
		textfieldMaPhong.setBounds(150, 120, 115, 20);
		panel1.add(textfieldMaPhong);
		
		JLabel labelHinhThuc = new JLabel("Hinh Thuc : ");
		labelHinhThuc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelHinhThuc.setFont(new Font("Bevan", Font.BOLD, 12));
		labelHinhThuc.setBounds(30, 160, 115, 20);
		panel1.add(labelHinhThuc);

		rbuttonTaiGia = new JRadioButton("Tai gia");
		rbuttonTaiGia.setBackground(textbackcolor);
		rbuttonTaiGia.setBounds(150, 160, 70, 20);
		rbuttonTaiGia.setActionCommand("Tai gia");
		panel1.add(rbuttonTaiGia);
		
		rbuttonNhapVien = new JRadioButton("Nhap vien");
		rbuttonNhapVien.setBackground(textbackcolor);
		rbuttonNhapVien.setBounds(220, 160, 90, 20);
		rbuttonNhapVien.setActionCommand("Nhap vien");
		panel1.add(rbuttonNhapVien);
		
		rbuttonCachLy = new JRadioButton("Cach ly");
		rbuttonCachLy.setBackground(textbackcolor);
		rbuttonCachLy.setBounds(150, 200, 70, 20);
		rbuttonCachLy.setActionCommand("Cach ly");
		panel1.add(rbuttonCachLy);
		
		bgroupHinhThuc = new ButtonGroup();
		bgroupHinhThuc.add(rbuttonTaiGia);
		bgroupHinhThuc.add(rbuttonNhapVien);
		bgroupHinhThuc.add(rbuttonCachLy);

		JLabel labelMucDo = new JLabel("Muc Do : ");
		labelMucDo.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMucDo.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMucDo.setBounds(300, 0, 115, 20);
		panel1.add(labelMucDo);
		
		comboBoxMucDo = new JComboBox(listMucDo);
		comboBoxMucDo.setBounds(420, 0, 115, 20);
		panel1.add(comboBoxMucDo);
		
		JLabel labelTinhTrang = new JLabel("Tinh Trang : ");
		labelTinhTrang.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTinhTrang.setFont(new Font("Bevan", Font.BOLD, 12));
		labelTinhTrang.setBounds(300, 40, 115, 20);
		panel1.add(labelTinhTrang);
		
		comboBoxTinhTrang = new JComboBox(listTinhTrang);
		comboBoxTinhTrang.setBounds(420, 40, 115, 20);
		comboBoxTinhTrang.addActionListener(this);
		panel1.add(comboBoxTinhTrang);
		
		JLabel labelNgayBatDau = new JLabel("Bat Dau: ");
		labelNgayBatDau.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayBatDau.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayBatDau.setBounds(300, 80, 115, 20);
		panel1.add(labelNgayBatDau);
		
		NgayBatDau = new JDateChooser();
		NgayBatDau.setBounds(420, 80, 115, 20);
		NgayBatDau.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgayBatDau.setDateFormatString("dd/MM/yyyy");
		NgayBatDau.setDate(Calendar.getInstance().getTime());
		panel1.add(NgayBatDau);
		
		SpinnerDateModel modelNgayBatDau = new SpinnerDateModel();
        modelNgayBatDau.setValue(Calendar.getInstance().getTime());
        spinnerNgayBatDau = new JSpinner(modelNgayBatDau);
        JSpinner.DateEditor editorNgayBatDau = new JSpinner.DateEditor(spinnerNgayBatDau, "HH:mm:ss");
        spinnerNgayBatDau.setEditor(editorNgayBatDau);
        spinnerNgayBatDau.setBounds(540, 80, 80, 20);
        panel1.add(spinnerNgayBatDau);
        DateFormatter formatterNgayBatDau = (DateFormatter)editorNgayBatDau.getTextField().getFormatter();
        formatterNgayBatDau.setAllowsInvalid(false);
        formatterNgayBatDau.setOverwriteMode(true);
		
		JLabel labelNgayKetThuc = new JLabel("Ket Thuc: ");
		labelNgayKetThuc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayKetThuc.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayKetThuc.setBounds(300, 120, 115, 20);
		panel1.add(labelNgayKetThuc);
		
		NgayKetThuc = new JDateChooser();
		NgayKetThuc.setBounds(420, 120, 115, 20);
		NgayKetThuc.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgayKetThuc.setDateFormatString("dd/MM/yyyy");
		NgayKetThuc.setDate(Calendar.getInstance().getTime());
		panel1.add(NgayKetThuc);
		
		SpinnerDateModel modelNgayKetThuc = new SpinnerDateModel();
        modelNgayKetThuc.setValue(Calendar.getInstance().getTime());
        spinnerNgayKetThuc = new JSpinner(modelNgayKetThuc);
        JSpinner.DateEditor editorNgayKetThuc = new JSpinner.DateEditor(spinnerNgayKetThuc, "HH:mm:ss");
        spinnerNgayKetThuc.setEditor(editorNgayKetThuc);
        spinnerNgayKetThuc.setBounds(540, 120, 80, 20);
        panel1.add(spinnerNgayKetThuc);
        DateFormatter formatterNgayKetThuc = (DateFormatter)editorNgayKetThuc.getTextField().getFormatter();
        formatterNgayKetThuc.setAllowsInvalid(false);
        formatterNgayKetThuc.setOverwriteMode(true);
		
		buttonCapNhat = new ButtonRound();
		buttonCapNhat.setText("  Cap Nhat  ");
		buttonCapNhat.setForeground(buttonfrontcolor);
		buttonCapNhat.setBackground(buttonbackcolor);
		buttonCapNhat.addActionListener(this);
		buttonCapNhat.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonCapNhat.setBounds(80, 40, 85, 21);
		buttonCapNhat.setBorderPainted(false);
		panel2.add(buttonCapNhat);
	
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
		
		//buttonChonBE = new JButton(new ImageIcon("PIM_ver4\\picture\\virus.png"));
		buttonChonBE = new ButtonRound();
		buttonChonBE.setIcon(new ImageIcon(getClass().getClassLoader().getResource("virus.png")));
		buttonChonBE.setForeground(new Color(40, 82, 106));
		buttonChonBE.setBackground(new Color(200, 210, 230));
		buttonChonBE.addActionListener(this);
		buttonChonBE.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonBE.setBounds(270, 80, 30, 20);
		buttonChonBE.setBorderPainted(false);
		panel1.add(buttonChonBE);
		
		//buttonChonPH = new JButton(new ImageIcon("PIM_ver4\\picture\\operating-room.png"));
		buttonChonPH = new ButtonRound();
		buttonChonPH.setIcon(new ImageIcon(getClass().getClassLoader().getResource("operating-room.png")));
		buttonChonPH.setForeground(new Color(40, 82, 106));
		buttonChonPH.setBackground(new Color(200, 210, 230));
		buttonChonPH.addActionListener(this);
		buttonChonPH.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonPH.setBounds(270, 120, 30, 20);
		buttonChonPH.setBorderPainted(false);
		panel1.add(buttonChonPH);
		
		if (table != null && table.getSelectedRowCount() == 1) {
			setInformation(table.getSelectedRow());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonCapNhat || e.getSource() == MyHome.getButtonBack()) {
			if (frameChonBN != null) {
				frameChonBN.dispose();
			}
			if (frameChonBE != null) {
				frameChonBE.dispose();
			}
			if (frameChonPH != null) {
				frameChonPH.dispose();
			}
			CapNhat();
		}
		if (e.getSource() == buttonChonBN) {
        	if (frameChonBN != null) {
				frameChonBN.dispose();
			} 	
			chonBN();
		}
        if (e.getSource() == buttonChonBE) {
        	if (frameChonBE != null) {
				frameChonBE.dispose();
			}
			chonBE();
		}
		if (e.getSource() == buttonChonPH) {
        	if (frameChonPH != null) {
				frameChonPH.dispose();
			} 	
			chonPH();
		}
		if (e.getSource() == comboBoxTinhTrang) {
			int result = 1;
			if (comboBoxTinhTrang.getItemAt(comboBoxTinhTrang.getSelectedIndex()).equals("Da ket thuc") && TinhTrangTruoc != 5) {
				result = JOptionPane.showConfirmDialog(null, "Voi tinh trang Da ket thuc, cac thuoc tinh khac se khong the thay doi. Xac nhan Da ket thuc ?","Xac nhan ket thuc",JOptionPane.YES_NO_OPTION);
				//TinhTrangTruoc2 = TinhTrangTruoc;
				if (result == 0) {
					TinhTrangTruoc = 5;
					setDisableAll();
				}
			}
			else if (result == 1){
				TinhTrangTruoc = comboBoxTinhTrang.getSelectedIndex();
			}
		}
		comboBoxTinhTrang.setSelectedIndex(TinhTrangTruoc);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() == tableChonBN) {
			textfieldMaBN.setText((String) tableChonBN.getValueAt(tableChonBN.getSelectedRow(),0));
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
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_cabenh_laybenh_theobacsi (?,?)  }");
			statement.setString(1,MyHome.getID());
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
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

public void setInformation(int SelectedRow) {

	    int [] mapping = getMapping();
	    
	    textfieldMaCa.setText((String)table.getValueAt(SelectedRow,mapping[0]));
		textfieldMaBN.setText((String)table.getValueAt(SelectedRow,mapping[1]));
		textfieldMaBenh.setText((String)table.getValueAt(SelectedRow,mapping[2]));
		textfieldMaPhong.setText((String)table.getValueAt(SelectedRow,mapping[8]));
		
		for (int i = 0; i < listMucDo.length; i++) {
			if (table.getValueAt(SelectedRow, mapping[3]) != null && ((String)table.getValueAt(SelectedRow, mapping[3])).equals(listMucDo[i])){
				comboBoxMucDo.setSelectedIndex(i);
				break;
			}
		}
		for (int i = 0; i < listTinhTrang.length; i++) {
			if (table.getValueAt(SelectedRow, mapping[7]) != null && ((String)table.getValueAt(SelectedRow, mapping[7])).equals(listTinhTrang[i])){
				comboBoxTinhTrang.setSelectedIndex(i);
				break;
			}
		}
		if ((String)table.getValueAt(SelectedRow,mapping[4]) != null) {
			switch ((String)table.getValueAt(SelectedRow,mapping[4])) {
			case "Tai gia":
				rbuttonTaiGia.setSelected(true);
				break;
			case "Nhap vien":
				rbuttonNhapVien.setSelected(true);
				break;
			case "Cach ly":
				rbuttonCachLy.setSelected(true);
				break;
			}
		}
		else {
			bgroupHinhThuc.clearSelection();
		} 

		if (table.getValueAt(SelectedRow, mapping[5]) != null) {
			try {
				NgayBatDau.setDate(new SimpleDateFormat("dd/MM/yyyy").parse((String)table.getValueAt(SelectedRow, mapping[5])));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SpinnerDateModel modelNgayBatDau = new SpinnerDateModel();
            try {
            	modelNgayBatDau.setValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String)table.getValueAt(SelectedRow, mapping[5])));
            } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            }
	            spinnerNgayBatDau.setModel(modelNgayBatDau);
	            JSpinner.DateEditor editorNgayBatDau = new JSpinner.DateEditor(spinnerNgayBatDau, "HH:mm:ss");
	            spinnerNgayBatDau.setEditor(editorNgayBatDau);
	            DateFormatter formatterNgayBatDau = (DateFormatter)editorNgayBatDau.getTextField().getFormatter();
	            formatterNgayBatDau.setAllowsInvalid(false);
	            formatterNgayBatDau.setOverwriteMode(true);
		}
		if (table.getValueAt(SelectedRow, mapping[6]) != null) {
			try {
				NgayKetThuc.setDate(new SimpleDateFormat("dd/MM/yyyy").parse((String)table.getValueAt(SelectedRow, mapping[6])));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SpinnerDateModel modelNgayKetThuc = new SpinnerDateModel();
			try {
				modelNgayKetThuc.setValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String)table.getValueAt(SelectedRow, mapping[6])));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			spinnerNgayKetThuc.setModel(modelNgayKetThuc);
            JSpinner.DateEditor editorNgayKetThuc = new JSpinner.DateEditor(spinnerNgayKetThuc, "HH:mm:ss");
            spinnerNgayKetThuc.setEditor(editorNgayKetThuc);
            DateFormatter formatterNgayKetThuc = (DateFormatter)editorNgayKetThuc.getTextField().getFormatter();
            formatterNgayKetThuc.setAllowsInvalid(false);
            formatterNgayKetThuc.setOverwriteMode(true);
		}
			textfieldMaCa.setBackground(new Color(210, 210, 210));
		    textfieldMaCa.setEditable(false);
		    textfieldMaBN.setBackground(new Color(210, 210, 210));
		    textfieldMaBN.setEditable(false);
		    textfieldMaBenh.setBackground(new Color(210, 210, 210));
		    textfieldMaBenh.setEditable(false);
		    NgayBatDau.setBackground(new Color(210, 210, 210));
	        ((JTextFieldDateEditor) NgayBatDau.getDateEditor()).setEditable(false);
	        NgayBatDau.getCalendarButton().setEnabled(false);
	        spinnerNgayBatDau.getComponent(0).setEnabled(false);
	        spinnerNgayBatDau.getComponent(1).setEnabled(false);
	        ((DefaultEditor) spinnerNgayBatDau.getEditor()).getTextField().setEditable(false);
	        
	        if (comboBoxTinhTrang.getItemAt(comboBoxTinhTrang.getSelectedIndex()).equals("Da ket thuc")) {
	        	setDisableAll();
	        }
	        buttonChonBN.setVisible(false);
	        buttonChonBE.setVisible(false);
}
	
	public void CapNhat() {
		
			String stringHinhThuc = "";
			if (bgroupHinhThuc.getSelection() != null) {
				stringHinhThuc = bgroupHinhThuc.getSelection().getActionCommand();
			}
			String stringKetThuc = "";
			if (NgayKetThuc.getDate() != null) {
				Date date = NgayKetThuc.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String dateString = sdf.format(date);
				
				date = (Date)spinnerNgayKetThuc.getValue();
	            sdf = new SimpleDateFormat("HH:mm:ss");
	            String stringSpinnerNgayKetThuc = sdf.format(date);
	            
				stringKetThuc = dateString + " " + stringSpinnerNgayKetThuc;
			}	
			String stringMucDo = "";
			if (!(comboBoxMucDo.getItemAt(comboBoxMucDo.getSelectedIndex()).equals(""))) {
				stringMucDo = (String) comboBoxMucDo.getItemAt(comboBoxMucDo.getSelectedIndex());
			}
			String stringTinhTrang = "";
			if (!(comboBoxTinhTrang.getItemAt(comboBoxTinhTrang.getSelectedIndex()).equals(""))) {
				stringTinhTrang = (String) comboBoxTinhTrang.getItemAt(comboBoxTinhTrang.getSelectedIndex());
			}
				

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_cabenh_sua1cabenh_theobacsi (?,?,?,?,?,?,?,?) }");
			
			statement.setString(1,MyHome.getID());
			statement.setString(2,textfieldMaCa.getText());
			statement.setString(3,stringMucDo);
			statement.setString(4,stringHinhThuc);
			statement.setString(5,stringKetThuc);
			statement.setString(6,stringTinhTrang);
			statement.setString(7,textfieldMaPhong.getText());
			statement.registerOutParameter(8,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(8);
			if (changedrows > 0) {
				JOptionPane.showMessageDialog(null, "Cap Nhat Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
				/*if (comboBoxTinhTrang.getItemAt(comboBoxTinhTrang.getSelectedIndex()).equals("Da ket thuc")) {
					setDisableAll();
				}*/
			}
			
			statement.close();
			
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			/*if (comboBoxTinhTrang.getItemAt(comboBoxTinhTrang.getSelectedIndex()).equals("Da ket thuc")) {
				comboBoxTinhTrang.setSelectedIndex(TinhTrangTruoc2);
				TinhTrangTruoc = TinhTrangTruoc2;
			}*/
		}
		//Refresh();
	}

	
	public void Refresh() {
		textfieldMaCa.setText("");
		textfieldMaBN.setText("");
		textfieldMaBenh.setText("");
		textfieldMaPhong.setText("");
		bgroupHinhThuc.clearSelection();
		NgayBatDau.setDate(null);
		NgayKetThuc.setDate(null);
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
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
	            case "Ma Ca":
	                mapping[0] = i;
	                break;
                case "Ma BN":
                    mapping[1] = i;
                    break;
                case "Ma Benh":
                    mapping[2] = i;
                    break;
                case "Muc Do":
                    mapping[3] = i;
                    break;
                case "Hinh Thuc":
                    mapping[4] = i;
                    break;
                case "Bat Dau":
                    mapping[5] = i;
                    break;
                case "Ket Thuc":
                    mapping[6] = i;
                    break;
                case "Tinh Trang":
                    mapping[7] = i;
                    break;
                case "Ma Phong":
                    mapping[8] = i;
                    break;
            }
        }
	return mapping;
	}
	
	public void setDisableAll() {

		comboBoxTinhTrang.setSelectedIndex(5);
		textfieldMaCa.setBackground(new Color(210, 210, 210));
	    textfieldMaCa.setEditable(false);
	    textfieldMaBN.setBackground(new Color(210, 210, 210));
	    textfieldMaBN.setEditable(false);
	    textfieldMaBenh.setBackground(new Color(210, 210, 210));
	    textfieldMaBenh.setEditable(false);
	    textfieldMaPhong.setBackground(new Color(210, 210, 210));
	    textfieldMaPhong.setEditable(false);
	    NgayBatDau.setBackground(new Color(210, 210, 210));
        ((JTextFieldDateEditor) NgayBatDau.getDateEditor()).setEditable(false);
        NgayBatDau.getCalendarButton().setEnabled(false);
        spinnerNgayBatDau.getComponent(0).setEnabled(false);
        spinnerNgayBatDau.getComponent(1).setEnabled(false);
        ((DefaultEditor) spinnerNgayBatDau.getEditor()).getTextField().setEditable(false);
        NgayKetThuc.setBackground(new Color(210, 210, 210));
        ((JTextFieldDateEditor) NgayKetThuc.getDateEditor()).setEditable(false);
        NgayKetThuc.getCalendarButton().setEnabled(false);
        spinnerNgayKetThuc.getComponent(0).setEnabled(false);
        spinnerNgayKetThuc.getComponent(1).setEnabled(false);
        ((DefaultEditor) spinnerNgayKetThuc.getEditor()).getTextField().setEditable(false);
        rbuttonTaiGia.setEnabled(false);
        rbuttonNhapVien.setEnabled(false);
        rbuttonCachLy.setEnabled(false);
        comboBoxTinhTrang.disable();
        comboBoxMucDo.disable();
        buttonChonBN.setVisible(false);
        buttonChonBE.setVisible(false);
        buttonChonPH.setVisible(false);
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
