package io.github.monull.customentity.client;

public class CustomEntity {

    public float scaleX;
    public float scaleY;
    public float scaleZ;
    public float lastScaleX;
    public float lastScaleY;
    public float lastScaleZ;
    public float prevScaleX;
    public float prevScaleY;
    public float prevScaleZ;
    public long scaleDuration;
    public int ticks = 0;

    public CustomEntity() {
        scaleX = 1.0F;
        scaleY = 1.0F;
        scaleZ = 1.0F;
        lastScaleX = 1.0F;
        lastScaleY = 1.0F;
        lastScaleZ = 1.0F;
        prevScaleX = 1.0F;
        prevScaleY = 1.0F;
        prevScaleZ = 1.0F;
    }

    public void setScale(float scaleX, float scaleY, float scaleZ, int ticks) {
        this.prevScaleX = this.lastScaleX;
        this.prevScaleY = this.lastScaleY;
        this.prevScaleZ = this.lastScaleZ;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        this.scaleDuration = this.ticks + ticks;
    }

    public void update() {
        ticks++;
        if (ticks <= scaleDuration) {
            long tick = scaleDuration - ticks;
            this.lastScaleX += this.prevScaleX + (this.scaleX - this.prevScaleX) / tick;
            this.lastScaleY += this.prevScaleY + (this.scaleY - this.prevScaleY) / tick;
            this.lastScaleZ = this.prevScaleZ + (this.scaleZ - this.prevScaleZ) / tick;
        } else {
            this.lastScaleX = this.scaleX;
            this.lastScaleY = this.scaleY;
            this.lastScaleZ = this.scaleZ;
        }
    }
}
