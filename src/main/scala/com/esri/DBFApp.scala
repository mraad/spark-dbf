package com.esri

import com.esri.core.geometry
import com.esri.core.geometry.{GeometryEngine, Point}
import com.esri.dbf.{DBFField, DBFHeader, DBFReader, DBFType}
import com.esri.spark.dbf._
import com.twitter.chill.KryoSerializer
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}


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
        .dbfFile("/Users/mraad_admin/Dropbox/Public/trips1M.dbf")
        .registerTempTable("trips")

      sqlContext.registerFunction("ST_DISTANCE", (x1: Float, y1: Float, x2: Float, y2: Float) =>
        GeometryEngine.geodesicDistanceOnWGS84(new Point(x1, y1), new geometry.Point(x2, y2)))

      sql("select tripdist,ST_DISTANCE(plon,plat,dlon,dlat) from trips limit 20").foreach(println)

    }
    finally {
      sc.stop()
    }
  }
}
