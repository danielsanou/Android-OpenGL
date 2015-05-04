package com.daniel.sanou.android.opengl.objects.others;


import com.daniel.sanou.android.opengl.objects.common.GLObject;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Explosion implements GLObject{

    private FloatBuffer sphereVerticesBuffer;
    private float[][] sphereVertices = new float[32][3];
    float R = 1.5f;
    float r = 0.5f;
    int maxn= 10; // max precision

    public Explosion(){
        ByteBuffer byteBuffer;
        byteBuffer = ByteBuffer.allocateDirect(sphereVertices.length * sphereVertices[0].length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        sphereVerticesBuffer = byteBuffer.asFloatBuffer();
    }

    public void draw(GL10 gl)
    {
        float rr=0.5f*r;
        double dv=2*Math.PI/Math.min(20,maxn-1);
        double dw=2*Math.PI/Math.min(20,maxn-1);
        double v=0.0f;
        double w=0.0f;

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        // outer loop
        for(w=0.0f; w<2*Math.PI+dw; w+=dw)
        {
            // inner loop
            int    ni = 0;
            for(v=0.0f; v<2*Math.PI+dv; v+=dv)
            {
                sphereVertices[ni][0] = (float) ((R + rr * Math.cos(v)) * Math.cos(w) - (R + r * Math.cos(v)) * Math.cos(w));
                sphereVertices[ni][1] = (float) ((R + rr * Math.cos(v)) * Math.sin(w) - (R + r * Math.cos(v)) * Math.sin(w));
                sphereVertices[ni][2] = (float) ((rr * Math.sin(v) - r * Math.sin(v)));
                sphereVertices[ni + 1][0] = (float) ((R+r*Math.cos(v+dv))*Math.cos(w + dw));
                sphereVertices[ni + 1][1] = (float) ((R+r*Math.cos(v+dv))*Math.sin(w + dw));
                sphereVertices[ni + 1][2] = (float) (r*Math.sin(v+dv));

                sphereVerticesBuffer.put(sphereVertices[ni]);
                sphereVerticesBuffer.put(sphereVertices[ni + 1]);

                ni += 2;

                if(ni>31){
                    sphereVerticesBuffer.position(0);

                    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, sphereVerticesBuffer);
                    gl.glNormalPointer(GL10.GL_FLOAT, 0, sphereVerticesBuffer);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, ni);

                    ni = 0;
                }

                sphereVerticesBuffer.position(0);
                gl.glVertexPointer(3, GL10.GL_FLOAT, 0, sphereVerticesBuffer);
                gl.glNormalPointer(GL10.GL_FLOAT, 0, sphereVerticesBuffer);
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, ni);

            } // inner loop
        } //outer loop

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }
}
