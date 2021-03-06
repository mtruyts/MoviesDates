package botMVC;

import com.pengrad.telegrambot.model.Update;

public class ControllerSearchSerie implements Controller {

	private Model model;
	private View view;

	public void search(Update update) {
		view.sendTypingMessage(update);
		model.listaSerie(update);		
	}

	public ControllerSearchSerie(Model model, View view) {
		this.model = model;
		this.view = view;
	}


}
