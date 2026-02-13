# BoxtArithmetic
An implementation of N. J. Wildberger's model of arithmetic using boxes
This is based on the youtube lectures Intro to Algebraic Calculus
This project implements the features described through the lecture "Integer
Arithmetic via Boxes"
This project was converted from the MSetArighmetic project by renaming MSet to Box
and removing the anti features.
The curious world of integral polynumbers. The basic unit is the box. 
A box is an unordered collection of objects with the allowance for repeated instances. 
The objects are restricted to be boxes.
## Representing natural numbers 
The number zero is represented by the empty box []. A natural number is represented 
by an box containing empty boxes. Thus 1 is [[]] 2 is [[][]] and so on.
## Representing PolyNumbers
A PolyNumber is an box of boxes. 
The polynumber &#x03B1; is represented by the box containing a box
containing an empty box, which is the box containing the natural number one..

[[[]]] = [[1]] = &#x03B1;

The polynumber 2&#x03B1; is the multiset containing two instances of alpha

[[[]][[]]] = [[1][1]] = 2&#x03B1;

The polynumber &#x03B1;<sup>2</sup> is represented by the box containing a box
containing two empty boxes, which is the box containing the natural number two.

[[[][]]] = [[2]] = &#x03B1;<sup>2</sup>

In general, m&#x03b1;<sup>n</sup> is represented by an box containing m copies of the natural number n.

The polynumber 3 + &#x03B1; + &#x03B1;<sup>3</sup> + 2&#x03B1;<sup>4</sup> is represented by

[[][][][[]][[][][]][[][][][]][[][][][]]]

which can be written as [0 0 0 1 3 4 4].

## Multinumbers

A muultinumber is an box of poly numbers. For example:

[0 0 0 [1] [1 1] [1 1] [1 1] [1 1] [1 1 1 1 1]]

Interpreting polynumbers as polynomials the "variable" &#x03B1; is used. Encapsulating a polynumber
into a mset introduces additional "variables". &#x03B1; now becomes &#x03B1;<sub>0</sub>, then
[&#x03B1;<sub>0</sub>] becomes &#x03B1;<sub>1</sub>. In general m&#x03B1;<sub>k</sub><sup>n</sup>
is represented by an mset containing m copies of the mset containing n copies of the number k.

 [0 0 2 3 3 3 3 [1] [1] [1] [0 1] [0 1] [0 1] [0 1] [0 1] [0 0 1 1]]
 
 Represents
 
 2+α₀²+4α₀³+3α₁+5α₀α₁+α₀²α₁²
 
 ## Operations

### Addition
Addition of multisets is performed by creating a new multiset that contains the
contents of the multisets being combined.

#### Addition of natural numbers

[[][]] + [[][][]] = [[][][][][]] (2 + 3 = 5)

#### Addition of polynumbers

[3 3 4] + 0 + 1 + [3 7] = [0 3 3 3 4 7]

2α₀³+α₀⁴ + 0 + 1 + α₀³+α₀⁷ = 1+3α₀³+α₀⁴+α₀⁷

#### Addition of multinumbers

[[4] [3]] + [0 [4] [1 1 2]] + [4 [1 1 2]] = [0 4 [4] [4] [3] [1 1 2] [1 1 2]]

α₄+α₃ + 1+α₄+α₁²α₂ + α₀⁴+α₁²α₂ = 1+α₀⁴+2α₄+α₃+2α₁²α₂ 

### Multiplication

Multiplication of boxes is accomplished by forming all possible combinations of the contents
of the msets being multiplied and adding them. For example [A B C] × [X Y] = [A+X A+Y B+X B+Y C+X C+Y].

#### Multiplication of natural numbers

[[][]] × [[][][]] = [[]+[] []+[] []+[] []+[] []+[] []+[]]

since []+[] = [] the result is 

[[][]] × [[][][]] = [[] [] [] [] [] []] or 2 × 3 = 6

#### Multiplication of polynumbers

[2 3] × [0 1 1] = [2 3 3 3 4 4]

α₀²+α₀³ × 1+2α₀ = α₀²+3α₀³+2α₀⁴

#### Multiplication of multinumbers

[[3 8] [0 0 2]] × [2 [9] [1 1]] = [[3 8 9] [1 1 3 8] [0 0 3 8] [0 0 2 9] [0 0 1 1 2] [0 0 0 0 2]]

α₃α₈+α₀²α₂ × α₀²+α₉+α₁² = α₃α₈α₉+α₁²α₃α₈+α₀²α₃α₈+α₀²α₂α₉+α₀²α₁²α₂+α₀⁴α₂

## Negative numbers

Negative numbers are represened by difference pairs. A difference pair contains 
two boxes and represents the value of the leftBox minus the rightBox. Note in
a previous lecture the differnce pair was called a virtual box. A previous
commit to implement the virtual box anticipated that the virutal box would
be a box and thus could be a component of a larget box. This has been removed
from this commit.

## Difference Pairs of Polynumbers
Polynumbers with integer coeficients are represented by difference pairs of
poly numbers. Thus
(1+3α₀²+5α₀⁷⊖2+3α₀⁷+4α₀⁸)

represents

̅1 + 3α₀² + 2α₀⁷ + ̅4α₀⁸

Also

C = 2 + ̅3α₀ + α₀⁴

D = 2α₀ + ̅5α₀²

C×D = 4α₀ + ̅16α₀² + 15α₀³ + 2α₀⁵ + ̅5α₀⁶
