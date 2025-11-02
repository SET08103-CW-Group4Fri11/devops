# SET08103 - Software Engineering Methods Project
<details>
<summary>Table of contents</summary>
<ol>
    <li><a href="#population-information-access-system">Population Information Access System</a></li>
    <li><a href="#about">About</a></li>
    <li><a href="#built-with">Built with</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#how-to-work-on-features">How to work on Features</a></li>
</ol>
</details>  

## Population Information Access System

### Aim
An organisation that reports on demographic statistics requires an easier way for analysts to access population information. This project designs and implements a system that works with a provided **SQL database** and presents results through a **GUI** so users can query, explore, and export population data efficiently.

> **Task brief**  
> “You work for an organisation that requires reporting on population information. You have been tasked with designing and implementing a new system to allow easy access to this population information. The organisation has provided you with an SQL database to work from available here: `<link or path to SQL DB>`.”

### Solution Overview
The system provides a backend layer to interact with the SQL database and a user interface for exploration and reporting. Although this is not a large-scale automated data pipeline, it **follows ETL (Extract, Transform, Load) principles in a simplified, hybrid form**:

- **Extract** – Data is read from the provided SQL database.  
- **Transform** – SQL joins, filters, and aggregations produce report-ready datasets (e.g., ordering by population, calculating totals and percentages).  
- **Load** – Results are delivered to the GUI: some as **precomputed views/materialized views** for speed and repeatability, others as **dynamic, parameterised queries** driven by user input (e.g., “Top N … in region X”).

This hybrid approach balances performance (preprocessed summaries) and flexibility (on-demand queries), and is common in lightweight analytical applications.

### Data Flow Design (Hybrid ETL-Inspired)
Some reports are **preprocessed** (computed at startup or via a refresh command) and loaded into the GUI directly—ideal for totals, language counts, and city vs. non-city splits that don’t change during a run. Parameterised tasks (like **Top N**) are **dynamic**: the app runs the query with the user’s inputs and streams the ordered results into GUI tables. This keeps the interface responsive without maintaining many precomputed variants.

### Reports Required (Specification Coverage)

**Countries (ordered by population, DESC)**
- World, by continent, by region  
- Top **N** globally, top **N** by continent, top **N** by region

**Cities (ordered by population, DESC)**
- World, by continent, by region, by country, by district  
- Top **N** globally, top **N** by continent/region/country/district

**Capital cities (ordered by population, DESC)**
- World, by continent, by region  
- Top **N** globally/by continent/by region

**Population splits (people in cities vs. not in cities + %)**  
- Per continent, per region, per country

**Population totals (single values)**  
- World, continent, region, country, district, city

**Languages (speakers + % of world)**  
- Chinese, English, Hindi, Spanish, Arabic

  
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
After having set up your feature branch do your work making sure you are commiting often.  
Once you are happy with your work push it to the repo  
`git push`
And open a [pull request](https://github.com/BenArthurs/devops/pulls)  
When creating the pull request:
- base: develop
- compare: feature/myfeature
- Complete any other info as best as you can, such as, project, milestone, etc.
- After the pull request has been created, ask a colleague for a peer review if possible
### <ins>**Do never push directly to Develop, Main or Release**</ins>
### How to run DB only so you can call methods without using the App container
Run this on your terminal:
`docker compose up -d db`
Wait for the db to be initialised, then run your main or method that you need.
## Group 4 - Friday - 11am
### Group members
- [Ben Arthurs](https://github.com/BenArthurs)
- [Sanel Contreras](https://github.com/Patanja)
- [Adrian Cordero](https://github.com/adricr)
- [Matt Harlos](https://github.com/morival)


### Badges
![workflow](https://github.com/BenArthurs/devops/actions/workflows/setup.yml/badge.svg) <br/>
[![GitHub release](https://img.shields.io/github/v/release/BenArthurs/devops.svg)](https://github.com/BenArthurs/devops/releases) <br/>
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

