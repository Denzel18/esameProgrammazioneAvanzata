package it.univpm.advancedcode.cri.model.entities;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity(name = "Allegato")
@Table(name = "allegati", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ALLEGATO_ID")})
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Allegato {
    
	private long id;
	private String description;
	private Documentazione documento;
	
	
	/**
	 * Getter DESCRIZIONE allegato
	 * @return descrizione dell'allegato
	 */
	@Column(name = "DESCRIZIONE" , nullable=false, length=100) 
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Getter per la proprietà allegato
	 * @return allegato associato al documento
	 */


    @OneToOne(mappedBy = "allegatoDocumento")
	public Documentazione getDocumento() {
		return this.documento;
	}
	/**
	 * Getter ID allegato
	 * @return id dell'allegato
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ALLEGATO_ID", unique = true)
	public long getId() {
		return this.id;
	}
	
	/**
	 * Setter DESCRIPTION
	 * @param description
	 */
	public void setDescription(String description) {
		this.description=description;
	}
		
	/**
	 * Setter per la proprietà post
	 * @param post: post associato all'allegato da settare
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
