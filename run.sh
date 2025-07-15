#!/bin/bash

SRC_DIR="src"
OUT_DIR="bin"
MAIN_CLASS="com.rmsca.Main"

mkdir -p $OUT_DIR

echo "Cleaning old class files..."
rm -rf $OUT_DIR/*

echo "Compiling files..."
javac -d $OUT_DIR $SRC_DIR/com/rmsca/*.java

if [ $? -eq 0 ]; then
	echo "Compile successful!"
	echo ""
	java -cp $OUT_DIR $MAIN_CLASS
fi
