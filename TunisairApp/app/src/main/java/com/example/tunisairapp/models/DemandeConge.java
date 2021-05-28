package com.example.tunisairapp.models;

public class DemandeConge {

    private String dateDebut;
    private String datefin;
    private String description;
    private String fichier;
    private String typeConge;
    private String etatRequestConge;

    public DemandeConge(String dateDebut, String datefin, String description, String fichier, String typeConge, String etatRequestConge)
    {
        this.dateDebut = dateDebut;
        this.datefin = datefin;
        this.description = description;
        this.fichier = fichier;
        this.typeConge = typeConge;
        this.etatRequestConge = etatRequestConge;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(String typeConge) {
        this.typeConge = typeConge;
    }

    public String getEtatRequestConge() {
        return etatRequestConge;
    }

    public void setEtatRequestConge(String etatRequestConge) {
        this.etatRequestConge = etatRequestConge;
    }

}
