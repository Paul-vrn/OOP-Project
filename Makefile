compile:
	javac -d bin -classpath 'bin:bin/gui.jar' -sourcepath src src/main/Application.java

run:
	java -classpath 'bin:bin/gui.jar' main.Application $(filter-out $@,$(MAKECMDGOALS))

clean:
	rm -rf bin/*.class
	rm -rf bin/main
	rm -rf bin/production