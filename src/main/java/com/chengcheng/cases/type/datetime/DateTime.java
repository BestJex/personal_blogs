package com.chengcheng.cases.type.datetime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 参考: https://mp.weixin.qq.com/s?__biz=MzA3ODQ0Mzg2OA==&mid=2649051659&idx=3&sn=863813c17188486bbe74c84fb9114e2b&chksm=87535638b024df2ea8198f09cb16500c2504ebca8bebabea8328c6e9dcf6bffb0b6bb6f5ed85&scene=126&sessionid=1584612512&key=68d3afa56a8f777642b0b21a15209d11f8aa17b16b7a6896ead403a2087c6b771f003e6026f9f4508ef41ea1489c3299e60417dc127657ae108f5c9a71301efbb30bc68be0ef49b3f0a1be29913ca6ce&ascene=1&uin=MjI4NDc3MDg4Mg%3D%3D&devicetype=Windows+10&version=62080079&lang=zh_CN&exportkey=AcbwcKavdvcOZxS4ifWCY58%3D&pass_ticket=tHIK0eOyZUa6tSDAoPtDvjxPEB3JRMp%2BL6Um8IHNfvrkYxNv%2Bvno1s7eLz2YZk7r
 */
@Api("18个Java8处理日期的新花样，肯定没用过！")
public class DateTime {

	/*
	 * Java 8 推出了全新的日期时间API，在教程中我们将通过一些简单的实例来学习如何使用新API。
	 * Java处理日期、日历和时间的方式一直为社区所诟病，将 java.util.Date设定为可变类型，以及SimpleDateFormat的非线程安全使其应用非常受限。
	 * 新API基于ISO标准日历系统，java.time包下的所有类都是不可变类型而且线程安全。
	 */

	@ApiOperation("示例1:Java 8中获取今天的日期")
	private void demo01() {
		LocalDate today = LocalDate.now();
		System.out.println("今天的日期:" + today);
	}

	@ApiOperation("示例2:Java 8中获取年、月、日信息")
	private void demo02() {
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();

		System.out.println("year:" + year);
		System.out.println("month:" + month);
		System.out.println("day:" + day);
	}

	/**
	 * 我们通过静态工厂方法now()非常容易地创建了当天日期，你还可以调用另一个有用的工厂方法LocalDate.of()创建任意日期， 该方法需要传入年、月、日做参数，返回对应的LocalDate实例。这个方法的好处是没再犯老API的设计错误，比如年度起始于1900，月份是从0开 始等等。
	 */
	@ApiOperation("示例3:Java 8中处理特定日期")
	private void demo03() {
		LocalDate date = LocalDate.of(2018, 2, 6);
		System.out.println("自定义日期:" + date);
	}

	@ApiOperation("示例4:Java 8中判断两个日期是否相等")
	private void demo04() {
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = LocalDate.of(2018, 2, 5);

		if (date1.equals(date2)) {
			System.out.println("时间相等");
		} else {
			System.out.println("时间不等");
		}
	}

	/**
	 * 只要当天的日期和生日匹配，无论是哪一年都会打印出祝贺信息。你可以把程序整合进系统时钟，看看生日时是否会受到提醒，或者写一个单元测试来检测代码是否运行正确。
	 */
	@ApiOperation("示例5:Java 8中检查像生日这种周期性事件")
	private void demo05() {
		LocalDate date1 = LocalDate.now();

		LocalDate date2 = LocalDate.of(2018, 2, 6);
		MonthDay birthday = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());
		MonthDay currentMonthDay = MonthDay.from(date1);

		if (currentMonthDay.equals(birthday)) {
			System.out.println("是你的生日");
		} else {
			System.out.println("你的生日还没有到");
		}
	}

	/**
	 * 可以看到当前时间就只包含时间信息，没有日期
	 */
	@ApiOperation("示例6:Java 8中获取当前时间")
	private void demo06() {
		LocalTime time = LocalTime.now();
		System.out.println("获取当前的时间,不含有日期:" + time);
	}

	/**
	 * 通过增加小时、分、秒来计算将来的时间很常见。Java 8除了不变类型和线程安全的好处之外，还提供了更好的plusHours()方法替换add()，并且是兼容的。注意，这些方法返回一个全新的LocalTime实例，由于其不可变性，返回后一定要用变量赋值。
	 */
	@ApiOperation("示例7:Java 8中获取当前时间")
	private void demo07() {
		LocalTime time = LocalTime.now();
		LocalTime newTime = time.plusHours(3);
		System.out.println("三个小时后的时间为:" + newTime);
	}

	/**
	 * 和上个例子计算3小时以后的时间类似，这个例子会计算一周后的日期。LocalDate日期不包含时间信息，它的plus()方法用来增加天、周、月，ChronoUnit类声明了这些时间单位。由于LocalDate也是不变类型，返回后一定要用变量赋值。
	 * 可以看到新日期离当天日期是7天，也就是一周。你可以用同样的方法增加1个月、1年、1小时、1分钟甚至一个世纪，更多选项可以查看Java 8 API中的ChronoUnit类
	 */
	@ApiOperation("示例8:Java 8如何计算一周后的日期")
	private void demo08() {
		LocalDate today = LocalDate.now();
		System.out.println("今天的日期为:" + today);
		LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
		System.out.println("一周后的日期为:" + nextWeek);
	}

	/**
	 * 利用minus()方法计算一年前的日期
	 */
	@ApiOperation("示例9:Java 8计算一年前或一年后的日期")
	private void demo09() {
		LocalDate today = LocalDate.now();

		LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
		System.out.println("一年前的日期 : " + previousYear);

		LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
		System.out.println("一年后的日期:" + nextYear);
	}

	/**
	 * Java 8增加了一个Clock时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。以前用到System.currentTimeInMillis()和TimeZone.getDefault()的地方都可用Clock替换。
	 */
	@ApiOperation("示例10:Java 8的Clock时钟类")
	private void demo10() {
		// Returns the current time based on your system clock and set to UTC.
		Clock clock = Clock.systemUTC();
		System.out.println("Clock : " + clock.millis());

		// Returns time based on system clock zone
		Clock defaultClock = Clock.systemDefaultZone();
		System.out.println("Clock : " + defaultClock.millis());
	}

	/**
	 * 另一个工作中常见的操作就是如何判断给定的一个日期是大于某天还是小于某天？在Java 8中，LocalDate类有两类方法isBefore()和isAfter()用于比较日期。调用isBefore()方法时，如果给定日期小于当前日期则返回true。
	 */
	@ApiOperation("示例11:如何用Java判断日期是早于还是晚于另一个日期")
	private void demo11() {
		LocalDate today = LocalDate.now();

		LocalDate tomorrow = LocalDate.of(2018, 2, 6);
		if (tomorrow.isAfter(today)) {
			System.out.println("之后的日期:" + tomorrow);
		}

		LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
		if (yesterday.isBefore(today)) {
			System.out.println("之前的日期:" + yesterday);
		}
	}

	/**
	 * Java 8不仅分离了日期和时间，也把时区分离出来了。现在有一系列单独的类如ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间。这在Java 8以前都是 GregorianCalendar类来做的。下面这个例子展示了如何把本时区的时间转换成另一个时区的时间。
	 */
	@ApiOperation("示例12:Java 8中处理时区")
	private void demo12() {
		// Date and time with timezone in Java 8
		ZoneId america = ZoneId.of("America/New_York");
		LocalDateTime localtDateAndTime = LocalDateTime.now();
		ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
		System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
	}

	/**
	 * 与 MonthDay检查重复事件的例子相似，YearMonth是另一个组合类，用于表示信用卡到期日、FD到期日、期货期权到期日等。还可以用这个类得到 当月共有多少天，YearMonth实例的lengthOfMonth()方法可以返回当月的天数，在判断2月有28天还是29天时非常有用。
	 */
	@ApiOperation("示例13:如何表示信用卡到期这类固定日期，答案就在YearMonth")
	private void demo13() {
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
		YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
		System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
	}

	@ApiOperation("示例14:如何在Java 8中检查闰年")
	private void demo14() {
		LocalDate today = LocalDate.now();
		if (today.isLeapYear()) {
			System.out.println("This year is Leap year");
		} else {
			System.out.println("2018 is not a Leap year");
		}
	}

	/**
	 * 有一个常见日期操作是计算两个日期之间的天数、周数或月数。在Java 8中可以用java.time.Period类来做计算。
	 */
	@ApiOperation("示例15:计算两个日期之间的天数和月数")
	private void demo15() {
		LocalDate today = LocalDate.now();

		LocalDate java8Release = LocalDate.of(2018, 12, 14);

		Period periodToNextJavaRelease = Period.between(today, java8Release);
		System.out.println("Months left between today and Java 8 release : "
				+ periodToNextJavaRelease.getMonths());
	}

	/**
	 * Instant类有一个静态工厂方法now()会返回当前的时间戳，如下所示：
	 * 时间戳信息里同时包含了日期和时间，这和java.util.Date很像。实际上Instant类确实等同于 Java 8之前的Date类，你可以使用Date类和Instant类各自的转换方法互相转换，例如：Date.from(Instant) 将Instant转换成java.util.Date，Date.toInstant()则是将Date类转换成Instant类。
	 */
	@ApiOperation("示例16:在Java 8中获取当前的时间戳")
	private void demo16() {
		Instant timestamp = Instant.now();
		System.out.println("What is value of this instant " + timestamp.toEpochMilli());
	}

	@ApiOperation("Java 8中如何使用预定义的格式化工具去解析或格式化日期")
	private void demo17() {
		String dayAfterTommorrow = "20180205";
		LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
				DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println(dayAfterTommorrow + "  格式化后的日期为:  " + formatted);
	}

	@ApiOperation("示例18:字符串互转日期类型")
	private void demo19() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		//日期转字符串
		String str = date.format(format1);
		System.out.println("日期转换为字符串:" + str);

		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		//字符串转日期
		LocalDate date2 = LocalDate.parse(str, format2);
		System.out.println("日期类型:" + date2);
	}


	/**
	 * 主函数测试...
	 */
	public static void main(String[] args) {
		new DateTime().demo19();
	}

}
