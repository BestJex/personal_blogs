package com.chengcheng.cases.deep.design.shiPeiQi;

/**
 *  2.1 创建实现了AdvancedMediaPlayer接口的实体类
 */
public class VlcPlayer implements AdvancedMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file. Name: " + fileName);
	}

	@Override
	public void playMp4(String fileName) {
		// 什么也不做
	}
}
