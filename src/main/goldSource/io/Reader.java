package main.goldSource.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Reader {

    public static String readString(RandomAccessFile reader, int length) throws IOException {
        byte[] arr = new byte[length];
        reader.read(arr);
        String[] ret = new String(arr).split("\0");
        return ret.length > 0 ? ret[0] : "";
    }

    public static int readInteger(RandomAccessFile reader) throws IOException {
        byte[] arr = new byte[4];
        reader.read(arr);
        return ByteBuffer.wrap(arr).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static short readShort(RandomAccessFile reader) throws IOException {
        byte[] arr = new byte[2];
        reader.read(arr);
        return ByteBuffer.wrap(arr).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    public static byte readByte(RandomAccessFile reader) throws IOException {
        return reader.readByte();
    }

    public static byte[] readByteArray(RandomAccessFile reader, int length) throws IOException {
        byte[] arr = new byte[length];
        reader.read(arr);
        return arr;
    }

    public static float readFloat(RandomAccessFile reader) throws IOException {
        byte[] arr = new byte[4];
        reader.read(arr);
        return ByteBuffer.wrap(arr).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

}
