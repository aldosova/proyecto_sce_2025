@echo off
call cjk.bat
echo Ejecutando POJO Envios...
echo -------------------------
java -cp "POJO_Envios.jar;lib/*" pojo_envios.POJO_Envios
pause