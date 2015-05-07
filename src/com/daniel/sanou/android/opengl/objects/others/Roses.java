package com.daniel.sanou.android.opengl.objects.others;

import com.daniel.sanou.android.opengl.objects.likeglut.Cone;
import com.daniel.sanou.android.opengl.objects.likeglut.Cylinder;
import com.daniel.sanou.android.opengl.objects.likeglut.Dodecahedron;
import com.daniel.sanou.android.opengl.objects.likeglut.Octahedron;

import javax.microedition.khronos.opengles.GL10;

public class Roses extends RosesHead{

    public Roses(){
        super();
    }

    public void draw(GL10 gl) {
        /////////////////////////TETE///////////////
        gl.glPushMatrix();
            gl.glRotatef(90, 1, 0, 0);
            super.draw(gl);
        gl.glPopMatrix();
        ///////////////////////////BOUTON FLORAL/////////////
        gl.glPushMatrix();
            gl.glRotatef(180, 1, 0, 0);
            gl.glTranslatef(0, 1.0f, 0);
            gl.glColor4f(0, 1, 0, 1);
            new Cone(0.6,1.5,10).draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(-0.8f, -0.7f, 0);
            gl.glRotatef(-30, 0, 0, -1);
            gl.glScalef(1, 0.1f, 0.5f);
            new Octahedron(0.9f).draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0.8f, -0.7f, 0f);
            gl.glRotatef(30, 0, 0, -1);
            gl.glScalef(1, 0.1f, 0.5f);
            new Octahedron(0.9f).draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.7f, 0.8f);
            gl.glRotatef(90, 0, -1, 0);
            gl.glRotatef(-30, 0, 0, 1);
            gl.glScalef(1, 0.1f, 0.5f);
            new Octahedron(0.9f).draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.7f, -0.8f);
            gl.glRotatef(90, 0, -1, 0);
            gl.glRotatef(30, 0, 0, 1);
            gl.glScalef(1, 0.1f, 0.5f);
            new Octahedron(0.9f).draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0, -0.7f, 0);
            new Dodecahedron(0.5f).draw(gl);
        gl.glPopMatrix();
        /////////////////////////////////TIGE/////////////////
        gl.glPushMatrix();
            gl.glTranslatef(0, -4.7f, 0);
            new Cylinder(0.6,0.3,4,8).draw(gl);
        gl.glPopMatrix();
        /////////////////////////////FEUILLE////////////////////
        gl.glPushMatrix();
            gl.glTranslatef(-0.7f, -2.7f, 0);
            gl.glRotatef(30, 0, 0, -1);
            gl.glScalef(1.5f, 0.1f, 1f);
            new Octahedron(0.7f).draw(gl);
        gl.glPopMatrix();
/////////////////////////////////////////////////EPINES///////////////////////////
        gl.glPushMatrix();
            gl.glTranslatef(0, -1.5f, -0.5f);
            gl.glRotatef(68, -1, 0, 0);
            new Cone(0.2,1,8).draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0, -2.0f, 0.5f);
            gl.glRotatef(-70, -1, 0, 0);
            new Cone(0.2,1,8).draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0, -2.5f, -0.5f);
            gl.glRotatef(72, -1, 0, 0);
            new Cone(0.2,1,8).draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, -3.0f, 0.5f);
            gl.glRotatef(-68, -1, 0, 0);
            new Cone(0.2,1,8).draw(gl);
        gl.glPopMatrix();
    }
}
