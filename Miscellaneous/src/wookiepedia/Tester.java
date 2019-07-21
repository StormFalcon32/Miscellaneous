package wookiepedia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tester {

	public static void main(String[] args) {
		// hashCode test drive:
		ChainedHashSet<Person> set = new ChainedHashSet<Person>();
		fillFromFile(set);
		set.uglyPrint();
	}

	public static void fillFromFile(ChainedHashSet<Person> fillThis) {
		try {
			FileReader reader = new FileReader(new File("./src/Wookieepedia/StarWarsInfo2.txt"));
			BufferedReader buff = new BufferedReader(reader);
			String line = buff.readLine(); // that's the lame intro line

			line = buff.readLine();
			while (line != null) {
				Person p = new Person(line.split(":"));

				fillThis.add(p);

				line = buff.readLine();
			}
			buff.close();
		} catch (FileNotFoundException x) {
			System.out.println("Can't Find It");
			System.exit(0);
		} catch (IOException i) {
			System.out.println("Can't read file");
			System.exit(0);
		} catch (Exception x) {
			x.printStackTrace();
		}

	}
}
