/*---------------------------------------------------------------------------------------
 * Object Name: ITAPConstants.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2016 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.itap.constant;

/**
 * Class which will provides the String final constants i.e JSP names, request
 * handler mappings, request parameters , strings and error codes.
 */

public final class ITAPConstants {
	public static final String SCOPE_SESSION = "session";
	public static final int BUFFER_SIZE = 4096;
	public static final String UNCHECKED = "unchecked";
	public static final String MM_DD_YYYY = "MM/dd/yyyy";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String DDMMYYYY_HHMMSS = "dd-MM-yyyy hh:mm:ss a zzz";
	public static final String dd_MM_yyyy = "dd-MM-yyyy";
	public static final String ERROR_MESSAGE = "Exception Occurred Contact Admin!!!";
	public static final String ERROR = "error";
	public static final String NO_RECORDS = "No Records found";
	public static final String DS_NAME = "itapDS";
	public static final String UNAME = "USER_NAME";
	public static final String UTYPE = "USER_TYPE";
	public static final String TDMP_SERVICE_ERROR_11 = "TDMP_SERVICE_ERROR_11";
	public static final String TDMP_SERVICE_ERROR_12 = "TDMP_SERVICE_ERROR12";
	public static final String TDMP_SERVICE_ERROR_13 = "TDMP_SERVICE_ERROR_13";
	public static final String TDMP_SERVICE_ERROR_14 = "TDMP_SERVICE_ERROR_14";
	public static final String TDMP_SERVICE_ERROR_15 = "TDMP_SERVICE_ERROR_15";
	public static final String TDMP_SERVICE_ERROR_16 = "TDMP_SERVICE_ERROR_16";
	public static final String TDMP_SERVICE_ERROR_17 = "TDMP_SERVICE_ERROR_17";
	public static final String TDMP_SERVICE_ERROR_18 = "TDMP_SERVICE_ERROR_18";
	public static final String TDMP_SERVICE_ERROR_19 = "TDMP_SERVICE_ERROR_19";
	public static final String TDMP_SERVICE_ERROR_20 = "TDMP_SERVICE_ERROR_20";
	public static final String TDMP_SERVICE_ERROR_21 = "TDMP_SERVICE_ERROR_21";
	public static final String TDMP_SERVICE_ERROR_22 = "TDMP_SERVICE_ERROR_22";
	public static final String TDMP_SERVICE_ERROR_23 = "TDMP_SERVICE_ERROR_23";
	public static final String TDMP_SERVICE_ERROR_24 = "TDMP_SERVICE_ERROR_24";
	public static final String TDMP_VIEW_ERROR_1 = "TDMP_VIEW_ERROR_1";
	public static final String TDMP_VIEW_ERROR_2 = "TDMP_VIEW_ERROR_2";
	public static final String TDMP_VIEW_ERROR_3 = "TDMP_VIEW_ERROR_3";
	public static final String TDMP_VIEW_ERROR_4 = "TDMP_VIEW_ERROR_4";
	public static final String TDMP_VIEW_ERROR_5 = "TDMP_VIEW_ERROR_5";
	public static final String EXCEPTION_ADMIN = "Exception Occurred Contact Admin!!!";
	public static long THREADTIME = 600000L;
	public static final String APP_STREAM = "application/octet-stream";
	public static final String HEADER_DISP = "Content-Disposition";
	public static final String ATTACHMENT = "attachment; filename=\"%s\"";
	public static final String PERSISTENCE_UNIT_USER = "userPersistenceUnit";
	public static final String PERSISTENCE_UNIT_TDM_INTEGRATE = "tdmITAPPersistenceUnit";
	public static final String PERSISTENCE_UNIT_USER_SCHEDULAR = "userPersistenceUnitSchedular";
	public static final String CURRENT_PAGE = "currentPage";
	public static final String START_PAGE = "startPage";
	public static final String LAST_PAGE = "lastPage";
	public static final String PAGE = "page";
	public static final String NO_OF_PAGES = "noOfPages";
	public static final String ID = "id";
	public static final String SESSION_UID = "UserId";
	public static final String SESSION_UNAME = "UserName";
	public static final String ROLE = "ROLE";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ENV_OWNR = "ROLE_ENV_OWNR";
	public static final String ROLE_INVALID = "ROLE_INVALID";
	public static final String MSG = "msg";
	public static final String USER_ID = "userId";
	public static final String REMS_LOGIN_REDIRECT = "login:redirect?accessDenied=true";
	public static final String REMS_LOGIN_SESSIONEXP = "/sessionExp";
	public static final String REMS_LOGIN_AUTHFAIL = "/authFail";
	public static final String REMS_LOGIN_BACK = "/back";
	public static final String REMS_LOOUT = "/logout";
	public static final String LOGOUT = "logout";
	public static final String INVALID_UNAME_PASS = "Invalid username and password!";
	public static final String LOGOUT_SUCCESS = "You've been logged out successfully.";
	public static final String SESSION_EXPIRED = "You are not allowed to perform 'Back' or You have not logged in or Session Expired.";
	public static final String NEW_USER = "You are not a registered user, Please request for providing the access.";
	public static final String REMS_LOGIN_VIEW = "login";
	public static final String REMS_LOGIN_CTLR = "~ ITAPLoginController ~";
	public static final String REMS_LOGIN_CTLR_GET = "~ login ~";
	public static final String REMS_LOGIN_CTLR_YES = "~ login Params:Yes ~";
	public static final String REMS_LOGIN_CTLR_NO = "~ Params:No ~";
	public static final String REMS_LOGOUT_CTLR_GET = "~ logout ~";
	public static final String REMS_LOGOUT_CTLR_NO = "~ Params:No ~";
	public static final String REMS_USER_AUTH_HANDLER = "~ UserAuthenticationSuccessHandler ~";
	public static final String REMS_USER_AUTH_HANDLER_ONAUTH = "~ onAuthenticationSuccess ~";
	public static final String REMS_USER_AUTH_HANDLER_ONAUTH_NO = "~ Params:No ~";
	public static final String REMS_USER_AUTH_HANDLER_HANDLE = "~ handle ~";
	public static final String REMS_USER_AUTH_HANDLER_HANDLE_NO = "~ handle Params:No ~";
	public static final String REMS_USER_AUTH_HANDLER_TAR_URL = "~ determineTargetUrl ~";
	public static final String REMS_USER_AUTH_HANDLER_TAR_URL_NO = "~ Params:No ~";
	public static final String REMS_USER_AUTH_HANDLER_TAR_URL_RETN = "~ Return ~";
	public static final String REMS_USER_AUTH_HANDLER_CLR_AUTH = "~ clearAuthenticationAttributes ~";
	public static final String REMS_USER_AUTH_HANDLER_CLR_AUTH_NO = "~ Params:No ~";
	public static final String REMS_PGE_UTIL = "~ PaginationUtil ~";
	public static final String REMS_PGE_UTIL_PAGENATE = "~ paginate ~";
	public static final String REMS_PGE_UTIL_PAGENATE_NO = "~ Params:No ~";
	public static final String REMS_PGE_UTIL_OFFSET = "~ getOffset ~";
	public static final String REMS_PGE_UTIL_OFFSET_NO = "~ Params:No ~";
	public static final String REMS_PGE_UTIL_TAR_URL_RETN = "~ Return ~";
	public static final String REMS_DOWNLOAD_UTIL = "~ DownloadUtils ~ ";
	public static final String DOWNLOAD = "~ download ~ ";
	public static final String MIME_TYPE = "~ MIME type: ";
	public static final String NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION = "NRE_0106";
	public static final String INVALID_QUERY_EXCEPTION = "11200";
	public static final String NULL_POINTER_EXCEPTION = "11201";
	public static final String DATABASE_EXCEPTION = "11202";
	public static final String SERVICE_EXCEPTION = "11203";
	public static final String IE_EXCEPTION = "11204";
	public static final String PARSE_EXCEPTION = "11205";
	public static final String LOG_INFO_PARAMS_NO = " Method Begain ~ params : No ";
	public static final String LOG_INFO_RETURN = " next is return ";
	public static final String LOG_ERROR_EXCEPTION = " Exception ";
	public static final String LOG_INFO_PARAMS_YES = " Method Begain ~ params : ";
	public static final String LOG_INFO_REDIRECT = " next is redirecting ";
	public static final String LOG_INFO_DOWNLOAD = " next is downloding xls ";
	public static final String LOG_INFO_PARAMS_SEPC = " Method Begain ~ params : page  ";
	public static final String LOG_INFO_POPUP = " to get popup ~ params : ";
	public static final String LOG_INFO_EXPORT = " Method Begain ~ params : export ";
	public static final String LOG_INFO_REQ_ID = "Method Begain ~ params : reqId";
	public static final String LOG_INFO_SEARCH = " Method Begain ~ params : search ";
	public static final String CROSS_BROWSER_TESTSUITE_FOLDER_PATH = "cb.ts.fld.path";
	public static final String CROSS_BROWSER_COMBO_EXCEL_FOLDER_PATH = "cb.comb.xls.fld.path";
	public static final String CROSS_BROWSER_COMBO_EXCEL_FOLDER_PATH_PERFECTO = "cb.comb.xls.fld.path.perfecto";
	public static final String CROSS_BROWSER_COMBO_TAB = "cd.comb.tab";
	public static final String CROSS_BROWSER_MAINSCRIPT_PATH = "cb.comb.ms.path";
	public static final String CROSS_BROWSER_MAINSCRIPT = "cb.comb.ms.name";
	public static final String CROSS_BROWSER_MAINSCRIPT_SHEET = "cb.comb.ms.sht.name";
	public static final String CROSS_BROWSER_Sheet = "cb.comb.ms.sheet";
	public static final String FT_UFT_MAINSCRIPT_PATH = "ft.uft.ms.path";
	public static final String FT_ITAP = "ft.itap";
	public static final String FT_UFT_MAINSCRIPT = "cb.comb.ms.name";
	public static final String FT_UFT_MAINSCRIPT_SHEET = "cb.comb.ms.sht.name";
	public static final String CROSS_BROWSER_COMBO_EXCEL_NAME = "cd.comb.xls.name";
	public static final String BDD_BAT_FILE = "call E:/AutoHub/Solution/BDD/Scripts/Cyclos-Hartford/BDD_Execute.bat";
	public static final String OPTIK_BAT_FILE = "call E:/AutoHub/Solution/Optik/Scripts/Optik_execute_anz.bat";
	public static final String PROP_FILE = "/properties/db.properties";
	public static final String DRIVER = "db.driver";
	public static final String URL = "db.url";
	public static final String USER = "db.user";
	public static final String PASS = "db.pass";
}
