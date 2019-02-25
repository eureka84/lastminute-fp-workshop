# GENERICS
Differently from Java/Kotlin the generic tyoe in Scala is put between square brackets

## Variance

### Covariance
A type parameter A of a generic class can be made covariant by using the annotation +A. For some class List[+A], 
making A covariant implies that for two types A and B where A is a subtype of B, then List[A] is a subtype of List[B]. 
This allows us to make very useful and intuitive subtyping relationships using generics.

Consider this simple class structure:

```scala
abstract class Animal {
  def name: String
}
case class Cat(name: String) extends Animal
case class Dog(name: String) extends Animal
```

Both Cat and Dog are subtypes of Animal. The Scala standard library has a generic immutable sealed abstract class 
List[+A] class, where the type parameter A is covariant. This means that a List[Cat] is a List[Animal] and a List[Dog] 
is also a List[Animal]. 
Intuitively, it makes sense that a list of cats and a list of dogs are each lists of animals, and you should be able to 
substitute either of them for a List[Animal].

###Contravariance
A type parameter A of a generic class can be made contravariant by using the annotation -A. This creates a subtyping 
relationship between the class and its type parameter that is similar, but opposite to what we get with covariance. 
That is, for some class Writer[-A], making A contravariant implies that for two types A and B where A is a subtype of B, 
Writer[B] is a subtype of Writer[A].

Ex.: 

```scala
abstract class Printer[-A] {
  def print(value: A): Unit
}
```

Note the position of the generic type parameter: here it is used as an input (contravariant position)

([see documentation](https://docs.scala-lang.org/tour/variances.html))


# IMPLICITS 

[Documentation](https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html)

# CONTEXT BOUNDS

[Documentation](https://docs.scala-lang.org/tutorials/FAQ/context-bounds.html)


 