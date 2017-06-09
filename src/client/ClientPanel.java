package client;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientPanel extends JPanel {
	private JTextField textField;
	private StartFrame frame;

	public ClientPanel(StartFrame frame) {

		LoadImage load = new LoadImage();
		this.frame = frame;
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		ImageIcon img = new ImageIcon(load.loadImage("logo.png"));
		label.setIcon(img);

		JLabel lblCntto = new JLabel("K\u1EBFt n\u1ED1i t\u1EDBi :");

		textField = new JTextField();

		textField.setToolTipText(
				"\u0110\u1ECBa ch\u1EC9 n\u00E0y l\u00E0 \u0111\u1ECBa ch\u1EC9 IP c\u1EE7a m\u00E1y ch\u1EE7 trong m\u1EA1ng n\u1ED9i b\u1ED9!");
		textField.setColumns(10);

		JLabel lblCnttodo = new JLabel("K\u1EBFt n\u1ED1i \u0111\u1EC3 :");

		JRadioButton rdbtnRem = new JRadioButton("\u0110i\u1EC1u Khi\u1EC3n");

		JRadioButton rdbtnRec = new JRadioButton("Quay M\u00E0n H\u00ECnh v\u00E0 Trao \u0110\u1ED5i");

		JButton btnStt = new JButton("B\u1EAFt \u0110\u1EA7u!");
		btnStt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = textField.getText();
				if (!ip.isEmpty()) {
					if (rdbtnRem.isSelected()) {
						Remote rem = new Remote(ip, frame.getScreen(), frame.getMousemove(), frame.getMouseclick(),
								frame.getKeyboard());
						frame.hide();
					} else if (rdbtnRec.isSelected()) {
						RecordAndChat rec = new RecordAndChat(ip, frame.getScreen(), frame.getChat());
						frame.hide();
					}
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(23).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblCntto).addComponent(lblCnttodo))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 203,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup().addComponent(rdbtnRem)
														.addGap(18).addComponent(rdbtnRec))))
								.addGroup(groupLayout.createSequentialGroup().addGap(171).addComponent(btnStt)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addGap(53)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblCntto).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblCnttodo)
						.addComponent(rdbtnRem).addComponent(rdbtnRec))
				.addGap(18).addComponent(btnStt).addContainerGap(55, Short.MAX_VALUE)));
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnRec);
		group.add(rdbtnRem);
		setLayout(groupLayout);
	}
}
