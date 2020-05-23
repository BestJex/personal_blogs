## 此目录下编写关于Nginx调试和测试的Case

#### Nginx的信息

#### Nginx访问地址:
- 127.0.0.1:80

#### Nginx配置Zuul集群测试
http://127.0.0.1/micro-client01/getTest

#### Nginx的操作:
1. cd D:\Nginx\nginx-1.15.3
2. start nginx.exe 或 start nginx          // 启动Nginx
3. ./nginx.exe -s stop                     // 不完整退出
4. ./nginx.exe -s quit                     // 完整退出
5. ./nginx.exe -s reload                   // 重新启动
6. tasklist /fi "imagename eq nginx.exe"   // 检测nginx是否成功启动
7. taskkill /pid  进程号 -t -f              // 强行杀死Nginx端口