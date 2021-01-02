package com.SS.restapi.models;

import javax.persistence.*;
import com.SS.restapi.models.Constants;


@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "SELECT t FROM Book t")
})
public class AntiqueBook extends Book{
    private static final long serialVersionUID = 1L;

    @Column(name = "releaseYear")
    private Integer releaseYear;

    /* Release year */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /* Antique book's total price */
    @Override
    public Double calcTotalPrice() {
        return  getQuantity() * getUnitPrice() * (Constants.CURRENT_YEAR-releaseYear)/Constants.ANTIQUE_TOTAL_PRICE_WEIGHT;
    }

    /* Update Antique book's attributes */
    @Override
    public Boolean updateItem(String key, String value) {
        if (key.equals("releaseYear")){
            setReleaseYear(Integer.parseInt(value));    // Special for antique book
            return true;
        } else {
            return super.updateItem(key, value);        // Update main attributes
        }
    }
}
