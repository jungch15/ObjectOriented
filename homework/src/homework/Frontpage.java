package homework;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

enum MenuList {
	FINISH, LOGIN, SIGNUP, INFO;
}


public class Frontpage {
	Userinfo userinfo = new Userinfo();
	//유저 정보를 이차원 리스트로 받아줌
	List<List<String>> userlist = userinfo.readUser();
	
	//전체 프로그램 실행 함수
	public void start() {
		Map<String, String> usermap;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean isstart = true;
		while(isstart) {
			//로그인 기능을 위해 해쉬맵에 유저들의 id와 pw를 넣어준다. 반복할때마다 실행하여 회원가입할 경우도 가능하게 해줌
			usermap = this.userload();
			System.out.println("-------------------------------------------------------------");
			System.out.println("안녕하세요! 환영합니다.");
			System.out.println("한류관련 처리를 하는 프로그램이에요!");
			System.out.println("시작하기전 로그인 or 회원가입 or 설명보기");
			System.out.println("-------------------------------------------------------------");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 간단한 설명");
			System.out.println("0. 종료");
			System.out.println("-------------------------------------------------------------");
			try {
				System.out.print("입력: ");
				int order = scanner.nextInt();
				scanner.nextLine();
				//위에 열거형 데이터이다. switch로 각 데이터에 따라 구분해주었다.
				MenuList menu = MenuList.values()[order];
				switch(menu) {
				case LOGIN:
					Frontmenu log = new Login(usermap);
					//로그린 클래스에 가서 로그인 실행함수 - Frontmenu란 인터페이스의 implements로 작성하여 다형성 구현
					boolean isuser = log.execute();
					// 인터페이스인 Frontmenu에 없는 함수를 사용하기 위해 구현
					Login login = (Login)log;
					if(!isuser) {
						break;
					}
					else {
						//로그인 성공
						Secondpage second = new Secondpage(login.getUserId());
						second.menulist();
						break;
					}
				case SIGNUP:
					//회원가입
					Frontmenu sign = new Signup(usermap);
					sign.execute();
					break;
					
				case INFO:
					Frontmenu info = new Programinfo();
					info.execute();
					break;
				
				case FINISH:
					isstart = false;
					this.end();
					break;
				}
			//처음 입력받는게 정수가 아니면 예외 실행
			}catch(InputMismatchException e) {
				e.printStackTrace();
				return;
			}
			
			
		}
		scanner.close();
		
	}
	public Map<String, String> userload(){
		Map<String, String> usermap = new HashMap<>();
		userlist = userinfo.readUser();
		for(int i=0;i<this.userlist.size();i++) {
			List<String> user = userlist.get(i);
			String id = user.get(0);
			String pw = user.get(1);
			usermap.put(id, pw);
		}
		//해쉬맵으로 구성되어 id를 key, pw를 value로 넣어주었다.
		return usermap;
	}
	
	//전체 프로그램 종료 함수
	public void end() {
		System.out.println("-------------------------------------------------------------");
		System.out.println("프로그램이 종료되었습니다.");
		System.out.println("안녕히 가세요!");
		System.out.println("-------------------------------------------------------------");
	}
}
