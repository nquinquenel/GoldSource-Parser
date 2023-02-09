package goldsource.io;

import java.nio.MappedByteBuffer;
import java.nio.charset.StandardCharsets;

public class Reader {

    private Reader() {
        throw new IllegalStateException("Utility class");
    }

    public static String readMsg(MappedByteBuffer reader, int length) {
        byte[] arr = new byte[length];
        for (int i = 0; i < length; i++) {
            arr[i] = reader.get();
        }
        String[] ret = new String(arr, StandardCharsets.US_ASCII).split("\0");
        return ret.length > 0 ? ret[0] : "";
    }

    public static String readString(MappedByteBuffer reader, int length) {
        byte[] arr = new byte[length];
        for (int i = 0; i < length; i++) {
            arr[i] = reader.get();
        }
        String[] ret = new String(arr).split("\0");
        return ret.length > 0 ? ret[0] : "";
    }

    public static int readInteger(MappedByteBuffer reader) {
        byte[] arr = new byte[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = reader.get();
        }
        return byteArrayToLeInt(arr);
    }

    public static short readShort(MappedByteBuffer reader) {
        byte[] arr = new byte[2];
        for (int i = 0; i < 2; i++) {
            arr[i] = reader.get();
        }
        return byteArrayToLeShort(arr);
    }

    public static byte readByte(MappedByteBuffer reader) {
        return reader.get();
    }

    public static byte[] readByteArray(MappedByteBuffer reader, int length) {
        byte[] arr = new byte[length];
        for (int i = 0; i < length; i++) {
            arr[i] = reader.get();
        }
        return arr;
    }

    public static float readFloat(MappedByteBuffer reader) {
        byte[] arr = new byte[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = reader.get();
        }
        return Float.intBitsToFloat(byteArrayToLeInt(arr));
    }

    public static int byteArrayToLeInt(byte[] encodedValue) {
        int value = (encodedValue[3] << (Byte.SIZE * 3));
        value |= (encodedValue[2] & 0xFF) << (Byte.SIZE * 2);
        value |= (encodedValue[1] & 0xFF) << (Byte.SIZE);
        value |= (encodedValue[0] & 0xFF);
        return value;
    }

    public static short byteArrayToLeShort(byte[] encodedValue) {
        short value = (short) (encodedValue[1] << (Byte.SIZE));
        value |= (encodedValue[0] & 0xFF);
        return value;
    }

}
