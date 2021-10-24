package bishiti;

import java.util.concurrent.Executors;

public class People {

    private static class PeopleHelper {
        private static final People instance = new People();
    }

    public static People get() {
        return PeopleHelper.instance;
    }

}
