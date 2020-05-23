package com.chengcheng.cases.current.queue;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.concurrent.ArrayBlockingQueue;


@Api("ArrayBlockingQueue的用法")
public class ArrayBlockingQue {

	@ApiOperation("主函数测试类")
	public static void main(String[] args) throws Exception {
//		insertBlocking();
//		fetchBlocking();

		ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(10);
		testProducerConsumer(abq);
	}

	@ApiOperation("此方法展示了ArrayBlockingQueue的插入阻塞特性：如果队列已经满了，那么插入的操作就会被阻塞，程序执行就会被迫暂停")
	private static void insertBlocking() throws InterruptedException {
		ArrayBlockingQueue<String> names = new ArrayBlockingQueue<>(1);
		System.out.println("开始: 添加到队列");
		names.put("a");
		System.out.println("堵塞: 队列已满发生堵塞..");
		names.put("b");  // 从这一句开始后面的就不会被执行了
		System.out.println("不执行: 此处不再执行..");
	}

	@ApiOperation("此方法展示了 ArrayBlockingQueue 的取出阻塞特性 ：如果队列为空，那么取的操作就会被阻塞，程序执行就会报错")
	private static void fetchBlocking() throws InterruptedException {
		ArrayBlockingQueue<String> names = new ArrayBlockingQueue<String>(1);
		names.put("a");
		names.remove();
		System.out.println("程序: 到此不会报错..");
		names.remove();
		System.out.println("程序: 不会执行此处,上一句会报错..");
		names.put("b");
		System.out.println("程序执行到此...");
	}

	/**
	 * 此方法用来测试生产者和消费者, 为了让程序在获取不到元素时不报错有两种方式：
	 * 1.让生产者的生产速度大于消费者的消费速度
	 * 2.在消费者获取资源出错时让消费者线程暂停一段时间，不输出错误。
	 */
	private static void testProducerConsumer(ArrayBlockingQueue<String> abq) {
		Thread tConsumer = new Consumer(abq);
		Thread tProducer = new Producer(abq);
		tConsumer.start();
		tProducer.start();
	}

}

@Api("定义消费者")
class Consumer extends Thread {
	private ArrayBlockingQueue<String> abq = null;

	Consumer(ArrayBlockingQueue<String> abq) {
		super();
		this.abq = abq;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
				String msg = abq.remove();
//				String msg = abq.take();  // 检索并删除此队列的头，如有必要，等待元素可用
//				String msg = abq.poll();  // 检索并删除此队列的头，如果此队列为空，则返回NULL, 可指定等待时间
				System.out.println("取数据：====" + msg + "\t剩余数据量：" + abq.size());
			} catch (Exception e) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

@Api("定义生产者")
class Producer extends Thread {
	private ArrayBlockingQueue<String> abq = null;

	Producer(ArrayBlockingQueue<String> abq) {
		this.abq = abq;
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			try {
				Thread.sleep(1000);
				abq.put("" + i);
				System.out.println("存放数据：====" + i + "\t剩余数据量：" + abq.size());
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

