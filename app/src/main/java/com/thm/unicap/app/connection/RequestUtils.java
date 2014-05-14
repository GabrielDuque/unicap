package com.thm.unicap.app.connection;

public abstract class RequestUtils {

    public static final String REQUEST_BASE_URL = "http://www.unicap.br/PortalGraduacao/";
    public static final int REQUEST_TIMEOUT = 60*1000; // one minute (debug reasons)

    public abstract static class Params {
        public static final String ROUTINE = "rotina";
        public static final String REGISTRATION = "Matricula";
        public static final String DIGIT = "Digito";
        public static final String PASSWORD = "Senha";
    }

    public abstract static class Values {
        public static final String ROUTINE_LOGIN = "1";
        public static final String ROUTINE_PERSONAL = "2";
        public static final String ROUTINE_SUBJECTS_ACTUAL = "14";
        public static final String ROUTINE_SUBJECTS_PAST = "5";
        public static final String ROUTINE_SUBJECTS_PENDING = "6";
    }
}