package com.chengcheng.cases.current.lock.wechatlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 我们以 java.util.concurrent 中的AtomicInteger 为例，看一下在不用锁的情况下是如何保证线程安全的
 * 经测试可得，不管循环多少次最后的结果都是0，也就是多线程并行的情况下，使用 AtomicInteger 可以保证线程安全性。incrementAndGet 和 decrementAndGet 都是原子性操作。
 */

class AtomicCounter {

	private AtomicInteger integer = new AtomicInteger();

	public AtomicInteger getInteger() {
		return integer;
	}

	public void setInteger(AtomicInteger integer) {
		this.integer = integer;
	}

	public void increment(){
		integer.incrementAndGet();
	}

	public void decrement(){
		integer.decrementAndGet();
	}

}

class AtomicProducer extends Thread{

	private AtomicCounter atomicCounter;

	public AtomicProducer(AtomicCounter atomicCounter){
		this.atomicCounter = atomicCounter;
	}

	@Override
	public void run() {
		for(int j = 0; j < AtomicTest.LOOP; j++) {
			System.out.println("producer : " + atomicCounter.getInteger());
			atomicCounter.increment();
		}
	}
}

class AtomicConsumer extends Thread{

	private AtomicCounter atomicCounter;

	public AtomicConsumer(AtomicCounter atomicCounter){
		this.atomicCounter = atomicCounter;
	}

	@Override
	public void run() {
		for(int j = 0; j < AtomicTest.LOOP; j++) {
			System.out.println("consumer : " + atomicCounter.getInteger());
			atomicCounter.decrement();
		}
	}
}

class AtomicTest {

	final static int LOOP = 10000;

	public static void main(String[] args) throws InterruptedException {

		AtomicCounter counter = new AtomicCounter();
		AtomicProducer producer = new AtomicProducer(counter);
		AtomicConsumer consumer = new AtomicConsumer(counter);

		producer.start();
		consumer.start();

		producer.join();
		consumer.join();

		System.out.println(counter.getInteger());

	}
}