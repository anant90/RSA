Declaration of originality:

This is to declare that the project has been made solely by myself without discussion with any other student.

Currently there are a few drawbacks in the project which I am working on:

1. Prime number generation is a highly time consuming task. For example, to search primes of length 20 or 
more takes more than 3-4 minutes which is, in my belief, infeasible. Infact, generation of primes of length 
100 may take upto an hour on a normal computer. An alternative to this problem may be to store all the primes 
having length 100 to (maybe) 110 in a file and for choosing primes, we can just pick any two primes from that list. 
I hope such a list should be available. 

2. Moreover, for generating primes, Fermat's test is used, which checks upto 10 times for non-failure of the test. 
This does not ensure that the number finally chosen IS a prime, and the algorithm fails if it not. 

3. The strings are not encoded as given in the project page.. i.e. using [A+[B+[C]]] concept. This will be implemented soon.

4.  The multiplication algorithm used is O(N^1.69) (i.e. Karatsuba Multiplication).. Decryption will work much faster 
if Fast Fourier Transform is implemented which should bring down the complexity to O(N logN).

5. The division algorithm is long division one. The complexity may be brought down to O(M(n)) i.e. equal to that of multiplication.

