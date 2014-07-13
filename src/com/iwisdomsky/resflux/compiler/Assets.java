package com.iwisdomsky.resflux.compiler;
import android.content.*;
import android.content.res.*;
import java.io.*;

public abstract class Assets
{

	public static void extract(Context context, String[] filenames){
		AssetManager ass = context.getAssets();
		for (String asset : filenames)
			try
			{
				InputStream in = ass.open(asset);
				File file = new File(context.getFilesDir(),asset);
				FileOutputStream os = new FileOutputStream(file);
				int l;byte[] buffer = new byte[1024];
				while ( (l = in.read(buffer)) != -1 )
					os.write(buffer,0,l);
				os.close();
				in.close();				
			}
			catch (IOException e)
			{}
		context.getFilesDir().setReadable(true,false);
	}


}
