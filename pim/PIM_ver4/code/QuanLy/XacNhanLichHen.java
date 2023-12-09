package QuanLy;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import Chung.ButtonRound;
import Chung.TextFieldRound;
import Connection.DatabaseConnector;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.*;

public class XacNhanLichHen implements ActionListener, KeyListener, MouseListener{
	
	private DatabaseConnector database;
	private JPanel panelContent;
	
	private Color textfrontcolor = Color.decode("#000000");
	private Color textbackcolor = Color.decode("#ffffff");
	private Color buttonfrontcolor = Color.decode("#ffffff");
	private Color buttonbackcolor = Color.decode("#1B3954");

	private ButtonRound buttonXacNhan;
	private ButtonRound buttonTuChoi;
	
	private TextFieldRound textfieldMaLich;
	private TextFieldRound textfieldMaBN;
	private TextFieldRound textfieldMaBS;
	private TextFieldRound textfieldNgayDuKien;
	private TextFieldRound textfieldNhuCauKham;

	private JFrame frameChonBS;
	private ButtonRound buttonChonBS;
	private JTable tableChonBS;
	
	private JTable table2, table3;
	
	XacNhanLichHen(DatabaseConnector database, JTable table2, JTable table3){
		
		this.database = database;
		this.table2 = table2;
		this.table3 = table3;
		
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

		JLabel labelHeading = new JLabel("XAC NHAN LICH HEN");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
        
        JLabel labelMaLich = new JLabel("Ma Ca : ");
		labelMaLich.setFont(new Font("Bevan", Font.BOLD, 12));
		labelMaLich.setBounds(30, 0, 115, 20);
		labelMaLich.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelMaLich);
		
		textfieldMaLich = new TextFieldRound();
		textfieldMaLich.setBounds(150, 0, 115, 20);
		textfieldMaLich.setColumns(10);
		panel1.add(textfieldMaLich);
		
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
		
		JLabel labelNgayDuKien = new JLabel("Ngay Du Kien : ");
		labelNgayDuKien.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayDuKien.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayDuKien.setBounds(30, 120, 115, 20);
		panel1.add(labelNgayDuKien);
		
		textfieldNgayDuKien = new TextFieldRound();
		textfieldNgayDuKien.setColumns(10);
		textfieldNgayDuKien.setBounds(150, 120, 115, 20);
		panel1.add(textfieldNgayDuKien);
		
		JLabel labelNhuCauKham = new JLabel("Nhu Cau Kham : ");
		labelNhuCauKham.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNhuCauKham.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNhuCauKham.setBounds(30, 160, 115, 20);
		panel1.add(labelNhuCauKham);
		
		textfieldNhuCauKham = new TextFieldRound();
		textfieldNhuCauKham.setColumns(10);
		textfieldNhuCauKham.setBounds(150, 160, 115, 20);
		panel1.add(textfieldNhuCauKham);
		
		buttonXacNhan = new ButtonRound();
		buttonXacNhan.setText("Xac Nhan");
		buttonXacNhan.setForeground(buttonfrontcolor);
		buttonXacNhan.setBackground(buttonbackcolor);
		buttonXacNhan.addActionListener(this);
		buttonXacNhan.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonXacNhan.setBorderPainted(false);
		panel2.add(buttonXacNhan);
		
		buttonTuChoi = new ButtonRound();
		buttonTuChoi.setText("Tu Choi");
		buttonTuChoi.setForeground(buttonfrontcolor);
		buttonTuChoi.setBackground(buttonbackcolor);
		buttonTuChoi.addActionListener(this);
		buttonTuChoi.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonTuChoi.setBorderPainted(false);
		panel2.add(buttonTuChoi);
		
		buttonChonBS = new ButtonRound();
		buttonChonBS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("doctor.png")));
		buttonChonBS.setForeground(new Color(40, 82, 106));
		buttonChonBS.setBackground(new Color(200, 210, 230));
		buttonChonBS.addActionListener(this);
		buttonChonBS.setFont(new Font("Bevan", Font.PLAIN, 18));
		buttonChonBS.setBounds(270, 80, 30, 20);
		buttonChonBS.setBorderPainted(false);
		panel1.add(buttonChonBS);

		setInformation(table2.getSelectedRow());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonXacNhan || e.getSource() == buttonTuChoi) {

			if (frameChonBS != null) {
				frameChonBS.dispose();
			}
			if (e.getSource() == buttonXacNhan) {
				XacNhan(1);
			}
			else {
				textfieldMaBS.setText("");
				XacNhan(2);
			}
			
		}
        if (e.getSource() == buttonChonBS) {
        	if (frameChonBS != null) {
				frameChonBS.dispose();
			} 	
			ChonBS();
		}
	}
	
	 @Override
		public void mouseClicked(MouseEvent e) {
		 	if (e.getSource() == tableChonBS) {
				textfieldMaBS.setText((String) tableChonBS.getValueAt(tableChonBS.getSelectedRow(),0));
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
			if (e.getSource() == tableChonBS) {
				textfieldMaBS.setText((String) tableChonBS.getValueAt(tableChonBS.getSelectedRow(),0));
			}
		}
		
		public void ChonBS() {
			frameChonBS = new JFrame("Danh sach cac bac si");
			frameChonBS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameChonBS.setSize(300,200);
			frameChonBS.setLocationRelativeTo(buttonChonBS);
			frameChonBS.setLocation(frameChonBS.getX() + 175, frameChonBS.getY() + 50);
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
				Connection connection = database.getConnection();
				CallableStatement statement = connection.prepareCall("{ call rootdata.proc_bacsi_laybacsi (?) }");
				statement.registerOutParameter(1, OracleTypes.CURSOR);
				statement.execute();
				ResultSet resultset = (ResultSet) statement.getObject(1);
				ResultSetMetaData metadata = resultset.getMetaData();
				int columnCount = metadata.getColumnCount();
				DefaultTableModel data = (DefaultTableModel) tableChonBS.getModel();
				
				data.setColumnIdentifiers(new String[] {"Ma BS","Ten BS","Khoa"});
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
	
	
	public void setInformation(int SelectedRow) {
		
		textfieldMaLich.setText("");
		textfieldMaBN.setText("");
		textfieldMaBS.setText("");
		textfieldNgayDuKien.setText("");
		textfieldNhuCauKham.setText("");
      
		System.out.println((String)table3.getValueAt(SelectedRow,0));
	    textfieldMaLich.setText((String)table3.getValueAt(SelectedRow,0));
		textfieldMaBN.setText((String)table3.getValueAt(SelectedRow,1));
		textfieldMaBS.setText((String)table3.getValueAt(SelectedRow,2));
		textfieldNgayDuKien.setText((String)table3.getValueAt(SelectedRow,3));
		textfieldNhuCauKham.setText((String)table3.getValueAt(SelectedRow,4));

	 	textfieldMaLich.setBackground(new Color(210, 210, 210));
	    textfieldMaLich.setEditable(false);
	    textfieldMaBN.setBackground(new Color(210, 210, 210));
	    textfieldMaBN.setEditable(false);
	    textfieldNgayDuKien.setBackground(new Color(210, 210, 210));
	    textfieldNgayDuKien.setEditable(false);
	    textfieldNgayDuKien.setBackground(new Color(210, 210, 210));
	    textfieldNgayDuKien.setEditable(false);
	    textfieldNhuCauKham.setBackground(new Color(210, 210, 210));
	    textfieldNhuCauKham.setEditable(false);
	}

	
	public void XacNhan(int xacnhan) {
		
		try {
			Connection connection = database.getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_lichhenkham_qlxacnhan (?,?,?,?) }");
			
			statement.setString(1,textfieldMaLich.getText());
			statement.setInt(2,xacnhan);
			statement.setString(3,textfieldMaBS.getText());
			statement.registerOutParameter(4,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(4);
			System.out.println(changedrows);
			if (changedrows > 0) {
				JOptionPane.showMessageDialog(null, "Cap Nhat Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
				if (frameChonBS != null) {
					frameChonBS.dispose();
				}
			}
			
			statement.close();
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
	
	public void setDisableAll() {

		textfieldMaLich.setBackground(new Color(210, 210, 210));
	    textfieldMaLich.setEditable(false);
	    textfieldMaBN.setBackground(new Color(210, 210, 210));
	    textfieldMaBN.setEditable(false);

	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
