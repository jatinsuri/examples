// Eclipse is weird because it compiles .groovy files present as resources
// Running gradle from command line does not have this problem.
// As a workaround, logback-test.xml is also present which is only used
// when running test cases from eclipse

appender("STDOUT", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
	  pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %M - %msg%n"
	}
  }
  
  root(DEBUG, ["STDOUT"])