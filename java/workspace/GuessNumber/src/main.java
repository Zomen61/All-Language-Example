import java.util.Scanner;

public class main {
	public static void main(String[] arg) {
		String temp ="";
		
		Scanner scanner = new Scanner(System.in);
		GuessNumberBrain gameBrain = new GuessNumberBrain();
		
		gameBrain.startnewGame();
		System.out.println("輸入restart可重新遊戲");
		while(true) {
			System.out.print("請輸入四位數字：");
			temp = scanner.nextLine();
			
			if(temp.equals("restart")) {
				gameBrain.startnewGame();
			}else if(temp.equals("getAns")){
				gameBrain.getAns();
			}else {
				gameBrain.userGuess(temp);
			}
		}
		
	}
}
