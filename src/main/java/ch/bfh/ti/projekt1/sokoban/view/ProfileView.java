package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ProfileView extends JPanel{
	
	public ProfileView(List<String> profiles) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		
		for (String str : profiles) {
			JButton button = new JButton(str);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					profileSelected(((JButton)e.getSource()).getText());
				}
			});
			add(button);
		}
		
	}
	
	protected void profileSelected(String profile) {}

}
