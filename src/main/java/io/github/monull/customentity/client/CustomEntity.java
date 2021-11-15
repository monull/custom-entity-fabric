package io.github.monull.customentity.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

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
    public long scaleTime;
    public long scaleDuration;
    public int colorR;
    public int colorG;
    public int colorB;
    public int prevColorR;
    public int prevColorG;
    public int prevColorB;
    public int lastColorR;
    public int lastColorG;
    public int lastColorB;
    public long colorTime;
    public long colorDuration;
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
        colorR = 255;
        colorG = 255;
        colorB = 255;
        prevColorR = 255;
        prevColorG = 255;
        prevColorB = 255;
        lastColorR = 255;
        lastColorG = 255;
        lastColorB = 255;
    }

    public void setScale(float scaleX, float scaleY, float scaleZ, int ticks) {
        this.prevScaleX = this.lastScaleX;
        this.prevScaleY = this.lastScaleY;
        this.prevScaleZ = this.lastScaleZ;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        this.scaleDuration = ticks;
        this.scaleTime = this.ticks + ticks;
    }

    public void setColor(int colorR, int colorG, int colorB, int ticks) {
        this.prevColorR = this.lastColorR;
        this.prevColorG = this.lastColorG;
        this.prevColorB = this.lastColorB;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.colorDuration = ticks;
        this.colorTime = this.ticks + ticks;
    }

    public void update() {
        ticks++;
        if (ticks < scaleTime) {
            long remainTime = this.scaleTime - ticks;
            float tick = (float) (this.scaleDuration - remainTime) / (float) this.scaleDuration;
            this.lastScaleX = this.prevScaleX + (this.scaleX - this.prevScaleX) * tick;
            this.lastScaleY = this.prevScaleY + (this.scaleY - this.prevScaleY) * tick;
            this.lastScaleZ = this.prevScaleZ + (this.scaleZ - this.prevScaleZ) * tick;
        } else {
            this.lastScaleX = this.scaleX;
            this.lastScaleY = this.scaleY;
            this.lastScaleZ = this.scaleZ;
        }

        if (ticks < colorTime) {
            long remainTime = this.colorTime - ticks;
            int tick = (int) ((this.colorDuration - remainTime) / this.colorDuration);
            this.lastColorR = this.prevColorR + (this.colorR - this.prevColorR) * tick;
            this.lastColorG = this.prevColorG + (this.colorG - this.prevColorG) * tick;
            this.lastColorB = this.prevColorB + (this.colorB - this.prevColorB) * tick;
            MinecraftClient.getInstance().player.sendMessage(new LiteralText(String.valueOf(lastColorR) + lastColorG + lastColorB), false);
        } else {
            this.lastColorR = this.colorR;
            this.lastColorG = this.colorG;
            this.lastColorB = this.colorB;
        }
    }
}
