package com.chengcheng.cases.deep.design.shiPeiQi;

/**
 *  4. 创建实现了MediaPlayer接口的实体类
 */
public class AudioPlayer implements MediaPlayer{

	MediaAdapter mediaAdapter;

	@Override
	public void play(String audioType, String fileName) {

		//播放 mp3 音乐文件的内置支持
		if(audioType.equalsIgnoreCase("mp3")){
			System.out.println("Playing mp3 file. Name: "+ fileName);
		}
		//mediaAdapter 提供了播放其他文件格式的支持
		else if(audioType.equalsIgnoreCase("vlc")
				|| audioType.equalsIgnoreCase("mp4")){
			// 调用 MediaAdapter 给advancedMusicPlayer赋值
			mediaAdapter = new MediaAdapter(audioType);
			// 调用MediaAdapter 类中的play方法(paly方法中处理了advancedMusicPlayer)
			mediaAdapter.play(audioType, fileName);
		}
		else{
			System.out.println("Invalid media. "+
					audioType + " format not supported");
		}
	}
}
