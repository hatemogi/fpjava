package fpjava.fson;

public interface Fson {

    enum JsonType {
        JNumber,
        JObject,
        JArray,
        JBoolean,
        JString,
        JNull
    };

    static JNumber number(Number value) {
        return new JNumber(value);
    }

    final class JNumber {
        private final Number value;
        private JNumber(Number value) {
            this.value = value;
        }
    }

    final class JString implements Fson {

    }
}
