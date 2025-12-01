@echo off
call cjk.bat
echo Ejecutando POJO Credito...
echo --------------------------
java -cp "POJO_Credito.jar;lib/*" pojo_credito.POJO_Credito
pause