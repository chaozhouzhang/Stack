package android.stack.opengles;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class TriangleGLSurfaceView extends GLSurfaceView {
    private TriangleGLRenderer mTriangleGLRenderer;

    public TriangleGLSurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        mTriangleGLRenderer = new TriangleGLRenderer();
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mTriangleGLRenderer);
        // Set the RenderMode
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    public TriangleGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
}
