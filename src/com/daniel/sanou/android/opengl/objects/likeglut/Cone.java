package com.daniel.sanou.android.opengl.objects.likeglut;

public class Cone  extends ObjectLikeGLUT {

    private double height;
    private double radius;
    private int slices;
    private float[][] vertex;

    public Cone() {
        super();
        height = 2;
        slices = 6;
        radius = 2;
        initBuffAndPutV();
    }

    public Cone(double radius, double height, int slices){
        super();
        this.radius = radius > 0.0 ? radius : 0.1;
        this.height = height > 0.0 ? height : 0.1;
        this.slices = slices > 2 ? slices : 3;

        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        vertexNumber = slices*4;
        vertex = new float[vertexNumber][6];
        bufferSize = vertex.length * vertex[0].length * 4;
        initBuffer();
        putVertex();
    }

    @Override
    public void putVertex() {
        int n =0;
        for (int i=0; i<slices*2; i++){
            vertex[n][0]= (float) (radius * Math.cos(Math.PI/slices*2*i));
            vertex[n][1]= (float)-height /2;
            vertex[n][2] = (float)(radius * Math.sin(Math.PI/slices*2*i));

            vertex[n][3]= (float) (radius * Math.cos(Math.PI/slices*2*(i+1)));
            vertex[n][4]= (float)-height /2;
            vertex[n][5] = (float)(radius * Math.sin(Math.PI/slices*2*(i+1)));

            vertexBuffer.put(vertex[i]);
            n+=2;
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height > 0.0 ? height : 0.1;
        initBuffAndPutV();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius > 0.0 ? radius : 0.1;
        initBuffAndPutV();
    }

    public int getSlices() {
        return slices;
    }

    public void setSlices(int slices) {
        this.slices = slices > 2 ? slices : 3;
        initBuffAndPutV();
    }
}