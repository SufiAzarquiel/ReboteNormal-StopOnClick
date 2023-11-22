package shared;

public class MovementFlag {
	private int state = 0;

	public MovementFlag() {
		super();
	}

	public boolean isMoving(int windowNumber) {
		boolean moving = true;

		// Clicking windows in order stops each window clicked
		// If you miss, all windows start moving again
		switch (state) {
			case 0:
				moving = true;
				break;
			case 1:
                moving = windowNumber != 1;
				break;
			case 2:
                if (windowNumber == 1 || windowNumber == 2)
					moving = false;
				break;
			case 3:
				moving = false;
				break;
		}
		return moving;
	}

	public void updateMoving(int windowNumber) {
		switch (state) {
			case 0:
				if (windowNumber == 1) {
					state = 1;
				}
				notifyAll();
				break;
			case 1:
				state = 2;
				if (windowNumber != 2) {
					state = 0;
					notifyAll();
				}
				break;
			case 2:
				state = 3;
				if (windowNumber != 3) {
					state = 0;
					notifyAll();
				}
				break;
			case 3:
				break;
		}
		System.out.println("state after click: " + state);
		System.out.println("Clicked window: " + windowNumber);
	}
}
