package com.chengcheng.cases.current.lock;

import io.swagger.annotations.Api;
import sun.misc.PostVMInitHook;

import javax.jws.soap.SOAPBinding;
import java.util.Random;

@Api("读写锁操作 - 2")
public class UseRwLockTest {

	private static final int readWriteRatio = 10;  // 读写线程的比例
	private static final int mintReadCount = 3;  // 最少线程数
	//static CountDownLatch latch = new CountDownLatch(1);

	/* 读操作 */
	private static class GetThread implements Runnable {
		private UseRwLock.UseRWLock useRwLock;  // 调用内部类的方式

		GetThread(UseRwLock.UseRWLock useRwLock) {
			this.useRwLock = useRwLock;
		}

		@Override
		public void run() {
/*			try {
				latch.await();//让读写线程同时运行
			} catch (InterruptedException e) {
			}*/
			long start = System.currentTimeMillis();
			for (int i = 0; i < 100; i++) {
				try {
					useRwLock.getNum();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "读取耗时：" + (System.currentTimeMillis() - start) + "ms");
		}
	}

	/* 写操作 */
	private static class SetThread implements Runnable {
		private UseRwLock.UseRWLock useRwLock;  // 调用内部类的方式

		SetThread(UseRwLock.UseRWLock useRwLock) {
			this.useRwLock = useRwLock;
		}

		@Override
		public void run() {
/*			try {
				latch.await();//让读写线程同时运行
			} catch (InterruptedException e) {
			}*/
			long start = System.currentTimeMillis();
			Random r = new Random();
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(50);
					useRwLock.setNum(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "写入耗时：" + (System.currentTimeMillis() - start) + "ms---------");
		}
	}

	/* 主函数测试 */
	public static void main(String[] args) throws InterruptedException {
		UseRwLock.GoodsInfo goodsInfo = new UseRwLock.GoodsInfo("CPU", 100000, 10000);
		UseRwLock.UseRWLock useRwLock = new UseRwLock.UseRWLock(goodsInfo);
		for (int i = 0; i < mintReadCount; i++) {
			Thread setT = new Thread(new SetThread(useRwLock));
			for (int j = 0; j < readWriteRatio; j++) {
				Thread getT = new Thread(new GetThread(useRwLock));
				getT.start();
			}
			Thread.sleep(100);
			setT.start();
		}
		//latch.countDown();
	}


}
