package com.robin.biblosearch.utils;

import java.io.IOException;

class NetworkUtils {

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

        return false;
    }

}
