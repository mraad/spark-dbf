#!/bin/sh
dest=target
libs=${dest}/libs
spark-shell --jars ${dest}/spark-dbf-0.1.jar,${libs}/Shapefile-1.3.1.jar,${libs}/commons-io-2.4.jar,${libs}/esri-geometry-api-1.2.jar,${libs}/jackson-core-asl-1.9.11.jar,${libs}/json-20090211.jar
