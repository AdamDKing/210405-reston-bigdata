### Databases

A Database is a program the stores data for you.  Typically databases are accessed over a network, though we can access databases running on our own machine.  Right now we're accessing a postgres database as if we were connecting over a network using DBeaver, even though it's running on our local machine.  A database can do many different things for us, but they almost always make efficient storage and retrieval of our data possible.  For projects running in production, databases are almost always preferred over file storage (for most access patterns, most ways that we use data).  We're going to be focusing this week on postgres, which is an RDBMS: Relational DataBase Management System.
Advantages of an RDBMS over writing to file:
- Provides and enforces structure on the stored data (RDBMS won't permit bad data to be added)
- Makes it easy and efficient to search + query stored data (RDBMS lets us store data so that our access paterns are efficient)
  - major tools: indexes and mat views.  Indexes build a search tree based on a column, mat views essentially cache retrieved data for fast access. Not an exhaustive list.
- Scalability is a major advantage.  As a rule of thumb, RDBMSs work well storing up to 1TB of data.  That rule of thumb may be outdated.  Beyond that 1TB, most databases and RDBMSs provide the tools to run on a cluster and scale further, though different DBs may make it easier/more difficult.
- Popularity of SQL.  Structured Query Language has been used to manage/create/modify/access data in databases since before I was born.  If your data is managed by an application that uses SQL, you'll always have developers.  Because SQL is so popular, it appears even outside of RDBMSs.
- Concurrent Access + authentication: your RDBMS provides the tools to have multiple users simultaneously accessing data, and it controls the access of those users according to permissions.  Access is managed used a SQL sublanguage call Data Control Language (DCL), which we won't see much of.
  - Concurrent access is a difficult problem, one that every DB system needs to provide a solution for.  Postgres and mongo both allow us to tune the solution to better suit our needs.
  - The user of a database is almost never the end-user, but is instead an application.
- Data integrity: RDBMS protects against bad/corrupt data being enterred into the database.
  - is guaranteed in each transaction in an RDBMS (each interaction with the DB)
  - transactions are written to a DB log as they occur, so changes that would introduce bad data are rolled back.

### Note on NoSQL vs SQL

SQL database refers to an RDBMS: postgres, mysql, oraclesql, mssql, ... Each of these vendors provides their own RDBMS that reads their own "dialect" of SQL.  SQL written for a postgres database is *slightly* different from SQL written for a mysql database.  There are also "NoSQL" databases.  You might have a NoSQL database bill itself as "No SQL", as in without SQL, or it might bill itself as "Not Only SQL".  There are some NoSQL databases that allow you to use SQL.  In general, NoSQL vs SQL is more of a tendency than a brute fact.  Your DB software exists to efficiently store + query data, SQL vendors take good ideas from NoSQL databases and vice versa.

### SQL Sublanguages

Sublanguages are just categorizations of SQL keywords, splitting the language into different parts based on different functionalities.  Some people split SQL into 5 sublanguages, some people split it into 4.  We'll list the 5, to be safe:
- DML (Data Manipulation Language): used to add/remove/modify individual records stored in tables.  Keywords here: INSERT, UPDATE, DELETE
- DDL (Data Definition Language): used to add/remove/modify tables and other database structures (schemas, databases, sequences, views, ...).  Keywords here: CREATE, ALTER, DROP, TRUNCATE (TRUNCATE removes all content from a table without deleting it)
- DQL (Data Query Language): used to retrieve data from the database.  Sometimes grouped in with DML.  Keyword here: SELECT.  SELECT has clauses: SELECT, FROM, WHERE, GROUP BY, HAVING, ORDER BY, OFFSET, LIMIT
- TCL (Transaction Control Language): used to delimit database transactions and save/rollback those transactions.  Keywords here: BEGIN, COMMIT, SAVEPOINT, ROLLBACK.  Default transaction behaviour in Postgres is to commit (save to DB) after every SQL statement.  Statements grouped together in a transaction will succeed or fail as a group (Atomicity).
- DCL (Data Control Language): used to control users and access to the DB.  Keywords: GRANT and REVOKE

### SQL avenues:
- More in depth SQL querying w/ some exercises
- learning about normalization + creating a sensible data model for your CLI application
- connecting a scala application to postgres, getting dependencies using sbt ***
- ACID and BASE, DB transactions and isolation levels, DB on a cluster, CAP Theorem
  - See a little MongoDB here

### Dependency management with sbt
the Scala Build Tool manages dependencies for us, so we mostly don't need to worry about finding the java files that we need or making sure those java files are available to our application.  Instead, we'll find some dependencies on mvnrepository and let sbt handle the files for us.