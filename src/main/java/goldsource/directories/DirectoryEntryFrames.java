package goldsource.directories;

import goldsource.frames.*;
import goldsource.io.Reader;
import lombok.ToString;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

@ToString
public class DirectoryEntryFrames {

    private final Map<Integer, List<FrameWithHeader>> frames;
    private Map<FrameHeader, NetworkMessageFrame> framesDefault;

    public DirectoryEntryFrames(MappedByteBuffer raf) throws IOException {
        frames = new HashMap<>();
        framesDefault = new HashMap<>();

        while (true) {
            FrameHeader frameHeader = new FrameHeader(raf);

            switch (frameHeader.getType()) {
                case SYNC_TICK: {
                    new SyncTickFrame();
                    break;
                }
                case SEQUENCE_INFO: {
                    FrameWithHeader fwh = new FrameWithHeader(frameHeader, new SequenceInfoFrame(raf));
                    frames.computeIfAbsent(frameHeader.getFrame(), f -> new ArrayList<>()).add(fwh);
                    break;
                }
                case FRAME_COMPLETE: {
                    FrameWithHeader fwh = new FrameWithHeader(frameHeader, new ClientDataFrame(raf));
                    frames.computeIfAbsent(frameHeader.getFrame(), f -> new ArrayList<>()).add(fwh);
                    break;
                }
                case NEXT_SECTION: {
                    new NextSectionFrame();
                    sortMaps();
                    return;
                }
                case EVENT: {
                    new EventFrame(raf);
                    break;
                }
                case WEAPON_ANIM: {
                    new WeaponAnimFrame(raf);
                    break;
                }
                case SOUND: {
                    new SoundFrame(raf);
                    break;
                }
                case DEMO_BUFFER: {
                    int bufferLength = Reader.readInteger(raf);
                    new DemoBufferFrame(raf, bufferLength);
                    break;
                }
                case DEFAULT: {
                    NetworkMessageFrame nmf = new NetworkMessageFrame(raf);
                    FrameWithHeader fwh = new FrameWithHeader(frameHeader, nmf);
                    frames.computeIfAbsent(frameHeader.getFrame(), f -> new ArrayList<>()).add(fwh);
                    framesDefault.put(frameHeader, nmf);
                    break;
                }
                default: {
                    final String message = "Invalid frame type";
                    throw new IOException(message);
                }
            }
        }
    }

    public void sortMaps() {
        framesDefault = framesDefault.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().getFrame()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (left, right) -> left,
                        LinkedHashMap::new));
    }

}
