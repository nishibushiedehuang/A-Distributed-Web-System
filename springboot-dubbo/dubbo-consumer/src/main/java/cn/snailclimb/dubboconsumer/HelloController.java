package cn.snailclimb.dubboconsumer;

import com.alibaba.dubbo.config.annotation.Reference;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.snailclimb.service.HelloService;

@RestController
public class HelloController {
	@Reference
	private HelloService helloService;
	
	@RequestMapping("/hello")
	public List<Map<String,Object>> hello() {
        String sql = "select * from user";
		List<Map<String,Object>> hello = helloService.userList();
		System.out.println(helloService.userList());
		return hello;
	}	
}
