# Spark SQL DBF Library

A library for querying [DBF](http://www.digitalpreservation.gov/formats/fdd/fdd000325.shtml) data with [Spark SQL](http://spark.apache.org/docs/latest/sql-programming-guide.html).

### This is work in progress

## Requirements
This library requires Spark 1.2+ and depends on my [Shapefile](https://github.com/mraad/Shapefile) github project.

## Building
Typically, [SBT](http://www.scala-sbt.org/) is used to build Scala based projects, but, I'm using [Maven](http://maven.apache.org/) to build this one.
The pom.xml has plugins to compile scala and java sources.

Make sure to first clone and install the [Shapefile](https://github.com/mraad/Shapefile) project.

```
$ mvn clean install
```

## Linking
You can link against this library in your program at the following coordinates:

```
groupId: com.esri
artifactId: spark-dbf
version: 0.1
```

The spark-dbf jar file can also be added to a Spark using the `--jars` command line option.
For example, to include it when starting the spark shell:

```
$ bin/spark-shell --jars spark-dbf-0.1.jar
```

## Examples

The following are based on the first 1 million records of the [NYC taxi trips](http://chriswhong.com/).
you can download the sample dbf from [here](https://dl.dropboxusercontent.com/u/2193160/trips1M.dbf)


```
$ wget https://dl.dropboxusercontent.com/u/2193160/trips1M.dbf
```

### Scala API

```
import org.apache.spark.sql.SQLContext

val sqlContext = new SQLContext(sc)

import sqlContext._

import com.esri.spark.dbf._

val trips = sqlContext.dbfFile("/Users/mraad_admin/Dropbox/Public/trips1M.dbf")

trips.schema.fields.foreach(println)

trips.registerTempTable("trips")

sql("select count(*)").collect

sql("select tripdist from trips order by tripdist desc limit 10").collect

```

### Python and SQL API
DBF data can be queried in pure SQL or from python by registering the data as a temporary table.


```sql
CREATE TEMPORARY TABLE trips
USING com.esri.spark.dbf
OPTIONS (path "trips1M.dbf")
```

### Java API
Avro files can be read using static functions in AvroUtils.


```java
import com.esri.spark.dbf.DBFUtils;

JavaSchemaRDD episodes = DBFUtils.dbfFile(sqlContext, "trips1M.dbf");
```
