### Week 1 Notes

#### Intro Discussion Terms/Concepts

- computer basics:  We can think of our computer as consisting of 3 components, 3 bundles of resources that are necessary to run an OS and applications
  - processor: brain of the machine.  All of the processing goes through here.  Your processor takes instructions in machine language and executes them, for many of us our processors read/execute x86.  Every single program we write/run is ultimately broken down into machine language commands for the processor.
  - memory (RAM): short term memory.  All the information needed for running applications is stored in short term memory(*).  The value and instructions that the processor executes are stored in short term memory before execution.  This is where data + programs are kept while those programs are being executed.  Memory is cleared when your machine shuts down.  There are multiple levels of short term memory, from registers on the processor itself through caches, to RAM.
  - disk: long term storage.  Your hard disk is the only part of the machine that remembers anything when powered down.
  - all the above is hardware.

- virtual machine: A virtual machine is a computer that uses virtual resources rather than physical ones.  Physical resources can be used to create virtual resources (a hypervizor manages this), then we can install an OS and run applications on those virtual resources.  Useful for compartmentalization and efficiently divvying up computing resources.

- Unix-like OS: GNU/Linux, MacOS, BSD, ... some less common ones as well.  Notably not Windows.  Some big data tooling strongly prefers a Unix-like OS.

- Binaries: a program that can run, machine code for your processor, often shortened to bin.

- Shell/CLI: A shell is a text-based interface with your OS.  CLI is Command-Line Interface.  WSL2 Ubuntu offers us only a shell, isntead of the more familiar GUI.

- Cluster: multiple computers networked together that function as a single unit.  Many of the tools we use will run "on a cluster", which means there will be processes on each machine in the cluster that communicate in order to achieve the functionality of the tool.  Long running processes are often called daemons, in some cases we call the processes running on a cluster daemons instead of processes.  Often administrative tasks are handled by master processes while the work of actual computation is done by worker processes, but there are multiple strategies for organizing/processing tasks + daemons across a cluster.  Once we have a cluster set up, we will often use it for many jobs/tasks without substantially modifying the cluster setup.

- Node: individual machine in a cluster.

- Big Data Gardner's 3 Vs:
  - Volume: The sheer amount of data to be processed
  - Velocity: The speed that new data is being produced + must be processed
  - Variety: The variability in format + imperfections of data.  Data can be structured/unstructed/semistructured and may contain errors.

- Big Data vs Data Science/Statistics/...:
  - Our focus in this training is on the tools + processes necessary to handle large amounts of data.  We're not going to be learning data science/stats, project work will require a little bit of creative thinking, but I'll only ask for a reasonable effort.

- Git: Git is a very common tool for Source Control Management (SCM).  Git allows us to save and access branching histories of the development of a project instead of just preserving the current version.  Git is exceptionally useful when working in teams on software, and is quite useful even working alone.  Git allows us/others to work on multiple branches of a project, letting us compartmentalize development of new features/changes.
  - basic commands (in order):
    - git status
    - git add .
    - git commit -m "my commit message"
    - git push
  - The above commands will save your changes in a new commit (unit of work) and then push those saved changes to github.
  - https://learngitbranching.js.org/ <- useful link for the curious.

- GitHub: very popular remote repository for git.  Git is a tool that runs on our machines, github is a website that saves/shares our repository.

#### How does Java run?

We write Java code in .java files.  This java code can't immediately run.  Instead, we run javac on the .java files to produce something called bytecode.  Java bytecode is stored in .class files.  Bytecode is somewhat human-readable, though typically less friendly than java code.  We run the compiled bytecode by running it in the Java Virtual Machine (JVM).  Inside the JVM is a Just-In-Time (JIT) Compiler that compiles bytecode down into machine code for our processor.

If you need to run Java on a machine, you need the JRE (Java Runtime Environment), which includes the JVM and core Java libraries.  If you need to write+run Java on a machine, you need the JDK (Java Development Kit), which includes a JRE and javac.

#### What is Scala?

The name Scala comes from "Scalable Language", because Martin Odersky, the original author, wanted the language to expand based on the needs of its users.  Odersky was a major contributor to javac, the java compiler, and Scala is built "on top of" Java.  The .scala files that we write are converted into java bytecode, which then runs on the JVM.  This means Scala can take advantage of the ubiquity and optimization of the JVM, as well as taking advantage of Java libraries.

Scala:
- Is a high level language, meaning it abstracts away underlying hardware (runs on JVM)
- Is statically typed, meaning variables cannot change types.
- Has a type inference system, so type does not always need to be declared
  - var y: String = "hello"
  - var y = "hello"
  - ^ both of these lines have the same effect.
- Has a REPL (Read, Eval, Print, Loop).  The Scala REPL lets us run scala interactively, instead of always needing to write-compile-run our full program
- Compiles down to java bytecode and is interoperable with Java.  Runs on the JVM, so understanding JVM architecture means we understand how Scala runs
  - we'll discuss stack + heap + pass by value/reference later
- Supports almost all Java functionality (no checked exceptions), draws inspiration from other languages as well.
- Supports OOP Paradigm
  - Achieves this by supporting Java functionality, with a few tweaks.  We have classes and objects, objects have state and behaviour, 4 pillars of OOP, ...
- Supports FP Paradigm
  - Functions are first-class citizens in Scala, meaning we can pass functions in as arguments, return functions from other functions, and store functions in variables and collections.
  - We won't do real FP, but we will make use of map, reduce, filter, fold, pure functions, higher-order functions.

#### Programming Paradigms

##### Imperative Programming

In the imperative paradigm, we write our program as a list of instructions for the computer to carry out.  This works fine to a point, but doesn't scale well for large teams or complex applications.

##### Object Oriented Programming

In the OOP paradigm, we write our program by defining classes and instantiating objects from those classes.  The program functions via the interaction + evolution of objects over time.  Toy examples of OOP use classes like Dog or Truck, in practice your classes are more like JsonParser, CliFactory, ApplicationContext, LoadTimeWeaver, ...  Often OOP applications are structured using Design Patterns.

##### Functional Programming

In the FP paradigm, we write our program by defining pure functions and our program executes via the application and composition of pure functions.  We apply functions to immutable data and compose functions to achieve our program's goals.  We won't do full FP in training, but we will use some FP concepts:
- Function: something that takes an input and produces an output
- Pure Function: a function that only takes an input and produces an output.  It doesn't modify the input, it doesn't read data other than the input, and it doesn't do anything other than produce an output.  It has no *side effects*.  A pure function, run with the same input, will *always* produce the same output, which makes pure functions very easy to test.
- Higher order function: a function that takes another function as an argument, returns a function, or both.  Notable higher order functions: map, reduce, filter, foreach.

#### JVM Internals: Stack and Heap

Understanding the memory usage and execution of our scala applications makes debugging *much* easier.  We don't need to go into great detail, but we should learn to understand all the steps the JVM goes through when executing a basic program.  There are 2 areas of memory we're concerned about inside the JVM:  the Stack and the Heap.  The Stack is where program execution happens.  The JVM executes the method/function at the top of the stack and holds all functions/methods yet to be executed below the currently-executing function/method on the stack.  The Heap is where objects are stored.  Each object on the Heap can be found using its address, though addresses are JVM addresses rather than underlying hardware memory addresses.

We've discussed an entrypoint to our scala applications, the main method.  When we run our application, the stack begins empty, and the first thing that happens is the main method goes on the stack.  The main method, being on top of the stack, starts execution.  In our demo, our main method only has a single line, Basics.run.  This line calls the run() method in the Basics object.  New method calls, including this one, go on top of the stack.  Our stack in the JVM is a stack (the data structure), meaning it is LIFO, Last-In-First-Out.  Execution of main() is paused, since it is no longer on top of the stack, and execution of run() begins, since it *is* on top of the stack.  The first thing Basics.run does is println.  println is a function call, function calls go on the stack, so println goes on top of the stack and begins execution...

Each time an object is created, that object is stored on the Heap.  When we, for instance, call new Basics("red", -3), that constructor call goes on the stack, executes, creates the appropriate object on the Heap, and returns a reference to that object.  The created object itself is always stored on the Heap.  Our variable/value contains only a reference.  Variables and values exist in the context of some method/function execution on the stack.  This is called the "stack frame" and each method/function execution gets its own stack frame.  The stack frame is how local scope is implemented in scala.

#### Exceptions and Errors

In Java/Scala, problems that occur during runtime are represented with two different possible objects: Exception and Error.  These objects are similar, they both subclass a rarely-used-directly class called Throwable, but Error and Exception ought be used differently.  An Exception is used when some exceptional behaviour occurs, and we typically attempt to recover from Exceptions.  An Erorr is used, most often by Java libraries, when a problem occurs with the JVM itself.  We typically cannot or should not attempt to recover from Errors.

Exception examples: ArrayIndexOutOfBoundsException, FileNotFoundException, ArithmeticException, NullPointerException, ...
(NPE occurs when we try to call a method or access a field on something that is null.  This is always the case)
null is a special value for reference types that indicates the reference does not point to an object
Error examples: OutOfMemoryError (no more space on Heap), StackOverflowError (no more space on Stack)

We attempt to recover from exceptions using try-catch blocks.  Code that may cause an exception is written in the try block, and machinery for handling specific exceptions is written in the catch block.  Try-catch can be combined with finally to make try-catch-finally.  In Scala we don't use multiple catch blocks, instead we just put multiple cases inside our catch block.  Code executed in a try block can have its exceptions caught even through many nested methods, though the throwing of Exceptions/Errors interrupts normal program flow.  In our code, we'll be writing our own Exceptions, throwing Exceptions, catching and handling those Exceptions.  We want to throw Exceptions when problems occur, and we want to catch and handle Exceptions where we have the ability to fix those problems.

Note that throwing Exceptions is a side effect, so Scala provides tools to handle problems without touching Exceptions or try-catch.  Try and Option are useful types for FP style problem solving.

