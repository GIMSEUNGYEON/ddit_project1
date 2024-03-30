package service;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import Controller.Controller;
import DAO.MemberDAO;
import util.ScanUtil;
import util.PrintUtil;
import View.View;


public class MemberService {
	
	private static MemberService instance = null;
	PrintUtil printUtil = new PrintUtil();
	private MemberService() {		
	}
	
	public static MemberService getInstance() {
		if(instance==null)
			instance= new MemberService();
		return instance;
		
	}
	
	MemberDAO memberDao=MemberDAO.getInstance();
	
	public int signUp() {
		printUtil.bar();
		printUtil.blank(1);
		System.out.println("\t\t== ȸ������ ==");
		System.out.println("\t\t1. ���ȸ������  \n\n\t\t2. ������ȸ������");
		printUtil.blank(1);
		printUtil.bar();
		
		
		System.out.print("��ȣ�Է�: ");
		int choice=0;
		choice=ScanUtil.nextInt();
		
		if(choice==1) {
		//���̵�
		String id="";
		while (true) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
	         System.out.println(" * ���̵� �Է� [ ���������� �ʼ� / 5���� �̻� �Է�]");
	         System.out.print(" >> ");
	         id = ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationID(id))
	            break;
	      }
		Map<String, Object> result1 = memberDao.isOverapID(id);
        if (result1 != null) {
           System.out.println();
           System.out.println("     >> �̹� ��ϵ� ���̵� �Դϴ�! <<");
        } else {
         
		//��й�ȣ
		String pw="";
		 while (true) {
			 System.out.println(" ������������������������������������������������������������������������������������������������������");
	         System.out.println(" * ��й�ȣ �Է� [ 4���� �̻� �Է�]");
	         System.out.print(" >> ");
	         pw= ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationPW(pw))
	            break;
	      }
	     //�̸�
		String name="";
		 while (true) {
			 System.out.println(" ������������������������������������������������������������������������������������������������������");
	         System.out.println(" * �̸� �Է� [ �ѱۤ����� �Է�]");
	         System.out.print(" >> ");
	         name=ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationName(name))
	            break;
		 }
		 //��ȭ��ȣ
		
		String hp="";
		  while (true) {
			  System.out.println(" ������������������������������������������������������������������������������������������������������");
		         System.out.println(" * ��ȭ��ȣ �Է� [ ex) 01012345678 ]");
		         System.out.print(" >> ");
		         hp = ScanUtil.nextLine();
		         System.out.println();
		         if (normalizationTel(hp))
		            break;
		      }
		if(hp!=null) {
			hp=hp.replaceAll(Pattern.quote("-")," ");
			if (hp.length() == 11) {
				// 010-1234-1234
				hp = hp.substring(0, 3) + "-" + hp.substring(3, 7) + "-" + hp.substring(7);

			} 
			}
		 
		
		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(hp);
		
		String admin="F";
		param.add(admin);
		
		int result=memberDao.signUp(param);
		if(result>0) {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
		System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�. �α���ȭ������ �̵��մϴ�.");
		ScanUtil.nextLine();
		return View.MEMBER_LOGIN;
		}else {
			System.out.println(" ������������������������������������������������������������������������������������������������������");
			System.out.println("ȸ�����Կ� �����߽��ϴ�. �ٽ� ȸ�������Ͻðڽ��ϱ�?");
			System.out.println("\t\t[y/n]");
			String selectYN = ScanUtil.nextLine();
			if(selectYN.equalsIgnoreCase("y")) {
				return View.MEMBER_SIGNUP;
			}else if(selectYN.equalsIgnoreCase("n")) {
				System.out.println("����ȭ������ �̵��մϴ�.");
				ScanUtil.nextLine();
				return View.MAIN;
			}else {
				System.out.println("�߸��� �Է��Դϴ�.");
				ScanUtil.nextLine();
				return View.MAIN;
			}
//			return 
		}
		}
		
		} else if(choice==2) {
			String make="maker1004";
			System.out.print("������ ���� ��й�ȣ :  ");
			String makepw=ScanUtil.nextLine();
			if(makepw.equals(make)) {
				System.out.println("������ ȸ������");
				//���̵�
				String id="";
				while (true) {
					System.out.println(" ������������������������������������������������������������������������������������������������������");
			         System.out.println(" * ���̵� �Է� [ ���������� �ʼ� / 5���� �̻� �Է�]");
			         System.out.print(" >> ");
			         id = ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationID(id))
			            break;
			      }
				Map<String, Object> result1 = memberDao.isOverapID(id);
		        if (result1 != null) {
		           System.out.println();
		           System.out.println(" ������������������������������������������������������������������������������������������������������");
		           System.out.println("     >> �̹� ��ϵ� ���̵� �Դϴ�! <<");
		           ScanUtil.nextLine();
		           return View.MEMBER_SIGNUP;
		        } else {
				//��й�ȣ
				String pw="";
				 while (true) {
					 System.out.println(" ������������������������������������������������������������������������������������������������������");
			         System.out.println(" * ��й�ȣ �Է� [ 4���� �̻� �Է�]");
			         System.out.print(" >> ");
			         pw= ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationPW(pw))
			            break;
			      }
			     //�̸�
				String name="";
				 while (true) {
					 System.out.println(" ������������������������������������������������������������������������������������������������������");
			         System.out.println(" * �̸� �Է� [ �ѱۤ����� �Է�]");
			         System.out.print(" >> ");
			         name=ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationName(name))
			            break;
				 }
				 //��ȭ��ȣ
				
				String hp="";
				  while (true) {
					  System.out.println(" ������������������������������������������������������������������������������������������������������");
				         System.out.println(" * ��ȭ��ȣ �Է� [ ex) 01012345678 ]");
				         System.out.print(" >> ");
				         hp = ScanUtil.nextLine();
				         System.out.println();
				         if (normalizationTel(hp))
				            break;
				      }
				if(hp!=null) {
					hp=hp.replaceAll(Pattern.quote("-")," ");
					if (hp.length() == 11) {
						// 010-1234-1234
						hp = hp.substring(0, 3) + "-" + hp.substring(3, 7) + "-" + hp.substring(7);

					} 
					}
				
				String admin="T";
				
				List<Object> param = new ArrayList<>();
				param.add(id);
				param.add(pw);
				param.add(name);
				param.add(hp);
				param.add(admin);
				
				int result=memberDao.signUp(param);
				if(result>0) {
				System.out.println("������ ������ �Ϸ�Ǿ����ϴ�. �α���ȭ������ ���ư��ϴ�.");
				ScanUtil.nextLine();
				return View.MEMBER_LOGIN;
						}
	        		}
				}else {
					System.out.println("������ ���� ��й�ȣ�� Ʋ�Ƚ��ϴ�. Ȩ���� �̵��մϴ�.");
					ScanUtil.nextLine();
			}
	
		}
		return View.MAIN; //�ߺ�ó������
	}

// �̸� ����ȭ true : ����ȭ �� | false : �ٽ� �Է�
	   private boolean normalizationName(String name) {
	      boolean result = true;
	      if (!name.matches("^[a-zA-Z��-�R]*$")) {
	         System.out.println(" * ���ڸ� �Է°����մϴ�.");
	         result = false;
	      } else if (name.length() < 2) {
	         System.out.println(" * �ùٸ� �Է��� �ƴմϴ�.");
	         result = false;
	      } else
	         return result;
	      System.out.println();
	      System.out.println(" * �ٽ� �Է��ϼ���.");
	      System.out.println();
	      return result;
	   }
	   // ���̵� ����ȭ true : ����ȭ �� | false : �ٽ� �Է�
	   private boolean normalizationID(String id) {
	      boolean result = true;
	      if (id.length() < 4) {
	         System.out.println(" * ���̵�� 4�ڸ� �̻��Դϴ�.");
	         result = false;
	      } else {
	         if (id.matches("^[a-zA-Z0-9]*$")) {
	            if (id.matches("^[a-zA-Z]*$")) {
	               System.out.println(" * ���ڸ� �ʼ��� �Է��ؾ� �մϴ�.");
	               result = false;
	            } else if (id.matches("^[0-9]*$")) {
	               System.out.println(" * ��� �ʼ��� �Է��ؾ� �մϴ�.");
	               result = false;
	            }
	         } else {
	            System.out.println(" * ���������ڸ� �Է°��� �մϴ�.");
	            result = false;
	         }
	      }
	      if (result)
	         return result;
	      System.out.println();
	      System.out.println(" * �ٽ� �Է��ϼ���.");
	      System.out.println();
	      return result;
	   }
	   // ��ȣ ����ȭ true : ����ȭ �� | false : �ٽ� �Է�
	   private boolean normalizationPW(String pw) {
	      if (pw.length() < 4) {
	         System.out.println(" * ��й�ȣ�� 4�ڸ� �̻��Դϴ�.");
	         System.out.println();
	         System.out.println(" * �ٽ� �Է��ϼ���. ");
	         System.out.println();
	         return false;
	      } else
	         return true;
	   }
	   // ��ȭ��ȣ ����ȭ true: ����ȭ �Ϸ� | false: �ٽ� �Է�
	   private boolean normalizationTel(String hp) {
	       boolean result = true;
	       if (!(hp.matches("^[0-9]*$"))) {
	           System.out.println(" * ���ڸ� �Է� �����մϴ�.");
	           result = false;
	       } else if (!hp.matches("^0[0-9]{10}$")) {
	           System.out.println(" * �ùٸ� ��ȭ��ȣ ������ �ƴմϴ�.");
	           result = false;
	       } else {
	           return result;
	       }
	       System.out.println();
	       System.out.println(" * �ٽ� �Է��ϼ���.");
	       System.out.println();
	       return result;
	   }

	
	public int login() {
		printUtil.bar2();
		printUtil.blank(2);	
		System.out.println("\t\t== �α��� ==");
		System.out.println("\t\t���̵� >> ");
		System.out.print("\t\t��й�ȣ >> ");
		printUtil.blank(4);	
		printUtil.bar2();
		System.out.print("\n���̵� �Է� : ");
		String id = ScanUtil.nextLine();
		System.out.print("��й�ȣ �Է� : ");
		String pw = ScanUtil.nextLine();
		Map<String, Object> row = memberDao.login(id, pw);
		if(row == null) {
			System.out.println("���̵� �Ǵ� ��й�ȣ �Է��� �߸��Ǿ����ϴ�! �ٽ� �α������ּ���.");	
			ScanUtil.nextLine();
			return View.MEMBER_LOGIN;//ùȭ��!
		}else {
			
			Controller.sessionStorage.put("login", true);
			Controller.sessionStorage.put("loginInfo",row);
			String TF=(String) row.get("MEM_ADMIN");
			if(TF.equals("T")) {
				System.out.println("������ "+row.get("MEM_NAME") + "�� ȯ���մϴ�! ����Ϸ��� ���͸� �Է��ϼ���.");
				ScanUtil.nextLine();
				return View.HOME;
	         }else {
	        	 System.out.println("�Ϲ� ȸ�� "+row.get("MEM_NAME") + "�� ȯ���մϴ�! ����Ϸ��� ���͸� �Է��ϼ���.");
	        	 ScanUtil.nextLine();
	        	 return View.HOME; //�α��� �Ŀ� â
	         }
		}
	}
	
	public int guest() {
		String guestId = "guest";
		String guestPW = "guest";
		Map<String, Object> row = memberDao.login(guestId, guestPW);
		Controller.sessionStorage.put("login", true);
		Controller.sessionStorage.put("loginInfo",row);
		System.out.println("�Խ�Ʈ ���� �α����ϼ̽��ϴ�. �Ϻ� ����� ���ѵ˴ϴ�.");
		ScanUtil.nextLine();
		return View.HOME;
	}
	
	public int logout() {
		System.out.println("�α׾ƿ�");
		System.out.println("�α׾ƿ��� �Ͻðڽ��ϱ�? (y/n)");
		String flag=ScanUtil.nextLine();
		if(flag.equalsIgnoreCase("y")) {
			Object o = Controller.sessionStorage.get("loginInfo");
	         Map<String, Object> loginInfo = (Map<String, Object>) o;
			System.out.println("�α׾ƿ��� �Ϸ�Ǿ����ϴ�. ���͸� �Է��ϸ� ����ȭ������ ���ư��ϴ�.");
			Controller.sessionStorage.put("login", false);
	        Object res1=Controller.sessionStorage.get("login");

	        if((boolean)res1==false) Controller.sessionStorage.put("loginInfo", null);

			ScanUtil.nextLine();
			return View.MAIN; //�α��� ��ȭ��(ùȭ��)
		}else if(flag.equalsIgnoreCase("n")){
			System.out.println("���͸� �Է��ϸ� Ȩȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
			return View.HOME; //�޴� ȭ��!
		}else {
			System.err.println("�߸��� �Է°��Դϴ�. Ȩȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
			return View.HOME; //�޴� ȭ��!
			
		}
		
		
	}
	
	public int inquiry() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
		if(memID.equals("guest")) {
			System.out.println("�Խ�Ʈ�� �������� ��ȸ�� �Ұ����մϴ�. Ȩ���� ���ư��ϴ�.");
			ScanUtil.nextLine();
			return View.HOME;
		}
		
		String sql="SELECT MEM_ID ���̵�, MEM_NAME �̸�, MEM_HP ��ȭ��ȣ FROM MEMBER WHERE MEM_ID='"+memID+"'";
		
		Map<String, Object> result=memberDao.inquiry(sql);
		
		System.out.println();
	    printUtil.bar();
	    System.out.print("\t\t== ȸ�� ����==\n");
	    printUtil.blank(1);
	      for(String key : result.keySet()) {
	         System.out.println(key + " : \t" + result.get(key));
	      }
		
		System.out.println();
		System.out.println("1.ȸ����������  2.ȸ��Ż�� 0.�ڷΰ���");
		printUtil.bar();
		System.out.print("��ȣ�Է�>>");
		int select = ScanUtil.nextInt();
		
		switch(select) {
		case 0 : return View.HOME;
		case 1 : return View.MEMBER_UPDATE;
		case 2 : return View.MEMBER_RESIGN;
		default : return View.ERROR;
		}
		
	}
	
	public int infoUpdate() {
		
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
	
		String sqlStr="UPDATE MEMBER SET " ;
		
		printUtil.blank(2);
		printUtil.bar2();
		System.out.println("\n\t\t== ȸ������ ���� ==");
		printUtil.blank(1);
		System.out.println("1.��й�ȣ ���� \n2.�̸� ���� \n3.��ȭ��ȣ ���� \n0.�ڷΰ���"  );
		printUtil.blank(1);
		printUtil.bar();
		System.out.print("��ȣ�Է�>> ");
		switch(ScanUtil.nextInt()) {
		case 0 : 
			System.out.println("Ȩȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
			return View.HOME;
			
		case 1: 
		printUtil.bar2();
		printUtil.blank(1);
		System.out.println("\n\t\t== ��й�ȣ���� ==");
		printUtil.blank(3);
		printUtil.bar();
		System.out.print("�ű� ��й�ȣ : ");
		String pw=ScanUtil.nextLine();
		String sql=sqlStr+"MEM_PW = '"+ pw +"' WHERE MEM_ID = '"+memID+"'";
	
		int result=memberDao.update(sql);
		
		if(result>0) {
		System.out.println("��й�ȣ ������ �Ϸ�Ǿ����ϴ�. �α��� ȭ������ ���ư��ϴ�.");
		Controller.sessionStorage.put("login", false);
	    Controller.sessionStorage.put("loginInfo",null);
		ScanUtil.nextLine();
		}
		return View.MEMBER_LOGIN;  
		
		
		case 2:
		printUtil.bar2();
		printUtil.blank(1);
		System.out.println("\n\t\t== �̸����� ==");
		printUtil.blank(3);
		printUtil.bar();
		System.out.print("�ű� �̸�: ");
		String name=ScanUtil.nextLine();
		sql=sqlStr+"MEM_NAME= '"+name+"' WHERE MEM_ID= '"+memID+"'";
		
		int result1=memberDao.update(sql);
		
		if(result1>0) {
			System.out.println("ȸ�� ������ ������Ʈ �Ǿ����ϴ�. Ȩȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
		}else {
			System.out.println("�߸��� �Է��Դϴ�! Ȩȭ������ ���ư��ϴ�.");
		}
		return View.HOME;
		
		case 3:
			printUtil.bar2();
			printUtil.blank(1);
			System.out.println("\n\t\t== ��ȭ��ȣ���� ==");
			printUtil.blank(3);
			printUtil.bar();
		System.out.print("�ű� ��ȭ��ȣ: "); 
		String hp=ScanUtil.nextLine();
		sql=sqlStr+"MEM_HP='"+hp+"' WHERE MEM_ID='"+memID+"'";
	
		int result2=memberDao.update(sql);
		if(result2>0) {
			System.out.println("ȸ�� ������ ������Ʈ �Ǿ����ϴ�. Ȩȭ������ ���ư��ϴ�.");
			ScanUtil.nextLine();
		}
		
		}
		return View.HOME;

	}
	public int resign() {
		
	
		
		System.out.println("������絵������ ȸ��Ż�� �Ͻðڽ��ϱ�? (Y/N)");
		String flag=ScanUtil.nextLine();
		if(flag.equalsIgnoreCase("y")) {
		
			Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
			String memID = (String) map.get("MEM_ID");
			
			String sql= "DELETE MEMBER WHERE MEM_ID='"+memID+"'";
			int result=memberDao.resign(sql);
			System.out.println(sql);
			
			
			if(result>0) {
				System.out.println("ȸ�� ������ �����Ǿ����ϴ�. ");
			}
		}
		return View.HOME;
		
	}
	
	public int admin_inquiry() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
		
		String sql="SELECT MEM_ID ���̵�, MEM_NAME �̸�, MEM_HP ��ȭ��ȣ FROM MEMBER WHERE MEM_ID='"+memID+"'";
		
		List<Map<String, Object>> memberList=memberDao.adminInquiry(sql);
		
             for (Map<String, Object> list : memberList) {
             System.out.print(list.get("���̵�"));
             System.out.print("\t" + list.get("�̸�"));
             System.out.print("\t" + list.get("��ȭ��ȣ"));
             System.out.println();
          }
		System.out.println();
	    printUtil.bar();
	    System.out.print("\t\t== ȸ�� ����==\n");
	    printUtil.blank(1);
	    
		System.out.print("0. �ڷ� ���� ");
		int select = ScanUtil.nextInt();
		
		if(select == 0) {
			ScanUtil.nextLine();
			return View.MEMBER_INQUIRY;
		}else {
			return View.ERROR;
		}
		
	}

}
