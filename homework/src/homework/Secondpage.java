package homework;

import java.util.Scanner;

enum Menu{
	LOGOUT, FREE, KOREA, NOTICE, USERCONTROL;
}

public class Secondpage {
	private String userId;
	private boolean isAdmin = false;
	
	public Secondpage(String id){
		if(id.equals("admin")) {
			setAdmin(true);
		}
		setUserId(id);
	}
	
	public void menulist() {
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean isstart = true;
		while(isstart) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("아래 메뉴 중 하나를 선택해 주세요.");
			System.out.println("1. 자유게시판");
			System.out.println("2. 한류게시판");
			System.out.println("3. 공지사항");
			if(isAdmin) { 
				System.out.println("4. 사용자 관리");
			}
			else {
				System.out.println("4. 사용자 정보 수정");
			}
			
			System.out.println("0. 로그아웃");
			System.out.println("-------------------------------------------------------------");
			System.out.print("선택: ");
			
			int order = scan.nextInt();
			//마찬가지로 영거형을 이용하여 switch를 사용함
			Menu menu = Menu.values()[order];
			scan.nextLine();
			//매개변수 다형성을 구현하기 위해 boardcontrol 클래스 사용
			BoardControl bdcontrol = new BoardControl();
			
			switch(menu) {
			//다형성을 사용하여 게시판 출력할거임
			case FREE:
				FreeBoard free = new FreeBoard(isAdmin,userId);
				bdcontrol.control(free); //다형성
				break;
			case KOREA:
				KoreaWave korea = new KoreaWave(isAdmin,userId);
				bdcontrol.control(korea);
				break;
			case NOTICE:
				Notice notice = new Notice(isAdmin,userId);
				bdcontrol.control(notice);
				break;
			case USERCONTROL:
				UserControl uc = new UserControl();
				if(isAdmin) {
					uc.controlBoard();
				}
				else {
					System.out.println("-------------------------------------------------------------");
					System.out.println("어떤 것을 수정하고 싶은가요?");
					System.out.println("1:pw");
					System.out.println("-------------------------------------------------------------");
					System.out.print("입력: ");
					String option = scan.nextLine();
					System.out.print("수정할 값: ");
					String value = scan.nextLine();

					uc.reviseUser(option, userId, value);
				}
				break;
			
			case LOGOUT:
				isstart = false;
				break;
			}
		}
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
	
	
}
