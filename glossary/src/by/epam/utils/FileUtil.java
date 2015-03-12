package by.epam.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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

	public static List<String> readAndSplitFile(String path) {
		Scanner sc = null;
		List<String> firstColumn = new ArrayList<String>();
		List<String> secondColumn = new ArrayList<String>();
		try {
			sc = new Scanner(new FileReader(path));
			while (sc.hasNext()) {
				String[] arr = sc.nextLine().split(Param.DELIM);
				firstColumn.add(arr[0]);
				secondColumn.add(arr[1]);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			sc.close();
		}
		firstColumn.addAll(secondColumn);
		return firstColumn;
	}

	public static void out(Collection collection, String path) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(path));
			Iterator<Param> it = collection.iterator();
			while (it.hasNext()) {
				out.println(it.next());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
