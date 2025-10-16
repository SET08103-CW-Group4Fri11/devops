### Use Case: City Reports

| **Use Case #**             | Generate City Report #1                                                       |
|----------------------------|-------------------------------------------------------------------------------|
| Goal in Context:           | Data Analyst issues request directly to our company, expects a city report    |
| Scope:                     | Company                                                                       | 
| Level:                     | Primary task                                                                  |
| Initiating Actor:          | Data Analyst                                                                  |
| Preconditions:             | The system has a correspondent database with city data                        |
| Successful End Conditions: | Data Analyst receives Report of all cities in the world ordered by population |
| Failed End Conditions:     | No report is generated                                                        |
| Trigger:                   | 'City Report' option is selected                                              |

### Flow of Events for Main Success Scenario:

| # | Direction | Actor Action &harr; System Response                                                                         |
|---|:---------:|-------------------------------------------------------------------------------------------------------------|
| 1 |  &rarr;   | Data Analyst attempts to access system                                                                      |
| 2 |  &larr;   | System verifies access and prompts available Report options                                                 |
| 3 |  &rarr;   | Data Analyst selects 'City Reports' section                                                                 |
| 4 |  &larr;   | System prompts available City Report options                                                                |
| 5 |  &rarr;   | Data Analyst selects 'All Cities in the World'                                                              |
| 6 |  &larr;   | System retrieves and sorts city data by population                                                          |
| 7 |  &larr;   | System displays report with following columns:<br/> - Name<br/> - Country<br/> - District<br/> - Population |

### Flow of Events for Extensions (Alternate Scenarios):

| #  | Direction | Actor Action &harr; System Response     |
|----|:---------:|-----------------------------------------|
| a1 |  &rarr;   | Data Analyst attempts to access system  |
| a2 |  &larr;   | The system fails to connect to the database and displays an error message to the Data Analyst             |
| a3 |  &larr;   | The Data Analyst is prompted to retry or contact support                                  |

(The arrows on the left indicate whether the action is initiated by the actor (&rarr;) or the system (&larr;).)

### Sub-variations
| #  | Actor | variation Details                                                                                                                                                                                                                                                                                                                   |
|----|-------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 3. | Data Analyst may select: | - 'Country Report' <br/> - 'Capital Report' <br/> - 'Population Report'                                                                                                                                                                                                                                               |
| 5. | Data Analyst selects: | - 'All Cities in a Continent' <br/>- 'All Cities in a Region' <br/> - 'All Cities in a Country' <br/> - 'All Cities in a District' <br/> - 'Top N Cities in the World' <br/> - 'Top N Cities in a Continent' <br/> - 'Top N Cities in a Region' <br/> - 'Top N Cities in a Country' <br/> - 'Top N Cities in a District' |


### Schedule
DUE DATE: Final Release
