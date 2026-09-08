
# Tool Setup

This documents describes how to setup all tools on Linux/Ubuntu
and on Windows.
Here is a summary of the tools we need:

 * [Java JDK 17 or later](https://adoptopenjdk.net/)
 * [sbt](https://www.scala-sbt.org/)
 * [IntelliJ](https://www.jetbrains.com/idea/download/) (the free Community version)
 * or [Visual Studio Code](https://code.visualstudio.com/)
   * with the [Scala Metals](https://scalameta.org/metals/docs/editors/vscode.html) extension
 * [GTKWave](http://gtkwave.sourceforge.net/) (optional)
 * make, git (for command line usage)


## Chisel

Chisel is *just* a library for Scala. And Scala is just a language that executes
on the Java virtual machine (JVM) and uses the Java library. Therefore, you need to have
[Java JDK](https://adoptopenjdk.net/) installed on your laptop.

For working on the command line you should also install
[sbt](https://www.scala-sbt.org/), the Scala build tool.
Please note that installing sbt will make the IntelliJ-build process a lot easier as well.

A nice editor for Chisel/Scala is
[IntelliJ](https://www.jetbrains.com/idea/download/). At the first start
of IntelliJ download the Scala plugin (at Download featured plugins).

[Visual Studio Code](https://code.visualstudio.com/) is also a good alternative, but you need to install the
[Scala Metals](https://scalameta.org/metals/docs/editors/vscode.html)
extension to get syntax highlighting and code completion.

### Scala-cli

Another optional tool is [Scala-cli](https://scala-cli.virtuslab.org/).
It is a command line tool to run Scala code and scripts.
Furthermore, you can use it for interactive Scala sessions (REPL).
REPL stands for Read-Eval-Print Loop, a simple interactive programming environment.

## Ubuntu/Linux

 * Install Java JDK and other tools with:
 * ```sudo apt install openjdk-17-jdk git make gtkwave```
 * Install sbt according to the instructions from [sbt download](https://www.scala-sbt.org/download.html)
 * Install IntelliJ and the Scala plugin with a launch shortcut in favorites


## Windows

### Native Windows

 * Install OpenJDK 17 or later from [AdoptOpenJDK](https://adoptopenjdk.net/)
 * Install [sbt](https://www.scala-sbt.org/)
 * Install [IntelliJ](https://www.jetbrains.com/idea/download/)
   * The community edition
   * Include Create Desktop Shortcut
 * Download the [GTKWave binaries](https://sourceforge.net/projects/gtkwave/files/)
   * Select the latest release that matches the pattern ```gtkwave-{release number}-bin-win32```
   * Extract the downloaded ```.zip``` into a directory of choice
   * Run the executable ```gtkwave.exe``` in the folder ```gtkwave\bin\```
   * Put a link to the executable on the desktop
 * Install a [git client](https://git-scm.com/download/win)

 ### WSL

  * If you want to use the Windows Subsystem for Linux (WSL) you can follow the instructions in the [WSL documentation](https://docs.microsoft.com/en-us/windows/wsl/install).
  * Then follow the Linux instructions above for Java, sbt, and IntelliJ.

## macOS

 * Install OpenJDK 17 or later from [AdoptOpenJDK](https://adoptopenjdk.net/)
   - alternatively you can use [Homebrew](https://brew.sh/) to install OpenJDK or 
     [SDKMAN](https://sdkman.io/) to install the latest Java version
 * Install sbt with ```brew install sbt```
 * Install [GTKWave](http://gtkwave.sourceforge.net/)
   * For MacOS 14 following installation is possible:
   * `brew install --HEAD randomplum/gtkwave/gtkwave`
   * see [this issue](https://github.com/gtkwave/gtkwave/issues/250)
 * Install [IntelliJ](https://www.jetbrains.com/idea/download/)
   * The community edition
   * Include Create Desktop Shortcut
 * Start IntelliJ to finish the setup
   * Select the light UI theme (if you prefer)
   * On the featured plugins select Install for Scala
   * When importing a project, select the JDK you installed before
     * On Project JDK select *New*
     * Select *JDK*
     * Select the path to your OpenJDK installation

## Java (and sbt) with SDKMAN

If you want to use the latest Java version and related tool, you can use [SDKMAN](https://sdkman.io/).

## Installation Check

The installation shall be checked with the first exercise in [lab0](lab0).

Or a quick, partial check with following commands in a terminal/shell

```
javac
sbt
```