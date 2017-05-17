#!/bin/sh
echo "del project"
rm -rf /usr/java/project/gemo/JX1
rm -rf /usr/java/project/gemo/JX2
echo "unzip project"
unzip -oq /usr/java/project/JXDome1.war -d /usr/java/project/gemo/JX1
unzip -oq /usr/java/project/JXDome2.war -d /usr/java/project/gemo/JX2

