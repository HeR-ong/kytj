1.项目使用技术栈
后端：springboot+mybatis
前端：vue+elementUI
数据库：mysql
爬虫：python3.7.2

2.提供的资源有源码包和可执行包，使用方式如下：
 
①先在本地安装java环境（jdk1.8以上版本），具体安装方法自行百度
②后端服务，找到demo-0.0.1-SNAPSHOT.jar 在命令行终端执行 nohup java -jar demo-0.0.1-SNAPSHOT.jar &
   至此后端服务启动完毕
③前端应用，在可运行文件夹下有个dist文件夹，这是前端打好包之后的资源文件，将该文件夹的目录地址配置在nginx（需要自行百度安装）服务器上
   配置如下：
server {
        listen       8099;
        server_name  localhost;

        location / {
            root   /home/hr/deploy/income/pc-web;（此目录改成自己dist所在目录）
            index  index.html index.htm;
        }
        location /api {
            proxy_pass http://localhost:8070/demo/;
        }
    }

配置完之后重启nginx服务，通过localhost:8099就可以访问前端啦
 

④源码包里的源码有兴趣可以自行阅读(运行前端源码需要安装node.js的环境，后端代码需要安装java环境，maven环境等)
⑤目前打好的后端服务包连的数据库是我服务器上建好的数据库，如果你不想自建数据库，可以继续使用我的，但我建议你自己本地安装一个mysql
   创建一个叫springboot的数据库，然后在后端源码包里有一个sql文件夹，执行该文件夹下的脚本，创建项目需要的表结构即可，然后将源码中配置的
数据库地址改成你自己建的数据库地址，用我的服务器上的数据库有风险哦，随时可能挂掉（服务器资源有限，笔者工作忙，没时间维护）
，再次重申，为了答辩顺利，建议自己安装mysql数据库，网上教程很多，我就不赘述了
⑥为方便学生先熟悉系统，我已将系统部署与个人服务器，访问地址：http://39.108.239.129:8087/#/login（服务器资源有限，第一次访问较慢，本地址笔者后续不再维护，
不建议学生直接用此链接做毕业答辩）
⑦系统笔者初始化了几个账号，分别是不同权限的账户：
系统管理员：admin/123456
后续可自己注册账户
⑧关于爬虫
1>.现在本地安装pathon环境（建议3.7.2版本）
2>.建议安装pyCharm编辑器（专业的python开发神器，插件比较强大，也比较易于使用）
3>.由于爬虫程序直接将爬到的数据存储到了mysql数据库，所以在执行python脚本之前需要通过pyCharm安装pyMysql插件，如下所示：
 
4>.运行python脚本之前请调整mysql配置，现在配置的是笔者服务器上的mysql数据库，请在开启爬虫之前修改为自己的mysql数据库
 
 
![image](https://user-images.githubusercontent.com/11371693/136687648-a1015ccf-df6a-4002-8a41-6d67b87e8049.png)
