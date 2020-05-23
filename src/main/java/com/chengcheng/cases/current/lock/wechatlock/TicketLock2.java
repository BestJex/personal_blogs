package com.chengcheng.cases.current.lock.wechatlock;

import io.swagger.annotations.Api;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 在计算机科学领域中，TicketLock 是一种同步机制或锁定算法，它是一种自旋锁，它使用ticket 来控制线程执行顺序。
 * 就像票据队列管理系统一样。面包店或者服务机构(例如银行)都会使用这种方式来为每个先到达的顾客记录其到达的顺序，而不用每次都进行排队。通常，这种地点都会有一个分配器(叫号器，挂号器等等都行)，先到的人需要在这个机器上取出自己现在排队的号码，这个号码是按照自增的顺序进行的，旁边还会有一个标牌显示的是正在服务的标志，这通常是代表目前正在服务的队列号，当前的号码完成服务后，标志牌会显示下一个号码可以去服务了。
 * 缺点: TicketLock 虽然解决了公平性的问题，但是多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量queueNum ，每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。
 * 为了解决这个问题，MCSLock 和 CLHLock 应运而生。
 */
@Api("TicketLock")
public class TicketLock2 {

	// 队列票据(当前排队号码)
	private AtomicInteger queueNum = new AtomicInteger();

	// 出队票据(当前需等待号码)
	private AtomicInteger dueueNum = new AtomicInteger();

	private ThreadLocal<Integer> ticketLocal = new ThreadLocal<>();

	public void lock(){
		int currentTicketNum = dueueNum.incrementAndGet();

		// 获取锁的时候，将当前线程的排队号保存起来
		ticketLocal.set(currentTicketNum);
		while (currentTicketNum != queueNum.get()){
			// doSomething...
		}
	}

	// 释放锁：从排队缓冲池中取
	public void unLock(){
		Integer currentTicket = ticketLocal.get();
		queueNum.compareAndSet(currentTicket,currentTicket + 1);
	}
}
