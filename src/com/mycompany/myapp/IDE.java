package com.mycompany.myapp;

import com.mycompany.io.*;
import com.mycompany.myapp.*;
import java.io.*;

//##################################################################
/** This class provides access to the Java development tools.
 * 
 * @since 01.06.2011
 * @author Tom Arn
 * @author www.t-arn.com
 */
public class IDE
//##################################################################
{

//===================================================================
public IDE () 
//===================================================================
{
 
}
//===================================================================
public int fnApkBuilder (String commandLine) 
//===================================================================
{
  return fnApkBuilder (fnTokenize(commandLine));
}
//===================================================================
public int fnApkBuilder (String[] args) 
//===================================================================
{
  long start=0;
  int i, rc = 99;
  try
  {
    // show arguments
    start = System.currentTimeMillis();
    System.out.println("ApkBuilder arguments:");
    for (i=0;i<args.length;i++) System.out.println(args[i]);
    System.out.println("");
    // call ApkBuilder
    rc = com.android.sdklib.build.ApkBuilderMain.main(args);
  }
  catch (Throwable t)
  {
    rc = 99;
    System.out.println("Error occurred!\n");
    t.printStackTrace();
  }
  System.out.println("\nDone in "+(System.currentTimeMillis()-start)/1000+" sec.\n");
  System.out.println("ExitValue: "+rc);
  return rc;
}//fnApkBuilder
//===================================================================
public int fnCompile (String commandLine) 
//===================================================================
{
  return fnCompile (fnTokenize(commandLine));
}
//===================================================================
public int fnCompile (String[] args) 
//===================================================================
{
  long start=0;
  int i, rc=99;
  boolean ok;
  org.eclipse.jdt.internal.compiler.batch.Main ecjMain;

  try
  {
    start = System.currentTimeMillis();
    System.out.println("Compilation arguments:");
    for (i=0;i<args.length;i++) System.out.println(args[i]);
    System.out.println("");
    // call compiler
    // new Main(new PrintWriter(System.out), new PrintWriter(System.err), true/*systemExit*/, null/*options*/, null/*progress*/).compile(argv);
    ecjMain = new org.eclipse.jdt.internal.compiler.batch.Main(new PrintWriter(System.out), new PrintWriter(System.err), false/*noSystemExit*/, null/*options*/, null/*progress*/);
    ok = ecjMain.compile(args);
    if (ok) rc = 0;
    else rc = 3;
    if (ecjMain.globalWarningsCount>0) rc = 1;
    if (ecjMain.globalErrorsCount>0) rc = 2;
  }
  catch (Throwable t)
  {
    rc = 99;
    System.out.println("Error occurred!\n");
    t.printStackTrace();
  }
  System.out.println("\nDone in "+(System.currentTimeMillis()-start)/1000+" sec.\n");
  System.out.println("ExitValue: "+rc);
  return rc;
}//fnCompile
//===================================================================
public int fnDx (String commandLine) 
//===================================================================
{
  return fnDx (fnTokenize(commandLine));
}
//===================================================================
public int fnDx (String[] args)
//===================================================================
{
  long start=0;
  int i, rc=99;
  try
  {
    // show arguments
    start = System.currentTimeMillis();
    System.out.println("Dx arguments:");
    for (i=0;i<args.length;i++) System.out.println(args[i]);
    System.out.println("");
    // start dx
    rc = com.android.dx.command.Main.main(args);
  }
  catch (Throwable t)
  {
    rc = 99;
    System.out.println("Error occurred!\n");
    t.printStackTrace();
  }
  System.out.println("\nDone in "+(System.currentTimeMillis()-start)/1000+" sec.\n");
  System.out.println("ExitValue: "+rc);
  return rc;
} //fnDx
//===================================================================
public void fnRedirectOutput (StringWriterOutputStream swos) 
//===================================================================
{
  // redirect stdout and stderr
  System.setOut(new PrintStream(swos));
  System.setErr(new PrintStream(swos));
}
//===================================================================
public void fnRunBeanshellScript (bsh.Interpreter i, String script) 
//===================================================================
{
  long start=0;
  try
  {
    start = System.currentTimeMillis();
    System.out.println("Running script "+script+"\n");
    if (new File(script).exists()) i.source(script);
    else System.out.println("Script does not exist.");
  }
  catch (Throwable t)
  {
    System.out.println("Error occurred!\n");
    t.printStackTrace();
  }
  finally
  {
    System.out.println("\nTotal script run time: "+(System.currentTimeMillis()-start)/1000+" sec.\n");
  }
}//fnRunBeanshellScript
//===================================================================
public int fnSignApk (String commandLine)
//===================================================================
{
  return fnSignApk(fnTokenize(commandLine));
} //
//===================================================================
public int fnSignApk (String[] args)
//===================================================================
{
  long start=0;
  int i, rc = 99;
  try
  {
    // show arguments
    start = System.currentTimeMillis();
    System.out.println("SignApk arguments:");
    for (i=0;i<args.length;i++) System.out.println(args[i]);
    System.out.println("");
    // start SignApk
    rc = SignApk.main(args);
  }
  catch (Throwable t)
  {
    rc = 99;
    System.out.println("Error occurred!\n");
    t.printStackTrace();
  }
  System.out.println("\nDone in "+(System.currentTimeMillis()-start)/1000+" sec.\n");
  return rc;
} //fnSignApk
//===================================================================
public static String[] fnTokenize (String commandLine) 
//===================================================================
{
  return org.eclipse.jdt.internal.compiler.batch.Main.tokenize(commandLine);
}
//===================================================================

} // IDE
//##################################################################

