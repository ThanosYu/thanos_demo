package com.thanos.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yingjie_Li on 2015/3/11.
 */
public class ZLData {

    public static final String ZENLIFE_API_HOST = Environment.getValue("api_host");

    public static final String FILE_UPLOAD_DIR = Environment.getValue("file_upload_dir");
    public static final String FILE_DOWNLOAD_SERVER = Environment.getValue("file_download_server");
    public static final String FILE_DOWNLOAD_PATH = Environment.getValue("file_download_path");
    public static final boolean RELEASE_FLG = Environment.getBooleanValue("release_flg");
    public static final String USER_AUTH_CHECK_URL = Environment.getValue("user_auth_check_url");
    public static final String WEBAPP_ZENLIFE_DIR = Environment.getValue("webapp_zenlife_dir");
    public static final String WEBAPP_DEPLOY_DIR = Environment.getValue("webapp_deploy_dir");
    public static final String WEBAPP_FOLDER_XML_PATH = Environment.getValue("webapp_folder_xml_path");
    public static final String ZENLIFE_UPLOAD_HEAD_TEST = Environment.getValue("zenlife_upload_head_test");

    public static final String CATEGORY_TAG_FOLDER = "文件夹";
    public static final String CATEGORY_TAG_GAME = "游戏";
    public static final String CATEGORY_TAG_APP = "应用";

    public static final String FILE_DOWNLOAD_URL = "file/dl";
    //    public static final String ICON_URL = "image";
    public static final String APK_FILE_PREFIX = "apk_";
    public static final Set<String> ICON_FILE_TYPE = new HashSet();
    public static final String CATEGORY_GAME_ALIAS = "game";
    public static final String TYPE_WEB_ALIAS = "web";
    public static final String TYPE_GAME_RANK_ALIAS = "rank";
    public static final String CLIENT_VERSION_GENERAL_ALIAS = "2";
    public static final String CLIENT_VERSION_GENERAL_ALIAS_OLD = "general";

    /**
     * 应用属性名
     */
    public static final class APP_ATTRIBUTES {
        public static final String POINT_ILLUSTRATE = "point_illustrate";
    }

    /**
     * 客户端接口参数 START
     */
    public static final String PARAM_API_VERSION = "apiversion";
    public static final String PARAM_PHONE_MODEL = "model";//机型
    public static final String PARAM_CHANNEL = "channel";//渠道
    public static final String PARAM_PROVINCE = "province";//省
    public static final String PARAM_CITY = "city";//市
    /**
     * 客户端接口参数 END
     */

    public static final String CONFIG_ZL_FOLDER_APP_LIMIT = "zl_folder_app_limit";
    public static final String ZL_FOLDER_APP_LIMIT_DEFAULT = "30";


    public static final String SEACH_FILTER_KEY_WORDS = "app_search_filter_key_words";

    /**
     * 功能块开关
     */
    public static final String TEST_FUNCION_BLOCK_SWITCH = "test_function_block_switch";

    public static final String APP_BLACK_LIST_UPLOAD_LIMIT = "app_black_list_upload_limit";

    public static final String OK = "ok";
    public static final String ERR = "err";
    public static final int SUCCESS_CODE = 0;
    public static final int RUNTIME_ERROR_CODE = -100;
    public static final int ERROR_CODE = -1;
    public static final String EXCEL_EXPORT_PATH = "/excel";
    public static final String STATS_EXPORT_PATH = "/stats_file";
    public static final String APK_UPLOAD_PATH = "/apk";
    public static final String ARTICLE_UPLOAD_PATH = "/article";
    public static final String FILE_UPLOAD_PATH = "/upload";
    public static final String THEME_ONLINE_FILE_UPLOAD_PATH = "/themeOnlineFile";
    public static final String THEME_ONLINE_IMAGE_UPLOAD_PATH = "/themeOnlineImage";
    public static final String THEME_ONLINE_BANNER_UPLOAD_PATH = "/themeOnlineBanner";
    public static final String THEME_ONLINE_INDEX_IMGURL_UPLOAD_PATH = "/indexImgUrl";
    public static final String THEME_ONLINE_IMAGE_NAVURL_UPLOAD_PATH = "/imageNavUrl";
    public static final String APK_LOCAL_UPLOAD_PATH = "/apkLocal";
    public static final String ICON_UPLOAD_PATH = "/icon";
    public static final String CMS_ICON_UPLOAD_PATH = "/cms/icon";
    public static final String IMAGE_UPLOAD_PATH = "/image";
    public static final String WALLPAPER_ONLINE_UPLOAD_PATH = "/wallpaper";
    public static final String WALLPAPER_ONLINE_BANNER_UPLOAD_PATH = "/wallpaperBanner";
    public static final String FEEDBACK_IMAGE_UPLOAD_PATH = "/feedback";
    public static final String NEW_APP_STORE_IMAGE_UPLOAD_PATH = "/appStore";
    public static final String APP_ICON_TYPE = "icon";
    public static final String DUIBA_CSV = "/duiba";
    public static final String MIGUVIDEO_IMAGE = "/miguvideo/image";
    public static final String MIGUVIDEO_OUTPUT = "/miguvideo/output";
    public static final String MIGUVIDEO_XML = "/miguvideo/xml";
    public static final String PUBLISH_INFO = "/publishImg";
    public static final String LAUNCHER_STATS_PATH = "/launcher";
    public static final String LEFT_SCREEN_STATS_PATH = "/lsStats";
    public static final String NEW_STATS_PATH = "/newStats";
    public static final String MIGUMUSIC_LIST = "/migumusic/list";
    public static final String COMPRESS_IMAGE_DOWNLOAD = "/compress/download";
    public static final String COMPRESS_IMAGE = "/compress/image";

    public static final String ADDRESS_FILE = "/address";

    public static final String SHIPPER_CODE_UPLOAD_PATH = "/ShipperCode";
    public static final String SHIPPER_CODE_FILENAME = "ShipperCode.xlsx";

    static {
        ICON_FILE_TYPE.add("gif");
        ICON_FILE_TYPE.add("png");
        ICON_FILE_TYPE.add("jpg");
        ICON_FILE_TYPE.add("jpeg");
    }

    /**
     * backup START
     */
    public static final String BACKUP_FILE_UPLOAD_DIR = Environment.getValue("backup_file_upload_dir");
    /**backup END*/

    /**
     * SC START
     */
    public static final String SC_USER_ADD_URL = Environment.getValue("sc_user_add_url");
    /**SC END*/

    /**
     * Shengshier assistant start
     **/
    public static final String LD_VERIFY_URL = Environment.getValue("ledong_verify_url");
    public static final String LD_USER_URL = Environment.getValue("ledong_user_url");
    public static final String LD_RESET_PASSWORD_URL = Environment.getValue("ledong_reset_password");
    /**
     * Shengshier assistant end
     **/

    public static final String USER_POINT_URL = Environment.getValue("user_point_url");

    public static final boolean SHENGSHIER_SYSTEM_FLAG = Environment.getBooleanValue("shengshier_system_flag");

    public static final String SHENGSHIER_SERVER_URL = Environment.getValue("shengshier_server_url");

    public static final String DUIBA_RECORD_URL = Environment.getValue("duiba_record_url");

    public static final String ALBUM_CARD_URL = Environment.getValue("album_card_url");

    public static final String MANUAL_DOWNLOAD = "1";
    public static final String MANUAL_UPLOAD = "2";
    public static final String BATCH_UPLOAD = "3";

    public static final String AIM_REGISTER = "register";
    public static final String AIM_LOGIN = "login";
    public static final String AIM_INFO = "info";

    public static final String FILTER_SUBSCRIBER = "subscriber";
    public static final String FILTER_SUBSCRIBING = "subscribing";

    /**
     * Zenlife
     **/
    public static final String EMPTY_FOR_VERIFY = "";

    public static final boolean SC_API_FLAG = Environment.getBooleanValue("sc_api_flag");

    /**
     * 咪咕合作方id
     **/
    public static final String MIGU_PARTNER_ID = Environment.getValue("migu_partner_id");

    /**
     * 咪咕第三方标志id
     **/
    public static final String MIGU_CLIENT_ID = Environment.getValue("migu_client_id");

    /**
     * 咪咕服务器地址
     **/
    public static final String MIGU_SERVICE_URL = Environment.getValue("migu_service_url");

    /** 四川剩余流量查询地址 **/
//    public static final String SC_FREEMIN_URL = Environment.getValue("sc_freemin_url");

    /**
     * 四川移动clientId
     **/
    public static final String SC_CLIENT_ID = "83388a97-aaee-4c3e-9ffe-89d3a7cd0bad";
//    public static final String SC_CLIENT_ID = Environment.getValue("sc_client_id");

    /**
     * 四川扣费地址
     **/
    public static final String SC_RECHARGE_URL = Environment.getValue("sc_recharge_url");

    public static final String[] MIGU_VEDIO_DEFINITION = {"HIGH", "NORMAL", "720P", "1080P"};

    public static final String NULL = "无数据";

    public static final List<String> MIGU_DEFINITION_LIST = Arrays.asList("HIGH", "NORMAL", "720P", "1080P");

    /**
     * 分享资讯地址
     **/
    public static final String SHARE_INFO_URL = Environment.getValue("share_info_url");

    public static final String SCYD_APP_KEY = "21000201";

    public static final String SCYD_USER_NAME = "yunshouji";

    //    public static final String API_STORE_HOST = Environment.getValue("api_store_host");
//    public static final String API_STORE_HOST = "https://223.87.178.96/scyd/cloudapi/";
    public static final String API_STORE_HOST = "http://218.205.252.26:32000/rest/1.0/";

    /**
     * 定时任务初始化flag
     **/
    public static final boolean SCHEDULE_JOB_INIT_FLAG = Environment.getBooleanValue("schedule_job_init_flag");

    /**
     * 微信项目域名
     **/
    public static final String WEIXIN_BASE_URL = Environment.getValue("weixin_base_url");


    /**系统配置*/
    /**
     * 应用权限允许比例标示 0：手动设置(默认) 1：系统计算
     */
    public static final String CONFIG_APP_PERMISSION_RATE_FLG = "CONFIG_APP_PERMISSION_RATE_FLG";

    /**
     * PC助手设置客户端安装省事儿版本ID, 不设置的时候取省事儿最高版本
     */
    public static final String CONFIG_PC_SHENGSHIER_VERSION_ID = "CONFIG_PC_SHENGSHIER_VERSION_ID";

    /**
     * 微信侧用户默认兑吧VIP等级
     */
    public static final String CONFIG_WX_DUIBA_DEFAULT_LEVEL = "CONFIG_WX_DUIBA_DEFAULT_LEVEL";

    /**
     * 平台管理
     **/
    public static final String PLATFORM_CLIENT = "client";
    public static final String PLATFORM_PC = "pc";
    public static final String PLATFORM_WX = "wx_public";

    public static final List<String> MIGU_MIN_MODES = Arrays.asList("ACAZ26111", "ACAZ26112", "ACAZ26113", "ACAZ26114", "ACAZ26115", "ACAZ26184", "ACAZ26185", "ACAZ27037");

    /**
     * 检索条件后缀
     */
    public static class CONDITION_SUFFIX {
        /**
         * 非空
         */
        public static final String NOT_NULL = "_not_null";
        /**
         * 为空
         */
        public static final String NULL = "_null";
        /**
         * 模糊查询
         */
        public static final String LIKE = "_like";
        /**
         * >=查询
         */
        public static final String START = "_start";
        /**
         * <=查询
         */
        public static final String END = "_end";
    }

    /**
     * 通用状态
     */
    public static class COMMON_STATE {
        /**
         * 状态 0：无效 1：有效
         */
        public static final int INVALID = 0;
        public static final int VALID = 1;
    }

    /**
     * 售后订单状态
     */
    public static class MAINTENANCE_ORDER_STATUS {
        /**
         * 状态 1：待审核 2：审核通过，等待上门取件 3：拒绝
         */
        public static final int ACTIVATE = 1;
        public static final int APPROVED = 2;
        public static final int REJECTED = 3;
    }

    /**
     * O2O跳转类型
     */
    public static class O2O_REDIRECT_TYPE {
        /**
         * 打开类型 0：普通url， 1：客户端页面， 2：第三方认证url， 3：触宝
         */
        public static final int URL = 0;
        public static final int CLIENT = 1;
        public static final int AUTH_URL = 2;
        public static final int CHUBAO = 3;
    }

    /**
     * 用于cms查询条件过滤，接口传入该值，查询时会忽略对应字段的查询条件
     */
    public static String SQL_RESTRICTIONS_FILTER_FLAG = "not_restrict";


    /**
     * excel文件输出相关
     */
    public static String DOWNLOAD_AWARD_EXCEL_EXPORT_FORDER = "/download_award";
    public static String OBJECT_DRAW_EXCEL_File_PREFIXE = "objectDrawResult";

    /**
     * 应用市场--热搜
     */
    public static String HOT_SEARCH_KEY = "hot_search";
    public static String HOT_SEARCH_FIELD = "hot_search_words";


    /**
     * Asus Account接口地址
     **/
    public static final String ASUS_ACCOUNT_API = Environment.getValue("asus_account_api");
    public static final String ASUS_ACCOUNT_TICKET_API = Environment.getValue("asus_account_ticket_api");
}
