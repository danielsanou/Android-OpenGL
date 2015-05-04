package com.daniel.sanou.android.opengl.objects.others;

import com.daniel.sanou.android.opengl.objects.common.GLObject;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class RosesHead implements GLObject {

    //private static final String TAG = Torus.class.getSimpleName();
    //Log.d(TAG, "------------Begin of draw---------------------");
    private FloatBuffer torusVerticesBuffer;
    private float[][] torusVertices = new float[32][3];

    float R = 1.5f;
    float r = 0.6f;
    int maxn = 8; // max precision

    public RosesHead(){
        super();
        ByteBuffer byteBuffer;
        byteBuffer = ByteBuffer.allocateDirect(torusVertices.length * torusVertices[0].length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        torusVerticesBuffer = byteBuffer.asFloatBuffer();
    }

    public void draw(GL10 gl)
    {
        int N = 35, n = 35;
        double dv=2*Math.PI/Math.min(n,maxn-1);
        double dw=2*Math.PI/Math.min(N,maxn-1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        // outer loop
        for(double w=0.0f; w<2*Math.PI+dw; w+=dw)
        {
            // inner loop
            int    ni = 0;
            for(double v=0.0f; v<2*Math.PI+dv; v+=dv)
            {
                torusVertices[ni][0] = (float) ((R + r * Math.cos(v)) * Math.cos(w));
                torusVertices[ni][1] = (float) ((R + r * Math.cos(v)) * Math.sin(w));
                torusVertices[ni][2] = (float) (r * Math.sin(v));
                torusVerticesBuffer.put(torusVertices[ni]);

                torusVertices[ni + 1][0] = (float) ((R+r*Math.cos(v+dv))*Math.cos(w + dw));
                torusVertices[ni + 1][1] = (float) ((R+r*Math.cos(v+dv))*Math.sin(w + dw));
                torusVertices[ni + 1][2] = (float) (r*Math.sin(v+dv));
                torusVerticesBuffer.put(torusVertices[ni + 1]);

                ni += 2;

                torusVerticesBuffer.position(0);
                gl.glVertexPointer(3, GL10.GL_FLOAT, 0, torusVerticesBuffer);
                gl.glNormalPointer(GL10.GL_FLOAT, 0, torusVerticesBuffer);
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, ni);
            } // inner loop
        } //outer loop

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }

}
