package jp.slm.business.bean;

// Generated 22 janv. 2014 15:00:53 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ArtistRateId generated by hbm2java
 */
@SuppressWarnings("serial")
@Embeddable
public class ArtistRateId implements java.io.Serializable
{

    private Long idArtist;

    private Long idUser;

    @Column(name = "id_artist", nullable = false)
    public Long getIdArtist()
    {
        return this.idArtist;
    }

    public void setIdArtist(Long idArtist)
    {
        this.idArtist = idArtist;
    }

    @Column(name = "id_user", nullable = false)
    public Long getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser(Long idUser)
    {
        this.idUser = idUser;
    }

}