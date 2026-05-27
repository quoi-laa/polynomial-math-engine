# polynomial-math-engine

A lightweight Java engine built to represent and manipulate mathematical polynomials (e.g.: 5.5x^9 + 9x^7 - x - 10) using a singly-linked list implementation

## origin & technical constraints

This project was originally developped as part of the Programming II curriculum (**IFT1025**) at Université de Montréal.

This assignment was designed to test core principles of computer science by enforcing the following constraints:

1. **No Standard Collections:** Utilizing Java's built-in data structures (e.g.: `ArrayList`, `LinkedList` was strictly prohibited)
2. **No Native Arrays:** Declaring standard arrays was forbidden.
3. **Custom Pointer Manipulation:** Node linking and sorting were built from scratch

## supported features

* **Dynamic Insertion** Automatically handles combining like-terms when a new term with an existing exponent is added.
* **Polynomial Addition**: Adds two distincts polynomials into a new polynomial expression
* **Scalar & Polynomial Multiplication**: Supports multiplying by a constant or by another polynomial
* **Calculus Differentiation**: Calculates and returns the mathematical derivative of the current expression
* **String Formatting**: Overrides `toString()` to display expressions in the standard console

## technical details & architecture

The system uses two core components:

1. `Noeud.java`: Acts as the node in the data structure, carrying the coefficient (`double`), the exponent (`int`), and a pointer to the next node (`Noeud prochain`).

   *Visual representation of a 3 term polynomial:*

   ```java
   [ Head ]                                      [ Tail ]
      |                                              |
      V                                              V
   ===============     ===============      =================
   | coeff: 5    |     | coeff: 6    |      | coeff: 8      |
   | exponent: 9 |     | exponent: 4 |      | exponent: 2   | 
   | prochain: * |---->| prochain: * | ---->| prochain: null| 
   ===============     ===============      =================
   ```
2. `Polynome.java`: Manages the chain of nodes, safeguarding head (`premier`) and tail (`dernier`) pointers, and driving the mathematical operations.

## how to run the tests

1. Clone the repository
2. Compile the files

   ```java
   javac Polynome.java Noeud.java
   ```
3. Run the application

   ```java
   java Polynome
   ```

## expected execution output

```
=== OK: Single term (constant) 
[ Result: 10 ]

=== OK: Single term (exponent = 1) 
[ Result: 10x ]

=== OK: Single term (exponent = 2) 
[ Result: 10x^2 ]

=== OK: Two terms 
[ Result: 5x^4 + 10x^2 ]

=== OK: Adding to an existing term 
[ Result: 5x^4 + 13x^2 ]

=== OK: Adding a constant 
[ Result: 5x^4 + 13x^2 + 7 ]

=== OK: Adding a negative coefficient 
[ Result: 5x^4 + -22x^3 + 13x^2 + 7 ]

=== OK: 1st derivative (5x^4 + -22x^3 + 13x^2 + 7) 
[ Result: 20x^3 + -66x^2 + 26x ]

=== OK: 4th derivative (5x^4 + -22x^3 + 13x^2 + 7) 
[ Result: 120 ]

=== OK: 10th derivative (5x^4 + -22x^3 + 13x^2 + 7) 
[ Result: 0 ]

=== OK: New Polynomial 
[ Result: 2.1x^6 ]

=== OK: Adding to end 
[ Result: 2.1x^6 + 6 ]

=== OK: Adding to beginning 
[ Result: 6x^86 + 2.1x^6 + 6 ]

=== OK: Adding between terms 
[ Result: 6x^86 + 3x^9 + 2.1x^6 + 6 ]

=== OK: Adding between terms 
[ Result: 6x^86 + 6.5x^12 + 3x^9 + 2.1x^6 + 6 ]

=== OK: Summing term with same exponents 
[ Result: 6x^86 + 6.5x^12 + 3x^9 + 2.1x^6 + 10 ]

=== OK: Summing term with same exponents 
[ Result: 6x^86 + 15x^12 + 3x^9 + 2.1x^6 + 10 ]

=== OK: Adding two polynomials: ( 6x^86 + 15x^12 + 3x^9 + 2.1x^6 + 10 ) + ( -8x^86 + 5x^6 ) 
[ Result: -2x^86 + 15x^12 + 3x^9 + 7.1x^6 + 10 ]

=== OK: Multiplying by a constant: ( -2x^86 + 15x^12 + 3x^9 + 7.1x^6 + 10 ) * 6.5 
[ Result: -13x^86 + 97.5x^12 + 19.5x^9 + 46.15x^6 + 65 ]

=== OK: Multiplying by zero: ( -13x^86 + 97.5x^12 + 19.5x^9 + 46.15x^6 + 65 ) * 0 
[ Result: 0 ]

=== OK: Multiplying two polynomials: (10x^2 + -20) * (75x^4 + 5.5x^2 + 3x) 
[ Result: 750x^6 + -1445x^4 + 30x^3 + -110x^2 + -60x ]
```
