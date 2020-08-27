import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class FleetInfoGui extends JFrame {
	private JLabel info;

	public FleetInfoGui() {
		super("Info");
		setLayout(new FlowLayout());

		info = new JLabel();
		add(info);
	}

	public void getInfo(String getInfo) {
		this.info.setText("");
		this.info.setText(getInfo);
	}
}
