package jp.slm.business.bean;

// Generated 22 janv. 2014 15:00:53 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jp.slm.business.bean.generic.GenericLongIdBean;

/**
 * Picture generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "picture")
public class Picture extends GenericLongIdBean
{
    private Artist artist;

    private Date inDate;

    private Long order;

    private String comment;

    private byte[] pictureData;

    private Set<Comment> comments = new HashSet<Comment>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artist", nullable = false, insertable = false, updatable = false)
    public Artist getArtist()
    {
        return this.artist;
    }

    public void setArtist(Artist artist)
    {
        this.artist = artist;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "in_date", nullable = false, length = 10)
    public Date getInDate()
    {
        return this.inDate;
    }

    public void setInDate(Date inDate)
    {
        this.inDate = inDate;
    }

    @Column(name = "order", nullable = false)
    public Long getOrder()
    {
        return this.order;
    }

    public void setOrder(Long order)
    {
        this.order = order;
    }

    @Column(name = "comment", nullable = false, length = 512)
    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    @Column(name = "picture_data")
    public byte[] getPictureData()
    {
        return this.pictureData;
    }

    public void setPictureData(byte[] pictureData)
    {
        this.pictureData = pictureData;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "picture")
    public Set<Comment> getComments()
    {
        return this.comments;
    }

    public void setComments(Set<Comment> comments)
    {
        this.comments = comments;
    }

}