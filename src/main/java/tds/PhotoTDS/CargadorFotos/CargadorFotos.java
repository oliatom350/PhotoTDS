package tds.PhotoTDS.CargadorFotos;

import java.util.Vector;

import umu.tds.fotos.Fotos;
import umu.tds.fotos.MapperFotosXMLtoJava;

public class CargadorFotos {
	
	private Fotos fotos;
	private Vector<FotosListener> fotosListeners = new Vector<FotosListener>();
	private String archivoFotos;
	
	public CargadorFotos() {}
	
	public synchronized void addFotosListener(FotosListener listener){
		fotosListeners.addElement(listener);
	}
	
	public synchronized void removeFotosListener(FotosListener listener){
		fotosListeners.removeElement(listener);
	}
	
	public void setArchivoFotos(String archivoFotos) {
		if (!archivoFotos.equals(this.archivoFotos)) {
			this.fotos = MapperFotosXMLtoJava.cargarFotos(archivoFotos);
			FotosEvent ev = new FotosEvent(this, fotos);
			notificarCambio(ev);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void notificarCambio(FotosEvent ev) {
		Vector<FotosListener> lista;
		synchronized (this) {
			lista = (Vector<FotosListener>) fotosListeners.clone();
		}
		for(FotosListener fl : lista) {
			fl.cargarFotos(ev);
		}
	}
}
