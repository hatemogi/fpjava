package fpjava.fson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodecTest {
    @Test
    void decodeTest() {
        Decoder<Integer> d = Decoder.intDecoder;
        Fson fson = Fson.number(456);
        assertEquals(456, d.decode(fson));
        Decoder<String> dS = d.map(Object::toString);
        assertEquals("456", dS.decode(fson));
    }

    @Test
    void encodeTest() {
        Encoder<Integer> e = Encoder.intEncoder;
        Fson fson = Fson.number(456);
        assertEquals(fson, e.encode(456));
        Encoder<String> eS = e.contramap(Integer::parseInt);
        assertEquals(fson, eS.encode("456"));
    }
}
