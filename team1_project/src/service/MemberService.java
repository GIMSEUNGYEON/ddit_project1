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
		System.out.println("\t\t== �蛾灠㊣� ==");
		System.out.println("\t\t1. 詹幗�蛾灠㊣�  \n\n\t\t2. 婦葬濠�蛾灠㊣�");
		printUtil.blank(1);
		printUtil.bar();
		
		
		System.out.print("廓��殮溘: ");
		int choice=0;
		choice=ScanUtil.nextInt();
		
		if(choice==1) {
		//嬴檜蛤
		String id="";
		while (true) {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
	         System.out.println(" * 嬴檜蛤 殮溘 [ 艙僥王璋濠 в熱 / 5旋濠 檜鼻 殮溘]");
	         System.out.print(" >> ");
	         id = ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationID(id))
	            break;
	      }
		Map<String, Object> result1 = memberDao.isOverapID(id);
        if (result1 != null) {
           System.out.println();
           System.out.println("     >> 檜嘐 蛔煙脹 嬴檜蛤 殮棲棻! <<");
        } else {
         
		//綠塵廓��
		String pw="";
		 while (true) {
			 System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
	         System.out.println(" * 綠塵廓�� 殮溘 [ 4旋濠 檜鼻 殮溘]");
	         System.out.print(" >> ");
	         pw= ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationPW(pw))
	            break;
	      }
	     //檜葷
		String name="";
		 while (true) {
			 System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
	         System.out.println(" * 檜葷 殮溘 [ и旋王艙僥 殮溘]");
	         System.out.print(" >> ");
	         name=ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationName(name))
	            break;
		 }
		 //瞪�食醽�
		
		String hp="";
		  while (true) {
			  System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		         System.out.println(" * 瞪�食醽� 殮溘 [ ex) 01012345678 ]");
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
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("�蛾灠㊣埬� 諫猿腎歷蝗棲棻. 煎斜檣�飛橉虞� 檜翕м棲棻.");
		ScanUtil.nextLine();
		return View.MEMBER_LOGIN;
		}else {
			System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("�蛾灠㊣埥� 褒ぬц蝗棲棻. 棻衛 �蛾灠㊣堌牮簸睍懂炱�?");
			System.out.println("\t\t[y/n]");
			String selectYN = ScanUtil.nextLine();
			if(selectYN.equalsIgnoreCase("y")) {
				return View.MEMBER_SIGNUP;
			}else if(selectYN.equalsIgnoreCase("n")) {
				System.out.println("詭檣�飛橉虞� 檜翕м棲棻.");
				ScanUtil.nextLine();
				return View.MAIN;
			}else {
				System.out.println("澀跤脹 殮溘殮棲棻.");
				ScanUtil.nextLine();
				return View.MAIN;
			}
//			return 
		}
		}
		
		} else if(choice==2) {
			String make="maker1004";
			System.out.print("婦葬濠 儅撩 綠塵廓�� :  ");
			String makepw=ScanUtil.nextLine();
			if(makepw.equals(make)) {
				System.out.println("婦葬濠 �蛾灠㊣�");
				//嬴檜蛤
				String id="";
				while (true) {
					System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			         System.out.println(" * 嬴檜蛤 殮溘 [ 艙僥王璋濠 в熱 / 5旋濠 檜鼻 殮溘]");
			         System.out.print(" >> ");
			         id = ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationID(id))
			            break;
			      }
				Map<String, Object> result1 = memberDao.isOverapID(id);
		        if (result1 != null) {
		           System.out.println();
		           System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		           System.out.println("     >> 檜嘐 蛔煙脹 嬴檜蛤 殮棲棻! <<");
		           ScanUtil.nextLine();
		           return View.MEMBER_SIGNUP;
		        } else {
				//綠塵廓��
				String pw="";
				 while (true) {
					 System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			         System.out.println(" * 綠塵廓�� 殮溘 [ 4旋濠 檜鼻 殮溘]");
			         System.out.print(" >> ");
			         pw= ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationPW(pw))
			            break;
			      }
			     //檜葷
				String name="";
				 while (true) {
					 System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			         System.out.println(" * 檜葷 殮溘 [ и旋王艙僥 殮溘]");
			         System.out.print(" >> ");
			         name=ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationName(name))
			            break;
				 }
				 //瞪�食醽�
				
				String hp="";
				  while (true) {
					  System.out.println(" 式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				         System.out.println(" * 瞪�食醽� 殮溘 [ ex) 01012345678 ]");
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
				System.out.println("婦葬濠 儅撩檜 諫猿腎歷蝗棲棻. 煎斜檣�飛橉虞� 給嬴骨棲棻.");
				ScanUtil.nextLine();
				return View.MEMBER_LOGIN;
						}
	        		}
				}else {
					System.out.println("婦葬濠 儅撩 綠塵廓�ㄟ� ぎ溜蝗棲棻. �阱虞� 檜翕м棲棻.");
					ScanUtil.nextLine();
			}
	
		}
		return View.MAIN; //醞犒籀葬蕨諼
	}

// 檜葷 薑敘�� true : 薑敘�� 諫 | false : 棻衛 殮溘
	   private boolean normalizationName(String name) {
	      boolean result = true;
	      if (!name.matches("^[a-zA-Z陛-鼇]*$")) {
	         System.out.println(" * 僥濠虜 殮溘陛棟м棲棻.");
	         result = false;
	      } else if (name.length() < 2) {
	         System.out.println(" * 螢夥艇 殮溘檜 嬴椎棲棻.");
	         result = false;
	      } else
	         return result;
	      System.out.println();
	      System.out.println(" * 棻衛 殮溘ж撮蹂.");
	      System.out.println();
	      return result;
	   }
	   // 嬴檜蛤 薑敘�� true : 薑敘�� 諫 | false : 棻衛 殮溘
	   private boolean normalizationID(String id) {
	      boolean result = true;
	      if (id.length() < 4) {
	         System.out.println(" * 嬴檜蛤朝 4濠葬 檜鼻殮棲棻.");
	         result = false;
	      } else {
	         if (id.matches("^[a-zA-Z0-9]*$")) {
	            if (id.matches("^[a-zA-Z]*$")) {
	               System.out.println(" * 璋濠蒂 в熱煎 殮溘п撿 м棲棻.");
	               result = false;
	            } else if (id.matches("^[0-9]*$")) {
	               System.out.println(" * 艙橫蒂 в熱煎 殮溘п撿 м棲棻.");
	               result = false;
	            }
	         } else {
	            System.out.println(" * 艙僥王璋濠虜 殮溘陛棟 м棲棻.");
	            result = false;
	         }
	      }
	      if (result)
	         return result;
	      System.out.println();
	      System.out.println(" * 棻衛 殮溘ж撮蹂.");
	      System.out.println();
	      return result;
	   }
	   // 懍�� 薑敘�� true : 薑敘�� 諫 | false : 棻衛 殮溘
	   private boolean normalizationPW(String pw) {
	      if (pw.length() < 4) {
	         System.out.println(" * 綠塵廓�ㄣ� 4濠葬 檜鼻殮棲棻.");
	         System.out.println();
	         System.out.println(" * 棻衛 殮溘ж撮蹂. ");
	         System.out.println();
	         return false;
	      } else
	         return true;
	   }
	   // 瞪�食醽� 薑敘�� true: 薑敘�� 諫猿 | false: 棻衛 殮溘
	   private boolean normalizationTel(String hp) {
	       boolean result = true;
	       if (!(hp.matches("^[0-9]*$"))) {
	           System.out.println(" * 璋濠虜 殮溘 陛棟м棲棻.");
	           result = false;
	       } else if (!hp.matches("^0[0-9]{10}$")) {
	           System.out.println(" * 螢夥艇 瞪�食醽� ⑽衝檜 嬴椎棲棻.");
	           result = false;
	       } else {
	           return result;
	       }
	       System.out.println();
	       System.out.println(" * 棻衛 殮溘ж撮蹂.");
	       System.out.println();
	       return result;
	   }

	
	public int login() {
		printUtil.bar2();
		printUtil.blank(2);	
		System.out.println("\t\t== 煎斜檣 ==");
		System.out.println("\t\t嬴檜蛤 >> ");
		System.out.print("\t\t綠塵廓�� >> ");
		printUtil.blank(4);	
		printUtil.bar2();
		System.out.print("\n嬴檜蛤 殮溘 : ");
		String id = ScanUtil.nextLine();
		System.out.print("綠塵廓�� 殮溘 : ");
		String pw = ScanUtil.nextLine();
		Map<String, Object> row = memberDao.login(id, pw);
		if(row == null) {
			System.out.println("嬴檜蛤 傳朝 綠塵廓�� 殮溘檜 澀跤腎歷蝗棲棻! 棻衛 煎斜檣п輿撮蹂.");	
			ScanUtil.nextLine();
			return View.MEMBER_LOGIN;//羅�飛�!
		}else {
			
			Controller.sessionStorage.put("login", true);
			Controller.sessionStorage.put("loginInfo",row);
			String TF=(String) row.get("MEM_ADMIN");
			if(TF.equals("T")) {
				System.out.println("婦葬濠 "+row.get("MEM_NAME") + "椒 �紊腎桭炴�! 啗樓ж溥賊 縛攪蒂 殮溘ж撮蹂.");
				ScanUtil.nextLine();
				return View.HOME;
	         }else {
	        	 System.out.println("橾奩 �蛾� "+row.get("MEM_NAME") + "椒 �紊腎桭炴�! 啗樓ж溥賊 縛攪蒂 殮溘ж撮蹂.");
	        	 ScanUtil.nextLine();
	        	 return View.HOME; //煎斜檣 �醴� 璽
	         }
		}
	}
	
	public int guest() {
		String guestId = "guest";
		String guestPW = "guest";
		Map<String, Object> row = memberDao.login(guestId, guestPW);
		Controller.sessionStorage.put("login", true);
		Controller.sessionStorage.put("loginInfo",row);
		System.out.println("啪蝶お 賅萄煎 煎斜檣ж樟蝗棲棻. 橾睡 晦棟檜 薯и腌棲棻.");
		ScanUtil.nextLine();
		return View.HOME;
	}
	
	public int logout() {
		System.out.println("煎斜嬴醒");
		System.out.println("煎斜嬴醒擊 ж衛啊蝗棲梱? (y/n)");
		String flag=ScanUtil.nextLine();
		if(flag.equalsIgnoreCase("y")) {
			Object o = Controller.sessionStorage.get("loginInfo");
	         Map<String, Object> loginInfo = (Map<String, Object>) o;
			System.out.println("煎斜嬴醒檜 諫猿腎歷蝗棲棻. 縛攪蒂 殮溘ж賊 詭檣�飛橉虞� 給嬴骨棲棻.");
			Controller.sessionStorage.put("login", false);
	        Object res1=Controller.sessionStorage.get("login");

	        if((boolean)res1==false) Controller.sessionStorage.put("loginInfo", null);

			ScanUtil.nextLine();
			return View.MAIN; //煎斜檣 瞪�飛�(羅�飛�)
		}else if(flag.equalsIgnoreCase("n")){
			System.out.println("縛攪蒂 殮溘ж賊 �亞飛橉虞� 給嬴骨棲棻.");
			ScanUtil.nextLine();
			return View.HOME; //詭景 �飛�!
		}else {
			System.err.println("澀跤脹 殮溘高殮棲棻. �亞飛橉虞� 給嬴骨棲棻.");
			ScanUtil.nextLine();
			return View.HOME; //詭景 �飛�!
			
		}
		
		
	}
	
	public int inquiry() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
		if(memID.equals("guest")) {
			System.out.println("啪蝶お朝 偃檣薑爾 褻�萼� 碳陛棟м棲棻. �阱虞� 給嬴骨棲棻.");
			ScanUtil.nextLine();
			return View.HOME;
		}
		
		String sql="SELECT MEM_ID 嬴檜蛤, MEM_NAME 檜葷, MEM_HP 瞪�食醽� FROM MEMBER WHERE MEM_ID='"+memID+"'";
		
		Map<String, Object> result=memberDao.inquiry(sql);
		
		System.out.println();
	    printUtil.bar();
	    System.out.print("\t\t== �蛾� 薑爾==\n");
	    printUtil.blank(1);
	      for(String key : result.keySet()) {
	         System.out.println(key + " : \t" + result.get(key));
	      }
		
		System.out.println();
		System.out.println("1.�蛾讔內蜈鶬�  2.�蛾躠酷� 0.菴煎陛晦");
		printUtil.bar();
		System.out.print("廓��殮溘>>");
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
		System.out.println("\n\t\t== �蛾讔內� 熱薑 ==");
		printUtil.blank(1);
		System.out.println("1.綠塵廓�� 滲唳 \n2.檜葷 滲唳 \n3.瞪�食醽� 滲唳 \n0.菴煎陛晦"  );
		printUtil.blank(1);
		printUtil.bar();
		System.out.print("廓��殮溘>> ");
		switch(ScanUtil.nextInt()) {
		case 0 : 
			System.out.println("�亞飛橉虞� 給嬴骨棲棻.");
			ScanUtil.nextLine();
			return View.HOME;
			
		case 1: 
		printUtil.bar2();
		printUtil.blank(1);
		System.out.println("\n\t\t== 綠塵廓�ㄩ秣� ==");
		printUtil.blank(3);
		printUtil.bar();
		System.out.print("褐敘 綠塵廓�� : ");
		String pw=ScanUtil.nextLine();
		String sql=sqlStr+"MEM_PW = '"+ pw +"' WHERE MEM_ID = '"+memID+"'";
	
		int result=memberDao.update(sql);
		
		if(result>0) {
		System.out.println("綠塵廓�� 滲唳檜 諫猿腎歷蝗棲棻. 煎斜檣 �飛橉虞� 給嬴骨棲棻.");
		Controller.sessionStorage.put("login", false);
	    Controller.sessionStorage.put("loginInfo",null);
		ScanUtil.nextLine();
		}
		return View.MEMBER_LOGIN;  
		
		
		case 2:
		printUtil.bar2();
		printUtil.blank(1);
		System.out.println("\n\t\t== 檜葷滲唳 ==");
		printUtil.blank(3);
		printUtil.bar();
		System.out.print("褐敘 檜葷: ");
		String name=ScanUtil.nextLine();
		sql=sqlStr+"MEM_NAME= '"+name+"' WHERE MEM_ID= '"+memID+"'";
		
		int result1=memberDao.update(sql);
		
		if(result1>0) {
			System.out.println("�蛾� 薑爾陛 機等檜お 腎歷蝗棲棻. �亞飛橉虞� 給嬴骨棲棻.");
			ScanUtil.nextLine();
		}else {
			System.out.println("澀跤脹 殮溘殮棲棻! �亞飛橉虞� 給嬴骨棲棻.");
		}
		return View.HOME;
		
		case 3:
			printUtil.bar2();
			printUtil.blank(1);
			System.out.println("\n\t\t== 瞪�食醽ㄩ秣� ==");
			printUtil.blank(3);
			printUtil.bar();
		System.out.print("褐敘 瞪�食醽�: "); 
		String hp=ScanUtil.nextLine();
		sql=sqlStr+"MEM_HP='"+hp+"' WHERE MEM_ID='"+memID+"'";
	
		int result2=memberDao.update(sql);
		if(result2>0) {
			System.out.println("�蛾� 薑爾陛 機等檜お 腎歷蝗棲棻. �亞飛橉虞� 給嬴骨棲棻.");
			ScanUtil.nextLine();
		}
		
		}
		return View.HOME;

	}
	public int resign() {
		
	
		
		System.out.println("渠湯檣營紫憮婦曖 �蛾躠酷薷� ж衛啊蝗棲梱? (Y/N)");
		String flag=ScanUtil.nextLine();
		if(flag.equalsIgnoreCase("y")) {
		
			Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
			String memID = (String) map.get("MEM_ID");
			
			String sql= "DELETE MEMBER WHERE MEM_ID='"+memID+"'";
			int result=memberDao.resign(sql);
			System.out.println(sql);
			
			
			if(result>0) {
				System.out.println("�蛾� 薑爾陛 餉薯腎歷蝗棲棻. ");
			}
		}
		return View.HOME;
		
	}
	
	public int admin_inquiry() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
		
		String sql="SELECT MEM_ID 嬴檜蛤, MEM_NAME 檜葷, MEM_HP 瞪�食醽� FROM MEMBER WHERE MEM_ID='"+memID+"'";
		
		List<Map<String, Object>> memberList=memberDao.adminInquiry(sql);
		
             for (Map<String, Object> list : memberList) {
             System.out.print(list.get("嬴檜蛤"));
             System.out.print("\t" + list.get("檜葷"));
             System.out.print("\t" + list.get("瞪�食醽�"));
             System.out.println();
          }
		System.out.println();
	    printUtil.bar();
	    System.out.print("\t\t== �蛾� 薑爾==\n");
	    printUtil.blank(1);
	    
		System.out.print("0. 菴煎 陛晦 ");
		int select = ScanUtil.nextInt();
		
		if(select == 0) {
			ScanUtil.nextLine();
			return View.MEMBER_INQUIRY;
		}else {
			return View.ERROR;
		}
		
	}

}
