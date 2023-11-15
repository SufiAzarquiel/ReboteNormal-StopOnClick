package shared;

public class MovementFlag {
	private boolean moving;

	public MovementFlag(boolean moving) {
		super();
		this.moving = moving;
	}
	
	public MovementFlag() {
		super();
		this.moving = true;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void notifyAllWindows() {
		this.notifyAll();
	}
}
