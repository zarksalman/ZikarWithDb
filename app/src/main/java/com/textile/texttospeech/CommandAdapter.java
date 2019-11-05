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

public class CommandAdapter extends RecyclerView.Adapter<CommandAdapter.CommandsViewHolder> {

    private Context mContext;
    private List<CommandsText> commandsTextList = new ArrayList<>();
    private LayoutInflater inflater;
    private CommandItemBinding commandItemBinding;

    public CommandAdapter(Context context) {
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
        CommandsText commandsText = commandsTextList.get(position);
        holder.bindHolder(commandsText);
    }

    @Override
    public int getItemCount() {
        return commandsTextList.size();
    }

    public void setData(List<CommandsText> commandsTexts) {

        this.commandsTextList = commandsTexts;
        notifyDataSetChanged();
    }


    class CommandsViewHolder extends RecyclerView.ViewHolder {

        CommandItemBinding mCommandItemBinding;

        CommandsViewHolder(@NonNull CommandItemBinding commandItemBinding) {
            super(commandItemBinding.getRoot());

            mCommandItemBinding = commandItemBinding;
        }

        void bindHolder(CommandsText commandsText) {

            mCommandItemBinding.tvCommand.setText(commandsText.getCommand());
        }
    }
}