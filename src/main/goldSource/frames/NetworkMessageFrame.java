package main.goldSource.frames;

import lombok.ToString;
import main.goldSource.io.Reader;
import main.goldSource.frames.networkMessage.MoveVars;
import main.goldSource.frames.networkMessage.NetworkRefParams;
import main.goldSource.frames.networkMessage.UserCmd;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.io.RandomAccessFile;

@ToString
public class NetworkMessageFrame implements Frame {

    protected float timestamp;
    protected NetworkRefParams rParms;
    protected UserCmd uCmd;
    protected MoveVars mVars;
    protected Point3D view;
    protected int viewmodel;
    protected int incomingSequence;
    protected int incomingAcknowledged;
    protected int incomingReliableAcknowledged;
    protected int incomingReliableSequence;
    protected int outgoingSequence;
    protected int reliableSequence;
    protected int lastReliableSequence;
    protected String msg;

    public NetworkMessageFrame(RandomAccessFile raf) throws IOException {
        timestamp = Reader.readFloat(raf);
        rParms = new NetworkRefParams(raf);
        uCmd = new UserCmd(raf);
        mVars = new MoveVars(raf);
        view = new Point3D(Reader.readFloat(raf),
                Reader.readFloat(raf),
                Reader.readFloat(raf));
        viewmodel = Reader.readInteger(raf);
        incomingSequence = Reader.readInteger(raf);
        incomingAcknowledged = Reader.readInteger(raf);
        incomingReliableAcknowledged = Reader.readInteger(raf);
        incomingReliableSequence = Reader.readInteger(raf);
        outgoingSequence = Reader.readInteger(raf);
        reliableSequence = Reader.readInteger(raf);
        lastReliableSequence = Reader.readInteger(raf);
        int msgLength = Reader.readInteger(raf);
        msg = Reader.readString(raf, msgLength);
    }

}
