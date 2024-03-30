package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.BoardDAO;
import util.JDBCUtil;
import util.ScanUtil;
import util.PrintUtil;
import View.View;
import Controller.Controller;

public class BoardService {
	private static BoardService instance = null;

	private BoardService() {
	}

	public static BoardService getInstance() {
		if (instance == null)
			instance = new BoardService();
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance(); // DAO�� �ִµ� ���� �� ������ϴ���
	BoardDAO dao = BoardDAO.getInstance();
	PrintUtil printUtil = new PrintUtil();
	
	public int board() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
		printUtil.bar2();
		System.out.println("\n\t\t== �Խ��� ��� ==");
		printUtil.blank(1);
		System.out.println("��ȣ\t����\t����\t�ۼ���\t�ۼ���");
		List<Map<String, Object>> board = dao.board();
		for (Map<String, Object> item : board) {
			System.out.print(item.get("BOARD_NO"));
			System.out.print("\t" + item.get("BOARD_TITLE"));
			System.out.print("\t" + item.get("BOARD_CONTENT"));
			System.out.print("\t" + item.get("MEM_ID"));
			System.out.print("\t" + item.get("BOARD_DATE"));
			System.out.println();
		}
		printUtil.blank(1);
		printUtil.bar2();
		System.out.println("\n1.�Խ��� ��ü���� 2.��� 0.�ڷΰ���");
		System.out.print("��ȣ �Է� >> ");
		int select = ScanUtil.nextInt();
		switch (select) {
		case 1: return View.BOARD_LIST;
		
		case 2:
			if(memID.equals("guest")) {
				System.out.println("��ȸ�� �̿��ڴ� �Խñ� ����� �Ұ����մϴ�.");
				ScanUtil.nextLine();
				return View.BOARD;
			}
			return View.BOARD_INSERT;
		default:return View.HOME;
		}
	}

	public int list() {
		System.out.println("\t\t== �Խ��� ��� ==");
		System.out.println("��ȣ\t����\t����\t�ۼ���\t�ۼ���");
		List<Map<String, Object>> list = dao.listtt();
		
		for (Map<String, Object> item : list) {
			System.out.print(item.get("BOARD_NO"));
			System.out.print("\t" + item.get("BOARD_TITLE"));
			System.out.print("\t" + item.get("BOARD_CONTENT"));
			System.out.print("\t" + item.get("MEM_ID"));
			System.out.print("\t" + item.get("BOARD_DATE"));
			System.out.println();
			
		}
		printUtil.bar2();
		System.out.print("\n��ȣ �Է� >>  \t\t0.�Խ��� ȭ��");
		int boardNo = ScanUtil.nextInt();
		ArrayList<Object> param = new ArrayList<>();
		param.add(boardNo); // ������� �Է��� �Ű������� �߰�

		String sql = "SELECT * FROM board WHERE board_no = ?";

		// Execute the SQL query to retrieve data
		List<Map<String, Object>> boardContent = jdbc.selectList(sql, param);

		if (boardContent != null && !boardContent.isEmpty()) {
			printUtil.bar();
		    // ����� ���� ��� ó��			
		    for (Map<String, Object> row : boardContent) {
		    	System.out.println("��ȣ \t" + row.get("BOARD_NO"));
		    	System.out.println("���� \t" + row.get("BOARD_TITLE"));
		    	System.out.println("���� \t" + row.get("BOARD_CONTENT"));
		    	System.out.println("�ۼ���\t" + row.get("MEM_ID"));
		    	System.out.println("�ۼ���\t" + row.get("BOARD_DATE"));
		        // ����� ó���ϴ� �ڵ�
		        // ��: String title = (String) row.get("title");
		        // ����� ����ϰų� �ʿ��� ��� ���
		        //System.out.println(row); // ����� ����ϴ� ����
		    }
		    printUtil.bar();
		    System.out.println("������� ���ư���>> �����Է�");
		    ScanUtil.nextLine();
		    return View.BOARD_LIST;
		
		}else if(boardNo == 0) {
			System.out.println("���͸� �Է��ϸ� �Խ��� ȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
			return View.BOARD;
		}else {
		}
		    // ����� ���� ��� ���� ó��
		    System.out.println("�ش��ϴ� �Խ��� ��ȣ�� �����ϴ�. ���͸� �Է��ϸ� �Խ��� ȭ������ ���ư��ϴ�.");

		    // �Ǵ� �ٸ� ������ ������ �� �ֽ��ϴ�.
		    // ��: �ٽ� �Է� �ޱ�, �޴��� ���ư��� ��
		    System.out.println();
		    ScanUtil.nextLine();
		    return View.BOARD;
		}


	public int insert() {
		// ��ȣ -> ���� �Խ����� ��ȣ���� maxã�Ƽ� +1 , ��¥ - ���糯¥(sysdate)
		// sql = "insert into board values
		ArrayList<Object> param = new ArrayList<>();
		String sql = "insert into BOARD values ((SELECT NVL(MAX(BOARD_NO),0)+1 FROM BOARD),? ,? ,(SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD')from dual),?)";
		
		// param�� 1,2,3 �� �ޱ�
		printUtil.bar();
		System.out.println("\t\t== �Խñ� ��� ==");
		System.out.print("����>");
		param.add(ScanUtil.nextLine());
		System.out.print("����>");
		param.add(ScanUtil.nextLine());	
		
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		
		String memID=(String) map.get("MEM_ID");
		param.add(memID);
		
		printUtil.loading();
		
		int result = jdbc.update(sql, param);
		if(param != null) {
		printUtil.blank(2);
		printUtil.bar();	
		printUtil.blank(2);
		System.out.println(result + "���� ���� ��ϵǾ����ϴ�.");
		printUtil.blank(2);
		printUtil.bar();	
		
		}
		
		ScanUtil.nextLine();
		return View.BOARD;
	}

	public int update() {
		   ArrayList<Object> param = new ArrayList<>();
		    
		    System.out.print("������ �Խñ� ��ȣ �Է� >> ");
		    int boardNoToUpdate = ScanUtil.nextInt();

		    // �Խñ��� �ۼ��ڸ� �����ɴϴ�.
		    String fetchAuthorSql = "SELECT MEM_ID FROM BOARD WHERE BOARD_NO = ?";
		    ArrayList<Object> fetchParams = new ArrayList<>();
		    fetchParams.add(boardNoToUpdate);
		    Map<String, Object> postInfo = jdbc.selectOne(fetchAuthorSql, fetchParams);

		    if (postInfo == null) {
		        System.out.println("�Խñ��� ã�� �� �����ϴ�.");
		        ScanUtil.nextLine();
		        return View.BOARD;
		    }

		    // ���� �α����� ������� ID�� �����ɴϴ�.
		    Map<String, Object> loggedInUser = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		    String loggedInUserId = (String) loggedInUser.get("MEM_ID");

		    // ���� �α����� ����ڰ� �Խñ��� �ۼ��ڿ� ��ġ�ϴ��� Ȯ���մϴ�.
		    if (!loggedInUserId.equals(postInfo.get("MEM_ID"))) {
		        System.out.println("������ �����ϴ�. �Խñ��� �ۼ��ڸ� ������ �� �ֽ��ϴ�.");
		        ScanUtil.nextLine();
		        return View.BOARD;
		    }

		    String sql = "UPDATE BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NO = ?";

		    System.out.print("����>");
		    String updateTitle = ScanUtil.nextLine() + "(������)";
		    param.add(updateTitle);
		    System.out.print("����>");
		    param.add(ScanUtil.nextLine());
		    param.add(boardNoToUpdate);

		    int result = jdbc.update(sql, param);

		    System.out.println(result + "���� ���� �����Ǿ����ϴ�.");
		    ScanUtil.nextLine();
		    return View.BOARD;
	}

	public int delete() {
	    System.out.print("������ �Խñ� ��ȣ �Է� >> ");
	    int boardNoToDelete = ScanUtil.nextInt();
	    
	    // Fetch the author of the post the user wants to delete
	    String fetchAuthorSql = "SELECT MEM_ID FROM BOARD WHERE BOARD_NO = ?";
	    ArrayList<Object> fetchParams = new ArrayList<>();
	    fetchParams.add(boardNoToDelete);
	    Map<String, Object> postInfo = jdbc.selectOne(fetchAuthorSql, fetchParams);

	    if (postInfo == null) {
	        System.out.println("�Խñ��� ã�� �� �����ϴ�.");
	        ScanUtil.nextLine();
	        return View.BOARD;
	    }

	    // Get the logged-in user's ID
	    Map<String, Object> loggedInUser = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
	    String loggedInUserId = (String) loggedInUser.get("MEM_ID");

	    // Check if the logged-in user is the author of the post
	    if (!loggedInUserId.equals(postInfo.get("MEM_ID"))) {
	        System.out.println("������ �����ϴ�. �Խñ��� �ۼ��ڸ� ������ �� �ֽ��ϴ�.");
	        ScanUtil.nextLine();
	        return View.BOARD;
	    }

	    // If the logged-in user is the author, proceed with the deletion
	    String deleteSql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
	    ArrayList<Object> deleteParams = new ArrayList<>();
	    deleteParams.add(boardNoToDelete);
	    int deleteResult = jdbc.update(deleteSql, deleteParams);

	    if (deleteResult > 0) {
	        System.out.println("�Խñ��� �����Ǿ����ϴ�.");

	        // Reorder the board numbers
	        String reorderSql = "UPDATE BOARD SET BOARD_NO = BOARD_NO - 1 WHERE BOARD_NO > ?";
	        ArrayList<Object> reorderParams = new ArrayList<>();
	        reorderParams.add(boardNoToDelete);
	        int reorderResult = jdbc.update(reorderSql, reorderParams);
	        if (reorderResult > 0) {
	        //    System.out.println(reorderResult + "���� �Խñ� ��ȣ�� ���ĵǾ����ϴ�.");
	        } else {
	        //    System.out.println("�Խñ� ��ȣ ���Ŀ� �����߽��ϴ�.");
	        }
	    } else {
	        System.out.println("�Խñ� ������ �����߽��ϴ�.");
	    }

	    ScanUtil.nextLine();
	    return View.BOARD;
	}
	
}