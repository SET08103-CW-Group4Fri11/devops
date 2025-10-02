# SET08103 - Software Engineering Methods Project
<details>
<summary>Table of contents</summary>
1. [About](#about)
2. [Built with](#built-with)
3. [Getting Started](#getting-started)
4. [How to work on Features](#how-to-work-on-features)
</details>
## About
This repository contains the coursework for the Software Engineering Methods group project
## Built with
- Java
- Docker
- Mysql
- Intellij IDEA
- ...
## Getting Started
### Prerequisites
In order to be able to work on this repository you will need:
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- JDK 17
- An IDE, preferably [IntelliJ IDEA](https://www.jetbrains.com/idea/) (comes with JDK)
- Maven (if you are not packaging from IntelliJ)
### Reminder for devs
If you need to run the solution you will first need to build and package with maven, after that you can run the docker image (if you have one...)

## How to work on Features
After having cloned the repo and from the repo's root
```
git checkout develop
git pull
git branch feature/myfeature
git checkout feature/myfeature
```
After having set up your feature branch do your work making sure you are comitting often.  
Once you are happy with your work push it to the repo  
`git push`
And open a [pull request](https://github.com/BenArthurs/devops/pulls)  
When creating the pull request:
- base: develop
- compare: feature/myfeature
- Complete any other info as best as you can, such as, project, milestone, etc.
- After the pull request has been created, ask a colleague for a peer review if possible
### <ins>**Do never push directly to Develop, Main or Release**</ins>

## Group 4 - Friday - 11am
### Group members
- [Ben Arthurs](https://github.com/BenArthurs)
- [Sanel Contreras](https://github.com/Patanja)
- [Adrian Cordero](https://github.com/adricr)
- [Matt Harlos](https://github.com/morival)
