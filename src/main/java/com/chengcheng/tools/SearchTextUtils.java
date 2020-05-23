package com.chengcheng.tools;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Api("搜索文件内容小工具")
@RestController
public class SearchTextUtils {

	/* 开发环境 */
	private static long mount = 0L;
	private static final String SEARCH_ADDRESS = "http://127.0.0.1/download/search/Search-";
	private static final String PATH_BEFORE = "./src/main/resources/templates/download/search/Search-";
	private static final String PATH_TYPE = ".html";
	private static String PATH = "./src/main/resources/templates/download/search/";
	private static boolean flag = false;

	@ApiOperation("搜索入口")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String Search(@ApiParam("搜索路径") @RequestParam("path") String path,
	                     @ApiParam("关键字, 多关键字用空格隔开") @RequestParam("array") String array,
	                     @ApiParam("文件类型") @RequestParam("type") String type) {

		double start = System.currentTimeMillis();
		long fileStamp = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long fileTime = Long.parseLong(sdf.format(fileStamp));

		BufferedWriter writer = null;
		try {
			File dirFile = new File(PATH);
			if (!dirFile.exists()) {
				Boolean bol = dirFile.mkdirs();
			}
			writer = new BufferedWriter(new FileWriter(PATH_BEFORE + fileTime + PATH_TYPE, true));
			writer.write("<meta charset=\"utf-8\">");
			writer.write("<title>搜索结果</title>");
			writer.flush();
			String[] arr_string = array.split(" ");
			String[] arr_type = type.split(" ");
			File file = new File(path);
			for (String s : arr_string) {
				findFile(file, s, fileTime, arr_type);
				print(s, fileTime);
			}
			double end = System.currentTimeMillis();
			double time = end - start;
			printOut(time, null, 0, null, "Search");
			writer.write("搜索共耗时: " + time / 1000 + " 秒!\n<br>");
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return SEARCH_ADDRESS + fileTime + PATH_TYPE;
	}

	@ApiOperation("判断文件是否存在及类型")
	private static boolean isTrueFile(@ApiParam("文件") File file,
	                                  @ApiParam("文件类型") String[] arr_type) {
		if (!file.exists() || !file.canRead() || file.getName().startsWith(".") || file.getName().endsWith(".")) {
			return false;
		}
		for (String typ : arr_type) {
			if (file.getName().endsWith(typ)) {
				return true;
			}
		}

		return false;
	}

	@ApiOperation("文件夹递归操作")
	private static void findFile(@ApiParam("文件") File file,
	                             @ApiParam("单个关键字") String word,
	                             @ApiParam("文件名后拼") long fileTime,
	                             @ApiParam("文件类型数组") String[] arr_type) {
		File[] listFiles = file.listFiles();
		if (listFiles != null) {  // 得到一个File数组，默认按文件最后修改日期排序
			for (File listFile : listFiles) {
				if (listFile.isDirectory()) {
					findFile(listFile, word, fileTime, arr_type);  // 递归
				} else if (isTrueFile(listFile, arr_type)) {
					search(listFile, word, fileTime);  // 文件搜索
				}
			}
		}
	}

	@ApiOperation("文件搜索")
	private static void search(@ApiParam("文件") File file,
	                           @ApiParam("单个关键字") String word,
	                           @ApiParam("文件名后拼") long fileTime) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			long j = 0, k = 0, ch = 0;
			StringBuilder str = null;
			String line = null;

			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(PATH_BEFORE + fileTime + PATH_TYPE, true));
			if (reader.readLine() != null) {
				while ((line = reader.readLine()) != null) {
					str = (str == null ? new StringBuilder("null") : str).append(line);
				}
			}
			if (str != null) {
				while (str.indexOf(word, Math.toIntExact(j)) != -1) {
					k++;
					j = str.indexOf(word, Math.toIntExact(j)) + 1;  // 返回第一次出现的指定字符串的索引
				}
			}
			if (k > 0) {
				printOut(0, file, k, word, "search");
				writer.write("目录: '" + file.getAbsolutePath() + "' 下有 '" + k + "' 个关键字 '" + word + "' \n<br>");
				writer.flush();
				mount++;
			}
		} catch (Exception e) {
			printOut(0, null, 0, word, "refuse");  // 存在磁盘拒绝访问等异常忽略, 不终止程序..
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@ApiOperation("查找结果总计")
	private static void print(@ApiParam("单个关键字") String word,
	                          @ApiParam("文件名后拼") long fileTime) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(PATH_BEFORE + fileTime + PATH_TYPE, true));
			if (mount != 0) {
				printOut(0, null, 0, word, "print01");
				writer.write("结果: 共搜索到 " + mount + " 个文件包含关键字" + word + "!\n<br>");
				writer.write("------------------------\n<br>");
				writer.flush();
				mount = 0;
			} else {
				printOut(0, null, 0, word, "print02");
				writer.write("结果: 没有找到对应关键字 '" + word + "' 的文件!\n<br>");
				writer.write("------------------------\n<br>");
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@ApiOperation("控制台打印")
	private static void printOut(@ApiParam("耗时") double time,
	                             @ApiParam("文件") File file,
	                             @ApiParam("关键字数量") long k,
	                             @ApiParam("关键字") String word,
	                             @ApiParam("分支判断") String flag) {
		if ("Search".equals(flag)) {
			System.out.println("搜索共耗时: " + time / 1000 + " 秒!");
		}
		if ("search".equals(flag)) {
			System.out.println("目录: '" + file.getAbsolutePath() + "' 下有 '" + k + "' 个关键字 '" + word + "'");
		}
		if ("print01".equals(flag)) {
			System.out.println("结果: 共搜索到 " + mount + " 个文件包含关键字" + word + "!");
			System.out.println("------------------------");
		}
		if ("print02".equals(flag)) {
			System.out.println("结果: 没有找到对应关键字 '" + word + "' 的文件!");
			System.out.println("------------------------");
		}
		if ("refuse".equals(flag)) {
			System.out.println("磁盘拒绝访问等等等异常!");
		}
	}

	/*  ---------------------------------------- */

	@ApiOperation("删除指定目录下的文件")
	@Scheduled(cron = "00 30 21 * * ?")
	@RequestMapping(value = "searchDel", method = RequestMethod.GET)
	private static void deleteDirectory() {

		if (!PATH.endsWith(File.separator)) {  // 如果Path不以文件分隔符结尾，自动添加文件分隔符
			PATH = PATH + File.separator;
		}
		File dirFile = new File(PATH);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return;
		}
		flag = true;
		File[] files = dirFile.listFiles();
		if (files != null) {
			for (File file1 : files) {  // 循环遍历删除文件夹下的所有文件(包括子目录)
				if (file1.isFile()) {
					flag = deleteFile(file1.getAbsolutePath());
					System.out.println(file1.getAbsolutePath() + " 删除成功");
					if (!flag) {
						break;
					}
				} else {
					deleteDirectory();  // 递归删除子目录
					if (!flag) {
						break;
					}
				}
			}
		}

	}

	@ApiParam("删除单个文件")
	private static boolean deleteFile(@ApiParam("文件路径") String filePath) {
		flag = false;
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

}
