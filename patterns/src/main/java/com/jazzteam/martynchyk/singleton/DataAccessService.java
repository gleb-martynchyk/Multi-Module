package com.jazzteam.martynchyk.singleton;

public class DataAccessService {
    private static volatile DataAccessService instance;

    public static DataAccessService getInstance() {
        DataAccessService localInstance = instance;
        if (localInstance == null) {
            synchronized (DataAccessService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataAccessService();
                }
            }
        }
        return localInstance;
    }
}
