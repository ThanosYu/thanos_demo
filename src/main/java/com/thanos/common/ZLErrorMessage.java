package com.thanos.common;

/**
 * Created by Yingjie_Li on 2015/3/12.
 */
public class ZLErrorMessage {
    public static final ZLErrorMessage ZL_DELETE_CONSTRAINT = new ZLErrorMessage("该数据有关联数据，不能删除", -101);
    public static final ZLErrorMessage ZL_DELETE_NO_DATA = new ZLErrorMessage("该数据不存在", -102);
    public static final ZLErrorMessage ZL_NO_FILE = new ZLErrorMessage("该文件不存在", -103);
    public static final ZLErrorMessage ZL_NO_APP = new ZLErrorMessage("应用不存在", -104);
    public static final ZLErrorMessage ZL_DUPLICATE_APP = new ZLErrorMessage("应用包名或者URL重复", -105);
    public static final ZLErrorMessage ZL_WEB_APP_NO_URL = new ZLErrorMessage("云应用在包名里必须填写URL", -106);
    public static final ZLErrorMessage ZL_NO_PARAMETER_ERROR = new ZLErrorMessage("缺少参数", -107);
    public static final ZLErrorMessage ZL_APP_TYPE_DELETE_ERROR = new ZLErrorMessage("存在该类型的应用，不能删除！", -108);
    public static final ZLErrorMessage ZL_APP_CATEGORY_DELETE_ERROR = new ZLErrorMessage("存在该分类的应用，不能删除！", -109);
    public static final ZLErrorMessage ZL_APP_CLICK_CATEGORY_DELETE_ERROR = new ZLErrorMessage("存在该点击类型的记录，不能删除！", -110);

    public static final ZLErrorMessage ZL_USER_NOT_LOGIN_ERROR = new ZLErrorMessage("用户未登录！", -110);
    public static final ZLErrorMessage ZL_USER_BACKUP_UP_ERROR = new ZLErrorMessage("备份文件上传失败！", -111);
    public static final ZLErrorMessage ZL_IMAGE_UP_ERROR = new ZLErrorMessage("图片文件上传失败！", -112);
    public static final ZLErrorMessage ZL_NO_USER_ERROR = new ZLErrorMessage("用户不存在！", -113);
    public static final ZLErrorMessage ZL_UPDATE_PROHIBIT_ERROR = new ZLErrorMessage("禁止更新！", -114);
    public static final ZLErrorMessage ZL_DUPLICATE_SCHEDULEJOB = new ZLErrorMessage("定时任务已存在", -115);
    public static final ZLErrorMessage ZL_DUPLICATE_BLOCK_APP = new ZLErrorMessage("App已添加", -116);
    public static final ZLErrorMessage ZL_DUPLICATE_BLOCK_BANNER = new ZLErrorMessage("Banner已添加", -117);
    public static final ZLErrorMessage ZL_DUPLICATE_SORT_NO = new ZLErrorMessage("该排序号已使用，无法添加", -118);
    public static final ZLErrorMessage ZL_NO_APP_INSIDE = new ZLErrorMessage("该排行中无应用数据，无法返回", -119);
    public static final ZLErrorMessage ZL_DUPLICATE_ARTICLE = new ZLErrorMessage("文章重复", -120);
    public static final ZLErrorMessage ZL_DUPLICATE_POSITION = new ZLErrorMessage("位置重复", -121);

    /**版本更新*/
    public static final ZLErrorMessage ZL_APK_VERSION_DUPLICATE = new ZLErrorMessage("省事儿版本已经存在！", -200);
    public static final ZLErrorMessage ZL_APK_VERSION_NO_UPDATE = new ZLErrorMessage("版本已经是最新的！", -201);

    /**sc*/
    public static final ZLErrorMessage ZL_SC_VERIFY_CODE_SEND_ERROR = new ZLErrorMessage("验证码发送失败", -10000);
    public static final ZLErrorMessage ZL_SC_VERIFY_CODE_ERROR = new ZLErrorMessage("验证码验证失败", -10001);
    public static final ZLErrorMessage ZL_SC_USER_CREATE_ERROR = new ZLErrorMessage("用户创建失败！", -10002);
    public static final ZLErrorMessage ZL_SC_IP_ERROR = new ZLErrorMessage("非法操作！", -10003);
    public static final ZLErrorMessage ZL_SC_IP_ERROR2 = new ZLErrorMessage("非法操作！", -10004);

    /**sign认证*/
    public static final ZLErrorMessage ZL_SIGN_ERROR = new ZLErrorMessage("签名不正确！", -11000);
    public static final ZLErrorMessage CLIENT_AUTH_ERROR = new ZLErrorMessage("客户端登陆认证失败!", -20030);


    /**CMS认证*/
    public static final ZLErrorMessage CMS_NO_USER_ERROR = new ZLErrorMessage("用户名或者密码错误！", -20000);
    public static final ZLErrorMessage CMS_AUTH_ERROR = new ZLErrorMessage("登录认证失败！", -20001);

    /**省事儿桌面秘钥验证**/
    public static final ZLErrorMessage SHENGSHIER_AUTH_ERROR = new ZLErrorMessage("秘钥验证失败!", -20002);
    public static final ZLErrorMessage SHENGSHIER_NO_AIM = new ZLErrorMessage("错误的操作", -20003);

    /**华硕Zenlife商城**/
    public static final ZLErrorMessage ZENLIFE_NO_SUCH_ACTION = new ZLErrorMessage("错误的操作", -20004);
    public static final ZLErrorMessage ZENLIFE_AUTH_ERROR = new ZLErrorMessage("平台登录认证失败!", -20005);
    public static final ZLErrorMessage ZENLIFE_LOGINI_ERROR = new ZLErrorMessage("用户名密码错误!", -20006);
    public static final ZLErrorMessage ZENLIFE_USERNAME_NOT_EXISTED = new ZLErrorMessage("用户名不存在", -20036);
    public static final ZLErrorMessage ZENLIFE_EXISTED_ERROR = new ZLErrorMessage("用户已注册!", -20007);
    public static final ZLErrorMessage ZENLIFE_APIIKEY_ERROR = new ZLErrorMessage("不存在的API KEY!", -20008);
    public static final ZLErrorMessage ZENLIFE_TOKEN_ERROR = new ZLErrorMessage("Token验证失败!", -20009);
    public static final ZLErrorMessage ZENLIFE_FETCH_ERROR = new ZLErrorMessage("用户信息获取失败!", -20010);
    public static final ZLErrorMessage ZENLIFE_UPDATE_ERROR = new ZLErrorMessage("用户信息更新失败", -20011);
    public static final ZLErrorMessage ZENLIFE_PWDWRONG = new ZLErrorMessage("原密码不匹配", -20012);
    public static final ZLErrorMessage ZENLIFE_NOUSR = new ZLErrorMessage("用户不存在", -20013);
    public static final ZLErrorMessage ZENLIFE_UNKNOWMERROR = new ZLErrorMessage("未知错误", -20014);
    public static final ZLErrorMessage ZENLIFE_VERIFYFAILED = new ZLErrorMessage("验证码错误", -20015);
    public static final ZLErrorMessage ZL_FETCH_FAILED = new ZLErrorMessage("获取列表失败", -20016);
    public static final ZLErrorMessage ZENLIFE_NO_SUCH_USER = new ZLErrorMessage("没有此用户", -20017);
    public static final ZLErrorMessage ZENLIFE_WRONG_SIGN = new ZLErrorMessage("签名错误", -20018);
    public static final ZLErrorMessage ZENLIFE_PARSE_FAULT = new ZLErrorMessage("日期转换错误", -20019);
    public static final ZLErrorMessage ZENLIFE_MOBILE_EXISTED = new ZLErrorMessage("此手机号已被绑定", -20020);
    public static final ZLErrorMessage ZENLIFE_NAME_EXISTED = new ZLErrorMessage("此用户名已被绑定", -20020);
    public static final ZLErrorMessage ZENLIFE_USER_CREATE_ERROR = new ZLErrorMessage("平台用户创建失败", -20021);
    public static final ZLErrorMessage ZENLIFE_MAIL_PLATFORM_ERROR = new ZLErrorMessage("短信接口调用错误", -20022);
    public static final ZLErrorMessage ZENLIFE_LACK_OF_POINT = new ZLErrorMessage("积分不足", -20029);
    public static final ZLErrorMessage ZENLIFE_ALREADY_EXIST = new ZLErrorMessage("用户已经注册过", -20031);
    public static final ZLErrorMessage ZENLIFE_FAKE_FAILED = new ZLErrorMessage("获取加用户失败", -20034);
    public static final ZLErrorMessage ZENLIFE_FILE_OPERATE_FAILED = new ZLErrorMessage("文件处理失败", -20035);
    public static final ZLErrorMessage ZENLIFE_USER_POINT_EDIT_FAILED = new ZLErrorMessage("用户积分修改失败", -20037);
    public static final ZLErrorMessage ZENLIFE_WRONG_RELATIONSHIP = new ZLErrorMessage("合伙人关系有误", -20041);

    /**Money Plus**/
    public static final ZLErrorMessage MONEY_PLUS_MISSION_FETCH_FAILED = new ZLErrorMessage("签到任务获取失败", -20023);
    public static final ZLErrorMessage CONVERT_FAILED = new ZLErrorMessage("转换错误", -20024);
    public static final ZLErrorMessage AD_FETCH_FAILED = new ZLErrorMessage("广告获取失败", -20025);
    public static final ZLErrorMessage CHECK_IN_PAGE_FAULT = new ZLErrorMessage("页面上存在错误", -20026);
    public static final ZLErrorMessage MISSSON_DETAIL_FETCH_FAILED = new ZLErrorMessage("任务详情获取失败", -20027);
    public static final ZLErrorMessage WRONG_VERSION = new ZLErrorMessage("任务详情获取失败", -20050);
    public static final ZLErrorMessage NO_RANK = new ZLErrorMessage("暂无排行", -20051);
    public static final ZLErrorMessage NOT_IN_RANK = new ZLErrorMessage("暂未上榜", -20052);
    public static final ZLErrorMessage REACH_LIMIT = new ZLErrorMessage("超过积分上限！", -20053);

    /**积分记录**/
    public static final ZLErrorMessage ZL_NO_SUCH_ITEM = new ZLErrorMessage("无此条数据", -300);
    public static final ZLErrorMessage ZL_DUPLICATE_POINT_RECORD = new ZLErrorMessage("此条积分记录已存在", -301);
    public static final ZLErrorMessage ZL_REACH_MAX_TIMES = new ZLErrorMessage("已达到最大积分次数", -302);
    public static final ZLErrorMessage ZL_DUPLICATE_REQUEST = new ZLErrorMessage("正在处理，请不要重复提交", -303);
    public static final ZLErrorMessage ZL_REDIS_ERROR = new ZLErrorMessage("缓存错误", -304);
    public static final ZLErrorMessage ZL_NO_BEHAVOIR_CONFIG = new ZLErrorMessage("未找到此行为", -305);
    public static final ZLErrorMessage ZL_REACH_MAX_POINT = new ZLErrorMessage("已达到最大积分", -306);

    /**应用安装使用统计**/
    public static final ZLErrorMessage ZL_IMEI_OR_DEVICE_EMPTY = new ZLErrorMessage("imei号或设备号不存在", -50000);
    public static final ZLErrorMessage ZL_UPLOAD_FAILED = new ZLErrorMessage("文件上传失败", -50001);
    public static final ZLErrorMessage ZL_UPLOAD_IO_ERROR = new ZLErrorMessage("IO口异常", -50002);
    public static final ZLErrorMessage ZL_DATA_SAVE_ERROR = new ZLErrorMessage("数据存储错误", -50003);

    /**CMS/CLIENT登陆验证失败**/
    public static final ZLErrorMessage ZL_VALIDATE_FAILED = new ZLErrorMessage("用户验证失败", -20028);

    /**独立版本**/
    public static final ZLErrorMessage ZL_DUPLICATE_FAVORITE_RECORD = new ZLErrorMessage("您已收藏过此资讯", -20032);
    public static final ZLErrorMessage ZL_NO_RECORD_TO_DELETE = new ZLErrorMessage("记录不存在，无法删除", -20033);

    /**清单卡片**/
    public static final ZLErrorMessage ZL_ALBUM_NOT_FOUND = new ZLErrorMessage("清单不存在", -20100);
    public static final ZLErrorMessage ZL_ALBUM_ITEM_NOT_FOUND = new ZLErrorMessage("条目不存在", -20101);
    public static final ZLErrorMessage ZL_NO_AUTHORITY_TO_MODIFY = new ZLErrorMessage("无权更改此清单", -20102);
    public static final ZLErrorMessage ZL_DUPLICATE_ALBUM_FAVOR = new ZLErrorMessage("已收藏过此清单，不能重复收藏", -20103);
    public static final ZLErrorMessage ZL_NO_DATA_FOUND = new ZLErrorMessage("未找到数据", -20104);
    public static final ZLErrorMessage ZL_NO_FUNCTION = new ZLErrorMessage("暂无分享微清单功能", -20105);

    /**账号打通**/
    public static final ZLErrorMessage ZL_TOKEN_CREATE_FAILED = new ZLErrorMessage("token 生成失败", -20038);
    public static final ZLErrorMessage ZL_WRONG_TICKET = new ZLErrorMessage("ticket 错误", -20039);
    public static final ZLErrorMessage ZL_EXPIRED_TICKET = new ZLErrorMessage("ticket 已过期，请重新获取", -20040);
    public static final ZLErrorMessage ZL_AUTH_FAIL = new ZLErrorMessage("token 认证失败！", -20042);
    public static final ZLErrorMessage ZL_UNKNOWN_PLATFORM = new ZLErrorMessage("请求来自未知平台", -20043);
    public static final ZLErrorMessage ZL_NULL_POINT = new ZLErrorMessage("zen币不可为空！", -20044);
    public static final ZLErrorMessage ZL_NO_ADDING_POINT = new ZLErrorMessage("此接口不可增加zen币！", -20045);
    public static final ZLErrorMessage ZL_NO_ORDER = new ZLErrorMessage("订单不存在！", -20046);
    public static final ZLErrorMessage ZL_DUPLICATE_ORDER = new ZLErrorMessage("订单已存在，请不要重复扣分！", -20047);
    public static final ZLErrorMessage ZL_NOT_TEST_USER = new ZLErrorMessage("请使用测试用户！", -20048);

    /**服务器监控**/
    public static final ZLErrorMessage ZL_SERVER_REDIS_ERROR = new ZLErrorMessage("Redis 调用失败！", -401);
    public static final ZLErrorMessage ZL_SERVER_DATABASE_ERROR = new ZLErrorMessage("数据库调用失败！", -402);
    public static final ZLErrorMessage ZL_SERVER_REDIS_AND_DATABASE_ERROR = new ZLErrorMessage("Redis 和数据库均调用失败！", -403);

    public static final ZLErrorMessage ZL_NO_USER_RECORD = new ZLErrorMessage("无此用户记录！", -150);

    /**用户指导**/
    public static final ZLErrorMessage ZL_DEVICE_TYPE_ERROR = new ZLErrorMessage("机型错误!", -20056);

    /**赚+用户验证**/
    public static final ZLErrorMessage ZL_TOKEN_FAILED = new ZLErrorMessage("Token验证失败!", -20057);

    public static final ZLErrorMessage ZL_ERROR_FORM_FILE = new ZLErrorMessage("文件格式错误", -122);


    private String msg;
    private int code;
    public ZLErrorMessage(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
