import org.example.CustomBase64;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class Base64EncodeDecode {

    public static void main(String[] args) throws InterruptedException
    {
        List<String> myList = generateList();
        CustomBase64 customBase64 = new CustomBase64(100);

        System.out.println("Waiting 10 seconds until starting encoding");
        Thread.sleep(10000);
        for (String str : myList) {
            customBase64.encode(str);
            //Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
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
