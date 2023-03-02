import java.util.Base64;
import static java.lang.System.out;

public class Base64EncodeDecode {

    public static void main(String[] args) {

        // 1. Encode string to Base64
        out.println("Encoded text: " + encode("This is a long string to encode."));

        // 2. Decode string from Base64
        out.println("Decoded text: " + new String(decode(encode("This is a long string to encode."))));

    }

    static String encode(String textData) {
        return Base64.getEncoder().encodeToString(textData.getBytes());
    }

    static String decode(String encodedText){
        return new String(Base64.getDecoder().decode(encodedText));
    }
}
