# Percolation
<a href="https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.phpl">Assignment specifications</a>

This is the week 1 submission.
It's a program that estimates the percolation threshold by Monte Carlo method or Monte Carlo simulation.

Given an n-by-n grid, initialising all initial sites to blocked, we open the sites one by one randomly(independently) until the system percolates.
The fraction of the sites opened after system starts percolating gives the percolation threshold.

PercolationStats takes two command-line arguments <i>n</i> and <i>T</i> where <i>n</i> is size-of-grid and <i>T</i> is number of 
computation experiments.

### To compile and run in Linux / macOS

<code>
javac -cp /file/path/algs4.jar Percolation.java PercolationStats.java

java -cp /file/path/algs4.jar: PercolationStats 200 100
</code>

### To compile and run in Windows

<code>
javac -cp "\file\path\algs4.jar" Percolation.java PercolationStats.java

java -cp "\file\path\algs4.jar;" PercolationStats 200 100
</code>

<code>algs4.jar</code> required for princeton library.
Download algs4.jar <a href="https://drive.google.com/open?id=1Iu7h69SiqSq4QyIImicnjXeb_a3s-vYW">here</a>

### ASSESSMENT SUMMARY

Correctness:  33/33 tests passed<br>
Memory:       8/8 tests passed<br>
Timing:       20/20 tests passed<br>
