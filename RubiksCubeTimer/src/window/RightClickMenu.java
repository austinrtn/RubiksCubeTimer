package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class RightClickMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	public RightClickMenu(SolveTimeList stl) {
		JMenuItem item = new JMenuItem("Delete");
		add(item);

		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeItem(stl);
			}
		});
	}

	public static void removeItem(SolveTimeList stl) {
		if (stl.getSelectedSolveTime() != null) {
			stl.removeSolve(stl.getSelectedSolveTime());
		}
	}
}
