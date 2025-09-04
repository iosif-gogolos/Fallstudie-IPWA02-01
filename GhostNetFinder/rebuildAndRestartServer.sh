mvn clean package

cp target/ghostnetfinder.war /Users/iosifgogolos/git/Fallstudie-IPWA02-01/apache-tomcat-11.0.4/webapps/

sh ../apache-tomcat-11.0.4/bin/shutdown.sh
sh ../apache-tomcat-11.0.4/bin/startup.sh

