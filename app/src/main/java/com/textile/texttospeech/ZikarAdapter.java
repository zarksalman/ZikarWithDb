package com.textile.texttospeech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.textile.texttospeech.databinding.CommandItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ZikarAdapter extends RecyclerView.Adapter<ZikarAdapter.CommandsViewHolder> {

    private Context mContext;
    private List<Zikar> zikarList = new ArrayList<>();
    private LayoutInflater inflater;
    private CommandItemBinding commandItemBinding;

    public ZikarAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        commandItemBinding = DataBindingUtil.inflate(inflater, R.layout.command_item, parent, false);
        return new CommandsViewHolder(commandItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandsViewHolder holder, int position) {
        Zikar zikar = zikarList.get(position);
        holder.bindHolder(zikar);
    }

    @Override
    public int getItemCount() {
        return zikarList.size();
    }

    public void setData(List<Zikar> zikars) {

        this.zikarList = zikars;
        notifyDataSetChanged();
    }


    class CommandsViewHolder extends RecyclerView.ViewHolder {

        CommandItemBinding mCommandItemBinding;

        CommandsViewHolder(@NonNull CommandItemBinding commandItemBinding) {
            super(commandItemBinding.getRoot());

            mCommandItemBinding = commandItemBinding;
        }

        void bindHolder(Zikar zikar) {

            mCommandItemBinding.tvCommand.setText(zikar.getZikar());
        }
    }
}