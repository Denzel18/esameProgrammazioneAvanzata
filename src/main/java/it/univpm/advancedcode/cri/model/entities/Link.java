package it.univpm.advancedcode.cri.model.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "links")
@PrimaryKeyJoinColumn(name="allegato_id")
public class Link extends Allegato implements Serializable {
	
	/**
	 * 
	 */
	private String link;

	/**
	 * Getter per la proprietà link
	 * @return link 
	 */
	@Column(nullable=false, length = 300)
	public String getLink() {
		return link;
	}

	/**
	 * Setter per la proprietà link
	 * @param link: link da settare
	 */
	public void setLink(String link) {
		this.link = link;
	}
}
