package QuanLy;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Chung.ButtonRound;
import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.*;


public class TaiKhoan implements ActionListener, KeyListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;

	private ButtonRound buttonThem;
	private ButtonRound buttonCapNhat;
	private ButtonRound buttonXoa;
	
	private TextFieldRound textfieldChiTietTraCuu;
	
	private JTable table;
	
	TaiKhoan(QLHome MyHome){
		
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
		panel0.setPreferredSize(new Dimension (160,110));
		panel0.setLayout(new BorderLayout());
		panelContent.add(panel0,BorderLayout.NORTH);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(textbackcolor);
		panel1.setPreferredSize(new Dimension (80,60));
		panel1.setLayout(null);
		panel0.add(panel1,BorderLayout.SOUTH);
		
		panel0.add(MyHome.getConfirmChatbot(), BorderLayout.EAST);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (80,80));
		panel2.setLayout(new FlowLayout(FlowLayout.TRAILING,50,20));
		panelContent.add(panel2, BorderLayout.SOUTH);
		
		JLabel labelHeading = new JLabel("DANH SACH TAI KHOAN");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
		JLabel labelChiTietTraCuu = new JLabel("Tim kiem : ");
		labelChiTietTraCuu.setHorizontalAlignment(SwingConstants.RIGHT);
		labelChiTietTraCuu.setFont(new Font("Bevan", Font.BOLD, 12));
		labelChiTietTraCuu.setBounds(50, 10, 60, 20);
		panel1.add(labelChiTietTraCuu);
		
		textfieldChiTietTraCuu = new TextFieldRound();
		textfieldChiTietTraCuu.setColumns(10);
		textfieldChiTietTraCuu.setBounds(120, 10, 400, 20);
		textfieldChiTietTraCuu.addKeyListener(this);
		panel1.add(textfieldChiTietTraCuu);
		
		buttonThem = new ButtonRound();
		buttonThem.setText("    Them    ");
		buttonThem.setForeground(buttonfrontcolor);
		buttonThem.setBackground(buttonbackcolor);
		buttonThem.addActionListener(this);
		buttonThem.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonThem.setBounds(180, 30, 85, 21);
		buttonThem.setBorderPainted(false);
		panel2.add(buttonThem);
		
		buttonCapNhat = new ButtonRound();
		buttonCapNhat.setText("  Cap Nhat  ");
		buttonCapNhat.setForeground(buttonfrontcolor);
		buttonCapNhat.setBackground(buttonbackcolor);
		buttonCapNhat.addActionListener(this);
		buttonCapNhat.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonCapNhat.setBounds(310, 30, 85, 21);
		buttonCapNhat.setBorderPainted(false);
		panel2.add(buttonCapNhat);
		
		buttonXoa = new ButtonRound();
		buttonXoa.setText("    Xoa    ");
		buttonXoa.setForeground(buttonfrontcolor);
		buttonXoa.setBackground(buttonbackcolor);
		buttonXoa.addActionListener(this);
		buttonXoa.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonXoa.setBounds(440, 30, 97, 21);
		buttonXoa.setBorderPainted(false);
		panel2.add(buttonXoa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension (100, 100));
		panelContent.add(scrollPane,BorderLayout.CENTER);
		
		table = new JTable() {
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
	            	comp.setBackground(Color.decode("#02063d"));
	            	comp.setForeground(Color.white);
	            }
	            else if (row % 2 == 0) {
	            	comp.setBackground(Color.decode("#d6e7ef"));
	            	comp.setForeground(Color.black);
	            }
	            else {
	            	comp.setBackground(Color.decode("#abc5d1"));
	            	comp.setForeground(Color.black);
	            }
	            return comp;
			}
		};
		table.setBackground(Color.decode("#d6e7ef"));
		table.setFont(new Font("Bevan", Font.PLAIN, 12));
		
		setInformation();
		scrollPane.setViewportView(table);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonThem) {
			MyHome.turnNext("TaiKhoan_Them", null);
		}
		else if (e.getSource() == buttonCapNhat) {
			MyHome.turnNext("TaiKhoan_CapNhat", table);
		}
		else if (e.getSource() == buttonXoa) {
			Xoa();
			setInformation();
		}
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
		if (e.getSource() == textfieldChiTietTraCuu) {
			TraCuu(textfieldChiTietTraCuu.getText());
		}
	}

public void setInformation() { 

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_taikhoan_laytaikhoan (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) table.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ten Dang Nhap","Mat Khau"});
			data.setRowCount(0);
			
			while(resultset.next()) {
				Vector row = new Vector();
				for (int i = 0; i < columnCount; i++)
					row.add(resultset.getString(i+1));
				data.addRow(row);
			} 
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
}
	
	public void TraCuu(String ChiTietTraCuu) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>((DefaultTableModel)table.getModel());
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(ChiTietTraCuu));
	}
	

	public void Xoa() {
		int[] mapping = getMapping();
		if ((table.getRowCount() > 0 && !textfieldChiTietTraCuu.getText().equals("")) || table.getSelectedRowCount() > 0) {
				if (table.getSelectedRowCount() > 0){
	        		int rowCount = table.getSelectedRowCount();
	                int selectedRows[] = table.getSelectedRows();
	                int changedrows = 0;
	                for (int i = 0; i < rowCount; i++){
	                    try {
	    					Connection connection = MyHome.getDatabase().getConnection();
	    					CallableStatement statement = connection.prepareCall("{ call rootdata.proc_taikhoan_xoa1taikhoan (?,?) }");
	    					statement.setString(1,(String) table.getValueAt(selectedRows[i], mapping[0]));
	    					statement.registerOutParameter(2,Types.INTEGER);
	    					
	    					statement.execute();
	    					changedrows +=(int) statement.getObject(2);	
	    					
	    				} catch (SQLException e) {
	    					JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
	    					e.printStackTrace();
	    					break;
	    				}
	                }
	                if (changedrows == rowCount) {
						JOptionPane.showMessageDialog(null, "Xoa Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
					}
	  
	            }
	            else {
	            	int changedrows = 0;
	                for (int i = 0; i < table.getRowCount(); i++) {
	                	try {
	    					Connection connection = MyHome.getDatabase().getConnection();
	    					CallableStatement statement = connection.prepareCall("{ call rootdata.proc_taikhoan_xoa1taikhoan (?,?)");
	    					statement.setString(1,(String) table.getValueAt(i, mapping[0]));
	    					
	    					statement.registerOutParameter(2,Types.INTEGER);
	    					
	    					statement.execute();
	    					changedrows +=(int) statement.getObject(2);	
	    					
	    				} catch (SQLException e) {
	    					JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
	    					e.printStackTrace();
	    					break;
	    				}
	                }
	                if (changedrows == table.getRowCount()) {
						JOptionPane.showMessageDialog(null, "Xoa Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
					}
	            }
			}
			setInformation();
			textfieldChiTietTraCuu.setText("");
			TraCuu(textfieldChiTietTraCuu.getText());
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
