package client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import server.Server;
import javax.swing.JRadioButton;

public class ServerPanel extends JPanel implements ActionListener {
	private Server server;
	JCheckBox chckbxTing;
	private JButton btnBtu;
	private JRadioButton rdbtnServerRemote,rdbtnRecordAndChat;
	private StartFrame sf;
	private JLabel lblChaR;
	
	public ServerPanel(StartFrame frame) {
		sf = frame;
		LoadImage load = new LoadImage();
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		ImageIcon img = new ImageIcon(load.loadImage("logo.png"));
		label.setIcon(img);
		
		JLabel lblaChMy = new JLabel("\u0110\u1ECBa Ch\u1EC9 M\u00E1y B\u1EA1n l\u00E0 :");
		lblaChMy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel = new JLabel();
		InetAddress adr = null;
		try {
			adr = Inet4Address.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblNewLabel.setText(adr.getHostAddress());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCngKtNi = new JLabel("C\u1ED5ng K\u1EBFt N\u1ED1i         :");
		lblCngKtNi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		lblChaR = new JLabel();
		btnBtu = new JButton("B\u1EAFt \u0110\u1EA7u!");
		btnBtu.addActionListener(this);
		
		
		lblChaR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		chckbxTing = new JCheckBox("T\u00F4i \u0111\u1ED3ng \u00FD l\u00E0m M\u00E1y Ch\u1EE7 \u0111\u1EC3 m\u00E1y kh\u00E1c c\u00F3 th\u1EC3 \u0111i\u1EC1u khi\u1EC3n v\u00E0 quay m\u00E0n h\u00ECnh");
		
		rdbtnServerRemote = new JRadioButton("Remote Server");
		
		rdbtnRecordAndChat = new JRadioButton("Record and Chat Server");
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnRecordAndChat);
		group.add(rdbtnServerRemote);
		rdbtnServerRemote.setSelected(true);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addComponent(lblCngKtNi)
					.addGap(18)
					.addComponent(lblChaR))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblaChMy)
					.addGap(18)
					.addComponent(lblNewLabel))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(179)
					.addComponent(btnBtu))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(chckbxTing))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(75)
					.addComponent(rdbtnServerRemote)
					.addGap(18)
					.addComponent(rdbtnRecordAndChat))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblaChMy)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCngKtNi)
						.addComponent(lblChaR))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnServerRemote)
						.addComponent(rdbtnRecordAndChat))
					.addGap(16)
					.addComponent(chckbxTing)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBtu)
					.addContainerGap())
		);
		setLayout(groupLayout);
		updateLabel();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(chckbxTing.isSelected()){
			if(rdbtnServerRemote.isSelected()){
			server = new Server(sf.getScreen(), sf.getMouseclick(),sf.getMousemove(),sf.getKeyboard());
			}else{
				server = new Server(sf.getScreen(), sf.getChat());
			}
			btnBtu.setEnabled(false);
		}
		else
			JOptionPane.showMessageDialog(null, "Bạn tích đồng ý để bắt đầu!");
	}
	
	public void updateLabel(){
		if(checkPort()){
			lblChaR.setText("Có thể dùng");
			lblChaR.setToolTipText("Các cổng kết nối được dùng để mở Server không bị sử dụng bởi bất cứ ứng dụng nào khác trên máy đang hoạt động, đã bắt đầu có thể mở Server và sử dụng");
			lblChaR.setForeground(Color.GREEN);
			btnBtu.setEnabled(true);
		}else{
			lblChaR.setText("Đã được sử dụng");
			lblChaR.setToolTipText("Các cổng kết nối được dùng để mở Server đã được sử dụng bởi ứng dụng khác đang hoạt động, vui lòng sửa mã nguồn của Server để đổi cổng khác");
			lblChaR.setForeground(Color.RED);
			btnBtu.setEnabled(false);
		}
	}
	
	public boolean checkPort(){
		ServerSocket s,s1,s2,s3,s4;
		try{
			s = new ServerSocket(sf.getScreen());
			s1= new ServerSocket(sf.getMouseclick());
			s2= new ServerSocket(sf.getMousemove());
			s3= new ServerSocket(sf.getKeyboard());
			s4 = new ServerSocket(sf.getChat());
		}catch(IOException e){
			return false;
		}
		try{
			s.close();
			s1.close();
			s2.close();
			s3.close();
			s4.close();
		}catch(IOException e){
			System.exit(0);
		}
		
		return true;
	}

}
