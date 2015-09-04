#https://ant.apache.org/manual/tutorial-HelloWorldWithAnt.html

all:

compile:
	time javac -sourcepath src -d build/classes/ src/filesystemcaches/FileSystemCaches.java
	#' Para ejecutar siga el siguiente orden
	#' 

run:	
	time java -cp build/classes/ filesystemcaches.FileSystemCaches 5
	#' Ejecutando...