package android.stack.opengles;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class StackGLSurfaceView extends GLSurfaceView {

    private StackGLRenderer mStackGLRenderer;

    public StackGLSurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        mStackGLRenderer = new StackGLRenderer();
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mStackGLRenderer);
        // Set the RenderMode
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    public StackGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
}
