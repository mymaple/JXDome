#!/bin/sh
echo "close tomcat"
/usr/java/tomcat/tomcat1/bin/shutdown.sh
#/usr/java/tomcat/tomcat2/bin/shutdown.sh
#kill tomcat pid
pidlist=`ps -ef|grep tomcat|grep -v "grep"|awk '{print $2}'`
if [ "$pidlist" = "" ]
   then
       echo "no tomcat pid alive!"
else
  echo "tomcat Id list :$pidlist"
  kill -9 $pidlist
  echo "KILL $pidlist:"
  echo "service stop success"
fi
echo "start tomcat"
/usr/java/tomcat/tomcat1/bin/startup.sh
#/usr/java/tomcat/tomcat2/bin/startup.sh
