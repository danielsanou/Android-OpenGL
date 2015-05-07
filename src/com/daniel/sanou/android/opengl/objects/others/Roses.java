package com.daniel.sanou.android.opengl.objects.others;

import com.daniel.sanou.android.opengl.objects.likeglut.Cone;
import com.daniel.sanou.android.opengl.objects.likeglut.Cylinder;
import com.daniel.sanou.android.opengl.objects.likeglut.Dodecahedron;
import com.daniel.sanou.android.opengl.objects.likeglut.Octahedron;

import javax.microedition.khronos.opengles.GL10;

public class Roses extends RosesHead{

    Cone coneBf, epine1, epine2, epine3, epine4;
    Octahedron obf1, obf2, obf3, obf4, feuille;
    Dodecahedron dodeBf;
    Cylinder tige;
    public Roses(){
        super();
        coneBf = new Cone(0.6,1.5,10);
        obf1 = obf2 = obf3 = obf4 = new Octahedron(0.9f);
        dodeBf = new Dodecahedron(0.5f);
        tige = new Cylinder(0.6,0.3,4,8);
        feuille = new Octahedron(0.7f);
        epine1 = epine2 = epine3 = epine4 = new Cone(0.2,1,8);
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
            coneBf.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(-0.8f, -0.7f, 0);
            gl.glRotatef(-30, 0, 0, -1);
            gl.glScalef(1, 0.1f, 0.5f);
            obf1.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0.8f, -0.7f, 0f);
            gl.glRotatef(30, 0, 0, -1);
            gl.glScalef(1, 0.1f, 0.5f);
            obf2.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.7f, 0.8f);
            gl.glRotatef(90, 0, -1, 0);
            gl.glRotatef(-30, 0, 0, 1);
            gl.glScalef(1, 0.1f, 0.5f);
            obf3.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0.0f, -0.7f, -0.8f);
            gl.glRotatef(90, 0, -1, 0);
            gl.glRotatef(30, 0, 0, 1);
            gl.glScalef(1, 0.1f, 0.5f);
            obf4.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0, -0.7f, 0);
            dodeBf.draw(gl);
        gl.glPopMatrix();
        /////////////////////////////////TIGE/////////////////
        gl.glPushMatrix();
            gl.glTranslatef(0, -4.7f, 0);
            tige.draw(gl);
        gl.glPopMatrix();
        /////////////////////////////FEUILLE////////////////////
        gl.glPushMatrix();
            gl.glTranslatef(-0.7f, -2.7f, 0);
            gl.glRotatef(30, 0, 0, -1);
            gl.glScalef(1.5f, 0.1f, 1f);
            feuille.draw(gl);
        gl.glPopMatrix();
/////////////////////////////////////////////////EPINES///////////////////////////
        gl.glPushMatrix();
            gl.glTranslatef(0, -1.5f, -0.5f);
            gl.glRotatef(68, -1, 0, 0);
            epine1.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0, -2.0f, 0.5f);
            gl.glRotatef(-70, -1, 0, 0);
            epine2.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0, -2.5f, -0.5f);
            gl.glRotatef(72, -1, 0, 0);
            epine3.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0, -3.0f, 0.5f);
            gl.glRotatef(-68, -1, 0, 0);
            epine4.draw(gl);
        gl.glPopMatrix();
    }
}
