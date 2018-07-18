package com.gc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ai.controller.Login;
import com.ai.util.LogUtils;

public class U_Main {

	private JFrame frame;
	private JTextField txtuname;
	private JPasswordField txtpwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					U_Main window = new U_Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public U_Main() {
		initialize();
	}
	
	//面板
	Panel mainPanel;
	JPanel roleInfo;
	JPanel login;
	JPanel functionpanel;
	JPanel logpanel;
	//组件
	public static TextArea txtlog;
	public static JLabel lblsoftstatus;
	public static JComboBox cbxServerlist;
	public static JComboBox cbxrolelist;
	private JLabel label_2;
	private JButton button;
	private JPanel infopanel;
	private JLabel lblNewLabel_1;
	private JLabel label_3;
	private JLabel lblVip;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	public static JLabel lblName;
	public static JLabel lbllv;
	public static JLabel lblviplv;
	public static JLabel lblyb;
	public static JLabel lblmc;
	public static JLabel lblls;
	public static JLabel lblbt;
	public static JLabel lbljb;
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 754);
		frame.setTitle("攻城掠地Beta 1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new Panel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		roleInfo = new JPanel();
		roleInfo.setBounds(209, 6, 815, 144);
		roleInfo.setBorder(BorderFactory.createTitledBorder("角色信息"));
		mainPanel.add(roleInfo);
		roleInfo.setLayout(null);
		cbxServerlist = new JComboBox();
		cbxServerlist.setBounds(6, 37, 112, 27);
		roleInfo.add(cbxServerlist);
		cbxServerlist.setBackground((Color) null);
		
		cbxrolelist = new JComboBox();
		cbxrolelist.setBackground((Color) null);
		cbxrolelist.setBounds(6, 74, 112, 27);
		roleInfo.add(cbxrolelist);
		
		label_2 = new JLabel("选择服务器与角色");
		label_2.setBounds(6, 20, 112, 16);
		roleInfo.add(label_2);
		
		button = new JButton("选择");
		button.setBounds(16, 109, 75, 29);
		roleInfo.add(button);
		
		infopanel = new JPanel();
		infopanel.setBounds(129, 16, 680, 111);
		infopanel.setBorder(BorderFactory.createTitledBorder(""));
		roleInfo.add(infopanel);
		infopanel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("角色：");
		lblNewLabel_1.setBounds(6, 6, 39, 16);
		infopanel.add(lblNewLabel_1);
		
		label_3 = new JLabel("级别：");
		label_3.setBounds(6, 34, 39, 16);
		infopanel.add(label_3);
		
		lblVip = new JLabel("VIP：");
		lblVip.setBounds(6, 62, 39, 16);
		infopanel.add(lblVip);
		
		label_4 = new JLabel("银币：");
		label_4.setBounds(6, 90, 39, 16);
		infopanel.add(label_4);
		
		label_5 = new JLabel("木材：");
		label_5.setBounds(109, 6, 39, 16);
		infopanel.add(label_5);
		
		label_6 = new JLabel("粮食：");
		label_6.setBounds(109, 34, 39, 16);
		infopanel.add(label_6);
		
		label_7 = new JLabel("镔铁：");
		label_7.setBounds(109, 62, 39, 16);
		infopanel.add(label_7);
		
		label_8 = new JLabel("金币：");
		label_8.setBounds(109, 90, 39, 16);
		infopanel.add(label_8);
		
		lblName = new JLabel("张三");
		lblName.setBounds(42, 6, 55, 16);
		infopanel.add(lblName);
		
		lbllv = new JLabel("张三");
		lbllv.setBounds(42, 34, 55, 16);
		infopanel.add(lbllv);
		
		lblviplv = new JLabel("张三");
		lblviplv.setBounds(42, 62, 55, 16);
		infopanel.add(lblviplv);
		
		lblyb = new JLabel("张三");
		lblyb.setBounds(42, 90, 55, 16);
		infopanel.add(lblyb);
		
		lblmc = new JLabel("张三");
		lblmc.setBounds(146, 6, 55, 16);
		infopanel.add(lblmc);
		
		lblls = new JLabel("张三");
		lblls.setBounds(146, 34, 55, 16);
		infopanel.add(lblls);
		
		lblbt = new JLabel("张三");
		lblbt.setBounds(146, 62, 55, 16);
		infopanel.add(lblbt);
		
		lbljb = new JLabel("张三");
		lbljb.setBounds(146, 90, 55, 16);
		infopanel.add(lbljb);
		
		login = new JPanel();
		login.setBorder(BorderFactory.createTitledBorder("登录"));
		login.setBounds(6, 6, 191, 144);
		mainPanel.add(login);
		login.setLayout(null);
		functionpanel = new JPanel();
		functionpanel.setBorder(BorderFactory.createTitledBorder("功能"));
		functionpanel.setBounds(6, 162, 1018, 301);
		mainPanel.add(functionpanel);
		
		logpanel = new JPanel();
		logpanel.setBorder(BorderFactory.createTitledBorder("挂机日志"));
		logpanel.setBounds(6, 475, 1018, 165);
		mainPanel.add(logpanel);
		logpanel.setLayout(null);
		
		
		txtlog = new TextArea();
		txtlog.setEditable(false);
		txtlog.setBounds(10, 21, 998, 134);
		logpanel.add(txtlog);
		
		JLabel lblNewLabel = new JLabel("服务器：");
		lblNewLabel.setBounds(6, 23, 52, 16);
		login.add(lblNewLabel);
		
		JComboBox serverCbx = new JComboBox();
		serverCbx.setModel(new DefaultComboBoxModel(new String[] {"傲世堂"}));
		serverCbx.setBackground(null);
		serverCbx.setBounds(57, 19, 114, 27);
		login.add(serverCbx);
		
		JLabel label = new JLabel("用户名：");
		label.setBounds(6, 51, 52, 16);
		login.add(label);
		
		JLabel label_1 = new JLabel("密   码：");
		label_1.setBounds(6, 81, 52, 16);
		login.add(label_1);
		
		txtuname = new JTextField();
		txtuname.setText("qq1605000002");
		txtuname.setBounds(57, 46, 114, 26);
		login.add(txtuname);
		txtuname.setColumns(10);
		
		txtpwd = new JPasswordField();
		txtpwd.setBounds(57, 76, 114, 26);
		login.add(txtpwd);
		
		JButton btnlogin = new JButton("登录");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Login.login("https://bbs.aoshitang.com/", txtuname.getText(), "a123456+");
							} catch (Exception e) {
								LogUtils.logstatus(e.getMessage());
							}
						}
					}).start();
			}
		});
		btnlogin.setBounds(57, 109, 75, 29);
		login.add(btnlogin);
		
		lblsoftstatus = new JLabel("软件状态");
		lblsoftstatus.setBounds(88, 652, 936, 16);
		mainPanel.add(lblsoftstatus);
		
		JLabel lblNewLabel_2 = new JLabel("运行状态：");
		lblNewLabel_2.setBounds(6, 652, 70, 16);
		mainPanel.add(lblNewLabel_2);
		
		
		
		
	}
}
