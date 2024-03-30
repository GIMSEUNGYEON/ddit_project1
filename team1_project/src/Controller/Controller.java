package Controller;

import util.PrintUtil;
import util.ScanUtil;
import util.Utility;

import java.util.HashMap;
import java.util.Map;

import View.View;
import service.BoardService;
import service.MemberService;
import service.ReservationService;

public class Controller {
	
	static public Map<String, Object> sessionStorage = new HashMap<>();
	MemberService memberService = MemberService.getInstance();
	BoardService boardService = BoardService.getInstance();
	ReservationService res = new ReservationService();
	static PrintUtil printUtil = new PrintUtil();
	public static void main(String[] args) {
		
		printUtil.intro();

		//PrintUtil.main();
		
		new Controller().controll();
		
	}

	private int controll() {
		int view = View.MAIN;
		while(true) {
			switch (view) {
			case View.MAIN : 			view = printUtil.main(); break;
			case View.ERROR :			view = error(); break;
			case View.HOME :			view  = home(); break;
			case View.MEMBER_ADMIN_INQUIRY :view  = memberService.admin_inquiry(); break;
			case View.MEMBER_LOGIN :	view = memberService.login();	break;		
			case View.MEMBER_LOGOUT :	view = memberService.logout();	break;		
//			case View.MEMBER : 			view = 
			case View.MEMBER_SIGNUP :	view = memberService.signUp(); break;
			case View.MEMBER_INQUIRY :	view = memberService.inquiry(); break;
			case View.MEMBER_UPDATE : 	view =	memberService.infoUpdate(); break; 
//			case View.MEMBER_RESIGN : 	view = 
			case View.RESERVATION : 	view = res.reservation(); break;
			case View.RESERVATION_RNO : view = res.reservationRno(); break;
//			case View.RESERVATION_SNO : view =
			case View.RESERVATION_IN : 	view = res.reservationIn(); break;
			case View.RESERVATION_OUT : view = res.reservationOut(); break;
			case View.BOARD : 			view = boardService.board(); break;
			case View.BOARD_LIST : 		view = boardService.list(); break;
			case View.BOARD_INSERT :    view = boardService.insert(); break;
			case View.BOARD_DELETE :  	view = boardService.delete(); break;
			case View.BOARD_UPDATE : 	view = boardService.update(); break;
			case View.GUEST : 			view = memberService.guest(); break;
			}
		}
	}
	
	public int home(){
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String TF=(String) map.get("MEM_ADMIN");
		Map<String, Object> mem = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) mem.get("MEM_ID");
		printUtil.bar2();
		printUtil.blank(2);		
		System.out.println("\t\t  H O M E");
		System.out.println("\t\t1. 개인정보 확인");
		System.out.println("\t\t2. 좌석 예약");
		System.out.println("\t\t3. 게시판");
		System.out.println("\t\t4. 로그아웃");
		printUtil.blank(2);
		printUtil.bar2();
		System.out.print("\n>>입력  ");
		int select = ScanUtil.nextInt();
		switch (select) {
		case 1 : if(TF.equals("T")) {
			return View.MEMBER_ADMIN_INQUIRY;
		}else{
			return View.MEMBER_INQUIRY; 
		}
		case 2 :
		if(memID.equals("guest")) {
			System.out.println("비회원 이용자는 좌석 예약이 불가능합니다.");
			ScanUtil.nextLine();
		}
		 return View.RESERVATION;
		case 3 :
		if(memID.equals("guest")) {
			System.out.println("비회원 이용자는 게시글 작성이 불가능합니다.");
			ScanUtil.nextLine();
		}
		 return View.BOARD;
		case 4 : return View.MEMBER_LOGOUT;
		default: return View.ERROR;
		}
	}
	
	public int error() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String TF=(String) map.get("MEM_ADMIN");
		System.out.println("오류가 발생했습니다. 엔터를 입력하면 홈 화면으로 돌아갑니다.");
		if(TF.equals("T")) {
		ScanUtil.nextLine();
		return View.HOME;
		}else {
		ScanUtil.nextLine();
		return View.HOME;
		}
	}

}
