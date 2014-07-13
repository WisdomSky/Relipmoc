#!/bin/sh


chdir $2
mkdir gen

# Generate R.java
aapt p -m -J "gen" -M "AndroidManifest.xml" -S res -I $3 -f -F $1".apk.res"