# jboss eap 5.1.0 osx

## References
* https://access.redhat.com/articles/113083#Cert_5
* https://access.redhat.com/articles/111663
* http://jbossas.jboss.org/downloads
* https://access.redhat.com/articles/112673
* http://hiplab.mc.vanderbilt.edu/projects/soempi/jboss_startstop.html
* http://stackoverflow.com/questions/5415608/jboss-default-password
* http://centerkey.com/jboss/
* https://docs.jboss.org/jbossas/docs/Installation_Guide/4/html/setting_JBOSS_HOME_windows.html

In this tutorial $DEV refers to /Users/b/DEV on my system. For consistency, you can choose any folder you like to be your DEV folder. 

##### Download and install the Java JDK 
* https://github.com/bachmeb/java-jdk/blob/master/docs/osx/1.6.md

##### Login to the Red Hat Customer Portal
* https://access.redhat.com/

##### Download Application Platform 5.1.0 Binary (jboss-eap-5.1.0.zip)
* https://access.redhat.com/jbossnetwork/restricted/softwareDetail.html?softwareId=4063&product=appplatform&version=5.1.0&downloadType=distributions

##### Unpack the contents of jboss-eap-5.1.0.zip
* Target: $DEV/jboss/eap/5.1.0

##### Set JBOSS_HOME 
* Start > Control Panel > System > Advanced System Settings > Environment Variables > User variables > New
  * Variable name: JBOSS_HOME
  * Variable value: $DEV\jboss\eap\5.1.0\jboss-as

##### Change JAVA="$JAVA_HOME/bin/java" to JAVA="$JAVA_HOME/java" in run.conf
```bash
## -*- shell-script -*- ######################################################
##                                                                          ##
##  JBoss Bootstrap Script Configuration                                    ##
##                                                                          ##
##############################################################################

### $Id: run.conf 91533 2009-07-22 01:20:05Z gbadner $

#
# This shell script is sourced by run.sh to initialize the environment 
# variables that run.sh uses. It is recommended to use this file to
# configure these variables, rather than modifying run.sh itself. 
#

#
# Specify the maximum file descriptor limit, use "max" or "maximum" to use
# the default, as queried by the system.
#
# Defaults to "maximum"
#
#MAX_FD="maximum"

#
# Specify the JBoss Profiler configuration file to load.
#
# Default is to not load a JBoss Profiler configuration file.
#
#PROFILER=""

#
# Specify the location of the Java home directory.  If set then $JAVA will
# be defined to $JAVA_HOME/bin/java, else $JAVA will be "java".
#
#JAVA_HOME="/usr/java/jdk1.6.0"

JAVA_HOME="/Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands/"

#
# Specify the exact Java VM executable to use.
#
#JAVA=""

#
# Specify options to pass to the Java VM.
#
if [ "x$JAVA_OPTS" = "x" ]; then
   JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true"
fi
## Specify the Security Manager options
#JAVA_OPTS="$JAVA_OPTS -Djava.security.manager -Djava.security.policy=$POLICY"

# Sample JPDA settings for remote socket debugging
#JAVA_OPTS="$JAVA_OPTS -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n"

# Sample JPDA settings for shared memory debugging 
#JAVA_OPTS="$JAVA_OPTS -Xrunjdwp:transport=dt_shmem,address=jboss,server=y,suspend=n"
```

##### 
```bash
#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##  JBoss Bootstrap Script                                                  ##
##                                                                          ##
### ====================================================================== ###

### $Id: run.sh 107078 2010-07-26 05:45:48Z jiwils $ ###

DIRNAME=`dirname $0`
PROGNAME=`basename $0`
GREP="grep"

# Set conf if specified, else set to default
JBOSSCONF="default"
CONF_SPECIFIED=false

arg_count=1
eval SWITCH=\${$arg_count}
while [ ! -z "$SWITCH" ]
do

        if [ "$SWITCH" = "-c" ]; then
            eval JBOSSCONF=\$`expr $arg_count + 1`
            CONF_SPECIFIED=true
            break
        fi

        echo "$SWITCH" | grep "^\-\-configuration=" > /dev/null
        if [ $? -eq 0 ]; then
            JBOSSCONF=`echo $SWITCH|sed 's/\-\-configuration=//'`
            break
        fi

        arg_count=`expr $arg_count + 1`
        eval SWITCH=\${$arg_count}
done

if [ x${CONF_SPECIFIED} = "xfalse" ]
then
   set -- "-c" ${JBOSSCONF} $@
fi

# Use the maximum available, or set MAX_FD != -1 to use that
MAX_FD="maximum"

#
# Helper to complain.
#
warn() {
    echo "${PROGNAME}: $*"
}

#
# Helper to puke.
#
die() {
    warn $*
    exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false;
darwin=false;
linux=false;
case "`uname`" in
    CYGWIN*)
        cygwin=true
        ;;

    Darwin*)
        darwin=true
        ;;

    Linux)
        linux=true
        ;;
esac

# Read an optional running configuration file
if [ "x$RUN_CONF" = "x" ]; then

    if [ ! -z "$JBOSSCONF" ] && [ -f "$DIRNAME/../server/$JBOSSCONF/run.conf" ]; then
        RUN_CONF="$DIRNAME/../server/$JBOSSCONF/run.conf"
    else
        RUN_CONF="$DIRNAME/run.conf"
    fi
fi
if [ -r "$RUN_CONF" ]; then
    . "$RUN_CONF"
fi

# Force IPv4 on Linux systems since IPv6 doesn't work correctly with jdk5 and lower
if [ "$linux" = "true" ]; then
   JAVA_OPTS="$JAVA_OPTS -Djava.net.preferIPv4Stack=true"
fi

# For Cygwin, ensure paths are in UNIX format before anything is touched
if $cygwin ; then
    [ -n "$JBOSS_HOME" ] &&
        JBOSS_HOME=`cygpath --unix "$JBOSS_HOME"`
    [ -n "$JAVA_HOME" ] &&
        JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
    [ -n "$JAVAC_JAR" ] &&
        JAVAC_JAR=`cygpath --unix "$JAVAC_JAR"`
fi

# Setup JBOSS_HOME
if [ "x$JBOSS_HOME" = "x" ]; then
    # get the full path (without any relative bits)
    JBOSS_HOME=`cd $DIRNAME/..; pwd`
fi
export JBOSS_HOME

# Increase the maximum file descriptors if we can
if [ "$cygwin" = "false" ]; then
    MAX_FD_LIMIT=`ulimit -H -n`
    if [ "$?" -eq 0 ]; then
        # Darwin does not allow RLIMIT_INFINITY on file soft limit
        if [ "$darwin" = "true" -a "$MAX_FD_LIMIT" = "unlimited" ]; then
            MAX_FD_LIMIT=`/usr/sbin/sysctl -n kern.maxfilesperproc`
        fi

	if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ]; then
	    # use the system max
	    MAX_FD="$MAX_FD_LIMIT"
	fi

	ulimit -n $MAX_FD
	if [ "$?" -ne 0 ]; then
	    warn "Could not set maximum file descriptor limit: $MAX_FD"
	fi
    else
	warn "Could not query system maximum file descriptor limit: $MAX_FD_LIMIT"
    fi
fi

# Setup the JVM
if [ "x$JAVA" = "x" ]; then
    if [ "x$JAVA_HOME" != "x" ]; then
	#JAVA="$JAVA_HOME/bin/java"
  JAVA="$JAVA_HOME/java"
    else
	JAVA="java"
    fi
fi

# Setup the classpath
runjar="$JBOSS_HOME/bin/run.jar"
if [ ! -f "$runjar" ]; then
    die "Missing required file: $runjar"
fi
JBOSS_BOOT_CLASSPATH="$runjar"

# Tomcat uses the JDT Compiler
# Only include tools.jar if someone wants to use the JDK instead.
# compatible distribution which JAVA_HOME points to
if [ "x$JAVAC_JAR" = "x" ]; then
    JAVAC_JAR_FILE="$JAVA_HOME/lib/tools.jar"
else
    JAVAC_JAR_FILE="$JAVAC_JAR"
fi
if [ ! -f "$JAVAC_JAR_FILE" ]; then
   # MacOSX does not have a seperate tools.jar
   if [ "$darwin" != "true" -a "x$JAVAC_JAR" != "x" ]; then
      warn "Missing file: JAVAC_JAR=$JAVAC_JAR"
      warn "Unexpected results may occur."
   fi
   JAVAC_JAR_FILE=
fi

if [ "x$JBOSS_CLASSPATH" = "x" ]; then
    JBOSS_CLASSPATH="$JBOSS_BOOT_CLASSPATH"
else
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JBOSS_BOOT_CLASSPATH"
fi
if [ "x$JAVAC_JAR_FILE" != "x" ]; then
    JBOSS_CLASSPATH="$JBOSS_CLASSPATH:$JAVAC_JAR_FILE"
fi

# Check for -d32/-d64 in JAVA_OPTS
JVM_OPTVERSION="-version"
JVM_D64_OPTION=`echo $JAVA_OPTS | $GREP "\-d64"`
JVM_D32_OPTION=`echo $JAVA_OPTS | $GREP "\-d32"`
test "x$JVM_D64_OPTION" != "x" && JVM_OPTVERSION="-d64 $JVM_OPTVERSION"
test "x$JVM_D32_OPTION" != "x" && JVM_OPTVERSION="-d32 $JVM_OPTVERSION"

# If -server not set in JAVA_OPTS, set it, if supported
SERVER_SET=`echo $JAVA_OPTS | $GREP "\-server"`
if [ "x$SERVER_SET" = "x" ]; then

    # Check for SUN(tm) JVM w/ HotSpot support
    if [ "x$HAS_HOTSPOT" = "x" ]; then
        HAS_HOTSPOT=`"$JAVA" $JVM_OPTVERSION -version 2>&1 | $GREP -i HotSpot`
    fi

    # Check for OpenJDK JVM w/server support
    if [ "x$HAS_OPENJDK_" = "x" ]; then
        HAS_OPENJDK=`"$JAVA" $JVM_OPTVERSION 2>&1 | $GREP -i OpenJDK`
    fi

    # Enable -server if we have Hotspot or OpenJDK, unless we can't
    if [ "x$HAS_HOTSPOT" != "x" -o "x$HAS_OPENJDK" != "x" ]; then
        # MacOS does not support -server flag
        if [ "$darwin" != "true" ]; then
            JAVA_OPTS="-server $JAVA_OPTS"
            JVM_OPTVERSION="-server $JVM_OPTVERSION"
        fi
    fi
else
    JVM_OPTVERSION="-server $JVM_OPTVERSION"
fi

# Setup JBoss Native library path
#
if [ -d "$JBOSS_HOME/../native/lib" ]; then
    JBOSS_NATIVE_DIR=`cd "$JBOSS_HOME/../native" && pwd`
elif [ -d "$JBOSS_HOME/native/lib" ]; then
    JBOSS_NATIVE_DIR=`cd "$JBOSS_HOME/native" && pwd`
elif [ -d "$JBOSS_HOME/../native/lib64" ]; then
    JBOSS_NATIVE_DIR=`cd "$JBOSS_HOME/../native" && pwd`
elif [ -d "$JBOSS_HOME/native/lib64" ]; then
    JBOSS_NATIVE_DIR=`cd "$JBOSS_HOME/native" && pwd`
elif [ -d "$JBOSS_HOME/../../jboss-ep-5.1/native" ]; then
    JBOSS_NATIVE_DIR=`cd "$JBOSS_HOME/../../jboss-ep-5.1/native" && pwd`
fi
if [ -d "$JBOSS_NATIVE_DIR" ]; then
    if $cygwin; then
        JBOSS_NATIVE_DIR="$JBOSS_NATIVE_DIR/bin"
        export PATH="$JBOSS_NATIVE_DIR:$PATH"
        JBOSS_NATIVE_LIBPATH=`cygpath --path --windows "$JBOSS_NATIVE_DIR"`
    else
        IS_64_BIT_JVM=`"$JAVA" $JVM_OPTVERSION 2>&1 | $GREP -i 64-bit`
        if [ "x$IS_64_BIT_JVM" != "x" ]; then
            JBOSS_NATIVE_DIR="$JBOSS_NATIVE_DIR/lib64"
        else
            JBOSS_NATIVE_DIR="$JBOSS_NATIVE_DIR/lib"
        fi
        if [ "x$LD_LIBRARY_PATH" = "x" ]; then
          LD_LIBRARY_PATH="$JBOSS_NATIVE_DIR"
        else
          LD_LIBRARY_PATH="$JBOSS_NATIVE_DIR:$LD_LIBRARY_PATH"
        fi
        export LD_LIBRARY_PATH
        JBOSS_NATIVE_LIBPATH=$LD_LIBRARY_PATH
    fi
    if [ "x$JAVA_OPTS" = "x" ]; then
        JAVA_OPTS="-Djava.library.path=$JBOSS_NATIVE_LIBPATH"
    else
        JAVA_OPTS="$JAVA_OPTS -Djava.library.path=$JBOSS_NATIVE_LIBPATH"
    fi
fi

# Setup JBoss specific properties
JAVA_OPTS="-Dprogram.name=$PROGNAME $JAVA_OPTS"

# Setup the java endorsed dirs
JBOSS_ENDORSED_DIRS="$JBOSS_HOME/lib/endorsed"

# For Cygwin, switch paths to Windows format before running java
if $cygwin; then
    JBOSS_HOME=`cygpath --path --windows "$JBOSS_HOME"`
    JAVA_HOME=`cygpath --path --windows "$JAVA_HOME"`
    JBOSS_CLASSPATH=`cygpath --path --windows "$JBOSS_CLASSPATH"`
    JBOSS_ENDORSED_DIRS=`cygpath --path --windows "$JBOSS_ENDORSED_DIRS"`
fi

# Display our environment
echo "========================================================================="
echo ""
echo "  JBoss Bootstrap Environment"
echo ""
echo "  JBOSS_HOME: $JBOSS_HOME"
echo ""
echo "  JAVA: $JAVA"
echo ""
echo "  JAVA_OPTS: $JAVA_OPTS"
echo ""
echo "  CLASSPATH: $JBOSS_CLASSPATH"
echo ""
echo "========================================================================="
echo ""

while true; do
   if [ "x$LAUNCH_JBOSS_IN_BACKGROUND" = "x" ]; then
      # Execute the JVM in the foreground
      eval \"$JAVA\" $JAVA_OPTS \
         -Djava.endorsed.dirs=\"$JBOSS_ENDORSED_DIRS\" \
         -classpath \"$JBOSS_CLASSPATH\" \
         org.jboss.Main "$@"
      JBOSS_STATUS=$?
   else
      # Execute the JVM in the background
      eval \"$JAVA\" $JAVA_OPTS \
         -Djava.endorsed.dirs=\"$JBOSS_ENDORSED_DIRS\" \
         -classpath \"$JBOSS_CLASSPATH\" \
         org.jboss.Main "$@" "&"
      JBOSS_PID=$!
      # Trap common signals and relay them to the jboss process
      trap "kill -HUP  $JBOSS_PID" HUP
      trap "kill -TERM $JBOSS_PID" INT
      trap "kill -QUIT $JBOSS_PID" QUIT
      trap "kill -PIPE $JBOSS_PID" PIPE
      trap "kill -TERM $JBOSS_PID" TERM
      if [ "x$JBOSS_PIDFILE" != "x" ]; then
        echo $JBOSS_PID > $JBOSS_PIDFILE
      fi
      # Wait until the background process exits
      WAIT_STATUS=128
      while [ "$WAIT_STATUS" -ge 128 ]; do
         wait $JBOSS_PID 2>/dev/null
         WAIT_STATUS=$?
         if [ "$WAIT_STATUS" -gt 128 ]; then
            SIGNAL=`expr $WAIT_STATUS - 128`
            SIGNAL_NAME=`kill -l $SIGNAL`
            echo "*** JBossAS process ($JBOSS_PID) received $SIGNAL_NAME signal ***" >&2
         fi
      done
      if [ "$WAIT_STATUS" -lt 127 ]; then
         JBOSS_STATUS=$WAIT_STATUS
      else
         JBOSS_STATUS=0
      fi
      if [ "$JBOSS_STATUS" -ne 10 ]; then
            # Wait for a complete shudown
            wait $JBOSS_PID 2>/dev/null
      fi
   fi
   # If restart doesn't work, check you are running JBossAS 4.0.4+
   #    http://jira.jboss.com/jira/browse/JBAS-2483
   # or the following if you're running Red Hat 7.0
   #    http://developer.java.sun.com/developer/bugParade/bugs/4465334.html
   if [ "$JBOSS_STATUS" -eq 10 ]; then
      echo "Restarting JBoss..."
   else
      exit $JBOSS_STATUS
   fi
done
```

##### Start JBoss from Terminal
	$JBOSS_HOME/bin/run.sh
```
=========================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: /Users/b/DEV/jboss/eap/5.1.0/jboss-as

  JAVA: /Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands//bin/java

  JAVA_OPTS: -Dprogram.name=run.sh -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true

  CLASSPATH: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/bin/run.jar

=========================================================================

./run.sh: line 286: /Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands//bin/java: No such file or directory
Brians-MacBook-Air:bin b$ ./run.sh 
=========================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: /Users/b/DEV/jboss/eap/5.1.0/jboss-as

  JAVA: /Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands//java

  JAVA_OPTS: -Dprogram.name=run.sh -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true

  CLASSPATH: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/bin/run.jar

=========================================================================

18:43:33,682 INFO  [ServerImpl] Starting JBoss (Microcontainer)...
18:43:33,683 INFO  [ServerImpl] Release ID: JBoss [EAP] 5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)
18:43:33,684 INFO  [ServerImpl] Bootstrap URL: null
18:43:33,684 INFO  [ServerImpl] Home Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as
18:43:33,684 INFO  [ServerImpl] Home URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/
18:43:33,684 INFO  [ServerImpl] Library URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/lib/
18:43:33,685 INFO  [ServerImpl] Patch URL: null
18:43:33,685 INFO  [ServerImpl] Common Base URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/common/
18:43:33,686 INFO  [ServerImpl] Common Library URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/common/lib/
18:43:33,686 INFO  [ServerImpl] Server Name: default
18:43:33,686 INFO  [ServerImpl] Server Base Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server
18:43:33,687 INFO  [ServerImpl] Server Base URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/
18:43:33,687 INFO  [ServerImpl] Server Config URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/conf/
18:43:33,687 INFO  [ServerImpl] Server Home Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default
18:43:33,687 INFO  [ServerImpl] Server Home URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/
18:43:33,688 INFO  [ServerImpl] Server Data Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/data
18:43:33,688 INFO  [ServerImpl] Server Library URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/lib/
18:43:33,688 INFO  [ServerImpl] Server Log Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/log
18:43:33,688 INFO  [ServerImpl] Server Native Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/tmp/native
18:43:33,689 INFO  [ServerImpl] Server Temp Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/tmp
18:43:33,689 INFO  [ServerImpl] Server Temp Deploy Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/tmp/deploy
18:43:34,530 INFO  [ServerImpl] Starting Microcontainer, bootstrapURL=file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/conf/bootstrap.xml
18:43:35,153 INFO  [VFSCacheFactory] Initializing VFSCache [org.jboss.virtual.plugins.cache.CombinedVFSCache]
18:43:35,156 INFO  [VFSCacheFactory] Using VFSCache [CombinedVFSCache[real-cache: null]]
18:43:35,457 INFO  [CopyMechanism] VFS temp dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/tmp
18:43:35,458 INFO  [ZipEntryContext] VFS force nested jars copy-mode is enabled.
18:43:36,994 INFO  [ServerInfo] Java version: 1.6.0_65,Apple Inc.
18:43:36,994 INFO  [ServerInfo] Java Runtime: Java(TM) SE Runtime Environment (build 1.6.0_65-b14-462-11M4833)
18:43:36,994 INFO  [ServerInfo] Java VM: Java HotSpot(TM) 64-Bit Server VM 20.65-b04-462,Apple Inc.
18:43:36,994 INFO  [ServerInfo] OS-System: Mac OS X 10.10.5,x86_64
18:43:36,996 INFO  [ServerInfo] VM arguments: -Dprogram.name=run.sh -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Djava.endorsed.dirs=/Users/b/DEV/jboss/eap/5.1.0/jboss-as/lib/endorsed 
18:43:37,027 INFO  [JMXKernel] Legacy JMX core initialized
18:43:40,523 INFO  [WebService] Using RMI server codebase: http://127.0.0.1:8083/
18:43:43,888 INFO  [NativeServerConfig] JBoss Web Services - Stack Native Core
18:43:43,888 INFO  [NativeServerConfig] 3.1.2.SP7
18:43:44,947 INFO  [LogNotificationListener] Adding notification listener for logging mbean "jboss.system:service=Logging,type=Log4jService" to server org.jboss.mx.server.MBeanServerImpl@72a60191[ defaultDomain='jboss' ]
18:43:49,461 INFO  [MailService] Mail Service bound to java:/Mail
18:43:50,070 WARN  [JBossASSecurityMetadataStore] WARNING! POTENTIAL SECURITY RISK. It has been detected that the MessageSucker component which sucks messages from one node to another has not had its password changed from the installation default. Please see the JBoss Messaging user guide for instructions on how to do this.
18:43:51,031 INFO  [TransactionManagerService] JBossTS Transaction Service (JTA version - tag:JBOSSTS_4_6_1_GA_CP07) - JBoss Inc.
18:43:51,031 INFO  [TransactionManagerService] Setting up property manager MBean and JMX layer
18:43:51,274 INFO  [TransactionManagerService] Initializing recovery manager
18:43:51,386 INFO  [TransactionManagerService] Recovery manager configured
18:43:51,386 INFO  [TransactionManagerService] Binding TransactionManager JNDI Reference
18:43:51,411 INFO  [TransactionManagerService] Starting transaction recovery manager
18:43:51,781 INFO  [AprLifecycleListener] The Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: .:/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java
18:43:51,840 INFO  [Http11Protocol] Initializing Coyote HTTP/1.1 on http-127.0.0.1-8080
18:43:51,840 INFO  [AjpProtocol] Initializing Coyote AJP/1.3 on ajp-127.0.0.1-8009
18:43:51,866 INFO  [StandardService] Starting service jboss.web
18:43:51,870 INFO  [StandardEngine] Starting Servlet Engine: JBoss Web/2.1.10
18:43:51,929 INFO  [Catalina] Server startup in 88 ms
18:43:51,947 INFO  [TomcatDeployment] deploy, ctxPath=/web-console
18:43:52,661 INFO  [TomcatDeployment] deploy, ctxPath=/invoker
18:43:52,695 INFO  [TomcatDeployment] deploy, ctxPath=/jbossws
18:43:52,786 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jboss-local-jdbc.rar/META-INF/ra.xml
18:43:52,804 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jboss-xa-jdbc.rar/META-INF/ra.xml
18:43:52,813 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jms-ra.rar/META-INF/ra.xml
18:43:52,827 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/mail-ra.rar/META-INF/ra.xml
18:43:52,845 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/quartz-ra.rar/META-INF/ra.xml
18:43:52,934 INFO  [SimpleThreadPool] Job execution threads will use class loader of thread: main
18:43:52,960 INFO  [QuartzScheduler] Quartz Scheduler v.1.5.2 created.
18:43:52,962 INFO  [RAMJobStore] RAMJobStore initialized.
18:43:52,962 INFO  [StdSchedulerFactory] Quartz scheduler 'DefaultQuartzScheduler' initialized from default resource file in Quartz package: 'quartz.properties'
18:43:52,963 INFO  [StdSchedulerFactory] Quartz scheduler version: 1.5.2
18:43:52,964 INFO  [QuartzScheduler] Scheduler DefaultQuartzScheduler_$_NON_CLUSTERED started.
18:43:53,402 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=DataSourceBinding,name=DefaultDS' to JNDI name 'java:DefaultDS'
18:43:53,784 INFO  [ServerPeer] JBoss Messaging 1.4.7.GA server [0] started
18:43:53,870 INFO  [QueueService] Queue[/queue/DLQ] started, fullSize=200000, pageSize=2000, downCacheSize=2000
18:43:53,872 INFO  [QueueService] Queue[/queue/ExpiryQueue] started, fullSize=200000, pageSize=2000, downCacheSize=2000
18:43:53,929 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
18:43:53,929 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@1b9db122 started
18:43:53,930 INFO  [ConnectionFactoryJNDIMapper] supportsFailover attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support failover
18:43:53,930 INFO  [ConnectionFactoryJNDIMapper] supportsLoadBalancing attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support load balancing
18:43:53,932 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
18:43:53,932 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@2a31ac15 started
18:43:53,933 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
18:43:53,933 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@9bc2c97 started
18:43:54,036 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=ConnectionFactoryBinding,name=JmsXA' to JNDI name 'java:JmsXA'
18:43:54,162 INFO  [TomcatDeployment] deploy, ctxPath=/admin-console
18:43:54,244 INFO  [config] Initializing Mojarra (1.2_13-b01-FCS) for context '/admin-console'
18:43:56,622 INFO  [TomcatDeployment] deploy, ctxPath=/
18:43:56,675 INFO  [TomcatDeployment] deploy, ctxPath=/jmx-console
18:43:56,720 INFO  [ProfileServiceBootstrap] Loading profile: ProfileKey@15c76b4c[domain=default, server=default, name=default]
18:43:56,728 INFO  [Http11Protocol] Starting Coyote HTTP/1.1 on http-127.0.0.1-8080
18:43:56,742 INFO  [AjpProtocol] Starting Coyote AJP/1.3 on ajp-127.0.0.1-8009
18:43:56,748 INFO  [ServerImpl] JBoss (Microcontainer) [5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)] Started in 23s:54ms
```

##### Go to the JBoss welcome page
* http://localhost:8080/

##### Enable the admin username and password for the JBoss EAP Admin Console
* Edit $DEV\jboss\eap\5.1.0\jboss-as\server\default\deploy\management\console-mgr.sar\web-console.war\WEB-INF\classes\web-console-users.properties
```
# A sample users.properties file for use with the UsersRolesLoginModule
admin=admin
```
* Edit $DEV\jboss\eap\5.1.0\jboss-as\server\default\conf\props\jmx-console-users.properties
```
# A sample users.properties file for use with the UsersRolesLoginModule
admin=admin
```

##### Stop JBoss
	ctrl-c

##### Restart JBoss
	$JBOSS_HOME\bin\run.bat

##### Go to the admin console
* http://localhost:8080/admin-console/

##### Login with the admin credentials
* admin/admin

```
16:35:48,308 INFO  [BootstrapAction] Initializing Administration Console v1.3.4.SP1...
16:35:54,577 INFO  [PluginContainerResourceManager] Discovering Resources...
```

##### Go to the JMX console
* http://localhost:8080/jmx-console/

##### Login with the admin credentials
* admin/admin
