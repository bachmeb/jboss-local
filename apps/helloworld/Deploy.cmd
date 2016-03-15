@echo off 

set JAVA_HOME=C:\DEV\java\6\64\jdk1.6.0_45
echo %JAVA_HOME%
pause

set JBossHome=C:\DEV\jboss\eap\5.1.0\jboss-as
echo %JBossHome%
pause

set ASDF="%JAVA_HOME%\bin\jar.exe" -cvf helloworld.war *.jsp WEB-INF 
echo %ASDF%
pause

%ASDF%

copy helloworld.war "%JBossHome%\server\default\deploy

pause 