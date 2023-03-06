import org.example.CustomBase64;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Base64EncodeDecode {

    public static void main(String[] args) {
        List<String> myList = generateList();
        for (String str : myList) {
            CustomBase64.encoder(str);
        }
    }

    public static List<String> generateList() {
        List<String> outputList  = new ArrayList<>();
        for (int i = 0; i < 35_000; i++) {
            outputList.add(UUID.randomUUID().toString());
        }
        return outputList;
    }
}
