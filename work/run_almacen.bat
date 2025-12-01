@echo off
call cjk.bat
echo Ejecutando POJO Almacen...
echo --------------------------
java -cp "POJO_Almacen.jar;lib/*" pojo_almacen.POJO_Almacen
pause