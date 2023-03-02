import java.nio.charset.StandardCharsets;

import static java.lang.System.out;


public class CustomBase64 {

    public static void main(String[] args) {
        out.println(encode("String passed as parameter."));
    }

    public static String encode(String input) {
        // 1. Define table of Base64 characters
        final char[] base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        // 2. Convert input to byte array using UTF-8 encoding
        final byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        // DRY
        final int inputLen = inputBytes.length;
        final int outputLen = (inputLen + 2) / 3 * 4;
        // 3. Output is stored as charArray to avoid creating temporary strings that would trigger the garbage collector
        final char[] outputChars = new char[outputLen];

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

        return new String(outputChars);
    }
}
