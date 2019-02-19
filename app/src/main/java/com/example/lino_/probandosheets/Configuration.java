package com.example.lino_.probandosheets;


public class Configuration {

    /*En este segmento se crean variables estaticas con el fin de armar la url para solicitar que Google SpreadSheets
    devuelva un JSON y con la informacion solicitada.
     */
    public static final String APP_SCRIPT_WEB_APP_URL = "https://script.google.com/macros/s/AKfycbx9fUHMLr3u3_WsgfntNRDOvoWESavhYBLILbwCmwxMSm1Zo3R7/exec";
    public static final String ADD_USER_URL = APP_SCRIPT_WEB_APP_URL;
    public static final String LIST_USER_URL = APP_SCRIPT_WEB_APP_URL+"?action=readAll";

    public static final String KEY_ID = "uId";
    public static final String KEY_NAME = "uName";
    public static final String KEY_IMAGE = "uImage";
    public  static final String KEY_ACTION = "action";

    public static final String KEY_USERS = "records";




}
