package BenhNhan;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;

import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;


public class DieuPhoiThietBi implements KeyListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private BNHome MyHome;
	private JPanel panelContent;

	private TextFieldRound textfieldChiTietTraCuu;
	
	private JTable table;
	
	DieuPhoiThietBi(BNHome MyHome){
		
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
		
		JLabel labelHeading = new JLabel("DANH SACH DIEU PHOI THIET BI");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
        
        panel0.add(MyHome.getConfirmChatbot(), BorderLayout.EAST);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension (100, 100));
		panelContent.add(scrollPane,BorderLayout.CENTER);
		
		table = new JTable() {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
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
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_dieuphoithietbi_laydieuphoithietbi_theobenhnhan (?,?) }");
			statement.setString(1,MyHome.getID());
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) table.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ma Thiet Bi","Ten Thiet Bi","Cong Dung","So Luong","Dieu Phoi","Ket Thuc"});
			data.setRowCount(0);
			
			while(resultset.next()) {
				Vector row = new Vector();
				for (int i = 0; i < columnCount; i++) {
					if (i != 4 && i != 5) {
						row.add(resultset.getString(i+1));
	                }
	                else if (resultset.getDate(i+1) != null){
	                	row.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(resultset.getDate(i+1)));
	                }
	                else {
	                	row.add(null);
	                }
				}
				data.addRow(row);
			} 
			statement.close();
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

	
	public JPanel getpanelContent() {
		return panelContent;
	}
	
}
