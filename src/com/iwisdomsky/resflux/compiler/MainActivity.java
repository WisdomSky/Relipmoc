package com.iwisdomsky.resflux.compiler;

import android.app.*;
import android.content.res.*;
import android.os.*;
import com.mycompany.io.*;
import java.io.*;
import android.content.pm.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		try
		{
			Resources res = getPackageManager().getResourcesForApplication("ph.wisdomsky.resxploit2");
			//res.getIdentifier(
		}
		catch (PackageManager.NameNotFoundException e)
		{}

		StringWriterOutputStream sw = new StringWriterOutputStream();
		StringWriterOutputStream sw1 = new StringWriterOutputStream();
		System.setErr(new PrintStream(sw1));
		System.setOut(new PrintStream(sw));
		
		IDE ide = new IDE();
		String source = "/mnt/sdcard/AppProjects/MyApp2/";
		String android_jar = "/mnt/sdcard/.aide/android.jar";
		String app_name = "haha";
		
		try
		{
			Runtime.getRuntime().exec("su -c sh /system/!/apk.sh "+app_name +" "+ source + " " + android_jar).waitFor();
		}
		catch (Exception e)
		{}

		StringBuilder args = new StringBuilder();
		args.append("-verbose -deprecation -extdirs \"\" -1.5");
		args.append(" -bootclasspath "+android_jar);
		//args.append(" -classpath "+source+"gen");
		//args.append(":"+source+"gen");
		args.append(" -d "+source+"classes");
		args.append(" "+source+"gen/com/mycompany/myapp2/R.java");
		ide.fnCompile(args.toString());
		
		args.setLength(0);
		args.append("--dex --output="+source+"classes.dex");
		args.append(" "+source+"classes");
		ide.fnDx(args.toString());
		
		args.setLength(0);
		args.append(source+app_name+".apk.unsigned");
		args.append(" -u -z "+source+app_name+".apk.res"); // signing is not working with apkbuilder
		args.append(" -f "+source+"classes.dex");
		ide.fnApkBuilder(args.toString());
		
		args.setLength(0);
		args.append("-M testkey");
		args.append(" -I "+source+app_name+".apk.unsigned");
		args.append(" -O "+source+app_name+".apk");
		ide.fnSignApk(args.toString());
		
		new File(source+app_name+".apk.unsigned").delete();
		new File(source+app_name+".apk.res").delete();
		new File(source+"classes.dex").delete();
		for( File f : new File(source+"classes").listFiles())
			f.delete();
		for( File f : new File(source+"gen").listFiles())
			f.delete();
		
		
		new AlertDialog.Builder(this).setMessage(sw.toString()+"\n\n"+sw1.toString()).create().show();
		
		
    }
	
	

	
	
	
	
}
