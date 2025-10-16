# USE CASE: Produce Country reports that are sorted by population

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Data Analyst* I want to *see different Tops regarding countries depending on certain geographical constraints* so that * I can assess and compare countries and create my own statistics*

### Scope

Company.

### Level

Primary task.

### Preconditions

The system has a correspondent database with country data.

### Success End Condition

A report is available that shows a top with the number of countries the user inputted.

### Failed End Condition

No report is produced.

### Primary Actor

Data Analyst.

### Trigger

Tha Data Analyst selects an option to generate a country report.

## MAIN SUCCESS SCENARIO

1. The Data Analyst access the system and navigates to the Country reports section
2. The system prompts the available report options for the Data Analyst to choose.
3. The Data Analyst chooses to see a report with a top of all countries in the world.
4. The system prompts the user to input the amount of countries they'd like to see in the report
5. The Data Analyst inputs the number
6. The system validates the inputted value
7. The system retrieves the requested data and sorts it by population, placing first the countries with the largest populations.
8. The system displays the resulting report, showing for each country the following information:
   - Code
   - Name
   - Continent
   - Region
   - Population
   - Capital
9. The Data Analyst views the report and uses the data accordingly.
## EXTENSIONS
###  

### 3a. **The Data Analyst chooses to see a report with a top of all countries in a continent**:
1. The system prompts the Data Analyst to input the continent's name.
2. The Data Analyst inserts the continent's name
3. The system validates that the continent's name is correct and it exists in the database
4. If the input is incorrect, the system prompts the user about it and allows them to re-select
### 3b. **The Data Analyst chooses to see a report with a top of all countries in a region**:
1. The system prompts the Data Analyst to input the region's name.
2. The Data Analyst inserts the region's name
3. The system validates that the region's name is correct and it exists in the database
4. If the input is incorrect, the system prompts the user about it and allows them to re-select
### 3c. **The Data Analyst chooses an incorrect report type**:
1. The system prompts the Data Analyst about their selection being incorrect and allows them to retry the selection.
### 4a. **The system cannot retrieve the requested data**:
1. The system prompts the Data Analyst about the error and takes them to the report selection screen.
### 6a. **The system cannot process the value, due to a validation error**:
1. The system prompts the Data Analyst about the error and allows them to re-enter the value.
### 1-5. **The Data Analyst chooses to exit the application**:
1. The system closes the application.
## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Final Release