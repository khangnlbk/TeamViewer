package client;

import java.awt.BorderLayout;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class StartFrame extends JFrame {

	private JPanel contentPane, mainPnl;
	private BufferedImage tvicon;
	private ClientPanel clientPnl;
	private ServerPanel serverPnl;
	private static StartFrame frame;
	public int screen = 9000, mousemove = 9001, mouseclick = 9002, keyboard = 9003, chat = 9004;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				      UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
					frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}

	public int getMousemove() {
		return mousemove;
	}

	public void setMousemove(int mousemove) {
		this.mousemove = mousemove;
	}

	public int getMouseclick() {
		return mouseclick;
	}

	public void setMouseclick(int mouseclick) {
		this.mouseclick = mouseclick;
	}

	public int getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(int keyboard) {
		this.keyboard = keyboard;
	}

	public int getChat() {
		return chat;
	}

	public void setChat(int chat) {
		this.chat = chat;
	}

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 380);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSetup = new JMenu("C\u00E0i \u0110\u1EB7t");
		
		
		
		menuBar.add(mnSetup);

		JCheckBoxMenuItem cbmClient = new JCheckBoxMenuItem("Client");
		
		
		
		cbmClient.setSelected(true);
		mnSetup.add(cbmClient);

		JCheckBoxMenuItem cbmServer = new JCheckBoxMenuItem("Server");
		
		mnSetup.add(cbmServer);

		ButtonGroup mnItem = new ButtonGroup();
		mnItem.add(cbmServer);
		mnItem.add(cbmClient);
		
		JMenuItem mnCon = new JMenuItem("K\u1EBFt N\u1ED1i");
		mnCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectFrame cf = new ConnectFrame(frame,serverPnl);
				cf.setVisible(true);
				cf.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}
		});
		mnSetup.add(mnCon);

		JMenu mnHelp = new JMenu("Tr\u1EE3 Gi\u00FAp");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Tr\u1EE3 Gi\u00FAp");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpFrame frame = new HelpFrame();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}
		});
		mnHelp.add(mntmHelp);

		JMenuItem mntmAbout = new JMenuItem("About TeamViewer");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutFrame frame = new AboutFrame();
				frame.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblSouth = new JLabel(
				"Ph\u1EA7n m\u1EC1m n\u00E0y \u0111\u01B0\u1EE3c ph\u00E1t tri\u1EC3n b\u1EDFi anonymous588");
		lblSouth.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblSouth, BorderLayout.SOUTH);
		serverPnl = new ServerPanel(this);
		clientPnl = new ClientPanel(this);
		mainPnl = new JPanel(new CardLayout());
		mainPnl.add(clientPnl, "Client");
		mainPnl.add(serverPnl, "Server");
		contentPane.add(mainPnl, BorderLayout.CENTER);
		cbmClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (mainPnl.getLayout());
				cl.show(mainPnl, "Client");
			}
		});
		cbmServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (mainPnl.getLayout());
				cl.show(mainPnl, "Server");
			}
		});
		
		LoadImage load = new LoadImage();
		tvicon = load.loadImage("icon.png");
		setIconImage(tvicon);
		setTitle("TeamViewer by anonymous588");
	}

}
