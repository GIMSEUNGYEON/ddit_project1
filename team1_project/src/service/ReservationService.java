package service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Controller.Controller;
import util.ScanUtil;
import util.PrintUtil;
import util.JDBCUtil;
import View.View;

public class ReservationService {
	private static ReservationService instance = null;

	JDBCUtil jdbc = JDBCUtil.getInstance();
	PrintUtil printUtil = new PrintUtil();

	public ReservationService() {
	}

	public static ReservationService getInstance() {
		if (instance == null)
			instance = new ReservationService();
		return instance;
	}

//	public static void main(String[] args) {
//		ReservationService rse = new ReservationService();
//		rse.reservation();
//	}

	// sql, List컬렉션, rs, rno, sno 변수선언
	String sql = null;
	List<Map<String, Object>> paramR = null;
	int rs = 0;
	int rno = 0;
	int sno = 0;
	String select;
	Map<String, Object> mem = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
	String memID;
//	String memID = "EB0221";
	

	public int reservation() {

		Map<String, Object> mem = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		// 화면 구현
		printUtil.bar2();
		printUtil.blank(1);
		System.out.println("\t\t좌석 예약");
		System.out.println("");
		System.out.println("\t\t1. 입실");
		System.out.println("\t\t2. 퇴실");
//		System.out.println("\t\t3. 예약시간 연장");
		printUtil.blank(2);
		printUtil.bar();
		System.out.println("\t\t\t\t0. 뒤로가기");
		System.out.print("\t\t선택 >> ");
//		System.out.println((String) mem.get("MEM_ID"));
		String memID = (String) mem.get("MEM_ID");

		// 명령문 입혁
		switch (ScanUtil.nextInt()) {

		case 0:
			return View.HOME;// 컨트롤러 연결해야 돌아감

		case 1:  //***********
			if(memID.equals("guest")) {
				return View.RESERVATION_RNO;
			}
			sql =" SELECT * FROM RESERVATION WHERE REV_OUT IS NULL AND MEM_ID=\'" + memID + "\' ";
			Map<String, Object> s = jdbc.selectOne(sql);
//			System.out.println(s.size());// s.size 6 출력 : key-value가 6쌍임
			if ( s != null ) {    //** 예약기록이 있는지 확인 : 있으면 reservation으로 돌아가기
				System.out.println("\t예약 중인 좌석을 먼저 퇴실해주세요. 퇴실 화면으로 이동합니다.");
				ScanUtil.nextLine();
//				reservationOut();
				return View.RESERVATION_OUT;
			}
			
//			reservationRno();// 컨트롤러 연결할때 주석처리
			return View.RESERVATION_RNO;

		case 2: //*******

			sql ="SELECT * FROM RESERVATION WHERE REV_OUT IS NULL AND MEM_ID=\'" + memID + "\' ";
			s = jdbc.selectOne(sql);
//			System.out.println(s.size());
			if ( s == null ) {
				System.out.println("예약 중인 좌석이 없습니다. \t예약 첫화면으로 돌아갑니다.");
				ScanUtil.nextLine();
//				reservation();
				return View.RESERVATION;
			}
//			reservationOut();
			return View.RESERVATION_OUT;

		default:
			System.out.println("\t잘못 입력하셨습니다.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		}
	}

	public int reservationRno() {
		Map<String, Object> mem = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) mem.get("MEM_ID");
		
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t열람실 선택");
		System.out.println("\t   1. 101호" + "( " + count(101) + " / 9  )");
		System.out.println("\t   2. 102호" + "( " + count(102) + " / 9  )");
		System.out.println("\t   3. 103호" + "( " + count(103) + " / 9  )");
		System.out.println("\t   4. 201호" + "( " + count(201) + " / 20 )");
		System.out.println("\t   5. 202호" + "( " + count(202) + " / 20 )");
		System.out.println("");
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t\t\t0. 뒤로가기");
		System.out.print("\t\t선택 >> ");
		if(memID.equals("guest")) {
			ScanUtil.nextLine();
			System.out.println("\n비회원이용자는 좌석 예약이 불가합니다. 홈화면으로 돌아갑니다.");
			ScanUtil.nextLine();
			return View.HOME;
		}
		switch (ScanUtil.nextInt()) {

		case 0:
//			reservation();
			return View.RESERVATION;

		case 1:
			rno = 101;
			reserveSeat9();
//			reservationIn();
			return View.RESERVATION_IN;

		case 2:
			rno = 102;
			reserveSeat9();
//			reservationIn();
			return View.RESERVATION_IN;

		case 3:
			rno = 103;
			reserveSeat9();
//			reservationIn();
			return View.RESERVATION_IN;

		case 4:
			rno = 201;
			reserveSeat20();
//			reservationIn();
			return View.RESERVATION_IN;

		case 5:
			rno = 202;
			reserveSeat20();
//			reservationIn();
			return View.RESERVATION_IN;

		default:
			System.out.println("/t잘못 입력하셨습니다.");
			ScanUtil.nextLine();
//			reservationRno();
			return View.RESERVATION_RNO;
		}
	}

	public void reserveSeat9() {
		sql = " SELECT * FROM SEAT WHERE SEAT_RNO = " + rno;
		paramR = jdbc.selectList(sql);
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t좌석 선택");
		printUtil.blank(1);

		for (int i = 0; i < (paramR.size()); i++) {
			if ((i + 1) % 3 == 0) {
				System.out.printf("\t  %-2s ", paramR.get(i).get("SEAT_SNO"));
				if (paramR.get(i).get("SEAT_REV").equals("T")) {
					System.out.println("■");
				} else {
					System.out.println("□");
				}
			} else {
				System.out.printf("\t  %-2s ", paramR.get(i).get("SEAT_SNO"));
				if (paramR.get(i).get("SEAT_REV").equals("T")) {
					System.out.print("■");
				} else {
					System.out.print("□");
				}
			}
		}

		printUtil.blank(1);
		printUtil.bar();
		System.out.println("\t\t\t\t0. 뒤로가기");
		System.out.print("\t\t선택 >> ");
		sno = ScanUtil.nextInt();
		if (sno == 0) {
			reservationRno();
		} else if (sno > 0 && sno < 10) {
			if (paramR.get(sno - 1).get("SEAT_REV").equals("T")) {
				System.out.println("/t이미 예약된 좌석입니다. 다른 좌석을 선택하세요.");
				ScanUtil.nextLine();
				reserveSeat9();
			}
		} else {
			System.out.println("/t잘못 입력하셨습니다. 좌석을 선택하세요.");
			ScanUtil.nextLine();
			reserveSeat9();
		}
	}

	public void reserveSeat20() {
		sql = " SELECT * FROM SEAT WHERE SEAT_RNO = " + rno;
		paramR = jdbc.selectList(sql);
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t좌석 선택");
		printUtil.blank(1);
		for (int i = 0; i < (paramR.size()); i++) {
			if ((i + 1) % 5 == 0) {
				System.out.printf("  %-2s ", paramR.get(i).get("SEAT_SNO"));
				if (paramR.get(i).get("SEAT_REV").equals("T")) {
					System.out.println("■ ");
				} else {
					System.out.println("□ ");
				}
			} else {
				if ((i + 1) % 5 == 1) {
					System.out.print("    ");
				}
				System.out.printf("  %-2s ", paramR.get(i).get("SEAT_SNO"));
				if (paramR.get(i).get("SEAT_REV").equals("T")) {
					System.out.print("■ ");
				} else {
					System.out.print("□ ");
				}
			}
		}

		printUtil.bar();
		System.out.println("\t\t\t\t0. 뒤로가기");
		System.out.print("\t\t선택 >> ");
		sno = ScanUtil.nextInt();

		if (sno == 0) {
			reservationRno();
//			return View.RESERVATION_RNO;
		} else if (sno > 0 && sno < 21) {
			if (paramR.get(sno - 1).get("SEAT_REV").equals("T")) {
				System.out.println("\t이미 예약된 좌석입니다. 다른 좌석을 선택하세요.");
				ScanUtil.nextLine();
				reserveSeat20();
			}
//			else {
//				return sno;	
//			}
		} else {
			System.out.println("\t잘못 입력하셨습니다. 좌석을 선택하세요.");
			ScanUtil.nextLine();
			reserveSeat20();
//			return View.RESERVATION_RNO;
		}
	}

	public int reservationIn() {
		Map<String, Object> mem = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t예약 확인");
		printUtil.blank(2);
		System.out.println("\t" + rno + "실 " + sno + "석을 예약하시겠습니까?");
		printUtil.blank(2);
		printUtil.bar();
		System.out.println("\t\t\t\t0. 뒤로가기");
		System.out.print("\t\t선택 (Y/N) >> ");
		select = ScanUtil.nextLine();
		if (select.equalsIgnoreCase("y")) {
//			memID = "EB0221"; //*************
			String memID = (String) mem.get("MEM_ID"); //********
			sql = "UPDATE SEAT SET SEAT_REV='T' WHERE SEAT_RNO = " + rno + "AND SEAT_SNO = " + sno;
			rs = jdbc.update(sql);
			if ( rs == 1 ) {
				System.out.println("\t좌석점유성공");
			};// rs가 1이 나오면 예약 잘 된것.. 표시?
			sql = "INSERT INTO RESERVATION(SEAT_RNO, SEAT_SNO, REV_NO, REV_IN, MEM_ID)"+
					"VALUES(" + rno + ", " + sno + ", \'" + rno + sno + "\'||TO_CHAR(SYSDATE,\'YYMMDDHH24MI\'), TO_CHAR(SYSDATE,\'YYMMDDHH24MI\'), \'" + memID + "\')";
			rs = jdbc.update(sql);
			if ( rs == 1 ) {
				System.out.println("\t예약기록생성성공");
			}
			Controller.sessionStorage.put("SEAT_RNO", rno);
			Controller.sessionStorage.put("SEAT_SNO", sno);
			System.out.println("\t예약이 완료되었습니다. 처음 화면으로 돌아갑니다.");

				    ScanUtil.nextLine();
//				    reservation();
			return View.HOME;
		}

		else if (select.equalsIgnoreCase("n")) {
			System.out.println("\t좌석 예약을 취소하고 예약 첫화면으로 돌아갑니다.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		} else if (select.equals("0")) {//**********
//			reservation();
			return View.RESERVATION;
		} 
		else {
			System.out.println("\t잘못 입력하셨습니다. 예약 첫화면으로 돌아갑니다.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		}
	}

	// y가 됐을때 T로 바꿔주고 + reservation 기록 남겨주기

	// count, 호실별 F의 갯수를 세서 반환하는 메소드
	public int count(int rno) {
		sql = " SELECT SEAT_REV FROM SEAT WHERE SEAT_RNO = " + rno + " AND SEAT_REV = 'F'";
		if (jdbc.selectList(sql).size() == 0)
			return 0;
		else
			return (Integer) jdbc.selectList(sql).size();
//		Integer.parseInt((String)jdbc.selectOne(sql).get("COUNT(*)"));

	}
	public int reservationOut() {
		Map<String, Object> mem = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) mem.get("MEM_ID");
		sql =" SELECT * FROM RESERVATION WHERE REV_OUT IS NULL AND MEM_ID=\'" + memID + "\' ";
		Map<String, Object> s = jdbc.selectOne(sql);
		rno = Integer.parseInt(String.valueOf(s.get("SEAT_RNO")));
//		rno = (Integer) Controller.sessionStorage.get("SEAT_RNO");
		sno = Integer.parseInt(String.valueOf(s.get("SEAT_SNO")));
//		System.out.print("\t\t좌석입력 >> ");
//		sno = (Integer) Controller.sessionStorage.get("SEAT_SNO");
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t퇴실 확인");
		printUtil.blank(2);
		System.out.println("\t" + rno + "실 " + sno + "석을 퇴실하시겠습니까?");
		printUtil.blank(2);
		printUtil.bar();
		System.out.println("\t\t\t\t0. 뒤로가기");
		System.out.print("\t\t선택 (Y/N) >> ");
		select = ScanUtil.nextLine();
		
		
		//**********************
		if (select.equalsIgnoreCase("y")) {
			sql = "UPDATE SEAT SET SEAT_REV='F' WHERE SEAT_RNO = " + rno + "AND SEAT_SNO = " + sno;
			rs = jdbc.update(sql);
			if ( rs == 1 ) {
				System.out.println("\t좌석비움성공");
			};// rs가 1이 나오면 예약 잘 된것.. 표시?
			sql ="UPDATE RESERVATION SET REV_OUT = TO_CHAR(SYSDATE,'YYMMDDHH24MI') WHERE REV_OUT IS NULL AND MEM_ID=\'" + memID + "\' ";
			rs = jdbc.update(sql);
			if ( rs == 1 ) {
				System.out.println("\t퇴실기록생성성공");
			}
			Controller.sessionStorage.remove("SEAT_RNO");
			Controller.sessionStorage.remove("SEAT_SNO");
			System.out.println("\t퇴실이 완료되었습니다. 처음 화면으로 돌아갑니다.");
			ScanUtil.nextLine();
			return View.HOME;
			//*********************
		} else if (select.equalsIgnoreCase("n")) {
			System.out.println("\t퇴실을 취소하고 예약 첫화면으로 돌아갑니다.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		} else if (select.equals("0")) {//**********
//			reservation();
			return View.RESERVATION;
		} 
		else {
			System.out.println("\t잘못 입력하셨습니다. 예약 첫화면으로 돌아갑니다.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		}
	}
}