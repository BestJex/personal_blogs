package com.chengcheng.cases.deep.quote;

import io.swagger.annotations.Api;

import java.lang.ref.SoftReference;

@Api("软引用演示")
public class SoftQuote {

	/*
	 *  下面的例子中SoftQuote的一个对象由一个强引用tsr和一个软引用sr共同指向,
	 *   然后tsr = null 去除强引用, 此时还剩下一个软引用指向该对象, 主动调用gc方法,
	 *   看看会发生什么? 按照软引用定义来看, 在北村还足够的时候不会回收软引用.
	 */

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed.");
	}

	public static void main(String[] args) {
		SoftQuote tsr = new SoftQuote();
		System.out.println("tsr instance: " + tsr);
		SoftReference sr = new SoftReference(tsr);  // 弱引用
		/*
		 *  此时SoftQuote的一个对象有两个引用指向它:
		 *  1. 一个强引用tsr
		 *  2. 一个软引用sr
		 */
		System.out.println("before gc: " + sr.get());
		tsr = null;  // 此使只有一个软引用sr指向该对象
		System.gc();  // 启动垃圾回收器
		System.out.println("after gc: " + sr.get());
	}

	// 运行结果:
	// tsr instance: com.chengcheng.cases.deep.quote.SoftQuote@626b2d4a
	// before gc: com.chengcheng.cases.deep.quote.SoftQuote@626b2d4a
	// after gc: com.chengcheng.cases.deep.quote.SoftQuote@626b2d4a

	/*
	 *  结果如下: 可以看到只存在一个软引用指向该对象时, 即使主动调用System.gc()方法也没有回收该对象的内存空间.
	 */

}
