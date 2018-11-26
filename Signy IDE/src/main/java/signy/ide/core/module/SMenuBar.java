package signy.ide.core.module;

import java.util.Collection;

import org.fxmisc.richtext.model.Paragraph;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import signy.ide.FXMLDocumentController;
import signy.ide.Main;
import signy.ide.controls.panes.SEditorPane;

public class SMenuBar {

	private Main mainApp;
	private SEditorPane editor;

	private Menu fileMenu, editMenu, selectionMenu, viewMenu, helpMenu;
	
	private String TempItem = "";

	public SMenuBar(FXMLDocumentController controller) {

		this.mainApp = controller.getMainApp();
		this.editor = controller.getEditorPane();

		// 1. File Menu
		Menu fileMenu = new Menu("_File");

		MenuItem newMnItem = new MenuItem("_New");
		newMnItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
		newMnItem.setOnAction(e -> {
			this.editor.handleNewFile();
		});

		MenuItem newWindowMnItem = new MenuItem("New Window");
		newWindowMnItem.setAccelerator(
				new KeyCodeCombination(KeyCode.N, KeyCombination.SHIFT_DOWN, KeyCombination.SHORTCUT_DOWN));
		newWindowMnItem.setOnAction(e -> {
			System.out.println(e.getSource() + " didn't have any action yet");
		});

		MenuItem openFileMnItem = new MenuItem("Open File...");
		openFileMnItem.setOnAction(e -> {
			this.editor.handleOpenFiles();
		});

		MenuItem openProjectsMnItem = new MenuItem("Open Projects...");
		openProjectsMnItem.setOnAction(e -> {
			System.out.println(e.getSource() + " didn't have any action yet");
		});

		MenuItem openRecentMnItem = new MenuItem("Open Recent");
		openRecentMnItem.setOnAction(e -> {
			System.out.println(e.getSource() + " didn't have any action yet");
		});

		MenuItem saveMnItem = new MenuItem("_Save");
		saveMnItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
		saveMnItem.setOnAction(e -> {
			this.editor.handleSaveFile();
		});

		MenuItem saveAsMnItem = new MenuItem("Save _As...");
		saveAsMnItem.setAccelerator(
				new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.SHORTCUT_DOWN));
		saveAsMnItem.setOnAction(e -> {
			this.editor.handleSaveAs();
		});

		MenuItem saveAllMnItem = new MenuItem("Sav_e All");
		saveAllMnItem.setAccelerator(
				new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN, KeyCombination.SHORTCUT_DOWN));
		saveAllMnItem.setOnAction(e -> {
			this.editor.handleSaveAllFiles();
		});

		MenuItem closeMnItem = new MenuItem("_Close");
		closeMnItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN));
		closeMnItem.setOnAction(e -> {
			System.out.println(e.getSource() + " didn't have any action yet");
		});

		MenuItem closeAllMnItem = new MenuItem("C_lose All");
		closeAllMnItem.setOnAction(e -> {
			System.out.println(e.getSource() + " didn't have any action yet");
		});

		MenuItem restartMnItem = new MenuItem("Restart");
		restartMnItem.setOnAction(e -> {
			System.out.println(e.getSource() + " didn't have any action yet");
		});

		MenuItem exitMnItem = new MenuItem("E_xit");
		exitMnItem.setOnAction(e -> {
			mainApp.exit();
		});

		fileMenu.getItems().addAll(newMnItem, newWindowMnItem, new SeparatorMenuItem(), openFileMnItem,
				openProjectsMnItem, openRecentMnItem, new SeparatorMenuItem(), saveMnItem, saveAsMnItem, saveAllMnItem,
				new SeparatorMenuItem(), closeMnItem, closeAllMnItem, new SeparatorMenuItem(), restartMnItem,
				exitMnItem);

		// 2. Edit Menu
		Menu editMenu = new Menu("_Edit");

		MenuItem UndoItem = new MenuItem("Undo");
		UndoItem.setAccelerator(
				new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
		UndoItem.setOnAction(e -> {
			editor.getCurrentActiveTab().getTextArea().undo();
		});
		
		MenuItem RedoItem = new MenuItem("Redo");
		RedoItem.setAccelerator(
				new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN));
		RedoItem.setOnAction(e -> {
			editor.getCurrentActiveTab().getTextArea().redo();
		});
		
		MenuItem CutItem = new MenuItem("Cut");
		CutItem.setAccelerator(
				new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
		CutItem.setOnAction(e -> {
			if (editor.getCurrentActiveTab().getTextArea().getSelectedText() != null) {
				TempItem = editor.getCurrentActiveTab().getTextArea().getSelectedText();
				editor.getCurrentActiveTab().getTextArea().replaceSelection("");;
			}
			//System.out.println(editor.getCurrentActiveTab().getTextArea().getText());
		});
		
		MenuItem CopyItem = new MenuItem("Copy");
		CopyItem.setAccelerator(
				new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
		CopyItem.setOnAction(e -> {
			if (editor.getCurrentActiveTab().getTextArea().getSelectedText() != null) {
				TempItem = editor.getCurrentActiveTab().getTextArea().getSelectedText();
			}
		});

		MenuItem PasteItem = new MenuItem("Paste");
		PasteItem.setAccelerator(
				new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
		PasteItem.setOnAction(e -> {
			if (TempItem != "") {
				editor.getCurrentActiveTab().getTextArea().replaceSelection(TempItem);
			}
		});
		
		MenuItem FindItem = new MenuItem("Find & Replace");
		FindItem.setOnAction(e -> {
			System.out.println(e.getSource() + " didn't have any action yet");
		});
		
		
		editMenu.getItems().addAll(UndoItem, RedoItem, new SeparatorMenuItem(), 
				CutItem, CopyItem, PasteItem ,new SeparatorMenuItem(), FindItem);
		
		// 3. Selection Menu
		Menu selectionMenu = new Menu("_Selection");

		MenuItem SelectAllItem = new MenuItem("Select All");
		SelectAllItem.setAccelerator(
				new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_DOWN));
		SelectAllItem.setOnAction(e -> {
			editor.getCurrentActiveTab().getTextArea().selectAll();
		});
		
		MenuItem ExpandSelectItem = new MenuItem("Expand Select");
		ExpandSelectItem.setOnAction(e -> {
			editor.getCurrentActiveTab().getTextArea().selectParagraph();
		});

		MenuItem ShrinkSelectItem = new MenuItem("Shrink Select");
		ShrinkSelectItem.setOnAction(e -> {
			editor.getCurrentActiveTab().getTextArea().selectWord();
		});
		
		MenuItem CopyLineUp = new MenuItem("Copy Line Up");
		CopyLineUp.setOnAction(e -> {
			System.out.print(e.getSource() + " didn't have any action yet");
		});
		
		MenuItem CopyLineDown = new MenuItem("Copy Line Down");
		CopyLineDown.setOnAction(e -> {
			System.out.print(e.getSource() + " didn't have any action yet");
		});
		
		MenuItem MoveLineUp = new MenuItem("Move Line Up");
		MoveLineUp.setOnAction(e -> {
			int currentCaret = editor.getCurrentActiveTab().getTextArea().getCurrentParagraph();
			// caret's current paragraph
			Paragraph<Collection<String>, String, Collection<String>> current 
				= editor.getCurrentActiveTab().getTextArea().getParagraph(currentCaret);
			// get paragraph
			String text = current.getText(); // current Text
			// max line
			int max_line = editor.getCurrentActiveTab().getTextArea().getText().split("\n").length;
			
			if (currentCaret == 0) { // line 0 not have any action
				return;
			}
			else if (currentCaret > 0 && currentCaret < max_line-1) {
				Paragraph<Collection<String>, String, Collection<String>> before 
					= editor.getCurrentActiveTab().getTextArea().getParagraph(currentCaret-1);
				String text_1 = before.getText(); // before Text
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, text + "\n");
				editor.getCurrentActiveTab().getTextArea().deleteText(currentCaret-1, 0, currentCaret, 0);
				editor.getCurrentActiveTab().getTextArea().deleteText(currentCaret, 0, currentCaret+1, 0);
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, text_1 + "\n");
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, ""); // set cursor
			}
			else if (currentCaret == max_line-1) {
				Paragraph<Collection<String>, String, Collection<String>> before 
					= editor.getCurrentActiveTab().getTextArea().getParagraph(currentCaret-1);
				String text_1 = before.getText(); // before Text
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, text + "\n");
				editor.getCurrentActiveTab().getTextArea().deleteText(currentCaret-1, 0, currentCaret, 0);
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, ""); // move cursor
				int max_char = editor.getCurrentActiveTab().getTextArea().getParagraph(max_line-1).length(); // length at last line
				editor.getCurrentActiveTab().getTextArea().replaceText(currentCaret, 0, currentCaret, max_char, text_1);
				
			}
			
		});
		
		MenuItem MoveLineDown = new MenuItem("Move Line Down");
		MoveLineDown.setOnAction(e -> {
			int currentCaret = editor.getCurrentActiveTab().getTextArea().getCurrentParagraph();
			Paragraph<Collection<String>, String, Collection<String>> a 
				= editor.getCurrentActiveTab().getTextArea().getParagraph(currentCaret);
			String text = a.getText(); // current Text
			int max_line = editor.getCurrentActiveTab().getTextArea().getText().split("\n").length;

			if (currentCaret == max_line-1) { // line 1 not have any action
				return;
			}
			else if (currentCaret > 0 && currentCaret < max_line-1) {
				Paragraph<Collection<String>, String, Collection<String>> b 
					= editor.getCurrentActiveTab().getTextArea().getParagraph(currentCaret-1);
				String text_1 = b.getText(); // before Text
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, text + "\n");
				editor.getCurrentActiveTab().getTextArea().deleteText(currentCaret-1, 0, currentCaret, 0);
				editor.getCurrentActiveTab().getTextArea().deleteText(currentCaret, 0, currentCaret+1, 0);
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, text_1 + "\n");
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, ""); // set cursor
			}
			else if (currentCaret == max_line-1) {
				System.out.println("ERROR0");
				Paragraph<Collection<String>, String, Collection<String>> b 
					= editor.getCurrentActiveTab().getTextArea().getParagraph(currentCaret-1);
				String text_1 = b.getText(); // before Text
				System.out.println("CurrentCaret : " + currentCaret + " " + text + " " + text_1);
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, text + "\n");
				editor.getCurrentActiveTab().getTextArea().deleteText(currentCaret-1, 0, currentCaret, 0);
				editor.getCurrentActiveTab().getTextArea().insertText(currentCaret, 0, ""); // move cursor
				int max_char = editor.getCurrentActiveTab().getTextArea().getParagraph(max_line-1).length();
				editor.getCurrentActiveTab().getTextArea().replaceText(currentCaret, 0, currentCaret, max_char, text_1);
				
			}
		});
		
		selectionMenu.getItems().addAll(SelectAllItem, ExpandSelectItem, 
				ShrinkSelectItem ,new SeparatorMenuItem(),CopyLineUp, CopyLineDown, MoveLineUp, MoveLineDown);
		
		// 4. View Menu
		Menu viewMenu = new Menu("_View");

		MenuItem Files = new MenuItem("Files");
		Files.setOnAction(e -> {
			System.out.print(e.getSource() + " didn't have any action yet");
		});
		
		MenuItem Search = new MenuItem("Search");
		Search.setOnAction(e -> {
			System.out.print(e.getSource() + " didn't have any action yet");
		});
		
		MenuItem ToggleBottomPanel = new MenuItem("Toggle Bottom Panels");
		ToggleBottomPanel.setOnAction(e -> {
			System.out.print(e.getSource() + " didn't have any action yet");
		});
		
		MenuItem CollaspeAllsidePanel = new MenuItem("Collaspe All Side Panels");
		CollaspeAllsidePanel.setOnAction(e -> {
			System.out.print(e.getSource() + " didn't have any action yet");
		});

		viewMenu.getItems().addAll(Files,Search ,new SeparatorMenuItem(),
				ToggleBottomPanel,CollaspeAllsidePanel);
		
		// 6. Help Menu
		Menu helpMenu = new Menu("_Help");
		
		MenuItem About = new MenuItem("About");
		About.setOnAction(e -> {
			System.out.print(e.getSource() + " didn't have any action yet");
		});
		
		MenuItem Document = new MenuItem("Documentation");
		Document.setOnAction(e -> {
			System.out.print(e.getSource() + " didn't have any action yet");
		});
		
		helpMenu.getItems().addAll(About,Document);

		this.fileMenu = fileMenu;
		this.editMenu = editMenu;
		this.selectionMenu = selectionMenu;
		this.viewMenu = viewMenu;
		this.helpMenu = helpMenu;

	}

	public MenuBar getMenuBar() {
		return new MenuBar(fileMenu, editMenu, selectionMenu, viewMenu, helpMenu);
	}

}
