package com.chengcheng.cases.deep.quote;

import io.swagger.annotations.Api;

import java.lang.ref.WeakReference;

@Api("弱引用演示")
public class WeakQuote {

	/*
	 *  用来描述非必须对象的, 但是它的强度比软引用更弱一些.
	 * 被弱引用关联的对象只能生存到下一次垃圾收集发生之前.
	 * 当垃圾收集器工作时, 无论当前内存是否足够,都会回收掉被弱引用关联的对象.
	 * 在jdk1.2之后, 提供了weakReference类来实现弱引用.
	 */

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed.");
	}

	public static void main(String[] args) {
		WeakQuote twr = new WeakQuote();
		WeakReference wr = new WeakReference(twr);

		/**
		 *  此时WeakQuote的一个对象有两个引用指向它:
		 *  1. 一个强引用twr
		 *  2. 一个弱引用sr
		 */

		System.out.println("before gc: " + wr.get());
		twr = null;  // 去掉强引用twr
		System.gc();
		System.out.println("after gc: " + wr.get());
	}

	// 结果如下: 可以看到弱引用被回收了.
	// before gc: com.chengcheng.cases.deep.quote.WeakQuote@626b2d4a
	// finalize method executed.
	// after gc: null

}
