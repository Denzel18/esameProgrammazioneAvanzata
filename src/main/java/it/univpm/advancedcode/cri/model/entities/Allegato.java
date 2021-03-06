package it.univpm.advancedcode.cri.model.entities;
import javax.persistence.*;

@Entity
@Table(name = "allegati")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Allegato {
    
	private long id;
	private String descrizione;
	private Documentazione documento;
	
	
	/**
	 * Getter DESCRIZIONE allegato
	 * @return descrizione dell'allegato
	 */
	@Column(name = "descrizione" , nullable=false, length=100) 
	public String getDescrizione() {
		return this.descrizione;
	}
	
	/**
	 * Getter per la proprietà allegato
	 * @return allegato associato al documento
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "documento_id", nullable = false)
	public Documentazione getDocumento() {
		return this.documento;
	}
	/**
	 * Getter ID allegato
	 * @return id dell'allegato
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="allegato_id")
	public long getId() {
		return this.id;
	}
	
	/**
	 * Setter DESCRIPTION
	 * @param description
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}
		
	/**
	 * Setter documentazione
	 * @param documento: documento associato all'allegato da settare
	 */
	public void setDocumento(Documentazione documento) {
		this.documento=documento;
	}
	/**
	 * Setter ID
	 * @param id
	 */
	public void setId(long id) {
		this.id=id;
	}
}
