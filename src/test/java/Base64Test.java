import org.example.CustomBase64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import static com.google.common.truth.Truth.assertThat;

import java.util.Base64;
import java.util.stream.Stream;

class Base64Test
{
    Base64EncodeDecode base64ED = new Base64EncodeDecode();
    CustomBase64 customBase64ED = new CustomBase64();

    @Test
    void EncodeText()
    {
        //assertEquals("U3RyaW5nIGZyb20gdGhlIG91dHNpZGUu", base64ED.encode("String from the outside."));
    }

    void DecodeText()
    {
        //assertEquals("U3RyaW5nIHBhc3NlZCBhcyBwYXJhbWV0ZXIu", customBase64ED.encode("String passed as parameter."));
    }

    public static Stream<Arguments> examples()
    {
        return Stream.of(
            Arguments.of("hola que tal", new String(Base64.getEncoder().encode("hola que tal".getBytes()))),
            Arguments.of("--estamos haciendo pruebas---",
                new String(Base64.getEncoder().encode("--estamos haciendo pruebas---".getBytes()))),
            Arguments.of("del base64 custom __", new String(Base64.getEncoder().encode("del base64 custom __".getBytes()))),
            Arguments.of(" a ver si esto funciona", new String(Base64.getEncoder().encode(" a ver si esto funciona".getBytes()))),
            Arguments.of(" y podemos seguir probando el uso de memoria", new String(Base64.getEncoder().encode((" y podemos seguir " +
                "probando el uso de memoria").getBytes()))),
            Arguments.of("de los metodos encode y decode custom", new String(Base64.getEncoder().encode(("de los metodos encode y decode " +
                "custom").getBytes()))),
            Arguments.of("1253014659264895|234523", new String(Base64.getEncoder().encode("1253014659264895|234523".getBytes())))
        );
    }

    //@MethodSource("examples")
    //@ParameterizedTest
    //void base64EncoderGivesSameResultAsJavaBase64(String input, String outputFromJavaBase64)
    //{
    //    final String outputFromMine = customBase64ED.encode(input);
    //    assertThat(outputFromMine).isEqualTo(outputFromJavaBase64);
    //}
}