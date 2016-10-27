package sort_eclipse_hotkeys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Sorter {
	private final File shortcuts = new File("eclipse_shortcuts.csv");
	private final File sortedShortcuts = new File("sorted_shortcuts.csv");
	private BufferedReader bReader;
	private BufferedWriter bWriter;
	private String[] shortcutLines;

	private void readShortcutsFromFile() {
		ArrayList<String> tempLineList = new ArrayList<>();
		try {
			String line = bReader.readLine();
			while (line != null) {
				tempLineList.add(line);
				line = bReader.readLine();
			}
			shortcutLines = new String[tempLineList.size()];
			Arrays.toString(tempLineList.toArray(shortcutLines));
			Arrays.sort(shortcutLines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeShortcutsInFile() {
		int lineCount = 0;
		try {
			while (lineCount != shortcutLines.length) {
				bWriter.write("\n" + shortcutLines[lineCount]);
				lineCount++;
			}
			closeBuffers();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeBuffers() {
		try {
			bWriter.flush();
			bWriter.close();
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			bReader = new BufferedReader(new FileReader(shortcuts));
			bWriter = new BufferedWriter(new FileWriter(sortedShortcuts));
		} catch (Exception e) {
			e.printStackTrace();
		}
		readShortcutsFromFile();
		System.out.println("Sorting...");
		writeShortcutsInFile();
		System.out.println("Done!");
		System.out.println("Output file: " + sortedShortcuts.getAbsolutePath());
	}
}
