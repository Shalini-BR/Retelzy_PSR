package com.psr.TestCases;

// folder create and delete

import java.io.File;

public class Foldertest {

	public static void main(String[] Args)
	{
		File file = new File("E:\\Createdfile");
		if(file.delete())
		{
			System.out.println(file + "got deleted");
		}
		else
		{
			System.out.println(file + "Not deleted");
		}
		//System.out.println("Dir structure"+ file.mkdirs());
	}
}
