package engine;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class BasketEngine implements GLEventListener, BasketConfiguration {

	private JFrame window;
	private FPSAnimator fpsAnimator;
	
    public GLU glu;
	private GLCanvas canvas;	
	
	public void start(){
		window = new JFrame();
        canvas = new GLCanvas(getCapabilites());
		glu = new GLU();
		fpsAnimator = new FPSAnimator(canvas, FPS);
		
		//Configure Canvas
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		canvas.addGLEventListener(this);
		
		//Configure Window
		window.setLocation(20, 20);
		window.getContentPane().add(canvas);
		window.setUndecorated(true); // Remove title bar
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(canvas);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		window.requestFocus();
        canvas.requestFocus();
	}
	
	GLCapabilities getCapabilites() {
		GLCapabilities capabilities = new GLCapabilities(GLProfile.getDefault());
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
		return capabilities;
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
    	drawable.setGL(gl);
    	
        // Global settings.
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); 
		gl.glClearDepth(1.0f);		
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);		
        
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
    	
    	// Change to projection matrix.
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        
        // Perspective.
        float widthHeightRatio = (float) canvas.getWidth() / (float) canvas.getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, CAMERA_DISTANCE);
    
        // Change back to model view matrix.
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // Clear color and depth buffers
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

}
