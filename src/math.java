public class math {

    // Q2
    public static String q02(double p) {
        if (p<0 || p >1) {
            return "invalid probability";
        } else if (0<p&&p<0.5) {
            return "Game 1";
        } else if (p == 0.5) {
            return "It doesn't matter.";
        } else {
            return "Game 2";
        }
    }

    public static void main(String[] args) {
        // Q2
        double p = 0.1;
        String game = q02(p);
        System.out.println("Q2: " + game);

    }
}
