package cn.snailclimb.service;

import java.util.List;
import java.util.Map;

public interface HelloService {
	public List<Map<String,Object>> userList(String sql);
}
