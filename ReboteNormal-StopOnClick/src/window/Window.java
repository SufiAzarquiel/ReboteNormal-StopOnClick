package window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.Serial;

import javax.swing.JButton;
import javax.swing.JFrame;

import shared.MovementFlag;

public class Window extends JFrame implements Runnable, ActionListener {
	/**
	 * sufiDev - November 2023
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private final JButton btn;
	private final MovementFlag flag;
	private final Vector v;
	private final long DELAY;
	private final int localNumber;

	public Window(int i, MovementFlag flag) {
		super();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width / 5, d.height / 5);
		String title = "n: " + i;
		setTitle(title);
		setResizable(false);
		setVisible(false);
		
		this.flag = flag;
		localNumber = i;

		btn = new JButton("Exit");
		add(btn);
		btn.addActionListener(this);

		v = new Vector(d.width, d.height, 2);
		DELAY = 20;

		thread = new Thread(this);
		thread.setName(title);
		thread.start();
	}

	public void run() {
		while (true) {
			synchronized (flag) {
				// if (!moving) {
				while(!flag.isMoving(localNumber)) {
					try {
						flag.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
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
				throw new RuntimeException(e);
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
			flag.updateMoving(localNumber);
		}
	}
}
