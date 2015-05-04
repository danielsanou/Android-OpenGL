package com.daniel.sanou.android.opengl.objects.likeglut;

public class Torus extends ObjectLikeGLUT {

    private float[] verticesAux;
    //nombre de segments du tore
    private int segments;
    //epaisseur des anneau du tore
    double thickness;
    //nombre de slices par segment d'anneau
    int segmentSlices;
    //diametre du trou
    double holeRadius;

    public Torus(){
        super();
        segments = 5;
        thickness = 0.5;
        segmentSlices = 8;
        holeRadius = 1;
        initBuffAndPutV();
    }

    public Torus(int segments,int segmentSlices, double thickness, double holeRadius){
        super();
        this.segments = segments > 2 ? segments : 3;
        this.segmentSlices = segmentSlices > 2 ? segmentSlices : 3;
        this.thickness = thickness > 0.0 ? thickness : 0.1;
        this.holeRadius = holeRadius >= 0.0 ? holeRadius : 0.0  ;
        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        vertexNumber = (segments +1) * segmentSlices *2;
        bufferSize = segments * segmentSlices * segmentSlices * 3 * 4;
        initBuffer();
        putVertex();
    }

    @Override
    public void putVertex() {
        double s, t;
        verticesAux = new float[3];
        double TWOPI = 2 * Math.PI;
        for (int i = 0; i < segmentSlices; i++) {
            for (int j = 0; j <= segments; j++) {
                for (int k = 1; k >= 0; k--) {
                    s = (i + k) % segmentSlices + 0.5;
                    t = j % segments;

                    verticesAux[0] = (float) ((holeRadius + thickness *Math.cos(s * TWOPI / segmentSlices))*Math.cos(t * TWOPI / segments));
                    verticesAux[1] = (float) ((holeRadius + thickness *Math.cos(s * TWOPI / segmentSlices))*Math.sin(t * TWOPI / segments));
                    verticesAux[2] = (float) (thickness * Math.sin(s * TWOPI / segmentSlices));
                    vertexBuffer.put(verticesAux);
                }
            }

        }
    }

    public int getSegments() {
        return segments;
    }

    public void setSegments(int segments) {
        this.segments = segments > 2 ? segments : 3;
        initBuffAndPutV();
    }

    public int getSegmentSlices() {
        return segmentSlices;
    }

    public void setSegmentSlices(int segmentSlices) {
        this.segmentSlices = segmentSlices > 2 ? segmentSlices : 3;
        initBuffAndPutV();
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness > 0.0 ? thickness : 0.1;
        initBuffAndPutV();
    }

    public double getHoleRadius() {
        return holeRadius;
    }

    public void setHoleRadius(double holeRadius) {
        this.holeRadius = holeRadius >= 0.0 ? holeRadius : 0.0  ;
        initBuffAndPutV();
    }
}
