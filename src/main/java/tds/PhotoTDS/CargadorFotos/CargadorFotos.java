package tds.PhotoTDS.CargadorFotos;

import java.util.Vector;

import umu.tds.fotos.Fotos;

public class CargadorFotos {
	
	private Fotos fotos;
	private Vector<FotosListener> fotosListeners = new Vector<FotosListener>();
	
	public CargadorFotos() {}
	
	public synchronized void addSueldoListener(FotosListener listener){
		fotosListeners.addElement(listener);
	}
	
	public synchronized void removeSueldoListener(FotosListener listener){
		fotosListeners.removeElement(listener);
	}
	
	public void setFotos(Fotos fotos) {
		if (!this.fotos.equals(fotos)) {
			FotosEvent ev = new FotosEvent(this, fotos);
			notificarCambio(ev);
		}
	}
	
	public void notificarCambio(FotosEvent ev) {
		Vector<FotosListener> lista;
		synchronized (this) {
			lista = (Vector<FotosListener>) fotosListeners.clone();
		}
		for(FotosListener fl : lista) {
			fl.enteradoCambioFotos(ev);
		}
	}
	
	public void MapperJavaToFotosXML(Fotos fotos) {
		
	}
	
}
