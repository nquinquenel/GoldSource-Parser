package goldsource.frames;

import goldsource.frames.network.MoveVars;
import goldsource.frames.network.NetworkRefParams;
import goldsource.frames.network.UserCmd;
import goldsource.io.Reader;
import javafx.geometry.Point3D;
import lombok.ToString;
import lombok.Value;

import java.nio.MappedByteBuffer;

@ToString
@Value
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

    public NetworkMessageFrame(MappedByteBuffer raf) {
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
        msg = Reader.readMsg(raf, msgLength);
    }

}
