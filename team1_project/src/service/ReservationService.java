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

	// sql, List�÷���, rs, rno, sno ��������
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
		// ȭ�� ����
		printUtil.bar2();
		printUtil.blank(1);
		System.out.println("\t\t�¼� ����");
		System.out.println("");
		System.out.println("\t\t1. �Խ�");
		System.out.println("\t\t2. ���");
//		System.out.println("\t\t3. ����ð� ����");
		printUtil.blank(2);
		printUtil.bar();
		System.out.println("\t\t\t\t0. �ڷΰ���");
		System.out.print("\t\t���� >> ");
//		System.out.println((String) mem.get("MEM_ID"));
		String memID = (String) mem.get("MEM_ID");

		// ��ɹ� ����
		switch (ScanUtil.nextInt()) {

		case 0:
			return View.HOME;// ��Ʈ�ѷ� �����ؾ� ���ư�

		case 1:  //***********
			if(memID.equals("guest")) {
				return View.RESERVATION_RNO;
			}
			sql =" SELECT * FROM RESERVATION WHERE REV_OUT IS NULL AND MEM_ID=\'" + memID + "\' ";
			Map<String, Object> s = jdbc.selectOne(sql);
//			System.out.println(s.size());// s.size 6 ��� : key-value�� 6����
			if ( s != null ) {    //** �������� �ִ��� Ȯ�� : ������ reservation���� ���ư���
				System.out.println("\t���� ���� �¼��� ���� ������ּ���. ��� ȭ������ �̵��մϴ�.");
				ScanUtil.nextLine();
//				reservationOut();
				return View.RESERVATION_OUT;
			}
			
//			reservationRno();// ��Ʈ�ѷ� �����Ҷ� �ּ�ó��
			return View.RESERVATION_RNO;

		case 2: //*******

			sql ="SELECT * FROM RESERVATION WHERE REV_OUT IS NULL AND MEM_ID=\'" + memID + "\' ";
			s = jdbc.selectOne(sql);
//			System.out.println(s.size());
			if ( s == null ) {
				System.out.println("���� ���� �¼��� �����ϴ�. \t���� ùȭ������ ���ư��ϴ�.");
				ScanUtil.nextLine();
//				reservation();
				return View.RESERVATION;
			}
//			reservationOut();
			return View.RESERVATION_OUT;

		default:
			System.out.println("\t�߸� �Է��ϼ̽��ϴ�.");
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
		System.out.println("\t\t������ ����");
		System.out.println("\t   1. 101ȣ" + "( " + count(101) + " / 9  )");
		System.out.println("\t   2. 102ȣ" + "( " + count(102) + " / 9  )");
		System.out.println("\t   3. 103ȣ" + "( " + count(103) + " / 9  )");
		System.out.println("\t   4. 201ȣ" + "( " + count(201) + " / 20 )");
		System.out.println("\t   5. 202ȣ" + "( " + count(202) + " / 20 )");
		System.out.println("");
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t\t\t0. �ڷΰ���");
		System.out.print("\t\t���� >> ");
		if(memID.equals("guest")) {
			ScanUtil.nextLine();
			System.out.println("\n��ȸ���̿��ڴ� �¼� ������ �Ұ��մϴ�. Ȩȭ������ ���ư��ϴ�.");
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
			System.out.println("/t�߸� �Է��ϼ̽��ϴ�.");
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
		System.out.println("\t\t�¼� ����");
		printUtil.blank(1);

		for (int i = 0; i < (paramR.size()); i++) {
			if ((i + 1) % 3 == 0) {
				System.out.printf("\t  %-2s ", paramR.get(i).get("SEAT_SNO"));
				if (paramR.get(i).get("SEAT_REV").equals("T")) {
					System.out.println("��");
				} else {
					System.out.println("��");
				}
			} else {
				System.out.printf("\t  %-2s ", paramR.get(i).get("SEAT_SNO"));
				if (paramR.get(i).get("SEAT_REV").equals("T")) {
					System.out.print("��");
				} else {
					System.out.print("��");
				}
			}
		}

		printUtil.blank(1);
		printUtil.bar();
		System.out.println("\t\t\t\t0. �ڷΰ���");
		System.out.print("\t\t���� >> ");
		sno = ScanUtil.nextInt();
		if (sno == 0) {
			reservationRno();
		} else if (sno > 0 && sno < 10) {
			if (paramR.get(sno - 1).get("SEAT_REV").equals("T")) {
				System.out.println("/t�̹� ����� �¼��Դϴ�. �ٸ� �¼��� �����ϼ���.");
				ScanUtil.nextLine();
				reserveSeat9();
			}
		} else {
			System.out.println("/t�߸� �Է��ϼ̽��ϴ�. �¼��� �����ϼ���.");
			ScanUtil.nextLine();
			reserveSeat9();
		}
	}

	public void reserveSeat20() {
		sql = " SELECT * FROM SEAT WHERE SEAT_RNO = " + rno;
		paramR = jdbc.selectList(sql);
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t�¼� ����");
		printUtil.blank(1);
		for (int i = 0; i < (paramR.size()); i++) {
			if ((i + 1) % 5 == 0) {
				System.out.printf("  %-2s ", paramR.get(i).get("SEAT_SNO"));
				if (paramR.get(i).get("SEAT_REV").equals("T")) {
					System.out.println("�� ");
				} else {
					System.out.println("�� ");
				}
			} else {
				if ((i + 1) % 5 == 1) {
					System.out.print("    ");
				}
				System.out.printf("  %-2s ", paramR.get(i).get("SEAT_SNO"));
				if (paramR.get(i).get("SEAT_REV").equals("T")) {
					System.out.print("�� ");
				} else {
					System.out.print("�� ");
				}
			}
		}

		printUtil.bar();
		System.out.println("\t\t\t\t0. �ڷΰ���");
		System.out.print("\t\t���� >> ");
		sno = ScanUtil.nextInt();

		if (sno == 0) {
			reservationRno();
//			return View.RESERVATION_RNO;
		} else if (sno > 0 && sno < 21) {
			if (paramR.get(sno - 1).get("SEAT_REV").equals("T")) {
				System.out.println("\t�̹� ����� �¼��Դϴ�. �ٸ� �¼��� �����ϼ���.");
				ScanUtil.nextLine();
				reserveSeat20();
			}
//			else {
//				return sno;	
//			}
		} else {
			System.out.println("\t�߸� �Է��ϼ̽��ϴ�. �¼��� �����ϼ���.");
			ScanUtil.nextLine();
			reserveSeat20();
//			return View.RESERVATION_RNO;
		}
	}

	public int reservationIn() {
		Map<String, Object> mem = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t���� Ȯ��");
		printUtil.blank(2);
		System.out.println("\t" + rno + "�� " + sno + "���� �����Ͻðڽ��ϱ�?");
		printUtil.blank(2);
		printUtil.bar();
		System.out.println("\t\t\t\t0. �ڷΰ���");
		System.out.print("\t\t���� (Y/N) >> ");
		select = ScanUtil.nextLine();
		if (select.equalsIgnoreCase("y")) {
//			memID = "EB0221"; //*************
			String memID = (String) mem.get("MEM_ID"); //********
			sql = "UPDATE SEAT SET SEAT_REV='T' WHERE SEAT_RNO = " + rno + "AND SEAT_SNO = " + sno;
			rs = jdbc.update(sql);
			if ( rs == 1 ) {
				System.out.println("\t�¼���������");
			};// rs�� 1�� ������ ���� �� �Ȱ�.. ǥ��?
			sql = "INSERT INTO RESERVATION(SEAT_RNO, SEAT_SNO, REV_NO, REV_IN, MEM_ID)"+
					"VALUES(" + rno + ", " + sno + ", \'" + rno + sno + "\'||TO_CHAR(SYSDATE,\'YYMMDDHH24MI\'), TO_CHAR(SYSDATE,\'YYMMDDHH24MI\'), \'" + memID + "\')";
			rs = jdbc.update(sql);
			if ( rs == 1 ) {
				System.out.println("\t�����ϻ�������");
			}
			Controller.sessionStorage.put("SEAT_RNO", rno);
			Controller.sessionStorage.put("SEAT_SNO", sno);
			System.out.println("\t������ �Ϸ�Ǿ����ϴ�. ó�� ȭ������ ���ư��ϴ�.");

				    ScanUtil.nextLine();
//				    reservation();
			return View.HOME;
		}

		else if (select.equalsIgnoreCase("n")) {
			System.out.println("\t�¼� ������ ����ϰ� ���� ùȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		} else if (select.equals("0")) {//**********
//			reservation();
			return View.RESERVATION;
		} 
		else {
			System.out.println("\t�߸� �Է��ϼ̽��ϴ�. ���� ùȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		}
	}

	// y�� ������ T�� �ٲ��ְ� + reservation ��� �����ֱ�

	// count, ȣ�Ǻ� F�� ������ ���� ��ȯ�ϴ� �޼ҵ�
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
//		System.out.print("\t\t�¼��Է� >> ");
//		sno = (Integer) Controller.sessionStorage.get("SEAT_SNO");
		printUtil.bar2();
		System.out.println("");
		System.out.println("\t\t��� Ȯ��");
		printUtil.blank(2);
		System.out.println("\t" + rno + "�� " + sno + "���� ����Ͻðڽ��ϱ�?");
		printUtil.blank(2);
		printUtil.bar();
		System.out.println("\t\t\t\t0. �ڷΰ���");
		System.out.print("\t\t���� (Y/N) >> ");
		select = ScanUtil.nextLine();
		
		
		//**********************
		if (select.equalsIgnoreCase("y")) {
			sql = "UPDATE SEAT SET SEAT_REV='F' WHERE SEAT_RNO = " + rno + "AND SEAT_SNO = " + sno;
			rs = jdbc.update(sql);
			if ( rs == 1 ) {
				System.out.println("\t�¼���򼺰�");
			};// rs�� 1�� ������ ���� �� �Ȱ�.. ǥ��?
			sql ="UPDATE RESERVATION SET REV_OUT = TO_CHAR(SYSDATE,'YYMMDDHH24MI') WHERE REV_OUT IS NULL AND MEM_ID=\'" + memID + "\' ";
			rs = jdbc.update(sql);
			if ( rs == 1 ) {
				System.out.println("\t��Ǳ�ϻ�������");
			}
			Controller.sessionStorage.remove("SEAT_RNO");
			Controller.sessionStorage.remove("SEAT_SNO");
			System.out.println("\t����� �Ϸ�Ǿ����ϴ�. ó�� ȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
			return View.HOME;
			//*********************
		} else if (select.equalsIgnoreCase("n")) {
			System.out.println("\t����� ����ϰ� ���� ùȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		} else if (select.equals("0")) {//**********
//			reservation();
			return View.RESERVATION;
		} 
		else {
			System.out.println("\t�߸� �Է��ϼ̽��ϴ�. ���� ùȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
//			reservation();
			return View.RESERVATION;
		}
	}
}