public class Bigo {
//
    public static void main(String[] args) {
        System.out.println(func(2));
        System.out.println(func(3));
    }

    public static int func(int target) {
        int i = 1;
        int sum = 0;

//        N + P = S (1+ i)*i/2
//        N - p = target;
//        s - tagett = 2p
//        int sum = 0;
        while (true) {
            sum += i;
            if ((sum - target) % 2 == 0 && sum >= target)
                return i;
            i++;
        }
    }
}
