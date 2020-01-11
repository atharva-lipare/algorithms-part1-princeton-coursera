# Slider Puzzle
<a href="https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php">Assignment specifications</a>


This is the week 4 submission. The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 tiles plus a blank square.
The goal is to rearrange the tiles to the goal board, using as few moves as possible.
It uses the <a href="https://en.wikipedia.org/wiki/A*_search_algorithm">A* search algorithm</a> which is a path search algorithm and is an extension to <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Djikstra's algorithm</a>.

A priority queue is used to implement the solution.
The test client takes the name of an input file as a command-line argument and prints the minimum number of moves to solve the puzzle and a corresponding solution. 

### To compile and run in Linux / macOS

<code>javac -cp /file/path/algs4.jar Board.java Solver.java</code><br>
<code>java -cp /file/path/algs4.jar: Solver puzzle04.txt</code><br>

### To compile and run in Windows

<code>javac -cp "\file\path\algs4.jar" Board.java Solver.java</code><br>
<code>java -cp "\file\path\algs4.jar;" Solver puzzle04.txt</code><br>

Download <code>algs4.jar</code> required for princeton library <a href="https://drive.google.com/open?id=1Iu7h69SiqSq4QyIImicnjXeb_a3s-vYW">here</a>

### ASSESSMENT SUMMARY
Correctness:  51/51 tests passed<br>
Memory:       22/22 tests passed<br>
Timing:       125/125 tests passed<br>
