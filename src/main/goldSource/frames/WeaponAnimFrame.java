package main.goldSource.frames;

import lombok.ToString;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class WeaponAnimFrame implements Frame {

    protected int anim;
    protected int body;

    public WeaponAnimFrame(RandomAccessFile raf) throws IOException {
        anim = Reader.readInteger(raf);
        body = Reader.readInteger(raf);
    }

}
