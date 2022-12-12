package homework;

public class Programinfo implements Frontmenu{
	public boolean execute(){
		
		System.out.println("간단한 프로그램 설명입니다");
		System.out.println("1. 회원이 아니시라면 회원 가입을 해주세요");
		System.out.println("	회원만 프로그램에 접속 가능합니다.");
		System.out.println("2. 로그인을 해주세요");
		System.out.println("3. 여러 게시판들을 돌아다니며 즐겨주세요");
		System.out.println("	한류게시판은 한류에 대한 설명이 있어요");
		System.out.println("	자유게시판은 자유롭게 대화하는 게시판이에요");
		System.out.println("4. 게시판에 작성할 수도 있지만 댓글도 되요!");
		System.out.println("-------------------------------------------------------------");
		return true;
	}
}
