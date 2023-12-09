package BacSi;



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

public class CaBenh_KeThuoc implements ActionListener, KeyListener, MouseListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private BSHome MyHome;
	private JPanel panelContent;
	private JPanel panel1;
	private ButtonRound buttonThem;
	
	private TextFieldRound textfieldMaCa;
	private TextFieldRound textfieldMaThuoc;
	private TextFieldRound textfieldSoLuong;
	
	private JDateChooser NgayKe;
	private JSpinner spinnerNgayKe;
	
	private JFrame frameChonThuoc;
	private ButtonRound buttonChonThuoc;

	private JTable table;
	private JTable tableChonThuoc;
	private JTable tableThuocDaCo;
	
	private JScrollPane scrollpaneThuocDaCo;


	CaBenh_KeThuoc(BSHome MyHome, JTable table){
		
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
		
		panel1 = new JPanel();
		panel1.setBackground(textbackcolor);
		panel1.setPreferredSize(new Dimension (100,250));
		panel1.setLayout(null);
		panelContent.add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (80,80));
		panel2.setLayout(new FlowLayout(FlowLayout.TRAILING,50,20));
		panelContent.add(panel2, BorderLayout.SOUTH);
		
		JLabel labelHeading = new JLabel("THEM MOI BANG KE THUOC");
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
		textfieldMaCa.setText((String)table.getValueAt(table.getSelectedRow(),getMapping()[0]));
		textfieldMaCa.setBackground(new Color(210, 210, 210));
	    textfieldMaCa.setEditable(false);
		panel1.add(textfieldMaCa);
		
		JLabel labelMaThuoc = new JLabel("Ma Thuoc : ");
		labelMaThuoc.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaThuoc.setBounds(30, 70, 115, 20);
		labelMaThuoc.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaThuoc);
		
		textfieldMaThuoc = new TextFieldRound();
		textfieldMaThuoc.setBounds(150, 70, 115, 20);
		textfieldMaThuoc.setColumns(10);
		panel1.add(textfieldMaThuoc);
		
		JLabel labelSoLuong = new JLabel("So Luong : ");
		labelSoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSoLuong.setFont(new Font("Bevan", Font.BOLD, 12));
		labelSoLuong.setBounds(30, 110, 115, 20);
		panel1.add(labelSoLuong);
		
		textfieldSoLuong = new TextFieldRound();
		textfieldSoLuong.setColumns(10);
		textfieldSoLuong.setBounds(150, 110, 115, 20);
		panel1.add(textfieldSoLuong);
		
		JLabel labelNgayKe = new JLabel("Ngay Ke Thuoc: ");
		labelNgayKe.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayKe.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayKe.setBounds(30, 150, 115, 20);
		panel1.add(labelNgayKe);
		
		NgayKe = new JDateChooser();
		NgayKe.setBounds(150, 150, 115, 20);
		NgayKe.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgayKe.setDateFormatString("dd/MM/yyyy");
		NgayKe.setDate(Calendar.getInstance().getTime());
		panel1.add(NgayKe);
		
		SpinnerDateModel modelNgayKe = new SpinnerDateModel();
        modelNgayKe.setValue(Calendar.getInstance().getTime());
        spinnerNgayKe = new JSpinner(modelNgayKe);
        JSpinner.DateEditor editorNgayKe = new JSpinner.DateEditor(spinnerNgayKe, "HH:mm:ss");
        spinnerNgayKe.setEditor(editorNgayKe);
        spinnerNgayKe.setBounds(270, 150, 80, 20);
        panel1.add(spinnerNgayKe);
        DateFormatter formatterNgayKe = (DateFormatter)editorNgayKe.getTextField().getFormatter();
        formatterNgayKe.setAllowsInvalid(false);
        formatterNgayKe.setOverwriteMode(true);
        
        JLabel labelDSThuocDaCo = new JLabel("DS Thuoc Da Ke: ");
        labelDSThuocDaCo .setHorizontalAlignment(SwingConstants.LEFT);
        labelDSThuocDaCo .setFont(new Font("Bevan", Font.BOLD, 12));
        labelDSThuocDaCo .setBounds(400, 30, 115, 20);
		panel1.add(labelDSThuocDaCo );
        
        scrollpaneThuocDaCo = new JScrollPane();
        scrollpaneThuocDaCo.setBounds(400, 60, 150, 100);
		panel1.add(scrollpaneThuocDaCo);
		CapNhatThuocDaCo();
		
		buttonThem = new ButtonRound();
		buttonThem.setText("    Them    ");
		buttonThem.setForeground(buttonfrontcolor);
		buttonThem.setBackground(buttonbackcolor);
		buttonThem.addActionListener(this);
		buttonThem.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonThem.setBounds(80, 30, 85, 21);
		buttonThem.setBorderPainted(false);
		panel2.add(buttonThem);
		
		buttonChonThuoc = new ButtonRound();
		buttonChonThuoc.setIcon(new ImageIcon(getClass().getClassLoader().getResource("drugicon.png")));
		buttonChonThuoc.setForeground(new Color(40, 82, 106));
		buttonChonThuoc.setBackground(new Color(200, 210, 230));
		buttonChonThuoc.addActionListener(this);
		buttonChonThuoc.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonThuoc.setBounds(270, 70, 30, 20);
		buttonChonThuoc.setBorderPainted(false);
		panel1.add(buttonChonThuoc);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == buttonThem || e.getSource() == MyHome.getButtonBack()) {
				frameChonThuoc.dispose();
			Them();
			CapNhatThuocDaCo();
		}
		if (e.getSource() == buttonChonThuoc) {
        	if (frameChonThuoc != null) {
				frameChonThuoc.dispose();
			} 	
			ChonThuoc();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() == tableChonThuoc) {
			textfieldMaThuoc.setText((String) tableChonThuoc.getValueAt(tableChonThuoc.getSelectedRow(),0));
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
		if (e.getSource() == tableChonThuoc) {
			textfieldMaThuoc.setText((String) tableChonThuoc.getValueAt(tableChonThuoc.getSelectedRow(),0));
		}
	}
	
	public void ChonThuoc() {
		frameChonThuoc = new JFrame("Danh sach cac loai thuoc");
		frameChonThuoc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameChonThuoc.setSize(300,200);
		frameChonThuoc.setLocationRelativeTo(buttonChonThuoc);
		frameChonThuoc.setLocation(frameChonThuoc.getX() + 175, frameChonThuoc.getY() + 50);
		frameChonThuoc.setBackground(new Color(214, 231, 239));
		frameChonThuoc.setLayout(new BorderLayout());
		frameChonThuoc.addMouseListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension (400, 200));
		frameChonThuoc.add(scrollPane,BorderLayout.CENTER);
		
		tableChonThuoc = new JTable() {
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
			tableChonThuoc.setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonThuoc.getTableHeader().setFont(new Font("Bevan", Font.PLAIN, 14));
			tableChonThuoc.setBackground(new Color(255, 255, 255));
			tableChonThuoc.setGridColor(new Color(235, 235, 235));
			tableChonThuoc.setRowHeight(20);
			tableChonThuoc.addMouseListener(this);
			tableChonThuoc.addKeyListener(this);
			tableChonThuoc.getTableHeader().setReorderingAllowed(false);
		
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thuoc_laythuoc (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) tableChonThuoc.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ma Thuoc","Ten Thuoc","SL Con Lai"});
			data.setRowCount(0);
			
			tableChonThuoc.getColumnModel().getColumn(0).setPreferredWidth(75);
            tableChonThuoc.getColumnModel().getColumn(1).setPreferredWidth(250);
            tableChonThuoc.getColumnModel().getColumn(2).setPreferredWidth(75);
			
			while(resultset.next()) {
				Vector row = new Vector();
				for (int i = 0; i < columnCount; i++) {
					if (i == 0 || i == 1 || i == 3) {
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
		
		scrollPane.setViewportView(tableChonThuoc);
		
		frameChonThuoc.setVisible(true);
	}
	
	public void CapNhatThuocDaCo() {
		
		tableThuocDaCo = new JTable() {
			boolean[] columnEditables = new boolean[] {
                                        false, false
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
			tableThuocDaCo.setFont(new Font("Bevan", Font.PLAIN, 14));
			tableThuocDaCo.getTableHeader().setFont(new Font("Bevan", Font.PLAIN, 14));
			tableThuocDaCo.setBackground(new Color(255, 255, 255));
			tableThuocDaCo.setGridColor(new Color(235, 235, 235));
			tableThuocDaCo.setRowHeight(20);
			tableThuocDaCo.getTableHeader().setReorderingAllowed(false);
			
			try {
				Connection connection = MyHome.getDatabase().getConnection();
				CallableStatement statement = connection.prepareCall("{ call rootdata.proc_kethuoc_laykethuoc_theoca (?,?) }");
				statement.setString(1,textfieldMaCa.getText());
				statement.registerOutParameter(2, OracleTypes.CURSOR);
				statement.execute();
				ResultSet resultset = (ResultSet) statement.getObject(2);
				ResultSetMetaData metadata = resultset.getMetaData();
				int columnCount = metadata.getColumnCount();
				DefaultTableModel data = (DefaultTableModel) tableThuocDaCo.getModel();
				
				data.setColumnIdentifiers(new String[] {"Ten","SL"});
				data.setRowCount(0);
				
				while(resultset.next()) {
					Vector row = new Vector();
					row.add(resultset.getString(1));
					row.add(resultset.getString(2));
					data.addRow(row);
				} 
				statement.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		
		scrollpaneThuocDaCo.setViewportView(tableThuocDaCo);
		panel1.repaint();
	}
	
	public void Them() {
		
		if (textfieldSoLuong.getText().equals("")) {
			textfieldSoLuong.setText("0");
		}
		
		String stringNgayKe = "", stringSpinnerNgayKe = "", stringNgayKeTong = "NULL";;
		if (NgayKe.getDate() != null) {
			Date date = NgayKe.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			stringNgayKe = sdf.format(date);
			
			date = (Date)spinnerNgayKe.getValue();
            sdf = new SimpleDateFormat("HH:mm:ss");
            stringSpinnerNgayKe = sdf.format(date);
            stringNgayKeTong = stringNgayKe + " " + stringSpinnerNgayKe;
		}

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_kethuoc_them1kethuoc (?,?,?,?,?) }");
			statement.setString(1,textfieldMaCa.getText());
			statement.setString(2,textfieldMaThuoc.getText());
			statement.setInt(4,Integer.parseInt(textfieldSoLuong.getText()));
			statement.setString(3,stringNgayKeTong);
			statement.registerOutParameter(5,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(5);
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
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
