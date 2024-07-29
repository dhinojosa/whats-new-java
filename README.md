# What's new in Java 2024

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/github.com/dhinojosa/whats-new-java)

## Outline

NOTE: JEPS only includes software features

* Introduction
    * Name
    * What do you do?
    * What is your experience with Java?
    * What IDE do you usually use?
    * How would you grade (Novice, Intermediate, Advanced) your experience with Lambdas/Streams/Optionals?
    * What Operating System do you use for work/home?  
    * What Java version are you using for work/home?
    * What other JVM languages do you use?
* SDKMan.io
* [Java Almanac](https://javaalmanac.io/)
* [JDK 9](https://docs.oracle.com/javase/9/docs/api/overview-summary.html)
    * JShell [JEP 222](https://openjdk.java.net/jeps/222)
    * Immutable/Convenience Factory Methods for Collections [JEP 269](https://openjdk.java.net/jeps/269)
    * Module System [JEP 261](https://openjdk.java.net/jeps/261)
    * JLink [JEP 282](https://openjdk.java.net/jeps/282)
    * Process Handles
    * Flow API [JEP 266](https://openjdk.java.net/jeps/266)
    * Functional and Stream Additions
    * HTTP2 Client (Preview) [JEP 110](https://openjdk.java.net/jeps/110)
    * Var Handles [JEP 193](https://openjdk.java.net/jeps/193)
    * `IntStream CharSequence.chars()`
    * `Objects.checkIndex()`
* [JDK 10](https://docs.oracle.com/javase/10/docs/api/overview-summary.html)
    * Local-Variable Type Inference [JEP 286](https://openjdk.java.net/jeps/286)
    * `copyOf` in `List`, `Map`, and `Set`
* [JDK 11](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
    * String class additions:
        * `lines()`
        * `isBlank()`
        * `repeat()`
        * `strip()`
        * `stripLeading()`
        * `stripTrailing()`
    * `Predicate.not(Predicate)`
    * HTTPClient API [JEP 321](https://openjdk.java.net/jeps/321)
    * `Files.readString()` and `Files.writeString()`
    * `Optional.isEmpty()`
    * `Collection.toArray(IntFunction)`
    * Null-Based Input, Output Stream and Readers
    * Local-Variable Syntax for Lambda Parameters [JEP 323](https://openjdk.org/jeps/323)
* [JDK 12](https://docs.oracle.com/en/java/javase/12/docs/api/)
    * `switch` expressions (Preview)
    * `teeing` collector
    * `Files.mismatch(Path, Path)`
    * Microbenchmark Suite [JEP 230](https://openjdk.java.net/jeps/230)
* [JDK 13](https://docs.oracle.com/en/java/javase/13/docs/api/)
    * Text Blocks (First Preview) [JEP 355](https://openjdk.java.net/jeps/355)
    * Reimplemented Socket API [JEP 353](https://openjdk.java.net/jeps/353)
    * `FileSystems.newFileSystem()` Method
    * `switch` expressions (Second Preview) [JEP 254](https://openjdk.java.net/jeps/354)
* [JDK 14](https://docs.oracle.com/en/java/javase/14/docs/api/)
    * Records (Preview) [JEP 359](https://openjdk.java.net/jeps/359)
    * Text Blocks (Second Preview) [JEP 368](https://openjdk.java.net/jeps/368)
    * Pattern Matching for `instanceof` (Preview) [JEP 305](https://openjdk.java.net/jeps/305)
    * `switch` expressions (Final) [JEP 361](https://openjdk.java.net/jeps/361)
    * Reimplemented DataSocket API [JEP 353](https://openjdk.java.net/jeps/353)
    * Helpful NullPointerExceptions [JEP 358](https://openjdk.java.net/jeps/358)
* [JDK 15](https://docs.oracle.com/en/java/javase/15/docs/api/)
    * Sealed Classes (First Preview) [JEP 360](https://openjdk.java.net/jeps/360)
    * Records (Second Preview) [JEP 384](https://openjdk.java.net/jeps/384)
    * Pattern Matching for `instanceof` (Second Preview) [JEP 375](https://openjdk.java.net/jeps/375)
    * Text Blocks [JEP 378](https://openjdk.java.net/jeps/378)
    * Hidden Classes [JEP 371](https://openjdk.java.net/jeps/371)
* [JDK 16](https://docs.oracle.com/en/java/javase/16/docs/api/)
    * `Stream.toList`, `Stream.toArray`
    * Vector API (First Incubator) [JEP 338](https://openjdk.java.net/jeps/338)
    * Records (Final) [JEP 395](https://openjdk.java.net/jeps/395)
    * Sealed Classes (Second Preview) [JEP 397](https://openjdk.java.net/jeps/397)
    * Packaging Tool [JEP 392](https://openjdk.java.net/jeps/392)
    * Foreign-Memory Access API (Third Incubator) [JEP 393](https://openjdk.java.net/jeps/393)
    * Pattern Matching for `instanceof` (Final) [JEP 394](https://openjdk.java.net/jeps/394)
* [JDK 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
    * Pattern Matching for Switch (Preview) [JEP 406](https://openjdk.java.net/jeps/409)
    * Sealed Classes [JEP 409](https://openjdk.java.net/jeps/409)
    * Enhanced Pseudo-Random Number Generators [JEP 356](https://openjdk.java.net/jeps/356)
    * Vector API (Second Incubator) [JEP 414](https://openjdk.java.net/jeps/414)
    * Foreign Function & Memory API (First Incubator) [JEP 412](https://openjdk.java.net/jeps/412)
    * `java.util.HexFormat`
    * `Process.inputReader()`, `Process.outputWriter()`, `Process.errorReader()`
* [JDK 18](https://docs.oracle.com/en/java/javase/18/docs/api/)
    * Pattern Matching for Switch (Second Preview) [JEP 420](https://openjdk.java.net/jeps/420)
    * Simple Web Server [JEP 408](https://openjdk.java.net/jeps/408)
    * Code Snippets in Java API Documentation [JEP 413](https://openjdk.java.net/jeps/413)
    * Foreign Function & Memory API (Second Incubator) [JEP 412](https://openjdk.java.net/jeps/419)
    * Vector API (Third Incubator) [JEP 417](https://openjdk.java.net/jeps/417)
    * `Math.ceilDiv`, `Math.ceilDivExact`, `Math.ceilMod`, `Math.divideExact`, `Math.floorDivExact`, `Math.unsignedMultiplyHigh`
    * `StrictMath.ceilDiv`, `StrictMath.ceilDivExact`, `StrictMath.ceilMod`, `StrictMath.divideExact`, `StrictMath.floorDivExact`, `StrictMath.unsignedMultiplyHigh`
    * `FileInputStream.transferTo(OutputStream)`
* [JDK 19](https://docs.oracle.com/en/java/javase/19/docs/api/)
    * Project Loom Virtual Threads (First Preview) [JEP 425](https://openjdk.java.net/jeps/425)
    * Pattern Matching for Switch (Third Preview) [JEP 427](https://openjdk.java.net/jeps/427)
    * Foreign Function & Memory API (First Preview) [JEP 424](https://openjdk.java.net/jeps/424)
    * Vector API (Fourth Incubator) [JEP 426](https://openjdk.java.net/jeps/426)
    * Project Loom Structured Concurrency (First Incubator) [JEP 428](https://openjdk.java.net/jeps/428)
    * `CompletableFuture.exceptionNow()`, `CompletableFuture.resultNow()`, `CompletableFuture.state()`
* [JDK 20](https://docs.oracle.com/en/java/javase/20/docs/api/)
    * Pattern Matching for `switch` (Forth Preview) [JEP 433](https://openjdk.java.net/jeps/433)
    * Record Patterns (Second Preview)  [JEP 432](https://openjdk.java.net/jeps/432)
    * Project Loom Virtual Threads (Second Preview) [JEP 436](https://openjdk.java.net/jeps/436)
    * Foreign Function & Memory API (Second Preview) [JEP 434](https://openjdk.java.net/jeps/434)
    * Project Loom Structured Concurrency (Second Incubator) [JEP 437](https://openjdk.java.net/jeps/437)
* [JDK 21](https://docs.oracle.com/en/java/javase/21/docs/api/)
    * Record Patterns [JEP 440](https://openjdk.org/jeps/440)
    * Pattern Matching for `switch` [JEP 441](https://openjdk.org/jeps/441)
    * Foreign Function & Memory API (Third Preview) [JEP 442](https://openjdk.org/jeps/442)
    * Unnamed Patterns and Variables (Preview) [JEP 443](https://openjdk.org/jeps/443)
    * Virtual Threads [JEP 444](https://openjdk.org/jeps/444)
    * Unnamed Classes and Instance Main Methods (Preview) [JEP 445](https://openjdk.org/jeps/445)
    * Scoped Values (Preview) [JEP 446](https://openjdk.org/jeps/446)
    * Statements before `super()` (Preview) [JEP 447](https://openjdk.org/jeps/447)
    * Vector API (Sixth Incubator) [JEP 448](https://openjdk.org/jeps/448)
    * Structured Concurrency (Preview) [JEP 453](https://openjdk.org/jeps/453)
    * String Templates (First Preview) [JEP 430](https://openjdk.java.net/jeps/430)
* [JDK 22](https://download.java.net/java/early_access/jdk22/docs/api/)
    * Statements before `super()` (Preview) [JEP 447](https://openjdk.org/jeps/447)
    * Foreign Function & Memory API [JEP 454](https://openjdk.org/jeps/454)
    * Primitive Types in Patterns, `instanceof`, and `switch` (Preview) [JEP 455](https://openjdk.org/jeps/455)
    * Unnamed Patterns and Variables [JEP 456](https://openjdk.org/jeps/456)
    * Class File API (Preview) [JEP 457](https://openjdk.org/jeps/457)
    * String Templates (Second Preview) [JEP 459](https://openjdk.org/jeps/459)
    * Launch Multi-File Source-Code Programs [JEP 458](https://openjdk.org/jeps/458)
    * Vector API (Seventh Incubator) [JEP 460](https://openjdk.org/jeps/460)
    * Stream Gatherers (Preview) [JEP 461](https://openjdk.org/jeps/461)
    * Structured Concurrency (Second Preview) [JEP 462](https://openjdk.org/jeps/462)
    * Implicit Declared Classes and Enhanced Main Methods (Second Preview) [JEP 463](https://openjdk.org/jeps/463)
    * Scoped Values (Second Preview) [JEP 464](https://openjdk.org/jeps/464)
* To Be Announced (Possibly)
    * Markdown Documentation Comments [JEP 467](https://openjdk.org/jeps/467)
    * Derived Record Creation (Preview) [JEP 468](https://openjdk.org/jeps/468)

