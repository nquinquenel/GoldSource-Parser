package main.goldSource.directories;

import lombok.ToString;
import main.goldSource.frames.*;
import main.goldSource.io.Reader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

@ToString
public class DirectoryEntryFrames {

    protected Map<FrameHeader, Frame> frames;

    public DirectoryEntryFrames(RandomAccessFile raf) throws IOException {
        frames = new HashMap<>();

        while (true) {
            FrameHeader frameHeader = new FrameHeader(raf);

            switch (frameHeader.type) {
                case SYNC_TICK: {
                    frames.put(frameHeader, new SyncTickFrame());
                    break;
                }
                case SEQUENCE_INFO: {
                    frames.put(frameHeader, new SequenceInfoFrame(raf));
                    break;
                }
                case FRAME_COMPLETE: {
                    frames.put(frameHeader, new ClientDataFrame(raf));
                    break;
                }
                case NEXT_SECTION: {
                    frames.put(frameHeader, new NextSectionFrame());
                    return;
                }
                case EVENT: {
                    frames.put(frameHeader, new EventFrame(raf));
                    break;
                }
                case WEAPON_ANIM: {
                    frames.put(frameHeader, new WeaponAnimFrame(raf));
                    break;
                }
                case SOUND: {
                    frames.put(frameHeader, new SoundFrame(raf));
                    break;
                }
                case DEMO_BUFFER: {
                    int bufferLength = Reader.readInteger(raf);
                    frames.put(frameHeader, new DemoBufferFrame(raf, bufferLength));
                    break;
                }
                case DEFAULT: {
                    frames.put(frameHeader, new NetworkMessageFrame(raf));
                    break;
                }
                default: {
                    throw new IOException("Invalid frame type");
                }
            }
        }
    }

}
