#!/bin/sh
echo "Compilation in process..."
rm ../bin/*
javac -d ./../bin ./../Encheres/*
echo "Compilation completed."
