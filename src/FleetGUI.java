import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FleetGUI extends JFrame {
	private JButton[][] f1;
	private JLabel info;

	public FleetGUI() {
		super("Armada");
		setLayout(new GridLayout(100, 100));

		f1 = new JButton[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				f1[i][j] = new JButton();
				f1[i][j].setText("");
				f1[i][j].setBackground(Color.GREEN.darker());
				f1[i][j].setOpaque(true);
//				f1[i][j].setBorderPainted(false);
				add(f1[i][j]);
			}
		}

	}

	public void changeColor(int shipid, int[] coords) {

		if (shipid == 1) {
			this.f1[coords[0]][coords[1]].setBackground(Color.RED);
		} else if (shipid == 2) {
			this.f1[coords[0]][coords[1]].setBackground(Color.BLACK.brighter());
		} else if (shipid == 3) {
			this.f1[coords[0]][coords[1]].setBackground(Color.BLUE);
		} else if (shipid == 4) {
			this.f1[coords[0]][coords[1]].setBackground(Color.CYAN);
		} else if (shipid == 5) {
			this.f1[coords[0]][coords[1]].setBackground(Color.DARK_GRAY.brighter());
		} else if (shipid == 6) {
			this.f1[coords[0]][coords[1]].setBackground(Color.MAGENTA);
		} else if (shipid == 7) {
			this.f1[coords[0]][coords[1]].setBackground(Color.ORANGE);
		} else if (shipid == 8) {
			this.f1[coords[0]][coords[1]].setBackground(Color.PINK);
		} else if (shipid == 9) {
			this.f1[coords[0]][coords[1]].setBackground(Color.WHITE);
		} else {
			this.f1[coords[0]][coords[1]].setBackground(Color.YELLOW);
		}

		setHarbour();

	}

	public void setHarbour() {
		this.f1[0][0].setBackground(Color.BLACK);
		this.f1[0][1].setBackground(Color.BLACK);
		this.f1[1][0].setBackground(Color.BLACK);
		this.f1[1][1].setBackground(Color.BLACK);

		this.f1[0][99].setBackground(Color.BLACK);
		this.f1[0][98].setBackground(Color.BLACK);
		this.f1[1][98].setBackground(Color.BLACK);
		this.f1[1][99].setBackground(Color.BLACK);

		this.f1[99][0].setBackground(Color.BLACK);
		this.f1[98][0].setBackground(Color.BLACK);
		this.f1[99][1].setBackground(Color.BLACK);
		this.f1[98][1].setBackground(Color.BLACK);

		this.f1[99][98].setBackground(Color.BLACK);
		this.f1[98][99].setBackground(Color.BLACK);
		this.f1[99][99].setBackground(Color.BLACK);
		this.f1[98][98].setBackground(Color.BLACK);

		// middle
		this.f1[47][48].setBackground(Color.BLACK);
		this.f1[48][49].setBackground(Color.BLACK);
		this.f1[48][50].setBackground(Color.BLACK);
		this.f1[48][51].setBackground(Color.BLACK);
		this.f1[47][52].setBackground(Color.BLACK);

		this.f1[49][49].setBackground(Color.BLACK);
		this.f1[49][50].setBackground(Color.BLACK);
		this.f1[49][49].setBackground(Color.BLACK);
		this.f1[49][51].setBackground(Color.BLACK);
		
		this.f1[51][48].setBackground(Color.BLACK);
		this.f1[51][52].setBackground(Color.BLACK);

		this.f1[50][50].setBackground(Color.BLACK);
		this.f1[50][49].setBackground(Color.BLACK);
		this.f1[50][51].setBackground(Color.BLACK);
	}

	public void restoreColor(int[] coords) {
		this.f1[coords[0]][coords[1]].setBackground(Color.GREEN.darker());
	}
}