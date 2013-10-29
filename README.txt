There are a few drawbacks in the project which I will work on in the future if I return to this project:

1. For generating primes, Fermat's test is used, which checks upto 10 times for non-failure of the test. 
This does not ensure that the number finally chosen IS a prime, and the algorithm fails if it not. 

2. The strings are not encoded as given in the project page.. i.e. using [A+[B+[C]]] concept. This will be implemented soon.

3.  The multiplication algorithm used is O(N^1.69) (i.e. Karatsuba Multiplication).. Decryption will work much faster 
if Fast Fourier Transform is implemented which should bring down the complexity to O(N logN).

4. The division algorithm is long division one. The complexity may be brought down to O(M(n)) i.e. equal to that of multiplication.
