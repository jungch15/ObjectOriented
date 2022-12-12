package homework;

import java.io.File;
import java.util.Scanner;

public class Notice extends Board{

	public Notice(boolean isAdmin, String userId) {
		super(isAdmin, userId);
		// TODO Auto-generated constructor stub
		this.boardName = "notice";
	}
	
	
	public void printBoard() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true){
			
			File articles = new File("./data/board/" + this.boardName + "/article");
			String[] articlenames = articles.list();
			for(int i=0;i<articlenames.length;i++) {
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
				if(!this.getisAdmin()) {
					System.out.println("-------------------------------------------------------------");
					System.out.println("이 게시판은 관리자만 글을 등록할 수 있습니다.");
					System.out.println("-------------------------------------------------------------");
				}
				else {
					//게시글 작성함수
					System.out.print("제목: ");
					String title = scan.nextLine();
					Article article = new Article(title, this.getuserId(), boardName);
					article.writeArticle();
				}
				
			}
			else {
				//인덱스 위치에 접속
				try{
		            int number = Integer.parseInt(idx);
		            Article article = new Article(articlenames[number-1], this.getuserId(), boardName);
					article.readArticle();
		        }
		        catch (NumberFormatException e){
		            e.printStackTrace();
		        }
			}
			
			
			
		}
	}
}
