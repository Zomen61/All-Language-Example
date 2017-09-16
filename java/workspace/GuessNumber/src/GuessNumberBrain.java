import java.util.Random;

public class GuessNumberBrain {
	int ans = 0;
	Random ran = new Random();
	char[] tempFromAns;
	
	
	void startnewGame() {
		ans = ran.nextInt(10000);
		tempFromAns = Integer.toString(ans).toCharArray();
		if(tempFromAns.length < 4) {
			char[] temp = new char[4];
			int moveToPos = temp.length - tempFromAns.length;
			for(int i = 0; i < temp.length; i++) {
				if(i >= moveToPos) {
					temp[i] = tempFromAns[i - moveToPos];
				}else {
					temp[i] = '0';
				}
			}
			tempFromAns = temp;
		}
		
		System.out.println("遊戲開始");
//		for(char everyone: tempFromAns) {
//			System.out.print(everyone);
//		}
//		System.out.println("");
	}
	
	void userGuess(String userGuessNumber) {
		
		if (userGuessNumber.length() == 4) {
			int A = 0, B = 0,tempA = 0 ,tempB = 0;
			char[] tempFromUser = userGuessNumber.toCharArray();
			int[] Acount0to9 = new int[10];
			int[][] ABC = new int[4][3];
			
			for (int i = 0; i < tempFromUser.length; i++) {
				for (int j = 0; j < tempFromAns.length; j++) {
					if (tempFromUser[i] == tempFromAns[j] && i == j) {
						ABC[i][0]++;
					} else if (tempFromUser[i] == tempFromAns[j]) {
						ABC[i][1]++;
					}else {
						ABC[i][2]++;
					}
				}
			}
			
			for(int i=0;i < ABC.length;i++) {
				if (ABC[i][0] != 0) {
					A++;
					Acount0to9[Character.getNumericValue(tempFromUser[i])]++;
				}
			}
			
			for(int i=0;i < ABC.length;i++) {
				if(ABC[i][0] == 0 && ABC[i][1] != 0 && ABC[i][1] > Acount0to9[Character.getNumericValue(tempFromUser[i])]) {
					B++;
				}
			}
			



			System.out.println(A + "A" + B + "B");

			if (A == 4) {
				System.out.println("恭喜您答對!!!");
				System.out.println("本局答案為:"+ans);
				this.startnewGame();
			}
		}else {
			System.out.println("請輸入4位數");
		}
		
	}
	
	void getAns() {
		System.out.print("答案為:");
		for(char everyone: tempFromAns) {
			System.out.print(everyone);
		}
		System.out.println("");
	}
}
