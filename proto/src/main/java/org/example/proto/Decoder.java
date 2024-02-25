package org.example.proto;

import java.nio.ByteBuffer;

public class Decoder {
    public static int decodeInt32(byte... bytes) {
        int value = 0;
        value |= (bytes[0] & 0xFF);
        value |= (bytes[1] & 0xFF) << 8;
        value |= (bytes[2] & 0xFF) << 16;
        value |= (bytes[3] & 0xFF) << 24;
        return value;
    }

    public static long decodeInt64(byte... bytes) {
        long value = 0;
        value |= (bytes[0] & 0xFF);
        value |= (bytes[1] & 0xFF) << 8;
        value |= (bytes[2] & 0xFF) << 16;
        value |= (long) (bytes[3] & 0xFF) << 24;
        value |= (long) (bytes[4] & 0xFF) << 32;
        value |= (long) (bytes[5] & 0xFF) << 40;
        value |= (long) (bytes[6] & 0xFF) << 48;
        value |= (long) (bytes[7] & 0xFF) << 56;
        return value;
    }

    public static String decodeString(byte[] bytes) {
        int length = decodeInt32(bytes[0], bytes[1], bytes[2], bytes[3]);
        return new String(bytes, 4, length);
    }

    public static float decodeFloat(byte... bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Floats are 4 bytes long");
        }
        return ByteBuffer.wrap(bytes).getFloat();
    }

    public static double decodeDouble(byte... bytes) {
        if (bytes.length != 8) {
            throw new IllegalArgumentException("Doubles are 8 bytes long");
        }
        return ByteBuffer.wrap(bytes).getDouble();
    }

    public static boolean decodeBoolean(byte... bytes) {
        if (bytes.length != 1) {
            throw new IllegalArgumentException("Booleans are 1 byte long");
        }
        return bytes[0] != 0;
    }
}
