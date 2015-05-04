package com.daniel.sanou.android.opengl.objects.likeglut;

public class Icosahedron extends ObjectLikeGLUT {
    private float radius;
    private float  X = 0.525731112119133606f;
    private float Z = 0.850650808352039932f;
    private float[][] vertex;

    int[][] indices = {
            {0,4,1}, {0,9,4}, {9,5,4}, {4,5,8}, {4,8,1},
            {8,10,1}, {8,3,10},{5,3,8}, {5,2,3}, {2,7,3},
            {7,10,3}, {7,6,10}, {7,11,6}, {11,0,6}, {0,1,6},
            {6,1,10}, {9,0,11}, {9,11,2}, {9,2,5}, {7,2,11}
    };

    public Icosahedron(){
        radius = 1.5f;
        initBuffAndPutV();
    }

    public Icosahedron(float radius){
        this.radius = radius > 0.0f ? radius : 0.1f;
        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        initVertex();
        vertexNumber = 60;
        bufferSize = 180*4;
        initBuffer();
        putVertex();
    }

    private void initVertex(){
        X*= radius;
        Z*= radius;
        vertex = new float[][] {
            {-X, 0.0f, Z}, {X, 0.0f, Z}, {-X, 0.0f, -Z}, {X, 0.0f, -Z},
            {0.0f, Z, X}, {0.0f, Z, -X}, {0.0f, -Z, X}, {0.0f, -Z, -X},
            {Z, X, 0.0f}, {-Z, X, 0.0f}, {Z, -X, 0.0f}, {-Z, -X, 0.0f}
        };
    }

    @Override
    public void putVertex() {
        for (int i=0; i<20; i++) {
            for(int j =0; j<3;j++)
                vertexBuffer.put(vertex[indices[i][j]]);
        }
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius > 0.0f ? radius : 0.1f;
        initBuffAndPutV();
    }
}
