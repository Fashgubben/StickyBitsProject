import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FleetGUI extends JFrame {
	private JButton[][] buttonCoordinate;

	public FleetGUI() {
		super("Armada");
		// Creates Grid 100x100 and puts buttons on it, with dark greencolor
		// can access them with y x coordinate

		setLayout(new GridLayout(100, 100));
		buttonCoordinate = new JButton[100][100];

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				buttonCoordinate[i][j] = new JButton();
				buttonCoordinate[i][j].setText("");
				buttonCoordinate[i][j].setBackground(Color.GREEN.darker());
				buttonCoordinate[i][j].setOpaque(true);
				add(buttonCoordinate[i][j]);
			}
		}

	}

	public void changeColor(int shipid, int[] coords) {

		// changes color on button depending what id the ship has

		if (shipid == 1) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.RED);
		} else if (shipid == 2) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.BLACK.brighter());
		} else if (shipid == 3) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.BLUE);
		} else if (shipid == 4) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.CYAN);
		} else if (shipid == 5) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.DARK_GRAY.brighter());
		} else if (shipid == 6) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.MAGENTA);
		} else if (shipid == 7) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.ORANGE);
		} else if (shipid == 8) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.PINK);
		} else if (shipid == 9) {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.WHITE);
		} else {
			this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.YELLOW);
		}

		setHarbour();

	}

	public void restoreColor(int[] coords) {
		// Sets the previous color coord to dark green, there by removing a trail where
		// the ships have traveled

		this.buttonCoordinate[coords[0]][coords[1]].setBackground(Color.GREEN.darker());
	}

	public void setHarbour() {

		// paints harbour on map

		this.buttonCoordinate[0][0].setBackground(Color.BLACK);
		this.buttonCoordinate[0][1].setBackground(Color.BLACK);
		this.buttonCoordinate[1][0].setBackground(Color.BLACK);
		this.buttonCoordinate[1][1].setBackground(Color.BLACK);

		this.buttonCoordinate[0][99].setBackground(Color.BLACK);
		this.buttonCoordinate[0][98].setBackground(Color.BLACK);
		this.buttonCoordinate[1][98].setBackground(Color.BLACK);
		this.buttonCoordinate[1][99].setBackground(Color.BLACK);

		this.buttonCoordinate[99][0].setBackground(Color.BLACK);
		this.buttonCoordinate[98][0].setBackground(Color.BLACK);
		this.buttonCoordinate[99][1].setBackground(Color.BLACK);
		this.buttonCoordinate[98][1].setBackground(Color.BLACK);

		this.buttonCoordinate[99][98].setBackground(Color.BLACK);
		this.buttonCoordinate[98][99].setBackground(Color.BLACK);
		this.buttonCoordinate[99][99].setBackground(Color.BLACK);
		this.buttonCoordinate[98][98].setBackground(Color.BLACK);

		// middle
		this.buttonCoordinate[47][48].setBackground(Color.BLACK);
		this.buttonCoordinate[48][49].setBackground(Color.BLACK);
		this.buttonCoordinate[48][50].setBackground(Color.BLACK);
		this.buttonCoordinate[48][51].setBackground(Color.BLACK);
		this.buttonCoordinate[47][52].setBackground(Color.BLACK);

		this.buttonCoordinate[49][49].setBackground(Color.BLACK);
		this.buttonCoordinate[49][50].setBackground(Color.BLACK);
		this.buttonCoordinate[49][49].setBackground(Color.BLACK);
		this.buttonCoordinate[49][51].setBackground(Color.BLACK);
		
		this.buttonCoordinate[51][48].setBackground(Color.BLACK);
		this.buttonCoordinate[51][52].setBackground(Color.BLACK);

		this.buttonCoordinate[50][50].setBackground(Color.BLACK);
		this.buttonCoordinate[50][49].setBackground(Color.BLACK);
		this.buttonCoordinate[50][51].setBackground(Color.BLACK);
	}

}