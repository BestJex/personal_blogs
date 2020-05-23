package com.chengcheng.cases.deep.quote;

import io.swagger.annotations.Api;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

@Api("WaekQuote之后 - 弱引用对象被回收, 弱引用本身问题")
public class TestReferenceQueue {

	/*
	 *  接下来就会有个问题, 那弱引用对应的对象被回收了, 那弱引用本身该怎么办?
	 *  因为它本身也对应了一个WeakReference的对象.
	 */

	/*
	 *  这个Reference是专门用来存放引用的, 当软引用, 弱引用, 虚引用
	 *  对应的那个对象被回收后的同时, 该引用会自动加入到你所定义的ReferenceQueue中.
	 */

	public static void main(String[] args) {
		ReferenceQueue rq = new ReferenceQueue();
		WeakReference wr = new WeakReference(new TestReferenceQueue(), rq);
		System.out.println("弱引用对应的对象:" + wr.get() + ", 弱引用本身:" + wr);
		System.out.println("队列中对象:" + rq.poll());
		/**
		 * TestReferenceQueue中的对象只有一个引用 就是wr弱引用
		 * 因此直接调用gc就可以
		 */
		System.gc();
		System.out.println("弱引用对应的对象:" + wr.get() + ", 弱引用本身:" + wr);
		System.out.println("队列中对象:" + rq.poll());
	}

	// 结果如下:
	// 弱引用对应的对象:com.chengcheng.cases.deep.quote.TestReferenceQueue@626b2d4a, 弱引用本身:java.lang.ref.WeakReference@5e91993f
	// 队列中对象:null
	// 弱引用对应的对象:null, 弱引用本身:java.lang.ref.WeakReference@5e91993f
	// 队列中对象:java.lang.ref.WeakReference@5e91993f

	// 在弱引用对应的对象被回收后该弱引用对象本身也进入到了ReferenceQueue中.
	// ReferenceQueue清除失去了弱引用对象的弱引用本身, 软引用, 虚引用也是如此.

}
