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




