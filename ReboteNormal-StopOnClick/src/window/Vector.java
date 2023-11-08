package window;

public class Vector {
	private int x;
	private int y;
	private int MAXx;
	private int MAXy;
	private int angulo; // en grados enteros
	private int mod;
	
	public Vector(int mx,int my,int pmod){
		MAXx=mx;
		MAXy=my;
		mod=pmod;
		//x=(int)(Math.random()*MAXx+1);
		//y=(int)(Math.random()*MAXy+1);
		x=MAXx/2;
		y=MAXy/2;
		angulo=(int)(Math.random()*360+1) ;
		System.out.println(angulo);
	}
	public void nextPos(){
		int x0=x;
		int y0=y;
		y0+=(int)(Math.sin(Math.toRadians(angulo))*mod);
		x0+=(int)(Math.cos(Math.toRadians(angulo))*mod);
		// Si x o y se pasan de 0 o de MAX tengo que modificar el �ngulo
		if (x0<0 || x0> MAXx) 
		  angulo=180-angulo;
		else {
		  if (y0<0 || y0>MAXy)
		    angulo=360-angulo;
 		  else {
			x=x0;
			y=y0;
		  }
		}
		//System.out.println(x+"-"+y);
	}
	public int cx(){
		return x;
	}
	public int cy(){
		return y;
	}

}

