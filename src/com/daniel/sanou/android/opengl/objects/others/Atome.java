package com.daniel.sanou.android.opengl.objects.others;

import com.daniel.sanou.android.opengl.objects.common.GLObject;
import com.daniel.sanou.android.opengl.objects.likeglut.Torus;

import javax.microedition.khronos.opengles.GL10;

public class Atome implements GLObject{
    Torus t1,t2,t3,t4;
    float rotation = 0;

    public Atome(){
        super();
        int segments = 50;
        int segmentSlices = 5;
        double thickness = 0.1;
        t1 = new Torus(segments, segmentSlices, thickness,2);
        t2 = new Torus(segments, segmentSlices, thickness,1.5);
        t3 = new Torus(segments, segmentSlices, thickness,1);
        t4 = new Torus(segments, segmentSlices, thickness,0.5);
    }

    @Override
    public void draw(GL10 gl) {
        rotation++;

        gl.glPushMatrix();
            gl.glRotatef(rotation, 1, 0, 0);
            t1.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glRotatef(rotation, 0, 1, 0);
            t2.draw(gl);
        gl.glPopMatrix();
        //////////////////////////////
        gl.glPushMatrix();
            gl.glRotatef(rotation, -1, 0, 0);
            t3.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glRotatef(rotation, 0, -1, 0);
            t4.draw(gl);
        gl.glPopMatrix();
    }
}
