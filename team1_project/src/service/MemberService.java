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
		System.out.println("\t\t== È¸¿ø°¡ÀÔ ==");
		System.out.println("\t\t1. ¸â¹öÈ¸¿ø°¡ÀÔ  \n\n\t\t2. °ü¸®ÀÚÈ¸¿ø°¡ÀÔ");
		printUtil.blank(1);
		printUtil.bar();
		
		
		System.out.print("¹øÈ£ÀÔ·Â: ");
		int choice=0;
		choice=ScanUtil.nextInt();
		
		if(choice==1) {
		//¾ÆÀÌµð
		String id="";
		while (true) {
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
	         System.out.println(" * ¾ÆÀÌµð ÀÔ·Â [ ¿µ¹®¤ý¼ýÀÚ ÇÊ¼ö / 5±ÛÀÚ ÀÌ»ó ÀÔ·Â]");
	         System.out.print(" >> ");
	         id = ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationID(id))
	            break;
	      }
		Map<String, Object> result1 = memberDao.isOverapID(id);
        if (result1 != null) {
           System.out.println();
           System.out.println("     >> ÀÌ¹Ì µî·ÏµÈ ¾ÆÀÌµð ÀÔ´Ï´Ù! <<");
        } else {
         
		//ºñ¹Ð¹øÈ£
		String pw="";
		 while (true) {
			 System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
	         System.out.println(" * ºñ¹Ð¹øÈ£ ÀÔ·Â [ 4±ÛÀÚ ÀÌ»ó ÀÔ·Â]");
	         System.out.print(" >> ");
	         pw= ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationPW(pw))
	            break;
	      }
	     //ÀÌ¸§
		String name="";
		 while (true) {
			 System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
	         System.out.println(" * ÀÌ¸§ ÀÔ·Â [ ÇÑ±Û¤ý¿µ¹® ÀÔ·Â]");
	         System.out.print(" >> ");
	         name=ScanUtil.nextLine();
	         System.out.println();
	         if (normalizationName(name))
	            break;
		 }
		 //ÀüÈ­¹øÈ£
		
		String hp="";
		  while (true) {
			  System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		         System.out.println(" * ÀüÈ­¹øÈ£ ÀÔ·Â [ ex) 01012345678 ]");
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
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("È¸¿ø°¡ÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù. ·Î±×ÀÎÈ­¸éÀ¸·Î ÀÌµ¿ÇÕ´Ï´Ù.");
		ScanUtil.nextLine();
		return View.MEMBER_LOGIN;
		}else {
			System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("È¸¿ø°¡ÀÔ¿¡ ½ÇÆÐÇß½À´Ï´Ù. ´Ù½Ã È¸¿ø°¡ÀÔÇÏ½Ã°Ú½À´Ï±î?");
			System.out.println("\t\t[y/n]");
			String selectYN = ScanUtil.nextLine();
			if(selectYN.equalsIgnoreCase("y")) {
				return View.MEMBER_SIGNUP;
			}else if(selectYN.equalsIgnoreCase("n")) {
				System.out.println("¸ÞÀÎÈ­¸éÀ¸·Î ÀÌµ¿ÇÕ´Ï´Ù.");
				ScanUtil.nextLine();
				return View.MAIN;
			}else {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				ScanUtil.nextLine();
				return View.MAIN;
			}
//			return 
		}
		}
		
		} else if(choice==2) {
			String make="maker1004";
			System.out.print("°ü¸®ÀÚ »ý¼º ºñ¹Ð¹øÈ£ :  ");
			String makepw=ScanUtil.nextLine();
			if(makepw.equals(make)) {
				System.out.println("°ü¸®ÀÚ È¸¿ø°¡ÀÔ");
				//¾ÆÀÌµð
				String id="";
				while (true) {
					System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			         System.out.println(" * ¾ÆÀÌµð ÀÔ·Â [ ¿µ¹®¤ý¼ýÀÚ ÇÊ¼ö / 5±ÛÀÚ ÀÌ»ó ÀÔ·Â]");
			         System.out.print(" >> ");
			         id = ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationID(id))
			            break;
			      }
				Map<String, Object> result1 = memberDao.isOverapID(id);
		        if (result1 != null) {
		           System.out.println();
		           System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		           System.out.println("     >> ÀÌ¹Ì µî·ÏµÈ ¾ÆÀÌµð ÀÔ´Ï´Ù! <<");
		           ScanUtil.nextLine();
		           return View.MEMBER_SIGNUP;
		        } else {
				//ºñ¹Ð¹øÈ£
				String pw="";
				 while (true) {
					 System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			         System.out.println(" * ºñ¹Ð¹øÈ£ ÀÔ·Â [ 4±ÛÀÚ ÀÌ»ó ÀÔ·Â]");
			         System.out.print(" >> ");
			         pw= ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationPW(pw))
			            break;
			      }
			     //ÀÌ¸§
				String name="";
				 while (true) {
					 System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			         System.out.println(" * ÀÌ¸§ ÀÔ·Â [ ÇÑ±Û¤ý¿µ¹® ÀÔ·Â]");
			         System.out.print(" >> ");
			         name=ScanUtil.nextLine();
			         System.out.println();
			         if (normalizationName(name))
			            break;
				 }
				 //ÀüÈ­¹øÈ£
				
				String hp="";
				  while (true) {
					  System.out.println(" ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				         System.out.println(" * ÀüÈ­¹øÈ£ ÀÔ·Â [ ex) 01012345678 ]");
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
				System.out.println("°ü¸®ÀÚ »ý¼ºÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù. ·Î±×ÀÎÈ­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
				ScanUtil.nextLine();
				return View.MEMBER_LOGIN;
						}
	        		}
				}else {
					System.out.println("°ü¸®ÀÚ »ý¼º ºñ¹Ð¹øÈ£°¡ Æ²·È½À´Ï´Ù. È¨À¸·Î ÀÌµ¿ÇÕ´Ï´Ù.");
					ScanUtil.nextLine();
			}
	
		}
		return View.MAIN; //Áßº¹Ã³¸®¿¹¿Ü
	}

// ÀÌ¸§ Á¤±ÔÈ­ true : Á¤±ÔÈ­ ¿Ï | false : ´Ù½Ã ÀÔ·Â
	   private boolean normalizationName(String name) {
	      boolean result = true;
	      if (!name.matches("^[a-zA-Z°¡-ÆR]*$")) {
	         System.out.println(" * ¹®ÀÚ¸¸ ÀÔ·Â°¡´ÉÇÕ´Ï´Ù.");
	         result = false;
	      } else if (name.length() < 2) {
	         System.out.println(" * ¿Ã¹Ù¸¥ ÀÔ·ÂÀÌ ¾Æ´Õ´Ï´Ù.");
	         result = false;
	      } else
	         return result;
	      System.out.println();
	      System.out.println(" * ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
	      System.out.println();
	      return result;
	   }
	   // ¾ÆÀÌµð Á¤±ÔÈ­ true : Á¤±ÔÈ­ ¿Ï | false : ´Ù½Ã ÀÔ·Â
	   private boolean normalizationID(String id) {
	      boolean result = true;
	      if (id.length() < 4) {
	         System.out.println(" * ¾ÆÀÌµð´Â 4ÀÚ¸® ÀÌ»óÀÔ´Ï´Ù.");
	         result = false;
	      } else {
	         if (id.matches("^[a-zA-Z0-9]*$")) {
	            if (id.matches("^[a-zA-Z]*$")) {
	               System.out.println(" * ¼ýÀÚ¸¦ ÇÊ¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	               result = false;
	            } else if (id.matches("^[0-9]*$")) {
	               System.out.println(" * ¿µ¾î¸¦ ÇÊ¼ö·Î ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
	               result = false;
	            }
	         } else {
	            System.out.println(" * ¿µ¹®¤ý¼ýÀÚ¸¸ ÀÔ·Â°¡´É ÇÕ´Ï´Ù.");
	            result = false;
	         }
	      }
	      if (result)
	         return result;
	      System.out.println();
	      System.out.println(" * ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
	      System.out.println();
	      return result;
	   }
	   // ¾ÏÈ£ Á¤±ÔÈ­ true : Á¤±ÔÈ­ ¿Ï | false : ´Ù½Ã ÀÔ·Â
	   private boolean normalizationPW(String pw) {
	      if (pw.length() < 4) {
	         System.out.println(" * ºñ¹Ð¹øÈ£´Â 4ÀÚ¸® ÀÌ»óÀÔ´Ï´Ù.");
	         System.out.println();
	         System.out.println(" * ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä. ");
	         System.out.println();
	         return false;
	      } else
	         return true;
	   }
	   // ÀüÈ­¹øÈ£ Á¤±ÔÈ­ true: Á¤±ÔÈ­ ¿Ï·á | false: ´Ù½Ã ÀÔ·Â
	   private boolean normalizationTel(String hp) {
	       boolean result = true;
	       if (!(hp.matches("^[0-9]*$"))) {
	           System.out.println(" * ¼ýÀÚ¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
	           result = false;
	       } else if (!hp.matches("^0[0-9]{10}$")) {
	           System.out.println(" * ¿Ã¹Ù¸¥ ÀüÈ­¹øÈ£ Çü½ÄÀÌ ¾Æ´Õ´Ï´Ù.");
	           result = false;
	       } else {
	           return result;
	       }
	       System.out.println();
	       System.out.println(" * ´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
	       System.out.println();
	       return result;
	   }

	
	public int login() {
		printUtil.bar2();
		printUtil.blank(2);	
		System.out.println("\t\t== ·Î±×ÀÎ ==");
		System.out.println("\t\t¾ÆÀÌµð >> ");
		System.out.print("\t\tºñ¹Ð¹øÈ£ >> ");
		printUtil.blank(4);	
		printUtil.bar2();
		System.out.print("\n¾ÆÀÌµð ÀÔ·Â : ");
		String id = ScanUtil.nextLine();
		System.out.print("ºñ¹Ð¹øÈ£ ÀÔ·Â : ");
		String pw = ScanUtil.nextLine();
		Map<String, Object> row = memberDao.login(id, pw);
		if(row == null) {
			System.out.println("¾ÆÀÌµð ¶Ç´Â ºñ¹Ð¹øÈ£ ÀÔ·ÂÀÌ Àß¸øµÇ¾ú½À´Ï´Ù! ´Ù½Ã ·Î±×ÀÎÇØÁÖ¼¼¿ä.");	
			ScanUtil.nextLine();
			return View.MEMBER_LOGIN;//Ã¹È­¸é!
		}else {
			
			Controller.sessionStorage.put("login", true);
			Controller.sessionStorage.put("loginInfo",row);
			String TF=(String) row.get("MEM_ADMIN");
			if(TF.equals("T")) {
				System.out.println("°ü¸®ÀÚ "+row.get("MEM_NAME") + "´Ô È¯¿µÇÕ´Ï´Ù! °è¼ÓÇÏ·Á¸é ¿£ÅÍ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
				ScanUtil.nextLine();
				return View.HOME;
	         }else {
	        	 System.out.println("ÀÏ¹Ý È¸¿ø "+row.get("MEM_NAME") + "´Ô È¯¿µÇÕ´Ï´Ù! °è¼ÓÇÏ·Á¸é ¿£ÅÍ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
	        	 ScanUtil.nextLine();
	        	 return View.HOME; //·Î±×ÀÎ ÈÄ¿¡ Ã¢
	         }
		}
	}
	
	public int guest() {
		String guestId = "guest";
		String guestPW = "guest";
		Map<String, Object> row = memberDao.login(guestId, guestPW);
		Controller.sessionStorage.put("login", true);
		Controller.sessionStorage.put("loginInfo",row);
		System.out.println("°Ô½ºÆ® ¸ðµå·Î ·Î±×ÀÎÇÏ¼Ì½À´Ï´Ù. ÀÏºÎ ±â´ÉÀÌ Á¦ÇÑµË´Ï´Ù.");
		ScanUtil.nextLine();
		return View.HOME;
	}
	
	public int logout() {
		System.out.println("·Î±×¾Æ¿ô");
		System.out.println("·Î±×¾Æ¿ôÀ» ÇÏ½Ã°Ú½À´Ï±î? (y/n)");
		String flag=ScanUtil.nextLine();
		if(flag.equalsIgnoreCase("y")) {
			Object o = Controller.sessionStorage.get("loginInfo");
	         Map<String, Object> loginInfo = (Map<String, Object>) o;
			System.out.println("·Î±×¾Æ¿ôÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù. ¿£ÅÍ¸¦ ÀÔ·ÂÇÏ¸é ¸ÞÀÎÈ­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			Controller.sessionStorage.put("login", false);
	        Object res1=Controller.sessionStorage.get("login");

	        if((boolean)res1==false) Controller.sessionStorage.put("loginInfo", null);

			ScanUtil.nextLine();
			return View.MAIN; //·Î±×ÀÎ ÀüÈ­¸é(Ã¹È­¸é)
		}else if(flag.equalsIgnoreCase("n")){
			System.out.println("¿£ÅÍ¸¦ ÀÔ·ÂÇÏ¸é È¨È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			ScanUtil.nextLine();
			return View.HOME; //¸Þ´º È­¸é!
		}else {
			System.err.println("Àß¸øµÈ ÀÔ·Â°ªÀÔ´Ï´Ù. È¨È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			ScanUtil.nextLine();
			return View.HOME; //¸Þ´º È­¸é!
			
		}
		
		
	}
	
	public int inquiry() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
		if(memID.equals("guest")) {
			System.out.println("°Ô½ºÆ®´Â °³ÀÎÁ¤º¸ Á¶È¸°¡ ºÒ°¡´ÉÇÕ´Ï´Ù. È¨À¸·Î µ¹¾Æ°©´Ï´Ù.");
			ScanUtil.nextLine();
			return View.HOME;
		}
		
		String sql="SELECT MEM_ID ¾ÆÀÌµð, MEM_NAME ÀÌ¸§, MEM_HP ÀüÈ­¹øÈ£ FROM MEMBER WHERE MEM_ID='"+memID+"'";
		
		Map<String, Object> result=memberDao.inquiry(sql);
		
		System.out.println();
	    printUtil.bar();
	    System.out.print("\t\t== È¸¿ø Á¤º¸==\n");
	    printUtil.blank(1);
	      for(String key : result.keySet()) {
	         System.out.println(key + " : \t" + result.get(key));
	      }
		
		System.out.println();
		System.out.println("1.È¸¿øÁ¤º¸¼öÁ¤  2.È¸¿øÅ»Åð 0.µÚ·Î°¡±â");
		printUtil.bar();
		System.out.print("¹øÈ£ÀÔ·Â>>");
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
		System.out.println("\n\t\t== È¸¿øÁ¤º¸ ¼öÁ¤ ==");
		printUtil.blank(1);
		System.out.println("1.ºñ¹Ð¹øÈ£ º¯°æ \n2.ÀÌ¸§ º¯°æ \n3.ÀüÈ­¹øÈ£ º¯°æ \n0.µÚ·Î°¡±â"  );
		printUtil.blank(1);
		printUtil.bar();
		System.out.print("¹øÈ£ÀÔ·Â>> ");
		switch(ScanUtil.nextInt()) {
		case 0 : 
			System.out.println("È¨È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			ScanUtil.nextLine();
			return View.HOME;
			
		case 1: 
		printUtil.bar2();
		printUtil.blank(1);
		System.out.println("\n\t\t== ºñ¹Ð¹øÈ£º¯°æ ==");
		printUtil.blank(3);
		printUtil.bar();
		System.out.print("½Å±Ô ºñ¹Ð¹øÈ£ : ");
		String pw=ScanUtil.nextLine();
		String sql=sqlStr+"MEM_PW = '"+ pw +"' WHERE MEM_ID = '"+memID+"'";
	
		int result=memberDao.update(sql);
		
		if(result>0) {
		System.out.println("ºñ¹Ð¹øÈ£ º¯°æÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù. ·Î±×ÀÎ È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
		Controller.sessionStorage.put("login", false);
	    Controller.sessionStorage.put("loginInfo",null);
		ScanUtil.nextLine();
		}
		return View.MEMBER_LOGIN;  
		
		
		case 2:
		printUtil.bar2();
		printUtil.blank(1);
		System.out.println("\n\t\t== ÀÌ¸§º¯°æ ==");
		printUtil.blank(3);
		printUtil.bar();
		System.out.print("½Å±Ô ÀÌ¸§: ");
		String name=ScanUtil.nextLine();
		sql=sqlStr+"MEM_NAME= '"+name+"' WHERE MEM_ID= '"+memID+"'";
		
		int result1=memberDao.update(sql);
		
		if(result1>0) {
			System.out.println("È¸¿ø Á¤º¸°¡ ¾÷µ¥ÀÌÆ® µÇ¾ú½À´Ï´Ù. È¨È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			ScanUtil.nextLine();
		}else {
			System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù! È¨È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
		}
		return View.HOME;
		
		case 3:
			printUtil.bar2();
			printUtil.blank(1);
			System.out.println("\n\t\t== ÀüÈ­¹øÈ£º¯°æ ==");
			printUtil.blank(3);
			printUtil.bar();
		System.out.print("½Å±Ô ÀüÈ­¹øÈ£: "); 
		String hp=ScanUtil.nextLine();
		sql=sqlStr+"MEM_HP='"+hp+"' WHERE MEM_ID='"+memID+"'";
	
		int result2=memberDao.update(sql);
		if(result2>0) {
			System.out.println("È¸¿ø Á¤º¸°¡ ¾÷µ¥ÀÌÆ® µÇ¾ú½À´Ï´Ù. È¨È­¸éÀ¸·Î µ¹¾Æ°©´Ï´Ù.");
			ScanUtil.nextLine();
		}
		
		}
		return View.HOME;

	}
	public int resign() {
		
	
		
		System.out.println("´ë´öÀÎÀçµµ¼­°üÀÇ È¸¿øÅ»Åð¸¦ ÇÏ½Ã°Ú½À´Ï±î? (Y/N)");
		String flag=ScanUtil.nextLine();
		if(flag.equalsIgnoreCase("y")) {
		
			Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
			String memID = (String) map.get("MEM_ID");
			
			String sql= "DELETE MEMBER WHERE MEM_ID='"+memID+"'";
			int result=memberDao.resign(sql);
			System.out.println(sql);
			
			
			if(result>0) {
				System.out.println("È¸¿ø Á¤º¸°¡ »èÁ¦µÇ¾ú½À´Ï´Ù. ");
			}
		}
		return View.HOME;
		
	}
	
	public int admin_inquiry() {
		Map<String, Object> map = (Map<String, Object>) Controller.sessionStorage.get("loginInfo");
		String memID = (String) map.get("MEM_ID");
		
		String sql="SELECT MEM_ID ¾ÆÀÌµð, MEM_NAME ÀÌ¸§, MEM_HP ÀüÈ­¹øÈ£ FROM MEMBER WHERE MEM_ID='"+memID+"'";
		
		List<Map<String, Object>> memberList=memberDao.adminInquiry(sql);
		
             for (Map<String, Object> list : memberList) {
             System.out.print(list.get("¾ÆÀÌµð"));
             System.out.print("\t" + list.get("ÀÌ¸§"));
             System.out.print("\t" + list.get("ÀüÈ­¹øÈ£"));
             System.out.println();
          }
		System.out.println();
	    printUtil.bar();
	    System.out.print("\t\t== È¸¿ø Á¤º¸==\n");
	    printUtil.blank(1);
	    
		System.out.print("0. µÚ·Î °¡±â ");
		int select = ScanUtil.nextInt();
		
		if(select == 0) {
			ScanUtil.nextLine();
			return View.MEMBER_INQUIRY;
		}else {
			return View.ERROR;
		}
		
	}

}
