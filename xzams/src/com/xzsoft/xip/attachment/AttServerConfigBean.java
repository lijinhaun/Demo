package com.xzsoft.xip.attachment;


/**
 * ClassName:AttServerConfigBean
 * Function: TODO
 *
 * @author   guoxf
 * @version  Ver 1.0
 * @since    Ver 1.0
 * @Date	 2014骞�鏈�5鏃ヤ笅鍗�:47:43
 *
 */
public class AttServerConfigBean {
	private static String userName;
	private static String password;
	private static String attachmentPath;
	private static int maxFileSize;

	/**
	 * getUserName:(鑾峰彇閰嶇疆鏂囦欢涓殑鐢ㄦ埛鍚�
	 *
	 * @return 鐢ㄦ埛鍚�
	 * @author guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static String getUserName() {
		return userName;
	}

	/**
	 * setUserName:(璁剧疆鐢ㄦ埛鍚�
	 *
	 * @param userName 鐢ㄦ埛鍚�
	 * @author guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static void setUserName(String userName) {
		AttServerConfigBean.userName = userName;
	}


	/**
	 * getPassword:(鑾峰彇閰嶇疆鏂囦欢涓殑鍙ｄ护)
	 *
	 * @return
	 * @author guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static String getPassword() {
		return password;
	}

	/**
	 * setPassword:(璁剧疆鍙ｄ护)
	 *
	 * @param password
	 * @author guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static void setPassword(String password) {
		AttServerConfigBean.password = password;
	}


	/**
	 * getAttachmentPath:(鑾峰彇闄勪欢瀛樻斁璺緞锛屽鏋滈厤缃枃浠舵湭璁剧疆鍒欏湪web宸ョ▼涓嬬殑attachment涓�
	 *
	 * @return
	 * @author guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static String getAttachmentPath() {
		return attachmentPath;
	}

	/**
	 * setAttachmentPath:(璁剧疆闄勪欢鐨勫瓨鏀捐矾寰�
	 *
	 * @param attachmentPath
	 * @author guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static void setAttachmentPath(String attachmentPath) {
		AttServerConfigBean.attachmentPath = attachmentPath;
	}

	/**
	 * getMaxFileSize:(鑾峰彇闄勪欢鏈�ぇ鏂囦欢澶у皬闄愬埗鍗曚綅鏄疢)
	 *
	 * @return 鏈�ぇ鏂囦欢澶у皬闄愬埗鍗曚綅鏄疢
	 * @author guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static int getMaxFileSize() {
		return maxFileSize;
	}

	
	/**
	 * setMaxFileSize:(璁剧疆鏈�ぇ鏂囦欢澶у皬闄愬埗鍗曚綅鏄疢)
	 *
	 * @param maxFileSize
	 * @author guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static void setMaxFileSize(int maxFileSize) {
		AttServerConfigBean.maxFileSize = maxFileSize;
	}

	/**
	 * checkUser:(鏍￠獙鐢ㄦ埛鍜屽彛浠ゆ槸鍚︿笌鏈嶅姟鍣ㄩ厤缃枃浠朵腑鐨勪竴鑷�
	 *
	 * @param userName 鐢ㄦ埛鍚�
	 * @param password 鍙ｄ护
	 * @return 涓�嚧杩斿洖true锛屽惁鍒欒繑鍥瀎alse
	 * @throws Exception 褰撻獙璇佺敤鎴峰拰鍙ｄ护涓嶅悎娉曠殑鏃跺�鎶涘嚭寮傚父骞剁粰鍑烘彁绀�
	 * @author Guoxf
	 * @version Ver 1.0
	 * @since   Ver 1.0
	*/
	public static Boolean checkUser(String userName, String password)
			throws Exception {
		if (userName == null || userName.trim().length() == 0) {
			throw new Exception("浼犲叆鐢ㄦ埛鍚嶄负绌猴紒");
		} else {
			if (password == null || password.trim().length() == 0) {
				throw new Exception("浼犲叆瀵嗙爜涓虹┖锛�");
			} else {
				if (!AttServerConfigBean.getUserName().equals(userName)) {
					throw new Exception("浼犲叆鐢ㄦ埛鍚嶄笉姝ｇ‘锛�");
				} else {
					if (!AttServerConfigBean.getPassword().equals(password)) {
						throw new Exception("浼犲叆瀵嗙爜涓嶆纭紒");
					} else {
						return true;
					}
				}
			}

		}
	}
}
