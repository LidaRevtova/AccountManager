package Account;

import java.util.HashMap;

public class FailedLoginCounter {
    private static volatile FailedLoginCounter instance;
    private HashMap<String, Integer> counter;

    private FailedLoginCounter() {
        this.counter = new HashMap<>();
    }

    public static FailedLoginCounter getInstance() {
        FailedLoginCounter localInstance = instance;
        if (localInstance == null) {
            synchronized (FailedLoginCounter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FailedLoginCounter();
                }
            }
        }
        return localInstance;
    }

    public void counterAdd(String email) {
        if (counter.containsKey(email)) {
            counter.replace(email, counter.get(email) + 1);

        } else {
            counter.put(email, 1);
        }
    }

    public int getCounter(String email) {
        return counter.get(email);
    }

    public void updateCounter(String email) {
        counter.put(email, 1);
    }


}
