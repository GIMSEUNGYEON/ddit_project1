package View;

public interface View {
	final int MAIN = 0; // 로그인 OR 회원가입 화면
	final int GUEST = 111;
	final int HOME = 1; // 로그인 후 좌석 예약, 게시판 등 확인 화면
	final int ADMIN_HOME = 11; // 로그인 후 좌석 예약, 게시판 등 확인 화면
	final int ERROR = 999;
	// 회원담당
	final int MEMBER = 2;
	final int MEMBER_SIGNUP = 21; // 회원가입
	final int MEMBER_LOGIN = 22; // 로그인
	final int MEMBER_INQUIRY = 23; // 멤버정보조회
	final int MEMBER_ADMIN_INQUIRY = 231; // 멤버정보조회
	final int MEMBER_UPDATE = 24; // 멤버정보수정
	final int MEMBER_WITHDRAW = 25; // 멤버탈퇴
	final int MEMBER_LOGOUT = 26; // 멤버탈퇴
	final int MEMBER_LIST = 27;
	// 예약 담당
	final int RESERVATION = 3; // 입실, 퇴실 선택
	final int RESERVATION_RNO = 311; // 열
	final int RESERVATION_SNO = 312;
	final int RESERVATION_IN = 31;
	final int RESERVATION_OUT = 32;
	final int RESERVATION_OUT_ADMIN = 321;
	
//	int RESERVATION_ADD = 33; //연장
	
	// 보드 담당
	final int BOARD = 4;
	final int BOARD_LIST = 41;
	final int BOARD_INSERT = 42;
	final int BOARD_DELETE = 43;
	final int BOARD_UPDATE = 44;
	final int BOARD_DETAIL = 45;
	
}
