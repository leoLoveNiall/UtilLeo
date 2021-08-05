package DataAsset.Data;

enum Sign {
    positive(1), negative(-1), undefined(0);
    int value;

    Sign(int i) {
        value = i;
    }

    int getValue() {
        return value;
    }

}
