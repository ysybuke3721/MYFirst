package com.slr.slrapp.utils;

/**
 * 存放一些标记，常量等
 * <p/>
 * <p/>
 * Created by admin on 2016/7/8.
 */
public class ContentValues {

    //直播的播放路径
//    public static final String VIDEO_PLAYER_URL = "http://vc7bd8aa.live.126.net/live/0c70e084aec84c348361ca59551e1480.flv";
//    //测试的baseUrl
//    public static final String BASE_URL = "http://192.168.0.188:8080/slrkj/appApi/";
    //测试的baseUrl
    public static final String BASE_URL = "http://115.28.184.116/slrkj/appApi/";
    //注册url
    public static final String REGISTER_URL = BASE_URL + "register";
    //登录url
    public static final String LOGIN_URL = BASE_URL + "login";
    //获取验证码url
    public static final String ENSURE_NUM_URL = BASE_URL + "messageVerify";
    //获取验证码_找回密码
    public static final String ENSURE_NUM_URL_FIND = BASE_URL + "forgotPasswordMessage";
    //找回密码
    public static final String FIND_NUM_URL = BASE_URL + "forgotPassword";
    //获取用户资料


    //从服务器返回的TOKEN值的键
    public static final String TOKEN_NAME = "token_name";

    //首页下拉刷新url
    public static final String FIRST_PAGE_REFRESH_URL = BASE_URL + "getInformation";
    //获取商品详情的url
    public static final String GOODS_DETAIL_URL = BASE_URL + "getComInformation";

    //第二个页面请求网络的url
    public static final String FARM_PAGE_URL = BASE_URL + "getSpaces";
    //查询购物车列表
    public static final String SHOP_CAR_URL = BASE_URL + "getCartList";
    //轮播图url
    public static final String LUN_BO_URL = BASE_URL + "indexCarousel";
    //提交订单url
    public static final String SUBMIT_ORDER_URL = BASE_URL + "conFirmOrder";
    //筛选栏url
    public static final String FILTER_BLANK_URL = BASE_URL + "typeList";

    //根据商品id查询养殖场监控视频等url
    public static final String FARM_CAMERA_URL = BASE_URL + "getSpacesId";
    //添加收藏url
    public static final String ADD_COLLECT_URL = BASE_URL + "collect";
    //取消收藏url
    public static final String CANCEL_COLLECT_URL = BASE_URL + "deleteCollect";

    //添加到购物车url
    public static final String ADD_TO_SHOP_CAR_URL = BASE_URL + "addCart";
    //删除购物车url
    public static final String DELETE_FROM_SHOP_CAR = BASE_URL + "deleteCart";
    //修改购物车数量
    public static final String CHANGE_SHOP_CAR_GOODS_COUNT_URL = BASE_URL + "getComNum";
    //查询收货地址
    public static final String SEARCH_RECEIVE_AREA=BASE_URL+"getAddress";
    //查询默认收货地址
    public static final String GET_DEFAULT_AREA_URL= BASE_URL+"settlement";
    //删除收货地址
    public static final String DELETE_RECEIVE_AREA= BASE_URL+"delete";
    //更改默认收货地址
    public static final String CHANGE_DEFAULT_RECEIVE_AREA=BASE_URL+"defaultAddress";

    //从订单页到收货地址页的请求吗
    public static final int GET_A_RECEIVE_AREA=212;
    //从地址页获得地址后的返回码
    public static final int SEND_A_RECEIVE_AREA=222;
    //地址页传递对象的键
    public static final String KEY_OF_GET_AREA="key_of_get_area";
    //留言的url
    public static final String LEAVE_MESSAGE_URL=BASE_URL+"addMessage";
    //在地图上查看牧场的位置
    public static final String SEARCH_FARM_LOCATION_URL=BASE_URL+"searchSpace";




    //用户id
    public static final String USER_ID = "user_id";


    //SharedPreferences 的文件名
    public static final String SP_NAME = "SharedPreferences_name";


    //第二个页面的第三个选择器是否选中了某个条目
    public static final String IF_ONE_ITEM_SELECTED = "if_one_item_selected";
    //第二个页面的第三个选择器是否选中了  设置为默认
//    public  static  final  String IF_SELECT_DEFAULT="if_select_default";

    //存放第二个页面第一个选择器的文字
    public static final String SELECTOR_FIRST_WORD = "selector_one_word";
    public static final String REMBER_SELECTOR_FIRST_WORD_ID = "rember_one_word_id";  //用于缓存商品列表

    //存放第二个页面第二个选择器的文字
    public static final String SELECTOR_SECOND_WORD = "selector_second_word";

    //存放第二个页面第三个选择器的文字的缓存
    public static final String REMBER_SELECTOR_THIRED_WORD = "rember_third_word";
    //存放第二个页面第三个选择器的文字
    public static final String SELECTOR_THIRD_WORD = "selector_third_word";


    //存放用intent  首页一级界面传递到二级界面对象的 键的名字
    public static final String FIRST_INTENT_BEAN_NAME = "first_intent_bean_name";
    //存放用intent  首页一级界面传递到二级界面对象的 bundle的名字
    public static final String FIRST_INTENT_BEAN_BUNDLE_NAME = "first_intent_bean_bundle_name";




    //当前最终确定定位的城市
    public static final String LOCATED_CITY_NAME = "located_city_name";

    //视频直播页面点击购买后的商品数量的键
    public static final String HOW_MANY_TO_BUY = "how_many_to_buy";


    //是否是第一次登陆
    public static final String IF_FIRST_LOGIN = "if_first_login";
    //用户是否登录
    public static final String IF_IS_LOGINED = "if_is_logined";
    //是否有用户头像
    public static final String HAS_FACE = "has_face";
    //用户头像
    public static final String FACE = "face";

    //是否记住密码
    public static final String IF_REMBER_NAME = "if_rember_name";
    //是否是第一次进入应用
    public static final String IF_FIRST_USE = "if_first_use";


    //登录跳转到注册的请求码
    public static final int OPEN_REGISTER = 100;
    //注册页面响应码
    public static final int TO_LOGIN_PAGE = 101;

    //购物车点击提交订单传递集合的键
    public static final String TO_SUBMIT_ORDER = "goumai";
    //多订单支付时点击横向的图片列表传递数据的键
    public static final String TO_SEE_LIST = "to_see_list";
    //提交订单路径，0 直接提交，1 购物车提交
    public static final String  ORDER_PATH="order_path";
    //传递到订单页的商品的json
    public static final String GOODS_JSON="goods_json";


    //传递总数量。总价格的标记
    public static final String TOTAL_COUNT = "allCount";
    public static final String TOTAL_PRICE = "totalprice";
    public static final String ALL_COUNT = "shuliang";

    //注册页面传递的用户名
    public static final String USER_TEL = "user_tel";


}
