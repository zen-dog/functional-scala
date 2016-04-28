
case class Book(title: String, authors: List[String])

val books: List[Book] = List (
  Book(title = "Structure and Interpretation of Computer Programms",
       authors = List("Abelson, Harald", "Sussman, Gerald J.")),
  Book(title = "Effective Java",
       authors = List("Bloch Joshua")),
  Book(title = "Effective Java 2",
       authors = List("Bloch Joshua"))
)

for {
  b <- books
  a <- b.authors
  if a startsWith "Bloch"
} yield b.title

// for expression break down with high order functions
books flatMap (b => for (a <- b.authors if a startsWith("Bloch")) yield b.title)
books flatMap (b => for (a <- b.authors.withFilter(x => x startsWith("Bloch"))) yield b.title)
books flatMap (b => b.authors withFilter(a => a.startsWith("Bloch")) map (x => b.title))