package com.daniel.sanou.android.opengl.objects.likeglut;

public class Tetrahedron extends ObjectLikeGLUT {
    float[][] vertex;
    float size;

    public Tetrahedron(){
        super();
        size = 1.0f;
        initBuffAndPutV();
    }

    public Tetrahedron(float size){
        super();
        this.size = size > 0.0f ? size : 0.1f;
        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        initVertex();
        vertexNumber = vertex.length;
        bufferSize = vertex.length * vertex[0].length * 4;
        initBuffer();
        putVertex();
    }

    public void initVertex(){
        vertex = new float[][]{
                {size, size, size},
                {-size, -size, size},
                {-size, size, -size},
                {size, -size, -size},
                {size, size, size},
                {-size, -size, size}
        };
    }

    @Override
    public void putVertex() {
        for (int i =0; i< vertex.length; i++){
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