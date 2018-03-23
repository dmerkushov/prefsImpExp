/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

/**
 *
 * @author Dmitriy Merkushov <d.merkushov@gmail.com>
 */
public class Main {

	public static void main (String args[]) {
		if (args.length == 0) {
			usage ();
		}

		try {
			String command = args[0].trim ().toLowerCase ();

			if (command.equals ("import")) {
				Preferences.importPreferences (System.in);
			} else if (command.equals ("exportuser")) {
				String subtree = "/";
				if (args.length >= 2) {
					subtree = args[1];
				}

				Preferences prefs = Preferences.userRoot ().node (subtree);
				prefs.exportSubtree (System.out);
			} else if (command.equals ("exportsystem")) {
				String subtree = "/";
				if (args.length >= 2) {
					subtree = args[1];
				}

				Preferences prefs = Preferences.systemRoot ().node (subtree);
				prefs.exportSubtree (System.out);
			} else {
				usage ();
			}
		} catch (IOException | BackingStoreException | InvalidPreferencesFormatException | RuntimeException ex) {
			System.err.println ("ERROR:");
			ex.printStackTrace (System.err);
			System.exit (-1);
		}
	}

	private static void usage () {
		System.out.println ("Preferences exporter/importer. USAGE:\n"
				+ "\tjava -cp prefsImpExp.jar Main import - import prefs from stdin\n"
				+ "\tjava -cp prefsImpExp.jar Main exportUser [/node/othernode] - export user prefs to stdout\n"
				+ "\tjava -cp prefsImpExp.jar Main exportSystem [/node/othernode] - export system prefs to stdout");
		System.exit (-1);
	}

}
