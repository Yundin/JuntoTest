package com.yundin.gladstest.model;

import com.yundin.gladstest.model.api.ApiRepository;
import com.yundin.gladstest.model.api.DefaultApiRepository;
import com.yundin.gladstest.model.database.DatabaseRepository;
import com.yundin.gladstest.model.database.DefaultDatabaseRepository;

/**
 * @author Yundin Vladislav
 */
public class RepositoryProvider {

    private static ApiRepository apiRepository = new DefaultApiRepository();
    private static DatabaseRepository databaseRepository = new DefaultDatabaseRepository();

    public static ApiRepository provideApiRepository() {
        return apiRepository;
    }

    public static DatabaseRepository provideDatabaseRepository() {
        return databaseRepository;
    }

    public static void setApiRepository(ApiRepository apiRepository) {
        RepositoryProvider.apiRepository = apiRepository;
    }

    public static void setDatabaseRepository(DatabaseRepository databaseRepository) {
        RepositoryProvider.databaseRepository = databaseRepository;
    }
}
