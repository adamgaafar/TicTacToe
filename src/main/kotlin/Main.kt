package tictactoe

var turn = 1

fun main() {

    val board = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' ')
    )

    printBoard(board)

    while(checkState(board) == "Game not finished"){
        gameLoop(board)
    }
    if(checkState(board) != "Game not finished"){
        //printBoard(board)
        println(checkState(board))
    }
}

fun gameLoop(board: MutableList<MutableList<Char>>) {
    val input = readln()
    if(isValidInput(input,board)) {
        val coordinates = input.split(' ').map{it.toInt()}
        board[coordinates.first() - 1][coordinates.last() - 1] = howsTurn(turn)
        printBoard(board)
        turn++
    }

}

fun howsTurn(turn: Int): Char {
    return when(turn){
        1 -> 'X'
        2 -> 'O'
        3 -> 'X'
        4 -> 'O'
        5 -> 'X'
        6 -> 'O'
        7 -> 'X'
        8 -> 'O'
        9 -> 'X'
        else -> ' '
    }
}
//To check if there is a win state
fun checkState(board: MutableList<MutableList<Char>>): String {
    var winner = ""
    var xCounts = 0
    var oCounts = 0
    var numOfwins = 0

    if(board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X' || board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X'){
        numOfwins++
        winner = "X"
    }else if(board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O' || board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O'){
        numOfwins++
        winner = "O"
    }
    for(j in board.indices){
        var xInCol = 0
        var oInCol = 0
        var xInRow = 0
        var oInRow = 0

        for (i in board[j].indices) {
            if(board[i][j] == 'X'){
                xCounts++
            }else if(board[i][j] == 'O'){
                oCounts++
            }

            //x or o in row
            if (board[i][j] == 'X') {
                xInRow++
            } else if (board[i][j] == 'O') {
                oInRow++
            }
            //x or o columns
            if (board[j][i] == 'X') {
                xInCol++
            } else if (board[j][i] == 'O') {
                oInCol++
            }
        }
        if (xInRow == 3) {
            winner = "X"
            numOfwins++
        } else if (oInRow == 3) {
            winner = "O"
            numOfwins++
        }
        if (xInCol == 3) {
            winner = "X"
            numOfwins++
        } else if (oInCol == 3) {
            winner = "O"
            numOfwins++
        }
    }

    var totalXandO: Int = xCounts + oCounts

    return if(totalXandO < 9 && numOfwins == 0){
        //println(numOfwins)
        "Game not finished"
    }else if(totalXandO == 9 && numOfwins == 0){
        "Draw"
    }else{
        "$winner wins"
    }
}

fun printBoard(board: MutableList<MutableList<Char>>){
    println("---------")

    for(j in board.indices){

        print("| ")
        for (i in board[j].indices) {
            print("${board[j][i]} ")
        }
        print("|")
        println("")
    }

    println("---------")

}

fun isValidInput(input: String, board: MutableList<MutableList<Char>>): Boolean {
    try {
        var values = input.split(" ").map { it.toInt() }
        input.split(" ").map { it.toInt() }
        if (input.split(" ").size > 2){
            throw NumberFormatException()
        }
        if(input.split(" ").map { it.toInt() }.first() > 3 || input.split(" ").map { it.toInt() }.first() < 1 || input.split(" ").map { it.toInt() }.last() > 3 || input.split(" ").map { it.toInt() }.last() < 1){
            throw Exception("Coordinates should be from 1 to 3!")
        }
        if (board[values.first() - 1][values.last() - 1] == 'O' || board[values.first() - 1][values.last() - 1] == 'X' ){
            throw Exception("This cell is occupied! Choose another one!")
        }
    }catch (e: NumberFormatException) {
        println("You should enter numbers!")
        return false
    }catch (e: Exception){
        println(e.message)
        return false
    }
    return true
}