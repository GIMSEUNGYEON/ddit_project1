package DAO;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class BoardDAO {
	private static BoardDAO instance = null;

	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
		if (instance == null)
			instance = new BoardDAO();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<Map<String, Object>> board() {
		return jdbc.selectList("SELECT BOARD_NO, SUBSTR(BOARD_TITLE,1,5) AS BOARD_TITLE, SUBSTR(BOARD_CONTENT,1,5) AS BOARD_CONTENT, mem_id, board_date FROM BOARD WHERE ROWNUM <=3 ORDER BY BOARD_NO");
	}

	public List<Map<String, Object>> listtt() {
		return jdbc.selectList(
				"SELECT BOARD_NO, SUBSTR(BOARD_TITLE,1,5) AS BOARD_TITLE, SUBSTR(BOARD_CONTENT,1,5) AS BOARD_CONTENT, mem_id, board_date FROM BOARD ORDER BY BOARD_NO");
	}
}
