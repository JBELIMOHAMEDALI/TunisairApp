package com.example.tunisairapp.models;

public class DemandeAutorisation {

    private String heureDebut;
    private String heureFin;
    private String raison;
    private String etatAutoReq;
    private String dateAutoReq;

    public DemandeAutorisation(String heureDebut, String heureFin, String raison, String etatAutoReq, String dateAutoReq){
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.raison = raison;
        this.etatAutoReq = etatAutoReq;
        this.dateAutoReq = dateAutoReq;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getEtatAutoReq() {
        return etatAutoReq;
    }

    public void setEtatAutoReq(String etatAutoReq) {
        this.etatAutoReq = etatAutoReq;
    }


    public String getDateAutoReq() {
        return dateAutoReq;
    }

    public void setDateAutoReq(String dateAutoReq) {
        this.dateAutoReq = dateAutoReq;
    }

}
