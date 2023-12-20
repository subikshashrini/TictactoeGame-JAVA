import java.util.Scanner;
import java.util.Random;

class TicTacToe
{
    static char[][] board;  // declaration only
    // declaring static coz we r accessing board thru class in isvalidmov()
    //default value for character is /u0000(null character) it is unicode

    public TicTacToe()
    {
        board=new char[3][3];//arrays are objects in java
        initBoard();

    }

    void initBoard()
    {
        for(int i=0;i< board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=' ';//we are initializing empty space for all places
            }
        }
    }

    static void displayBoard(){
        System.out.println("-------------");
        for(int i=0;i< board.length;i++){
            System.out.print("| ");
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }

    }

    static void placeMark(int row,int column,char mark){
        if(row>=0 && row<=2 && column>=0 && column<=2){
            board[row][column]=mark;
        }
        else{
            System.out.println("Invalid Position");
        }
    }

    static boolean checkColumnWin()
    { // checking column win
        for(int j=0;j<=2;j++)
        {
            if(board[0][j]!=' ' &&
                    board[0][j]==board[1][j] &&
                    board[1][j]==board[2][j])
            {
                return true;
            }
        }
        return false;
 }
    static boolean checkRowWin()
    {
        for(int i=0;i<=2;i++)
        {
            if(board[i][0]!=' ' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2] )
            {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagWin()
    {
        if(board[0][0]!=' ' &&
                board[0][0]==board[1][1] && board[1][1]==board[2][2]
        || board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]  )
        {
            return true;
        }
        return false;
    }

    static boolean checkDraw()
    {
        for(int i=0;i<=2;i++)
        {
            for(int j=0;j<=2;j++)
            {
                if(board[i][j]==' ')
                {
                    return false;
                }
            }
        }
        return true;
    }


}

abstract  class player
{

    String name;
    char mark;

    abstract  void makeMove();

    boolean isValidMove(int row,int column)
    {
        if(row>=0 && row<=2 &&
                column>=0 && column<=2)
        {
            if(TicTacToe.board[row][column]==' ')
            {
                return true;
            }
        }
        return false;
    }


}

class Humanplayer extends  player
{

    Humanplayer(String name, char mark)
    {
        this.name=name;
        this.mark=mark;
    }

    void makeMove()
    {

       Scanner scanner = new Scanner(System.in);
       int row;
       int column;
       do
       {
       System.out.println("Enter the row and column:");
        row= scanner.nextInt();
        column=scanner.nextInt();
       }while(!isValidMove( row,column));

       TicTacToe.placeMark(row,column,mark);


    }

}

class AIplayer extends  player
{


    AIplayer(String name, char mark)
    {
        this.name=name;
        this.mark=mark;
    }

    void makeMove()
    {

        Scanner scanner = new Scanner(System.in);
        int row;
        int column;
        do
        {
           Random r = new Random();//this is builtin class in java
            row = r.nextInt(3); //3 is boundary --- 0 1 2
            column = r.nextInt(3);//--- 0 1 2
            //nextInt() is method inside this class....it generates number
        }while(!isValidMove( row,column));

        TicTacToe.placeMark(row,column,mark);


    }

}

public class launchgame {

    public static void main(String[] args) {
       TicTacToe t = new TicTacToe();//t is an object

        Humanplayer p1=new Humanplayer("Your",'X');
        AIplayer p2=new AIplayer("AI Player",'O');

        player cp;//current player
        cp = p1; //reference type assignment
        while(true)//while- as long as true
        {
            System.out.println(cp.name + " turn");
            cp.makeMove();
            TicTacToe.displayBoard();


            if(TicTacToe.checkColumnWin() || TicTacToe.checkRowWin() ||
                    TicTacToe.checkDiagWin())
            {
                System.out.println(cp.name+" has won!");
                break;//will come out of loop
            }
            else if(TicTacToe.checkDraw())
            {
                System.out.println("GAME IS DRAW!PLAY AGAIN...");
                break;

            }
            else
            {
                if(cp==p1)
                {
                    cp = p2;
                }
                else
                {
                    cp=p1;
                }

            }
        }


    }
}
