# What's new in Java 2022

## Outline

* Introduction
  * Name
  * What you do?
  * What is your experience with Java?
  * What IDE do you usually use?
  * How would grade (Novice, Intermediate, Advanced) 
    your experience with Lambdas/Streams/Optionals?
  * What Java version are you using for work/home?  
  * What other JVM languages do you use?
* SDKMan.io
* JDK 9
  * JShell [JEP 222](https://openjdk.java.net/jeps/222)
  * Immutable Collection Creation
  * Module System [JEP 261](https://openjdk.java.net/jeps/261)
  * JLink [JEP 282](https://openjdk.java.net/jeps/282)
  * Process Handles
  * Flow API [JEP 266](https://openjdk.java.net/jeps/266)
  * Functional and Stream Additions
  * HTTP2 Client
  * Var Handles [JEP 193](https://openjdk.java.net/jeps/193)
  * `IntStream CharSequence.chars()`
* JDK 10
  * Type inference
  * `Collectors.toUnmodifiableList`, `Collectors.toUnmodifiableMap`, `Collectors.toUnmodifiableSet`
  * `copyOf` in `List`, `Map`, and `Set`
* JDK 11
  * String class additions:
     * `lines()` 
     * `isBlank()` 
     * `repeat()`
     * `strip()`
     * `stripLeading()`
     * `stripTrailing()`
  * `Predicate.not(Predicate)`
  * Type inference lambda-variables
  * HTTPClient API
  * `Files.readString()` and `Files.writeString()`
  * `Optional.isEmpty()`
  * `Collection.toArray(IntFunction)`
  * Null-Based Input, Output Stream and Readers
* JDK 12
  * `switch` expressions (Preview)
  * `teeing` collector
  * `Files.mismatch(Path, Path)`
  * Microbenchmark Suite [JEP 230](https://openjdk.java.net/jeps/230)
* JDK 13
  * Text Blocks (First Preview) [JEP 368](https://openjdk.java.net/jeps/368)
  * Reimplemented Socket API [JEP 353](https://openjdk.java.net/jeps/353)
  * `FileSystems.newFileSystem()` Method
  * `switch` expressions (Second Preview) [JEP 254](https://openjdk.java.net/jeps/354)
* JDK 14
   * Records (Preview) [JEP 359](https://openjdk.java.net/jeps/359)
   * Text Blocks (Second Preview) [JEP 368](https://openjdk.java.net/jeps/368)
   * Pattern Matching for `instanceof` (Preview) [JEP 305](https://openjdk.java.net/jeps/305)
   * `switch` expressions (Final) [JEP 361](https://openjdk.java.net/jeps/361)
   * Reimplemented DataSocket API [JEP 353](https://openjdk.java.net/jeps/353)
   * `-XX:+ShowCodeDetailsInExceptionMessages`
* JDK 15
   * Sealed Classes (First Preview) [JEP 360](https://openjdk.java.net/jeps/360)
   * Records (Second Preview) [JEP 384](https://openjdk.java.net/jeps/384)
   * Enhanced Pattern Matching [JEP 375](https://openjdk.java.net/jeps/375)
   * Text Blocks (Preview) [JEP 378](https://openjdk.java.net/jeps/378)
   * Hidden Classes [JEP 371](https://openjdk.java.net/jeps/371)
* JDK 16
   * `Stream.toList`
   * Vector API (First Incubator) [JEP 338](https://openjdk.java.net/jeps/338)
   * Records (Final) [JEP 395](https://openjdk.java.net/jeps/395)
   * Sealed Classes (Second Preview) [JEP 397](https://openjdk.java.net/jeps/397)
   * Packaging Tool [JEP 392](https://openjdk.java.net/jeps/392)
   * Foreign-Memory Access API (Third Incubator) [JEP 393](https://openjdk.java.net/jeps/393)
* JDK 17
   * Pattern Matching for Switch (Preview) [JEP 406](https://openjdk.java.net/jeps/409)
   * Sealed Classes [JEP 409](https://openjdk.java.net/jeps/409)
   * Enhanced Pseudo-Random Number Generators [JEP 356](https://openjdk.java.net/jeps/356)
   * Vector API (Second Incubator) [JEP 414](https://openjdk.java.net/jeps/414)
   * Foreign Function & Memory API (First Incubator) [JEP 412](https://openjdk.java.net/jeps/412)
* JDK 18
   * Pattern Matching for Switch (Second Preview) [JEP 420](https://openjdk.java.net/jeps/420)
   * Simple Web Server [JEP 408](https://openjdk.java.net/jeps/408)
   * Code Snippets in Java API Documentation [JEP 413](https://openjdk.java.net/jeps/413)
   * Foreign Function & Memory API (Second Incubator) [JEP 412](https://openjdk.java.net/jeps/419)
   * Vector API (Third Incubator) [JEP 417](https://openjdk.java.net/jeps/417)
* JDK 19 
   * Project Loom Virtual Threads (First Preview) [JEP 425](https://openjdk.java.net/jeps/425)
   * Pattern Matching for Switch (Third Preview) [JEP 427](https://openjdk.java.net/jeps/427)
   * Foreign Function & Memory API (First Preview) [JEP 424](https://openjdk.java.net/jeps/424)
   * Vector API (Fourth Incubator) [JEP 426](https://openjdk.java.net/jeps/426)
   * Project Loom Structured Concurrency (First Incubator) [JEP 428](https://openjdk.java.net/jeps/428)
* JDK 20
   * Pattern Matching for Switch (Forth Preview) [JEP 433](https://openjdk.java.net/jeps/433)
   * Record Patterns (Second Preview)  [JEP 432](https://openjdk.java.net/jeps/432)
   * Project Loom Virtual Threads (Second Preview) [JEP 436](https://openjdk.java.net/jeps/436)
   * Foreign Function & Memory API (Second Preview) [JEP 434](https://openjdk.java.net/jeps/434)
   * Project Loom Structured Concurrency (Second Incubator) [JEP 437](https://openjdk.java.net/jeps/437)
* To Be Announced
   * String Templates (First Preview) [JEP 430](https://openjdk.java.net/jeps/430)

