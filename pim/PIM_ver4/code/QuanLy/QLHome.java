package QuanLy;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Stack;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Chung.ButtonRound;
import Chung.DangNhap;
import Connection.DatabaseConnector;
import oracle.jdbc.OracleTypes;

public class QLHome implements ActionListener, MouseListener{
	
	private String ID = "";
	private static DatabaseConnector database;
	
	private Color textfrontcolor = Color.decode("#000000");
	private Color textbackcolor = Color.decode("#ffffff");
	private Color buttonfrontcolor = Color.decode("#ffffff");
	private Color buttonbackcolor = Color.decode("#1B3954");
	
	private JFrame frame;
	private JPanel panelTitle;
	private JPanel panelMenu;
	private JPanel panelContent;
	private JPanel panelInfo;
	
	private JButton buttonBacSi;
	private JButton buttonBenhNhan;
	private JButton buttonBenh;
	private JButton buttonCaBenh;
	private JButton buttonPhongBenh;
	private JButton buttonThietBiYTe;
	private JButton buttonDieuPhoiThietBi;
	private JButton buttonTaiKhoan;
	private JButton buttonThuoc;
	private JButton buttonKeThuoc;
	private JButton buttonLichHen;
	private JButton buttonHoaDon;
	private JButton buttonThongKe;
	private JButton buttonDangXuat;
	private JButton buttonQuayLai;
	
	private JLabel labeltitleName;
	private JLabel labeltitleMenu;
	
	private Stack<String> stackQuayLai = new Stack();
	private JTable table;
	private JTable table2;
	private JTable table3;
	
	private ButtonRound confirm;

	
	public QLHome(DangNhap dangnhap){
		
		this.ID = dangnhap.getID();
		QLHome.database = new DatabaseConnector();
		database.connect("QL");
		if (database == null)
			System.out.println("hey");
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(dangnhap.getFrame().getSize());
		frame.setLocation(dangnhap.getFrame().getLocation());
		frame.setExtendedState(dangnhap.getFrame().getExtendedState());
		//frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(textbackcolor);
		frame.getContentPane().setLayout(new BorderLayout());
		
		frame.setGlassPane(new JComponent() {
			@Override
			protected void paintComponent (Graphics g) {
				g.setColor(new Color(0,0,0,150));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		});
		
		panelTitle = new JPanel();
		panelTitle.setBackground(textbackcolor);
		panelTitle.setPreferredSize(new Dimension (50,50));
		panelTitle.setBorder(BorderFactory.createLineBorder(Color.black));
		
		panelMenu = new JPanel();
		panelMenu.setBackground(buttonbackcolor);
		panelMenu.setPreferredSize(new Dimension (150,100));

		panelContent = new JPanel();
		panelContent.setBackground(textbackcolor);
		panelContent.setPreferredSize(new Dimension (100,100));
		panelContent.setLayout(new BorderLayout());
		
		labeltitleMenu = new JLabel("Trang Chu", SwingConstants.CENTER);
		labeltitleMenu.setPreferredSize(new Dimension(200,50));
		labeltitleMenu.setForeground(textfrontcolor);
		labeltitleMenu.setFont(new Font("Bevan", Font.BOLD, 15));
		labeltitleMenu.setVisible(false);
		
		labeltitleName = new JLabel("QUAN LY", SwingConstants.CENTER);
		labeltitleName.setPreferredSize(new Dimension(200,50));
		labeltitleName.setForeground(textfrontcolor);
		labeltitleName.setFont(new Font("Bevan", Font.BOLD, 15));
		labeltitleName.addMouseListener(this);
		
		panelTitle.setLayout(new BorderLayout());
		panelTitle.add(labeltitleMenu, BorderLayout.CENTER);
		panelTitle.add(labeltitleName, BorderLayout.EAST);
		
		
		panelMenu.setLayout(new GridLayout(13,1,0,0));
		
			
		buttonBacSi = new JButton("Bac Si");
		buttonBacSi.setBackground(buttonbackcolor);
		buttonBacSi.setForeground(buttonfrontcolor);
		buttonBacSi.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonBacSi.addActionListener(this);
		buttonBacSi.setBorderPainted(false);
		panelMenu.add(buttonBacSi);
		
		buttonBenhNhan = new JButton("Benh Nhan");
		buttonBenhNhan.setBackground(buttonbackcolor);
		buttonBenhNhan.setForeground(buttonfrontcolor);
		buttonBenhNhan.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonBenhNhan.addActionListener(this);
		buttonBenhNhan.setBorderPainted(false);
		panelMenu.add(buttonBenhNhan);
		
		buttonBenh = new JButton("Benh");
		buttonBenh.setBackground(buttonbackcolor);
		buttonBenh.setForeground(buttonfrontcolor);
		buttonBenh.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonBenh.addActionListener(this);
		buttonBenh.setBorderPainted(false);
		panelMenu.add(buttonBenh);
		
		buttonCaBenh = new JButton("Ca Benh");
		buttonCaBenh.setBackground(buttonbackcolor);
		buttonCaBenh.setForeground(buttonfrontcolor);
		buttonCaBenh.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonCaBenh.addActionListener(this);
		buttonCaBenh.setBorderPainted(false);
		panelMenu.add(buttonCaBenh);
		
		buttonPhongBenh = new JButton("Phong Benh");
		buttonPhongBenh.setBackground(buttonbackcolor);
		buttonPhongBenh.setForeground(buttonfrontcolor);
		buttonPhongBenh.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonPhongBenh.addActionListener(this);
		buttonPhongBenh.setBorderPainted(false);
		panelMenu.add(buttonPhongBenh);
		
		buttonThietBiYTe = new JButton("Thiet Bi Y Te");
		buttonThietBiYTe.setBackground(buttonbackcolor);
		buttonThietBiYTe.setForeground(buttonfrontcolor);
		buttonThietBiYTe.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonThietBiYTe.addActionListener(this);
		buttonThietBiYTe.setBorderPainted(false);
		panelMenu.add(buttonThietBiYTe);
		
		buttonDieuPhoiThietBi = new JButton("Dieu Phoi TB");
		buttonDieuPhoiThietBi.setBackground(buttonbackcolor);
		buttonDieuPhoiThietBi.setForeground(buttonfrontcolor);
		buttonDieuPhoiThietBi.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonDieuPhoiThietBi.addActionListener(this);
		buttonDieuPhoiThietBi.setBorderPainted(false);
		panelMenu.add(buttonDieuPhoiThietBi);
		
		buttonTaiKhoan = new JButton("Tai Khoan");
		buttonTaiKhoan.setBackground(buttonbackcolor);
		buttonTaiKhoan.setForeground(buttonfrontcolor);
		buttonTaiKhoan.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonTaiKhoan.addActionListener(this);
		buttonTaiKhoan.setBorderPainted(false);
		panelMenu.add(buttonTaiKhoan);
		
		buttonThuoc = new JButton("Thuoc");
		buttonThuoc.setBackground(buttonbackcolor);
		buttonThuoc.setForeground(buttonfrontcolor);
		buttonThuoc.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonThuoc.addActionListener(this);
		buttonThuoc.setBorderPainted(false);
		panelMenu.add(buttonThuoc);
		
		buttonKeThuoc = new JButton("Ke Thuoc");
		buttonKeThuoc.setBackground(buttonbackcolor);
		buttonKeThuoc.setForeground(buttonfrontcolor);
		buttonKeThuoc.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonKeThuoc.addActionListener(this);
		buttonKeThuoc.setBorderPainted(false);
		panelMenu.add(buttonKeThuoc);
		
		buttonLichHen = new JButton("Lich Hen");
		buttonLichHen.setBackground(buttonbackcolor);
		buttonLichHen.setForeground(buttonfrontcolor);
		buttonLichHen.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonLichHen.addActionListener(this);
		buttonLichHen.setBorderPainted(false);
		panelMenu.add(buttonLichHen);
		
		buttonHoaDon = new JButton("Hoa Don No");
		buttonHoaDon.setBackground(buttonbackcolor);
		buttonHoaDon.setForeground(buttonfrontcolor);
		buttonHoaDon.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonHoaDon.addActionListener(this);
		buttonHoaDon.setBorderPainted(false);
		panelMenu.add(buttonHoaDon);
		
		buttonThongKe = new JButton("Thong Ke");
		buttonThongKe.setBackground(buttonbackcolor);
		buttonThongKe.setForeground(buttonfrontcolor);
		buttonThongKe.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonThongKe.addActionListener(this);
		buttonThongKe.setBorderPainted(false);
		panelMenu.add(buttonThongKe);
		
		buttonQuayLai = new JButton(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		buttonQuayLai.setText("   Quay Lai   ");
		buttonQuayLai.setBackground(buttonbackcolor);
		buttonQuayLai.setForeground(buttonfrontcolor);
		buttonQuayLai.setFont(new Font("Inter", Font.PLAIN, 16));
		buttonQuayLai.addActionListener(this);
		buttonQuayLai.setBorderPainted(false);
		buttonQuayLai.setVisible(false);
		panelTitle.add(buttonQuayLai, BorderLayout.WEST);
		
		panelInfo = new JPanel();
		panelInfo.setBackground(textbackcolor);
		panelInfo.setLayout(null);
		panelInfo.setBounds(0, 0, 160, 50);
		panelInfo.addMouseListener(this);
		frame.add(panelInfo);
        frame.getContentPane().setComponentZOrder(panelInfo,0);
		panelInfo.setLocation(frame.getWidth() - panelInfo.getWidth(), 50);
		panelInfo.setVisible(false);
		
		buttonDangXuat = new JButton("Dang Xuat");
		buttonDangXuat.setBackground(textbackcolor);
		buttonDangXuat.setForeground(textfrontcolor);
		buttonDangXuat.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonDangXuat.addActionListener(this);
		buttonDangXuat.addMouseListener(this);
		buttonDangXuat.setBorderPainted(false);
		buttonDangXuat.setBounds(0, 0, 160, 50);
		panelInfo.add(buttonDangXuat, BorderLayout.CENTER);
		
		
		frame.getContentPane().add(panelTitle,BorderLayout.NORTH);
		frame.getContentPane().add(panelMenu,BorderLayout.WEST);
		frame.getContentPane().add(panelContent,BorderLayout.CENTER);
		
		frame.setVisible(true);


	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonBacSi) {
			turnNext("BacSi", null);
		}
		else if (e.getSource() == buttonBenhNhan) {
			turnNext("BenhNhan", null);
		}
		else if (e.getSource() == buttonBenh) {
			turnNext("Benh", null);
		}
		else if (e.getSource() == buttonCaBenh) {
			turnNext("CaBenh", null);
		}
		else if (e.getSource() == buttonPhongBenh) {
			turnNext("PhongBenh", null);
		}
		else if (e.getSource() == buttonThietBiYTe) {
			turnNext("ThietBiYTe", null);
		}
		else if (e.getSource() == buttonDieuPhoiThietBi) {
			turnNext("DieuPhoiThietBi", null);
		}
		else if (e.getSource() == buttonTaiKhoan) {
			turnNext("TaiKhoan", null);
		}
		else if (e.getSource() == buttonThuoc) {
			turnNext("Thuoc", null);
		}
		else if (e.getSource() == buttonKeThuoc) {
			turnNext("KeThuoc", null);
		}
		else if (e.getSource() == buttonLichHen) {
			turnNext("LichHen", null);
		}
		else if (e.getSource() == buttonHoaDon) {
			turnNext("HoaDon", null);
		}
		else if (e.getSource() == buttonThongKe) {
			turnNext("ThongKe", null);
		}
		else if (e.getSource() == buttonDangXuat) {
			DangNhap dangnhap = new DangNhap();
			dangnhap.getFrame().setSize(frame.getSize());
			dangnhap.getFrame().setLocation(frame.getLocation());
			dangnhap.getFrame().setExtendedState(frame.getExtendedState());
			database.disconnect();
			frame.dispose();
		}
		else if (e.getSource() == buttonQuayLai) {
			turnBack();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == labeltitleName) {
			panelInfo.setLocation(frame.getWidth() - panelInfo.getWidth() - 40, 50);
			panelInfo.setVisible(true);
		}
		else if (e.getSource() == buttonDangXuat) {
			buttonDangXuat.setBackground(new Color(200, 200, 200));
		}
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == labeltitleName
				&& ((e.getX() <= 40 && e.getY() >= 50)
				|| (e.getX() >= 190 && e.getY() >= 50)
				|| !(e.getX() >= 0 && e.getX() <= labeltitleName.getWidth() && e.getY() >= 0 && e.getY() <= 100))){
			panelInfo.setVisible(false);
		}
		else if (e.getSource() == buttonDangXuat
				&& !(e.getX() >= 0 && e.getX() <= buttonDangXuat.getWidth() && e.getY() >= -50 && e.getY() <= 50)) {
			panelInfo.setVisible(false);
		}
		else if (e.getSource() == buttonDangXuat) {
			buttonDangXuat.setBackground(new Color(255, 255, 255));
		}
		
	}
	
	
	public String getID() {
		return this.ID;
	}
	public DatabaseConnector getDatabase() {
		return database;
	}
	public JFrame getFrame() {
		return frame;
	}
	
	public JPanel getPanelContent() {
		return panelContent;
	}
	
	public Color getTextFrontColor() {
		return this.textfrontcolor;
	}
	
	public Color getTextBackColor() {
		return this.textbackcolor;
	}
	
	public Color getButtonFrontColor() {
		return this.buttonfrontcolor;
	}
	
	public Color getButtonBackColor() {
		return this.buttonbackcolor;
	}
	
	public JButton getButtonBack() {
		return buttonQuayLai;
	}
	
	public ButtonRound getConfirmChatbot() {
		confirm = new ButtonRound();
		confirm.setIcon(new ImageIcon(getClass().getClassLoader().getResource("chatbot.png")));
		confirm.setFont(new Font("Bevan", Font.BOLD, 20));
		confirm.setBackground(textbackcolor);
		confirm.addActionListener(this);
		confirm.setBorderPainted(false);
		confirm.setRoundAllCorner(60);
		
		int demchuaxacnhan = 0;
		
		try {
			Connection connection = database.getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_lichhenkham_laylichhenkham (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			while(resultset.next()) {
				if (resultset.getInt(6) == 0) {
					demchuaxacnhan += 1;
				}
			} 
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		if (demchuaxacnhan > 0){
			confirm.setBackground(Color.decode("#ff3b0a"));
			confirm.setText(Integer.toString(demchuaxacnhan));
		}
		
		activateConfirmTable();
		
		return confirm;
	}
	
	public void activateConfirmTable() {
		final Container glassPane = (Container)frame.getGlassPane();
		glassPane.setLayout(null);
		glassPane.addMouseListener(new MouseAdapter() {});

		JFrame frame2 = new JFrame();
		frame2.setSize(300, 300);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setBackground(new Color(214, 231, 239));
		frame2.setLayout(new BorderLayout());
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
		
		
		
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				glassPane.setVisible(true);
				frame2.setLocationRelativeTo(null);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setPreferredSize(new Dimension (400, 200));
				frame2.add(scrollPane,BorderLayout.CENTER);
				
				table2 = new JTable() {
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
					
				table2.setBackground(Color.decode("#d6e7ef"));
				table2.setFont(new Font("Bevan", Font.PLAIN, 12));
					
				setInformationLichHen();
				scrollPane.setViewportView(table2);
				
				frame2.addWindowListener(new WindowAdapter() {
					@Override
				    public void windowClosing(WindowEvent windowEvent) {
						glassPane.setVisible(false);
					}
				});
				
				JButton buttonXem = new JButton("Xem Chi Tiet");
				buttonXem.setForeground(buttonfrontcolor);
				buttonXem.setBackground(buttonbackcolor);
				buttonXem.setFont(new Font("Bevan", Font.BOLD, 12));
				buttonXem.setBorderPainted(false);
				buttonXem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed (ActionEvent e) {
						if (table2 != null && table2.getSelectedRowCount() == 1) {
							JFrame frame3 = new JFrame();
							frame3.setSize(700, 500);
							frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							frame3.setBackground(new Color(214, 231, 239));
							frame3.setLayout(new BorderLayout());
							
							
							frame2.addWindowListener(new WindowAdapter() {
								@Override
							    public void windowClosing(WindowEvent windowEvent) {
									frame3.dispose();
									glassPane.setVisible(false);
								}
							});
							
							frame3.addWindowListener(new WindowAdapter() {
								@Override
							    public void windowClosing(WindowEvent windowEvent) {
									glassPane2.setVisible(false);
								}
							});
							
							glassPane2.setVisible(true);
							frame3.setLocationRelativeTo(null);
							frame3.add(new XacNhanLichHen(getDatabase(), table2, table3).getpanelContent(), BorderLayout.CENTER);
							frame3.setVisible(true);
						}
					}
				});
				frame2.add(buttonXem, BorderLayout.SOUTH);
				
				frame2.setVisible(true);
			}
		});
	}
	
	public void setInformationLichHen() { 
		table3 = new JTable();

		try {
			Connection connection = database.getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_lichhenkham_laylichhenkham (?) }");
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(1);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data2 = (DefaultTableModel) table2.getModel();	
			data2.setColumnIdentifiers(new String[] {"Ma Lich","Ma BN","Ngay Du Kien"});
			data2.setRowCount(0);
			
			DefaultTableModel data3 = (DefaultTableModel) table3.getModel();
			data3.setColumnIdentifiers(new String[] {"Ma Lich","Ma BN","Ma BS","Ngay Du Kien","Nhu Cau Kham","QL Xac Nhan","BN Xac Nhan"});
			data3.setRowCount(0);
			
			while(resultset.next()) {
				if (resultset.getInt(6) == 0) {
					Vector row2 = new Vector();
					row2.add(resultset.getString(1));
					row2.add(resultset.getString(2));
					row2.add(resultset.getString(4));
					data2.addRow(row2);
					
					Vector row3 = new Vector();
					for (int i = 0; i < columnCount; i++) {
						row3.add(resultset.getString(i+1));
					}
					data3.addRow(row3);
				}
			} 
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	
	public void RefreshMenuBackground() {
		buttonBacSi.setBackground(buttonbackcolor);
		buttonBenhNhan.setBackground(buttonbackcolor);
		buttonBenh.setBackground(buttonbackcolor);
		buttonCaBenh.setBackground(buttonbackcolor);
		buttonPhongBenh.setBackground(buttonbackcolor);
		buttonThietBiYTe.setBackground(buttonbackcolor);
		buttonDieuPhoiThietBi.setBackground(buttonbackcolor);
		buttonTaiKhoan.setBackground(buttonbackcolor);
		buttonThuoc.setBackground(buttonbackcolor);
		buttonKeThuoc.setBackground(buttonbackcolor);
		buttonLichHen.setBackground(buttonbackcolor);
		buttonHoaDon.setBackground(buttonbackcolor);
		buttonThongKe.setBackground(buttonbackcolor);
		
		buttonBacSi.setForeground(buttonfrontcolor);
		buttonBenhNhan.setForeground(buttonfrontcolor);
		buttonBenh.setForeground(buttonfrontcolor);
		buttonCaBenh.setForeground(buttonfrontcolor);
		buttonPhongBenh.setForeground(buttonfrontcolor);
		buttonThietBiYTe.setForeground(buttonfrontcolor);
		buttonDieuPhoiThietBi.setForeground(buttonfrontcolor);
		buttonTaiKhoan.setForeground(buttonfrontcolor);
		buttonThuoc.setForeground(buttonfrontcolor);
		buttonKeThuoc.setForeground(buttonfrontcolor);
		buttonLichHen.setForeground(buttonfrontcolor);
		buttonHoaDon.setForeground(buttonfrontcolor);
		buttonThongKe.setForeground(buttonfrontcolor);
	}
	
	public void turnNext(String NavigatedClass, JTable table) {
		this.table = table;
		System.out.println("go to " + NavigatedClass);
		if (stackQuayLai.isEmpty() || (stackQuayLai.peek() != NavigatedClass)){
			stackQuayLai.push(NavigatedClass);
			buttonQuayLai.setVisible(true);
		}
		change(NavigatedClass);
	}
	
	public void turnBack() {
		this.table = null;
		stackQuayLai.pop();
		if (stackQuayLai.isEmpty()) {
			buttonQuayLai.setVisible(false);
			change("KhoiTao");
		}
		else{
			System.out.println("back to " + stackQuayLai.peek());
			change(stackQuayLai.peek());
		}
	}
	
	public void change (String NavigatedClass) {
		switch(NavigatedClass) {
		case "KhoiTao":{
			panelContent.removeAll();
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Trang Chu");
			RefreshMenuBackground();
			break;
		}
		case "BacSi":{
			panelContent.removeAll();
			panelContent.add(new BacSi(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Bac Si");
			RefreshMenuBackground();
			buttonBacSi.setBackground(buttonfrontcolor);
			buttonBacSi.setForeground(buttonbackcolor);
			break;
		}
		case "BacSi_Them":{
			panelContent.removeAll();
			panelContent.add(new BacSi_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Bac Si");
			RefreshMenuBackground();
			buttonBacSi.setBackground(buttonfrontcolor);
			buttonBacSi.setForeground(buttonbackcolor);
			break;
		}
		case "BacSi_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new BacSi_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Bac Si");
			RefreshMenuBackground();
			buttonBacSi.setBackground(buttonfrontcolor);
			buttonBacSi.setForeground(buttonbackcolor);
			break;
		}
		case "BenhNhan":{
			panelContent.removeAll();
			panelContent.add(new BenhNhan(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Benh Nhan");
			RefreshMenuBackground();
			buttonBenhNhan.setBackground(buttonfrontcolor);
			buttonBenhNhan.setForeground(buttonbackcolor);
			break;
		}
		case "BenhNhan_Them":{
			panelContent.removeAll();
			panelContent.add(new BenhNhan_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Benh Nhan");
			RefreshMenuBackground();
			buttonBenhNhan.setBackground(buttonfrontcolor);
			buttonBenhNhan.setForeground(buttonbackcolor);
			break;
		}
		case "BenhNhan_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new BenhNhan_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Benh Nhan");
			RefreshMenuBackground();
			buttonBenhNhan.setBackground(buttonfrontcolor);
			buttonBenhNhan.setForeground(buttonbackcolor);
			break;
		}
		case "Benh":{
			panelContent.removeAll();
			panelContent.add(new Benh(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Benh");
			RefreshMenuBackground();
			buttonBenh.setBackground(buttonfrontcolor);
			buttonBenh.setForeground(buttonbackcolor);
			break;
		}
		case "Benh_Them":{
			panelContent.removeAll();
			panelContent.add(new Benh_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Benh");
			RefreshMenuBackground();
			buttonBenh.setBackground(buttonfrontcolor);
			buttonBenh.setForeground(buttonbackcolor);
			break;
		}
		case "Benh_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new Benh_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Benh");
			RefreshMenuBackground();
			buttonBenh.setBackground(buttonfrontcolor);
			buttonBenh.setForeground(buttonbackcolor);
			break;
		}
		case "CaBenh":{
			panelContent.removeAll();
			panelContent.add(new CaBenh(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Ca Benh");
			RefreshMenuBackground();
			buttonCaBenh.setBackground(buttonfrontcolor);
			buttonCaBenh.setForeground(buttonbackcolor);
			break;
		}
		case "CaBenh_Them":{
			panelContent.removeAll();
			panelContent.add(new CaBenh_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Ca Benh");
			RefreshMenuBackground();
			buttonCaBenh.setBackground(buttonfrontcolor);
			buttonCaBenh.setForeground(buttonbackcolor);
			break;
		}
		case "CaBenh_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new CaBenh_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Ca Benh");
			RefreshMenuBackground();
			buttonCaBenh.setBackground(buttonfrontcolor);
			buttonCaBenh.setForeground(buttonbackcolor);
			break;
		}
		case "PhongBenh":{
			panelContent.removeAll();
			panelContent.add(new PhongBenh(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Phong Benh");
			RefreshMenuBackground();
			buttonPhongBenh.setBackground(buttonfrontcolor);
			buttonPhongBenh.setForeground(buttonbackcolor);
			break;
		}
		case "PhongBenh_Them":{
			panelContent.removeAll();
			panelContent.add(new PhongBenh_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Phong Benh");
			RefreshMenuBackground();
			buttonPhongBenh.setBackground(buttonfrontcolor);
			buttonPhongBenh.setForeground(buttonbackcolor);
			break;
		}
		case "PhongBenh_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new PhongBenh_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Phong Benh");
			RefreshMenuBackground();
			buttonPhongBenh.setBackground(buttonfrontcolor);
			buttonPhongBenh.setForeground(buttonbackcolor);
			break;
		}
		case "ThietBiYTe":{
			panelContent.removeAll();
			panelContent.add(new ThietBiYTe(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Thiet Bi Y Te");
			RefreshMenuBackground();
			buttonThietBiYTe.setBackground(buttonfrontcolor);
			buttonThietBiYTe.setForeground(buttonbackcolor);
			break;
		}
		case "ThietBiYTe_Them":{
			panelContent.removeAll();
			panelContent.add(new ThietBiYTe_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Thiet Bi Y Te");
			RefreshMenuBackground();
			buttonThietBiYTe.setBackground(buttonfrontcolor);
			buttonThietBiYTe.setForeground(buttonbackcolor);
			break;
		}
		case "ThietBiYTe_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new ThietBiYTe_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Thiet Bi Y Te");
			RefreshMenuBackground();
			buttonThietBiYTe.setBackground(buttonfrontcolor);
			buttonThietBiYTe.setForeground(buttonbackcolor);
			break;
		}
		case "DieuPhoiThietBi":{
			panelContent.removeAll();
			panelContent.add(new DieuPhoiThietBi(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Dieu Phoi Thiet Bi");
			RefreshMenuBackground();
			buttonDieuPhoiThietBi.setBackground(buttonfrontcolor);
			buttonDieuPhoiThietBi.setForeground(buttonbackcolor);
			break;
		}
		case "DieuPhoiThietBi_Them":{
			panelContent.removeAll();
			panelContent.add(new DieuPhoiThietBi_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Dieu Phoi Thiet Bi");
			RefreshMenuBackground();
			buttonDieuPhoiThietBi.setBackground(buttonfrontcolor);
			buttonDieuPhoiThietBi.setForeground(buttonbackcolor);
			break;
		}
		case "DieuPhoiThietBi_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new DieuPhoiThietBi_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Dieu Phoi Thiet Bi");
			RefreshMenuBackground();
			buttonDieuPhoiThietBi.setBackground(buttonfrontcolor);
			buttonDieuPhoiThietBi.setForeground(buttonbackcolor);
			break;
		}
		case "TaiKhoan":{
			panelContent.removeAll();
			panelContent.add(new TaiKhoan(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Tai Khoan");
			RefreshMenuBackground();
			buttonTaiKhoan.setBackground(buttonfrontcolor);
			buttonTaiKhoan.setForeground(buttonbackcolor);
			break;
		}
		case "TaiKhoan_Them":{
			panelContent.removeAll();
			panelContent.add(new TaiKhoan_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Tai Khoan");
			RefreshMenuBackground();
			buttonTaiKhoan.setBackground(buttonfrontcolor);
			buttonTaiKhoan.setForeground(buttonbackcolor);
			break;
		}
		case "TaiKhoan_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new TaiKhoan_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Tai Khoan");
			RefreshMenuBackground();
			buttonTaiKhoan.setBackground(buttonfrontcolor);
			buttonTaiKhoan.setForeground(buttonbackcolor);
			break;
		}
		case "Thuoc":{
			panelContent.removeAll();
			panelContent.add(new Thuoc(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Thuoc");
			RefreshMenuBackground();
			buttonThuoc.setBackground(buttonfrontcolor);
			buttonThuoc.setForeground(buttonbackcolor);
			break;
		}
		case "Thuoc_Them":{
			panelContent.removeAll();
			panelContent.add(new Thuoc_Them(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Ke Thuoc");
			RefreshMenuBackground();
			buttonThuoc.setBackground(buttonfrontcolor);
			buttonThuoc.setForeground(buttonbackcolor);
			break;
		}
		case "Thuoc_CapNhat":{
			panelContent.removeAll();
			panelContent.add(new Thuoc_CapNhat(this, table).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Thuoc");
			RefreshMenuBackground();
			buttonThuoc.setBackground(buttonfrontcolor);
			buttonThuoc.setForeground(buttonbackcolor);
			break;
		}
		case "KeThuoc":{
			panelContent.removeAll();
			panelContent.add(new KeThuoc(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Ke Thuoc");
			RefreshMenuBackground();
			buttonKeThuoc.setBackground(buttonfrontcolor);
			buttonKeThuoc.setForeground(buttonbackcolor);
			break;
		}
		case "LichHen":{
			panelContent.removeAll();
			panelContent.add(new LichHen(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Lich Hen");
			RefreshMenuBackground();
			buttonLichHen.setBackground(buttonfrontcolor);
			buttonLichHen.setForeground(buttonbackcolor);
			break;
		}
		case "HoaDon":{
			panelContent.removeAll();
			panelContent.add(new HoaDon(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Hoa Don Chua Thanh Toan");
			RefreshMenuBackground();
			buttonHoaDon.setBackground(buttonfrontcolor);
			buttonHoaDon.setForeground(buttonbackcolor);
			break;
		}
		case "ThongKe":{
			
			panelContent.removeAll();
			panelContent.add(new ThongKe(this).getpanelContent());
			panelContent.validate();
			panelContent.repaint();
			
			labeltitleMenu.setText("Thong Ke");
			RefreshMenuBackground();
			buttonThongKe.setBackground(buttonfrontcolor);
			buttonThongKe.setForeground(buttonbackcolor);
			break;
		}
		}
	}

	
	public String fillZero(int number, int range){
        String missingZero = "";
        int missingrange = range - (Integer.toString(number)).length();
        if (missingrange <= 0){
            return missingZero;
        }
        for (int i = 0; i < missingrange; i++){
            missingZero += "0";
        }
        return missingZero;
    }



}
