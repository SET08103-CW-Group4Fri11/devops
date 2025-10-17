
| **Use Case #**             | USE CASE: Produce a report for the population of a District. |
|----------------------------|-------------------------------------------------------------|
| Goal in Context:           | To be able to request a report of the population of a district when provided with the distric.              |
| Scope:                     | Company               | 
| Level:                     | Primary Task                        |
| Initiating Actor:          | Data analysist             |
| Preconditions:             | The system has correspondence to a database filled with populations of districts.        |
| Successful End Conditions: | A report is return to the user, with the data that they requested (the population of requested district).                       |
| Failed End Conditions:     | No report is returned successfully.                        |
| Trigger:                   | The user (data analyst) requests a report to be generated, of the population is a specific district.                            |

Flow of Events for Main Success Scenario:

| # | Direction | Actor Action &harr; System Response |
|---|:---------:|-------------------------------------|
| 1 |  &rarr;   | Data analyst attempts to request the population of a district.                 |
| 2 |  &larr;   | System Propts user to select district for population report to be calculated in.          |
| 3 |  &rarr;   | User selects the district they want the report to be form for.                              |
| 4 |  &larr;   | System generates report of population in requested district.                              |
| 5 |  &larr;   | User is presented with report.                              |

Flow of Events for Extensions (Alternate Scenarios):

| #  | Direction | Actor Action &harr; System Response |
|----|:---------:|-------------------------------------|
| a1 |  &rarr;   | Data analyst attempts to request the population of a district.                 |
| a2 |  &larr;   | System fails to produce report.          |
| a3 |  &larr;   | System propts user to make another request.          |
| a4 |  &rarr;   | User requests again.                               |
