package com.npci.creational.singleton;

// author : Nilesh Patil
class AppConfig {
    private AppConfig() {
        System.out.println("AppConfig instance created");
    }

    //public static AppConfig appConfig = new AppConfig(); // Eager initialization
    private static AppConfig appConfig; // = null

    public static synchronized AppConfig getInstance() {
        if (appConfig == null) {
            appConfig = new AppConfig(); // Lazy initialization
        }
        return appConfig;
    }
}


public class Application {
    public static void main(String[] args) {

        //--------------------------------------------------
        // Module-1
        //--------------------------------------------------

        //AppConfig appConfig1 = new AppConfig();
        AppConfig appConfig1 = AppConfig.getInstance();

        //--------------------------------------------------
        // Module-2
        //--------------------------------------------------

        //AppConfig appConfig2 = new AppConfig();
        AppConfig appConfig2 = AppConfig.getInstance();


        //--------------------------------------------------
        System.out.println(appConfig1 == appConfig2);

    }
}
