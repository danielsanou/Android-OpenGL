package com.daniel.sanou.android.opengl.objects.likeglut;


public class Sphere extends ObjectLikeGLUT {

    private int steps;
    private float[][] vertex;
    private double radius;

    public Sphere(){
        super();
        steps = 10;
        radius = 2;
        initBuffAndPutV();
    }

    public Sphere(double radius, int steps){
        super();
        this.radius = radius > 0.0 ? radius : 0.1;
        safeSetStep(steps);
        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        vertex = new float[((180/ steps)*(360/ steps)*4)][3];
        vertexNumber = vertex.length;
        bufferSize = vertex.length * 4 * 4;
        initBuffer();
        putVertex();
    }

    @Override
    public void putVertex() {
        float  theta, pai;
        int n;
        float nbStep = 90.0f;
        float P = 360.0f;
        double T = 180.0;
        for (pai = -nbStep; pai < nbStep; pai += steps) {

            n = 0;
            for (theta = 0.0f; theta <= P; theta += steps) {

                vertex[n][0] = (float)(radius*(Math.cos((pai + steps) * Math.PI / T)) * (Math.cos(theta * Math.PI / T)));
                vertex[n][1] = (float) (radius*Math.sin((pai + steps) * Math.PI / T));
                vertex[n][2] = (float)(radius*(Math.cos((pai + steps) * Math.PI / T)) * -(Math.sin(theta * Math.PI / T)));

                vertex[n + 1][0] = (float) (radius*(Math.cos(pai * Math.PI / T)) * (Math.cos(theta * Math.PI / T)));
                vertex[n + 1][1] = (float) (radius*Math.sin(pai * Math.PI / T));
                vertex[n + 1][2] = (float)(radius*(Math.cos(pai * Math.PI / T)) * -(Math.sin(theta * Math.PI / T)));

                vertexBuffer.put(vertex[n]);
                vertexBuffer.put(vertex[n + 1]);

                n += 2;
            }
        }
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius > 0.0 ? radius : 0.1;
        initBuffAndPutV();
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        safeSetStep(steps);
        initBuffAndPutV();
    }

    private void safeSetStep(int steps) {
        if (steps > 0 ){
            if (steps > 20){
                if (steps >30){
                    if (steps > 40){
                        this.steps = steps >= 50 ? 60 : 40;
                    }
                }
                else {
                    this.steps = steps >= 35 ? 40 : 30;
                }
            }
            else {
                this.steps = steps;
            }
        }
        else {
            this.steps = 10;
        }
    }
}
