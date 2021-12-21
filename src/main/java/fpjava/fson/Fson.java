package fpjava.fson;

import java.util.Objects;

public interface Fson {

    enum JsonType {
        JNumber,
        JObject,
        JArray,
        JBoolean,
        JString,
        JNull
    };

    Integer intValue();

    static FNumber number(Number value) {
        return new FNumber(value);
    }

    final class FNumber implements Fson {
        private final Number value;
        private FNumber(Number value) {
            this.value = value;
        }

        public Integer intValue() {
            return value.intValue();
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof FNumber)) {
                return false;
            } else {
                final Object that = ((FNumber)o).value;
                return Objects.equals(value, that);
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "JNumber(" + value + ")";
        }
    }

    final class FString implements Fson {

        @Override
        public Integer intValue() {
            throw new UnsupportedOperationException();
        }
    }
}
