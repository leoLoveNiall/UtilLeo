package DataAsset.Random;

public class RandomCharacter {
    public static char getRandNumChar() {
        return (char) (RandomNumber.getRandomInt(0, 9) + '0');
    }

    public static char getRandLetterChar() {
        return (char) (RandomNumber.getRandomInt(0, 26) + 'a');
    }

    public static char getRandCapLetterChar() {
        return (char) (RandomNumber.getRandomInt(0, 26) + 'A');
    }

    public static char getRandSignChar() {
        return getRandChar("@$!%*#_~?&^");
    }

    public static char getRandChar(String s) {
        var sign = s.toCharArray();
        return sign[RandomNumber.getRandomInt(0, sign.length)];
    }

}
