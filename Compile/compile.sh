#!/bin/sh
echo "Compilation in process..."
rm ../bin/*
javac -classpath ../lib/ojdbc11.jar -d ./../bin ./../Encheres/*.java
echo "Compilation completed."
if [ "$1" = "sergei" ]
then
scp ../bin/* s_kravtsov_cloud@verschneit:/app/encheres
echo "Exported to server."
elif [ "$1" = "houssain" ]
then
scp ../bin/* houssain@34.65.198.124:/app/encheres
echo "Exported to server."
elif [ "$1" = "william" ]
then
scp ../bin/* william@34.65.198.124:/app/encheres
echo "Exported to server."
else
echo "Publisher unspecified. Not exported."
fi


