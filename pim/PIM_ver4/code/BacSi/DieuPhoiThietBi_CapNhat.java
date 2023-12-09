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

public class DieuPhoiThietBi_CapNhat implements ActionListener, KeyListener, MouseListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private BSHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonCapNhat;
	
	private TextFieldRound textfieldMaCa;
	private TextFieldRound textfieldMaThietBi;
	private TextFieldRound textfieldSoLuong;
	
	private JDateChooser NgayDieuPhoi;
	private JDateChooser NgayKetThuc;
	
	private JSpinner spinnerNgayDieuPhoi;
	private JSpinner spinnerNgayKetThuc;
	
	private JTable table;
	
	private JFrame frameChonTB;

	private ButtonRound buttonChonTB;

	private JTable tableChonTB;
	
	DieuPhoiThietBi_CapNhat(BSHome MyHome, JTable table){
		
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

		JLabel labelHeading = new JLabel("CAP NHAT DIEU PHOI THIET BI");
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
		
		buttonCapNhat = new ButtonRound();
		buttonCapNhat.setText("  Cap Nhat  ");
		buttonCapNhat.setForeground(Color.decode("#28526a"));
		buttonCapNhat.setBackground(Color.decode("#91B6C9"));
		buttonCapNhat.addActionListener(this);
		buttonCapNhat.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonCapNhat.setBounds(80, 30, 85, 21);
		buttonCapNhat.setBorderPainted(false);
		panel2.add(buttonCapNhat);
		
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
		
		if (table != null && table.getSelectedRowCount() == 1) {
			setInformation(table.getSelectedRow());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonCapNhat || e.getSource() == MyHome.getButtonBack()) {
			if (frameChonTB != null) {
				frameChonTB.dispose();
			}
			CapNhat();
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
	
	public void setInformation(int SelectedRow) {
        
	        int [] mapping = getMapping();
	        
		textfieldMaCa.setText((String)table.getValueAt(SelectedRow,mapping[0]));
		textfieldMaThietBi.setText((String)table.getValueAt(SelectedRow,mapping[1]));
		textfieldSoLuong.setText((String)table.getValueAt(SelectedRow,mapping[2]));
	
		if (table.getValueAt(SelectedRow, mapping[3]) != null) {
			try {
				NgayDieuPhoi.setDate(new SimpleDateFormat("dd/MM/yyyy").parse((String)table.getValueAt(SelectedRow, mapping[3])));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SpinnerDateModel modelNgayDieuPhoi = new SpinnerDateModel();
            try {
            	modelNgayDieuPhoi.setValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String)table.getValueAt(SelectedRow, mapping[3])));
            } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            }
	            spinnerNgayDieuPhoi.setModel(modelNgayDieuPhoi);
	            JSpinner.DateEditor editorNgayDieuPhoi = new JSpinner.DateEditor(spinnerNgayDieuPhoi, "HH:mm:ss");
	            spinnerNgayDieuPhoi.setEditor(editorNgayDieuPhoi);
	            DateFormatter formatterNgayDieuPhoi = (DateFormatter)editorNgayDieuPhoi.getTextField().getFormatter();
	            formatterNgayDieuPhoi.setAllowsInvalid(false);
	            formatterNgayDieuPhoi.setOverwriteMode(true);
		}
		if (table.getValueAt(SelectedRow, mapping[4]) != null) {
			try {
				NgayKetThuc.setDate(new SimpleDateFormat("dd/MM/yyyy").parse((String)table.getValueAt(SelectedRow, mapping[4])));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SpinnerDateModel modelNgayKetThuc = new SpinnerDateModel();
			try {
				modelNgayKetThuc.setValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String)table.getValueAt(SelectedRow, mapping[4])));
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
	        textfieldMaThietBi.setBackground(new Color(210, 210, 210));
	        textfieldMaThietBi.setEditable(false);
	       /* textfieldSoLuong.setBackground(new Color(210, 210, 210));
	        textfieldSoLuong.setEditable(false);*/
	        NgayDieuPhoi.setBackground(new Color(210, 210, 210));
	        ((JTextFieldDateEditor) NgayDieuPhoi.getDateEditor()).setEditable(false);
	        NgayDieuPhoi.getCalendarButton().setEnabled(false);
	        spinnerNgayDieuPhoi.getComponent(0).setEnabled(false);
	        spinnerNgayDieuPhoi.getComponent(1).setEnabled(false);
	        ((DefaultEditor) spinnerNgayDieuPhoi.getEditor()).getTextField().setEditable(false);
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
	
	
	public void CapNhat() {
		

			if (textfieldSoLuong.getText().equals("")) {
				textfieldSoLuong.setText("0");
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
			String stringDieuPhoi = "";
			if (NgayDieuPhoi.getDate() != null) {
				Date date = NgayDieuPhoi.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String dateString = sdf.format(date);
				
				date = (Date)spinnerNgayDieuPhoi.getValue();
	            sdf = new SimpleDateFormat("HH:mm:ss");
	            String stringSpinnerNgayDieuPhoi = sdf.format(date);
	            
				stringDieuPhoi = dateString + " " + stringSpinnerNgayDieuPhoi;
			}

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_dieuphoithietbi_sua1dieuphoithietbi_theobacsi (?,?,?,?,?,?,?) }");
			
			statement.setString(1,MyHome.getID());
			statement.setString(2,textfieldMaCa.getText());
			statement.setString(3,textfieldMaThietBi.getText());
			statement.setString(4,textfieldSoLuong.getText());
			statement.setString(5,stringDieuPhoi);
			statement.setString(6,stringKetThuc);
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
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
                case "Ma Ca":
                    mapping[0] = i;
                    break;
                case "Ma Thiet Bi":
                    mapping[1] = i;
                    break;
                case "So Luong":
                    mapping[2] = i;
                    break;
                case "Dieu Phoi":
                    mapping[3] = i;
                    break;
                case "Ket Thuc":
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
