package by.epam.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import by.epam.beans.Param;

public class FileUtil {
	public static String readFile(String path) {
		StringBuilder builder = new StringBuilder();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(path));
			while (sc.hasNext()) {
				builder.append(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			sc.close();
		}
		return builder.toString().trim();
	}

	public static void out(Set<Param> set, String path) {
		PrintWriter out=null;
		try {
			out = new PrintWriter(new FileWriter(path));
			Iterator<Param> it = set.iterator();
			while (it.hasNext()) {
				out.println(it.next().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
}
