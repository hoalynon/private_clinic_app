package BacSi;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

import javax.swing.*;

import Chung.DangNhap;
import Connection.DatabaseConnector;
import oracle.jdbc.OracleTypes;

public class BSHome implements ActionListener, MouseListener{
	
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
	
	private JButton buttonThongTinCaNhan;
	private JButton buttonBacSi;
	private JButton buttonBenhNhan;
	private JButton buttonBenh;
	private JButton buttonCaBenh;
	private JButton buttonPhongBenh;
	private JButton buttonThietBiYTe;
	private JButton buttonDieuPhoiThietBi;
	private JButton buttonThuoc;
	private JButton buttonKeThuoc;
	private JButton buttonLichHen;
	private JButton buttonDangXuat;
	private JButton buttonQuayLai;
	
	private JLabel labeltitleName;
	private JLabel labeltitleMenu;
	
	private Stack<String> stackQuayLai = new Stack();
	private JTable table;
	
	public BSHome(DangNhap dangnhap){
		this.ID = dangnhap.getID();
		this.database = new DatabaseConnector();
		database.connect("BS");
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(dangnhap.getFrame().getSize());
		frame.setLocation(dangnhap.getFrame().getLocation());
		frame.setExtendedState(dangnhap.getFrame().getExtendedState());
		//frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(textbackcolor);
		frame.getContentPane().setLayout(new BorderLayout());
		
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
		labeltitleMenu.setForeground(textbackcolor);
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
		
		panelMenu.setLayout(new GridLayout(10,1,0,0));
		
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
		
		if (e.getSource() == buttonThongTinCaNhan) {
			panelInfo.setVisible(false);
			turnNext("ThongTinCaNhan", null);
		}
		else if (e.getSource() == buttonBacSi) {
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
		else if (e.getSource() == buttonThuoc) {
			turnNext("Thuoc", null);
		}
		else if (e.getSource() == buttonKeThuoc) {
			turnNext("KeThuoc", null);
		}
		else if (e.getSource() == buttonLichHen) {
			turnNext("LichHen", null);
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
	
	public JButton getButtonBack() {
		return buttonQuayLai;
	}
	
	public String getName() {
		try {
			Connection connection = database.getConnection();
			CallableStatement statement = connection.prepareCall("{ call rootdata.proc_bacsi_laybacsi_theobacsi (?,?) }");
			statement.setString(1,ID);
			statement.registerOutParameter(2, OracleTypes.CURSOR);
			statement.execute();
			ResultSet resultset = (ResultSet) statement.getObject(2);
			resultset.next();
			return resultset.getString("HOTEN");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Loi: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return "Vo Danh";
	}
	
	public void RefreshMenuBackground() {
		buttonBacSi.setBackground(buttonbackcolor);
		buttonBenhNhan.setBackground(buttonbackcolor);
		buttonBenh.setBackground(buttonbackcolor);
		buttonCaBenh.setBackground(buttonbackcolor);
		buttonPhongBenh.setBackground(buttonbackcolor);
		buttonThietBiYTe.setBackground(buttonbackcolor);
		buttonDieuPhoiThietBi.setBackground(buttonbackcolor);
		buttonThuoc.setBackground(buttonbackcolor);
		buttonKeThuoc.setBackground(buttonbackcolor);
		buttonLichHen.setBackground(buttonbackcolor);
		
		buttonBacSi.setForeground(buttonfrontcolor);
		buttonBenhNhan.setForeground(buttonfrontcolor);
		buttonBenh.setForeground(buttonfrontcolor);
		buttonCaBenh.setForeground(buttonfrontcolor);
		buttonPhongBenh.setForeground(buttonfrontcolor);
		buttonThietBiYTe.setForeground(buttonfrontcolor);
		buttonDieuPhoiThietBi.setForeground(buttonfrontcolor);
		buttonThuoc.setForeground(buttonfrontcolor);
		buttonKeThuoc.setForeground(buttonfrontcolor);
		buttonLichHen.setForeground(buttonfrontcolor);
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
				case "CaBenh_KeThuoc":{
					panelContent.removeAll();
					panelContent.add(new CaBenh_KeThuoc(this, table).getpanelContent());
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
