# A-Distributed-Web-System  
## Introdution
# How to Use
## Step One 
### Requirement
* JDK>=1.8
* Zookeeper
* Mysql
* VUE
## Step Two 
### 实现服务接口 dubbo-interface
#### 项目结构
<img width="350" height="350" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/1.jpg"/><br>
#### 1. dubbo-interface 项目创建
 File->New->Module... ,然后选择 Maven类型的项目。
#### 2. 创建接口类
<pre>
package top.snailclimb.service; <br>
      public interface HelloService{<br>       
          public  String sayHello(String name);
      }
</pre>
#### 3. 将项目打成 jar 包供其他项目使用
右键Run as->Maven clean，然后Run as->Maven install，这样 jar 包就打好在target目录下了。<br><br>
<img width="700" height="500" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/3.png"/><br>
<img width="700" height="500" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/4.jpg"/><br>
## Step Three
### 实现服务提供者 dubbo-provider
#### 项目结构
<img width="300" height="450" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/5.jpg"/><br>
#### 1. dubbo-provider 项目创建
创建一个 SpringBoot 项目，注意勾选上 `web `,`mysql`模块。创建过程可参考:<br>
[https://my.oschina.net/u/3771868/blog/1839898](https://my.oschina.net/u/3771868/blog/1839898)<br>
#### 2. pom 文件引入相关依赖
需要引入 dubbo 、zookeeper以及接口的相关依赖 jar 包。注意将dubbo-interface 项目的 dependency 依赖的 groupId 、artifactId以及version改成自己的。dubbo 整合spring boot 的 jar 包在这里找dubbo-spring-boot-starter,zookeeper 的 jar包在 Maven 仓库 搜索 zkclient 即可找到。
 ```
 <dependency>
        <groupId>cn</groupId>
        <artifactId>dubbo-interface</artifactId>
        <version>0.0.1-SNAPSHOT</version>
</dependency>
<!--引入dubbo的依赖-->
<dependency>
        <groupId>com.alibaba.spring.boot</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <version>2.0.0</version>
</dependency>
<!-- 引入zookeeper的依赖 -->
<dependency>
        <groupId>com.101tec</groupId>
        <artifactId>zkclient</artifactId>
        <version>0.10</version>
</dependency>
```
#### 3. 在 application.properties 配置文件中配置 dubbo 相关信息
<pre>
#配置端口
server.port=8333<br>
spring.dubbo.application.name=dubbo-provider
spring.dubbo.application.registry=zookeeper://你的ip地址:2181
</pre>
#### 4. 实现接口
<pre>
package top.snailclimb.service.impl;<br>
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.snailclimb.service.HelloService;<br>
@Component
@Service
public class HelloServiceImpl implements HelloService {
       @Override
       public String sayHello(String name) {
              return "Hello " + name;
       }
}
</pre>
#### 5. 服务提供者启动类编写
注意：不要忘记加上 `@EnableDubboConfiguration `注解开启Dubbo 的自动配置。
<pre>
package top.snailclimb;<br>
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;<br>
@SpringBootApplication
// 开启dubbo的自动配置
@EnableDubboConfiguration
public class DubboProviderApplication {
     public static void main(String[] args) {
          SpringApplication.run(DubboProviderApplication.class, args);
     }
}
</pre>
## Step Four
### 实现服务消费者 dubbo-consumer
#### 项目结构：
<img width="350" height="400" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/10.jpg"/><br>
#### 1、2、3步同Step Three，注意`application properties`中端口应与服务提供者不一致。
<pre>
#配置端口
server.port=8330<br>
spring.dubbo.application.name=dubbo-consumer
spring.dubbo.application.registry=zookeeper://你的ip地址:2181
</pre>
#### 4. 编写一个简单 Controller 调用远程服务
<pre>
package top.snailclimb.dubboconsumer;<br>
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.snailclimb.service.HelloService;<br>
@RestController
public class HelloController {
          @Reference
          private HelloService helloService;

          @RequestMapping("/hello")
          public String hello() {
                 String hello = helloService.sayHello("SpringBoot!");
                 System.out.println(helloService.sayHello("SpringBoot!"));
                 return hello;
          }
}
</pre>
#### 5. 服务消费者启动类编写
<pre>
package top.snailclimb.dubboconsumer;<br>
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;<br>
@SpringBootApplication
@EnableDubboConfiguration
public class DubboConsumerApplication {<br>
           public static void main(String[] args) {<br>
                  SpringApplication.run(DubboConsumerApplication.class, args);
           }
}
</pre>
#### 6. 测试效果
先启动服务提供者DubboProviderApplication.Java，再启动服务消费者DubboConsumerApplication.Java,浏览器访问 [http://localhost:8330/hello](http://localhost:8330/hello) 页面返回 Hello SpringBoot！控制台输出 Hello SpringBoot！使用SpringBoot+Dubbo 搭建第一个简单的分布式服务实验成功。
## Step Five
### SpringBoot + JDBC连接数据库
SpringBoot + JDBC连接数据库可参考[https://www.bilibili.com/video/av75196742?p=2](https://www.bilibili.com/video/av75196742?p=2)<br>
#### 项目结构
<img width="340" height="400" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/14.jpg"/><br>
#### 1. 在mysql中的test库中建立user表,并插入数据，为后续做好准备
<img width="550" height="400" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/13.png"/><br>
#### 2. 在pom.xml中添加JDBC与Mysql依赖
```
<!--JDBC-->
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency><br>		
<dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
</dependency>
```
#### 3. 添加配置文件配置数据库和其他参数
在resource文件夹下application.properties配置文件中输入数据库参数，serverTimezone时区等不可省略。<br>
<pre>
spring.datasource.url=jdbc:mysql://localhost:3306/你的数据库名?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=你的password
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.max-idle=10
spring.datasource.max-wait=10000
spring.datasource.min-idle=5
spring.datasource.initial-size=5
</pre>
#### 4. 新建测试类连接数据库
新建一个JdbcController类连接数据库。<br>
<pre>
package cn.snailclimb.service.controller;<br>
import java.util.List;
import java.util.Map;<br>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;<br>
@RestController
public class JdbcController {<br>
	   @Autowired
	   JdbcTemplate jdbcTemplate;<br>	
   	   @GetMapping("/userList")
	   public List < Map< String,Object > > userList(){
		       String sql = "select * from user";
		       List< Map< String,Object>> list_maps = jdbcTemplate.queryForList(sql);
		       return list_maps;
	   }
}
</pre>
#### 5. 测试效果
启动服务提供者DubboProviderApplication.Java，浏览器访问 [http://localhost:8333/userList](http://localhost:8333/userList)，连接数据库成功。<br>
<img width="700" height="120" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/15.png"/><br>
在HelloServiceImpl中使用JDBC连接数据库<br>
<pre>
package cn.snailclimb.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import cn.snailclimb.service.HelloService;

@Component
@Service
public class HelloServiceImpl implements HelloService {	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    	@Override
	public List<Map<String,Object>> userList(){
		String sql = "select * from user";
		List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql);
		return list_maps;
	}
}
</pre>
启动服务消费者DubboConsumerApplication.Java,浏览器访问 [http://localhost:8330/hello](http://localhost:8330/hello)，获取数据成功。
<img width="700" height="120" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/16.jpg"/><br>
## Step Six
### 构建Web前端
#### 1. 使用VUE搭建一个电影购票系统，项目结构:
<img width="350" height="400" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/17.png"/><br>
#### 2. 配置代理路径
在index.js的dev下设置代理路径
<pre>
    proxyTable: {
    	'/api': {
    	  	target: 'http://localhost:8330',//后台Springboot项目的请求网址    
    	  	changeOrigin: true,    
    	  	pathRewrite: {      
    	  		'^/api': ''
    	  	}
    	}
</pre>
#### 3. 项目展示
<img width="400" height="300" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/18.jpg"/><br>
<img width="400" height="300" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/19.png"/><br>
#### 4. 在user表中添加影院信息数据
<img width="400" height="300" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/22.png"/><br>
#### 5. 选择地区、输入关键字搜索影院信息
<img width="400" height="300" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/20.png"/><br>
#### 6. 发起请求
theater.vue中向服务消费端发起请求。
<pre>
    //在methods中使用axios的get请求向后台获取信息数据     
    search(){
	this.$ajax('/api/cinema',{
		params:{
			zoneId :this.value,
			keyword:this.input,
		}
	}).then(res => {
		  this.tableData = res.data;
		  this.num = this.tableData.length;
		  console.log(res.data);
	}).catch(function (error) {
	  console.log(error);
	});
    }
</pre>
#### 7. 接收请求、查询数据
将consumer端口修改为[http://localhost:8330/cinema](http://localhost:8330/cinema)，传入前端请求参数，参考[https://www.bilibili.com/video/av47953244?from=search&seid=5742145757867088188](https://www.bilibili.com/video/av47953244?from=search&seid=5742145757867088188)
<pre>
	@RequestMapping(value="/cinema",params = {"zoneId","keyword"})
	public List<Map<String,Object>> hello(String zoneId,String keyword){
		String sql =new String();
		if(zoneId != null && zoneId.length() > 0 && keyword != null && keyword.length() > 0 ) {
			sql = "select * from user where zoneId ='"+zoneId+"' and theater_name like '%"+keyword+"%'" ;
		}else if(zoneId != null && zoneId.length() > 0 ) {
			sql = "select * from user where zoneId ='"+zoneId+"'" ;
		}else if(keyword != null && keyword.length() > 0) {
			sql = "select * from user where theater_name like '%"+keyword+"%'" ;
		}else {
			sql = "select * from user";
		}
        	System.out.println(helloService.userList(sql));	
		List<Map<String,Object>> hello = helloService.userList(sql);
		return hello;
	}
</pre>
#### 8. 返回结果
运行npm run dev，浏览器访问 [http://localhost:8080](http://localhost:8080)，搜索影院信息，获取consumer端口数据成功。
<img width="400" height="300" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/20.png"/><br>
<img width="400" height="300" src="https://github.com/nishibushiedehuang/A-Distributed-Web-System/raw/master/img-folder/21.png"/>
