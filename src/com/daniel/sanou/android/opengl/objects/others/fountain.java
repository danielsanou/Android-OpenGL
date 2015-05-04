package com.daniel.sanou.android.opengl.objects.others;

import com.daniel.sanou.android.opengl.objects.common.GLObject;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class fountain implements GLObject {

    //private static final String TAG = Torus.class.getSimpleName();

    private FloatBuffer torusVerticesBuffer;
    private float[][] torusVertices = new float[30][3];

    float R = 0.5f;
    float r = 0.3f;
    int rings=10;
    int nsides = 20;

    public fountain(){
        super();
        ByteBuffer byteBuffer;
        byteBuffer = ByteBuffer.allocateDirect(torusVertices.length * torusVertices[0].length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        torusVerticesBuffer = byteBuffer.asFloatBuffer();
    }

    public void draw(GL10 gl)
    {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        float ringDelta = 2.0f * (float) Math.PI / rings;
        float sideDelta = 2.0f * (float) Math.PI / nsides;
        float theta = 0.0f, cosTheta = 1.0f, sinTheta = 0.0f;

        for (int i = rings - 1; i >= 0; i--) {
            float theta1 = theta + ringDelta;
            float cosTheta1 = (float) Math.cos(theta1);
            float sinTheta1 = (float) Math.sin(theta1);
            float phi = 0.0f;
            for (int j = nsides/2; j >= 0; j--) {
                phi += sideDelta;
                float cosPhi = (float) Math.cos(phi);
                float sinPhi = (float) Math.sin(phi);
                float dist = R + r * cosPhi;

                torusVertices[j] = new float[]{cosTheta1 * cosPhi, -sinTheta1 * cosPhi, sinPhi};
                torusVertices[j + 1] = new float[]{cosTheta1 * dist, -sinTheta1 * dist, r * sinPhi};
                torusVertices[j + 2] = new float[]{cosTheta * cosPhi, -sinTheta * cosPhi, sinPhi};
                torusVertices[j + 3] = new float[]{cosTheta * dist, -sinTheta * dist, r * sinPhi};

                torusVerticesBuffer.put(torusVertices[j]);
                torusVerticesBuffer.put(torusVertices[j + 1]);
                torusVerticesBuffer.put(torusVertices[j + 2]);
                torusVerticesBuffer.put(torusVertices[j + 3]);

                torusVerticesBuffer.position(0);
                gl.glVertexPointer(3, GL10.GL_FLOAT, 0, torusVerticesBuffer);
                gl.glNormalPointer(GL10.GL_FLOAT, 0, torusVerticesBuffer);
                gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 16);

            }
            theta = theta1;
            cosTheta = cosTheta1;
            sinTheta = sinTheta1;
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }
}
