import java.util.Scanner;

public class Logic {
    static Scanner in = new Scanner(System.in);

    public static void UserPlay(Game ob) {
        Game currentState = ob;
        // int move;
        String move;
        currentState.print();
        while (true) {
            if (currentState.isFinal()) {
                System.out.println("YOU WIN");
                break;
            }
            System.out.println("w >up ,,,,,, s >down ,,,,,, a >left ,,,,,, d >right");
            move = in.nextLine();
            switch (move) {
                case "w":
                    if (move.equals("w") && currentState.CanMoveUp()) // move == 8 (بحالة الادخال بالارقام) بالاحرف مانها وافية بتم بتابع الدوت
                   currentState = currentState.MoveUp();
                          else
                System.out.println("YOU CAN'T MOVE HERE");
            currentState.print();
                    break;

                case "s":
                    if (move.equals("s") && currentState.CanMoveDown()) // move == 8 (بحالة الادخال بالارقام) بالاحرف مانها وافية بتم بتابع الدوت
                   currentState = currentState.MoveDown();
                          else
                System.out.println("YOU CAN'T MOVE HERE");
            currentState.print();
                    break;

                case "a":
                    if (move.equals("a") && currentState.CanMoveLeft()) // move == 8 (بحالة الادخال بالارقام) بالاحرف مانها وافية بتم بتابع الدوت
                   currentState = currentState.MoveLeft();
                          else
                System.out.println("YOU CAN'T MOVE HERE");
            currentState.print();
                    break;

                case "d":
                    if (move.equals("d") && currentState.CanMoveRight()) // move == 8 (بحالة الادخال بالارقام) بالاحرف مانها وافية بتم بتابع الدوت
                   currentState = currentState.MoveRight();
                          else
                System.out.println("YOU CAN'T MOVE HERE");
            currentState.print();
                    break;
             
                default:
                System.out.println("WRONG CHARECHTER");
                    break;
            }
            // if (move.equals("w") && currentState.CanMoveUp()) // move == 8 (بحالة الادخال بالارقام) بالاحرف مانها وافية بتم بتابع الدوت
            //     currentState = currentState.MoveUp();
            // else if (move.equals("s") && currentState.CanMoveDown())
            //     currentState = currentState.MoveDown();
            // else if (move.equals("a") && currentState.CanMoveLeft())
            //     currentState = currentState.MoveLeft();
            // else if (move.equals("d") && currentState.CanMoveRight())
            //     currentState = currentState.MoveRight();
            // else
            //     System.out.println("YOU CAN'T MOVE HERE");
            // currentState.print();
            // System.out.println("=====================================");
        }
    }
}

