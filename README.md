# Agile Hardware Design (02201)

Material for the [Agile Hardware Design Course](https://kurser.dtu.dk/course/02201) at DTU.

Agile hardware design with Chisel (Constructing Hardware in a Scala Embedded Language) is a specialized course that focuses on using the Chisel hardware description language (HDL) as part of the agile hardware design process. Chisel is a modern hardware description language that enables designers to create digital circuits with concise, high-level code that is easier to write and maintain than traditional HDLs.

This course is designed for students who have a background in digital design and are interested in learning how to apply agile methodologies to hardware design using Chisel. The course will cover the principles of agile hardware design, the Chisel HDL, and how to use Chisel to create efficient, flexible, and maintainable hardware designs.

The course covers the following topics:

1. Introduction to Agile Hardware Design with Chisel: an overview of agile design in general and hardware design and the Chisel HDL, including its syntax, features, and benefits.

2. Agile Hardware Design Process with Chisel: agile hardware design process using Chisel, including requirements gathering, design, prototyping, testing, and deployment.

3. Chisel Design Patterns: design patterns and idioms used in Chisel, such as generators, parameterization, and functional abstraction.

4. Verification of Designs: Verification with Chisel Test, test-driven development, continuous integration, formal verification.

5. Agile Hardware Design Case Studies: a project of a real-world hardware design project that uses Chisel and agile methodologies, providing students with practical examples of how to apply these concepts in their work.

Overall, the course will provide the students with a comprehensive understanding of agile hardware design with Chisel, enabling them to design and implement complex digital circuits via generators.

## Getting Started

 * Install the tools as described in [Setup.md](Setup.md).
 * Test the installation with the [lab0](lab0) example design and test.

## Course Info

 * Instructor: Martin Schoeberl
 * Email: masca@dtu.dk
 * When: Tuesdays 13:00-17:00
 * Place: 421.013

 ## Lecture Plan

 ### Week 1: Agile and Scala

  * [Slides](01_scala.pdf)
  * Lab: [lab0](lab0) and [lab1](lab1)
  * Reading
    - [Agile Manifesto](https://agilemanifesto.org/)
    - [Scala Collections](https://docs.scala-lang.org/overviews/collections-2.13/overview.html)
    - 10.1 from the [Chisel book](https://www.imm.dtu.dk/~masca/chisel-book.html)

### Week 2: Introduction to Chisel

  * [Slides](02_chisel.pdf)
  * Labs from the Digital Electroncs 2 course: [Chisel Lab 2](https://github.com/schoeberl/chisel-lab/tree/master/lab2) and [Chisel Lab 3](https://github.com/schoeberl/chisel-lab/tree/master/lab3)
 * Reading: Chapter 1 to 6 from the [Chisel book](https://www.imm.dtu.dk/~masca/chisel-book.html)
### Week 3: Simple Generators (Kasper)

 * [Slides](03_simp_gen.pdf)
 * Labs: [lab2](lab2) and [lab3](lab3)
 * Reading: 10.2, and 10.3 from the [Chisel book](https://www.imm.dtu.dk/~masca/chisel-book.html)

### Week 4: Generators

 * [Slides](04_generators.pdf)
 * Lab: [lab4](lab4)
 * Reading
   - 10.4, 10.5, 10.6, and 11.3 from the [Chisel book](https://www.imm.dtu.dk/~masca/chisel-book.html)
   - [Scala defined hardware generators for Chisel](https://www.sciencedirect.com/science/article/pii/S014193312500050X)

### Week 5: Digital Design with LLMs in Industry (Ioannis)

Experiences of using LLMs at Microchip.

 * Project presentations
 * Lab: project work
 * Reading: [Creating an Agile Hardware Design Flow](https://cs.stanford.edu/~niemetz/publications/2020/DAC2020.pdf)


### Week 6: Testing and CI (Emad)

 * [Slides](06_testing_and_ci.pdf)
 * Lab: [lab6](lab6)
 * Reading: Chapter 13 from the [Chisel book](https://www.imm.dtu.dk/~masca/chisel-book.html)

 ### Week 7: Co-Simulation and Scrum

 * [Slides](07_co_sim.pdf)
 * Lab: [lab7](lab7)

 ### Week 8: Project Presentation

  * Including presentation of a MVP

 ### Week 9: TBD

  * Lab: standup presentation (3 minutes per group, no slides), project work

 ### Week 10: Formal Verification (Amelia)

  * Lab: verification lab
  * Standup presentation (2 minutes per group, no slides), project work

### Week 11: Spade, and Using Your Language in Agile Design (Frans)

  * [Slides](spade.pdf)
  * Install Spade, see [Spade Setup](https://docs.spade-lang.org/agile/quick_setup.html)
  * Lab: [https://docs.spade-lang.org/agile/intro.html]
 
### Week xx: Chisel in Research (Maybe)

 * [Slides](tbd.pdf)

### Week 12: Project Work

 * Lab: Project work

### Week 13: Project Presentations

  * Lab: Final project presentation and demo

## Group Projects 2026

 * [Group 1: Advanced Encryption Standard encryption/decryption](https://github.com/gluetex/Agile-HW-Design-AES)
 * [Group 2: Image Encryption Hardware Accelerator using Ascon-AEAD128](https://github.com/LordWicked/image-encryption-accelerator)
 * [Group 3: Parameterizable Cache Generator](https://github.com/ertugrulkilic5713/Agile-Design-Project-Parameterized-Cache-Generator)
 * [Group 4: ECC Generator](https://github.com/StefanBuzu/ecc-gen)
 * [Group 5: Hardware-Accelerated JSON Parsing / Validation Engine](https://github.com/koch-c/chisel-json-accelerator)
 * [Group 6: Systolic Arrays for AI acceleration](https://github.com/SystolicArraysChisel/Systolic-Arrays-Chisel)
 * [Group 7: A Configurable Chisel Generator for Quantized Convolutional Neural Network Inference](https://github.com/Jfvind/chisel-cnn-generator)
 * [Group 8: Hardware Minesweeper with an On-Chip Deductive Solver](https://github.com/s262007/Minesweeper-Group-Project)
 * [Group 10: Agile FFT Project](https://github.com/Fewrick/Agile-FFT-Project)
 * [Group 11: Hardware Chess Engine Generator on an FPGA](https://github.com/CharleezGithub/Hardware-Chess-Engine)
 * [Group 12: Parameterizable Live Controlled Synthesizer](https://github.com/bathtuballigator/chisel-synth)
 * [Group 14: BPU-Gen: Parameterized Branch Predictor Generator](https://github.com/Thodorhs/BPU-gen)

## Group Projects 2025

 1. [Computer Vision for image classification](https://github.com/WAAAlex1/Agile-hw-dev-project-ComVis)
 1. [Sha-256 hasher](TBD)
 1. [Random Number Generation](https://github.com/rifkifi/Agile-HW-Design-RNG)
 1. [Real-time Mandelbrot fractal visualization](https://github.com/komv123/AgileHWProject)
 1. [Chisel sound effects](https://github.com/oteyatosys/chisel-sound-effects)
 1. [Chisel checkers](https://github.com/jaller698/Chisel-checkers)
 1. [GPU-Like Vector Processor](TBD)
 1. [FFT Core](https://github.com/lasseslips/fft-core)

## Agile Tutorial

 * [Agile Hardware Design Tutorial](agile_hw.pdf) at FPL and OrConf 2026

## Further Material

 * [The Chisel book](https://www.imm.dtu.dk/~masca/chisel-book.html)
 * [Scott's Agile Hardware Design Course](https://scottbeamer.net/cse228a-sp25/project/index.html) and [slide sources](https://github.com/agile-hw/lectures)
 * [Agile SW on Wikipedia](https://en.wikipedia.org/wiki/Agile_software_development)

 ### Papers

 * [Scala defined hardware generators for Chisel](https://www.sciencedirect.com/science/article/pii/S014193312500050X)
 * [Creating an Agile Hardware Design Flow](https://cs.stanford.edu/~niemetz/publications/2020/DAC2020.pdf)

 ### Agile Reading

  * [The New New Product Development Game](https://hbr.org/1986/01/the-new-new-product-development-game) by Hirotaka Takeuchi and Ikujiro Nonaka, Game. Harvard Business Review, Jan./Feb. 1986: 285–305.
  * [Scrum: The Art of Doing Twice the Work in Half the Time](https://www.amazon.com/Scrum-Doing-Twice-Half-Time/dp/038534645X) by Jeff Sutherland and J.J. Sutherland.
  * [Agile Manifesto](https://agilemanifesto.org/)
