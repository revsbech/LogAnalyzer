#.xs$(OBJ_EXT):
#        $(XSUBPPRUN) $(XSPROTOARG) $(XSUBPPARGS) $*.xs > $*.xsc && $(MV) $*.xsc $*.c
#        $(CCCMD) $(CCCDLFLAGS) "-I$(PERL_INC)" $(PASTHRU_DEFINE) $(DEFINE) $*.c

JAVA_COMPILER = javac
JAVA_RUNNER = java

CLASSPATH = ".:/opt/local/share/java/hadoop-0.20.203.0/hadoop-core-0.20.203.0.jar:../classes/json-lib-2.4-jdk15.jar:../classes/commons-io-2.1.jar:../classes/commons-lang3-3.1.jar:../classes/json_simple-1.1.jar"

all: LogEntry.class ApacheCombinedLogEntry.class CalculateBytesMapper.class CalculateBytesReducer.class LogAnalyzer.class


%.class : %.java
	echo $_
	$(JAVA_COMPILER) -classpath $(CLASSPATH) $<

run: LogAnalyzer.class
	java LogAnalyzer

clean:
	rm *.class