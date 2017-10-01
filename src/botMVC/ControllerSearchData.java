package botMVC;

import com.pengrad.telegrambot.model.Update;

public class ControllerSearchData implements Controller {

	private Model model;
	private View view;

	public void search(Update update) {
		view.sendTypingMessage(update);
		model.procuraData(update);		
	}

	public ControllerSearchData(Model model, View view) {
		this.model = model;
		this.view = view;
	}

}
