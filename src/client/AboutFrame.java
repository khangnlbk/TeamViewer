package client;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AboutFrame extends JFrame {

	private JPanel contentPane;
	private String about = "<html>\n"+"<p>Về tác giả : Nguyễn Tiến Linh, K60 Máy tính & KHTT, Đại học Khoa học Tự Nhiên, Đại học Quốc Gia Hà Nội."+
"<br>\n"+
"Facebook : <a href=https://facebook.com/linh.tiensinh>https://facebook.com/linh.tiensinh";

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public AboutFrame() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		LoadImage load = new LoadImage();
		BufferedImage image = load.loadImage("logo.png");
		ImageIcon img = new ImageIcon(image.getScaledInstance(450,100,image.SCALE_SMOOTH));
		JLabel logo = new JLabel("");
		logo.setIcon(img);
		panel.add(logo, BorderLayout.NORTH);
		
		JLabel label = new JLabel();
		label.setText(about );
		panel.add(label, BorderLayout.CENTER);
		setTitle("Về TeamViewer");
		setBounds(100,100,400,250);
	}

}
