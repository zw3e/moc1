package com.moc.common.utils;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;

import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;

import com.moc.common.Constant.Constants;
import com.moc.common.entity.Page;



/**
 * 类描述：公共工具类
 * @author Administrator
 * 
 */
public class CommonUtil {
	/**
	 * 生成随机字符串的元数据
	 */
	public static final String WORD_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

	/**
	 * 方法描述：获取异常完整堆栈信息
	 * @param e 异常信息
	 * @return 异常完整堆栈信息
	 */
	public static List<String> getExceptionStack(Throwable e) {
		List<String> retList = new ArrayList<String>();
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			// 将出错的栈信息输出到printWriter中
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		String[] arr = sw.toString().split("\r\n");
		Collections.addAll(retList, arr);
		return retList;
	}

	/**
	 * 方法描述：获取servlet上下文
	 * @return ServletContext
	 */
	public static ServletContext getServletContext() {
		return ContextLoader.getCurrentWebApplicationContext().getServletContext();
	}

	/**
	 * 方法描述：数字前导填零
	 * @param num 待填零的数字 width 需要的数字宽度
	 * @return 填零后的数字字符串形式
	 */
	public static String getPreFillNum(int num, int width) {
		String strNum = String.valueOf(num);
		int len = strNum.length();
		if (num <= 0 || width - len <= 0) {
			return String.valueOf(num);
		}
		for (int i = 0; i < width - len; i++) {
			strNum = "0" + strNum;
		}
		return strNum;
	}

	/**
	 * 方法描述：根据位数获取随机数
	 * @param width 位数
	 * @return 随机数
	 */
	public static String getRandomNum(int width) {
		StringBuilder result = new StringBuilder();
		result.append(String.valueOf((long) (new Date().getTime() * Math.random() * 1000)));
		int len = result.length();
		if (len < width) {
			for (int i = width - len; i > 0; i--) {
				result.insert(0, "0");
			}
		} else {
			return result.substring(len - width, len);
		}
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(getRandomNum(6));
	}
	/**
	 * Description: 获取指定位数的随机字符串
	 * @param width 位数
	 * @return 随机字符串
	 */
	public static String getRandomStr(int width) {
		String result = "";
		for (int i = 0; i < width; i++) {
			int d = (int) (Math.random() * WORD_STRING.length());
			result += WORD_STRING.charAt(d);
		}
		return result;
	}

	/**
	 * 字符串是否以指定后缀结尾
	 * @param str 待检查字符串 suffix 后缀
	 * @return 是否以指定后缀结尾
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		return !(!StringUtils.hasLength(str) || !StringUtils.hasLength(suffix) || str.length() < suffix.length())
				&& str.substring(str.length() - suffix.length()).equalsIgnoreCase(suffix);
	}

	/**
	 * 判断List集合是否为空
	 * @param <T>
	 */
	public static <T> boolean isEmptyList(List<T> list) {
		return null == list || list.isEmpty();
	}




	/**
	 * 获取Integer的String值
	 */
	public static String getStringValue(Integer value) {
		if (null == value) {
			return "";
		}

		return value.toString();
	}

	/**
	 * String转Integer工具类
	 */
	public static Integer StringToInteger(String str) {
		return StringUtils.hasLength(str) ? Integer.valueOf(str) : null;
	}

	/**
	 * 获取颜色
	 * @param
	 * @return
	 */
	public static Color getRandColor(int lower, int upper) {
		Random random = new Random();
		if (upper > 255)
			upper = 255;
		if (upper < 1)
			upper = 1;
		if (lower < 1)
			lower = 1;
		if (lower > 255)
			lower = 255;
		int r = lower + random.nextInt(upper - lower);
		int g = lower + random.nextInt(upper - lower);
		int b = lower + random.nextInt(upper - lower);
		return new Color(r, g, b);
	}

	/**
	 * 获取指定字符串中子串的出现位置, 忽略大小写
	 * @param source 目标字符串
	 * @param suffix 子串
	 * @return 子串在目标字符串中出现的位置
	 */
	public static int indexOfIgnoreCase(String source, String suffix) {
		return source.toLowerCase().indexOf(suffix.toLowerCase());
	}

	/**
	 * 比较时间大小
	 * @param source
	 * @param target
	 * @return 1表示前者大于后者 -1表示前者小于后者 0表示相等
	 * @throws ParseException
	 */
	public static int compareDate(String source, String target) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1 = df.parse(source);
		Date date2 = df.parse(target);
		return date1.compareTo(date2);
	}

	/**
	 * 和当前时间比较
	 * @param source
	 * @return 1表示前者大于后者 -1表示前者小于后者 0表示相等
	 * @throws ParseException
	 */
	public static int compareNow(String source) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = df.parse(source);
		return date1.compareTo(new Date());
	}

	/**
	 * 初始化Page信息
	 * @param page 前台传入的page参数
	 */
	public static Page initializationPage(Page page) {
		if (page != null) {
			if (page.getStart() == null && page.getLength() != null) {
				page.setStart(Constants.PAGE_START);
			} else if (page.getStart() != null && page.getLength() == null) {
				page.setLength(Constants.PAGE_SIZE);
			} else if (page.getStart() == null && page.getLength() == null) {
				// 如果Page的两个成员变量都为null, 则将page置为null
				page = null;
			}
		}
		return page;
	}
}