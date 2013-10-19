// From the UW
// Represents the overall graphical user interface for the Critter simulation.
//
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class CritterGui implements ActionListener, ChangeListener, Observer {
	// class constants
	private static final long serialVersionUID = 0;

	private static final String TITLE = "Critter Safari";

	private static final int DELAY = 100;

	// Helper method to create a JButton with the given properties.
	public static JButton createButton(String text, char mnemonic,
			ActionListener listen, Container panel) {
		JButton button = new JButton(text);
		button.setMnemonic(mnemonic);
		button.addActionListener(listen);
		panel.add(button);
		return button;
	}

	// Helper method to create a JCheckBox with the given properties.
	public static JCheckBox createCheckBox(String text, char mnemonic,
			ActionListener listen, Container panel) {
		JCheckBox box = new JCheckBox(text);
		box.setMnemonic(mnemonic);
		box.addActionListener(listen);
		panel.add(box);
		return box;
	}

	public static JSlider createSlider(int min, int max, int initial,
			int majorTick, int minorTick, ChangeListener listen, Container panel) {
		JSlider slider = new JSlider(min, max, initial);
		slider.setMajorTickSpacing(majorTick);
		slider.setMinorTickSpacing(minorTick);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(listen);
		panel.add(slider);
		return slider;
	}

	// fields
	private JFrame frame;

	private CritterModel model;

	private CritterPanel panel;

	private javax.swing.Timer timer;

	private JLabel[] counts;

	private JButton go, stop, tick;

	private JSlider slider;

	// Constructs a new GUI to display the given model of critters.
	public CritterGui(CritterModel model) {
		this.model = model;
		model.addObserver(this);

		// set up critter picture panel and set size
		panel = new CritterPanel(model);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		addTimer();

		// set up timing slider

		// timer controls
		JPanel south1 = new JPanel();
		go = createButton("Go", 'G', this, south1);
		stop = createButton("Stop", 'S', this, south1);
		tick = createButton("Tick", 'T', this, south1);
		JPanel south2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
		// slider = createSlider(5, 50, 1000 / DELAY, 5, 1, this, south2);

		Container south = Box.createVerticalBox();
		south.add(south1);
		south.add(south2);

		JPanel center = new JPanel();
		center.add(panel);

		// create frame and do layout
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		// frame.add(north, BorderLayout.NORTH);
		frame.add(center, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
	}

	// Responds to action events in the GUI.
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == go) {
			timer.start();
		} else if (src == stop) {
			timer.stop();
		} else if (src == tick && !timer.isRunning()) {
			doOneStep();
		}
	}

	// Responds to change events on the slider.
	public void stateChanged(ChangeEvent e) {
		int fps = slider.getValue();
		timer.setDelay(1000 / fps);
	}

	// Starts the simulation. Assumes all critters have already been added.
	public void start() {
		addClassCounts();
		frame.pack();
		frame.setVisible(true);
	}

	// Responds to Observable updates in the model.
	public void update(Observable o, Object arg) {
		setCounts();
	}

	// Adds right-hand column of labels showing how many of each type are alive.
	private void addClassCounts() {
		Set<Map.Entry<String, Integer>> entries = model.getCounts();
		JPanel p = new JPanel(new GridLayout(entries.size(), 1));
		// JPanel p = new JPanel();
		// p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(BorderFactory.createTitledBorder("Totals:"));
		counts = new JLabel[entries.size()];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = new JLabel();
			p.add(counts[i]);
		}
		frame.add(p, BorderLayout.EAST);
		setCounts();
	}

	// Updates the counter labels to store the current count information.
	private void setCounts() {
		int i = 0;
		Set<Map.Entry<String, Integer>> countSet = model.getCounts();
		for (Map.Entry<String, Integer> entry : countSet) {
			counts[i].setText(entry.toString());
			i++;
		}
	}

	// Creates a timer to call model's update method and repaint display.
	private void addTimer() {
		ActionListener updater = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOneStep();
			}
		};
		timer = new javax.swing.Timer(DELAY, updater);
		timer.setCoalesce(true);
	}

	// one step of the simulation
	private void doOneStep() {
		model.update();
	}
}
