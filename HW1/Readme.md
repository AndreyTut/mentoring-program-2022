# Databases

[Database creation script](createDB.sql)  
[Database structure](db_structure.png)
  
### Index types  
  
#### Find user by name (exact match)  
  
|Index type|Size|Cost|Time|
|----|---|---|---|
|Without index|2208|0.00..2464.00|0.899..395.313|
|btree index|5312 |12.04..1235.06|1.934..33.846|
|hash index|6320 |39.75..1262.77|4.126..310.992|
|gin index|2488 |0.00..2464.00|0.048..22.009|
|gist index|6256 |0.00..2464.00|0.688..36.698  
  
#### Find user by surname (partial match)

|Index type|Size|Cost|Time|
|----|---|---|---|
|Without index|2208|0.00..2464.00|2.271..545.678|
|btree index|2912 |0.29-4.31|0.048..15.765|
|hash index|8280kB |8.02|0.053..16.829|
|gin index|2344 |0.00..2484 .00|0.065..16.724|
|gist index|6256 |0.00..2484 .00|0.083..21.012|  

#### Find user by phone number (partial match)

|Index type|Size|Cost|Time|
|----|---|---|---|
|Without index|2208|0.00..2464.00|9.437..678.052|
|btree index|3184  |0.00..2464.00|0.549..31.489|
|hash index|6320 |0.00..2464.00|1.167..30.290|
|gin index|3424  |0.00..2464.00|0.332..23.117|
|gist index|5520  |0.00..2464.00|0.384..23.163

#### Find user with marks by user surname (partial match)

|Index type|Size|Cost|Time|
|----|---|---|---|
|Without index|2208|2956.77..14640.20|399.198..1597.826|
|btree index|2944   |2956.77..14640.20|355.239..1539.948|
|hash index|7944  |2956.77..14640.20|59.879..816.740|
|gin index|2488   |2956.77..14640.20|309.331..877.250|
|gist index|6257   |2956.77..14640.20|52.070..623.282
    
#### Data insertion time with different types of index

|Index type|Index creating time (empty table)|Index creating time (filled table)|Data import time|
|---|---|---|---|
|btree|15 ms|497 ms|14 sec, 115 ms|
|hash|20 ms|458 ms|13 sec, 999 ms|
|gin|14 ms|341 ms|12 sec, 409 ms|
|gist|12 ms| 2 s 879 ms|17 sec, 769 ms |  
    
  

#### Add trigger that will update column updated_datetime to current date in case of updating any of student
[trigger script](trigger.sql)  
#### Add validation on DB level that will check username on special characters (reject student name with next characters '@', '#', '$').  
[constraint script](constraint.sql)  
  
#### Create snapshot that will contain next data: student name, student surname, subject name, mark (snapshot means that in case of changing some data in source table – your snapshot should not change)
[snapshot script](snapshot.sql)  
  
#### Create function that will return average mark for input user. 
[avarage mark for student script](avrgMarkStud.sql)  
  
#### Create function that will return avarage mark for input subject name. 
[avarage mark for subject name script](avrgMarkSbj.sql)   
  
#### Create function that will return student at "red zone" (red zone means at least 2 marks <=3)  
[red zone script](red_zone.sql)