package org.example.proto;

import java.nio.ByteBuffer;

public class Encoder {
    public static byte[] encodeInt32(int value) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (value & 0xFF);
        bytes[1] = (byte) ((value >> 8) & 0xFF);
        bytes[2] = (byte) ((value >> 16) & 0xFF);
        bytes[3] = (byte) ((value >> 24) & 0xFF);
        return bytes;
    }

    public static byte[] encodeInt64(long value) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (value & 0xFF);
        bytes[1] = (byte) ((value >> 8) & 0xFF);
        bytes[2] = (byte) ((value >> 16) & 0xFF);
        bytes[3] = (byte) ((value >> 24) & 0xFF);
        bytes[4] = (byte) ((value >> 32) & 0xFF);
        bytes[5] = (byte) ((value >> 40) & 0xFF);
        bytes[6] = (byte) ((value >> 48) & 0xFF);
        bytes[7] = (byte) ((value >> 56) & 0xFF);
        return bytes;
    }

    /**
     * Encodes a string into a byte array.
     * First 4 bytes are the length of the string, then the string itself.
     */
    public static byte[] encodeString(String value) {
        byte[] bytes = value.getBytes();
        byte[] length = encodeInt32(bytes.length);
        byte[] result = new byte[bytes.length + length.length];
        System.arraycopy(length, 0, result, 0, length.length);
        System.arraycopy(bytes, 0, result, length.length, bytes.length);
        return result;
    }

    public static byte[] encodeFloat(float value) {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }

    public static byte[] encodeDouble(double value) {
        return ByteBuffer.allocate(8).putDouble(value).array();
    }

    public static byte[] encodeBoolean(boolean value) {
        return new byte[] { (byte) (value ? 1 : 0) };
    }
}
