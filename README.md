# Box Arithmetic
An implementation of N. J. Wildberger's model of arithmetic using boxes
This is based on the youtube lectures Intro to Algebraic Calculus
This project implements the features described through the lecture "A new world
of functions from box arithmetic".
This branch is being updated to be consistent with Wildberger's new book. 
This project was converted from the MSetArighmetic project by renaming MSet to Box
and removing the anti features. (The anti features may be restored.)
The basic unit is the box. 
A box is an unordered collection of objects with the allowance for repeated instances. 
The objects are restricted to be boxes.
## Representing natural numbers 
The number zero is represented by the empty box &#x230a;&#x230b;, or by &#x25a1;. 
A natural number is represented by an box containing empty boxes. Thus 1 is 
&#x230a;&#x230a;&#x230b;&#x230b; 2 is &#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b;
 and so on.
## Representing PolyNumbers
A PolyNumber is an box of boxes. 
The polynumber &#x03B1; is represented by the box containing a box
containing an empty box, which is the box containing the natural number one..

&#x230a;&#x230a;&#x230a;&#x230b;&#x230b;&#x230b; = &#x230a;&#x230a;1&#x230b;&#x230b; = &#x03B1;

The polynumber 2&#x03B1; is the multiset containing two instances of alpha

&#x230a;&#x230a;&#x230a;&#x230b;&#x230b;&#x230a;&#x230a;&#x230b;&#x230b;&#x230b; = &#x230a;&#x230a;1&#x230b;&#x230a;1&#x230b;&#x230b; = 2&#x03B1;

The polynumber &#x03B1;<sup>2</sup> is represented by the box containing a box
containing two empty boxes, which is the box containing the natural number two.

&#x230a;&#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b;&#x230b; = &#x230a;&#x230a;2&#x230b;&#x230b; = &#x03B1;<sup>2</sup>

In general, m&#x03b1;<sup>n</sup> is represented by an box containing m copies of the natural number n.

The polynumber 3 + &#x03B1; + &#x03B1;<sup>3</sup> + 2&#x03B1;<sup>4</sup> is represented by

&#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230a;&#x230b;&#x230b;&#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b;&#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b;&#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b;&#x230b;

which can be written as &#x230a;0 0 0 1 3 4 4&#x230b;.

## Multinumbers

A muultinumber is an box of poly numbers. For example:

&#x230a;0 0 0 &#x230a;1&#x230b; &#x230a;1 1&#x230b; &#x230a;1 1&#x230b; &#x230a;1 1&#x230b; &#x230a;1 1&#x230b; &#x230a;1 1 1 1 1&#x230b;&#x230b;

Interpreting polynumbers as polynomials the "variable" &#x03B1; is used. Encapsulating a polynumber
into a mset introduces additional "variables". &#x03B1; now becomes &#x03B1;<sub>0</sub>, then
&#x230a;&#x03B1;<sub>0</sub>&#x230b; becomes &#x03B1;<sub>1</sub>. In general m&#x03B1;<sub>k</sub><sup>n</sup>
is represented by an mset containing m copies of the mset containing n copies of the number k.

 &#x230a;0 0 2 3 3 3 3 &#x230a;1&#x230b; &#x230a;1&#x230b; &#x230a;1&#x230b; &#x230a;0 1&#x230b; &#x230a;0 1&#x230b; &#x230a;0 1&#x230b; &#x230a;0 1&#x230b; &#x230a;0 1&#x230b; &#x230a;0 0 1 1&#x230b;&#x230b;
 
 Represents
 
 2+α₀²+4α₀³+3α₁+5α₀α₁+α₀²α₁²
 
 ## Operations

### Addition
Addition of multisets is performed by creating a new multiset that contains the
contents of the multisets being combined.

#### Addition of natural numbers

&#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b; + &#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b; = &#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b; (2 + 3 = 5)

#### Addition of polynumbers

&#x230a;3 3 4&#x230b; + 0 + 1 + &#x230a;3 7&#x230b; = &#x230a;0 3 3 3 4 7&#x230b;

2α₀³+α₀⁴ + 0 + 1 + α₀³+α₀⁷ = 1+3α₀³+α₀⁴+α₀⁷

#### Addition of multinumbers

&#x230a;&#x230a;4&#x230b; &#x230a;3&#x230b;&#x230b; + &#x230a;0 &#x230a;4&#x230b; &#x230a;1 1 2&#x230b;&#x230b; + &#x230a;4 &#x230a;1 1 2&#x230b;&#x230b; = &#x230a;0 4 &#x230a;4&#x230b; &#x230a;4&#x230b; &#x230a;3&#x230b; &#x230a;1 1 2&#x230b; &#x230a;1 1 2&#x230b;&#x230b;

α₄+α₃ + 1+α₄+α₁²α₂ + α₀⁴+α₁²α₂ = 1+α₀⁴+2α₄+α₃+2α₁²α₂ 

### Multiplication

Multiplication of boxes is accomplished by forming all possible combinations of the contents
of the msets being multiplied and adding them. For example &#x230a;A B C&#x230b; × &#x230a;X Y&#x230b; = &#x230a;A+X A+Y B+X B+Y C+X C+Y&#x230b;.

#### Multiplication of natural numbers

&#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b; × &#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b; = &#x230a;&#x230a;&#x230b;+&#x230a;&#x230b; &#x230a;&#x230b;+&#x230a;&#x230b; &#x230a;&#x230b;+&#x230a;&#x230b; &#x230a;&#x230b;+&#x230a;&#x230b; &#x230a;&#x230b;+&#x230a;&#x230b; &#x230a;&#x230b;+&#x230a;&#x230b;&#x230b;

since &#x230a;&#x230b;+&#x230a;&#x230b; = &#x230a;&#x230b; the result is 

&#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b; × &#x230a;&#x230a;&#x230b;&#x230a;&#x230b;&#x230a;&#x230b;&#x230b; = &#x230a;&#x230a;&#x230b; &#x230a;&#x230b; &#x230a;&#x230b; &#x230a;&#x230b; &#x230a;&#x230b; &#x230a;&#x230b;&#x230b; or 2 × 3 = 6

#### Multiplication of polynumbers

&#x230a;2 3&#x230b; × &#x230a;0 1 1&#x230b; = &#x230a;2 3 3 3 4 4&#x230b;

α₀²+α₀³ × 1+2α₀ = α₀²+3α₀³+2α₀⁴

#### Multiplication of multinumbers

&#x230a;&#x230a;3 8&#x230b; &#x230a;0 0 2&#x230b;&#x230b; × &#x230a;2 &#x230a;9&#x230b; &#x230a;1 1&#x230b;&#x230b; = &#x230a;&#x230a;3 8 9&#x230b; &#x230a;1 1 3 8&#x230b; &#x230a;0 0 3 8&#x230b; &#x230a;0 0 2 9&#x230b; &#x230a;0 0 1 1 2&#x230b; &#x230a;0 0 0 0 2&#x230b;&#x230b;

α₃α₈+α₀²α₂ × α₀²+α₉+α₁² = α₃α₈α₉+α₁²α₃α₈+α₀²α₃α₈+α₀²α₂α₉+α₀²α₁²α₂+α₀⁴α₂

## Lists, Singletons,  Vexels, and Maxels

### Lists

A list is an ordered sequence of boxes. If A, B, C, D are boxes then the list
⌈A, B, C, D⌉ is represented by the boz &#x230a;&#x230a;A&#x230b;&#x230a;A B&#x230b;&#x230a;A B C&#x230b;&#x230a;A B C D&#x230b;&#x230b;.
Lists are currently represented in this implementation, as well as two special
instances.

### Singletons
A singleton is a list of a single box. The paper restrices the enclosing box to
natural numbers, but the lecture did not incloude this restribtion.
Observe that the singleton of 0 is &#x230a;&#x230a;&#x230b;&#x230b; which is 1. The singleton of 1 is &#x230a;&#x230a;&#x230a;&#x230b;&#x230b;&#x230b;
which is &#x230a;&#x230a;1&#x230b;&#x230b; which is α₁, and the singleton of 2 is &#x230a;&#x230a;2&#x230b;&#x230b; or α₂ and so on. The
paper renames αₙ as eₙ.

### Pixels
A pixel is a list of two boxes. The paper restricts the boxes to natural numbers,
but the lecture does not have this restriction. Internally we represent a pixel
both as a list and also the separate components. Thus the pixel ⌈3, 4⌉ is the box
&#x230a;&#x230a;3&#x230b;&#x230a;3 4&#x230b;&#x230b; the individual components are also represented as a and b. 

#### Pixel multiplication
If ⌈A, B⌉ and ⌈C, D⌉ are pixels then ⌈A, B⌉ × ⌈C, C⌉ is ⌈A, D⌉ only if B=C. 
It is otherwse nothing. Computationally we use null to represent nothing. 
In multiplying a signleton ⌈A⌉ and the pixel ⌈B, C⌉ results in the singleton
⌈C⌉ only if A=B. In multiplying a pixel ⌈A, B⌉ and a singleton ⌈C⌉ results
in ⌈A⌉ only if B=C. 

### Vexels
A vexel is a box of singletons. If the singletons are restricted to natural numbers,
then a vexel is a multi. For example the box &#x230a;₁ &#x230a;&#x230a;4&#x230b;&#x230b; ₃ &#x230a;1&#x230b; ₂ &#x230a;&#x230a;1&#x230b;&#x230b;&#x230b; contains 
represents 3e₀+2e₁+e₄ and represents the vector (3, 2, 0, 0, 1).

### Maxels
A maxel is a box of pixels. The individual pixels represent the row and column
in a matrix. And the number of instances of a pixel represent the value at that
position.

The matrix

    1  2
    3  4

is the box &#x230a;₄ ⌈1, 1⌉ ₃ ⌈1, 0⌉ ₂ ⌈0, 1⌉ ₁ ⌈0, 0⌉&#x230b;

Example from the lecture

    M = &#x230a;⌈1, α₀⌉ ⌈0, α₀⁷⌉&#x230b;
    N = &#x230a;⌈α₀, 2⌉ ⌈α₀⁷, 3⌉&#x230b;
    M × N = &#x230a;⌈1, 2⌉ ⌈0, 3⌉&#x230b; 

Note that &#x230a;7&#x230b; is α₀⁷

Example from the paper:

    M = &#x230a;₃ ⌈0, 2⌉ ₂ ⌈0, 0⌉ ⌈1, 0⌉&#x230b;

        2   0   3
        1   0   0

    N =&#x230a;₄ ⌈0, 1⌉ ₇ ⌈2, 1⌉ ⌈1, 0⌉ ₅ ⌈3, 2⌉&#x230b;

        0   4   0
        1   0   0
        0   7   0
        0   0   5

    M×N =&#x230a;₂₉ ⌈0, 1⌉ ₄ ⌈1, 1⌉&#x230b;

        0  29
        0   4

### ListBoxes
A Listbox is a Box of Lists. Maxels and Vixels are ListBoxes.
Example:
    X = &#x230a;⌈5, &#x230a;&#x230a;&#x230a;2&#x230b;&#x230b;&#x230b;, 1⌉ ⌈2, α₀³⌉ ⌈0, 0, &#x230a;&#x230a;1&#x230b;&#x230b;, &#x230a;0 1&#x230b;⌉&#x230b;
The function pi(i) selects the ith element of each list and returns is as a box.
    X.pi(1) = X.pi(1) &#x230a;2 5 0&#x230b;
    X.pi(2) &#x230a;&#x230a;3&#x230b; &#x230a;&#x230a;&#x230a;2&#x230b;&#x230b;&#x230b; 0&#x230b;
    X.pi(3) &#x230a;1 &#x230a;&#x230a;1&#x230b;&#x230b;&#x230b;

## Functions
A function is a Maxel where pi(1) known as the range is a set. Example:
    F: &#x230a;⌈2, 6+α₀²⌉ ⌈0, 3⌉ ⌈α₀, 1⌉ ⌈3, 1⌉ ⌈4, 5⌉&#x230b;
    G: &#x230a;⌈1, 8⌉ ⌈3, 8⌉ ⌈0, 0⌉&#x230b;
Functions can be composed by multiplying the correxponding Maxels.
    F×G: &#x230a;⌈0, 8⌉ ⌈α₀, 8⌉ ⌈3, 8⌉&#x230b;