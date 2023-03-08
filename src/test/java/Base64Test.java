import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Base64;
import java.util.stream.Stream;

import org.example.Base64EncodingException;
import org.example.CustomBase64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Base64Test
{
    private CustomBase64 customBase64ED = new CustomBase64(100);

    @Test
    void EncodeText()
    {
        assertThat("U3RyaW5nIGZyb20gdGhlIG91dHNpZGUu").isEqualTo(customBase64ED.encode("String from the outside."));
    }

    void DecodeText()
    {
        //assertEquals("String passed as parameter.", customBase64ED.decode("U3RyaW5nIHBhc3NlZCBhcyBwYXJhbWV0ZXIu"));
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

    @MethodSource("examples")
    @ParameterizedTest
    void base64EncodeGivesSameResultAsJavaBase64(String input, String outputFromJavaBase64)
    {
        final String outputFromMine = customBase64ED.encode(input);
        assertThat(outputFromMine).isEqualTo(outputFromJavaBase64);
    }

    public static Stream<Arguments> examplesDecoder()
    {
        return Stream.of(
            Arguments.of(new String(Base64.getEncoder().encode("hola que tal".getBytes())), "hola que tal"),
            Arguments.of(           new String(Base64.getEncoder().encode("--estamos haciendo pruebas---".getBytes())), "--estamos haciendo pruebas---"),
            Arguments.of(new String(Base64.getEncoder().encode("del base64 custom __".getBytes())), "del base64 custom __"),
            Arguments.of(new String(Base64.getEncoder().encode(" a ver si esto funciona".getBytes())), " a ver si esto funciona"),
            Arguments.of(new String(Base64.getEncoder().encode((" y podemos seguir probando el uso de memoria").getBytes())), " y podemos seguir probando el uso de memoria"),
            Arguments.of(new String(Base64.getEncoder().encode(("de los metodos encode y decode custom").getBytes())), "de los metodos encode y decode custom"),
            Arguments.of(new String(Base64.getEncoder().encode("1253014659264895|234523".getBytes())), "1253014659264895|234523")
        );
    }

    @MethodSource("examplesDecoder")
    @ParameterizedTest
    void base64DecodeGivesSameResultAsJavaBase64(String input, String outputFromJavaBase64)
    {
        final String outputFromMine = customBase64ED.decode(input);
        assertThat(outputFromMine).isEqualTo(outputFromJavaBase64);
    }

    @Test
    void throwsExceptionIfEncodeInputTooLong()
    {
        final CustomBase64 shortBase64 = new CustomBase64(5);
        final Base64EncodingException ex = assertThrows(Base64EncodingException.class, () -> shortBase64.encode("123456"));
        assertThat(ex.getMessage()).isEqualTo("input length exceeds maxInputSize: 5");
    }

    @Test
    void throwsExceptionIfDecodeInputTooLong()
    {
        final CustomBase64 shortBase64 = new CustomBase64(5);
        final Base64EncodingException ex = assertThrows(Base64EncodingException.class, () -> shortBase64.decode("123456"));
        assertThat(ex.getMessage()).isEqualTo("input length exceeds maxInputSize: 5");
    }
}