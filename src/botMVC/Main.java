package botMVC;

public class Main {

	private static Model model; 

	public static void main(String[] args) {
		model = Model.getInstance();
		inicializarDados(model);
		View view = new View(model);
		model.registerObserver(view);
		view.receiveUsersMessages();		
	}

	public static void inicializarDados(Model model) {
		model.addData(new Dia("28/09/2017", "Filme", "Kingsman: o círculo dourado"));
		model.addData(new Dia("16/11/2017", "Filme", "Liga da Justiça"));
		model.addData(new Dia("25/10/2017", "Filme", "Thor: Ragnarok"));
		model.addData(new Dia("15/12/2017", "Filme", "Star Wars: Os Últimos Jedi"));
		model.addData(new Dia("20/10/2017", "Filme", "Jogos Mortais 8"));


		model.addData(new Dia("25/09/2017", "Serie", "The Big Bang Theory"));
		model.addData(new Dia("25/09/2017", "Serie", "Young Sheldon"));
		model.addData(new Dia("10/10/2017", "Serie", "The Flash"));
		model.addData(new Dia("22/10/2017", "Serie", "The Walking Dead"));
		model.addData(new Dia("27/10/2017", "Serie", "Stranger things"));


	}


}
