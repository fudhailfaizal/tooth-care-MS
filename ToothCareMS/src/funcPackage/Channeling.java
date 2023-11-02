package funcPackage;

import java.util.HashMap;

public class Channeling {
    public static void channelingTimes() {
        HashMap<String, String> channelingTimes = new HashMap<String, String>();
        channelingTimes.put("Monday","6PM-9PM");
        channelingTimes.put("Wednesday","6PM-9PM");
        channelingTimes.put("Saturday","3PM-10PM");
        channelingTimes.put("Sunday","3PM-10PM");

        System.out.println(channelingTimes);
    }
}
