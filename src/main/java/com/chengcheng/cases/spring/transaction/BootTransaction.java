package com.chengcheng.cases.spring.transaction;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Api("事务")
@Service
public class BootTransaction {

	// 参考: https://blog.csdn.net/justry_deng/article/details/80828180

	@Transactional(rollbackFor = {Exception.class})
	public void save() {
		try {
			// 业务代码 .....
//			int i =  1/0;  // 异常代码
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发生了异常, 马上回滚...");
		}
	}

	public static void main(String[] args) {
		BootTransaction bootTransaction = new BootTransaction();
		bootTransaction.save();
	}


}
