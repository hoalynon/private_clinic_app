package BenhNhan;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import Chung.ButtonRound;
import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.*;


public class HoaDon implements KeyListener, ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private BNHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonTaiMau;

	private TextFieldRound textfieldChiTietTraCuu;
	
	private JTable table;
	
	HoaDon(BNHome MyHome){
		
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
		
		JLabel labelHeading = new JLabel("DANH SACH HOA DON");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
        
        panel0.add(MyHome.getConfirmChatbot(), BorderLayout.EAST);
        
        JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (80,80));
		panel2.setLayout(new FlowLayout(FlowLayout.TRAILING,50,20));
		panelContent.add(panel2, BorderLayout.SOUTH);
		
		buttonTaiMau = new ButtonRound();
		buttonTaiMau.setText(" Tai Hoa Don ");
		buttonTaiMau.setForeground(buttonfrontcolor);
		buttonTaiMau.setBackground(buttonbackcolor);
		buttonTaiMau.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonTaiMau.addActionListener(this);
		buttonTaiMau.setBorderPainted(false);
		panel2.add(buttonTaiMau);

		
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
					false, false, false, false, false, false, false
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
		if (e.getSource() == buttonTaiMau) {
			TaiHoaDon();
			
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
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_hoadonvienphi_layhoadonvienphi_theobenhnhan(?,?) }");
			statement.setString(1,MyHome.getID());
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data = (DefaultTableModel) table.getModel();
			
			data.setColumnIdentifiers(new String[] {"Ma HD","Ma Ca","Ngay Lap","Tong Tien","Tien Thuoc","Tien Dieu Tri","Trang Thai"});
			data.setRowCount(0);
			
			while(resultset.next()) {
				Vector row = new Vector();
				for (int i = 0; i < 7; i++)
					if (i == 6) {
						if (resultset.getInt(7) == 1)
							row.add("Da thanh toan");
						else
							row.add("Chua thanh toan");
					}
					else
						row.add(resultset.getString(i+1));
				data.addRow(row);
			} 
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
}
	public void TaiHoaDon() {
		int[] mapping = getMapping();
		
		if ((table.getRowCount() == 1 && !textfieldChiTietTraCuu.getText().equals("")) || table.getSelectedRowCount() == 1) {
			if ( table.getSelectedRowCount() == 1) {
				if ((String) table.getValueAt(0, mapping[6]) == "Chua thanh toan") {
					JOptionPane.showMessageDialog(null, "Khong The In Hoa Don Chua Thanh Toan", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					XuatHD((String) table.getValueAt(0, mapping[0]));
				}
			}
			else {
				if ((String) table.getValueAt(0, mapping[6]) == "Chua thanh toan") {
					JOptionPane.showMessageDialog(null, "Khong The In Hoa Don Chua Thanh Toan", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					XuatHD((String) table.getValueAt(0, mapping[0]));
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Khong The In Nhieu Hoa Don Cung Luc", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void XuatHD(String MaHD) {
		String path = "";
		JFileChooser j = new JFileChooser();
		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int x = j.showSaveDialog(this.MyHome.getFrame());
		
		if (x == JFileChooser.APPROVE_OPTION)
		{
			path = j.getSelectedFile().getPath();
		}
		String TenBN = "", DiaChi = "", GioiTinh = "", Tuoi = "", NgayVaoVien = "", TenKhoa = "", TongTien = "", MaCa = ""; 
		
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_hoadonvienphi_layhoadonvienphi_theobenhnhan(?,?) }");
			statement.setString(1,MyHome.getID());
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			
			while(resultset.next()) {

				if (resultset.getString(1).equals(MaHD)) {
					TenBN = resultset.getString(10);
					DiaChi = resultset.getString(13);
					GioiTinh = resultset.getString(11);
					Tuoi = resultset.getString(12);
					NgayVaoVien = resultset.getString(9);
					TenKhoa = resultset.getString(14);
					TongTien = resultset.getString(4);
					MaCa = resultset.getString(2);
					System.out.println("hey");
					break;
				}
			} 
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(path + "//HoaDon.pdf"));
			document.open();
			
			document.add(new Phrase("         PHONG KHAM TU NHAN               HOA DON VIEN PHI             Ma Hoa Don: " + MaHD + "\n"));
			document.add(new Phrase("        HANG THUYEN, THU DUC                                                           Ma Ca: " + MaCa + "\n\n"));
			document.add(new Phrase("- Ho ten nguoi benh: " + TenBN + "                                       Tuoi: " + Tuoi + "    " + GioiTinh + "\n"));
			document.add(new Phrase("- Ma nguoi benh: " + MyHome.getID() + "                                                          Ngay vao vien: " + NgayVaoVien + "\n"));
			document.add(new Phrase("- Dia chi: " + DiaChi + "\n"));
			document.add(new Phrase("- Khoa dieu tri: " + TenKhoa + "\n"));
			document.add(new Phrase("- Ly do thu: Phi dieu tri va phi thuoc\n"));
			document.add(new Phrase("- So tien: " + TongTien + " vnd\n"));
			document.add(new Phrase("- Trang thai: Da thanh toan\n\n"));
			Paragraph paragraph = new Paragraph();
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			paragraph.add(new Phrase("         \n"));
			paragraph.add(new Phrase("Nhan vien xac nhan         \n"));
			paragraph.add(new Phrase("Hoang                   \n"));
			paragraph.add(new Phrase("Ho ten: Tran Minh Hoang      \n"));
			document.add(paragraph);
			
			document.close();
			
			JOptionPane.showMessageDialog(null, "Tai Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		}
	}

	public void TraCuu(String ChiTietTraCuu) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>((DefaultTableModel)table.getModel());
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(ChiTietTraCuu));
	}
	
	public int[] getMapping(){
        int[] mapping = new int[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++){
            switch(table.getColumnName(i)){
                case "Ma HD":
                    mapping[0] = i;
                    break;
                case "Ma Ca":
                    mapping[1] = i;
                    break;
                case "Ngay Lap":
                    mapping[2] = i;
                    break;
                case "Tong Tien":
                    mapping[3] = i;
                    break;
                case "Tien Thuoc":
                    mapping[4] = i;
                    break;
                case "Tien Dieu Tri":
                    mapping[5] = i;
                    break;
                case "Trang Thai":
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
