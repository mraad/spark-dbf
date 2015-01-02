package com.esri;

import com.esri.core.geometry.Point;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.DataType;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.Row;
import org.apache.spark.sql.api.java.UDF2;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.Serializable;

/**
 */
public class JavaTestSuite implements Serializable
{

    private transient JavaSparkContext sc;
    private transient JavaSQLContext sqlContext;

    @Before
    public void setUp()
    {
        sc = new JavaSparkContext("local", "JavaAPISuite");
        sqlContext = new JavaSQLContext(sc);
    }

    @After
    public void tearDown()
    {
        sc.stop();
        sc = null;
    }

    @SuppressWarnings("unchecked")
    @Test
    @Ignore
    public void pointTest()
    {
        sqlContext.registerFunction("ST_POINT", new UDF2<Double, Double, Point>()
        {
            @Override
            public Point call(final Double x, final Double y) throws Exception
            {
                return new Point(x, y);
            }
        }, DataType.BinaryType);

        Row result = (Row) sqlContext.sql("SELECT ST_POINT(1.0, 2.0)")
                                     .first();
        // System.out.println("result.toString() = " + result.toString());
        // assert (result.get(0).);
    }

}
