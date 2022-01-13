#!/bin/bash
cmhome=$CONTROLM/cm/AFT
cd $cmhome
if [ "$#" == 5 ] ; then
./JRE_LINK/bin/java  -jar exe/converter-1.0.jar $*
RESULT=$?
if [ "$RESULT" -eq 0 ]
 then
    echo "SUCCESS"
    exit 0
else
    echo "FAILURE"
    exit 1
fi
elif [ "$#" != 5 ] ; then
 echo "Wrong parameters"
 echo "Use of parameters:
       1. Source full file path name
       2. Dest full file path name
       3. Conversion table full file path name
       4. Conversion direction: ETOA or ATOE
       5. Record length"
 exit 1
fi

