package org.example;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.System.out;


public class CustomBase64 {

    public static void main(String[] args) {CustomBase64 customBase64 = new CustomBase64();
    }

    private static char[] outputChars = new char[100];
    private static char[] buffer = new char[100];
    private static final char[] base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    static String res = "";
    //private StringBuilder sb; <<-- it allocates when calls toString()

    public static String encoder(String input) {
        // 2. Convert input to byte array using UTF-8 encoding
        final byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        // DRY
        final int inputLen = inputBytes.length;
        final int outputLen = (inputLen + 2) / 3 * 4;

        // 4. Convert 3-byte chunks to 4-byte chunks
        for (int i = 0, j = 0; i < inputLen;) {
            // triplet stores the input data, padded with zeros if necessary. (fewer than 3 bytes remaining at the end of the input data)
            // First byte is obtained by shifting the first byte of the input data 16 bits to the left. ( Multiplying it by 2^16)
            // Second byte is obtained by shifting the second byte of the input data 8 bits to the left. ( Multiplying it by 2^8)
            // Third byte is obtained directly from the third byte of the input data.
            // The three bytes are then combined using bitwise OR operations, resulting in a 24-bit value that is stored in the triplet variable.
            int triplet = (inputBytes[i++] & 0xff) << 16 | (i < inputLen ? inputBytes[i++] & 0xff : 0) << 8 | (i < inputLen ? inputBytes[i++] & 0xff : 0);
            outputChars[j++] = base64Chars[(triplet >> 18) & 0x3f];
            outputChars[j++] = base64Chars[(triplet >> 12) & 0x3f];
            outputChars[j++] = base64Chars[(triplet >> 6) & 0x3f];
            outputChars[j++] = base64Chars[triplet & 0x3f];
        }

        // 5. Pad output with '=' characters if necessary
        if (inputLen % 3 != 0) {
            int numPadChars = 3 - (inputLen % 3);
            for (int i = 0; i < numPadChars; i++) {
                outputChars[outputLen - 1 - i] = '=';
            }
        }

        // Copiar el array de chars al array buffer y luego hacer un loop para generar un string es mejor solucion?
        // El loop genera demasiadas operaciones y empuja mucho el CPU
        // Este loop esta creando un nuevo objeto?
        System.arraycopy(outputChars, 0, buffer, 0, outputLen);
        for (int i = 0; i < outputLen; i++) {
            res += buffer[i];
        }

        return res;
    }
}

