package com.chengcheng.cases.deep.quote;

import io.swagger.annotations.Api;

// 参考地址: https://www.jianshu.com/p/e5364c05cc80
@Api("强引用演示")
public class StrongQuote {

	/*
	 *   强引用:
	 *   在程序代码中普遍存在, Object obj = new Object() 这类的引用, 只要强引用还存在, 垃圾收集器永远不会回收掉被引用的对象
	 */

	/*
	 *    例子1: 强引用
	 *    先解释finalize()方法: 每
	 *      - 一个对象的fianlize()[从Object集成的方法]都只会被系统子自动调用一次, 如果对象面临下一次回收, 它的finalize()不会被再次执行
	 */

	public static StrongQuote SAVE_HOOK = null;

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed");
		StrongQuote.SAVE_HOOK = this;
	}

	public static void helpGC() throws Throwable {
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			System.out.println("yes, i am still alive.");
		} else {
			System.out.println("no, i am dead.");
		}
	}

	public static void main(String[] args) throws Throwable {
		SAVE_HOOK = new StrongQuote();
		helpGC();  // 第一次执行了finalize自救
		helpGC();  // finalize 执行过了一次便不再执行
	}

	// 结果:
	// finalize method executed
	// yes, i am still alive.
	// no, i am dead.

	/*
	 *  结果: 在这里StrongQuote对象有一个强引用SAVE_HOOK指向它, 如果不设置为null,垃圾祸首器将不会回收该对象.
	 *        主动设置为null, 可以帮助垃圾收集器回收被引用的对象.
	 */

}
