package jp.slm.business.bean;

// Generated 22 janv. 2014 15:00:53 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jp.slm.business.bean.generic.GenericLongIdBean;

/**
 * Event generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "event")
public class Event extends GenericLongIdBean
{
    private Artist artist;

    private String title;

    private String description;

    private Date dateTime;

    private Float duration;

    private float lat;

    private float long_;

    private String adress;

    private Set<Comment> comments = new HashSet<Comment>(0);

    private Set<User> users = new HashSet<User>(0);

    private Set<EventRate> eventRates = new HashSet<EventRate>(0);

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

    @Column(name = "title", nullable = false, length = 45)
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Column(name = "description", nullable = false, length = 512)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time", nullable = false, length = 19)
    public Date getDateTime()
    {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime)
    {
        this.dateTime = dateTime;
    }

    @Column(name = "duration", precision = 12, scale = 0)
    public Float getDuration()
    {
        return this.duration;
    }

    public void setDuration(Float duration)
    {
        this.duration = duration;
    }

    @Column(name = "lat", nullable = false, precision = 12, scale = 0)
    public float getLat()
    {
        return this.lat;
    }

    public void setLat(float lat)
    {
        this.lat = lat;
    }

    @Column(name = "long", nullable = false, precision = 12, scale = 0)
    public float getLong_()
    {
        return this.long_;
    }

    public void setLong_(float long_)
    {
        this.long_ = long_;
    }

    @Column(name = "adress", nullable = false, length = 256)
    public String getAdress()
    {
        return this.adress;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    public Set<Comment> getComments()
    {
        return this.comments;
    }

    public void setComments(Set<Comment> comments)
    {
        this.comments = comments;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "presence", catalog = "slm", joinColumns = {
        @JoinColumn(name = "id_event", nullable = false, insertable = false, updatable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)})
    public Set<User> getUsers()
    {
        return this.users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    public Set<EventRate> getEventRates()
    {
        return this.eventRates;
    }

    public void setEventRates(Set<EventRate> eventRates)
    {
        this.eventRates = eventRates;
    }

}