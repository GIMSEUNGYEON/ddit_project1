package util;
import View.View;

public class PrintUtil {
	public void bar() {
		System.out.println("\n=============================================");
	}

	public void bar2() {
		System.out.print("=============================================");

	}
	
	public void direction1() {
		System.out.println("¡ç A     D ¡æ");
	}

	public void blank(int line) {
		for (int i = 0; i < line; i++) {
			System.out.println("");
		}
	}

	public void intro() {
		String[] team = { "¡á", "  ", "¡á", "T¡á", "TE¡á", "TEE¡á", "TE¡á", "TEA_", "TEAM¡á", "TEAM1" };
		String[] name = { "¤¡¡á", "±è¡á", "±è¤µ_", "±è½Â¡á", "±è½Â¿¬", "±è½Â¿¬,", "±è½Â¿¬, ¤¡", "±è½Â¿¬, ±è¡á", "±è½Â¿¬, ±èÂ÷_", "±è½Â¿¬, ±èÂ÷Àº",
						  "±è½Â¿¬, ±èÂ÷Àº, ", "±è½Â¿¬, ±èÂ÷Àº, ¤²¡á", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú_", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¤·_", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ì¡á", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ì¤¸¡á",
						  "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¤²_", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹è_", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹ð",
						  "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹è", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹èÀ²_", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹èÀ¯¸®",
						  "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹èÀ¯¸², ", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹èÀ¯¸², ¤µ_", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹èÀ¯¸², ¼Û¡á", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹èÀ¯¸², ¼ÛÀ¸_",
						  "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹èÀ¯¸², ¼ÛÀº¤²_", "±è½Â¿¬, ±èÂ÷Àº, ¹Ú¿ìÀç, ¹èÀ¯¸², ¼ÛÀººñ" };
		for (int i = 0; i < team.length; i++) {
			Utility.wait(400);
			bar();
			blank(3);
			System.out.println(team[i]);
			blank(5);
			bar2();
		}
		for (int i = 0; i < name.length; i++) {
			Utility.wait(250);
			bar();
			blank(3);
			System.out.println(team[9]);
			System.out.println(name[i]);
			blank(4);
			bar2();
		}
		Utility.wait(800);
		bar();
		blank(3);
		System.out.println(team[9]);
		System.out.println(name[30]);
		System.out.print("START ¡æ");
		blank(2);
		bar();
		System.out.print("Enter ÀÔ·ÂÀ¸·Î ½ÃÀÛ ");
		ScanUtil.nextLine(); //¿£ÅÍ ÀÔ·ÂÀ¸·Î ½ÃÀÛ
	}

	public int main() {

		bar();
		blank(1);
		System.out.println("\t\t´ë´öÀÎÀçµµ¼­°ü");
		System.out.println("\t\t1.·Î±×ÀÎ");
		System.out.println("\t\t2.È¸¿ø°¡ÀÔ");
		System.out.println("\t\t3.°Ô½ºÆ®¸ðµå");
		blank(1);

		blank(1);
		bar2();
		System.out.print("\n\t\t >>ÀÔ·Â  ");
		blank(1);
		switch(ScanUtil.nextInt()) {
		case 1 : return View.MEMBER_LOGIN;
		case 2 : return View.MEMBER_SIGNUP;
		case 3 : return View.GUEST;
		default : return View.ERROR;
		}
		
	}

	public void login() {
		
		bar();
		blank(1);
		System.out.println("\t\t´ë´öÀÎÀçµµ¼­°ü");
		System.out.println("\t\tID : ");
		System.out.println("\t\tPW : ");

		blank(1);

		blank(2);
		bar2();
		System.out.print("\n>>ÀÔ·Â");
		ScanUtil.nextLine();
	}

	public void loading() {
		String[] loading = { "Á¢   ¼Ó   Áß   ", "Á¢   ¼Ó   Áß   .", "Á¢   ¼Ó   Áß   .  .", "Á¢   ¼Ó   Áß   .  .  .",
							 "Á¢   ¼Ó   Áß   .", "Á¢   ¼Ó   Áß   .  .", "Á¢   ¼Ó   Áß   .  .  ." };
		for (int i = 0; i < loading.length; i++) {
			Utility.wait(400);
			bar();
			blank(3);
			System.out.println(loading[i]);
			blank(5);
			bar2();
		}
	}
}
