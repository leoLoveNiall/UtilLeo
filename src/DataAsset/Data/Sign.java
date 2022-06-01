package DataAsset.Data;

public enum Sign {
    positive(1), negative(-1), unsigned(1);
    int value;

    Sign(int i) {
        value = i;
    }

    int getValue() {
        return value;
    }

}
