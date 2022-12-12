package homework;

import java.util.Scanner;
import java.io.*;

//각각의 게시판의 부모 클래스
public class Board {
	private boolean isAdmin;
	private String userId;
	String boardName=null;
	
	public Board(boolean isAdmin, String userId) {
		this.isAdmin = isAdmin;
		this.userId=userId;
	}
	
	//게시판 정보 출력
	public void printBoard() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true){
			
			File articles = new File("./data/board/" + this.boardName + "/article");
			//게시글 정보를 list()를 이용하여 배열로 받는다.
			String[] articlenames = articles.list();
			System.out.println("-------------------------------------------------------------");
			for(int i=0;i<articlenames.length;i++) {
				//.txt를 제외하고 출력
				System.out.println(i+1+"   " + articlenames[i].substring(0, articlenames[i].length()-4));
			}
			System.out.println("-------------------------------------------------------------");
			System.out.println("0: 뒤로가기, wa: 게시글작성");
			System.out.println("-------------------------------------------------------------");
			System.out.print("입력: ");
			String idx = scan.nextLine();
			
			if(idx.equals("0")) {
				break;
			}
			else if(idx.equals("wa")){
				//게시글 작성함수
				System.out.print("제목: ");
				String title = scan.nextLine();
				//게시글 작성
				Article article = new Article(title, userId, boardName);
				article.writeArticle();
			}
			else {
				//인덱스 위치에 접속
				try{
		            int number = Integer.parseInt(idx);
		            Article article = new Article(articlenames[number-1], userId, boardName);
					article.readArticle();
		        }
		        catch (NumberFormatException e){
		            e.printStackTrace();
		        }
			}
			
			
			
		}
		
		
	}
	//게시판 추가 - admin인 경우
	public void addBoard(String boardname) {
		if(!isAdmin) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("오류! 관리자가 아닙니다.");
			System.out.println("-------------------------------------------------------------");
			return;
		}
		else {
			String path = "./data/board/"+ boardname; //폴더 경로
			String articlepath = path+"/article";
			String commentpath = path + "/comment";
			File folder = new File(path);
			File articlefolder = new File(articlepath);
			File commentfolder = new File(commentpath);

			// 해당 폴더 없으면 생성
			if (!folder.exists()) {
				try{
					folder.mkdir();
					articlefolder.mkdir();
					commentfolder.mkdir();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("-------------------------------------------------------------");
				System.out.println("이미 있는 게시판이에요 ");
				System.out.println("-------------------------------------------------------------");
			}
		}
	}
	public String getuserId() {
		return userId;
	}
	public boolean getisAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
