package window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import shared.MovementFlag;

public class Window extends JFrame implements Runnable, ActionListener {
	/**
	 * sufiDev - November 2023
	 */
	private static final long serialVersionUID = 1L;
	private Dimension d;
	private Thread thread;
	private JButton btn;
	private MovementFlag flag;
	private Vector v;
	private final long DELAY;

	public Window(int i, MovementFlag flag) {
		super();
		d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width / 5, d.height / 5);
		String title = "Window number: " + i;
		setTitle(title);
		setResizable(false);
		setVisible(false);
		
		this.flag = flag;

		btn = new JButton("Exit");
		add(btn);
		btn.addActionListener(this);

		v = new Vector(d.width, d.height, 2);
		DELAY = 10;

		thread = new Thread(this);
		thread.setName(title);
		thread.start();
	}

	public void run() {
		// moving = true;
		flag.setMoving(true);
		while (true) {
			synchronized (flag) {
				// if (!moving) {
				if (!flag.isMoving()) {
					try {
						flag.wait();
					} catch (Exception e) {
					}
				}
			}

			v.nextPos();
			this.setLocation(v.cx(), v.cy());

			if (!isVisible())
				setVisible(true);

			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			handleButton();
		}
	}

	private void handleButton() {
		// moving = false;
		synchronized (flag) {
			flag.setMoving(!flag.isMoving());
			flag.notifyAll();
		}
	}
}
