package com.example.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import java.util.List;

public class AgendamentoAdapter extends RecyclerView.Adapter<AgendamentoAdapter.AgendamentoViewHolder> {

    private Context context;
    private List<Agendamento> agendamentoList;

    public AgendamentoAdapter(Context context, List<Agendamento> agendamentoList) {
        this.context = context;
        this.agendamentoList = agendamentoList;
    }

    @NonNull
    @Override
    public AgendamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item (item_agendamento.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.item_agendamento, parent, false);
        return new AgendamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentoViewHolder holder, int position) {
        // Pega o item da lista
        Agendamento agendamento = agendamentoList.get(position);

        // Define os dados nas Views
        holder.tvTitulo.setText(agendamento.getTitulo());
        holder.tvDataHora.setText(agendamento.getDataHora());
        holder.tvMateriais.setText(agendamento.getMateriais());
        holder.chipTag.setText(agendamento.getTag());

        // TODO: Adicionar lógica de clique para os botões (Detalhes, Editar, etc.)
    }

    @Override
    public int getItemCount() {
        return agendamentoList.size();
    }

    // O ViewHolder gerencia as Views de cada item
    public static class AgendamentoViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitulo, tvDataHora, tvMateriais;
        Chip chipTag;
        // Adicione os botões aqui se precisar de lógica de clique neles

        public AgendamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tv_item_titulo);
            tvDataHora = itemView.findViewById(R.id.tv_item_data_hora);
            tvMateriais = itemView.findViewById(R.id.tv_item_materiais);
            chipTag = itemView.findViewById(R.id.chip_item_tag);
        }
    }
}