package view;
import shared.MovementFlag;
import window.Window;

public class View {
 
	public static void main(String[] args) {
	  int MAXV=3;
	  MovementFlag flag = new MovementFlag();
	  for (int i=0;i<=MAXV-1;i++){
		  new Window(i, flag);
	  }

	}

}
