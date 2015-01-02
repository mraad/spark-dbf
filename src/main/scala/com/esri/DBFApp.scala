package com.esri

import com.esri.dbf.{DBFField, DBFHeader, DBFReader, DBFType}
import com.esri.spark.dbf._
import com.twitter.chill.KryoSerializer
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}


/*
class Inc(val add: Double = 1.0) extends Serializable {
  def inc(x: Double) = x + add
}
*/

object DBFApp {

  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("DBFApp")
      .set("spark.driver.memory", "2g")
      .set("spark.executor.memory", "2g")
      .set("spark.serializer", KryoSerializer.getClass().getName)
      .registerKryoClasses(Array(
      classOf[DBFType],
      classOf[DBFField],
      classOf[DBFHeader],
      classOf[DBFReader],
      classOf[DBFRelation]
    ))

    val sc = new SparkContext(conf)
    try {

      val sqlContext = new SQLContext(sc)
      import sqlContext._

      sqlContext
        .dbfFile("/Users/mraad_admin/Share/trips1M.dbf")
        .registerTempTable("trips")

      // val inc = new Inc(2.0)
      // sqlContext.registerFunction("hex", (x: Double) => inc.inc(x))

      sql("select min(plon),max(plon),min(plat),max(plat) from trips where plon between -180 and 0 and plat between -90 and 90").foreach(println)

    }
    finally {
      sc.stop()
    }
  }
}
