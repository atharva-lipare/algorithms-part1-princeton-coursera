# Queues
<a href="https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php">Assignment specifications</a>


This is the week 2 submission. It simply implements a dequeue and a randomized queue.<br>

The test client program Permutation.java takes an integer <i>k</i> as a command-line argument; reads a sequence of strings from standard input using StdIn.readString(); and prints exactly <i>k</i> of them, uniformly at random; and print each item from the sequence at most once. 

### To compile and run in Linux / macOS

<code>
javac -cp /file/path/algs4.jar Deque.java RandomizedQueue.java Permutation.java

echo Q W E R T Y I O P | java -cp /file/path/algs4.jar: Permutation 3
</code>

### To compile and run in Windows

<code>
javac -cp "\file\path\algs4.jar" Deque.java RandomizedQueue.java Permutation.java

echo Q W E R T Y I O P | java -cp "\file\path\algs4.jar;" Permutation 3
</code>

<code>algs4.jar</code> required for princeton library.
Download algs4.jar <a href="https://drive.google.com/open?id=1Iu7h69SiqSq4QyIImicnjXeb_a3s-vYW">here</a>

### ASSESSMENT SUMMARY

Correctness:  43/43 tests passed<br>
Memory:       122/122 tests passed<br>
Timing:       193/193 tests passed<br>
