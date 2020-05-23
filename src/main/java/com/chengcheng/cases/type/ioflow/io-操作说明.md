- FileReader => 文件读出字符  => 父类Reader
- FileWriter => 文件写入字符  => 父类Writer
- BufferedReader => 高效缓冲字符输入
- BufferedWriter => 高效缓冲字符输出
--------------
##. 字节流不需要Flush
- FileInputStream => 字节输入流  => 父类InputStream
- FileOutputStream => 字节输出流  => 父类OutputStream
- BufferdInputStream => 高效缓冲字节输入流
- BufferdOutputStream => 高效缓冲字节输出流
--------------
## 字符流和字节流的转换桥梁
- OutPutStreamWriter => 字符流通向字节流的桥梁 Writer->Stream(Writer操作Stream)
- InputStreamReader  => 字符流通向字节流的桥梁 Reader->Stream(Reader操作Stream)
- PrintWriter  => 字符打印流