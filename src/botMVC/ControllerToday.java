package botMVC;

import com.pengrad.telegrambot.model.Update;

public class ControllerToday implements Controller {

	private Model model;
	private View view;

	public void search(Update update) {
		view.sendTypingMessage(update);
		model.exibirToday(update);
	}

	public ControllerToday(Model model, View view) {
		this.model = model;
		this.view = view;
	}


}

