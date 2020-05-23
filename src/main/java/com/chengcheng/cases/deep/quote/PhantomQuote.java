package com.chengcheng.cases.deep.quote;

import io.swagger.annotations.Api;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

@Api("虚引用演示")
public class PhantomQuote {

	/*
	 *  虚引用是最弱的一终引用关系.
	 *  一个对象事都有虚引用的存在, 完全不会对其生存时间构成影响, 也无法通过虚引用来取得一个对象实例.
	 *  为一个对象设置虚引用关联的唯一目的就是希望能在这个对象被手机器回收时收到一个系统通知.
	 *  在JDK1.2之后, 提供了 PhantomReference 类来实现虚引用.
	 */

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed");
	}

	public static void main(String[] args) {

		ReferenceQueue rq = new ReferenceQueue();
		WeakQuote twr = new WeakQuote();
		PhantomReference pr = new PhantomReference(twr, rq);
		System.out.println("before gc: " + pr.get() + ", " + rq.poll());
		twr = null;  // 去掉强引用twr
		System.gc();
		System.out.println("after gc: " + pr.get() + ", " + rq.poll());
	}

	// before gc: null, null
	// after gc: null, null
	// finalize method executed.


}
