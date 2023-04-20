package tds.PhotoTDS.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.PhotoTDS.Comentario;
import tds.PhotoTDS.Foto;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorTDSFotoDAO implements IAdaptadorFotoDAO{

	ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private PoolDAO poolFotos = PoolDAO.getInstance();
	private static AdaptadorTDSFotoDAO unicaInstancia;
	
	public void registrarFoto(Foto foto) {
		boolean existe = false;
		Entidad eFoto = null;
		try {
			eFoto = servPersistencia.recuperarEntidad(foto.getId());
		} catch (NullPointerException e) {
			existe = true;
		}
		if (existe || eFoto != null) return;
		
		eFoto = new Entidad();
		eFoto.setNombre("foto");
		eFoto.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("titulo", foto.getTitulo()),
						new Propiedad("fecha", dateFormat.format(foto.getFecha())),
						new Propiedad("descripcion", foto.getDescripcion()),
						new Propiedad("meGusta", String.valueOf(foto.getMeGusta())),
						new Propiedad("hashtags", AuxiliarDAO.obtenerCadenaDeIds(foto.getHashtags())),
						new Propiedad("usuario", String.valueOf(foto.getUsuario())),
						new Propiedad("comentarios", AuxiliarDAO.obtenerIdsComentarios(foto.getComentarios())),
						new Propiedad("ruta", foto.getPath())))
		);
		eFoto = servPersistencia.registrarEntidad(eFoto);
		foto.setId(eFoto.getId());
	}
	
	@Override
	public void borrarFoto(Foto foto) {
		Entidad eFoto;
		eFoto = servPersistencia.recuperarEntidad(foto.getId());
		
		servPersistencia.borrarEntidad(eFoto);
		
	}

	@Override
	public void modificarFoto(Foto foto) {
		Entidad eFoto = servPersistencia.recuperarEntidad(foto.getId());
		
		for (Propiedad propiedad : eFoto.getPropiedades()) {
			if(propiedad.getNombre().equals("titulo")) {
				propiedad.setValor(foto.getTitulo());
			}
			if(propiedad.getNombre().equals("fecha")) {
				propiedad.setValor(dateFormat.format(foto.getFecha()));
			}
			if(propiedad.getNombre().equals("descripcion")) {
				propiedad.setValor(foto.getDescripcion());
			}
			if(propiedad.getNombre().equals("meGusta")) {
				propiedad.setValor(String.valueOf(foto.getMeGusta()));
			}
			if(propiedad.getNombre().equals("hashtags")) {
				propiedad.setValor(AuxiliarDAO.obtenerCadenaDeIds(foto.getHashtags()));
			}
			if(propiedad.getNombre().equals("usuario")) {
				propiedad.setValor(String.valueOf(foto.getUsuario()));
			}
			if(propiedad.getNombre().equals("comentarios")) {
				propiedad.setValor(AuxiliarDAO.obtenerIdsComentarios(foto.getComentarios()));
			}
			if(propiedad.getNombre().equals("ruta")) {
				propiedad.setValor(foto.getPath());
			}
			
			servPersistencia.modificarPropiedad(propiedad);
		}
	}

	@Override
	public Foto recuperarFoto(int codigo) throws Exception {
		if (poolFotos.contains(codigo)) return (Foto) poolFotos.getObject(codigo);
		
		String titulo = null;
		Date fecha = null;
		String descripcion = null;
		int meGusta = 0;
		ArrayList<String> hashtags = null;
		ArrayList<Comentario> comentarios = null;
		int usuario = 0;
		String ruta = null;
		
		Entidad eFoto = servPersistencia.recuperarEntidad(codigo);
		titulo = servPersistencia.recuperarPropiedadEntidad(eFoto, "titulo");
		descripcion = servPersistencia.recuperarPropiedadEntidad(eFoto, "descripcion");
		meGusta = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eFoto, "meGusta"));
		usuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eFoto, "usuario"));
		ruta = servPersistencia.recuperarPropiedadEntidad(eFoto, "ruta");
		try {
			fecha = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eFoto, "fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		hashtags = (ArrayList<String>) AuxiliarDAO.obtenerListaDeIds(servPersistencia.recuperarPropiedadEntidad(eFoto, "hashtags"));
		comentarios = (ArrayList<Comentario>) AuxiliarDAO.obtenerComentariosDesdeIds(servPersistencia.recuperarPropiedadEntidad(eFoto, "comentarios"));
		
		Foto foto = new Foto(titulo, descripcion, hashtags, usuario, ruta);
		foto.setComentarios(comentarios);
		foto.setFecha(fecha);
		foto.setMeGusta(meGusta);
		foto.setId(codigo);
		
		return foto;
	}

	@Override
	public List<Foto> recuperarTodasFotos() {
		List<Foto> fotoes = new ArrayList<Foto>();
        List<Entidad> eFotoes = servPersistencia.recuperarEntidades("foto");
        for(Entidad e : eFotoes) {
        	try {
				fotoes.add(recuperarFoto(e.getId()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        return fotoes;
	}

	public static AdaptadorTDSFotoDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			 return new AdaptadorTDSFotoDAO();
		else
			 return unicaInstancia; 
	}
}
