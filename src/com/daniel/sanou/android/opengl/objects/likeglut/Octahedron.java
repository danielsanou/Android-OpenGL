package com.daniel.sanou.android.opengl.objects.likeglut;

public class Octahedron extends ObjectLikeGLUT {

    private float size;
    private float[][] vertex;

    public Octahedron(){
        super();
        setSize(2);
    }

    public Octahedron(float size){
        super();
        setSize(size);
    }

    private void initBuffAndPutV() {
        initVertex();
        bufferSize = vertex.length*8*4;
        vertexNumber = vertex.length;
        initBuffer();
        putVertex();
    }

    public void initVertex() {
        vertex = new float[][]{
                {0.0f,size,0.0f},{0.0f,0.0f,size},{size,0.0f,0.0f},
                {0.0f,size,0.0f},{0.0f,0.0f,size},{-size,0.0f,0.0f},
                {0.0f,size,0.0f},{0.0f,0.0f,-size},{-size,0.0f,0.0f},
                {0.0f,size,0.0f},{0.0f,0.0f,-size},{size,0.0f,0.0f},
                {0.0f,-size,0.0f},{0.0f,0.0f,size},{-size,0.0f,0.0f},
                {0.0f,-size,0.0f},{0.0f,0.0f,size},{size,0.0f,0.0f},
                {0.0f,-size,0.0f},{0.0f,0.0f,-size},{size,0.0f,0.0f},
                {0.0f,-size,0.0f},{0.0f,0.0f,-size},{-size,0.0f,0.0f}
        };
    }

    @Override
    public void putVertex() {
        for (int i=0; i<vertex.length; i++) {
            vertexBuffer.put(vertex[i]);
        }
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size > 0.0f ? size : 0.1f;
        initBuffAndPutV();
    }
}
