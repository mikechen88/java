import javax.swing.JFrame;

public class MainClass {
	// the model to store the data ( = the list of the squares )

	public static void main(String[] args) {

		ModelOfSquares model = new ModelOfSquares();
		// graphical display
		JFrame frame = new JFrame("Squares widh MVC");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// views
		PanelOfSquares panel = new PanelOfSquares();
		frame.add(panel);
		ConsoleView console = new ConsoleView();
		model.addView(panel);
		model.addView(console);

		// controller
		SquaresController controller = new SquaresController(model);
		frame.addKeyListener(controller);
		panel.addMouseListener(controller);

		// show it all
		frame.setVisible(true);
		
	/*for (int i=0;i<100000;i++){
		model.addSquare(500, 400);
	}*/
	}
}