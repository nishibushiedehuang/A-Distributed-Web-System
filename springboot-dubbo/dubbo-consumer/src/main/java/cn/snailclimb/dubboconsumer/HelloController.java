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
}
