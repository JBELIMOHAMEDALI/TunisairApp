package com.example.tunisairapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tunisairapp.R;
import com.example.tunisairapp.models.DemandeConge;

import java.util.List;

import static com.example.tunisairapp.R.color.black;
import static com.example.tunisairapp.R.color.myDarkRed;
import static com.example.tunisairapp.R.color.myblue;
import static com.example.tunisairapp.R.color.mydarkGreen;
import static com.example.tunisairapp.R.color.mydarkOrange;

public class ListDemandeCongeAdapter extends RecyclerView.Adapter<ListDemandeCongeAdapter.DemandeCongeViewHolder> {

    List<DemandeConge> listeDemandeConge;
    Context context;
    private OnDemandeCongeListener mOnDemandeCongeListener;

    public ListDemandeCongeAdapter(List<DemandeConge> listeCamp, Context context, OnDemandeCongeListener mOnCampListener ) {
        this.listeDemandeConge = listeCamp;
        this.context = context;
        this.mOnDemandeCongeListener = mOnCampListener;
    }

    @NonNull
    @Override
    public DemandeCongeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vue= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_demande_conge_item,parent,false);
        DemandeCongeViewHolder PVH=new DemandeCongeViewHolder(vue, mOnDemandeCongeListener);
        return PVH;
    }

    @Override
    public void onBindViewHolder(@NonNull DemandeCongeViewHolder holder, int position) {

        DemandeConge demandeConge = listeDemandeConge.get(position);

        holder.dateDebut.setText(demandeConge.getDateDebut());
        holder.dateFin.setText(demandeConge.getDatefin());
        holder.typeConge.setText(demandeConge.getTypeConge());
        holder.etatReqConge.setText(demandeConge.getEtatRequestConge());
        switch (holder.etatReqConge.getText().toString()){
            case "Accepted" :
                holder.etatReqConge.setTextColor(ContextCompat.getColor(context, mydarkGreen));
                break;
            case "In progress" :
                holder.etatReqConge.setTextColor(ContextCompat.getColor(context, mydarkOrange));
                break;
            case "Rejected" :
                holder.etatReqConge.setTextColor(ContextCompat.getColor(context, myblue));
                break;
            default:
                holder.etatReqConge.setTextColor(ContextCompat.getColor(context, black));
        }

        //holder.description.setText(demandeConge.getDescription());
        //holder.fichier.setText(demandeConge.getFichier());

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Libelle: "+listeCamp.get(position).getLibelle()+ "\n quantite :" + listeCamp.get(position).getQuantité(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listeDemandeConge.size();
    }

    public static class DemandeCongeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dateDebut, dateFin, description, fichier, typeConge, etatReqConge;
        OnDemandeCongeListener onDemandeCongeListener;

        public DemandeCongeViewHolder(@NonNull View itemView, OnDemandeCongeListener onCampListener) {
            super(itemView);

            dateDebut = itemView.findViewById(R.id.date_debut);
            dateFin = itemView.findViewById(R.id.date_fin);
            typeConge = itemView.findViewById(R.id.txtTitreType);
            etatReqConge = itemView.findViewById(R.id.etatLeaveReq);
            //description =itemView.findViewById(R.id.desc);
            //fichier= itemView.findViewById(R.id.fichier);

            this.onDemandeCongeListener = onCampListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onDemandeCongeListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnDemandeCongeListener
    {
        void onNoteClick(int position);
    }
}
