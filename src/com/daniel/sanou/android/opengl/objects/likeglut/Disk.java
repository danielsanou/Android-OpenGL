package com.daniel.sanou.android.opengl.objects.likeglut;

import javax.microedition.khronos.opengles.GL10;

public class Disk extends ObjectLikeGLUT {

    private double zero = 0;
    private double radius;
    private int slices;
    private float[][] vertex;

    public Disk() {
        super();
        slices = 8;
        radius = 2;
        initBuffAndPutV();
    }

    public Disk(double radius, int slices) {
        super();
        this.radius = radius > 0 ? radius : 0.1;
        this.slices = slices > 2 ? slices : 3;
        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        vertexNumber = slices*2;
        vertex = new float[slices*2][3];
        bufferSize = vertex.length *2* vertex[0].length * 4;
        drawMode = GL10.GL_TRIANGLE_FAN;

        initBuffer();
        putVertex();
    }

    @Override
    public void putVertex() {
        int ind=0;
        for (int i=0; i<slices; i++){
            vertex[ind][0]= (float) (radius * Math.cos(Math.PI/slices*2*i));
            vertex[ind][1]= (float)zero;
            vertex[ind][2] = (float)(radius * Math.sin(Math.PI / slices * 2 * i));
            vertexBuffer.put(vertex[ind]);

            vertex[ind+1][0]= (float) (radius * Math.cos(Math.PI/slices*2*(i+1)));
            vertex[ind+1][1]= (float)zero;
            vertex[ind+1][2] = (float)(radius * Math.sin(Math.PI/slices*2*(i+1)));
            vertexBuffer.put(vertex[ind + 1]);

            ind+=2;
        }
    }

    public int getSlices() {
        return slices;
    }

    public void setSlices(int slices) {
        this.slices = slices > 2 ? slices : 3;
        initBuffAndPutV();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius > 0.0 ? radius : 0.1;
        initBuffAndPutV();
    }
}
