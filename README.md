Map reduce framework for parsing Apache Logentries
==================================================

This package contains my very first Map-Reduce java program for parsing Apache
logfiles on a massive scale using Hadoop. It takes as input a file in the format

SOURCE	APACHE_LOG_ENTRY

Where SOURCE is something loke file:://server//var/log/apache.log as outputed from
the LogStash log-collection program which we use at MOC to collect logfiles from 
many webservers.

Be warned that this is my very first map-reduce program, and also my first Java program
in over 10 years! 
I'm no Java-programmer, I usually code PHP for a living, and my code might show this i 
subtle, and not-so-subtle ways.

Compiling and running
---------------------

Compile using 

 ant clean
 ant jar
 
And run with 

 haddop jar build/jar/LagAnalyer.jar [COMMAND] [INPUT_DIR] [OUTPUT_DIR]

Where COMMAND is either browser or geoip::country and INPUT_DIR is a hdfs
directory (or local when running in pseudo mode) path to where your
correctly formatted logfiles reside.

The output will be a file with first column being the browser og country code (depending
on which command you gave the program), and column two will be two numbers, the first is the
hit-count, and the second the byte-count
 
Example
-------

 hadoop jar build/jar/LogAnalyzer.jar browser res/access-sample.log out/
 
   