import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

   public static void main(String[] args) {
      Game game = new Game(null, Levels.level8, 0);
      int n , m; //


      while (true) {
            System.out.println("Choose the Strategy you want to  play with :");
            System.out.println("1- User Play");
            System.out.println("2- DFS");
            System.out.println("3- BFS");
            System.out.println("4- UCS");
            System.out.println("5- AStar");
            System.out.println("6- Hill Climbing");
            System.out.println("q: Quit");

         char option = new Scanner(System.in).nextLine().charAt(0);
         Scanner input = new Scanner(System.in);  //
         //final int option;

            switch (option) {   
               case '1':
              System.out.println("Enter L to play a level OR C to create a new board:"); //
               char op = new Scanner(System.in).nextLine().charAt(0); //
               switch (op) {  //
                  case 'l':   //

// System.out.println("Enter a number between [1-30] to play a level");
// int k = input.nextInt();
// Levels.levels[k - 1]; ////

                     Logic.UserPlay(game);
                     break;
                  case 'c':  //
                  System.out.println("Enter width");
                  n = input.nextInt();
                  System.out.println("Enter length");
                  m = input.nextInt();
                  System.out.println();

                  char[][] board = new char[n][m];
                   
                   System.out.println("Enter:\n- for free positions" + "\n" +
                                              "U to add Basket" + "\n" +
                                              "o to add a ball" + "\n" +
                                              "$ to add coins" + "\n" +
                                              "# to add the walls" + "\n" 
                                          );
                  for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        System.out.print("Enter your object in this position: (" + i + " , " + j + "): ");
                        board[i][j] = input.next().charAt(0);
                    }
                }
                Game g = new Game( null, board, 0 );
                System.out.println("  ");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                       // System.out.print(board[i][j] + "  ");
                    }
                  //  System.out.println();
                }
                Logic.UserPlay(g);


                //   Create_Board c = new Create_Board();
                //   c.new_board(null, game);
                 break;
                }
                 //Logic.UserPlay(game);
                   break;

               case '2':
                  long startTime = System.currentTimeMillis();
                  List<Game> winPath = SearchAlgorithms.DFS(game);
                   if (winPath != null) {               // هاد الشرط مشان اذا ما لقت الخوارزمية حل
                    for (Game s : winPath) {
                    s.print();
                     }
                      } else {
                  System.out.println("No solution");
                      }
                      long endTime = System.currentTimeMillis();
                      long executionTime = endTime - startTime;
                      System.out.println("Algorithm take " + executionTime + " ms"); 
                    break;

               case '3':
                  // SearchAlgorithms.BFS(game);  // هاد السطر فعلياً عملي بس مؤشر عكلاس الخوارزمية بس ما طبع المسار ليحل الليفل
                  long startTime1 = System.currentTimeMillis();
                  List<Game> winPathList = SearchAlgorithms.BFS(game);
                   if (winPathList != null) {               // هاد الشرط مشان اذا ما لقت الخوارزمية حل
                    for (Game s : winPathList) {
                    s.print();
                     }
                      } else {
                  System.out.println("No solution");
                      }
                      long endTime1 = System.currentTimeMillis();
                      long executionTime1 = endTime1 - startTime1;
                      System.out.println("Algorithm take " + executionTime1 + " ms"); 
                    break;

               case '4':
                long startTime2 = System.currentTimeMillis();
                List<Game> winPathL = SearchAlgorithms.UCS(game);
                   if (winPathL != null) {               // هاد الشرط مشان اذا ما لقت الخوارزمية حل
                    for (Game s : winPathL) {
                    s.print();
                     }
                      } else {
                  System.out.println("No solution");
                      }
                      long endTime2 = System.currentTimeMillis(); 
                      long executionTime2 = endTime2 - startTime2;
                      System.out.println("Algorithm take " + executionTime2 + " ms"); 
                    break;

               case '5':
               long startTime3 = System.currentTimeMillis();
               List<Game> winPathL1 = SearchAlgorithms.AStar(game);
                   if (winPathL1 != null) {               // هاد الشرط مشان اذا ما لقت الخوارزمية حل
                    for (Game s : winPathL1) {
                    s.print();
                     }
                      } else {
                  System.out.println("No solution");
                      }
                      long endTime3 = System.currentTimeMillis(); 
                      long executionTime3 = endTime3 - startTime3; 
                      System.out.println("Algorithm take " + executionTime3 + " ms"); 
                  // AStar aStar = new AStar(level1);
                    break;

               case '6':
               long startTime4 = System.currentTimeMillis();
               List<Game> winPathL2 = SearchAlgorithms.HillClimbing(game);
                   if (winPathL2 != null) {               // هاد الشرط مشان اذا ما لقت الخوارزمية حل
                    for (Game s : winPathL2) {
                    s.print();
                     }
                      } else {
                  System.out.println("No solution");
                      }
                      long endTime4 = System.currentTimeMillis(); 
                      long executionTime4 = endTime4 - startTime4;
                      System.out.println("Algorithm take " + executionTime4 + " ms"); 
                //    HillClimbing hillClimbing = new HillClimbing(level1);
                    break;

               case 'q':
                    return;
            }
        }



// // طباعة الوقت المستغرق من الخوارزمية في عملية التنفيذ
// game.print();
// long startTime = System.currentTimeMillis();
// List<Game> winPath = SearchAlgorithms.BFS(game);
// if (winPath != null) { 
//          for (Game s : winPath) {
//             s.print();
//          }
//       } else {
//          System.out.println("No solution");
//       }
// long endTime = System.currentTimeMillis();
// long executionTime = endTime - startTime;
// System.out.println("Algorithm take " + executionTime + "ms");   
       



      // game.print();
      // Logic.UserPlay(game);  // استدعاء تابع اليوزر بالكلاس عن طريق نخلي static و هي الطريقة الاصح

      // int starttime = ;
      // int endtime = ;
      // int algotime = endtime - starttime;   // ممكن تكون تعليمات اضافية لنفذها مع الخوازميات و بتقلي اديش استغرق وقت بالتنفيذ
      // System.out.println("Algorithm take " + algotime + "ms");

      // List<Game> winPath = SearchAlgorithms.DFS(game);
      // if (winPath != null) {               // هاد الشرط مشان اذا ما لقت الخوارزمية حل
      //    for (Game s : winPath) {
      //       s.print();
      //    }
      // } else {
      //    System.out.println("No solution");
      // }

      // game = game.MoveDown();
      // game.print();
      // System.out.println(game.isFinal());
      // level.length
   }
}



 // Logic l = new Logic();
 // l.UserPlay(game);       // هي كانت طريقة الاستدعاء بعمل اوبجكت من الكلاس 



 //create board ?
 //levels [1-30]
 //execution time for alghorithms ?
 //hillClimpng ?
