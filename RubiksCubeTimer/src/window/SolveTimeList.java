package window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logger.Logger;
import logger.SolveTime;

public class SolveTimeList extends JList<String> implements MouseListener {

	private static final long serialVersionUID = 1L;

	private DefaultListModel<String> solveModel;
	private SolveTime selectedSolveTime;

	public SolveTime getSelectedSolveTime() {
		return selectedSolveTime;
	}

	public SolveTimeList() {
		createListModel();
		addMouseListener(this);
		addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (getValueIsAdjusting()) {
					int index = getSelectedIndex();
					selectedSolveTime = Logger.getSolveList().get(index);
				}

			}
		});
	}

	private void createListModel() {
		solveModel = new DefaultListModel<>();
		for (int i = 0; i < Logger.getSolveList().size(); i++) {
			SolveTime st = Logger.getSolveList().get(i);
			printSolve(st);
		}
		setModel(solveModel);

	}

	public void printSolve(SolveTime st) {
		String str = st.getId() + " | " + st.getTimeSolved();
		solveModel.addElement(str);
	}

	public void removeSolve(SolveTime st) {
		solveModel.removeElementAt(this.getSelectedIndex());
		Logger.removeFromList(st);
		selectedSolveTime = null;
		createListModel();
	}

	////////////////
	// MOUSE LISTENER
	/////////////////

	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger() && selectedSolveTime != null)
			rightClick(e);

	}

	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger() && selectedSolveTime != null)
			rightClick(e);
	}

	private void rightClick(MouseEvent e) {
		RightClickMenu menu = new RightClickMenu(this);
		menu.show(e.getComponent(), e.getX(), e.getY());
	}

	///////////////
	///////////////
	//////////////

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
