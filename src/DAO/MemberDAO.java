package DAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDAO {
	private static MemberDAO instance = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(instance == null) instance = new MemberDAO();
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public Map<String, Object> login(String id, String pw) {
		return jdbc.selectOne("SELECT * FROM MEMBER "
				+ " WHERE MEM_ID='"+id+"' AND MEM_PW='"+pw+"' ");
	}
	
	public int signUp(List<Object> param) {
		return jdbc.update("INSERT INTO MEMBER (MEM_ID, MEM_PW, MEM_NAME, MEM_HP, MEM_ADMIN) VALUES (?,?,?,?,?)", param);
	}
	
	public Map<String, Object> inquiry(String sql) {
		return jdbc.selectOne(sql);
	}
	
	public int update(String sql) {
		return jdbc.update(sql);
		
	}
	public int resign(String sql) {
		return jdbc.update(sql);
		
	}
	public Map<String, Object> isOverapID(String id) {
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID='" + id + "'";
		return jdbc.selectOne(sql);
	}
	public List<Map<String, Object>> adminInquiry(String sql){
		return jdbc.selectList(sql);
	}
}

