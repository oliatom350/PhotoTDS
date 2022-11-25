package tds.PhotoTDS.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.PhotoTDS.Comentario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;


public class AdaptadorTDSComentarioDAO implements IAdaptadorComentarioDAO {

	ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void registrarComentario(Comentario comentario) {
		boolean existe = false;
		Entidad eComentario = null;
		try {
			eComentario = servPersistencia.recuperarEntidad(comentario.getId());
		} catch (NullPointerException e) {
			existe = true;
		}
		if (existe || eComentario != null) return;
		
		eComentario = new Entidad();
		eComentario.setNombre("comentario");
		eComentario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("titulo", comentario.getTexto()),
						new Propiedad("usuario", comentario.getUsuario())
		))
		);
		eComentario = servPersistencia.registrarEntidad(eComentario);
		comentario.setId(eComentario.getId());
	}

	@Override
	public void borrarComentario(Comentario comentario) {
		Entidad eComentario;
		eComentario = servPersistencia.recuperarEntidad(comentario.getId());
		
		servPersistencia.borrarEntidad(eComentario);
		
	}

	@Override
	public void modificarComentario(Comentario comentario) {
		Entidad eComentario = servPersistencia.recuperarEntidad(comentario.getId());
		
		for (Propiedad propiedad : eComentario.getPropiedades()) {
			if(propiedad.getNombre().equals("titulo")) {
				propiedad.setValor(comentario.getTexto());
			}
			if(propiedad.getNombre().equals("usuario")) {
				propiedad.setValor(comentario.getUsuario());
			}
		}
	}

	@Override
	public Comentario recuperarComentario(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comentario> recuperarTodasComentarios() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
