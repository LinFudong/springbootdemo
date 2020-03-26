package org.linfd.interview.util;

import java.util.UUID;

public class CommonUtil {

    /**
     * generate UUID
     * @return
     */
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
