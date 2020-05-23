package com.chengcheng.cases.type.exception.exceptentity;

import lombok.Data;

/**
 *  统一异常处理 3-2
 *  参考: https://mp.weixin.qq.com/s?__biz=MzAxNjk4ODE4OQ==&mid=2247488573&idx=2&sn=722ff10ffdcac6d4a2c0e9ed7367e426&chksm=9bed354fac9abc593ef3e6a91b168b2e74b175f076e32a03ea8b1b81015d4247687134428de6&scene=21#wechat_redirect
 */
@Data
public class CMSException extends RuntimeException {
	private Integer code;

	public CMSException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public CMSException(ResultCodeEnum resultCodeEnum) {
		super(resultCodeEnum.getMessage());
		this.code = resultCodeEnum.getCode();
	}

	@Override
	public String toString() {
		return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
	}
}
