package botMVC;

import java.util.*;

import com.pengrad.telegrambot.model.Update;

public class Model implements Subject {

	private List<Dia> datas = new LinkedList<Dia>();
	private List<Observer> observers = new LinkedList<Observer>();
	private static Model uniqueInstance;

	public void addData(Dia data) {
		this.datas.add(data);
	}

	public static Model getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers(long chatId, String data) {
		for(Observer observer:observers) {
			observer.update(chatId, data);
		}
	}

	public void procuraData(Update update) {
		String data = null;
		for(Dia lista: datas) {
			if(lista.getData().equals(update.message().text())) {
				data = lista.getTitulo();
				this.notifyObservers(update.message().chat().id(), data);
			}
		}		
		if(data != null) {
			//this.notifyObservers(update.message().chat().id(), data);

		}else {
			this.notifyObservers(update.message().chat().id(), "Que pena!\n Nada encontrado na Data digitada");
		}
	}


	public void listaFilme(Update update) {
		String data = null;

		for(Dia lista: datas) {
			System.out.println(lista.getTitulo());

			if(update.message().text().equals("Exibir")) {
				if(lista.getFS().equals("Filme")) {
					data = lista.getFS();
					this.notifyObservers(update.message().chat().id(), lista.getTitulo() + " - " + lista.getData());

				}else if(data == null) {
					this.notifyObservers(update.message().chat().id(), "Que pena! Nenhum filme encontrado nesta data");
				}

			}else {
				if(lista.getTitulo().equals(update.message().text())) {
					data = lista.getTitulo() + " - " + lista.getData();
					this.notifyObservers(update.message().chat().id(), data);

				}else if(data == null) {
					this.notifyObservers(update.message().chat().id(), "Que pena! Nenhum filme encontrado");
				}
			}
		}
	}
	
	public void listaSerie(Update update) {
		String data = null;

		for(Dia lista: datas) {
			System.out.println(lista.getTitulo());

			if(update.message().text().equals("Exibir")) {
				if(lista.getFS().equals("Serie")) {
					data = lista.getFS();
					this.notifyObservers(update.message().chat().id(), lista.getTitulo() + " - " + lista.getData());

				}else if(data == null) {
					this.notifyObservers(update.message().chat().id(), "Que pena! Nenhuma serie encontrado nesta data");
				}

			}else {
				if(lista.getTitulo().equals(update.message().text())) {
					data = lista.getTitulo() + " - " + lista.getData();
					this.notifyObservers(update.message().chat().id(), data);

				}else if(data == null) {
					this.notifyObservers(update.message().chat().id(), "Que pena! Nenhuma serie encontrado");
				}
			}
		}
	}

}
