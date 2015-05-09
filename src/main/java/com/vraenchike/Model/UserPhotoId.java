package com.vraenchike.Model;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Alexeev on 02-May-15.
 */
@Embeddable
public class UserPhotoId implements Serializable {

    private User u;
    private Photo p;

    @ManyToOne(cascade = CascadeType.ALL)
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Photo getP() {
        return p;
    }

    public void setP(Photo p) {
        this.p = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPhotoId that = (UserPhotoId) o;

        if (p != null ? !p.equals(that.p) : that.p != null) return false;
        if (u != null ? !u.equals(that.u) : that.u != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = u != null ? u.hashCode() : 0;
        result = 31 * result + (p != null ? p.hashCode() : 0);
        return result;
    }
}
