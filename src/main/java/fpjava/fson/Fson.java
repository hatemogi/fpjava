package fpjava.fson;

import java.util.Map;
import java.util.Objects;

public interface Fson {
    default Integer intValue() {
        throw new UnsupportedOperationException();
    }

    default Double doubleValue() {
        throw new UnsupportedOperationException();
    }

    default String stringValue() {
        throw new UnsupportedOperationException();
    }

    default FObject objectValue() {
        throw new UnsupportedOperationException();
    }

    default FArray arrayValue() {
        throw new UnsupportedOperationException();
    }

    default boolean boolValue() {
        throw new UnsupportedOperationException();
    }

    static FNumber number(Number value) {
        return new FNumber(value);
    }

    static FString string(String value) {
        return new FString(value);
    }

    static FBoolean bool(boolean value) {
        return new FBoolean(value);
    }

    static FArray array(Iterable<Fson> values) {
        return new FArray(values);
    }

    static FNull nullValue() {
        return FNull.INSTANCE;
    }

    final class FNull implements Fson {
        private static FNull INSTANCE = new FNull();
        private FNull() {}

        @Override
        public String toString() {
            return "FNull";
        }
    }

    final class FObject implements Fson {
        private final Map<String, Fson> map;
        private FObject(Map<String, Fson> map) {
            this.map = map;
        }
    }

    final class FArray implements Fson {
        private final Iterable<Fson> values;

        private FArray(Iterable<Fson> values) {
            this.values = values;
        }
    }

    final class FNumber implements Fson {
        private final Number value;
        private FNumber(Number value) {
            this.value = value;
        }

        @Override
        public Integer intValue() {
            return value.intValue();
        }

        @Override
        public Double doubleValue() {
            return value.doubleValue();
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
            return "FNumber(" + value + ")";
        }
    }

    final class FString implements Fson {
        private final String value;

        private FString(String value) {
            this.value = value;
        }

        @Override
        public String stringValue() {
            return value;
        }
    }

    final class FBoolean implements Fson {
        private final boolean value;

        private FBoolean(boolean value) {
            this.value = value;
        }

        @Override
        public boolean boolValue() {
            return value;
        }
    }

}
