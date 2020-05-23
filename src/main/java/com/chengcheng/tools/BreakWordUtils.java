package com.chengcheng.tools;

import io.swagger.annotations.Api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("断词器 - 未完成")
public class BreakWordUtils {

	private Map<Character, Object> dictionary;

	public BreakWordUtils(String dictionaryFilePath) throws IOException {
		this.loadDictionary(dictionaryFilePath);  // 从文件加载字典到treeMap
	}

	private void loadDictionary(String dictionaryFilePath) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFilePath)));
		String line = null;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}

			char c;
			Map<Character, Object> child = this.dictionary;

			// 组成以这个字符开头的词的树
			for (int i = 0; i < line.length(); i ++) {
				c = line.charAt(i);
				Map<Character, Object> ccMap = (Map<Character, Object>) child.get(c);
				if (ccMap == null) {
					ccMap = new HashMap<Character, Object>();
					child.put(c,ccMap);
				}
				child = ccMap;
			}
		}
	}

	public List<String> participle(String text) {
		if (text == null) {
			return null;
		}

		text = text.trim();
		if (text.length() == 0) {
			return null;
		}

		List<String> tokens = new ArrayList<>();
		char c;
		for (int i = 0; i < text.length();){
			StringBuilder token = new StringBuilder();
			Map<Character, Object> child = this.dictionary;
			boolean matchToken = false;
			for (int j = i; j < text.length(); j ++) {
				c = text.charAt(j);
				token.append(c);
				Map<Character, Object> ccMap = (Map<Character, Object>) child.get(c);
				if (ccMap == null) {
					break;
				}
				if (ccMap.isEmpty()) {
					matchToken = true;
					i = j + 1;
					break;
				}
				child = ccMap;
			}

			if (matchToken) {
				tokens.add(token.toString());
			} else {
				tokens.add("" + text.charAt(i));
				i++;
			}
		}
		return  tokens;
	}

	public static void main(String[] args) throws IOException {
		BreakWordUtils tk = new BreakWordUtils(BreakWordUtils.class.getResource("/dictionary.txt").getPath());
		List<String> tokens = tk.participle("张宜成");
		for (String s : tokens) {
			System.out.println(s);
		}
	}


}