package client;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpFrame extends JFrame {

	private JPanel contentPane;
	private String help1 = "<html>\n" +
			"<font size = 5 px, color=gray><b>Giới Thiệu</b> </font>"+
			"<p>Phần mềm TeamViewer này được viết bằng ngôn ngữ Java. Để sử dụng phần mềm này bạn cần cấp phép quyền truy cập cho nó nếu tường lửa có hỏi. Phần mềm này cho phép hai máy cùng trong mạng LAN có thể điều khiển và trao đổi màn hình cũng như chat với nhau. Mời bạn click các nút bên trái nếu như có thắc mắc về bất cứ mục nào tương ứng trong phần Cài Đặt</p>";
;
	private String help2 = "<html>\n" +
			"<font size = 5 px, color=gray><b>Client</b></font>"+
			"<p>Client là máy thực hiện điều khiển, ghi hình máy chủ. Để trở thành Client, bạn cần biết địa chỉ IP của máy chạy Server.</p><br>"+"Có hai tùy chọn:"+
			"<ul>\n"+
				"<li><strong>Điều Khiển</strong>: Bạn cần kết nối tới máy chạy Server điều khiển, bạn có thể điều khiển chuột và bàn phím.	</li>"+
				"<li><strong>Quay màn hình và trao đổi</strong>: Bạn cần kết nối tới máy chạy Server quay và trao đổi, bạn có thể nhìn được màn hình của máy Server và trao đổi nội dung với họ</li>";
	private String help3 = "<html>\n" +
			"<font size = 5 px, color=gray><b>Server</b></font>"+
			"<p>Client là máy chủ. Để trở thành Server, bạn cần tích đồng ý để trở thành máy chủ và click nút Bắt đầu.</p><br>"+"Có hai tùy chọn:"+
			"<ul>\n"+
				"<li><strong>Điều Khiển</strong>: Bạn sẽ được máy kết nối đến điều khiển chuột và bàn phím</li>"+
				"<li><strong>Quay màn hình và trao đổi</strong>: Bạn sẽ cho phép máy khác thấy màn hình của bạn và có khung chat để trao đổi</li>";
	private String help4 = "<html>\n" +
			"<font size = 5 px, color=gray><b>Kết Nối</b></font>"+
			"<p>Đây là các cài đặt về cổng kết nối để thực hiện các chức năng trong chương trình. Máy Client và Server cần thống nhất các cổng trong chương trình để sử dụng.</p><br>"+"Có 5 cổng:"+
			"<ul>\n"+
				"<li><strong>Screen</strong>: Cổng nhận và gửi màn hình</li>"+
				"<li><strong>Mouse Move</strong>: Cổng nhận và gửi hành động di chuyển của chuột</li>"+
				"<li><strong>Mouse Click</strong>: Cổng nhận và gửi hành động của các phím bấm trên chuột (lưu ý: Không hỗ trợ các phím mở rộng trên chuột)</li>"+
				"<li><strong>Keyboard</strong>: Cổng nhận và gửi hành động của bàn phím</li>";
	
	private JLabel label;
	

	/**
	 * Create the frame.
	 */
	public HelpFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel btnPnl = new JPanel();
		contentPane.add(btnPnl, BorderLayout.WEST);
		btnPnl.setLayout(new MigLayout("", "[89px]", "[][23px][][]"));
		btnPnl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Chọn các mục sau"),BorderFactory.createEmptyBorder(10,10,10,10)));
		
		JButton btnClient = new JButton("<html>\n" +
			"<strong>Client</strong>");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label.setText(help2);
			}
		});
		btnPnl.add(btnClient, "cell 0 0,growx");
		
		JButton btnServer = new JButton("<html>\n" +
				"<strong>Server</strong>");
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label.setText(help3);
			}
		});
		btnPnl.add(btnServer, "cell 0 1,growx");
		
		JButton btnKetNoi = new JButton("<html>\n" +
				"<strong>Kết Nối</strong>");
		btnKetNoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label.setText(help4);
			}
		});
		btnPnl.add(btnKetNoi, "cell 0 2,growx");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(help1);
		label.setVerticalAlignment(SwingConstants.TOP);
		panel.add(label, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Nội Dung"),BorderFactory.createEmptyBorder(10,10,10,10)));
		
		LoadImage load = new LoadImage();
		BufferedImage image = load.loadImage("logo.png");
		ImageIcon img = new ImageIcon(image.getScaledInstance(450,100,image.SCALE_SMOOTH));
		JLabel logo = new JLabel("");
		logo.setIcon(img);
		panel.add(logo, BorderLayout.NORTH);
		setTitle("Trợ giúp");
	}

}
