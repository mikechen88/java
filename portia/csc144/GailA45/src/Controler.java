// Assignment 3
// Programmer: Gail Thynes
// Exercise intended to learn Model View, plus get more familiar with Interfaces and Abstract classes
// by creating 2 shapes(diamond and plus) on JPanel
//when click the correct graphic area, the pointed graphic will automatic add level or reduce level
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Controler implements MouseListener {
	int level = 0;
	DrawModel model;
	JPanel jpanel;

	JRadioButton one, two;

	ButtonGroup group;

	JButton resume;

	int increment = 1;

	List<AbstractShape> bb;

	int index;

	boolean already = true;

	boolean add = true;

	public Controler(DrawModel model, JPanel jpanel) {
		this.model = model;
		this.jpanel = jpanel;

		bb = model.getShapes();

		group = new ButtonGroup();
		one = new JRadioButton("Increase");
		one.setSelected(true);
		one.addActionListener(al);
		group.add(one);
		jpanel.add(one);

		two = new JRadioButton("Decrease");
		two.addActionListener(al);
		group.add(two);

		jpanel.add(two);

		resume = new JButton("Reset");

		jpanel.add(resume);
		resume.addActionListener(al);

	}

	ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// JRadioButton radio = (JRadioButton) e.getSource();
			if (e.getSource() == one) {
				add = true;
			} else if (e.getSource() == two) {
				add = false;
			} else if (e.getSource() == resume) {
				level = 0;
				for (AbstractShape v : bb) {
					v.setLevelZero();
				}
				model.cycle();
			}

		}
	};

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		int x = e.getX();
		int y = e.getY();
		// System.out.println("x  =  " + x + "      y=" + y);
		if (!containShape(x, y))
			return;

		if (add) {
			increase();
		} else {
			decrease();

		}
	}

	public boolean increase() {

		level = Math.abs(increment);
		int height = model.getShapes().get(index).getHeight();
		if (height / 2 <= 1) {
			// if less than 1 show message and throw exception
			JOptionPane.showMessageDialog(null,
					"height less than 1 will kill my computer", "information",
					JOptionPane.INFORMATION_MESSAGE);

			throw new lessThan1("height is less than 1, I have to give up");

		}
		model.getShapes().get(index).addLevel(level);

		model.cycle();

		return true;
	}

	public boolean decrease() {
		level = -Math.abs(increment);
		if (model.getShapes().get(index).getLevel() <= 0) {
			level = 0;
			return false;
		}
		model.getShapes().get(index).addLevel(level);

		model.cycle();

		return true;
	}

	private boolean containShape(int x, int y) {
		// TODO Auto-generated method stub

		for (int i = 0; i < bb.size(); i++) {
			if (bb.get(i).getBound().contains(x, y)) {

				index = i;
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		// Create a graphics view and put it in a window
		JFrame frame = new JFrame("Shapes");
		View view = new View();
		frame.getContentPane().setBackground(Color.BLACK);
		// frame.getContentPane().add(view);
		frame.setSize(1500, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(view);
		frame.getContentPane().add(view);
		frame.validate();
		frame.setVisible(true);

		// Create the simulation model and populate it
		DrawModel shapes = new DrawModel();

		shapes.add(new Diamond(300, 400, 200, 0));
		shapes.add(new Plus(800, 400, 200, 0));

		// Connect the view to the simulation and let it run

		shapes.addView(view);

		Controler controler = new Controler(shapes, view);

		// add controler
		frame.addMouseListener(controler);

	}

}

class lessThan1 extends RuntimeException {
	public lessThan1(String s) {
		super(s);
	}
}
