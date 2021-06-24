javac -d classes -classpath "lib\*" src\com\unomas\client\*.java src\com\unomas\controller\*.java src\com\unomas\model\cards\*.java src\com\unomas\model\player\*.java src\com\unomas\util\*.java

jar --create --file unomas-1.0.jar -C classes .