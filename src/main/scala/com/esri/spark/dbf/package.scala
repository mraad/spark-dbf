package com.esri.spark

import org.apache.spark.sql.SQLContext

package object dbf {

  /**
   * Adds a method, `dbfFile`, to SQLContext that allows reading data stored in DBF.
   */
  implicit class DBFSQLContext(sqlContext: SQLContext) {
    def dbfFile(filePath: String) =
      sqlContext.baseRelationToSchemaRDD(DBFRelation(filePath)(sqlContext))
  }

}
