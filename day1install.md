We’ll need to start by installing WSL2 + Ubuntu 18+ + Windows Terminal.  Go through each step carefully:

- https://docs.microsoft.com/en-us/windows/wsl/install-win10#manual-installation-steps
- https://www.microsoft.com/store/apps/9N9TNGVNDL3Q
- https://docs.microsoft.com/en-us/windows/terminal/get-started


This will require a restart.  You’ll know all is well when you’re able to open Windows terminal and select Ubuntu from the dropdown at the top.  You’ll have a prompt that begins with username@computername

Next we’ll need to get our IDE: VSCode.  Download it here: https://code.visualstudio.com/

We’ll need a few VSCode extensions.  You can reach the extensions by clicking on the boxes icon on the left hand side of the screen:
- Remote - WSL, from Microsoft
- Scala Syntax (official), from scala-lang
- Scala Metals

I’m not sure whether it’s better to install these Locally or on WSL.  Both seem to work in my testing, but we may discover one is better than the other.

With this, VSCode should be setup for basic Scala development.  Your Ubuntu environment probably isn’t yet.  You’ll need to install java and then sbt: 

- sudo apt-get update
- sudo apt-get upgrade
- sudo apt-get install openjdk-8-jdk

Then:
https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Linux.html
