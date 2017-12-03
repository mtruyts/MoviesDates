package botMVC;

import com.pengrad.telegrambot.model.Update;

public class ControllerNewAdd implements ControllerNew{

	Model model;
	View view;

	public ControllerNewAdd(Model model, View view){
		this.model = model;
		this.view = view;
	}


	public void n(Update update){
		view.sendTypingMessage(update);
		model.addNewData(update);	
	}


}

