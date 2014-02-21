@echo off
echo JAVA_HOME=%JAVA_HOME%
cd /D %~dp0
call mvn -v
pause