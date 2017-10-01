package botMVC;

import com.pengrad.telegrambot.model.Update;

public class ControllerSearchFilme implements Controller {

	private Model model;
	private View view;

	public void search(Update update) {
		view.sendTypingMessage(update);
		model.listaFilme(update);		
	}

	public ControllerSearchFilme(Model model, View view) {
		this.model = model;
		this.view = view;
	}

}


