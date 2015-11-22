package objects;

import com.jogamp.opengl.GL2;

public class GameObject {
	
	public float x, y, z, radius;	
	public GL2 gl;
			
	public void draw(GL2 gl) {
		this.gl = gl;
	}

}
