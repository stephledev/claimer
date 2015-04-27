package ch.claimer.client.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ch.claimer.shared.models.Person;

/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */

public class HomeController {
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private TableView<Person> homeTableView;
	
	@FXML
	private TableColumn<Person, String> colProject;
	
	@FXML
	private TableColumn<Person, String> colMangel;
	
	@FXML
	private TableColumn<Person, String> colStatus;
	
	@FXML
	private TableColumn<Person, String> colDeadline;
	
	@FXML
	private TextField txt_search;
	
	@FXML
	private Label lbl_title;

}
