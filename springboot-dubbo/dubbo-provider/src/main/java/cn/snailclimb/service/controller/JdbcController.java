//package cn.snailclimb.service.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class JdbcController {
//	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
// 
//	
//	@GetMapping("/userList")
//	public List<Map<String,Object>> userList(){
//		String sql = "select * from user";
//		List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql);
//		return list_maps;
//	}
//}
