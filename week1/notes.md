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

#### What is Scala?



