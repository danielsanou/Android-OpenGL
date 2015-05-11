package com.daniel.sanou.android.opengl.objects.others;


import com.daniel.sanou.android.opengl.objects.common.GLObject;
import com.daniel.sanou.android.opengl.objects.likeglut.Dodecahedron;

import javax.microedition.khronos.opengles.GL10;

public class CircleWith implements GLObject{
    private GLObject object;
    private double radius;
    private double stepAngle;

    public CircleWith(){
        super();
        object = new Dodecahedron(0.3f);
        radius = 1.5;
        stepAngle = 45;
    }

    public CircleWith(GLObject object, double radius, double stepAngle){
        super();
        setObject(object);
        setRadius(radius);
        setStepAngle(stepAngle);
    }

    @Override
    public void draw(GL10 gl) {
        double TwoPi = Math.PI*2;
        double Period = 360;
        float x,y,z=0;

        for (int i = 0; i<Period; i+=stepAngle){

            x = (float) (radius * Math.cos(i*TwoPi/Period));
            y = (float) (radius * Math.sin(i*TwoPi/Period));

            gl.glTranslatef(x,y,z);
            object.draw(gl);
        }
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius > 0.0 ? radius : 0.1;
    }

    public double getStepAngle() {
        return stepAngle;
    }

    public void setStepAngle(double stepAngle) {
        this.stepAngle = stepAngle > 0.0 ? stepAngle : 0.1;
    }

    public GLObject getObject() {
        return object;
    }

    public void setObject(GLObject object) {
        this.object = object;
    }
}
