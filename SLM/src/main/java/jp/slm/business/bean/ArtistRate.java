package jp.slm.business.bean;

// Generated 22 janv. 2014 15:00:53 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jp.slm.business.bean.generic.GenericEmbeddedPkBean;

/**
 * ArtistRate generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "artist_rate")
public class ArtistRate extends GenericEmbeddedPkBean<ArtistRateId> {
	
	private User user;
	
	private Artist artist;
	
	private Integer rate;
	
	@Override
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "idArtist", column = @Column(name = "id_artist", nullable = false)),
			@AttributeOverride(name = "idUser", column = @Column(name = "id_user", nullable = false)) })
	public ArtistRateId getId() {
		return this.id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_artist", nullable = false, insertable = false, updatable = false)
	public Artist getArtist() {
		return this.artist;
	}
	
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	@Column(name = "rate", nullable = false)
	public Integer getRate() {
		return this.rate;
	}
	
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
}
