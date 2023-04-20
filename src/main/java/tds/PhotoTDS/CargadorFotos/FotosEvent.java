package tds.PhotoTDS.CargadorFotos;

import java.util.EventObject;

import umu.tds.fotos.Fotos;

@SuppressWarnings("serial")
public class FotosEvent extends EventObject{
	
	protected Fotos fotos;
	
	public FotosEvent(Object fuente, Fotos fotos) {
		super(fuente);
		this.fotos = fotos;
	}

	public Fotos getFotos() {return fotos;}
	
}
