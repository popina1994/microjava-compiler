#Java system paths
JAVAC=C\:/Program\ Files/Java/jdk1.8.0_111/bin/javac
JAVA=C\:/Program\ Files/Java/jdk1.8.0_111/bin/java
#Project path
ROOT=C\:/pp1\ domaci/workspace/MJCompiler/
LIB=$(ROOT)lib/
TEST=$(ROOT)test/
BIN=$(ROOT)bin/
SRC=$(ROOT)src/
PACKAGE=rs/ac/bg/etf/pp1/
SRC_UTIL=util
#Flags for unit tests
FLAG_TYPE=sem
FLAG_RUN_PREV=true
FLAG_RUN_BUILD=true
FLAG_TEST_NUM=-1
JAVA_FLAGS=-t $(FLAG_TYPE) -n $(FLAG_TEST_NUM) -b $(FLAG_RUN_BUILD) -rp $(FLAG_RUN_PREV)
run:
	 $(JAVA) -cp $(BIN)\;$(LIB)* unit.Main $(JAVA_FLAGS)
build:
	$(JAVAC) -cp $(LIB)\* $(TEST)unit/*.java $(TEST)utility/*.java -d $(BIN)
clean:
	rm -r $(BIN)*
