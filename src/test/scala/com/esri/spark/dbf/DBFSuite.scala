package com.esri.spark.dbf

import com.esri.core.geometry.Point
import org.apache.spark.sql.test._
import org.scalatest.FunSuite

/* Implicits */

import org.apache.spark.sql.test.TestSQLContext._

/**
  */
class DBFSuite extends FunSuite {

  val dbfFile = "src/test/resources/test.dbf"

  test("dsl test") {
    val results = TestSQLContext
      .dbfFile(dbfFile)
      .select('AText)
      .collect()

    assert(results.size === 2)
  }

  test("sql test") {
    sql( s"""
        |CREATE TEMPORARY TABLE dbfTable
        |USING com.esri.spark.dbf
        |OPTIONS (path "$dbfFile")
      """.stripMargin.replaceAll("\n", " "))

    assert(sql("SELECT * FROM dbfTable").collect().size === 2)
  }

}
