package BenhNhan;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Stack;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Chung.ButtonRound;
import Chung.DangNhap;
import Connection.DatabaseConnector;
import QuanLy.PhongBenh_Them;
import QuanLy.XacNhanLichHen;
import oracle.jdbc.OracleTypes;

public class BNHome implements ActionListener, MouseListener{
	
	private String ID = "", Scheduling = "Khong";
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
	private JButton buttonThongTinCaNhan;
	private JButton buttonCaBenh;
	private JButton buttonPhongBenh;
	private JButton buttonDieuPhoiThietBi;
	private JButton buttonLichHen;
	private JButton buttonHoaDon;
	private JButton buttonDangXuat;
	private JButton buttonQuayLai;
	
	private JLabel labeltitleName;
	private JLabel labeltitleMenu;
	
	private Stack<String> stackQuayLai = new Stack();
	private JTable table;
	private JTable table2;
	
	private ButtonRound confirm;
	
	public BNHome(DangNhap dangnhap){
		
		this.ID = dangnhap.getID();
		this.database = new DatabaseConnector();
		database.connect("BN");
		
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
		panelMenu.setBackground(textbackcolor);
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
		
		labeltitleName = new JLabel(getName(), SwingConstants.CENTER);
		labeltitleName.setPreferredSize(new Dimension(200,50));
		labeltitleName.setForeground(textfrontcolor);
		labeltitleName.setFont(new Font("Bevan", Font.BOLD, 15));
		labeltitleName.addMouseListener(this);
		
		panelTitle.setLayout(new BorderLayout());
		panelTitle.add(labeltitleMenu, BorderLayout.CENTER);
		panelTitle.add(labeltitleName, BorderLayout.EAST);
		
		panelMenu.setLayout(new GridLayout(6,1,0,0));
		
		buttonBacSi = new JButton("Bac Si");
		buttonBacSi.setBackground(buttonbackcolor);
		buttonBacSi.setForeground(buttonfrontcolor);
		buttonBacSi.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonBacSi.addActionListener(this);
		buttonBacSi.setBorderPainted(false);
		panelMenu.add(buttonBacSi);
		
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
		
		buttonDieuPhoiThietBi = new JButton("Dieu Phoi TB");
		buttonDieuPhoiThietBi.setBackground(buttonbackcolor);
		buttonDieuPhoiThietBi.setForeground(buttonfrontcolor);
		buttonDieuPhoiThietBi.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonDieuPhoiThietBi.addActionListener(this);
		buttonDieuPhoiThietBi.setBorderPainted(false);
		panelMenu.add(buttonDieuPhoiThietBi);
		
		buttonLichHen = new JButton("Lich Hen");
		buttonLichHen.setBackground(buttonbackcolor);
		buttonLichHen.setForeground(buttonfrontcolor);
		buttonLichHen.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonLichHen.addActionListener(this);
		buttonLichHen.setBorderPainted(false);
		panelMenu.add(buttonLichHen);
		
		buttonHoaDon = new JButton("Hoa Don");
		buttonHoaDon.setBackground(buttonbackcolor);
		buttonHoaDon.setForeground(buttonfrontcolor);
		buttonHoaDon.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonHoaDon.addActionListener(this);
		buttonHoaDon.setBorderPainted(false);
		panelMenu.add(buttonHoaDon);
		
		buttonQuayLai = new JButton(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		buttonQuayLai.setText("   Quay Lai   ");
		buttonQuayLai.setBackground(buttonbackcolor);
		buttonQuayLai.setForeground(buttonfrontcolor);
		buttonQuayLai.setFont(new Font("Bevan", Font.BOLD, 15));
		buttonQuayLai.addActionListener(this);
		buttonQuayLai.setBorderPainted(false);
		buttonQuayLai.setVisible(false);
		panelTitle.add(buttonQuayLai, BorderLayout.WEST);
		
		panelInfo = new JPanel();
		panelInfo.setBackground(textbackcolor);
		panelInfo.setLayout(null);
		panelInfo.setBounds(0, 0, 160, 100);
		panelInfo.addMouseListener(this);
		frame.add(panelInfo);
        frame.getContentPane().setComponentZOrder(panelInfo,0);
		panelInfo.setLocation(frame.getWidth() - panelInfo.getWidth(), 50);
		panelInfo.setVisible(false);
		
		buttonThongTinCaNhan = new JButton("Thong Tin Ca Nhan");
		buttonThongTinCaNhan.setBackground(textbackcolor);
		buttonThongTinCaNhan.setForeground(textfrontcolor);
		buttonThongTinCaNhan.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonThongTinCaNhan.addActionListener(this);
		buttonThongTinCaNhan.addMouseListener(this);
		buttonThongTinCaNhan.setBorderPainted(false);
		buttonThongTinCaNhan.setBounds(0, 0, 160, 50);
		panelInfo.add(buttonThongTinCaNhan, BorderLayout.NORTH);
		
		buttonDangXuat = new JButton("Dang Xuat");
		buttonDangXuat.setBackground(textbackcolor);
		buttonDangXuat.setForeground(textfrontcolor);
		buttonDangXuat.setFont(new Font("Bevan", Font.BOLD, 12));
		buttonDangXuat.addActionListener(this);
		buttonDangXuat.addMouseListener(this);
		buttonDangXuat.setBorderPainted(false);
		buttonDangXuat.setBounds(0, 50, 160, 50);
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
		else if (e.getSource() == buttonThongTinCaNhan) {
			panelInfo.setVisible(false);
			turnNext("ThongTinCaNhan", null);
		}
		else if (e.getSource() == buttonCaBenh) {
			turnNext("CaBenh", null);
		}
		else if (e.getSource() == buttonPhongBenh) {
			turnNext("PhongBenh", null);
		}
		else if (e.getSource() == buttonDieuPhoiThietBi) {
			turnNext("DieuPhoiThietBi", null);
		}
		else if (e.getSource() == buttonLichHen) {
			turnNext("LichHen", null);
		}
		else if (e.getSource() == buttonHoaDon) {
			turnNext("HoaDon", null);
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
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == labeltitleName) {
			panelInfo.setLocation(frame.getWidth() - panelInfo.getWidth() - 40, 50);
			panelInfo.setVisible(true);
		}
		else if (e.getSource() == buttonThongTinCaNhan) {
			buttonThongTinCaNhan.setBackground(new Color(200, 200, 200));
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
				|| !(e.getX() >= 0 && e.getX() <= labeltitleName.getWidth() && e.getY() >= 0 && e.getY() <= 150))){
			panelInfo.setVisible(false);
		}
		else if (e.getSource() == buttonThongTinCaNhan
				&& !(e.getX() >= 0 && e.getX() <= buttonThongTinCaNhan.getWidth() && e.getY() >= -50 && e.getY() <= 100)) {
			panelInfo.setVisible(false);
		}
		else if (e.getSource() == buttonDangXuat
				&& !(e.getX() >= 0 && e.getX() <= buttonDangXuat.getWidth() && e.getY() >= -100 && e.getY() <= 50)) {
			panelInfo.setVisible(false);
		}
		else if (e.getSource() == buttonThongTinCaNhan) {
			buttonThongTinCaNhan.setBackground(textbackcolor);
		}
		else if (e.getSource() == buttonDangXuat) {
			buttonDangXuat.setBackground(textbackcolor);
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
	
	
	public String getName() {
		try {
			Connection connection = database.getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_benhnhan_laybenhnhan_theobenhnhan (?,?) }");
			statement.setString(1,ID);
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			resultset.next();
			if (resultset.getInt("KHANANGDATLICH") == 1)
				this.Scheduling = "Co";
			return resultset.getString("HOTEN");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return "Vo Danh";
	}
	
	public String getSchedulingAbility() {
		return Scheduling;
	}
	
	public ButtonRound getConfirmChatbot() {
		confirm = new ButtonRound();
		confirm = new ButtonRound();
		confirm.setIcon(new ImageIcon(getClass().getClassLoader().getResource("chatbot.png")));
		confirm.setFont(new Font("Bevan", Font.BOLD, 20));
		confirm.setBackground(textbackcolor);
		confirm.addActionListener(this);
		confirm.setBorderPainted(false);
		confirm.setRoundAllCorner(60);
		
		int demchuaxem = 0;
		
		try {
			Connection connection = database.getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_lichhenkham_laylichhenkham_theobenhnhan (?,?) }");
			statement.setString(1,getID());
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			while(resultset.next()) {
				if (resultset.getInt(7) == 0 && resultset.getInt(6) > 0) {
					demchuaxem += 1;
				}
			} 
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		if (demchuaxem > 0){
			confirm.setBackground(Color.decode("#ff3b0a"));
			confirm.setText(Integer.toString(demchuaxem));
		}
		
		activateConfirmTable();
		
		return confirm;
	}
	
	public void activateConfirmTable() {
		final Container glassPane = (Container)frame.getGlassPane();
		glassPane.setLayout(null);
		glassPane.addMouseListener(new MouseAdapter() {});

		JFrame frame2 = new JFrame("Phan hoi moi nhat ve cac lich hen");
		frame2.setSize(400, 300);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setBackground(textbackcolor);
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
				scrollPane.setPreferredSize(new Dimension (400, 300));
				frame2.add(scrollPane,BorderLayout.CENTER);
				
				table2 = new JTable() {
					boolean[] columnEditables = new boolean[] {
		                                        false, false, false, false
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
					
				table2.setBackground(textbackcolor);
				table2.setFont(new Font("Bevan", Font.PLAIN, 12));
					
				setInformationLichHen();
				scrollPane.setViewportView(table2);
				
				frame2.addWindowListener(new WindowAdapter() {
					@Override
				    public void windowClosing(WindowEvent windowEvent) {
						glassPane.setVisible(false);
						setDaXem();
					}
				});
				
				frame2.setVisible(true);
			}
		});
	}
	
	public void setDaXem() {
		int changedrows = 0;
        	try {
				Connection connection = database.getConnection();
				CallableStatement statement = connection.prepareCall("{ call rootdata.proc_lichhenkham_bndaxem (?)");
				
				statement.registerOutParameter(1,Types.INTEGER);
				statement.execute();
				changedrows +=(int) statement.getObject(1);	
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	}
	
	public void setInformationLichHen() { 

		try {
			Connection connection = database.getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_lichhenkham_laylichhenkham_theobenhnhan (?,?) }");
			statement.setString(1,getID());
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			ResultSetMetaData metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();
			DefaultTableModel data2 = (DefaultTableModel) table2.getModel();	
			data2.setColumnIdentifiers(new String[] {"Ma Lich","Ngay Du Kien","Nhu Cau Kham","Tinh Trang"});
			data2.setRowCount(0);
			
			while(resultset.next()) {
				if (resultset.getInt(7) == 0 && resultset.getInt(6) > 0) {
					Vector row2 = new Vector();
					row2.add(resultset.getString(1));
					row2.add(resultset.getString(4));
					row2.add(resultset.getString(5));
					
					if (resultset.getInt(6) == 2)
						row2.add("Tu choi");
					else
						row2.add("Da xac nhan");
					data2.addRow(row2);
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
		buttonCaBenh.setBackground(buttonbackcolor);
		buttonPhongBenh.setBackground(buttonbackcolor);
		buttonDieuPhoiThietBi.setBackground(buttonbackcolor);
		buttonLichHen.setBackground(buttonbackcolor);
		buttonHoaDon.setBackground(buttonbackcolor);
		
		buttonBacSi.setForeground(buttonfrontcolor);
		buttonCaBenh.setForeground(buttonfrontcolor);
		buttonPhongBenh.setForeground(buttonfrontcolor);
		buttonDieuPhoiThietBi.setForeground(buttonfrontcolor);
		buttonLichHen.setForeground(buttonfrontcolor);
		buttonHoaDon.setForeground(buttonfrontcolor);
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
				case "ThongTinCaNhan":{
					panelContent.removeAll();
					panelContent.add(new ThongTinCaNhan(this).getpanelContent());
					panelContent.validate();
					panelContent.repaint();
					
					labeltitleMenu.setText("Thong Tin Ca Nhan");
					RefreshMenuBackground();
					buttonThongTinCaNhan.setBackground(textbackcolor);
					buttonThongTinCaNhan.setForeground(textfrontcolor);
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
				case "LichHen":{
					panelContent.removeAll();
					panelContent.add(new LichHen(this).getpanelContent());
					panelContent.validate();
					panelContent.repaint();
					
					labeltitleMenu.setText("Lich Hen Kham");
					RefreshMenuBackground();
					buttonLichHen.setBackground(buttonfrontcolor);
					buttonLichHen.setForeground(buttonbackcolor);
				break;
				}
				case "LichHen_Them":{
					panelContent.removeAll();
					panelContent.add(new LichHen_Them(this).getpanelContent());
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
					
					labeltitleMenu.setText("Hoa Don Vien Phi");
					RefreshMenuBackground();
					buttonHoaDon.setBackground(buttonfrontcolor);
					buttonHoaDon.setForeground(buttonbackcolor);
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