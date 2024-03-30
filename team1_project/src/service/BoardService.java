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

	JDBCUtil jdbc = JDBCUtil.getInstance(); // DAO에 있는데 여기 꼭 써줘야하는지
	BoardDAO dao = BoardDAO.getInstance();
	PrintUtil printUtil = new PrintUtil();
	
	public int board() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
		printUtil.bar2();
		System.out.println("\n\t\t== 게시판 목록 ==");
		printUtil.blank(1);
		System.out.println("번호\t제목\t내용\t작성자\t작성일");
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
		System.out.println("\n1.게시판 전체보기 2.등록 0.뒤로가기");
		System.out.print("번호 입력 >> ");
		int select = ScanUtil.nextInt();
		switch (select) {
		case 1: return View.BOARD_LIST;
		
		case 2:
			if(memID.equals("guest")) {
				System.out.println("비회원 이용자는 게시글 등록이 불가능합니다.");
				ScanUtil.nextLine();
				return View.BOARD;
			}
			return View.BOARD_INSERT;
		default:return View.HOME;
		}
	}

	public int list() {
		System.out.println("\t\t== 게시판 목록 ==");
		System.out.println("번호\t제목\t내용\t작성자\t작성일");
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
		System.out.print("\n번호 입력 >>  \t\t0.게시판 화면");
		int boardNo = ScanUtil.nextInt();
		ArrayList<Object> param = new ArrayList<>();
		param.add(boardNo); // 사용자의 입력을 매개변수로 추가

		String sql = "SELECT * FROM board WHERE board_no = ?";

		// Execute the SQL query to retrieve data
		List<Map<String, Object>> boardContent = jdbc.selectList(sql, param);

		if (boardContent != null && !boardContent.isEmpty()) {
			printUtil.bar();
		    // 결과가 있을 경우 처리			
		    for (Map<String, Object> row : boardContent) {
		    	System.out.println("번호 \t" + row.get("BOARD_NO"));
		    	System.out.println("제목 \t" + row.get("BOARD_TITLE"));
		    	System.out.println("내용 \t" + row.get("BOARD_CONTENT"));
		    	System.out.println("작성자\t" + row.get("MEM_ID"));
		    	System.out.println("작성일\t" + row.get("BOARD_DATE"));
		        // 결과를 처리하는 코드
		        // 예: String title = (String) row.get("title");
		        // 결과를 출력하거나 필요한 대로 사용
		        //System.out.println(row); // 결과를 출력하는 예제
		    }
		    printUtil.bar();
		    System.out.println("목록으로 돌아가기>> 엔터입력");
		    ScanUtil.nextLine();
		    return View.BOARD_LIST;
		
		}else if(boardNo == 0) {
			System.out.println("엔터를 입력하면 게시판 화면으로 돌아갑니다.");
			ScanUtil.nextLine();
			return View.BOARD;
		}else {
		}
		    // 결과가 없을 경우 예외 처리
		    System.out.println("해당하는 게시판 번호가 없습니다. 엔터를 입력하면 게시판 화면으로 돌아갑니다.");

		    // 또는 다른 동작을 수행할 수 있습니다.
		    // 예: 다시 입력 받기, 메뉴로 돌아가기 등
		    System.out.println();
		    ScanUtil.nextLine();
		    return View.BOARD;
		}


	public int insert() {
		// 번호 -> 현재 게시판의 번호에서 max찾아서 +1 , 날짜 - 현재날짜(sysdate)
		// sql = "insert into board values
		ArrayList<Object> param = new ArrayList<>();
		String sql = "insert into BOARD values ((SELECT NVL(MAX(BOARD_NO),0)+1 FROM BOARD),? ,? ,(SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD')from dual),?)";
		
		// param의 1,2,3 값 받기
		printUtil.bar();
		System.out.println("\t\t== 게시글 등록 ==");
		System.out.print("제목>");
		param.add(ScanUtil.nextLine());
		System.out.print("내용>");
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
		System.out.println(result + "개의 글이 등록되었습니다.");
		printUtil.blank(2);
		printUtil.bar();	
		
		}
		
		ScanUtil.nextLine();
		return View.BOARD;
	}

	public int update() {
		   ArrayList<Object> param = new ArrayList<>();
		    
		    System.out.print("수정할 게시글 번호 입력 >> ");
		    int boardNoToUpdate = ScanUtil.nextInt();

		    // 게시글의 작성자를 가져옵니다.
		    String fetchAuthorSql = "SELECT MEM_ID FROM BOARD WHERE BOARD_NO = ?";
		    ArrayList<Object> fetchParams = new ArrayList<>();
		    fetchParams.add(boardNoToUpdate);
		    Map<String, Object> postInfo = jdbc.selectOne(fetchAuthorSql, fetchParams);

		    if (postInfo == null) {
		        System.out.println("게시글을 찾을 수 없습니다.");
		        ScanUtil.nextLine();
		        return View.BOARD;
		    }

		    // 현재 로그인한 사용자의 ID를 가져옵니다.
		    Map<String, Object> loggedInUser = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		    String loggedInUserId = (String) loggedInUser.get("MEM_ID");

		    // 현재 로그인한 사용자가 게시글의 작성자와 일치하는지 확인합니다.
		    if (!loggedInUserId.equals(postInfo.get("MEM_ID"))) {
		        System.out.println("권한이 없습니다. 게시글의 작성자만 수정할 수 있습니다.");
		        ScanUtil.nextLine();
		        return View.BOARD;
		    }

		    String sql = "UPDATE BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NO = ?";

		    System.out.print("제목>");
		    String updateTitle = ScanUtil.nextLine() + "(수정됨)";
		    param.add(updateTitle);
		    System.out.print("내용>");
		    param.add(ScanUtil.nextLine());
		    param.add(boardNoToUpdate);

		    int result = jdbc.update(sql, param);

		    System.out.println(result + "개의 행이 수정되었습니다.");
		    ScanUtil.nextLine();
		    return View.BOARD;
	}

	public int delete() {
	    System.out.print("삭제할 게시글 번호 입력 >> ");
	    int boardNoToDelete = ScanUtil.nextInt();
	    
	    // Fetch the author of the post the user wants to delete
	    String fetchAuthorSql = "SELECT MEM_ID FROM BOARD WHERE BOARD_NO = ?";
	    ArrayList<Object> fetchParams = new ArrayList<>();
	    fetchParams.add(boardNoToDelete);
	    Map<String, Object> postInfo = jdbc.selectOne(fetchAuthorSql, fetchParams);

	    if (postInfo == null) {
	        System.out.println("게시글을 찾을 수 없습니다.");
	        ScanUtil.nextLine();
	        return View.BOARD;
	    }

	    // Get the logged-in user's ID
	    Map<String, Object> loggedInUser = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
	    String loggedInUserId = (String) loggedInUser.get("MEM_ID");

	    // Check if the logged-in user is the author of the post
	    if (!loggedInUserId.equals(postInfo.get("MEM_ID"))) {
	        System.out.println("권한이 없습니다. 게시글의 작성자만 삭제할 수 있습니다.");
	        ScanUtil.nextLine();
	        return View.BOARD;
	    }

	    // If the logged-in user is the author, proceed with the deletion
	    String deleteSql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
	    ArrayList<Object> deleteParams = new ArrayList<>();
	    deleteParams.add(boardNoToDelete);
	    int deleteResult = jdbc.update(deleteSql, deleteParams);

	    if (deleteResult > 0) {
	        System.out.println("게시글이 삭제되었습니다.");

	        // Reorder the board numbers
	        String reorderSql = "UPDATE BOARD SET BOARD_NO = BOARD_NO - 1 WHERE BOARD_NO > ?";
	        ArrayList<Object> reorderParams = new ArrayList<>();
	        reorderParams.add(boardNoToDelete);
	        int reorderResult = jdbc.update(reorderSql, reorderParams);
	        if (reorderResult > 0) {
	        //    System.out.println(reorderResult + "개의 게시글 번호가 정렬되었습니다.");
	        } else {
	        //    System.out.println("게시글 번호 정렬에 실패했습니다.");
	        }
	    } else {
	        System.out.println("게시글 삭제에 실패했습니다.");
	    }

	    ScanUtil.nextLine();
	    return View.BOARD;
	}
	
}