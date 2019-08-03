package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class MainFrame extends JFrame {

	private MainToolBar toolbar;
	private MainDialogBox dialogBox;
	private BetDialogBox betDialogBox;
	private MainPanel mainPanel;
	private StatusBar status;
	private final GameEngine gameEngine;

	public MainFrame(GameEngine gameEngine) {
		super("Card Game");
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(this));

		setPreferredSize(new Dimension(1024, 520));
		pack();
		this.gameEngine = gameEngine;

		setJMenuBar(new MainMenu(this));
		toolbar = new MainToolBar(gameEngine, this);
		mainPanel = new MainPanel(gameEngine, this);
		status = new StatusBar();

		// Add the components to the frame
		add(toolbar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(status, BorderLayout.SOUTH);

		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
	}

	// Calls methods from other component classes
	// Dialog box is popped up when the add player button is clicked
	public void enterPlayerDetails() {
		dialogBox = new MainDialogBox(gameEngine, this, true);
	}

	// Dialog box gets popped up when the player clicks place bet
	public void enterBetValue(Player player) {
		betDialogBox = new BetDialogBox(gameEngine, this, true);
	}

	// GameEngineCallbackGUI calls next player on frame
	public void nextPlayerCard(Player player, PlayingCard card) {
		mainPanel.setCard(player, card);
		status.statusDealPlayer();
	}

	public void nextHouseCard(PlayingCard card, GameEngine gameEngine) {
		mainPanel.setHouseCard(card, gameEngine);
		status.statusDealHouse();
	}

	public void bustPlayerMessage(Player player) {
		mainPanel.getBustPlayerMessage(player);
	}

	public void bustHouseMessage() {
		mainPanel.getBustHouseMessage();
		status.statusHouseBust();
	}

	public void getResults(int result, GameEngine gameEngine) {
		mainPanel.result(result, gameEngine);
		status.statusResults();
	}

	public void addSummaryText(Player player) {
		mainPanel.addSummaryText(player);
	}

	public void setSummaryText() {
		mainPanel.setSummaryText();
	}

	// The current player is called from the tool bar combo box
	public void populateComboBox(Player player) {
		toolbar.populateComboBox(player);
		status.statusAddPlayer();
	}

	public Object getSelectedItemCombo() {
		return toolbar.getSelectedItemCombo();
	}

	public void getIfNoPlayer() {
		toolbar.getIfNoPlayer();
	}

	public void getIfNoBet() {
		toolbar.getIfNoBet();
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void setIfCanDeal(Player player, Boolean ifCanDeal) {
		mainPanel.setIfCanDeal(player, ifCanDeal);
	}

	public Boolean getIfCanDeal(Player player) {
		return mainPanel.getIfCanDeal(player);
	}

	public JButton getDeal() {
		return toolbar.getDealButton();
	}

	public String getIDLabelText(MainDialogBox dialogBox) {
		return dialogBox.getIDLabelText();
	}

	public String getNameLabelText(MainDialogBox dialogBox) {
		return dialogBox.getNameLabelText();
	}

	public int getPointsLabelTextDialogBox(MainDialogBox dialogBox) {
		return dialogBox.getPointsLabelTextDialogBox();
	}

	public void closeWindow(MainDialogBox dialogBox) {
		dialogBox.closeWindow();
		;
	}

	public void setIfNotIntegerDialogBox(MainDialogBox dialogBox) {
		dialogBox.setIfNotIntegerDialogBox();
	}

	public String getPointsTextField(MainDialogBox dialogBox) {
		return dialogBox.getPointsTextField();
	}

	public void setIfIntDialogBox(MainDialogBox dialogBox) {
		dialogBox.setIfIntDialogBox();
	}

	public String getBet(BetDialogBox betDialogBox) {
		return betDialogBox.getBet();
	}

	public int getActualBet(BetDialogBox betDialogBox) {
		return betDialogBox.getActualBet();
	}

	public void setIfInt(BetDialogBox betDialogBox) {
		betDialogBox.setIfInt();
	}

	public void setIfNotInteger(BetDialogBox betDialogBox) {
		betDialogBox.setIfNotInteger();
	}

	public void cannotSetBet(BetDialogBox betDialogBox) {
		betDialogBox.cannotSetBet();
	}

	public void existingBet(BetDialogBox betDialogBox) {
		betDialogBox.existingBet();
	}

	public void betZero(BetDialogBox betDialogBox) {
		betDialogBox.betZero();
	}

	public void closeBetWindow(BetDialogBox betDialogBox) {
		betDialogBox.closeWindow();
	}

	public void canReset(Boolean canReset) {
		mainPanel.setIfCanReset(canReset);
	}

	public Boolean getIfcanReset() {
		return mainPanel.getIfCanReset();
	}

	public void reset() {
		mainPanel.reset();
	}

	public JPanel getCardPanel() {
		return mainPanel.getCardPanel();
	}

	public void isEmpty(MainDialogBox dialogBox) {
		dialogBox.ifEmpty();
	}
}
