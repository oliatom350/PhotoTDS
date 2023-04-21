package tds.PhotoTDS.interfaz;

import tds.PhotoTDS.Usuario;

public interface Observable {
	void addObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers(Usuario usuario);
}

