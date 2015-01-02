name := "spark-dbf"

version := "0.1"

organization := "com.esri"

scalaVersion := "2.10.4"

resolvers += "Local Maven Repository" at "file:///"+Path.userHome+"/.m2/repository"

libraryDependencies += "com.esri" % "Shapefile" % "1.3.1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.2.0" % "provided"

libraryDependencies += "org.apache.avro" % "avro" % "1.7.7" exclude("org.mortbay.jetty", "servlet-api")

libraryDependencies += "org.apache.avro" % "avro-mapred" % "1.7.7" exclude("org.mortbay.jetty", "servlet-api")

publishMavenStyle := true

pomExtra := (
  <url>https://github.com/mraad/spark-dbf</url>
    <licenses>
      <license>
        <name>Apache License, Verision 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:mraad/spark-dbf.git</url>
      <connection>scm:git:git@github.com:mraad/spark-dbf.git</connection>
    </scm>
    <developers>
      <developer>
        <id>mraad</id>
        <name>Mansour Raad</name>
        <url>https://github.com/mraad</url>
        <email>mraad@esri.com</email>
      </developer>
    </developers>)

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

libraryDependencies += "junit" % "junit" % "4.12" % "test"
