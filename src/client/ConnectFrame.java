package client;


import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnectFrame extends JFrame{
	private JPanel contentPane;
	private JTextField tfScreen;
	private JTextField tfClick;
	private JTextField tfMouse;
	private JTextField tfKey;
	private JTextField tfChat;
	private StartFrame sf;
	
	public ConnectFrame(StartFrame sf, ServerPanel serverPnl) {
		this.sf = sf;
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		JLabel lblScreen = new JLabel("Screen");
		contentPane.add(lblScreen, "cell 0 0,alignx left");
		
		tfScreen = new JTextField("");
		contentPane.add(tfScreen, "cell 1 0,growx");
		tfScreen.setColumns(10);
		tfScreen.setText(String.valueOf(sf.getScreen()));
		
		JLabel lblMouse = new JLabel("Mouse Move");
		contentPane.add(lblMouse, "cell 0 1,alignx left");
		
		tfMouse = new JTextField();
		contentPane.add(tfMouse, "cell 1 1,growx");
		tfMouse.setColumns(10);
		tfMouse.setText(String.valueOf(sf.getMousemove()));
		
		JLabel lblClick = new JLabel("Mouse Click");
		contentPane.add(lblClick, "cell 0 2,alignx left");
		
		tfClick = new JTextField();
		contentPane.add(tfClick, "cell 1 2,growx");
		tfClick.setColumns(10);
		tfClick.setText(String.valueOf(sf.getMouseclick()));
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sf.setScreen(Integer.parseInt(tfScreen.getText()));
				sf.setMousemove(Integer.parseInt(tfMouse.getText()));
				sf.setMouseclick(Integer.parseInt(tfClick.getText()));
				sf.setKeyboard(Integer.parseInt(tfKey.getText()));
				sf.setChat(Integer.parseInt(tfChat.getText()));
				dispose();
				serverPnl.updateLabel();
				serverPnl.repaint();
				sf.setEnabled(true);
			}
		});
		
		JLabel lblRelease = new JLabel("Keyboard");
		contentPane.add(lblRelease, "cell 0 4,alignx left");
		
		tfKey = new JTextField();
		contentPane.add(tfKey, "cell 1 4,growx");
		tfKey.setColumns(10);
		tfKey.setText(String.valueOf(sf.getKeyboard()));
		
		JLabel lblChat = new JLabel("Chat");
		contentPane.add(lblChat, "cell 0 5,alignx left");
		
		tfChat = new JTextField();
		contentPane.add(tfChat, "cell 1 5,growx");
		tfChat.setColumns(10);
		tfChat.setText(String.valueOf(sf.getChat()));
		contentPane.add(btnOk, "flowx,cell 1 6");
		
		JButton btnCancel = new JButton("H\u1EE7y");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				sf.setEnabled(true);
			}
		});
		contentPane.add(btnCancel, "cell 1 6");
		pack();
	}
}
