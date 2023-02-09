package goldsource.frames.network;

import goldsource.io.Reader;
import lombok.ToString;

import java.nio.MappedByteBuffer;

@ToString
public class MoveVars {

    protected float gravity;
    protected float stopspeed;
    protected float maxspeed;
    protected float spectatormaxspeed;
    protected float accelerate;
    protected float airaccelerate;
    protected float wateraccelerate;
    protected float friction;
    protected float edgefriction;
    protected float waterfriction;
    protected float entgravity;
    protected float bounce;
    protected float stepsize;
    protected float maxvelocity;
    protected float zmax;
    protected float waveHeight;
    protected int footsteps;
    protected String skyName;
    protected float rollangle;
    protected float rollspeed;
    protected float skycolorR;
    protected float skycolorG;
    protected float skycolorB;
    protected float skyvecX;
    protected float skyvecY;
    protected float skyvecZ;

    public MoveVars(MappedByteBuffer raf) {
        gravity = Reader.readFloat(raf);
        stopspeed = Reader.readFloat(raf);
        maxspeed = Reader.readFloat(raf);
        spectatormaxspeed = Reader.readFloat(raf);
        accelerate = Reader.readFloat(raf);
        airaccelerate = Reader.readFloat(raf);
        wateraccelerate = Reader.readFloat(raf);
        friction = Reader.readFloat(raf);
        edgefriction = Reader.readFloat(raf);
        waterfriction = Reader.readFloat(raf);
        entgravity = Reader.readFloat(raf);
        bounce = Reader.readFloat(raf);
        stepsize = Reader.readFloat(raf);
        maxvelocity = Reader.readFloat(raf);
        zmax = Reader.readFloat(raf);
        waveHeight = Reader.readFloat(raf);
        footsteps = Reader.readInteger(raf);
        skyName = Reader.readString(raf, 32);
        rollangle = Reader.readFloat(raf);
        rollspeed = Reader.readFloat(raf);
        skycolorR = Reader.readFloat(raf);
        skycolorG = Reader.readFloat(raf);
        skycolorB = Reader.readFloat(raf);
        skyvecX = Reader.readFloat(raf);
        skyvecY = Reader.readFloat(raf);
        skyvecZ = Reader.readFloat(raf);
    }

}
