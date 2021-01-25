package main.goldSource.frames;

import lombok.ToString;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class SoundFrame implements Frame {

    protected int channel;
    protected int sampleLength;
    protected byte[] sample;
    protected float attenuation;
    protected float volume;
    protected int flags;
    protected int pitch;

    public SoundFrame(RandomAccessFile raf) throws IOException {
        channel = Reader.readInteger(raf);
        sampleLength = Reader.readInteger(raf);
        sample = Reader.readByteArray(raf, sampleLength);
        attenuation = Reader.readFloat(raf);
        volume = Reader.readFloat(raf);
        flags = Reader.readInteger(raf);
        pitch = Reader.readInteger(raf);
    }

}
