package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class ImgButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ImgButton(ImageIcon imageIcon){
		
		setContentAreaFilled(false);
		setIcon(imageIcon);
		setBorder(null);
		setFocusable(false);
		//添加按键检测
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick();
			}
		});
	}
	
	public abstract void onClick();

}
