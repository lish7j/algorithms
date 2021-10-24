package pattern;

public class SingleStatic {

    private SingleStatic(){};

    private static class Helper{
        private static SingleStatic instance = new SingleStatic();
    }

    public SingleStatic get() {
        return Helper.instance;

    }
}
