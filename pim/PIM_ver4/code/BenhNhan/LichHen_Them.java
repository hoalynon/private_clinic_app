package BenhNhan;



import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DateFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import Chung.ButtonRound;
import Chung.GiayTo;
import Chung.TextFieldRound;
import oracle.jdbc.OracleTypes;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LichHen_Them implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private BNHome MyHome;
	private JPanel panelContent;
	private ButtonRound buttonThem;
	private ButtonRound buttonTaiMau;
	
	private TextFieldRound textfieldNhuCauKham;
	
	private JDateChooser NgayDuKien;
	private JSpinner spinnerNgayDuKien;

	private int maxRow;
	
	LichHen_Them(BNHome MyHome){

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
		
		JLabel labelHeading = new JLabel("THEM MOI LICH HEN KHAM");
        labelHeading.setFont(new Font("Bevan", Font.BOLD, 16));
        labelHeading.setForeground(textfrontcolor);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        panel0.add(labelHeading, BorderLayout.CENTER);
		
		JLabel labelNhuCauKham = new JLabel("Nhu Cau Kham : ");
		labelNhuCauKham.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNhuCauKham.setBounds(30, 30, 115, 20);
		labelNhuCauKham.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(labelNhuCauKham);
		
		textfieldNhuCauKham = new TextFieldRound();
		textfieldNhuCauKham.setBounds(150, 30, 115, 20);
		textfieldNhuCauKham.setColumns(10);
		panel1.add(textfieldNhuCauKham);
		
		JLabel labelNgayDuKien = new JLabel("Ngay Du Kien: ");
		labelNgayDuKien.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNgayDuKien.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNgayDuKien.setBounds(300, 30, 115, 20);
		panel1.add(labelNgayDuKien);
		
		NgayDuKien = new JDateChooser();
		NgayDuKien.setBounds(420, 30, 115, 20);
		NgayDuKien.setFont(new Font("Bevan", Font.PLAIN, 12));
		NgayDuKien.setDateFormatString("dd/MM/yyyy");
		NgayDuKien.setDate(Calendar.getInstance().getTime());
		panel1.add(NgayDuKien);
		
		SpinnerDateModel modelNgayDuKien = new SpinnerDateModel();
        modelNgayDuKien.setValue(Calendar.getInstance().getTime());
        spinnerNgayDuKien = new JSpinner(modelNgayDuKien);
        JSpinner.DateEditor editorNgayDuKien = new JSpinner.DateEditor(spinnerNgayDuKien, "HH:mm:ss");
        spinnerNgayDuKien.setEditor(editorNgayDuKien);
        spinnerNgayDuKien.setBounds(540, 30, 80, 20);
        panel1.add(spinnerNgayDuKien);
        DateFormatter formatterNgayDuKien = (DateFormatter)editorNgayDuKien.getTextField().getFormatter();
        formatterNgayDuKien.setAllowsInvalid(false);
        formatterNgayDuKien.setOverwriteMode(true);
		
		buttonThem = new ButtonRound();
		buttonThem.setText("    Them    ");
		buttonThem.setForeground(buttonfrontcolor);
		buttonThem.setBackground(buttonbackcolor);
		buttonThem.addActionListener(this);
		buttonThem.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonThem.setBounds(80, 30, 85, 21);
		buttonThem.setBorderPainted(false);
		panel2.add(buttonThem);
		
		buttonTaiMau = new ButtonRound();
		buttonTaiMau.setText(" Tai Mau Lich Hen ");
		buttonTaiMau.setForeground(buttonfrontcolor);
		buttonTaiMau.setBackground(buttonbackcolor);
		buttonTaiMau.setFont(new Font("Bevan", Font.BOLD, 12));
		//buttonTaiMau.setBounds(80, 30, 85, 21);
		buttonTaiMau.setBorderPainted(false);
		panel2.add(buttonTaiMau);
		
		xemMauLichHen();
		
		maxRow = getMaxRow();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == buttonThem) {
			Them();
		}
	}
	
	public void Them() {
		String stringNgayDuKien = "", stringSpinnerNgayDuKien = "", stringDuKien = "NULL";;
		if (NgayDuKien.getDate() != null) {
			Date date = NgayDuKien.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			stringNgayDuKien = sdf.format(date);
			
			date = (Date)spinnerNgayDuKien.getValue();
            sdf = new SimpleDateFormat("HH:mm:ss");
            stringSpinnerNgayDuKien = sdf.format(date);
            stringDuKien = stringNgayDuKien + " " + stringSpinnerNgayDuKien;
		}

		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_lichhenkham_them1lichhenkham (?,?,?,?,?) }");
			statement.setString(1,"LH" + MyHome.fillZero(maxRow, 3) + Integer.toString(maxRow));
			statement.setString(2,MyHome.getID());
			statement.setString(3,stringDuKien);
			statement.setString(4,textfieldNhuCauKham.getText());
			statement.registerOutParameter(5,Types.INTEGER);
			
			statement.execute();
			
			int changedrows = (int) statement.getObject(5);
			if (changedrows > 0) {
				JOptionPane.showMessageDialog(null, "Them Moi Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
				maxRow++;
			}
			
			statement.close();
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		//Refresh();
	}
	
	public void xemMauLichHen() {
		final Container glassPane = (Container)MyHome.getFrame().getGlassPane();
		glassPane.setLayout(null);
		glassPane.addMouseListener(new MouseAdapter() {});

		JFrame frame2 = new JFrame("Mau Lich Hen");
		frame2.setSize(800, 820);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setBackground(textbackcolor);
		frame2.setLayout(null);
		frame2.setResizable(false);
		frame2.setGlassPane(new JComponent() {
			@Override
			protected void paintComponent (Graphics g) {
				g.setColor(new Color(0,0,0,150));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		});
		
		
		final Container glassPane2 = (Container)frame2.getGlassPane();
		glassPane2.setLayout(null);
		glassPane2.addMouseListener(new MouseAdapter() {});
		
		
		
		buttonTaiMau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				glassPane.setVisible(true);
				frame2.setLocationRelativeTo(null);
				
				frame2.addWindowListener(new WindowAdapter() {
					@Override
				    public void windowClosing(WindowEvent windowEvent) {
						glassPane.setVisible(false);
					}
				});
				
				JPanel MauDK = (new GiayTo()).layMauDKKham();
				frame2.add(MauDK);
				
				ButtonRound buttonDownload = new ButtonRound();
				buttonDownload.setIcon(new ImageIcon(getClass().getClassLoader().getResource("download.jpg")));
				buttonDownload.setBackground(textbackcolor);
				buttonDownload.setBorderPainted(false);
				buttonDownload.setRoundAllCorner(60);
				buttonDownload.setBounds(370, 720, 60, 60);
				buttonDownload.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed (ActionEvent ed) {
						String path = "";
						JFileChooser j = new JFileChooser();
						j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int x = j.showSaveDialog(frame2);
						
						if (x == JFileChooser.APPROVE_OPTION)
						{
							path = j.getSelectedFile().getPath();
						}
						
						Document document = new Document();
						try {
							PdfWriter.getInstance(document, new FileOutputStream(path + "//MauDK.pdf"));
							document.open();
							
						
								//BaseFont bf = BaseFont.createFont("/pim/vuArial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
					
							//Font font = new Font(bf,15, Font.PLAIN);

							
							document.add(new Phrase("So Y te: ....................................... PHIEU DANG KY KHAM BENH                     42/BV-01\n"));
							document.add(new Phrase("Phong kham:......................................KHOA KHAM.......................................So vao kham.............\n\n"));
							document.add(new Phrase("I. HANH CHINH: \n"));
							document.add(new Phrase("1. Ho va Ten (In hoa):............................................ 2. Sinh ngay:....../....../......... Tuoi:...........\n"));
							document.add(new Phrase("3. Gioi:            1. Nam              2. Nu                      4. Nghe Nghiep:.....................................\n"));
							document.add(new Phrase("5. Dan toc:....................................................... 6. Ngoai kieu:.......................................\n"));
							document.add(new Phrase("7. Dia chi: So nha ............. Thôn, phố:.......................Xa, phuong .......................................\n"));
							document.add(new Phrase("   Huyen (Q, Tx):.............................................. Tinh, thanh pho .....................................\n"));
							document.add(new Phrase("8. Noi lam viec:.............................................9. Doi tuong:   1.BHYT   2.Thu phi   3.Mien    4.Khac\n"));
							document.add(new Phrase("10. BHYT gia tri den ngay...........thang..........nam...........So the BHYT :..........................................\n"));
							document.add(new Phrase("11. Ho ten dia chi nguoi nha khi can bao:...............................................................................\n"));
							document.add(new Phrase("..............................................................................   Dien Thoai So............................\n"));
							document.add(new Phrase("12. Den kham benh luc:..........gio..............phut              ngay ............. thang ..............nam .............\n"));
							document.add(new Phrase("Chan doan noi gioi thieu:...........................................................\n"));
							document.add(new Phrase("II. LI DO KHAM BENH:.......................................................................................................\n"));
							document.add(new Phrase("III. HOI BENH:   \n"));
							document.add(new Phrase("1. Qua trinh benh li:.........................................................................................................\n"));
							document.add(new Phrase("...............................................................................................................................\n"));
							document.add(new Phrase(".................................................................................................................................\n"));
							document.add(new Phrase("Tien su benh:   \n"));
							document.add(new Phrase("- Ban than:.................................................................................................................   \n"));
							document.add(new Phrase("- Gia dinh:.................................................................................................................   \n\n"));
							
							Paragraph paragraph = new Paragraph();
							paragraph.setAlignment(Element.ALIGN_RIGHT);
							paragraph.add(new Phrase("Ngay..........thang..........nam...............   \n"));
							paragraph.add(new Phrase("NHAN VIEN XAC NHAN                          \n"));
							paragraph.add(new Phrase("Ho ten..........................................\n"));
							document.add(paragraph);
							
							document.close();
							
							JOptionPane.showMessageDialog(null, "Tai Thanh Cong", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
							frame2.dispose();
							glassPane.setVisible(false);
						} catch (DocumentException de) {
							de.printStackTrace();
						} catch (FileNotFoundException fe) {
							fe.printStackTrace();
						}
						
					}
				});
				frame2.add(buttonDownload);
				
				JPanel paneltemp = new JPanel();
				paneltemp.setBackground(Color.white);
				paneltemp.setLayout(null);
				paneltemp.setBounds(0, 720, 800, 100);
				frame2.add(paneltemp);
				
				frame2.setVisible(true);
			}
		});
	}
	
	public int getMaxRow(){
        int maxRow = -1, compareNum;
       
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_lichhenkham_laylichhenkham(?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			while(resultset.next()) {
							
	                        compareNum = Integer.parseInt(resultset.getString("MaLich").substring(2));  
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
		textfieldNhuCauKham.setText("");
		
		SpinnerDateModel modelNgayDuKien = new SpinnerDateModel();
        modelNgayDuKien.setValue(Calendar.getInstance().getTime());
        spinnerNgayDuKien.setModel(modelNgayDuKien);
        JSpinner.DateEditor editorNgayDuKien = new JSpinner.DateEditor(spinnerNgayDuKien, "HH:mm:ss");
        spinnerNgayDuKien.setEditor(editorNgayDuKien);
        DateFormatter formatterNgayDuKien = (DateFormatter)editorNgayDuKien.getTextField().getFormatter();
        formatterNgayDuKien.setAllowsInvalid(false);
        formatterNgayDuKien.setOverwriteMode(true);
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
}
