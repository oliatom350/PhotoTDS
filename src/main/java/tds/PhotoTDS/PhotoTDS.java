package tds.PhotoTDS;

public class PhotoTDS {

	//Atributos
	private RepoUsuarios repUsers;
	private RepoPublicaciones repPubli;
	private GeneradorPDF genPDF;
	private GeneradorExcel genExcel;
	
	//Constructor
	public PhotoTDS(RepoUsuarios repUsers, RepoPublicaciones repPubli, GeneradorPDF genPDF, GeneradorExcel genExcel) {
		super();
		this.repUsers = repUsers;
		this.repPubli = repPubli;
		this.genPDF = genPDF;
		this.genExcel = genExcel;
	}
	
	//Metodos
	public RepoUsuarios getRepUsers() {
		return repUsers;
	}

	public RepoPublicaciones getRepPubli() {
		return repPubli;
	}

	public GeneradorPDF getGenPDF() {
		return genPDF;
	}

	public GeneradorExcel getGenExcel() {
		return genExcel;
	}

	public void registrarUsuario() {
		
	}
	
	public void addFoto() {
		
	}
}
