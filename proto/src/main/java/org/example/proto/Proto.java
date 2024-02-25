package org.example.proto;

import java.io.*;

public class Proto {
    public static void main(String[] args) {
//        try {
//            FileOutputStream fos = new FileOutputStream("/Users/sheldon.tauro/Desktop/proto");
//            for (int i = (int) -1e1; i < 1e1; i++) {
//                byte[] bytes = Encoder.encodeInt32(i);
//                fos.write(bytes);
//            }
//            fos.close();
//            FileInputStream fis = new FileInputStream("/Users/sheldon.tauro/Desktop/proto");
//            byte[] bytes = fis.readAllBytes();
//            for (int i = 0; i < bytes.length; i += 4) {
//                System.out.println(Decoder.decodeInt32(bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]));
//            }
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            FileOutputStream fos = new FileOutputStream("/Users/sheldon.tauro/Desktop/proto");
//            for (long i = (long) -1e10; i < -1e10 + 20; i++) {
//                byte[] bytes = Encoder.encodeInt64(i);
//                fos.write(bytes);
//            }
//            fos.close();
//            FileInputStream fis = new FileInputStream("/Users/sheldon.tauro/Desktop/proto");
//            byte[] bytes = fis.readAllBytes();
//            for (int i = 0; i < bytes.length; i += 8) {
//                System.out.println(Decoder.decodeInt64(
//                        bytes[i],
//                        bytes[i + 1],
//                        bytes[i + 2],
//                        bytes[i + 3],
//                        bytes[i + 4],
//                        bytes[i + 5],
//                        bytes[i + 6],
//                        bytes[i + 7]));
//            }
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        for (int i = (int)-1e9; i < 1e9; i++) {
//            if (i != Decoder.decodeInt32(Encoder.encodeInt32(i))) {
//                System.out.println("hi");
//                break;
//            }
//        }

//        for (long i = (long)1e10; i < 1.3e10; i++) {
//            if (i != Decoder.decodeInt64(Encoder.encodeInt64(i))) {
//                System.out.println(Decoder.decodeInt64(Encoder.encodeInt64(i)));
//                break;
//            }
//        }

        String s = "spaces !!dude";
        if (!s.equals(Decoder.decodeString(Encoder.encodeString(s)))) {
            System.out.println("error");
        }

        for (float i = 0.0f; i < 10000f; i += 0.99) {
            if (i != Decoder.decodeFloat(Encoder.encodeFloat(i))) {
                System.out.println("error");
                break;
            }
        }

        for (double i = 0.0; i < 10000.0; i += 0.99) {
            if (i != Decoder.decodeDouble(Encoder.encodeDouble(i))) {
                System.out.println("error");
                break;
            }
        }


        if (true != Decoder.decodeBoolean(Encoder.encodeBoolean(true))) {
            System.out.println("error");
        }
        if (false != Decoder.decodeBoolean(Encoder.encodeBoolean(false))) {
            System.out.println("error");
        }
    }
}
