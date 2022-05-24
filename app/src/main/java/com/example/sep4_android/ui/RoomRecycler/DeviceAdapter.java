package com.example.sep4_android.ui.RoomRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;
import com.example.sep4_android.model.persistence.entities.Device;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private List<Device> devices;
    private OnClickListener listener;

    public DeviceAdapter(List<Device> devices){
        this.devices = devices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);;
        ViewHolder viewH = new ViewHolder(view);
        return viewH;
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.className.setText(devices.get(position).getRoomName());
        holder.classAvg.setText(""+32+" \u2103");
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView className;
        private final TextView classAvg;

        ViewHolder(View itemView){
            super(itemView);
            className = itemView.findViewById(R.id.rvRoom_text);
            classAvg = itemView.findViewById(R.id.rvRoom_avg);
            itemView.setOnClickListener(v ->{
                listener.onClick(devices.get(getBindingAdapterPosition()));
            });

        }
    }

}
