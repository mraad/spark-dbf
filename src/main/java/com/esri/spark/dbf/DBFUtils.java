package com.esri.spark.dbf;

import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;

/**
 * A collection of static functions for working with dbf in Spark SQL
 */
public class DBFUtils
{
    /**
     * Returns a Schema RDD for the given dbf path.
     */
    public static JavaSchemaRDD dbfFile(final JavaSQLContext sqlContext, final String path)
    {
        final DBFRelation relation = new DBFRelation(path, sqlContext.sqlContext());
        return sqlContext.baseRelationToSchemaRDD(relation);
    }

}
