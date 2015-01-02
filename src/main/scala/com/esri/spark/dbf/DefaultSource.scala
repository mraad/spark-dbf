package com.esri.spark.dbf

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources.{BaseRelation, RelationProvider}

/**
 * Provides access to dbf data from pure SQL statements (i.e. for users of the JDBC server).
 */
class DefaultSource extends RelationProvider {
  /**
   * Creates a new relation for data store in dbf given a `path` as a parameter.
   */
  override def createRelation(
                               sqlContext: SQLContext,
                               parameters: Map[String, String]): BaseRelation = {
    DBFRelation(parameters("path"))(sqlContext)
  }
}
