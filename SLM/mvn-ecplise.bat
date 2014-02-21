cd /D %~dp0
call mvn clean eclipse:clean eclipse:eclipse -DdownloadSources=true -DdownloadJavaDoc=true -Dwtpversion=2.0
pause