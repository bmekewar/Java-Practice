package com.bvm.java;


public class SystemPropertiesTest {

	public static void main (String args[])
	{
		String str = (String) System.getenv("JAVA_HOME");
		System.out.println("String: "+ str);
		//System.setProperty("line.seperator","\n");
		System.out.println("line.seperator: \t"+ System.getProperty("line.seperator"));
		System.out.println("java.class.path: \t"+ System.getProperty("java.class.path"));
		System.out.println("java.version: \t"+ System.getProperty("java.version"));
		System.out.println("java.vendor: \t"+ System.getProperty("java.vendor"));
		System.out.println("java.vendor.url: \t"+ System.getProperty("java.vendor.url"));
		System.out.println("java.home: \t"+ System.getProperty("java.home"));
		System.out.println("java.vm.specification.version: \t"+ System.getProperty("java.vm.specification.version"));
		System.out.println("java.vm.specification.vendor: \t"+ System.getProperty("java.vm.specification.vendor"));
		System.out.println("java.vm.specification.name: \t"+ System.getProperty("java.vm.specification.name"));
		System.out.println("java.vm.version: \t"+ System.getProperty("java.vm.version"));
		System.out.println("java.vm.vendor: \t"+ System.getProperty("java.vm.vendor"));
		System.out.println("java.vm.name: \t"+ System.getProperty("java.vm.name"));
		System.out.println("java.specification.version: \t"+ System.getProperty("java.specification.version"));
		System.out.println("java.specification.vendor: \t"+ System.getProperty("java.specification.vendor"));
		System.out.println("java.specification.name: \t"+ System.getProperty("java.specification.name"));
		System.out.println("java.class.version: \t"+ System.getProperty("java.class.version"));
		System.out.println("java.class.path: \t"+ System.getProperty("java.class.path"));
		System.out.println("java.library.path: \t"+ System.getProperty("java.library.path"));
		System.out.println("java.io.tmpdir: \t"+ System.getProperty("java.io.tmpdir"));
		System.out.println("java.compiler: \t"+ System.getProperty("java.compiler"));
		System.out.println("java.ext.dirs: \t"+ System.getProperty("java.ext.dirs"));
		System.out.println("os.name: \t"+ System.getProperty("os.name"));
		System.out.println("os.arch: \t"+ System.getProperty("os.arch"));
		System.out.println("os.version: \t"+ System.getProperty("os.version"));
		System.out.println("file.separator: \t"+ System.getProperty("file.separator"));
		System.out.println("path.separator: \t"+ System.getProperty("path.separator"));
		System.out.println("line.separator: \t"+ System.getProperty("line.separator"));
		System.out.println("user.name: \t"+ System.getProperty("user.name"));
		System.out.println("user.home: \t"+ System.getProperty("user.home"));
		System.out.println("user.dir: \t"+ System.getProperty("user.dir"));
		System.out.println("System.getProperties \t"+ System.getProperties());
	}
}