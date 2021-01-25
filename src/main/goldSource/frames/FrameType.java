package main.goldSource.frames;

public enum FrameType {

    DEFAULT((byte) -1),
    SYNC_TICK((byte) 2),
    SEQUENCE_INFO((byte) 3),
    FRAME_COMPLETE((byte) 4),
    NEXT_SECTION((byte) 5),
    EVENT((byte) 6),
    WEAPON_ANIM((byte) 7),
    SOUND((byte) 8),
    DEMO_BUFFER((byte) 9);

    private final byte type;

    FrameType(byte type) {
        this.type = type;
    }

    public static FrameType getTypeFromByte(byte type) {
        for (FrameType ft: FrameType.values()) {
            if (type == ft.type) {
                return ft;
            }
        }
        return DEFAULT;
    }

}
