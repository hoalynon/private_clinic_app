package QuanLy;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Chung.ButtonRound;

import java.sql.*;
import java.util.*;
import oracle.jdbc.OracleTypes;


public class ThongKe implements ActionListener{
	
	private Color textfrontcolor;
	private Color textbackcolor;
	private Color buttonfrontcolor;
	private Color buttonbackcolor;
	
	private QLHome MyHome;
	private JPanel panelContent;
	private JPanel panelMenuMini;
	private JPanel panel0;
	
	private JButton buttonDoanhThu;
	private JButton buttonCaBenh;
	private JButton buttonTopBenh;
	private JButton buttonTopNo;
	
	private ArrayList<String>listNam;
	String DSThang[]  = {"All","1","2","3","4","5","6","7","8","9","10","11","12"}; 
	
	ThongKe(QLHome MyHome){
		
		this.MyHome = MyHome;
		
		textfrontcolor = MyHome.getTextFrontColor();
		textbackcolor = MyHome.getTextBackColor();
		buttonfrontcolor = MyHome.getButtonFrontColor();
		buttonbackcolor = MyHome.getButtonBackColor();
		
		panelContent = new JPanel();
		panelContent.setBackground(textbackcolor);
		panelContent.setLayout(new BorderLayout());
	
		panelMenuMini = new JPanel();
		panelMenuMini.setBackground(textbackcolor);
		panelMenuMini.setForeground(textfrontcolor);
		panelMenuMini.setPreferredSize(new Dimension (100,100));
		panelMenuMini.setLayout(new GridLayout(4,1,0,0));
		panelContent.add(panelMenuMini,BorderLayout.WEST);
		
		panel0 = new JPanel();
		panel0.setBackground(textbackcolor);
		panel0.setPreferredSize(new Dimension (500,300));
		panel0.setLayout(new BorderLayout());
		panelContent.add(panel0,BorderLayout.CENTER);
		
		buttonDoanhThu = new JButton("<html>Doanh<br />Thu</html>");
		buttonDoanhThu.setBackground(Color.decode("#38d662"));
		buttonDoanhThu.setForeground(Color.decode("#ffffff"));
		buttonDoanhThu.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonDoanhThu.addActionListener(this);
		buttonDoanhThu.setBorderPainted(false);
		panelMenuMini.add(buttonDoanhThu);
		
		buttonCaBenh = new JButton("<html>Luong<br />Ca Benh</html>");
		buttonCaBenh.setBackground(Color.decode("#1a5424"));
		buttonCaBenh.setForeground(Color.decode("#ffffff"));
		buttonCaBenh.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonCaBenh.addActionListener(this);
		buttonCaBenh.setBorderPainted(false);
		panelMenuMini.add(buttonCaBenh);
		
		buttonTopBenh = new JButton("<html>Top 5<br />Benh</html>");
		buttonTopBenh.setBackground(Color.decode("#1a5424"));
		buttonTopBenh.setForeground(Color.decode("#ffffff"));
		buttonTopBenh.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonTopBenh.addActionListener(this);
		buttonTopBenh.setBorderPainted(false);
		panelMenuMini.add(buttonTopBenh);
		
		buttonTopNo = new JButton("<html>Top 5<br />No</html>");
		buttonTopNo.setBackground(Color.decode("#1a5424"));
		buttonTopNo.setForeground(Color.decode("#ffffff"));
		buttonTopNo.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonTopNo.addActionListener(this);
		buttonTopNo.setBorderPainted(false);
		panelMenuMini.add(buttonTopNo);
		
		layDSNam();
		layDoanhThu();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonDoanhThu) {
			layDoanhThu();
			RefreshMenuBackground();
			buttonDoanhThu.setBackground(Color.decode("#38d662"));
		}
		else if (e.getSource() == buttonCaBenh) {
			layCaBenh();
			RefreshMenuBackground();
			buttonCaBenh.setBackground(Color.decode("#38d662"));
		}
		else if (e.getSource() == buttonTopBenh) {
			layTopBenh();
			RefreshMenuBackground();
			buttonTopBenh.setBackground(Color.decode("#38d662"));
		}
		else if (e.getSource() == buttonTopNo) {
			layTopNo();
			RefreshMenuBackground();
			buttonTopNo.setBackground(Color.decode("#38d662"));
		}
	}
	
	public void RefreshMenuBackground() {
		buttonDoanhThu.setBackground(Color.decode("#1a5424"));
		buttonCaBenh.setBackground(Color.decode("#1a5424"));
		buttonTopBenh.setBackground(Color.decode("#1a5424"));
		buttonTopNo.setBackground(Color.decode("#1a5424"));
		
	}

	public void layDoanhThu() {
		int DSNamsize = listNam.size() + 1;
		String []DSNam = new String[DSNamsize];
		
		if (listNam.size() == 0)
			DSNam = null;
		else {
			DSNam[0] = "All";
			for (int i = 1; i < DSNamsize; i++) {
				DSNam[i] = listNam.get(i-1);
			}
		}
		
		panel0.removeAll();

		JPanel panel1 = new JPanel();
		panel1.setBackground(textbackcolor);
		panel1.setPreferredSize(new Dimension (400,60));
		panel1.setLayout(new FlowLayout(FlowLayout.TRAILING,0,10));
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel0.add(panel1,BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (400,200));
		panel2.setLayout(new BorderLayout());
		panel0.add(panel2,BorderLayout.CENTER);

		JLabel labelNam = new JLabel("Nam : ");
		labelNam.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNam.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNam.setSize(80,20);
		panel1.add(labelNam);
		
		JComboBox comboBoxNam = new JComboBox(DSNam);
		comboBoxNam.setSize(80,20);
		panel1.add(comboBoxNam);
		
		JLabel labelThang = new JLabel("        Thang : ");
		labelThang.setHorizontalAlignment(SwingConstants.RIGHT);
		labelThang.setFont(new Font("Bevan", Font.BOLD, 12));
		labelThang.setSize(80, 20);
		panel1.add(labelThang);
		
		JComboBox comboBoxThang = new JComboBox(DSThang);
		comboBoxThang.setSize(80,20);
		panel1.add(comboBoxThang);
		
		JLabel labelGap1= new JLabel("            ");
		panel1.add(labelGap1);
		
		
		
		ButtonRound buttonTim= new ButtonRound();
		buttonTim.setText(" Tim ");
		buttonTim.setForeground(buttonfrontcolor);
		buttonTim.setBackground(buttonbackcolor);
		buttonTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent ae) {
				ArrayList <String> arrX = new ArrayList <String>();
				ArrayList <Integer> arrY = new ArrayList <Integer>();
				int TruongHop = 0;
				String tencotX = "";
				
				if (listNam.size() != 0) {
				
					try {
						Connection connection = MyHome.getDatabase().getConnection();
						CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thongke_laydoanhthu (?,?,?) }");
						if (((String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex())) == "All")
						{
							statement.setInt(1,0);
						}
						else
						{
							statement.setInt(1,Integer.valueOf((String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex())));
							TruongHop += 1;
						}
						
						if (((String) comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())) == "All")
						{	
							statement.setInt(2,0);
						}
						else
						{
							statement.setInt(2,Integer.valueOf((String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())));
							TruongHop += 10;
						}
						statement.registerOutParameter(3, OracleTypes.CURSOR);
						statement.execute();
						ResultSet resultset = (ResultSet) statement.getObject(3);
						
						while(resultset.next()) {
							arrX.add(resultset.getString(1));
							arrY.add(resultset.getInt(2));
						} 
						statement.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					
					switch(TruongHop) {
						case 0:
							tencotX = "Cac Nam";
							break;
						case 10:
							tencotX = "Cac Nam Cua Thang " + (String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex());
							break;
						case 1:
							tencotX = "Cac Thang Cua Nam " + (String)comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
							break;
						case 11:
							tencotX = "Cac Ngay Cua Thang" 
									+ (String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex()) + " Cua Nam "
									+ (String)comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
					}
					
					DefaultCategoryDataset dcd = new DefaultCategoryDataset();
					int length = arrX.size();
					for (int i = 0; i < length ; i++) {
						dcd.setValue(arrY.get(i),"Doanh thu",arrX.get(i));
					}

					JFreeChart jchart = ChartFactory.createBarChart("Thong Ke Doanh Thu", tencotX, "Tong Tien", dcd, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot = jchart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartPanel chartPanel = new ChartPanel(jchart);
					
					// Có thể mở nội dung bên dưới để xem demo
					/*
					DefaultCategoryDataset dcd = new DefaultCategoryDataset();
					dcd.setValue(78.80,"Marks","Ganesh");
					dcd.setValue(68.80,"Marks","Dinesh");
					dcd.setValue(88.80,"Marks","John");
					dcd.setValue(98.80,"Marks","Ali");
					dcd.setValue(58.80,"Marks","Sachin");
					

					JFreeChart jchart = ChartFactory.createBarChart("Student ReCord", "Student Name", "Student Marks", dcd, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot = jchart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartPanel chartPanel = new ChartPanel(jchart);
					*/
					panel2.removeAll();
					panel2.add(chartPanel);
					panel2.validate();
					panel2.repaint();
				}
				
			
			}
		});
		buttonTim.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonTim.setBorderPainted(false);
		panel1.add(buttonTim);
		
		JLabel labelGap2= new JLabel("                                            ");
		panel1.add(labelGap2);
		
		ButtonRound confirm = MyHome.getConfirmChatbot();
		confirm.setSize(60, 60);
		panel1.add(confirm);

		panel0.validate();
		panel0.repaint();
	}
	
	public void layCaBenh() {
		int DSNamsize = listNam.size() + 1;
		String []DSNam = new String[DSNamsize];
		
		if (listNam.size() == 0)
			DSNam = null;
		else {
			DSNam[0] = "All";
			for (int i = 1; i < DSNamsize; i++) {
				DSNam[i] = listNam.get(i-1);
			}
		}
		
		panel0.removeAll();

		JPanel panel1 = new JPanel();
		panel1.setBackground(textbackcolor);
		panel1.setPreferredSize(new Dimension (400,60));
		panel1.setLayout(new FlowLayout(FlowLayout.TRAILING,0,10));
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel0.add(panel1,BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (400,200));
		panel2.setLayout(new BorderLayout());
		panel0.add(panel2,BorderLayout.CENTER);

		JLabel labelNam = new JLabel("Nam : ");
		labelNam.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNam.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNam.setSize(80,20);
		panel1.add(labelNam);
		
		JComboBox comboBoxNam = new JComboBox(DSNam);
		comboBoxNam.setSize(80,20);
		panel1.add(comboBoxNam);
		
		JLabel labelThang = new JLabel("        Thang : ");
		labelThang.setHorizontalAlignment(SwingConstants.RIGHT);
		labelThang.setFont(new Font("Bevan", Font.BOLD, 12));
		labelThang.setSize(80, 20);
		panel1.add(labelThang);
		
		JComboBox comboBoxThang = new JComboBox(DSThang);
		comboBoxThang.setSize(80,20);
		panel1.add(comboBoxThang);
		
		JLabel labelGap1= new JLabel("            ");
		panel1.add(labelGap1);
		
		
		
		ButtonRound buttonTim= new ButtonRound();
		buttonTim.setText(" Tim ");
		buttonTim.setForeground(buttonfrontcolor);
		buttonTim.setBackground(buttonbackcolor);
		buttonTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent ae) {
				ArrayList <String> arrX = new ArrayList <String>();
				ArrayList <Integer> arrY = new ArrayList <Integer>();
				int TruongHop = 0;
				String tencotX = "";
				
				if (listNam.size() != 0) {
				
					try {
						Connection connection = MyHome.getDatabase().getConnection();
						CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thongke_layluongcabenh (?,?,?) }");
						if (((String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex())) == "All")
						{
							statement.setInt(1,0);
						}
						else
						{
							statement.setInt(1,Integer.valueOf((String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex())));
							TruongHop += 1;
						}
						
						if (((String) comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())) == "All")
						{	
							statement.setInt(2,0);
						}
						else
						{
							statement.setInt(2,Integer.valueOf((String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())));
							TruongHop += 10;
						}
						statement.registerOutParameter(3, OracleTypes.CURSOR);
						statement.execute();
						ResultSet resultset = (ResultSet) statement.getObject(3);
						
						while(resultset.next()) {
							arrX.add(resultset.getString(1));
							arrY.add(resultset.getInt(2));
						} 
						statement.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					
					switch(TruongHop) {
						case 0:
							tencotX = "Cac Nam";
							break;
						case 10:
							tencotX = "Cac Nam Cua Thang " + (String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex());
							break;
						case 1:
							tencotX = "Cac Thang Cua Nam " + (String)comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
							break;
						case 11:
							tencotX = "Cac Ngay Cua Thang" 
									+ (String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex()) + " Cua Nam "
									+ (String)comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
					}
					
					DefaultCategoryDataset dcd = new DefaultCategoryDataset();
					int length = arrX.size();
					for (int i = 0; i < length ; i++) {
						dcd.setValue(arrY.get(i),"Ca Benh",arrX.get(i));
					}

					JFreeChart jchart = ChartFactory.createBarChart("Thong Ke Luong Ca Benh", tencotX, "So Luong", dcd, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot = jchart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartPanel chartPanel = new ChartPanel(jchart);

					panel2.removeAll();
					panel2.add(chartPanel);
					panel2.validate();
					panel2.repaint();
				}
				
			
			}
		});
		buttonTim.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonTim.setBorderPainted(false);
		panel1.add(buttonTim);
		
		JLabel labelGap2= new JLabel("                                            ");
		panel1.add(labelGap2);
		
		ButtonRound confirm = MyHome.getConfirmChatbot();
		confirm.setSize(60, 60);
		panel1.add(confirm);

		panel0.validate();
		panel0.repaint();
	}
	
	public void layTopBenh() {
		int DSNamsize = listNam.size();
		String []DSNam = new String[DSNamsize];
		
		if (listNam.size() == 0)
			DSNam = null;
		else {
			for (int i = 0; i < DSNamsize; i++) {
				DSNam[i] = listNam.get(i);
			}
		}
		
		panel0.removeAll();

		JPanel panel1 = new JPanel();
		panel1.setBackground(textbackcolor);
		panel1.setPreferredSize(new Dimension (400,60));
		panel1.setLayout(new FlowLayout(FlowLayout.TRAILING,0,10));
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel0.add(panel1,BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (400,200));
		panel2.setLayout(new BorderLayout());
		panel0.add(panel2,BorderLayout.CENTER);

		JLabel labelNam = new JLabel("Nam : ");
		labelNam.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNam.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNam.setSize(80,20);
		panel1.add(labelNam);
		
		JComboBox comboBoxNam = new JComboBox(DSNam);
		comboBoxNam.setSize(80,20);
		panel1.add(comboBoxNam);
		
		JLabel labelThang = new JLabel("        Thang : ");
		labelThang.setHorizontalAlignment(SwingConstants.RIGHT);
		labelThang.setFont(new Font("Bevan", Font.BOLD, 12));
		labelThang.setSize(80, 20);
		panel1.add(labelThang);
		
		JComboBox comboBoxThang = new JComboBox(DSThang);
		comboBoxThang.setSize(80,20);
		panel1.add(comboBoxThang);
		
		JLabel labelGap1= new JLabel("            ");
		panel1.add(labelGap1);
		
		
		
		ButtonRound buttonTim= new ButtonRound();
		buttonTim.setText(" Tim ");
		buttonTim.setForeground(buttonfrontcolor);
		buttonTim.setBackground(buttonbackcolor);
		buttonTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent ae) {
				ArrayList <String> arrX = new ArrayList <String>();
				ArrayList <Integer> arrY = new ArrayList <Integer>();
				int TruongHop = 0;
				String tenChart = "";
				
				if (listNam.size() != 0) {
				
					try {
						Connection connection = MyHome.getDatabase().getConnection();
						CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thongke_laytop5benh (?,?,?) }");
							
						statement.setInt(1,Integer.valueOf((String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex())));
						TruongHop += 1;
						
						if (((String) comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())) == "All")
						{	
							statement.setInt(2,0);
						}
						else
						{
							statement.setInt(2,Integer.valueOf((String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())));
							TruongHop += 10;
						}
						statement.registerOutParameter(3, OracleTypes.CURSOR);
						statement.execute();
						ResultSet resultset = (ResultSet) statement.getObject(3);
						
						while(resultset.next()) {
							arrX.add(resultset.getString(1));
							arrY.add(resultset.getInt(2));
						} 
						statement.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					
					if (TruongHop == 1) {
						tenChart = "Top 5 Loai Benh Co Nhieu Ca Benh Nhat Trong Nam " + (String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
					}
					else if (TruongHop == 11) {
						tenChart = "Top 5 Loai Benh Co Nhieu Ca Benh Nhat Trong Thang "
									+ (String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())
									+ " Cua Nam " + (String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
					}
					
					DefaultCategoryDataset dcd = new DefaultCategoryDataset();
					int length = arrX.size();
					for (int i = 0; i < length ; i++) {
						dcd.setValue(arrY.get(i),"Luong Mac Benh",arrX.get(i));
					}

					JFreeChart jchart = ChartFactory.createBarChart(tenChart, "Cac Loai Benh", "So Luong Ca", dcd, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot = jchart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartPanel chartPanel = new ChartPanel(jchart);

					panel2.removeAll();
					panel2.add(chartPanel);
					panel2.validate();
					panel2.repaint();
				}
				
			
			}
		});
		buttonTim.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonTim.setBorderPainted(false);
		panel1.add(buttonTim);
		
		JLabel labelGap2= new JLabel("                                            ");
		panel1.add(labelGap2);
		
		ButtonRound confirm = MyHome.getConfirmChatbot();
		confirm.setSize(60, 60);
		panel1.add(confirm);

		panel0.validate();
		panel0.repaint();
	}
	
	public void layTopNo() {
		int DSNamsize = listNam.size();
		String []DSNam = new String[DSNamsize];
		
		if (listNam.size() == 0)
			DSNam = null;
		else {
			for (int i = 0; i < DSNamsize; i++) {
				DSNam[i] = listNam.get(i);
			}
		}
		
		panel0.removeAll();

		JPanel panel1 = new JPanel();
		panel1.setBackground(textbackcolor);
		panel1.setPreferredSize(new Dimension (400,60));
		panel1.setLayout(new FlowLayout(FlowLayout.TRAILING,0,10));
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel0.add(panel1,BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(textbackcolor);
		panel2.setPreferredSize(new Dimension (400,200));
		panel2.setLayout(new BorderLayout());
		panel0.add(panel2,BorderLayout.CENTER);

		JLabel labelNam = new JLabel("Nam : ");
		labelNam.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNam.setFont(new Font("Bevan", Font.BOLD, 12));
		labelNam.setSize(80,20);
		panel1.add(labelNam);
		
		JComboBox comboBoxNam = new JComboBox(DSNam);
		comboBoxNam.setSize(80,20);
		panel1.add(comboBoxNam);
		
		JLabel labelThang = new JLabel("        Thang : ");
		labelThang.setHorizontalAlignment(SwingConstants.RIGHT);
		labelThang.setFont(new Font("Bevan", Font.BOLD, 12));
		labelThang.setSize(80, 20);
		panel1.add(labelThang);
		
		JComboBox comboBoxThang = new JComboBox(DSThang);
		comboBoxThang.setSize(80,20);
		panel1.add(comboBoxThang);
		
		JLabel labelGap1= new JLabel("            ");
		panel1.add(labelGap1);
		
		
		
		ButtonRound buttonTim= new ButtonRound();
		buttonTim.setText(" Tim ");
		buttonTim.setForeground(buttonfrontcolor);
		buttonTim.setBackground(buttonbackcolor);
		buttonTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent ae) {
				ArrayList <String> arrX = new ArrayList <String>();
				ArrayList <Integer> arrY = new ArrayList <Integer>();
				int TruongHop = 0;
				String tenChart = "";
				
				if (listNam.size() != 0) {
				
					try {
						Connection connection = MyHome.getDatabase().getConnection();
						CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thongke_laytop5no (?,?,?) }");
							
						statement.setInt(1,Integer.valueOf((String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex())));
						TruongHop += 1;
						
						if (((String) comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())) == "All")
						{	
							statement.setInt(2,0);
						}
						else
						{
							statement.setInt(2,Integer.valueOf((String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())));
							TruongHop += 10;
						}
						statement.registerOutParameter(3, OracleTypes.CURSOR);
						statement.execute();
						ResultSet resultset = (ResultSet) statement.getObject(3);
						
						while(resultset.next()) {
							arrX.add(resultset.getString(1));
							arrY.add(resultset.getInt(2));
						} 
						statement.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					
					if (TruongHop == 1) {
						tenChart = "Top 5 Benh Nhan No Nhieu Nhat Trong Nam " + (String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
					}
					else if (TruongHop == 11) {
						tenChart = "Top 5 Benh Nhan No Nhieu Nhat Trong Thang "
									+ (String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex())
									+ " Cua Nam " + (String) comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
					}
					
					DefaultCategoryDataset dcd = new DefaultCategoryDataset();
					int length = arrX.size();
					for (int i = 0; i < length ; i++) {
						dcd.setValue(arrY.get(i),"Tong No",arrX.get(i));
					}

					JFreeChart jchart = ChartFactory.createBarChart(tenChart, "Ten Cac Benh Nhan", "Luong Tien Chua Tra", dcd, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot = jchart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartPanel chartPanel = new ChartPanel(jchart);

					panel2.removeAll();
					panel2.add(chartPanel);
					panel2.validate();
					panel2.repaint();
				}
				
			
			}
		});
		buttonTim.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonTim.setBorderPainted(false);
		panel1.add(buttonTim);
		
		JLabel labelGap2= new JLabel("                                            ");
		panel1.add(labelGap2);
		
		ButtonRound confirm = MyHome.getConfirmChatbot();
		confirm.setSize(60, 60);
		panel1.add(confirm);

		panel0.validate();
		panel0.repaint();
	}
	
	public void layDSNam() {
		
		ArrayList<String> DSNam = new ArrayList<String>();
		
		try {
			Connection connection = MyHome.getDatabase().getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_thongke_laynam (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			ResultSetMetaData metadata = resultset.getMetaData();
			
			while(resultset.next()) {
				Vector row = new Vector();
				DSNam.add(resultset.getString(1));
			} 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		this.listNam = DSNam;
		
	}
	
	public JPanel getpanelContent() {
		return panelContent;
	}
	
}
