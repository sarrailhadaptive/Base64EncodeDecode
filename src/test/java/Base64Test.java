import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Base64Test {
    Base64EncodeDecode base64ED = new Base64EncodeDecode();
    @Test
    void EncodeText() {
        assertEquals("U3RyaW5nIGZyb20gdGhlIG91dHNpZGUu", base64ED.encode("String from the outside."));
    }
    void DecodeText(){
        assertEquals("U3RyaW5nIHBhc3NlZCBhcyBwYXJhbWV0ZXIu", base64ED.decode("String passed as parameter."));
    }

}