package netease.test.common;

public class KeyValues {

	/** 参数前缀定义 */
	public static final String SYS_PREF = "auth_";

	// ************************* 通用值定义
	/** 是 */
	public static final int VAL_TRUE = 1;
	/** 否 */
	public static final int VAL_FALSE = 0;

	public static final int VAL_INVALID_ST = -9999;

	/** 有效 */
	public static final int VAL_STATUS_VALID = 1;
	/** 无效 */
	public static final int VAL_STATUS_INVALID = 0;
	/** 已取消 */
	public static final int VAL_STATUS_CANCELLED = -1;

	// ************************* POP接口结果定义
	/** 结果：成功 */
	public static final int VAL_RET_CODE_SUCCESS = 200;
	/** 结果：待确认 */
	public static final int VAL_RET_CODE_ORDER_WAIT = 100;
	/** 结果：失败，可重试 */
	public static final int VAL_RET_CODE_ERROR_WAIT = -100;
	/** 结果：失败，无需重试 */
	public static final int VAL_RET_CODE_ERROR = -200;
	/** 结果：失败，sign校验失败 */
	public static final int VAL_RET_SIGN_ERROR = -1000;
	/** 结果：失败，接口未授权 */
	public static final int VAL_RET_NO_AUTH = -1001;

	// ************************* 夺宝接口参数定义
	/** 结果编码 */
	public static final String KEY_CODE = "code";
	/** 提示信息 */
	public static final String KEY_MSG = "msg";

	public static final String KEY_MESSAGE = "message";
	/** 返回结果 */
	public static final String KEY_RESULT = "result";
	/** 数据 */
	public static final String KEY_DATA = "data";
	/** 产品号 */
	public static final String KEY_AID = "aid";
	/** 校验码字段 */
	public static final String KEY_SIGN = "sign";
	/** 地址类型：自定义 */
	public static final short ADDRESS_TYPE_NORMAL = 0;
	/** 地址类型：广州 */
	public static final short ADDRESS_TYPE_GZ = 1;
	/** 地址类型：杭州 */
	public static final short ADDRESS_TYPE_HZ = 2;

}
