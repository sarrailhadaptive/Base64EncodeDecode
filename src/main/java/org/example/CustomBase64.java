package org.example;

import static java.lang.String.format;

import java.nio.charset.StandardCharsets;

public class CustomBase64
{
    private static final char[] BASE_64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private final int maxInputSize;
    private byte[] inputBytes;
    private final char[] outputChars;

    public CustomBase64(final int maxInputSize)
    {
        this.inputBytes = new byte[maxInputSize];
        this.outputChars = new char[(maxInputSize + 2) / 3 * 4];
        this.maxInputSize = maxInputSize;
    }

    /**
     * Converts to Base64
     *
     * @param input the String to be encoded to Base64
     * @return the input String in Base64
     * @throws IllegalArgumentException if the input length is greater than the maxInputSize
     */
    public String encode(String input)
    {
        final int inputLen = input.length();
        if (inputLen > maxInputSize)
        {
            throw new IllegalArgumentException(format("input length exceeds maxInputSize: %s", maxInputSize));
        }

        // 2. Convert input to byte array using UTF-8 encoding
        {
            inputBytes = input.getBytes(StandardCharsets.UTF_8);
        }
        // DRY
        final int outputLen = (inputLen + 2) / 3 * 4;

        // 4. Convert 3-byte chunks to 4-byte chunks
        for (int i = 0, j = 0; i < inputLen; )
        {
            // triplet stores the input data, padded with zeros if necessary. (fewer than 3 bytes remaining at the end of the input data)
            // First byte is obtained by shifting the first byte of the input data 16 bits to the left. ( Multiplying it by 2^16)
            // Second byte is obtained by shifting the second byte of the input data 8 bits to the left. ( Multiplying it by 2^8)
            // Third byte is obtained directly from the third byte of the input data.
            // The three bytes are then combined using bitwise OR operations, resulting in a 24-bit value that is stored in the triplet
            // variable.
            int triplet = (inputBytes[i++] & 0xff) << 16 |
                (i < inputLen ? inputBytes[i++] & 0xff : 0) << 8 |
                (i < inputLen ? inputBytes[i++] & 0xff : 0);
            outputChars[j++] = BASE_64_CHARS[(triplet >> 18) & 0x3f];
            outputChars[j++] = BASE_64_CHARS[(triplet >> 12) & 0x3f];
            outputChars[j++] = BASE_64_CHARS[(triplet >> 6) & 0x3f];
            outputChars[j++] = BASE_64_CHARS[triplet & 0x3f];
        }

        // 5. Pad output with '=' characters if necessary
        if (inputLen % 3 != 0)
        {
            int numPadChars = 3 - (inputLen % 3);
            for (int i = 0; i < numPadChars; i++)
            {
                outputChars[outputLen - 1 - i] = '=';
            }
        }

        return String.valueOf(outputChars, 0, outputLen);
    }
}

