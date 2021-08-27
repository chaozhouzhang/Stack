package android.stack.opengles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TriangleGLRenderer implements GLSurfaceView.Renderer {
    private Triangle mTriangle;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // initialize a triangle
        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mTriangle.draw();
    }



    /**
     * 加载并编译着色器代码
     * 渲染器类型type={GLES20.GL_VERTEX_SHADER, GLES20.GL_FRAGMENT_SHADER}
     * 渲染器代码 GLSL
     */
    public static int loadShader(int type, String shaderCode){

        int shader = GLES20.glCreateShader(type);
        //加载shader代码 glShaderSource 和 glCompileShader
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
