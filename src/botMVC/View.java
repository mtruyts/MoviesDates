package botMVC;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class View implements Observer {

	TelegramBot bot = TelegramBotAdapter.build("416670765:AAF4Iu9JMen-RBc0iQgMSpe9vtggIzAyb7E");

	GetUpdatesResponse updatesResponse; //Recebe as mensagens
	SendResponse sendResponse; //Gerencia o envio de respostas
	BaseResponse baseResponse; //Gerencia o envio de ações do chat
	boolean searchBehaviour = false;
	Controller controller; // Conecta view com o controller
	private Model model;
	Dia dia = new Dia();
	ControllerNew controllernew;

	public View(Model model) {
		this.model = model; 
	}

	public void receiveUsersMessages() {

		int queuesIndex = 0; //controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila

		//loop infinito pode ser alterado por algum timer de intervalo curto
		while (true) {

			//executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(queuesIndex));

			//Lista de mensagens
			List<Update> updates = updatesResponse.updates();

			//Analisa cada ação da mensagem
			for (Update update : updates) {

				//atualiza o off-set
				queuesIndex = update.updateId()+1;

				if(this.searchBehaviour==true) {
					this.callController(update);
					searchBehaviour = false;

				}else if(update.message().text().equals("Data")) {
					setController(new ControllerSearchData(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Qual data deseja consultar? Digite no formato: dd/mm/aa"));
					this.searchBehaviour = true;					

				}else if(update.message().text().equals("Filme")) {
					setController(new ControllerSearchFilme(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Digite Exibir(para exibir todos os filmes) ou o nome do Filme:"));
					this.searchBehaviour = true;

				}else if(update.message().text().equals("Serie")) {
					setController(new ControllerSearchSerie(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Digite Exibir(para exibir todas as series) ou o nome da Serie:"));
					this.searchBehaviour = true;

				}else if(update.message().text().toLowerCase().equals("Today")){
					setController(new ControllerToday(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Hoje é " + dia.today() +  " ?"));
					searchBehaviour = true;

				}else if(update.message().text().toLowerCase().equals("Add")){
					setController(new ControllerNewAdd(model, this));
					t = 0;
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Digite a Data, filme ou serie separados por vigula como os exemplos abaixo:\n17/11/2017, Serie, Justiceiro\n"));
					searchBehaviour = true;

				}else if(this.searchBehaviour==false) {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Olá, seja bem vindo ao MoviesDates bot!\n"
							+                                                               "Fique por dentro das datas dos seus filmes e series favoritos."));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Deseja pesquisar por Data, Filme ou Serie?\n"
							+                                                               "Digite Add para adicionar um novo filme ou serie\n"
							+                                                               "Digite Today para exibir os filmes e series diposniveis hoje\n"));
					System.out.println("Mensagem Enviada?" +sendResponse.isOk());
				}

				System.out.println("Recebendo mensagem:"+ update.message().text());

				//verificação de mensagem enviada com sucesso
				System.out.println("Mensagem Enviada?" +sendResponse.isOk());

			}

		}
	}

	public void callController(Update update) {
		this.controller.search(update);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setController(ControllerNew controllernew){
		this.controllernew = controllernew;
	}

	public void sendTypingMessage(Update update) {
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}

	public void update(long chatId, String fsData) {
		sendResponse = bot.execute(new SendMessage(chatId, fsData));
		this.searchBehaviour = false;
	}


}


