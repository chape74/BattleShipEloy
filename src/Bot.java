public class Bot {
    public static String place() {
        char[] auto = new char[3];
        auto[0] = (char) ('0' + Math.random() * 8);
        auto[1] = (char) ('A' + Math.random() * 8);
        char vh;
        if ((int) (Math.random() * 2) == 0) vh = 'V';
        else vh = 'H';
        auto[2] = vh;
        return new String(auto);
    }

    public static String shoot(int difficulty) {
        char[] auto = new char[3];
        auto[0] = (char) ('0' + Math.random() * 10);
        auto[1] = (char) ('A' + Math.random() * 10);
        if (difficulty>=2){
            if (auto[0]+auto[1]%2==0)
                return shoot(difficulty);
        }
        return new String(auto);
    }
}
