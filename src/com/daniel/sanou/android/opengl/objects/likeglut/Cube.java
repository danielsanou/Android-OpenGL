package com.daniel.sanou.android.opengl.objects.likeglut;

public class Cube extends ObjectLikeGLUT {

    private float width ;
    private float height ;
    private float thickness;
    private final float zero = 0;

    float[] vertex;

    public Cube() {
        super();
        width = 1.0f;
        height = width;
        thickness = height *2;
        initBuffAndPutV();
    }

    public Cube(float width, float height, float thickness){
        super();
        this.width = width > 0.0f ? width : 0.1f;
        this.height = height > 0.0f ? height : 0.1f;
        this.thickness = thickness > 0.0f ? thickness : 0.1f;
        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        initVertex();
        vertexNumber = 36;
        bufferSize = vertex.length * 4;
        initBuffer();
        putVertex();
    }

    private void initVertex(){
        vertex = new float[]{
                width, height,zero, -width, height,zero, -width,-height,zero, -width,-height,zero,
                width,-height,zero, width, height,zero, width,-height, thickness, width, height, thickness,
                -width, height, thickness, -width, height, thickness, -width,-height, thickness, width,-height, thickness,
                width, height, thickness, width, height,zero, width,-height,zero, width, height, thickness,
                width,-height,zero, width,-height, thickness, -width, height, thickness, -width, height,zero,
                -width,-height, thickness, -width,-height, thickness, -width, height,zero, -width,-height,zero,
                width, height, thickness, -width, height, thickness, -width, height,zero, width, height, thickness,
                -width, height,zero, width, height,zero, width,-height, thickness, -width,-height, thickness,
                -width,-height,zero, width,-height, thickness, -width,-height,zero, width,-height,zero
        };
    }

    @Override
    public void putVertex() {
        vertexBuffer.put(vertex);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height > 0.0f ? height : 0.1f;
        initBuffAndPutV();
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness > 0.0f ? thickness : 0.1f;
        initBuffAndPutV();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width > 0.0f ? width : 0.1f;
        initBuffAndPutV();
    }
}
