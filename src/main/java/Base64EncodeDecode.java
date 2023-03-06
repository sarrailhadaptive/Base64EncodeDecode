import org.example.CustomBase64;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static java.lang.System.out;

public class Base64EncodeDecode {

    public static void main(String[] args) {
        CustomBase64 customBase64 = new CustomBase64();
        List<String> myList = customBase64.generateList();
        // 1. Encode string to Base64
        // Loop genera lista de strings
        // Loop de la lista haciendo el encode y decode
        for(String s : myList){
            CustomBase64.encode(s);
        }

            //out.println("Encoded text: " + encode(GenerateString()));

        // 2. Decode string from Base64
        //out.println("Decoded text: " + new String(decode(encode("This is a long string to encode."))));

    }

    public static String GenerateString() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }

    static String encode(String textData) {
        return Base64.getEncoder().encodeToString(textData.getBytes());
    }

    static String decode(String encodedText){
        return new String(Base64.getDecoder().decode(encodedText));
    }
}
